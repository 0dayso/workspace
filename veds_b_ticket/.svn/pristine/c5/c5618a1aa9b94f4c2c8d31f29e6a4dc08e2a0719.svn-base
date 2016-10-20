package cn.vetech.vedsb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.pid.api.Seat;
import org.vetech.core.business.pid.api.book.SpellBookCommand;
import org.vetech.core.business.pid.api.book.TicketCommandBook;
import org.vetech.core.business.pid.api.parse.ParseBookPnr;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.pidbean.CommandBean;
import org.vetech.core.business.pid.pidbean.PidBookResult;
import org.vetech.core.business.pid.util.ParsePnrUtil;

import cn.vetech.vedsb.jp.entity.jpddgl.JpDdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdCjrBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdHdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;

/**
 * 机票预订工具类
 * 
 * @author  gengxianyan
 * @version  [版本号, Apr 24, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class BookOrderUtil {
	
	/**
	 * 获取政策编号
	 * @param khddList 订单集合
	 * @return [参数说明]
	 */
	public String getZcid(List<Map> khddList){
		String zcid = "";
		for(Map map : khddList){
			if(StringUtils.isEmpty(zcid)) zcid = map.get("ZCID") == null ? "" : (String)map.get("ZCID");
		}
		return zcid;
	}
	
	/**
	 * 执行预订
	 * @param jpKhdd 订单Bean
	 * @param paramMap 其他参数Map
	 * @return 返回集合
	 * @throws Exception [参数说明]
	 */
	public Map<String,Object> excCommand(JpKhdd jpKhdd,
										 JpPz jpPz, 
										 String pidYh,
										 List<JpKhddHd> jpHdList, 
										 List<JpKhddCjr> jpCjrList) throws Exception{
		//如果K位则取供应商设置的K位URL和用户名
		String url = "";
		String userid = "";
		String office = "";
		String officeId = "";
		if(jpPz != null){
			officeId = jpPz.getOfficeid();
		} else {
			throw new Exception("无法获取Office号");
		}
		
		Map<String, String> bookParam = getBookParam(null ,pidYh,officeId, jpPz);
		if(MapUtils.isNotEmpty(bookParam)){
			url = bookParam.get("URL");
			userid = bookParam.get("USERID");
			office = bookParam.get("OFFICE");
		}
		
		if(StringUtils.isBlank(url) || StringUtils.isBlank(userid)){
			throw new Exception("供应商没有提供产品指定OFFICE号对应的配置，请联系平台！");
		}
		
		CommandBean commandBean = new CommandBean();
		pushValue(jpKhdd, commandBean, jpHdList, jpCjrList);
		
		commandBean.setDpyid(userid);//订票员编号
		
		Map<String, String> otherMap = new HashMap<String,String>();
		//otherMap.put("TDMC", bookForm.getTdmc());//9人自动成团团队名称
		//Map<String,String> map = khddList.get(0);
//		otherMap.put("SFXYH", jpKhdd.getSfxyh());//三方协议号
		otherMap.put("ISDPZUSERID", userid);//如果传入USERID同VE_YHB不同，则取这个Userid
		commandBean.setOtherMap(otherMap);
		
		Seat seat = null;
//		if ("1".equals(commandBean.getBookType())) {
//			seat = new IbeBookCommand();
//		}else{//Eterm
			seat = new SpellBookCommand(url,userid,office,"B");
//		}
		
		TicketCommandBook commandBook = new TicketCommandBook(commandBean,seat);
		commandBook.book();
		
		Map<String, Object> pnrMap = new HashMap<String, Object>();
		
		String pnrnr = commandBook.getPnrnr();
		if (StringUtils.isEmpty(pnrnr)) {
			throw new Exception("预订不成功！请检查网络和配置是否正常！");
		}else{
			ParseBookPnr bookPnr = new ParseBookPnr(pnrnr,commandBean.getBookType(),commandBean.getDpyid());
			if("0".equals(bookPnr.getFlag())){
				System.out.println("预订失败，预订返回Xml：" + bookPnr.getPnrlr());
				throw new Exception("预订失败，返回的错误提示："+bookPnr.getPnrlr());
			}
			
			JpDdBean jpDdBean = new JpDdBean();
			jpDdBean.setXgyh(userid);
			jpDdBean.setShbh(jpPz.getShbh());
			this.setDdBean(pnrMap, jpKhdd, jpDdBean, bookPnr, jpHdList, jpCjrList);
			pnrMap.put("PNRNO", bookPnr.getPnrno());
			pnrMap.put("BIGPNR", bookPnr.getBig_pnrno());
			pnrMap.put("PNRLR", bookPnr.getPnrlr());
			pnrMap.put("PNRZT", bookPnr.getPnrzt());
			pnrMap.put("OFFICE", bookPnr.getOffice());
			pnrMap.put("TIMEOFNOSEAT", bookPnr.getTimeofnoseat());
			
			dealChildCabin(jpKhdd, bookPnr.getResultBean());
			
			//如果9人以上自动成团，则生成团编，后加人.生成的订单为团订单（订单类型为3）
			addCjr(commandBean, bookPnr.getPnrno());
		}
		return pnrMap;
	}
	
	private void setDdBean(Map<String, Object> pnrMap, JpKhdd jpKhdd, JpDdBean jpDdBean, ParseBookPnr bookPnr, List<JpKhddHd> jpHdList, List<JpKhddCjr> jpCjrList){
		jpDdBean.setDdbh(jpKhdd.getDdbh());
		jpDdBean.setCg_pnr_no(bookPnr.getPnrno());
		jpDdBean.setCg_hkgs_pnr(bookPnr.getBig_pnrno());
		if(StringUtils.isNotBlank(jpKhdd.getNewPrice())){
			jpDdBean.setXgly("出票前降舱");
		} else {
			jpDdBean.setXgly("换PNR出票");
		}
		
		List<JpDdHdBean> hdList = new ArrayList<JpDdHdBean>();
		if(jpHdList != null){
			for(JpKhddHd jpKhddHd : jpHdList){
				JpDdHdBean jpDdHdBean = new JpDdHdBean();
				jpDdHdBean.setId(jpKhddHd.getId());
				if(StringUtils.isNotBlank(jpKhdd.getNewCw())){
					jpDdHdBean.setCg_cw(jpKhdd.getNewCw());
				}
				hdList.add(jpDdHdBean);
			}
		}
		
		List<JpDdCjrBean> cjrList = new ArrayList<JpDdCjrBean>();
		if(jpCjrList != null){
			for(JpKhddCjr jpKhddHd : jpCjrList){
				JpDdCjrBean jpDdCjrBean = new JpDdCjrBean();
				jpDdCjrBean.setId(jpKhddHd.getId());
				if(StringUtils.isNotBlank(jpKhdd.getNewPrice())){
					jpDdCjrBean.setCg_zdj(NumberUtils.toLong(jpKhdd.getNewPrice()));
				}
				cjrList.add(jpDdCjrBean);
			}
		}
		
		pnrMap.put("jpdd", jpDdBean);
		pnrMap.put("jpddhdlist", hdList);
		pnrMap.put("jpddcjrlist", cjrList);
	}
	
	/**
	 * 预订新编码
	 * @param commandBean 执行指令的bean
	 * @param url 接口地址
	 * @return 返回Map
	 * @throws Exception [参数说明]
	 */
	public Map<String,String> excCommand(CommandBean commandBean, String url) throws Exception{
	    
	    Seat seat = null;
//	    if ("1".equals(commandBean.getBookType())) {
//	        seat = new IbeBookCommand();
//	    }else{
	        seat = new SpellBookCommand(url,commandBean.getDpyid(),"","B");
//	    }
	    
	    TicketCommandBook commandBook = new TicketCommandBook(commandBean, seat);
	    commandBook.book();
	    
	    Map<String, String> pnrMap = new HashMap<String, String>();
	    
	    String pnrnr = commandBook.getPnrnr();
	    if (StringUtils.isEmpty(pnrnr)) {
	        
	        throw new Exception("预订不成功！请检查网络和配置是否正常！");
	        
	    }else{
	        
	        ParseBookPnr bookPnr = new ParseBookPnr(pnrnr,commandBean.getBookType(),commandBean.getDpyid());
	        
	        if("0".equals(bookPnr.getFlag())){
	            throw new Exception("预订失败，返回的错误提示："+bookPnr.getPnrlr());
	        }
	        
	        pnrMap.put("PNRNO", bookPnr.getPnrno());
	        pnrMap.put("BIGPNR", bookPnr.getBig_pnrno());
	        pnrMap.put("PNRLR", bookPnr.getPnrlr());
	        pnrMap.put("PNRZT", bookPnr.getPnrzt());
	        pnrMap.put("OFFICE", bookPnr.getOffice());
	        pnrMap.put("TIMEOFNOSEAT", bookPnr.getTimeofnoseat());
	    }
	    
	    return pnrMap;
	}
	
	/**
	 * 处理预订V舱，生成的编码为预订V1舱，需要修改航段表中子舱位字段值
	 * @param jpKhdd 订单Bean
	 * @param resultBean PNR内容
	 */
	private void dealChildCabin(JpKhdd jpKhdd,PidBookResult resultBean){
	    
	    if(null == resultBean || null == resultBean.getChildBean()) return;
	    
	    ParsePnrUtil util = new ParsePnrUtil();
	    
	    String hdxx = resultBean.getChildBean().getHdxx();
	    
	    List<List<String>> segmentList = util.convertToList(hdxx);
	    
	    List<JpKhddHd> hdList = null;//jpKhdd.getHdList();
	    
	    if(CollectionUtils.isNotEmpty(segmentList) && CollectionUtils.isNotEmpty(hdList)){
	        for(int i=0;i<segmentList.size();i++){
	            List<String> list = segmentList.get(i);
	            if(CollectionUtils.isNotEmpty(list) && list.size() >= 14 && hdList.size() > i){
	                String zcw = StringUtils.trimToEmpty(list.get(13));
	                if(StringUtils.isNotBlank(zcw)){
	                	JpKhddHd jp_dd_hd = hdList.get(i);
//	                    jp_dd_hd.setZcw(zcw);
	                }
	            }
	        }
	    }
	}
	
	/**
	 * 页面Form赋值到预订PNRBean中
	 * @param jpKhdd 订单Bean
	 * @param commandBean [参数说明]
	 */
	private void pushValue(JpKhdd jpKhdd, CommandBean commandBean,  List<JpKhddHd> jpHdList, List<JpKhddCjr> jpCjrList) {
		commandBean.setBookType("0");//默认Eterm
		int hdLen = jpHdList.size();
		
		String[] cfcitys = new String[hdLen];
		String[] ddcitys = new String[hdLen];
		String[] cfdates = new String[hdLen];
		String[] cfsjs = new String[hdLen];
		
		String[] hkgss = new String[hdLen];
		String[] hbhs = new String[hdLen];
		String[] cws = new String[hdLen];
		for(int i=0;i<hdLen;i++){
			JpKhddHd jpKhddHd = jpHdList.get(i);
			
			cfcitys[i] = jpKhddHd.getCfcity();
			ddcitys[i] = jpKhddHd.getDdcity();
			cfdates[i] = jpKhddHd.getCfsj();
			cfsjs[i]   = "";//VeDate.Date2Str(jpKhddHd.getCf_datetime(),"HH:mm");
			
			hkgss[i] = jpKhdd.getHkgs();
			hbhs[i] = jpKhddHd.getXsHbh();
			cws[i] = jpKhddHd.getXsCw();
			
		}
		
		commandBean.setCfcity(cfcitys);
		commandBean.setDdcity(ddcitys);
		commandBean.setCfdate(cfdates);
		commandBean.setCfsj(cfsjs);
		commandBean.setHkgs(hkgss);
		commandBean.setHbh(hbhs);
		commandBean.setCw(cws);
		
		int rs = 0;
		
	
		int cjrLen = jpCjrList.size();
		String[] cjrxms = new String[cjrLen];
		String[] cjrlxs = new String[cjrLen];
		String[] csrqs = new String[cjrLen];
		String[] etsjhms = new String[cjrLen];
		String[] sjhms = new String[cjrLen];
		String[] zjhms = new String[cjrLen];
		for(int i=0;i<cjrLen;i++){
			JpKhddCjr cjrBean = jpCjrList.get(i);
			if("1".equals(cjrBean.getCjrlx())){
				rs ++;
				sjhms[i] = cjrBean.getSjhm();
			}else if("2".equals(cjrBean.getCjrlx())){
				rs ++;
				etsjhms[i] = cjrBean.getSjhm();
			}else if("3".equals(cjrBean.getCjrlx())){
				sjhms[i] = cjrBean.getSjhm();
			}
			
			cjrlxs[i] = cjrBean.getCjrlx();
			cjrxms[i] = cjrBean.getCjr();
			csrqs[i] = cjrBean.getCsrq();//TODO VeDate.Date2Str(cjrBean.getCsrq(), "yyyy-MM-dd");;
			zjhms[i] = cjrBean.getZjhm();
		}
		commandBean.setRs(String.valueOf(rs));
		commandBean.setCjrlx(cjrlxs);
		commandBean.setCjrxm(cjrxms);
		commandBean.setCsrq(csrqs);
		commandBean.setEtsjhm(etsjhms);
		commandBean.setSjhm(sjhms);
		commandBean.setZjhm(zjhms);
		
		commandBean.setGngj(jpKhdd.getGngj());
		commandBean.setYl_timetype("");//使用方式二
		commandBean.setIfqk("1");
		
		// 是否预订当天
		if("2".equals("1")){//FIXME jpKhdd.getDdlx() //预订K位，需要更改行动代码
			List<String> ifnoseatList = new ArrayList<String>();
			for(int i=0;i<hdLen;i++){
				ifnoseatList.add("1");
			}
			commandBean.setIfnoseat(ifnoseatList.toArray(new String[0]));
		}else{
			commandBean.setIfnoseat(new String[hdLen]);
		}
		
		if(StringUtils.isNotBlank(jpKhdd.getXsPnrNo())){
			commandBean.setYpnrno(jpKhdd.getXsPnrNo());//提取成人PNR编码
		}
	}
	
	/**
	 * 预订9人以上自动成团，后续手动加人
	 * @param command 
	 * @param pnrno 
	 * @throws Exception [参数说明]
	 */
	public void addCjr(CommandBean command,String pnrno) throws Exception{
		/*if(null != command.getCjrxm() && command.getCjrxm().length <=9  || StringUtils.isEmpty(pnrno)) return ;
		
		String url = BookSearchUtil.getVepidUrl();
		String userid = BookSearchUtil.getCsValue("1002","");
		
		EditPnr editPnr = new EditPnr(pnrno);
		PidAvhBean avhBean = new PidAvhBean();
		avhBean.setUrl(url);
		avhBean.setUserid(userid);
		editPnr.setCommand(avhBean);
		
		for (int i = 0; i < command.getCjrxm().length; i++) {
			String hbh = command.getHbh()[0];
			String hkgs = command.getHkgs()[0];
			String cfdate = command.getCfdate()[0];
			if ("2".equals(command.getCjrlx()[i])) {
				editPnr.addChild(command.getCjrxm()[i]);
				if (StringUtils.isNotBlank(command.getZjhm()[i])) {
					editPnr.addSSR_FOID(hkgs, "NI", command.getZjhm()[i], "P" + (i+1));
				}
			} else if ("3".equals(command.getCjrlx()[i])) {
				String yedate = BookUtil.dateCommandYear(command.getZjhm()[i]);
				String csrq = BookUtil.dateCommandTime(command.getZjhm()[i]);
				String yecfdate = BookUtil.dateCommandDay(cfdate);
				editPnr.addInfant(yedate, "P" + (i+1), command.getCjrxm()[i]);
				String yexm = "";
				if (VeStr.isChinese(command.getCjrxm()[i])) {
					yexm = BookUtil.mhPingying(command.getCjrxm()[i]);
				} else {
					yexm = command.getCjrxm()[i];
				}
				editPnr.addInfoAirSeg(hkgs, command.getCfcity()[0],command.getDdcity()[0], StringUtils.replace(hbh, "*", ""),
						command.getCw()[0], yecfdate, yexm, csrq, "P" + (i+1));
			} else {
				editPnr.addAdult(command.getCjrxm()[i]);
				if (StringUtils.isNotBlank(command.getZjhm()[i])) {
					editPnr.addSSR_FOID(hkgs, "NI", command.getZjhm()[i], "P" + (i+1));
				}
			}
		}
		
		try {
			CommandResult commandResult = editPnr.excute();
			if (commandResult != null && !"1".equals(StringUtils.trim(commandResult.getInitData()))) {
				throw new PidException("团编自动加人异常：" + commandResult.getInitData());
			}
		} catch (PidException e) {
			e.printStackTrace();
			throw e;
		}*/
	}
	
	/**
	 * 获取预订接口所需参数
	 * @param ddlx 订单类型
	 * @param gy_shbh 供应商户编号
	 * @param gy_yhbh 供应用户编号
	 * @param office OFFICE号
	 * @return [参数说明]
	 */
	public static Map<String, String> getBookParam(String ddlx,String pidYh,String office,JpPz jppz){
		if(StringUtils.isBlank(ddlx)){
			ddlx = "1";
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		if("1".equals(ddlx)){//普通 取平台设置的配置
			String url = jppz.getPzIp()+":"+jppz.getPzPort();
//			String userid = jppz.getPzyhm();//TODO BookSearchUtil.getCsValue("1002","");
			paramMap.put("URL", url);
			paramMap.put("USERID", pidYh);
			paramMap.put("OFFICE", office);
		}else if("2".equals(ddlx)){//K位 取供应商配置管理里面第一个有效的配置
//			paramMap.putAll(getJkUrlAndUserid(gy_shbh,gy_yhbh,null));
		}else if("3".equals(ddlx)){//免票 取供应商配置管理里面第一个有效的配置
//			paramMap.putAll(getJkUrlAndUserid(gy_shbh,gy_yhbh,null));
		}else if("4".equals(ddlx)){//其他 如果生成PNR，则取产品中的OFFICE号对应的配置
//			paramMap.putAll(getJkUrlAndUserid(gy_shbh,gy_yhbh,office));
		}else{//特价/NFD特价 取产品中的OFFICE号对应的配置
//			paramMap.putAll(getJkUrlAndUserid(gy_shbh,gy_yhbh,office));
		}
		
		return paramMap;
	}
	
	/**
	 * 根据订单信息提取预订接口所需配置信息
	 * @param gy_shbh 商户编号
	 * @param gy_yhbh 供应用户编号
	 * @param office OFFICE号
	 * @return [参数说明]
	 */
//	public static Map<String, String> getJkUrlAndUserid(String gy_shbh,String gy_yhbh,String office){
//		Sh_shpzbService sh_shpzbService = (Sh_shpzbService)SpringContextUtil.getBean("sh_shpzbService");
//		
//		Map<String, String> paramMap = new HashMap<String, String>();
//		
//		Sh_shpzb sh_shpzb = new Sh_shpzb();
//		sh_shpzb.setZt("0");//已启用
//		sh_shpzb.setSftbpid("1");//已同步PID
//		if(StringUtils.isNotBlank(office)){
//			sh_shpzb.setOfficeid(office);
//		}
//		sh_shpzb.setShzh(gy_shbh);
//		List<Sh_shpzb> shpzbList = sh_shpzbService.getSh_shpzb(sh_shpzb);
//		if(CollectionUtils.isNotEmpty(shpzbList)){//客户提供配置，则取客户提供的配置
//			Sh_shpzb bean = shpzbList.get(0);
//			
//			String url = "http://" +bean.getPz_ip() + ":" + bean.getPz_port();
//			
//			if(StringUtils.isBlank(bean.getPz_ip()) || StringUtils.isBlank(bean.getPz_port()) || StringUtils.isBlank(url)){//如果供应商配置没有设置白屏接口URL，则取参数设置的URL
//				url = BookSearchUtil.getVepidUrl();
//			}
//			
//			paramMap.put("URL", url);
//			paramMap.put("USERID", gy_yhbh);
//		}else{//供应商没有提供配置，则取供应商设置的默认配置信息
//			String _url = BookSearchUtil.getAutoK_PidUrl(gy_shbh);
//			String _userid="";
//			Map<String,String> map = BookSearchUtil.getAutoK_AccountOffice(gy_shbh);
//			if(MapUtils.isNotEmpty(map)){
//				_userid = map.get("ACCOUNT");
//				//_office  = map.get("OFFICE");
//			}
//			
//			paramMap.put("URL", _url);
//			paramMap.put("USERID", _userid);
//			//paramMap.put("OFFICE", _office);
//		}
//		return paramMap;
//	}
	
	/**
	 * test
	 * @param args [参数说明]
	 */
	public static void main(String[] args) {}
}
