package cn.vetech.web.vedsb.cgdzbb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.cache.EhcacheManage;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgdzbb.DetrTsl;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslBean;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslCheck;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslDzjg;
import cn.vetech.vedsb.jp.entity.cgdzbb.ParseTSL;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.cgdzbb.DetrTslServiceImpl;
import cn.vetech.vedsb.jp.service.cgdzbb.JpTslCheckServiceImpl;
import cn.vetech.vedsb.jp.service.cgdzbb.JpTslDzjgServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCgdzController extends MBaseControl<JpJp, JpJpServiceImpl>{
	@Autowired
	private EhcacheManage ehcacheManage;
	@Autowired
	private JpTslCheckServiceImpl jpTslCheckServiceImpl;
	@Autowired
	private DetrTslServiceImpl detrTslServiceImpl;
	@Autowired
	private JpTslDzjgServiceImpl jpTslDzjgServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	private final static int CACHE_TIME=1800;//缓存秒数 30分钟
	private final String CACHE_BSPET_KEY="BSPET_DS_DATA";
	private final String CACHE_TSL_KEY="TSL_WEB_DATA";
	public static final String FETCH_TICKETS_DATA="FetchTicketsData";
	@Override
	public void insertInitEntity(JpJp t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInitEntity(JpJp t) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 采购对账查询bspet票电商数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String searchBspet(HttpServletRequest request,Model model){
		Shyhb user=SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			String office = request.getParameter("office");
			String printid = request.getParameter("printno");
			String cp_pid = request.getParameter("cp_pid");	
			String ksrq = request.getParameter("ksrq");
			String searchString = office+ printid+ksrq;
			String key = this.getKey(user.getBh(), searchString, CACHE_BSPET_KEY);
			this.removeCache(key);//查询之前清空缓存
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("officeid", office);
			map.put("printid", printid);
			map.put("agent", cp_pid);
			map.put("searchdatetime", ksrq);
			map.put("shbh", shbh);
			List<Map<String, Object>> list = this.baseService.searchBspet(map);
			if(CollectionUtils.isNotEmpty(list)){
				this.putCache(key, list);//将系统数据放入缓存中
			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("查询bspet票数据报错!", e);
			e.printStackTrace();
		}
		return viewpath("jpCgdzBspetLeft");
	}
	
	@RequestMapping
	public String serachTslData(HttpServletRequest request,Model model){
		synchronized (JpCgdzController.class){
			try {
				Shyhb user=SessionUtils.getShshbSession();
				/**
				 * 检查页面参数是否合法
				 */
				//tsl指令日期参数
				String date = StringUtils.trimToEmpty(request.getParameter("tsldate"));
				//tsl指令office参数
				String office = StringUtils.trimToEmpty(request.getParameter("office"));
				//tsl指令printno参数
				String printno = StringUtils.trimToEmpty(request.getParameter("printno"));
				if(StringUtils.isBlank(date)||StringUtils.isBlank(office)||StringUtils.isBlank(printno)){
					throw new Exception("TSL指令参数不合法：office="+office+",printno="+printno+",date="+date);
				}
				if(!VeDate.isDate(date)){
					throw new Exception("TSL指令日期参数不合法，不是真实的日期：date="+date);
				}
				String searchString = office+printno+date;
				String key = this.getKey(user.getBh(), searchString, CACHE_TSL_KEY);
				this.removeCache(key);//查询之前清空缓存
				
				/**
				 * 调用接口查询数据
				 */
				
		    	List<JpTslBean> list=processTSlInfo(request);//调取tsl接口方法(processTSlInfo)----------------------------------
				
				if(CollectionUtils.isNotEmpty(list)){
					this.putCache(key, list);//将接口返回的数据放入缓存中用作后面的对账作用
				}
				model.addAttribute("list",list);
			} catch (Exception e) {
				logger.error("查询tsl数据报错!", e);
				e.printStackTrace();
			}
		return viewpath("jpCgdzTslRight");
		}
	}
	
	/**
	 * 处理TSL数据
	 * 当天的TSL数据，存在就更新，不存在就插入
	 * 历史的TSL数据，直接在数据库中查询
	 * 对于未来的数据不予处理
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<JpTslBean> processTSlInfo(HttpServletRequest request) throws Exception{
		Shyhb user=SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		// 获取PID配置
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
		if (jpPz == null) {
			throw new Exception("未获取到商户PID配置");
		}
		String userid = user.getPidyh();
		//userid="16072718360531";
		//tsl指令日期参数
		String date = StringUtils.trimToEmpty(request.getParameter("tsldate"));
		//tsl指令office参数
		String office = StringUtils.trimToEmpty(request.getParameter("office"));
		//tsl指令printno参数
		String printno = StringUtils.trimToEmpty(request.getParameter("printno"));
		//tsl指令agent参数
		String agent = StringUtils.trimToEmpty(request.getParameter("agent"));
		//agent = "44867";
		if(StringUtils.isBlank(agent)){
			agent="none";
		}
		//是否直接获取TSL数据
		String querytype = StringUtils.trimToEmpty(request.getParameter("querytype"));//1不重新提取,,,2重新提取
		//查询数据库中是否存在对应的TSL数据
		JpTslCheck jpTslCheck = this.jpTslCheckServiceImpl.selTslCheck(date, office, agent, printno, user.getShbh());
		
		if(null!=jpTslCheck){
			request.setAttribute("lastTime", jpTslCheck.getZhtqsj());
		}else{
			request.setAttribute("lastTime", VeDate.dateToStrLong(new Date()));
		}
		/**
		 * 比较查询日期与当天日期两个时间大小
		 * 查询日期 与 当天日期 为同一天返回0
		 * 查询日期 早于 当天日期 返回相差的天数   负数
		 * 查询日期 晚于 当天日期 返回相差的天数	 正数
		 */
		int diffDay=VeDate.getTwoDay(date,VeDate.getStringDateShort());
		
		//是否直接查询航信数据
		if(StringUtils.isNotBlank(querytype)&&"1".equals(querytype)){
			diffDay=0;//视为同一天来处理
		}
		if(diffDay>0){
			throw new Exception("TSL指令日期参数不合法，不能查询未来时间：date="+date);
		}else if(diffDay<0){
			/**
			 * 查询以前数据
			 */
			List<JpTslBean> list = null;
			if(jpTslCheck!=null){
				//根据表中记录的xml数据直接解析TSL账单数据
				JpTslBean j = ParseTSL.parseTSLXml(jpTslCheck.getXmlstr());
				list = j.getTicketList();
			}
			 if(CollectionUtils.isNotEmpty(list)){
				 return list;
			 }else{
				 if(jpTslCheck!=null){
					throw new Exception("解析TSL账单XML异常!");
				 }
				String reqXml = this.tslRequestXml(date, office, printno, agent, userid);
				String pzip = jpPz.getPzIp();
				pzip = pzip + ":" + jpPz.getPzPort();
				WebEtermService etermService = new WebEtermService(pzip);//----urls
				String resultData = etermService.generalCmdProcess(reqXml);//掉接口
				JpTslBean tslbean = ParseTSL.parseTSLXml(resultData);//解析
				if(tslbean==null ||tslbean.getTicketList().isEmpty()){
		    		throw new Exception("TSL账单接口调用没有返回数据");
		    	}
				tslbean.setDate(date);
		    	//用户id
				tslbean.setUserid(user.getShbh());
				//OFFICE号
				tslbean.setOffice(office);
				//AGENT号
				tslbean.setAgent(agent);
				//PRINTNO号
				tslbean.setPrintno(printno);
				List<JpTslBean> lists = this.updateTSLData(tslbean);
				return lists;
			 }
				
		}else{
			String reqXml = this.tslRequestXml(date, office, printno, agent, userid);
			WebEtermService etermService = new WebEtermService(jpPz.getPzIp());//----url
			String resultData = etermService.generalCmdProcess(reqXml);//掉接口
			JpTslBean tslbean = ParseTSL.parseTSLXml(resultData);//解析
			if(tslbean==null ||tslbean.getTicketList().isEmpty()){
	    		throw new Exception("获取TSL账单接口调用发生异常");
	    	}
			//是否已经存在记录
	    	if(jpTslCheck!=null){
	    		tslbean.setTslId(jpTslCheck.getId());
	    	}
			tslbean.setDate(date);
	    	//用户id
			tslbean.setUserid(user.getShbh());
			//OFFICE号
			tslbean.setOffice(office);
			//AGENT号
			tslbean.setAgent(agent);
			//PRINTNO号
			tslbean.setPrintno(printno);
			
			List<JpTslBean> lists = this.updateTSLData(tslbean);
			return lists;
		}
	}
	
	private List<JpTslBean> updateTSLData(JpTslBean jpTslBean) throws Exception{
		Shyhb user=SessionUtils.getShshbSession();
		//检查接口数据数据参数是否合法
		if( jpTslBean==null || jpTslBean.getTicketList().isEmpty()){
			return null;
		}
		List<JpTslBean> tslBeanList=jpTslBean.getTicketList();
		//返回的机票数量
		int size=tslBeanList.size();
		String datestr = jpTslBean.getDate();
		String officeid = jpTslBean.getOffice();
		String agent = jpTslBean.getAgent();
		String printno = jpTslBean.getPrintno();
		List<Map<String, Object>> list = this.detrTslServiceImpl.searchDetrTsl(datestr, officeid, agent, printno, user.getShbh());//查询明细表
		
		//先更新主表数据 JP_TSL_CHECK
		JpTslCheck jpTslCheck0 = new JpTslCheck();
		jpTslCheck0.setId(jpTslBean.getTslId());
		jpTslCheck0.setZhtqsj(VeDate.dateToStrLong(new Date()));
		jpTslCheck0.setZhtjr(jpTslBean.getUserid());
		jpTslCheck0.setXmlstr(jpTslBean.getXmlStr());
		
		//插入主表数据 T_TSL_CHECK(根据cp_date,officeid,agent,printno,shbh)
		
		String cp_date = jpTslBean.getDate();//删除主表参数
		
		//插入主表参数
		JpTslCheck jpTslCheck1 = new JpTslCheck();
		String id = VeDate.getNo(6);
		jpTslCheck1.setId(id);
		jpTslCheck1.setCpDate(jpTslBean.getDate());
		jpTslCheck1.setOfficeid(jpTslBean.getOffice());
		jpTslCheck1.setAgent(jpTslBean.getAgent());
		jpTslCheck1.setPrintno(jpTslBean.getPrintno());
		jpTslCheck1.setZhtqsj(VeDate.dateToStrLong(new Date()));
		jpTslCheck1.setZhtjr(jpTslBean.getUserid());
		jpTslCheck1.setShbh(user.getShbh());
//		jpTslCheck1.setBy1("");
//		jpTslCheck1.setBy2("");
//		jpTslCheck1.setBy3("");
		jpTslCheck1.setXmlstr(jpTslBean.getXmlStr());
		List<JpTslBean> listTslbean = jpTslBean.getTicketList();
		if(CollectionUtils.isNotEmpty(list)){//接口返回的数据系统数据库存在
			String keyid = (String)list.get(0).get("BY1");//取第一个
			/**
			 * 更新明细表数据,先根据查询条件删除主表数据,再插入主表数据，在
			 */
			List<JpTslBean>  insertList=new ArrayList<JpTslBean>();//过滤有重复记录的数据
			for(int i=0;i<listTslbean.size();i++){
				JpTslBean  jpBean= listTslbean.get(i);
				String tkno=jpBean.getTicketno();
//				 if(tkno!=null){
//					 String[] tknStr=tkno.split("-");
//					 if(tknStr!=null&&tknStr.length==2){
//						 tkno=tknStr[1];
//					 }
//				 }
//				String office=jpBean.getOffice();
//				String agents=jpBean.getAgent();
//				String printnos=jpBean.getPrintno();
				String date=jpBean.getDate();
				String city=jpBean.getOrigdest();
				String isVoid="0"; 
				String refund="0";
				if(city!=null){
					 if(city.length()==4){
						isVoid="1";//废票
					}else{
						refund="1";//退票
					}
				
				}
				boolean isExit=false;
				for(int j=0;j<list.size();j++){
					Map<String, Object> map=list.get(j);
					if(map!=null&&!map.isEmpty()){
						if(tkno.equals(map.get("TKNO"))
								&&date.equals(map.get("DATESTR"))
								&&isVoid.equals(map.get("VOIDN"))
								&&refund.equals(map.get("REFUND"))){
							isExit=true;//存在相同的记录；
							break;
						}
					}
				}
				if(!isExit){
//					jpBean.setOffice(jpTslBean.getOffice());
//					jpBean.setPrintno(jpTslBean.getPrintno());
//					jpBean.setDate(jpTslBean.getDate());
					insertList.add(jpBean);
				}
				JpTslCheck jpTslCheck4 = new JpTslCheck();
				String zhtqsj = VeDate.dateToStrLong(new Date());
				String zhtjr = user.getShbh();
				String xmlstr = jpTslBean.getXmlStr();
				jpTslCheck4.setZhtjr(zhtjr);
				jpTslCheck4.setZhtqsj(zhtqsj);
				jpTslCheck4.setXmlstr(xmlstr);
				jpTslCheck4.setId(keyid);
				//this.jpTslCheckServiceImpl.deleteTslCheck(cp_date, officeid, agent, printno, user.getShbh());
				//this.jpTslCheckServiceImpl.insert(jpTslCheck1);
				this.jpTslCheckServiceImpl.update(jpTslCheck4);//更新主表
				this.insertDetr(insertList, id,jpTslBean.getOffice(),jpTslBean.getDate(),jpTslBean.getPrintno());//插入明细表数据
			}
		}else{
			/**
			 * 插入明细表数据,更新主表数据
			 */
			String zhtqsj = VeDate.dateToStrLong(new Date());
			String zhtjr = user.getShbh();
			String xmlstr = jpTslBean.getXmlStr();
			JpTslCheck jpTslCheck2 = new JpTslCheck();
			if(StringUtils.isNotBlank(jpTslBean.getTslId())){//如果在主表中存在记录直接更新主表再插入明细表
				jpTslCheck2.setZhtjr(zhtjr);
				jpTslCheck2.setZhtqsj(zhtqsj);
				jpTslCheck2.setXmlstr(xmlstr);
				jpTslCheck2.setId(jpTslBean.getTslId());
				this.jpTslCheckServiceImpl.update(jpTslCheck2);//根据Id更新主表
			}else{
				this.jpTslCheckServiceImpl.insert(jpTslCheck1);//如果不存在插入主表数据
			}
			this.insertDetr(listTslbean, id,jpTslBean.getOffice(),jpTslBean.getDate(),jpTslBean.getPrintno());
			
		}
		return jpTslBean.getTicketList();
	}
	
	private void insertDetr(List<JpTslBean> listTslbean,String id,String office,String date,String printno) throws Exception{
		Shyhb user=SessionUtils.getShshbSession();
		DetrTsl tsl = new DetrTsl();
		for(int i=0;i<listTslbean.size();i++){
			JpTslBean bean = listTslbean.get(i);
			tsl.setId(VeDate.getNo(6));//生成id
			 String tkno=bean.getTicketno();
//			 if(tkno!=null){
//				 String[] tknStr=tkno.split("-");
//				 if(tknStr!=null&&tknStr.length==2){
//					 tkno=tknStr[1];//不要票号前面的三位数,去后面的
//				 }
//			 }
			 tsl.setTkno(tkno);
			 String city=bean.getOrigdest();
			 String fromcity="";
			 String tocity="";
			 String isVoid="0"; 
			 String refund="0";
			 if(city!=null){
				if(city.length()==7){
					fromcity=city.substring(0, 3);
					tocity=city.substring(4, 7);
				}else if(city.length()==4){
					isVoid="1";//废票
				}else{
					refund="1";//退票
				}
			 }
			 tsl.setFromcity(fromcity);
			 tsl.setTocity(tocity);
			 String pj_xsj = VeStr.isDouble(bean.getCollection()) ? bean.getCollection() : "0";
			 tsl.setPjXsj(NumberUtils.toDouble(pj_xsj));
			 String pj_tax =VeStr.isDouble(bean.getTaxs()) ? bean.getTaxs() : "0";
			 tsl.setPjTax(NumberUtils.toDouble(pj_tax));
			 tsl.setComm(bean.getComm());
			 tsl.setPnrNo(bean.getPnr());
			 tsl.setPid(bean.getPid());
			 tsl.setOfficeid(office);
			 tsl.setAgent(bean.getAgent());
			 tsl.setPrintno(printno);
			 tsl.setDatestr(date);
			 tsl.setBy1(id);
			 tsl.setUserid(user.getShbh());
			 tsl.setShbh(user.getShbh());
			 tsl.setRefund(refund);
			 tsl.setVoidn(isVoid);
			 this.detrTslServiceImpl.insert(tsl);//插入数据库
		}
	}
	
	@RequestMapping
	public String compareToTSl(HttpServletRequest request,Model model) throws Exception{
		synchronized (JpCgdzController.class){
			Shyhb user = SessionUtils.getShshbSession();
			/**
			 * 提取缓存中存放的 查询的BSPET票 以及 TSL账单数据
			 */
			List<Map<String, Object>> bspetDataList = new ArrayList<Map<String, Object>>();
			List<JpTslBean> tslDataList = new ArrayList<JpTslBean>();
			
			//tsl指令日期参数
			String date = StringUtils.trimToEmpty(request.getParameter("tsldate"));
			//tsl指令office参数
			String office = StringUtils.trimToEmpty(request.getParameter("office"));
			//tsl指令printno参数
			String printno = StringUtils.trimToEmpty(request.getParameter("printno"));
			String searchString = office+ printno+date;
			
			String key1 = this.getKey(user.getBh(), searchString, CACHE_BSPET_KEY);
			String key2 = this.getKey(user.getBh(), searchString, CACHE_TSL_KEY); 
			
			List<Map<String, Object>> bspetlist = (List)getCache(key1);//从缓存中获取电商数据
			if(CollectionUtils.isEmpty(bspetlist)){
				throw new Exception("请重新查询电商中的BSPET票数据。");
			}
			bspetDataList.addAll(bspetlist);
			List<JpTslBean>  tsllist = (List)getCache(key2);//从缓存中获取tsl数据
			if(CollectionUtils.isEmpty(tsllist)){
				throw new Exception("请重新提取TSL账单数据。");
			}
			tslDataList.addAll(tsllist);
			
			int bspet_size=bspetDataList.size();
			int tsl_size=tslDataList.size();
			
			//将tsl账单的原始数据处理成以票号为key ，记录为value的map   正常票
			Map<String, JpTslBean> tsl_tkno_map = new HashMap<String, JpTslBean>();
			//将tsl账单的原始数据处理成以票号为key ，记录为value的map   退票
			Map<String, JpTslBean> tp_tsl_tkno_map = new HashMap<String,JpTslBean>();
			//将tsl账单的原始数据处理成以票号为key ，记录为value的map	 废票
			Map<String, JpTslBean> fp_tsl_tkno_map = new HashMap<String, JpTslBean>();
			
			for(int i=0;i<tsl_size;i++){
				JpTslBean tslMap = tslDataList.get(i);
				 if(tslMap!=null){
					 String tkno=tslMap.getTicketno();
//					 if(tkno!=null){
//						 String[] tknStr=tkno.split("-");
//						 if(tknStr!=null&&tknStr.length==2){
//							 tkno=tknStr[1];
//						 }
//					 }
//					 //处理航程，导出用
//					 String city=tslMap.getOrigdest();
//					 String fromCity="";
//					 String toCity="";
//					 if(city.length()==7){
//							fromCity=city.substring(0, 3);
//							toCity=city.substring(4, 7);
//					 }
//					 if(StringUtils.isNotBlank(fromCity) && StringUtils.isNotBlank(toCity)){
//						 tslMap.put("HCMC", fromCity+"-"+toCity);
//					 }
					 String isVoid = tslMap.getIsVoid();
					 String isRefund = tslMap.getIsRefund();
					 //正常票
					 if("0".equals(isVoid)&&"0".equals(isRefund)){
						 if(tsl_tkno_map.get(tkno)==null){
							 tslMap.setPzztmc("正常票");//导出用
							 tsl_tkno_map.put(tkno, tslMap);
						 }else{
							 //存在相同的正常票票号
							 System.out.println("some"+tkno);
						 }
					 }
					 //退票
					 if("0".equals(isVoid)&&"1".equals(isRefund)){
						 if(tp_tsl_tkno_map.get(tkno)==null){
							 tslMap.setPzztmc("退票");//导出用;
							 tp_tsl_tkno_map.put(tkno, tslMap);
						 }else{
							 //存在相同的退票票号，
							 System.out.println("some"+tkno);
						 }
					 }
					 //废票
					 if("1".equals(isVoid)&&"0".equals(isRefund)){
						 if(fp_tsl_tkno_map.get(tkno)==null){
							 tslMap.setPzztmc("废票");//导出用
							 fp_tsl_tkno_map.put(tkno, tslMap);
						 }else{
							 //存在相同的废票票号
							 System.out.println("some"+tkno);
						 }
					 }
					 
				 }
			}
			
			//将电商系统中的bspet票的原始数据处理成以票号为key ，记录为value的map
			Map<String, Map<String, Object>> asms_tkno_map = new HashMap<String, Map<String, Object>>();//正常
			Map<String, Map<String, Object>> tp_asms_tkno_map = new HashMap<String, Map<String, Object>>();//退票
			Map<String, Map<String, Object>> fp_asms_tkno_map = new HashMap<String, Map<String, Object>>();//废票
			
			for(int i=0;i<bspet_size;i++){
				 Map<String, Object> bspetMap = bspetDataList.get(i);
				 if(bspetMap!=null && !bspetMap.isEmpty()){
					 String tkno=(String)bspetMap.get("TKNO");
					 String type=(String)bspetMap.get("TYPE");
					 if("1".equals(type)){//正常票
						 if(asms_tkno_map.get(tkno)==null){
							 asms_tkno_map.put(tkno, bspetMap);
						 }else{
							 //存在相同的票号，可能是退票、或者废票
						 }
					 }else if("2".equals(type)){//退票
						 if(tp_asms_tkno_map.get(tkno)==null){
							 tp_asms_tkno_map.put(tkno, bspetMap);
						 }else{
							 //存在相同的退票票号，可能是退票、或者废票
						 }
					 }else{//废票
						 if(fp_asms_tkno_map.get(tkno)==null){
							 fp_asms_tkno_map.put(tkno, bspetMap);
						 }else{
							 //存在相同的废票票号，可能是退票、或者废票
						 }
					 }
					
				 }
			}
			
			/**
			 * 对比数据
			 * 完全正确：票号一致、账单价、机建、税费完成相同　国际票要还要对比代理费率
			 * 票价不对：票号两边都一样，账单价、机建、税费，其中有至少一项不一致的数据 国际票要还要对代理费率
			 * 只在电商系统中存在：该票号在电商中存在，但在TSL账单中不存在
			 * 只在TSL账单中存在：该票号在TSL中存在，但在电商系统中不存在
			 * 废票：只核对票号，因为TSL账单中没有价格
			 */
			//完全正确：票号一致、账单价、机建、税费完成相同
			//List<Map<String, Object>> allRightList = new ArrayList<Map<String, Object>>();
			//票价不对：票号两边都一样，账单价、机建、税费，其中有至少一项不一致的数据
			//List<Map<String, Object>> amountErrorList = new ArrayList<Map<String, Object>>();
	
			//只在电商系统中存在：该票号在电商中存在，但在TSL账单中不存在
			//List<Map<String, Object>> onlyAsmsList = new ArrayList<Map<String, Object>>();
			//只在TSL账单中存在：该票号在TSL中存在，但在电商中不存在
			//List<Map<String, JpTslBean>> onlyTslList = new ArrayList<Map<String, JpTslBean>>();
			
			/**
			 * 处理  【完全正确】  的数据
			 */
			for(int i=0;i<bspet_size;i++){
				 
				 Map<String, Object> map = bspetDataList.get(i);
				 //allRightList.add(map);
				 if(map!=null&&!map.isEmpty()){
					
					 String TKNO = (String) map.get("TKNO");
					 String type = (String) map.get("TYPE");
					 //提取tsl账单中票号相同的记录
					 JpTslBean tslMap=null;
					 
					 if("1".equals(type)){
						 //提取tsl账单中票号相同的记录----正常票
						  tslMap = tsl_tkno_map.get(TKNO);
					 }else if("2".equals(type)){
						//提取tsl账单中票号相同的记录----退票
						  tslMap = tp_tsl_tkno_map.get(TKNO);
					 }else{
						//提取tsl账单中票号相同的记录----废票
						  tslMap = fp_tsl_tkno_map.get(TKNO);
					 }
					 
					
					 if(tslMap==null){
						 continue; 
					 }
					 tslMap.setOffice(office);
					 tslMap.setPrintno(printno);
					 tslMap.setDate(date);
					 /**
					  * 废票只需要比较 票号是否相同
					  */
					 String voidType=tslMap.getIsVoid();//tsl账单中的废票
					 if("3".equals(type)&&"1".equals(voidType)){
						 //Map<String, JpTslBean> tslMaps = new HashMap<String, JpTslBean>();
						 //tslMaps.put("comparetsl", tslMap);
						 //map.putAll(tslMaps);
						 //allRightList.add(map);//保存完全相同的bspet票数据
						 this.insertTslDzjg(tslMap,map,"0","",date);
						 continue;
					 }
					 
					 
					
					//账单价相同才继续比较
					 double zdj = Arith.Obj2Double(map.get("ZDJ"));
					 double tslZdj=0.00;
					 String tslZdjStr=tslMap.getCollection();
					 tslZdjStr = VeStr.isDouble(tslZdjStr)? tslZdjStr : "0.00";
					 if(tslZdjStr!=null&&tslZdjStr.length()!=0){
						 tslZdj = Arith.Obj2Double(new BigDecimal(tslZdjStr));
					 }
					 
					 if(Math.abs(zdj)!=Math.abs(tslZdj)){
						 continue;
					 }
					 
					//税费相同才继续比较
					 double pj_tax = Arith.Obj2Double(map.get("PJ_TAX"));
					 double pj_jsf =  Arith.Obj2Double(map.get("PJ_JSF"));
					 double tax=Arith.add(Arith.Obj2Double(pj_tax),Arith.Obj2Double(pj_jsf));
					 
					 
					 double tslTax=0.00;
					 String tslTaxStr=tslMap.getTaxs();
					 tslTaxStr = VeStr.isDouble(tslTaxStr)? tslTaxStr : "0.00";
					 if(tslTaxStr!=null&&tslTaxStr.length()!=0){
						 tslTax = Arith.Obj2Double(new BigDecimal(tslTaxStr));
					 }
					 
					 if(Math.abs(tax)!=Math.abs(tslTax)){
						 continue;
					 }
					 
					 //如果是国际票，要比较C值，代理费率
					 String gngj = ObjectUtils.toString(map.get("GNGJ"),"");
					 if(gngj.equals("0")){
						 double dlfl = Arith.Obj2Double(map.get("HXFL"));
						 dlfl = Arith.mul(dlfl, 100);
						 
						 
						 double tslComm = 0.00;
						 String tslCommStr=tslMap.getComm();
						 tslCommStr = VeStr.isDouble(tslCommStr)? tslCommStr : "0.00";
						 if(StringUtils.isNotBlank(tslCommStr)){
							 tslComm = Arith.Obj2Double(new BigDecimal(tslCommStr));
						 }
						 if(Math.abs(dlfl)!=Math.abs(tslComm)){
							 continue;
						 }
					 }
					 /**
					  * 将tsl账单数据添加在条记录中
					  */
//					 Map<String, JpTslBean> tslMaps = new HashMap<String, JpTslBean>();
//					 tslMaps.put("comparetsl", tslMap);
//					 map.putAll(tslMaps);
//					 
//					 allRightList.add(map);//保存完全相同的bspet票数据
					 this.insertTslDzjg(tslMap,map,"0","",date);
				 }
				 
			 }
			 //request.setAttribute("allRightList", allRightList);
//			 request.setAttribute("allRightSize", allRightList.size());
			 
			 /**
			  * 处理  【票价不对】  的数据
			  */
			 for(int i=0;i<bspet_size;i++){
				 Map<String, Object> map = bspetDataList.get(i);
				 if(map!=null&&!map.isEmpty()){
					 String TKNO = (String) map.get("TKNO");
					 
					 String type = (String) map.get("TYPE");
					 //提取tsl账单中票号相同的记录
					 JpTslBean tslMap=null;
					 
					 if("1".equals(type)){
						 //提取tsl账单中票号相同的记录----正常票
						  tslMap = tsl_tkno_map.get(TKNO);
					 }else if("2".equals(type)){
						//提取tsl账单中票号相同的记录----退票
						  tslMap = tp_tsl_tkno_map.get(TKNO);
					 }else{
						//提取tsl账单中票号相同的记录----废票
						  tslMap = fp_tsl_tkno_map.get(TKNO);
					 }
					 
					
					 if(tslMap==null){
						 continue; 
					 }
					 tslMap.setOffice(office);
					 tslMap.setPrintno(printno);
					 tslMap.setDate(date);
					 /**
					  * 废票只需要比较 票号是否相同
					  */
					 String voidType=tslMap.getIsVoid();//tsl账单中的废票
					 if("3".equals(type)&&"1".equals(voidType)){
						 continue;
					 }
					
					 
					 double zdj = Arith.Obj2Double(map.get("ZDJ"));
					 double tslZdj=0.00;
					 String tslZdjStr=tslMap.getCollection();
					 tslZdjStr = VeStr.isDouble(tslZdjStr)? tslZdjStr : "0.00";
					 if(tslZdjStr!=null&&tslZdjStr.length()!=0){
						 tslZdj = Arith.Obj2Double(new BigDecimal(tslZdjStr));
					 }
				     boolean isDiff=false;
				     String color1="0";
				     String color2="0";
				     String color3="0";
					 if(Math.abs(zdj)!=Math.abs(tslZdj)){
						 color1="1";
						 isDiff=true;
						 //amountErrorList.add(map);//保存 票价不正确 的bspet票数据
						 //continue;
					 }
					 
					 double pj_tax = Arith.Obj2Double(map.get("PJ_TAX"));
					 double pj_jsf =  Arith.Obj2Double(map.get("PJ_JSF"));
					 double tax=Arith.add(Arith.Obj2Double(pj_tax),Arith.Obj2Double(pj_jsf));
					
					 
					 double tslTax=0.00;
					 String tslTaxStr=tslMap.getTaxs();
					 tslTaxStr = VeStr.isDouble(tslTaxStr)? tslTaxStr : "0.00";
					 if(tslTaxStr!=null&&tslTaxStr.length()!=0){
						 tslTax = Arith.Obj2Double(new BigDecimal(tslTaxStr));
					 }
					 if(Math.abs(tax)!=Math.abs(tslTax)){
						 color2="1";
						 
						 isDiff=true;
						 //amountErrorList.add(map);//保存 票价不正确 的bspet票数据
						 //continue;
					 }
					 
					 //如果是国际票，要比较C值，代理费率
					 String gngj = ObjectUtils.toString(map.get("GNGJ"),"");
					 if(gngj.equals("0")){
						 double dlfl = Arith.Obj2Double(map.get("HXFL"));
						 dlfl = Arith.mul(dlfl, 100);
						 
						 double tslComm = 0.00;
						 String tslCommStr=tslMap.getComm();
						 tslCommStr = VeStr.isDouble(tslCommStr)? tslCommStr : "0.00";
						 if(StringUtils.isNotBlank(tslCommStr)){
							 tslComm = Arith.Obj2Double(new BigDecimal(tslCommStr));
						 }
						 if(Math.abs(dlfl)!=Math.abs(tslComm)){
							 color3="1";
							 isDiff=true;
						 }
					 }
					 
					 if(isDiff){
//						 Map<String, JpTslBean> tslMaps = new HashMap<String, JpTslBean>();
//						 tslMaps.put("comparetsl", tslMap);
//						 map.putAll(tslMaps);
//						 map.put("COLOR1",color1);
//						 map.put("COLOR2",color2);
//						 map.put("COLOR3",color3);
						 //amountErrorList.add(map);//保存 票价不正确 的bspet票数据
						 String cwlx="";
						 if(StringUtils.isNotBlank((String)map.get("COLOR2"))){
							 cwlx = (String)map.get("COLOR1")+"/"+(String)map.get("COLOR2")+"/"+(String)map.get("COLOR3");
						 }else{
							 cwlx = (String)map.get("COLOR1")+"/"+(String)map.get("COLOR3");
						 }
						 if(cwlx.endsWith("/")){
							 cwlx = cwlx.substring(0, cwlx.length()-1);
						 }else if(cwlx.startsWith("/")){
							 cwlx = cwlx.substring(1, cwlx.length());
						 }
						 this.insertTslDzjg(tslMap,map,"1",cwlx,date);
					 }
					 
				 }
				 
			 }
//			 request.setAttribute("amountErrorList", amountErrorList);
//			 request.setAttribute("amountErrorSize", amountErrorList.size());
			 
			 /**
			 * 处理  【只在电商系统中存在】 的数据
			 */
			 for(int i=0;i<bspet_size;i++){
				 Map<String, Object> map = bspetDataList.get(i);
				 if(map!=null&&!map.isEmpty()){
					 
					 String TKNO = (String) map.get("TKNO");
					 
					 
					 String type = (String) map.get("TYPE");
					 //提取tsl账单中票号相同的记录
					 JpTslBean tslMap=null;
					 
					 if("1".equals(type)){
						 //提取tsl账单中票号相同的记录----正常票
						  tslMap = tsl_tkno_map.get(TKNO);
					 }else if("2".equals(type)){
						//提取tsl账单中票号相同的记录----退票
						  tslMap = tp_tsl_tkno_map.get(TKNO);
					 }else{
						//提取tsl账单中票号相同的记录----废票
						  tslMap = fp_tsl_tkno_map.get(TKNO);
					 }
					 
					 if(tslMap != null){
						 continue; 
					 }
					 
					 //onlyAsmsList.add(map);
					 this.insertTslDzjg(null,map,"2","",date);
				 }
				 
			 }
			 //request.setAttribute("onlyAsmsList", onlyAsmsList);
			// request.setAttribute("onlyAsmsSize", onlyAsmsList.size());
			 
			 /**
			  * 处理  【只在TSL账单中存在】 的数据
			  */
			 for(int i=0;i<tsl_size;i++){
				 JpTslBean map = tslDataList.get(i);
				 if(map!=null){
					 map.setOffice(office);
					 map.setPrintno(printno);
					 map.setDate(date);
					 String tkno = map.getTicketno();
//					 if(tkno!=null){
//						 String[] tknStr=tkno.split("-");
//						 if(tknStr!=null&&tknStr.length==2){
//							 tkno=tknStr[1];
//						 }
//					 }
					 //提取asms系统bspet票中票号相同的记录
					 Map<String, Object> bspetMap=null;
					 String isVoid=map.getIsVoid();
					 String isRefund=map.getIsRefund();
					 
					 //正常票
					 if("0".equals(isVoid)&&"0".equals(isRefund)){
						 bspetMap=asms_tkno_map.get(tkno);
					 }
					 //退票
					 if("0".equals(isVoid)&&"1".equals(isRefund)){
						 bspetMap=tp_asms_tkno_map.get(tkno);
					 }
					 //废票
					 if("1".equals(isVoid)&&"0".equals(isRefund)){
						 bspetMap=fp_asms_tkno_map.get(tkno);
					 }
					 
					 if(bspetMap != null){
						 continue; 
					 }
//					 Map<String, JpTslBean> tslMap = new HashMap<String, JpTslBean>();
//					 tslMap.put("comparetsl", map);
//					 onlyTslList.add(tslMap);
					 if(!(map.getOrigdest().contains("REFUND"))){
						 this.insertTslDzjg(map,null,"3","",date);
					 }
				 }
				 
			 }
//			 request.setAttribute("onlyTslList", onlyTslList);
			// request.setAttribute("onlyTslSize", onlyTslList.size());
			 List<JpTslDzjg> dbzqtlsList = this.jpTslDzjgServiceImpl.getDzjgList(user.getShbh(), printno, office, date, "0");//查询对比正确
			 int dbtslzqSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, date, "0");
			 int dbtslcwSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, date, "1");
			 int dbxtSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, date, "2");
			 int dbtslSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, date, "3");
			 request.setAttribute("list",dbzqtlsList);
			 request.setAttribute("dbtslzqSize", dbtslzqSize);
			 request.setAttribute("dbtslcwSize", dbtslcwSize);
			 request.setAttribute("dbxtSize", dbxtSize);
			 request.setAttribute("dbtslSize", dbtslSize);
			return viewpath("tslCompareCg");
		}
	}
	
	private void insertTslDzjg(JpTslBean jpTslBean,Map<String, Object> map,String dbjg,String cwlx,String cprq) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		try {
			JpTslDzjg jpTslDzjg = new JpTslDzjg();
			jpTslDzjg.setId(VeDate.getNo(6));
			jpTslDzjg.setShbh(user.getShbh());
			if((jpTslBean !=null && map==null)){
				if("0".equals(jpTslBean.getIsVoid())&& "0".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("1");//正常票
				}else if("1".equals(jpTslBean.getIsVoid())&& "0".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("3");//废票
				}else if("0".equals(jpTslBean.getIsVoid())&& "1".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("2");//退票
				}
				jpTslDzjg.setTkno(jpTslBean.getTicketno());
				jpTslDzjg.setTslhc(jpTslBean.getOrigdest());
				jpTslDzjg.setTslzdj((int)NumberUtils.toDouble(jpTslBean.getCollection()));
				jpTslDzjg.setTsltax((int)NumberUtils.toDouble(jpTslBean.getTaxs()));
				double tsldlfl = Arith.div(NumberUtils.toDouble(jpTslBean.getComm()), 100);
				jpTslDzjg.setTsldlfl(tsldlfl);
				jpTslDzjg.setTslpnr(jpTslBean.getPnr());
				jpTslDzjg.setTslworkno(jpTslBean.getAgent());
				jpTslDzjg.setCpDate(jpTslBean.getDate());
				jpTslDzjg.setDbjg(dbjg);
				jpTslDzjg.setCwlx(cwlx);
				jpTslDzjg.setXtoffice(jpTslBean.getOffice());
				jpTslDzjg.setXtpringtno(jpTslBean.getPrintno());
			}else if(jpTslBean ==null && map!=null){
				if("1".equals(map.get("TYPE"))){
					jpTslDzjg.setPzlx("1");//正常票
				}else if("2".equals(map.get("TYPE"))){
					jpTslDzjg.setPzlx("2");//退票
				}else if("3".equals(map.get("TYPE"))){
					jpTslDzjg.setPzlx("3");//废票
				}
				jpTslDzjg.setTkno((String)map.get("TKNO"));
				jpTslDzjg.setXthc((String)map.get("HC"));
				jpTslDzjg.setXtzdj(((BigDecimal)map.get("ZDJ")).intValue());
				jpTslDzjg.setXttax(((BigDecimal)map.get("TAX")).intValue());
				double xtdlfl = Arith.div(((BigDecimal)map.get("HXFL")).doubleValue(), 100);
				jpTslDzjg.setXtdlfl(xtdlfl);
				jpTslDzjg.setXtpnr((String)map.get("CG_PNR_NO"));
				jpTslDzjg.setXtworkno((String)map.get("WORKNO"));
				jpTslDzjg.setXtpringtno((String)map.get("PRINTNO"));
				jpTslDzjg.setCpDate(cprq);
				jpTslDzjg.setDbjg(dbjg);
				jpTslDzjg.setCwlx(cwlx);
				jpTslDzjg.setXtoffice((String)map.get("CP_OFFICEID"));
			}else if((jpTslBean!=null && map!=null)){
				if("0".equals(jpTslBean.getIsVoid())&& "0".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("1");//正常票
				}else if("1".equals(jpTslBean.getIsVoid())&& "0".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("3");//废票
				}else if("0".equals(jpTslBean.getIsVoid())&& "1".equals(jpTslBean.getIsRefund())){
					jpTslDzjg.setPzlx("2");//退票
				}
				jpTslDzjg.setTkno(jpTslBean.getTicketno());
				jpTslDzjg.setTslhc(jpTslBean.getOrigdest());
				jpTslDzjg.setTslzdj((int)NumberUtils.toDouble(jpTslBean.getCollection()));
				jpTslDzjg.setTsltax((int)NumberUtils.toDouble(jpTslBean.getTaxs()));
				double tsldlfl = Arith.div(NumberUtils.toDouble(jpTslBean.getComm()), 100);
				jpTslDzjg.setTsldlfl(tsldlfl);
				jpTslDzjg.setTslpnr(jpTslBean.getPnr());
				jpTslDzjg.setTslworkno(jpTslBean.getAgent());
				jpTslDzjg.setCpDate(cprq);
				jpTslDzjg.setDbjg(dbjg);
				jpTslDzjg.setCwlx(cwlx);
				jpTslDzjg.setXthc((String)map.get("HC"));
				jpTslDzjg.setXtzdj(((BigDecimal)map.get("ZDJ")).intValue());
				jpTslDzjg.setXttax(((BigDecimal)map.get("TAX")).intValue());
				double xtdlfl = Arith.div(((BigDecimal)map.get("HXFL")).doubleValue(), 100);
				jpTslDzjg.setXtdlfl(xtdlfl);
				jpTslDzjg.setXtpnr((String)map.get("CG_PNR_NO"));
				jpTslDzjg.setXtworkno((String)map.get("WORKNO"));
				jpTslDzjg.setXtpringtno((String)map.get("PRINTNO"));
				jpTslDzjg.setXtoffice((String)map.get("CP_OFFICEID"));
			}
			this.jpTslDzjgServiceImpl.insert(jpTslDzjg);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据打印机号,office号,日期查询采购对账结果
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String cgdzResult(HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String office = request.getParameter("office");
		String printno = request.getParameter("printno");
		String tsldate = request.getParameter("tsldate");
		List<JpTslDzjg> list = this.jpTslDzjgServiceImpl.getTslDzjgList(user.getShbh(), printno, office, tsldate);
		if(CollectionUtils.isNotEmpty(list)){
			return "1";
		}else{
			return "0";
		}
	}
	/**
	 * 查询具体的对账结果(跟据条件查询对账正确,对账错误,只存在tsl中,只存在系统中4中数据)
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String cgdzDbResult(HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String office = request.getParameter("office");
		String printno = request.getParameter("printno");
		String tsldate = request.getParameter("tsldate");
		String dbjg = request.getParameter("dbjg");
		List<JpTslDzjg> list = this.jpTslDzjgServiceImpl.getDzjgList(user.getShbh(), printno, office, tsldate, dbjg);
		 int dbtslzqSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, tsldate, "0");
		 int dbtslcwSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, tsldate, "1");
		 int dbxtSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, tsldate, "2");
		 int dbtslSize = this.jpTslDzjgServiceImpl.getDzjgCount(user.getShbh(), printno, office, tsldate, "3");
		 request.setAttribute("dbtslzqSize", dbtslzqSize);
		 request.setAttribute("dbtslcwSize", dbtslcwSize);
		 request.setAttribute("dbxtSize", dbxtSize);
		 request.setAttribute("dbtslSize", dbtslSize);
		 request.setAttribute("list", list);
		if("0".equals(dbjg)){
			return viewpath("tslCompareCg");
		}else if("1".equals(dbjg)){
			if(CollectionUtils.isNotEmpty(list)){
				for(JpTslDzjg j : list){
					if(j.getCwlx().indexOf("0")>0){
						request.setAttribute("color1", "1");
					}else if(j.getCwlx().indexOf("1")>0){
						request.setAttribute("color2", "1");
					}else if(j.getCwlx().indexOf("2")>0){
						request.setAttribute("color3", "1");
					}
				}
			}
			return viewpath("tslCompareNcg");
		}else if("2".equals(dbjg)){
			return viewpath("existXt");
		}else if("3".equals(dbjg)){
			return viewpath("existTsl");
		}
		return "";
	}
	/**
	 * 拼接请求tsl指令的xml参数
	 * @param date
	 * @param office
	 * @param printno
	 * @param agent
	 * @return
	 */
	public String tslRequestXml(String date,String office,String printno,String agent,String userid){
		StringBuffer xmldate = new StringBuffer();
		xmldate.append("<INPUT>");
		xmldate.append(XmlUtils.xmlnode("COMMAND", "VETICKETMGR"));
		xmldate.append("<PARA>");
		xmldate.append(XmlUtils.xmlnode("USER", userid));
		xmldate.append(XmlUtils.xmlnode("CHILDCMD", FETCH_TICKETS_DATA));
		xmldate.append(XmlUtils.xmlnode("OFFICE", office));
		xmldate.append(XmlUtils.xmlnode("PRINTER", printno));
		xmldate.append(XmlUtils.xmlnode("DATE",VeDate.dateCommandTime(date)));
		if(StringUtils.isNotBlank(agent)){
			xmldate.append(XmlUtils.xmlnode("AGENT", agent));
		}
		xmldate.append(XmlUtils.xmlnode("BYAGENT", ""));
		xmldate.append("</PARA>");
		xmldate.append("</INPUT>");
		return xmldate.toString();
	}
	/**
	 * 获取缓存key
	 * @param userBh
	 * @param searchString
	 * @param lx
	 * @return
	 */
	private String getKey(String userBh,String searchString,String lx){
		return userBh+searchString+lx;
	}
	
	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 */
	private void putCache(String key,Object value){
		ehcacheManage.put("Cache", key, value, CACHE_TIME);
	}
	
	/**
	 * 从缓存中取数据
	 * @param key
	 * @return
	 */
	private Object getCache(String key){
		return ehcacheManage.get("Cache", key);
	}
	
	/**
	 * 清空缓存数据
	 * @param key
	 */
	private void removeCache(String key){
		ehcacheManage.remove("Cache", key);
	}
	
	String s = "<RESULT><STATUS>0</STATUS><DESCRIPTION><Result><PID>78135</PID><CONTENT></CONTENT>"
		+"<Tickets><Ticket><Index>1</Index><TicketNO>160-1791075187</TicketNO><Continual></Continual><ORIGDEST>LON LON</ORIGDEST>"
				+"<Collection>7610.00</Collection><TAXS>1157.00</TAXS><COMM>3.00</COMM><PNR>KT2FGD</PNR><AGENT>5358</AGENT></Ticket></Tickets><NOTIMPORTED></NOTIMPORTED></Result></DESCRIPTION></RESULT>";
public static void main(String[] args) {
	String s = "760.00";
	System.out.println((int)NumberUtils.toDouble(s));
}

}
