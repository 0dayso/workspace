package cn.vetech.vedsb.platpolicy.taobao;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.BairwaycwCache;
import org.vetech.core.business.cache.BairwaycwCacheService;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.modules.cache.RedisManage;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.cpsz.JpCpymSz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpCpymSzServiceImpl;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.platpolicy.GetPolicyUtil;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.CreateOrderRequest;
import cn.vetech.vedsb.platpolicy.cps.response.pay.GetOrderPayLinkResponse;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.CreateOrderResponse;
import cn.vetech.vedsb.platpolicy.cps.service.CpsInvoke;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
import cn.vetech.vedsb.platpolicy.cpslink.PlatOrderParam;
import cn.vetech.vedsb.platpolicy.taobao.model.CpsLinkTbcb;
import cn.vetech.vedsb.platpolicy.taobao.request.CpsLinkPolicyRequest;
import cn.vetech.vedsb.platpolicy.taobao.response.CpsLinkPolicyResponse;
import cn.vetech.vedsb.platpolicy.taobao.response.TBSubmitOrderRes;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripFlightinfoGetRequest;
import com.taobao.api.request.AlitripOrderinfoAirbookRequest;
import com.taobao.api.response.AlitripFlightinfoGetResponse;
import com.taobao.api.response.AlitripOrderinfoAirbookResponse;

@Service
public class TaobaobuyerService {
	private static Logger logger = LoggerFactory.getLogger(TaobaobuyerService.class);
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired 
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private JpCpymSzServiceImpl JpCpymSzServiceImpl;
	@Autowired
	private VeclassCacheService veclassCacheService;
	@Autowired
	private BairwaycwCacheService bairwaycwCacheService;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private CpsService cpsService;
	@Autowired
	private CpsInvoke cpsInvoke;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	@Autowired
	private RedisManage redisManage;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	public static final String CACHNAME = "TBZC:";
	
	public List<PolicyItem> searchpolicy(String ddbh,Shyhb yhb) throws Exception {
		Shcsb shcsb = shcsbServiceImpl.findCs("2022", yhb.getShbh());
		String cs2022 = "0";//默认0
		if(shcsb!=null){
			cs2022 = StringUtils.trimToEmpty(shcsb.getCsz1());
			cs2022 = StringUtils.isBlank(cs2022) ? "0" : cs2022;
		}
		List<PolicyItem> list = null;
		if("0".equals(cs2022)){
			list = searchpolicy_1(ddbh, yhb);
		}else{
			list = searchpolicy_2(ddbh, yhb);
		}
		for(int i=0;i<list.size();i++){
			PolicyItem policyItem = list.get(i);
			redisManage.put(CACHNAME+ddbh+":", policyItem.getId(), policyItem, 20*60);
		}
		return list;
	}
	public TBSubmitOrderRes submitorder(PlatOrderParam orderParam) throws Exception {
		Shcsb shcsb = shcsbServiceImpl.findCs("2022", orderParam.getYhb().getShbh());
		String cs2022 = "0";//默认0
		if(shcsb!=null){
			cs2022 = StringUtils.trimToEmpty(shcsb.getCsz1());
			cs2022 = StringUtils.isBlank(cs2022) ? "0" : cs2022;
		}
		TBSubmitOrderRes res = null;
		if("0".equals(cs2022)){
			res = submitorder_1(orderParam);
		}else{
			res = submitorder_2(orderParam);
		}
		if(DsResponse.SUCCESS.equals(res.getStatus())){
			redisManage.remove(CACHNAME+orderParam.getDdbh()+":","*");
		}
		return res;
	}
	/***************************本地淘宝代购START*********************************/
	public List<PolicyItem> searchpolicy_1(String ddbh,Shyhb yhb) throws Exception {
		List<PolicyItem> policyList = new ArrayList<PolicyItem>();
		// 航班号 舱位 价格
		long price = 0;
		JpPtLog ptLog=new JpPtLog();
		boolean bl = false;//是否记录平台日志
		try {
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("订单编号不能为空");
			}
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			bean.setShbh(yhb.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, yhb.getShbh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, yhb.getShbh());
			if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
				throw new Exception("订单信息不全");
			}
			String flightno = jpKhdd.getCgHbh();
			String cabin = jpKhdd.getCgCw();
			boolean hasChdOrInf = false;
			for(int i=0;i<cjrList.size();i++){
				JpKhddCjr onecjr = cjrList.get(i);
				if(!"1".equals(onecjr.getCjrlx())){
					hasChdOrInf = true;
					break;
				}
			}
			if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
				throw new Exception("淘宝代购只支持成人单程");
			}
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, yhb.getShbh());
			if(ptzh==null){
				throw new Exception("请先配置淘宝采购信息");
			}
			bl = true;
			ptLog.setShbh(yhb.getShbh());
			ptLog.setYhbh(yhb.getBh());
			ptLog.setDdbh(ddbh);
			ptLog.setPtzcbs(ptzh.getPtzcbs());//不能为空
			ptLog.setPnrNo(jpKhdd.getCgPnrNo());
			ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptLog.setYwlx(DictEnum.PTLOGYWLX.CZC.getValue());
			ptLog.setBy1("1001901");
			ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
			JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(yhb.getShbh(), "TB");
			if(jpCpymSz==null){
				jpCpymSz = new JpCpymSz();
			}
	        String bookTravelerInfo ="";     
	        bookTravelerInfo = bookTravelerInfo +"[";
	        for(int i=0;i<cjrList.size();i++) {
	        	JpKhddCjr cjr = cjrList.get(i);
				String lklx = cjr.getCjrlx();
				if(!"1".equals(lklx)){
					throw new Exception("非成人订单不询价");
				}
	            String cjrlx = "ADT";
	            if("1".equals(lklx)) {
	                cjrlx="ADT";
	            }else if("2".equals(lklx)) {
	                cjrlx="CHD";
	            }
				String zjhm = StringUtils.trimToEmpty(cjr.getZjhm());
				
				String zjlx = cjr.getZjlx();
	            String zjlxT = zjlx;
	            if("NI".equals(zjlx)){
	            	zjlxT = "NI";
	            }else if("P".equals(zjlx)||"PP".equals(zjlx)){
	                zjlxT = "PP";
	            }else{//淘宝下单其他是TH
	            	zjlxT = "TH";
	            }
	            String brithday = "";
	            if("NI".equals(zjlxT)){
	            	brithday = VeStr.getBirthDateFromCard(zjhm);
	            }
	            if(StringUtils.isNotBlank(brithday)){
	            	cjr.setCsrq(brithday);
	            }
	            if(i==0) {
	                bookTravelerInfo = bookTravelerInfo+"{";
	            }else {
	                bookTravelerInfo = bookTravelerInfo+",{";
	            }
	            bookTravelerInfo = bookTravelerInfo +"\"passenger_name\":\""+cjr.getCjr()+"\"," +
	            "\"passenger_type\":\""+cjrlx+"\"," +
	            "\"doc_id\":\""+cjr.getZjhm()+"\"," +
	            "\"doc_type\":\""+zjlxT+"\"," +
	            "\"birth_date\":\""+brithday+"\"}";
			}
			bookTravelerInfo = bookTravelerInfo +"]";
			
			JpKhddHd hd = hdList.get(0);
			String dep_airport = hd.getCfcity();
			String arr_airport = hd.getDdcity();
			String cfsj = hd.getCfsj();
			String dep_date = StringUtils.substring(cfsj, 0, 10);
			flightno = flightno.replaceAll("\\*","");
			String airline_code = flightno.substring(0, 2);
			String tbxj = StringUtils.trimToEmpty(jpCpymSz.getTbXj());//参数控制哪些航司带舱位询价
			String cw = getCw(cabin,jpKhdd.getHkgs(), hd.getCgZcw(), tbxj);
			LogUtil.getDgrz("TB",ddbh).error("根据订单编号["+ddbh+"],参数tbxj["+tbxj+"]判断是否要带舱位询价：" + cw);
			//获取相关信息
			String url = ptzh.getUrl1();
			String appkey = ptzh.getUser1();
			String secret = ptzh.getPwd1();
			String sessionKey = ptzh.getUrl2();
			
			String channelName = "胜意";
			String password = MD5Tool.MD5Encode("123456");
			TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret,"xml");
			AlitripFlightinfoGetRequest req=new AlitripFlightinfoGetRequest();
			req.setAirlineCode(airline_code);
			req.setCabin(cw);
			req.setChannelName(channelName);
			req.setPassword(password);
			req.setPrice(price);
			req.setFlightNo(flightno);
			req.setDepAirport(dep_airport);
			req.setArrAirport(arr_airport);
			req.setDepDate(dep_date);
			req.setAlitripFlag("1");
			req.setBookTravelerList(bookTravelerInfo);
			String reservationCode=ddbh.replace("JP", "98");//把我們系統订单编号前缀TK改成99,
			String IgnoredShopNames = StringUtils.trimToEmpty(jpCpymSz.getTbGlDp());//淘宝过滤店铺名称
			req.setIgnoredShopNames(IgnoredShopNames);
			req.setReservationCode(reservationCode);
	        //控制是否走淘宝原编码出票模式,此模式下淘宝询价多出一个参数has_pnr（如该参数为true，则搜索产品时不再做AV过滤，即：没有余票的产品也将返回）。
			String tbcpfs = StringUtils.trimToEmpty(jpCpymSz.getTbCpfs());
			LogUtil.getDgrz("TB",ddbh).error("判断是否走原编码流程参数tbcpfs["+tbcpfs+"]" );
		    String changerecord = "1";//默认为换编码政策
			if("1".equals(tbcpfs)){//0全部走换编码流程；1全部走原编码流程；2先走还编码流程如果下单返回余票不足时，用原编码下单。
		       req.setHasPnr(true);
		       changerecord = "0";
		    }else{
		       req.setHasPnr(false);
		       changerecord = "1";
		    }
			
			AlitripFlightinfoGetResponse res = client.execute(req , sessionKey);
			Map<String,String> map = res.getParams();
			if(map!=null) {
				String loginfo = "";
				for(String key : map.keySet()) {
					loginfo += key+"-->"+map.get(key)+"&";
				}
				LogUtil.getDgrz("TB",ddbh).error("查询淘宝政策请求参数：" + loginfo);
				ptLog.add("查询政策请求参数:"+loginfo);
			}
			LogUtil.getDgrz("TB",ddbh).error("查询淘宝政策返回：" + res.getBody());
			ptLog.add("查询政策返回:"+res.getBody());
			//解析返回
			ParseXml pXml = new ParseXml(res.getBody());
			Element root = pXml.getRoot();
			Element lowest_product_list = root.element("lowest_product_list");
            String requestId = root.elementTextTrim("request_id");
            String airways = root.elementTextTrim("airline");
            String depDate = root.elementTextTrim("dep_date");
            String arrTime = root.elementTextTrim("arr_time");
            String arrDate = StringUtils.trimToEmpty(StringUtils.substringBefore(arrTime, " ")); 
            int count = 0;
            String glcp = StringUtils.trimToEmpty(jpCpymSz.getCgGlCp());//控制过滤不显示产品的参数
            List<Element> lowest_products = lowest_product_list.elements("lowest_product");
            for(Element e : lowest_products) {
            	String zws_str = e.elementTextTrim("amount");
            	double zws = NumberUtils.toDouble(zws_str, 10);
                //QW：全网最低价产品 JX：精选产品 JP：金牌产品 HS：航司产品
                String productType = StringUtils.trimToEmpty(e.elementTextTrim("product_type"));
                if(glcp.indexOf(productType)>-1){
                	continue;
                }
                PolicyItem policyItem = new PolicyItem();
                policyItem.setPtzcbs(ptzh.getPtzcbs());
                policyItem.setId("TAOBAO" + "@@" + productType + "@@" + requestId + "@" + count);
                policyItem.setZclx("1");
                policyItem.setPtmc("淘宝代购");
                policyItem.setChangerecord(changerecord);//默认换编码出票
                String tgqStr = e.elementTextTrim("tuigaiqian");
                double billPrice = jpKhdd.getCgZdj();
                double buyPrice = NumberUtils.toDouble(e.elementTextTrim("cabin_price"));
                double superProfit = Arith.sub(billPrice, buyPrice);//单人代理费
                double rate = Arith.div(Arith.mul(superProfit, 100), billPrice,2); //这里存的是百分数
                double tax = NumberUtils.toDouble(e.elementTextTrim("fee"));//燃油
                double jsf = NumberUtils.toDouble(e.elementTextTrim("tax"));//建设
                double payfee = Arith.mul(Arith.sum(buyPrice,tax,jsf),cjrList.size());
                policyItem.setXsj(buyPrice);//存销售价  单人
                policyItem.setPj_cgj(billPrice);//存采购账单价  单人
                policyItem.setPayfee(payfee);
                policyItem.setRate(rate);
                policyItem.setRateshow(rate);
                policyItem.setSuperProfit(superProfit);
                policyItem.setZcpj(String.valueOf(billPrice));
                policyItem.setTgqgd(tgqStr);
                policyItem.setLq(0.0);//把代理费全部放到留钱字段上面，防止精度缺失
                policyItem.setWorktime("截止2359");//默认值全天适用
                policyItem.setChooseOutWorkTime("截止2359");//默认值全天适用
                policyItem.setAircome(airways);
                policyItem.setCabin(e.elementTextTrim("cabin"));
                policyItem.setSdate(depDate);//出发日期
                policyItem.setEdate(arrDate);//到达日期

                policyItem.setOffice("");
                
                //取订单上面的机建税费【多人】
                policyItem.setTax(tax);
                policyItem.setJsf(jsf);
                policyItem.setIsspecmark("0");// 0 普通政策 1特殊政策
                String note = "淘宝C站产品类型：";
                
    		 	//获取数据字典中类别为7500的数据
    		 	List<VeclassCache> listclass = veclassCacheService.getLb("7500");
    		 	Map<String, String> map_class = new HashMap<String, String>();
    		 	if(listclass!=null) {
    		 		for(VeclassCache c : listclass){
    		 			map_class.put(c.getYwmc(), c.getMc());
    		 		}
    		 	}
    		 	note = StringUtils.isBlank(map_class.get(productType)) ? note : map_class.get(productType);
                policyItem.setNote(note);
                policyItem.setPolicytype(note);//
                policyList.add(policyItem);
                count ++ ;
            }
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.getDgrz("TB",ddbh).error("查询淘宝政策报错。",e);
			ptLog.add("查询淘宝政策报错。"+e.getMessage());
		} finally{
			try {
				if(bl){
					jpPtLogServiceImpl.insertLog(ptLog);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return policyList;
	}
    
    public TBSubmitOrderRes submitorder_1(PlatOrderParam orderParam) throws Exception{
    	TBSubmitOrderRes tbres = new TBSubmitOrderRes();
		Shyhb yhb=orderParam.getYhb();
		String ddbh=orderParam.getDdbh();
		JpPtLog ptLog=new JpPtLog();
		String shbh=yhb.getShbh();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(yhb.getBh());
		ptLog.setDdbh(ddbh);

        String policyId = orderParam.getPolicyId();//之前规定规则拼装的政策ID，格式："TAOBAO" + "@@" + productType + "@@" + requestId + "@" + count
        String productType = StringUtils.substringBeforeLast(policyId, "@@");
        productType = StringUtils.substringAfterLast(productType, "@@");
		JpKhdd bean = new JpKhdd();
		bean.setDdbh(ddbh);
		bean.setShbh(yhb.getShbh());
		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
		List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
		List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
		if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
			throw new Exception("订单信息不全");
		}
		boolean hasChdOrInf = false;
		for(int i=0;i<cjrList.size();i++){
			JpKhddCjr onecjr = cjrList.get(i);
			if(!"1".equals(onecjr.getCjrlx())){
				hasChdOrInf = true;
				break;
			}
		}
		if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
			throw new Exception("淘宝代购只支持成人单程");
		}
		JpKhddHd khddHd = hdList.get(0);
		//取JpPtzcZh表设置
		JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, shbh);
		if(ptzh==null){
			throw new Exception("请先配置淘宝采购信息");
		}
		ptLog.setPtzcbs(orderParam.getPlatcode());//不能为空
		ptLog.setPnrNo(jpKhdd.getCgPnrNo());
		ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptLog.setYwlx(DictEnum.PTLOGYWLX.XD.getValue());
		ptLog.setBy1("1001901");
		ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(shbh, "TB");
		if(jpCpymSz==null){
			jpCpymSz = new JpCpymSz();
		}
        String pnrno = jpKhdd.getCgPnrNo();
		int salePrice = 0; //采购价
		int ticketPrice = 0; //票面
		String flight_no = "";
		String cw = "";
		String msg = "订单编号【"+ddbh+"】PNR【"+pnrno+"】";
        try {
            //获取相关信息
            String url = ptzh.getUrl1();
            String appkey = ptzh.getUser1();
            String secret = ptzh.getPwd1();
            String sessionKey = ptzh.getUrl2();
            /*url = "http://112.124.54.173:30001/airs/conver.shtml";
            appkey = "21549144";
            secret = "97582f944eeee2a12322360cd9a81a39";
            sessionKey = "61005023e61a6953833632249a2a3351835569b58a7ad122592421237";*///华正的正式账号
            
            String channelName = "胜意";
            String password = MD5Tool.MD5Encode("123456");
            
            String reservationCode=ddbh.replace("JP", "98");//把我們系統订单编号前缀TK改成99,
            int oneMoney = (int)NumberUtils.toDouble(orderParam.getJsj(), 0);//这里取了销售价
            ticketPrice = (int)Arith.div(jpKhdd.getCgZdj(),cjrList.size());//这里取采购账单价
            int fee = (int)Arith.div(jpKhdd.getCgJsf(),cjrList.size());//机建
            int tax = (int)Arith.div(jpKhdd.getCgTax(),cjrList.size());//燃油税
            salePrice = (int)Arith.sum(oneMoney, -fee,-tax);
            
            String extra="";
            int totalMoney = oneMoney*cjrList.size();
            TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret,"xml",30*1000,2*60*1000);
            AlitripOrderinfoAirbookRequest req=new AlitripOrderinfoAirbookRequest();
            
            req.setChannelName(channelName);
            req.setPassword(password);
            req.setReservationCode(reservationCode);
            req.setTotalMoney(totalMoney+"");
            req.setFee(fee+"");
            req.setTax(tax+"");
            req.setExtra(extra);
            
            req.setProductType(productType);//选择的产品类型
//          req.setMustUseActivity(false);
            String IgnoredShopNames = StringUtils.trimToEmpty(jpCpymSz.getTbGlDp());//淘宝过滤店铺名称
            req.setIgnoredShopNames(IgnoredShopNames);

            String dep_airport = khddHd.getCfcity();
            String arr_airport = khddHd.getDdcity();
            String dep_date = khddHd.getCfsj().substring(0,10);
            String airline_code = jpKhdd.getHkgs();
            flight_no = jpKhdd.getCgHbh();
            cw = jpKhdd.getCgCw();
            
            String dep_time = khddHd.getCfsj();//2014-12-25 16:10:00
            String arr_time = khddHd.getDdsj();
            
            if(StringUtils.isNotBlank(dep_time)) {
                dep_time = dep_time.substring(11,16);
            }
            if(StringUtils.isNotBlank(arr_time)) {
                arr_time = arr_time.substring(11,16);
            }
            
            String bookFlightSegment = "{\"dep_airport\":\""+dep_airport+"\"," +
                                        "\"arr_airport\":\""+arr_airport+"\"," +
                                        "\"dep_date\":\""+dep_date+"\"," +
                                        "\"airline_code\":\""+airline_code+"\"," +
                                        "\"flight_no\":\""+flight_no+"\"," +
                                        "\"dep_time\":\""+dep_time+"\"," +
                                        "\"arr_time\":\""+arr_time+"\"," +
                                        "\"cabin\":\""+cw+"\"," +
                                        "\"leg\":\"0\",\"sale_price\":\""+salePrice+"\"}";
            msg+="航班号【"+flight_no+"】";
            String bookTravelerInfo =""; 
            String bookArrangerInfo ="";
            List<String> listPassengerDocId = new ArrayList<String>();
            bookTravelerInfo = bookTravelerInfo +"[";
//          int tmpint = cjrrs>xz ? xz : cjrrs;
            String cjrnames = "";
            for (int i = 0; i < cjrList.size(); i++) {
                JpKhddCjr cjr = cjrList.get(i);
                String cjrlx = "ADT";
                if("1".equals(cjr.getCjrlx())) {
                    cjrlx="ADT";
                }else if("2".equals(cjr.getCjrlx())) {
                    cjrlx="CHD";
                }
                if(i==0) {
                    bookTravelerInfo = bookTravelerInfo+"{";
                }else {
                    bookTravelerInfo = bookTravelerInfo+",{";
                }
                String zjlx = StringUtils.defaultIfBlank(cjr.getZjlx(), "NI");
                String zjlxT = zjlx;
                if("NI".equals(zjlx)){
                	zjlxT = "NI";
                }else if("P".equals(zjlx)||"PP".equals(zjlx)){
                    zjlxT = "PP";
                }else{//淘宝下单其他是TH
                	zjlxT = "TH";
                }
                String brithday = cjr.getCsrq();
                String brithday_new = "";
                if("NI".equals(zjlxT)){
                	brithday_new = VeStr.getBirthDateFromCard(cjr.getZjhm());
                }
                if(StringUtils.isNotBlank(brithday_new)){
                	brithday = brithday_new;
                }
                if(StringUtils.isBlank(brithday)){
                	throw new Exception("取出生日期失败，不允许下单到淘宝");
                }
                listPassengerDocId.add(cjr.getZjhm());
                bookTravelerInfo = bookTravelerInfo +"\"passenger_name\":\""+cjr.getCjr()+"\"," +
                                                    "\"passenger_type\":\""+cjrlx+"\"," +
                                                    "\"doc_id\":\""+cjr.getZjhm()+"\"," +
                                                    "\"doc_type\":\""+zjlxT+"\"," +
                                                    "\"birth_date\":\""+brithday+"\"}";
                cjrnames+=","+cjr.getCjr();
            }
            msg+="乘机人【"+cjrnames.substring(1)+"】";
            bookTravelerInfo = bookTravelerInfo +"]";
            //
            String tbnxr = StringUtils.trimToEmpty(jpCpymSz.getTbNxr());//联系人信息
    		String linker = "";
    		String linkMobile = "";
    		String linkerEmail = "";
    		String reg = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";       		
    		try {
    			ptLog.add("联系人信息参数【"+tbnxr+"】");
				String[] strArr = tbnxr.split("\\|\\|");
				linker = strArr[0];
				linkMobile = strArr[1];
				linkerEmail = strArr[2];
    		} catch (Exception e) {
    			//如果报错,不能影响
    			ptLog.add("根据联系人参数信息失败！");
    			linker = "";
    			linkMobile = "";
    			linkerEmail = "";
    		}
    		if(StringUtils.isBlank(linker)){
    			ptLog.add("通过订单取联系人信息……");
    			linker=jpKhdd.getNxr();
    			linkMobile = jpKhdd.getNxsj();
    			linkerEmail = "";
    		}
    		if(StringUtils.isBlank(linkMobile)){
    			throw new Exception("联系人手机号不能为空，不能淘宝代购！");
    		}
    		if(!Pattern.matches(reg, linkMobile)){
    			throw new Exception("联系人手机号格式不正确，不能淘宝代购！");
    		}
            bookArrangerInfo = bookArrangerInfo +"{\"contact_name\":\""+linker+"\"," +
                                                "\"contact_phone\":\""+linkMobile+"\"," +
                                                "\"contact_email\":\""+linkerEmail+"\"}";
            
            req.setBookArrangerInfo(bookArrangerInfo);
            req.setBookFlightSegmentList(bookFlightSegment);
            req.setBookTravelerList(bookTravelerInfo);
            String pnr_zt = jpKhdd.getCgPnrZt();
	        if("RR".equals(pnr_zt)){
	        	throw new Exception("该订单的PNR状态为RR，为了避免重复出票，不能淘宝代购！");
	        }
            
	        //控制是否走淘宝原编码出票模式,此模式下淘宝下单接口多三个参数：。
			String tbcpfs =  StringUtils.trimToEmpty(jpCpymSz.getTbCpfs());
	        if("1".equals(tbcpfs)){//原编号出票要授权
	        	
	        }
	        String ifxepnr = "1";
	        JpPtzcFzsz jpPtzcFzsz = jpPtzcFzszServiceImpl.genZfkmByDjm("2", jpKhdd.getShbh(), "10063971", PlatCode.TB);//10063971是支付宝对接码
	        if(jpPtzcFzsz==null){
	        	throw new Exception("请先在[平台支付方式对应]中设置淘宝[支付宝]的支付科目！");
	        }
	        boolean bl = false;
	        int i=0;
	        while(i==0||bl){//当cs104008_1为2，并且换编码下单失败返回"余票不足"是
	        	i++;//第i次请求
	        	ifxepnr = "1".equals(tbcpfs) || ("2".equals(tbcpfs) &&i==2) ? "1" : "0";
				AlitripOrderinfoAirbookResponse res = client.execute(req, sessionKey);
//	        	AlitripOrderinfoAirbookResponse res = new AlitripOrderinfoAirbookResponse();
//	        	res.setOrderid("245218844220");
				Map<String,String> map = res.getParams();
				String loginfo = "";
				if(map!=null) {
					for(String key : map.keySet()) {
						loginfo += key+"-->"+map.get(key)+"&";
					}
					LogUtil.getDgrz("TB",ddbh).error("第"+i+"次请求参数："+loginfo);
					ptLog.add("第"+i+"次请求参数："+loginfo);
				}
				LogUtil.getDgrz("TB",ddbh).error("第"+i+"次请求淘宝下单接口返回：" + res.getBody());
				ptLog.add("第"+i+"次请求淘宝下单接口返回：" + res.getBody());
				if (StringUtils.isNotBlank(res.getOrderid())) {
					int tbTotalMoney = (int)NumberUtils.toDouble(res.getTotalMoney(),0);
					if(tbTotalMoney!=0){
						int cj = (int)Arith.sub(totalMoney, tbTotalMoney);
						totalMoney = tbTotalMoney;
						salePrice = (int)Arith.sub(salePrice, Arith.div(cj, cjrList.size()));
					}
					bl=false;//下单成功，不继续请求
//					rtnmsg ="订单创建成功单号是:" +res.getOrderid()+"支付金额是：" + totalMoney;
					LogUtil.getDgrz("TB",ddbh).error("订单创建成功单号是:" +res.getOrderid()+"支付金额是：" + totalMoney);
					ptLog.add("订单创建成功单号是:" +res.getOrderid()+"支付金额是：" + totalMoney);
					String isQzd = StringUtils.trimToEmpty(orderParam.getIsQzd());
					saveJpCgdd(jpKhdd, "1", yhb, loginfo, salePrice, totalMoney, res.getOrderid(), ifxepnr, jpPtzcFzsz, isQzd);
					//修改采购订单主表采购订单编号
					JpKhdd khdd=new JpKhdd();
					khdd.setDdbh(ddbh);
					khdd.setCgly("TB");
					khdd.setXgly("淘宝代购下单");
					khdd.setXgyh(yhb.getBh());
					khdd.setCgdw(orderParam.getPlatcode());
					khdd.setCgDdbh(res.getOrderid());
					jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(khdd);
					
					tbres.setStatus(TBSubmitOrderRes.SUCCESS);
					tbres.setOrderNo(res.getOrderid());
					tbres.setScny(salePrice+"");
					tbres.setPaymentAmount(tbTotalMoney+"");
					tbres.setTax(fee+"");
					tbres.setYq(tax+"");
					//本地淘宝代购下单成功后需要注册job获取淘宝票号回填信息
					try {
						JpCgdd jpcgdd = jpCgddServiceImpl.getJpCgddByCgly(jpKhdd.getDdbh(), "TB", res.getOrderid());
						qrtzServiceImpl.add("30001",jpcgdd.getId());
						LogUtil.getDgrz("TB",ddbh).error("注册淘宝票号回填扫描job成功");
						ptLog.add("注册淘宝票号回填扫描job成功");
					} catch (Exception e) {
						LogUtil.getDgrz("TB",ddbh).error("注册淘宝票号回填扫描job失败，请人工处理该订单票号。失败原因：",e);
						ptLog.add("注册淘宝票号回填扫描job失败，请人工处理该订单票号。请人工处理该订单票号。失败原因："+e.getMessage());
					}
					return tbres;
				}else if("2".equals(tbcpfs)&&i==1){
					String strTmp = "余票";
					String[] gjzArr = strTmp.split(",");
					String subMsg = StringUtils.trimToEmpty(res.getSubMsg());
					for(int j=0;j<gjzArr.length;j++){
						if(subMsg.indexOf(gjzArr[j])>-1){
							bl=true;
							break;
						}
					}
					if(!bl){//不符合余票不足
						bl=false;//其他异常，不继续请求
						//解析失败信息
						throw new Exception(subMsg);
					}
					//授权
//		        	String office = PlatSuperUtil.getOffficeByddbh(tmp_service, asmsddbh);
//		        	if(StringUtils.isBlank(office)){
//		        		office = "0".equals(kh_khdd.getDp_officeid())?"":kh_khdd.getDp_officeid();
//		        	} 
//		        	List<PnrDo> listPnrDo = new ArrayList<PnrDo>();
//		        	PnrDo pnrdo = new PnrDo();
//		        	pnrdo.setPnrCode(kh_khdd.getPnr_no());
//		        	pnrdo.setOfficeCode(office);
//		        	pnrdo.setListPassengerDocId(listPassengerDocId);
//		        	req.setListPnrDo(listPnrDo);
//		        	
//			        String pnr_lr = StringUtils.trimToEmpty(kh_khdd.getPnr_lr());
//			        if (pnr_lr.length() < 10) {
//			            try {
//			                pnr_lr = PlatSuperUtil.rtPnr(kh_khdd.getDdbh(), kh_khdd.getPnr_no(), office, ve_yhb, platpolicy_dao);
//			                platpolicy_dao.updatePnrlr(asmsddbh, pnr_lr);
//			            } catch (Exception e) {
//			                e.printStackTrace();
//			            }
//			        }
//			        if(pnr_lr.length()<10){
//			        	throw new Exception("获取PNR内容失败"); 
//			        }
//					String targetOffice = cs104008_2;
//					String[] rtnArr = TaobaoUtils.tbAuthPnr(targetOffice, pnr_lr, kh_khdd.getPnr_no(), office, ve_yhb);
//					if("-1".equals(rtnArr[0])){
//						throw new Exception(rtnArr[1]); 
//		        	}
				}else {
					throw new Exception(res.getSubMsg());
				}
	        }
        } catch(SocketTimeoutException e){
        	tbres.setStatus(TBSubmitOrderRes.FAILL);
        	tbres.setMsg(msg+" 淘宝订单创建失败，接口连接超时异常，请上淘宝官网检查订单是否成功！" + e.getMessage());
			e.printStackTrace();
			LogUtil.getDgrz("TB",ddbh).error(msg+" 淘宝订单创建失败，接口连接超时异常，请上淘宝官网检查订单是否成功！", e);
			ptLog.add(msg+" 淘宝订单创建失败，接口连接超时异常，请上淘宝官网检查订单是否成功！" + e.getMessage());
		} catch (Exception e) {
			tbres.setStatus(TBSubmitOrderRes.FAILL);
			tbres.setMsg(msg+" 淘宝订单创建失败:" + e.getMessage()+"，请上淘宝官网检查订单是否成功！");
			e.printStackTrace();
			LogUtil.getDgrz("TB",ddbh).error(msg+" 淘宝订单创建失败，请上淘宝官网检查订单是否成功！",e);
			ptLog.add(msg+" 淘宝订单创建失败:" + e.getMessage()+"，请上淘宝官网检查订单是否成功！");
        }finally{
    		try {
    			jpPtLogServiceImpl.insertLog(ptLog);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
		return tbres;
    }
    private void saveJpCgdd(JpKhdd jpKhdd,String zfzt,Shyhb yhb,String orderXml,double price,double zfje,String orderNo,String ifxepnr,JpPtzcFzsz jpPtzcFzsz,String isQzd){
    	if(StringUtils.isBlank(zfzt)){
	    	zfzt = "1";
	    }
    	//TODO 写本地采购订单表 jp_cgdd
		JpCgdd jpCgddBean = new JpCgdd();
		jpCgddBean.setId(Identities.randomLong()+"");
		jpCgddBean.setDdbh(jpKhdd.getDdbh());
		jpCgddBean.setZt(zfzt);//下单淘宝代购时，修改采购订单状态为支付成功
		jpCgddBean.setCjUserid(yhb.getBh());
		jpCgddBean.setCjdatetime(VeDate.getNow());
		jpCgddBean.setSubmitParam(orderXml);
		jpCgddBean.setOrderhkgs(jpKhdd.getHkgs());
		jpCgddBean.setHkgs(jpKhdd.getHkgs());
		jpCgddBean.setPnrno(jpKhdd.getCgPnrNo());
		jpCgddBean.setHkgsPnrno(jpKhdd.getCgHkgsPnr());
		jpCgddBean.setPaykind("1");
		jpCgddBean.setHkgszh("");
		jpCgddBean.setHbh(jpKhdd.getCgHbh());
		jpCgddBean.setCw(jpKhdd.getCgCw());
		jpCgddBean.setCwpj(price);
		jpCgddBean.setZfzh("");
		jpCgddBean.setZfje(zfje);
	    jpCgddBean.setCgZfkm(jpPtzcFzsz.getXtZfkm());
	    jpCgddBean.setIfxepnr("1");//这里先暂时默认为0
	    jpCgddBean.setCgly("TB");//采购来源取ve_class表 id字段 lb='10014'
	    jpCgddBean.setCgdw(PlatCode.TB);////采购单位取ve_class表 ywmc字段 lb='10014'
	    jpCgddBean.setShbh(yhb.getShbh());
	    jpCgddBean.setJyzt("1");
	    jpCgddBean.setDgdh(orderNo);
	    jpCgddBean.setIfqzdcp(isQzd);
		try {
			jpCgddServiceImpl.insert(jpCgddBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("淘宝代购下单插入jpCgdd失败。",e);
		}
		//修改订单表数据
		JpKhdd _jpKhdd = new JpKhdd();
		_jpKhdd.setDdbh(jpKhdd.getDdbh());
		_jpKhdd.setCgly("TB");
		_jpKhdd.setCgdw(PlatCode.TB);
		_jpKhdd.setCgDdbh(orderNo);
		_jpKhdd.setXgly("淘宝代购");
		_jpKhdd.setXgyh(yhb.getBh());
		try {
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(_jpKhdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	private String getCw(String cabin,String hkgs,String cgzcw,String tbxj){
		String cw = "";//要返回的值
		
		//条件一，如果订单舱位为非经济舱，则需要带舱位询价
		String cwdjlx = "0";
		List<BairwaycwCache> BairwaycwCacheList = bairwaycwCacheService.getEzdh(hkgs);
		for(int i=0;i<BairwaycwCacheList.size();i++){
			if(BairwaycwCacheList.get(i).getCwdj().equals(cabin)){
				cwdjlx = BairwaycwCacheList.get(i).getCwdjlx();
				break;
			}
		}
		if(StringUtils.isNotBlank(cwdjlx)&&"6,7".indexOf(cwdjlx)>-1){//头等舱 公务舱 要带舱位询价
			cw = cabin;
		}
		//条件二，根据参数判断是否带舱位询价
        //取参数cs104006
        Map<String,String> tmpMap = toMap(tbxj);
        if(tmpMap!=null&&tmpMap.size()!=0){
        	String tmp_cw_str = StringUtils.trimToEmpty(tmpMap.get(hkgs));
        	if(tmp_cw_str.indexOf(cabin)>-1||tmp_cw_str.indexOf("---")>-1){
        		cw = cabin;
        	}
        }
        //条件三，如果带舱位询价，并且订单中存在子舱位，则传子舱位
        if(StringUtils.isNotBlank(cw)&&StringUtils.isNotBlank(cgzcw)){
        	cw = cgzcw;
        }
		return cw;
	}
	private static Map<String,String> toMap(String str){
		if(StringUtils.isBlank(str)){
			return null;
		}
		Map<String,String> map = new HashMap<String, String>();
		try {
			String[] strArr1 = str.split(",");
			for(int i=0;i<strArr1.length;i++){
				String[] strArr2 = strArr1[i].split(":");
				map.put(strArr2[0], strArr2[1]);
			}
		} catch (Exception e) {
			return null;
		}
		return map;
	}
	/***************************本地淘宝代购END*********************************/
	/***************************CPSLINK淘宝代购START***************************/
    public List<PolicyItem> searchpolicy_2(String ddbh,Shyhb yhb) throws Exception {
		JpPtLog ptLog=new JpPtLog();
		boolean bl = false;//是否记录平台日志
		if(StringUtils.isBlank(ddbh)){
			throw new Exception("订单编号不能为空");
		}
		List<PolicyItem> policyList = new ArrayList<PolicyItem>();;
		try {
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			bean.setShbh(yhb.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, yhb.getShbh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, yhb.getShbh());
			if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
				throw new Exception("订单信息不全");
			}
			String flightno = jpKhdd.getCgHbh();
			flightno = flightno.replaceAll("\\*","");
			String cabin = jpKhdd.getCgCw();
			boolean hasChdOrInf = false;
			for(int i=0;i<cjrList.size();i++){
				JpKhddCjr onecjr = cjrList.get(i);
				if(!"1".equals(onecjr.getCjrlx())){
					hasChdOrInf = true;
					break;
				}
			}
			if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
				throw new Exception("淘宝代购只支持成人单程");
			}
			JpKhddHd khddHd = hdList.get(0);
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, yhb.getShbh());
			if(ptzh==null){
				throw new Exception("请先配置淘宝采购信息");
			}
			bl = true;
			ptLog.setShbh(yhb.getShbh());
			ptLog.setYhbh(yhb.getBh());
			ptLog.setDdbh(ddbh);
			ptLog.setPtzcbs(ptzh.getPtzcbs());//不能为空
			ptLog.setPnrNo(jpKhdd.getCgPnrNo());
			ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptLog.setYwlx(DictEnum.PTLOGYWLX.CZC.getValue());
			ptLog.setBy1("1001901");
			ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
			JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(yhb.getShbh(), "TB");
			if(jpCpymSz==null){
				jpCpymSz = new JpCpymSz();
			}
			String IgnoredShopNames = StringUtils.trimToEmpty((String)jpCpymSz.getTbGlDp());
			String docType="";  //证件类型		
			String docId=""; //证件ID		
			String passengerType=""; //乘客类型		
			String passengerName=""; //乘客姓名		
			String birthDate=""; //乘客出生日期
			for(int i=0;i<cjrList.size();i++){
				JpKhddCjr oneCjr = cjrList.get(i);
				String cjrlx = oneCjr.getCjrlx();
				if(!"1".equals(cjrlx)){
					throw new Exception("非成人订单不允许代购淘宝");
				}
				String zjlx = StringUtils.defaultIfBlank(oneCjr.getZjlx(), "NI");
				String zjhm = oneCjr.getZjhm();
				String cjr = oneCjr.getCjr();
				String csrq = oneCjr.getCsrq();
			    String zjlxT = zjlx;
			    if("NI".equals(zjlx)){
			    	zjlxT = "NI";
			    }else if("P".equals(zjlx)||"PP".equals(zjlx)){
			        zjlxT = "PP";
			    }else{//淘宝下单其他是TH
			    	zjlxT = "TH";
			    }
			    String csrq_new = "";
			    if("NI".equals(zjlxT)){
			    	csrq_new = VeStr.getBirthDateFromCard(zjhm);
			    }
			    if(StringUtils.isNotBlank(csrq_new)){
			    	csrq = csrq_new;
			    }
				docType+="|"+zjlxT;  	
				docId+="|"+zjhm; 		
				passengerType+="|"+cjrlx; 	
				passengerName+="|"+cjr; 		
				birthDate+="|"+csrq; 
			}
			docType = StringUtils.substring(docType, 1);
			docId = StringUtils.substring(docId, 1);
			passengerType = StringUtils.substring(passengerType, 1);
			passengerName = StringUtils.substring(passengerName, 1);
			birthDate = StringUtils.substring(birthDate, 1);
			
			//取设置tbxj
			String tbxj = StringUtils.trimToEmpty(jpCpymSz.getTbXj());//参数控制哪些航司带舱位询价
			String cw = getCw(cabin,jpKhdd.getHkgs(), khddHd.getCgZcw(), tbxj);
			LogUtil.getDgrz("TB",ddbh).error("根据订单编号["+ddbh+"],参数tbxj["+tbxj+"]判断是否要带舱位询价：" + cw);
			
			CpsLinkPolicyRequest req = new CpsLinkPolicyRequest();
			String price = "100";//参考集中出票获取淘宝C站价格 写死
			req.setFlightNo(flightno);
			req.setPnrNo(jpKhdd.getCgPnrNo());
			req.setOrderNo(ddbh);
			req.setSeatClass(cw);//询价时不传舱位
			req.setPrice(price);//询价时写死为100
			req.setTravelRange(khddHd.getCfcity()+khddHd.getDdcity());
			req.setAirways(jpKhdd.getHkgs());
			req.setFromDatetime(StringUtils.substring(khddHd.getCfsj(), 0, 10));//
			req.setIgnoredShopNames(IgnoredShopNames);//待确认  与张鸣对比
			req.setDocId(docId);
			req.setDocType(docType);
			req.setPassengerType(passengerType);
			req.setPassengerName(passengerName);
			req.setBirthDate(birthDate);

			req.setService("taobaoFlightInfo");//产品询价
			LogUtil.getDgrz("TB",ddbh).error("CPSLINK查询接口请求参数："+XmlUtils.toXml(req));
			ptLog.add("CPSLINK查询接口请求参数："+XmlUtils.toXml(req));
			String retXml = "";
			try {
				retXml = cpsInvoke.send(req, cpsService.getCpsAccount(yhb.getShbh()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			LogUtil.getDgrz("TB",ddbh).error("CPSLINK查询接口返回：" + retXml);
			ptLog.add("CPSLINK查询接口返回：" + retXml);
			CpsLinkPolicyResponse res = (CpsLinkPolicyResponse) XmlUtils.fromXml(retXml,CpsLinkPolicyResponse.class);
			if(!"1".equals(res.getStatus())){
				throw new Exception("CPSLINK询价接口返回失败，失败原因："+res.getMsg());
			}		
			int count = 0;
			int aduCou = cjrList.size();
			String cgGlCp = StringUtils.trimToEmpty((String)jpCpymSz.getCgGlCp());//控制过滤不显示产品的参数
			for(CpsLinkTbcb tbcp : res.getTbcp_info()) {
				String zws_str = tbcp.getAmount();
				double zws = NumberUtils.toDouble(zws_str, 10); 	
			    //QW：全网最低价产品 JX：精选产品 JP：金牌产品 HS：航司产品
			    String productType = StringUtils.trimToEmpty(tbcp.getProductType());
			    if(cgGlCp.indexOf(productType)>-1){
			    	continue;
			    }  
			    PolicyItem policyItem = new PolicyItem();
			    policyItem.setPtzcbs(PlatCode.CPS);
			    policyItem.setId("TAOBAO" + "@@" + productType + "@@" + "requestId" + "@" + count);
			    policyItem.setZclx("7");
			    double billPrice = NumberUtils.toDouble(tbcp.getTicket_price());//单人
			    double buyPrice = NumberUtils.toDouble(tbcp.getCabin_price());
			    double superProfit = Arith.sub(billPrice, buyPrice);//单人代理费
			    double rate = Arith.div(Arith.mul(superProfit, 100), billPrice,2); //这里存的是百分数
			    double tax = NumberUtils.toDouble(tbcp.getFee());//燃油
			    double jsf = NumberUtils.toDouble(tbcp.getTax());//建设费
			    double payfee = Arith.mul(Arith.sum(buyPrice,tax,jsf), aduCou);
			    String tgqStr = tbcp.getTuigaiqian();
			    policyItem.setSuperProfit(superProfit);
			    policyItem.setZcpj(tbcp.getTicket_price());
			    policyItem.setXsj(buyPrice);
			    policyItem.setPj_cgj(billPrice);
			    policyItem.setPayfee(payfee);
			    policyItem.setRate(rate);
			    policyItem.setRateshow(rate);
			    policyItem.setTgqgd(tgqStr);
			    policyItem.setJgxx("0");//以政策为准
			    policyItem.setZclx("7");//1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策 6精准营销价格类 7淘宝
			    policyItem.setLq(0.0);//把代理费全部放到留钱字段上面，防止精度缺失
			    
			    policyItem.setWorktime("截止2359");//默认值全天适用
			    policyItem.setChooseOutWorkTime("截止2359");//默认值全天适用
			    policyItem.setAircome(res.getAirawys());
			    policyItem.setCabin(tbcp.getCabin());
			    policyItem.setSdate(res.getFromDatetime());//出发日期
			    policyItem.setEdate(res.getToDatetime());//到达日期
			    policyItem.setOffice("");
			    
			    //取订单上面的机建税费【多人】
			    policyItem.setTax(tax);
			    policyItem.setJsf(jsf);

			    policyItem.setIsspecmark("0");// 0 普通政策 1特殊政策
			    String note = "淘宝C站产品类型：";
			    
			 	//获取数据字典中类别为7500的数据
			 	List<VeclassCache> listclass = veclassCacheService.getLb("7500");
			 	Map<String, String> map_class = new HashMap<String, String>();
			 	if(listclass!=null) {
			 		for(VeclassCache c : listclass){
			 			map_class.put(c.getYwmc(), c.getMc());
			 		}
			 	}
			    note = StringUtils.isBlank(map_class.get(productType)) ? note : map_class.get(productType);
			    policyItem.setPolicytype(note);
			    policyItem.setNote(note);
			    policyList.add(policyItem);
			    count ++ ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.getDgrz("TB",ddbh).error("查询CPSLINK淘宝政策报错。",e);
			ptLog.add("查询CPSLINK政策报错。"+e.getMessage());
		} finally{
			try {
				if(bl){
					jpPtLogServiceImpl.insertLog(ptLog);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return policyList; 
    }
    public TBSubmitOrderRes submitorder_2(PlatOrderParam orderParam) throws Exception{
    	TBSubmitOrderRes tbres = new TBSubmitOrderRes();
		Shyhb yhb=orderParam.getYhb();
		String ddbh=orderParam.getDdbh();
		PolicyItem policyItem = orderParam.getPolicyItem();
		JpPtLog ptLog=new JpPtLog();
		String shbh=yhb.getShbh();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(yhb.getBh());
		ptLog.setDdbh(ddbh);
        String policyId = orderParam.getPolicyId();//之前规定规则拼装的政策ID，格式："TAOBAO" + "@@" + productType + "@@" + requestId + "@" + count
        String productType = StringUtils.substringBeforeLast(policyId, "@@");
        productType = StringUtils.substringAfterLast(productType, "@@");
		JpKhdd bean = new JpKhdd();
		bean.setDdbh(ddbh);
		bean.setShbh(yhb.getShbh());
		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
		List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
		List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
		if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
			throw new Exception("订单信息不全");
		}
		boolean hasChdOrInf = false;
		for(int i=0;i<cjrList.size();i++){
			JpKhddCjr onecjr = cjrList.get(i);
			if(!"1".equals(onecjr.getCjrlx())){
				hasChdOrInf = true;
				break;
			}
		}
		if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
			throw new Exception("淘宝代购只支持成人单程");
		}
		JpKhddHd khddHd = hdList.get(0);
		JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, shbh);
		if(ptzh==null){
			throw new Exception("请先配置淘宝采购信息");
		}
		ptLog.setPtzcbs(orderParam.getPlatcode());//不能为空
		ptLog.setPnrNo(jpKhdd.getCgPnrNo());
		ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptLog.setYwlx(DictEnum.PTLOGYWLX.XD.getValue());
		ptLog.setBy1("1001901");
		ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(shbh, "TB");
		if(jpCpymSz==null){
			jpCpymSz = new JpCpymSz();
		}
		int crs = cjrList.size();
		double cgj = policyItem.getXsj(); //多人采购价
		double pmj = policyItem.getPj_cgj();//多人票面价
//		double tickePrice_all = (Double) NumberUtils.toDouble(kh_khdd.getPj_cgj());//获取的是多人的票面
		CreateOrderRequest request = new CreateOrderRequest();
		request.setService("createOrder");
		request.setOrderNo(ddbh);
		request.setBigPnrNo(jpKhdd.getXsHkgsPnr());
		request.setPnrNo(jpKhdd.getXsPnrNo());
		request.setProductType("9");//产品类型 1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策 6精准营销 7新版特价  9为cpslinktb
		JpKhddCjr cjr0 = cjrList.get(0);//取第一个乘机人用他来获取乘机人类型
		this.addPnrnr(jpKhdd, cjr0.getCjrlx(), yhb, request);
		request.setPolicyId(policyItem.getId());//政策ID
		request.setTravelType(jpKhdd.getHclx());//航程类型 1单程、2往返、3联程、4缺口
		String hc="";
		String hbh="";
		String realhbh="";//实际承运航班号
		String jx="";
		String cw="";
		String zcw = "";
		String zk="";
		String cfdatetime="";
		String dddatetime="";
		String cfhzl="";
		String ddhzl="";
		if("1".equals(jpKhdd.getHclx())){//单程  淘宝代购只支持单程
			hc = khddHd.getCfcity()+khddHd.getDdcity();
			hbh = khddHd.getCgHbh();
			jx = khddHd.getFjjx();
			cw = khddHd.getCgCw();
			zcw = khddHd.getCgZcw();
			cfdatetime = khddHd.getCfsj();
			dddatetime = khddHd.getDdsj();
			cfhzl = khddHd.getCfhzl();
			ddhzl = khddHd.getDdhzl();
		}
		request.setTravelRange(hc);//航程 WUHPEK|PEKCAN|WUHPEK|PEKCAN
		request.setFlightNo(hbh);//航班号
		//request.setRealFlightNo(realhbh);//实际承运航班号
		request.setPlaneType(jx);//机型
		request.setSeatClass(cw);//舱位
		if(!"|".equals(zcw)){
			request.setSubSeatClass(zcw);//子舱位
		}
		request.setFromDatetime(cfdatetime);//起飞时间 格式示例：2013-08-30 09:30| 2013-08-31 10:30
		request.setToDatetime(dddatetime);//到达时间 格式示例：2013-08-30 10:30| 2013-08-31 11:30
		request.setTofromterminal(cfhzl);//出发航站楼
		request.setTerminal(ddhzl);//到达航站楼
		String cjrs = "";
		String csrqs = "";
		String cjrlxs = "";
		String zjlxs = "";
		String zjhms = "";
		String sjhms = "";
		for(int i=1;i<cjrList.size();i++){
			cjrs+="|"+cjrList.get(i).getCjr();
			csrqs+="|"+cjrList.get(i).getCsrq();
			cjrlxs+="|"+cjrList.get(i).getCjrlx();
			zjlxs+="|"+cjrList.get(i).getZjlx();
			zjhms+="|"+cjrList.get(i).getZjhm();
			sjhms+="|"+cjrList.get(i).getSjhm();
		}
		int cjrcnt=cjrList.size();
		double zdj=Arith.div(pmj,cjrcnt);
		double yq=Arith.div(jpKhdd.getCgJsf(), cjrcnt);
		double tax=Arith.div(jpKhdd.getCgTax(), cjrcnt);
		String scnys=GetPolicyUtil.getIntStr(zdj);//账单价  单个人的
		String yqs=GetPolicyUtil.getIntStr(yq);
		String taxs=GetPolicyUtil.getIntStr(tax);
		
		String settlementPrice ="0";
		request.setPassenger(cjrs);//乘机人 格式示例： 乘机人名1｜乘机人名1｜乘机人名2｜乘机人名2
		request.setBirthDate(csrqs);//
		request.setPassengerType(cjrlxs);//乘机人类型
		request.setCardType(zjlxs);//证件类型 格式示例：NI|ID|PP
		request.setCardId(zjhms);//证件号码
		request.setPassengerMobile(sjhms);//手机号
		request.setScny(scnys);//账单价
		request.setYq(yqs);//税费
		request.setTax(taxs);//机建
		request.setSettlementPrice(settlementPrice);//结算价 不写值，只参与比较之用
		request.setIfChangeOrder("0");//是否升航换开订单 1是升舱换开，0不是升舱换开
		
        String tbnxr = StringUtils.trimToEmpty(jpCpymSz.getTbNxr());//联系人信息
		String linker = "";
		String linkMobile = "";
		String linkerEmail = "";
		String reg = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";       		
		try {
			ptLog.add("联系人信息参数【"+tbnxr+"】");
			String[] strArr = tbnxr.split("\\|\\|");
			linker = strArr[0];
			linkMobile = strArr[1];
			linkerEmail = strArr[2];
		} catch (Exception e) {
			//如果报错,不能影响
			ptLog.add("根据联系人参数信息失败！");
			linker = "";
			linkMobile = "";
			linkerEmail = "";
		}
		if(StringUtils.isBlank(linker)){
			ptLog.add("通过订单取联系人信息……");
			linker=jpKhdd.getNxr();
			linkMobile = jpKhdd.getNxsj();
			linkerEmail = "";
		}
		request.setLinker(linker);
		request.setLinkMobile(linkMobile);
		request.setLinkerEmail(linkerEmail);
        JpPtzcFzsz jpPtzcFzsz = jpPtzcFzszServiceImpl.genZfkmByDjm("2", jpKhdd.getShbh(), "312013300", PlatCode.CPS);//10063971是支付宝对接码
        if(jpPtzcFzsz==null){
        	throw new Exception("请先在[平台支付方式对应]中设置CPS[预存款]的支付科目！");
        }
		CreateOrderResponse res = null;	
		try {
			ptLog.add("|CPS下单开始:");
			ptLog.add("CPS创建订单请求XML1:"+request.toString());
			res = cpsInvoke.send(request, cpsService.getCpsAccount(yhb.getShbh()), CreateOrderResponse.class);
			if(res ==null){
				throw new Exception("调取cps接口下单出现异常!");
			}else{
				ptLog.add("CPS下单返回:"+res.toString());
				if("-1".equals(res.getStatus())){
					String errMsg = StringUtils.trimToEmpty(res.getErrorMessage());
					if(errMsg.indexOf("timed out")>-1 || errMsg.indexOf("time out")>-1){
						errMsg = "下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。";
					}
					ptLog.add("下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。");
					throw new Exception("错误代码:"+res.getErrorCode()+"错误原因:"+errMsg);
				}
				String minutes = res.getPaymentTimeLimit();//支付时限
				String zt=res.getOrderStatus();//CPS订单状态，已确认待支付112，待确认101，待确认订单不能直接支付
				// 1预定成功,等待采购方支付 10：支付完成，等待出票 12：无法出票 13：出票完成 14：更换编码出票完成
				// 21：退票处理中 22：无法退票 31：废票处理中 32：无法废票 90：完成退费票 99：交易取消退款
				if(StringUtils.isNotBlank(minutes)){//支付时限，如果是已确认待支付的订单，则返回此值，单位为分钟
					res.setErrMsgTip("请在"+minutes+"分钟内完成支付！否则系统会自动取消订单！");
				}
				double cwpj = NumberUtils.toDouble(res.getScny());
				double zfje = NumberUtils.toDouble(res.getPaymentAmount());
				ptLog.add("CPS下单成功，开始获取支付链接，传入的支付单号/金额为："+res.getOrderNo()+"/"+res.getPaymentAmount());
				GetOrderPayLinkResponse paylink = cpsService.getPayUrl(res.getOrderNo(), res.getPaymentAmount());
				String payurl=paylink.getUrl();
				String payment = paylink.getPayment();
				ptLog.add("CPS下单成功，获取支付链接："+payurl);
				String zfzt = "0";
				tbres.setOrderStatus("3");
				if("10".equals(zt)){
					zfzt="1";
					tbres.setOrderStatus("1");
				}
				if ("1".equals(orderParam.getAutopay())&&"0".equals(zfzt)) {
					ptLog.add("ASMS后台创建平台订单后自动代扣开始");
					try {
						cpsService.submitautopay(res.getOrderNo(), res.getPaymentAmount(),payment);
						ptLog.add("ASMS后台创建平台订单后自动代扣完成");
						zfzt = "1";
						tbres.setOrderStatus("1");
					} catch (Exception e) {
						tbres.setOrderStatus("2");
						tbres.setZfsbyy("ASMS后台创建平台订单后自动代扣失败："+e.getMessage());
						ptLog.add("ASMS后台创建平台订单后自动代扣失败："+e.getMessage());
					}
				}
				String isQzd = StringUtils.trimToEmpty(orderParam.getIsQzd());
				saveJpCgdd(jpKhdd, zfzt, yhb, request.toString(), cwpj, zfje, res.getOrderNo(), "0", jpPtzcFzsz, isQzd);
				//修改采购订单主表采购订单编号
				JpKhdd khdd=new JpKhdd();
				khdd.setDdbh(ddbh);
				khdd.setCgly("TB");
				khdd.setXgly("淘宝代购下单");
				khdd.setXgyh(yhb.getBh());
				khdd.setCgdw(orderParam.getPlatcode());
				khdd.setCgDdbh(res.getOrderNo());
				jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(khdd);
				
				tbres.setStatus(TBSubmitOrderRes.SUCCESS);
				tbres.setOrderNo(res.getOrderNo());
				tbres.setScny(res.getScny());
				tbres.setPaymentAmount(res.getPaymentAmount());
				tbres.setTax(res.getTax());
				tbres.setYq(res.getYq());
				tbres.setPaymentTimeLimit(minutes);
				tbres.setPayurl(payurl);
				tbres.setPayment(payment);
				return tbres;
			}
		} catch (SocketTimeoutException e) {
			tbres.setStatus(TBSubmitOrderRes.FAILL);
			tbres.setMsg("错误原因:下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。");
		} catch (Exception e) {
			tbres.setStatus(TBSubmitOrderRes.FAILL);
			tbres.setMsg("错误原因："+e.getMessage());
		} finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tbres;
    }
	/**
	 * <功能详细描述>
	 * 获取pnr内容
	 * @param jpKhdd
	 * @param cjrlx
	 * @param yhb
	 * @param request [参数说明]
	 */
	private void addPnrnr(JpKhdd jpKhdd,String cjrlx,Shyhb yhb,Object request) throws Exception{
		String ddbh=jpKhdd.getDdbh();
		String shbh=yhb.getShbh();
		String patnr=jpKhdd.getPatLr();
		String pnrnr=jpKhdd.getPnrLr();
		if(StringUtils.isBlank(patnr) || StringUtils.isBlank(pnrnr)){
			JpPz jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if(jppz==null){
				throw new Exception("没获取到商户"+shbh+"的pid配置");
			}
			Pnr pnrObj=DataUtils.getPnrPat(jpKhdd.getCgPnrNo(), cjrlx,jppz ,yhb,"");
			pnrnr=pnrObj.getPnr_lr();
			patnr=pnrObj.getPat();
			JpKhdd updd=new JpKhdd();
			updd.setDdbh(ddbh);
			updd.setPnrLr(pnrnr);
			updd.setShbh(shbh);
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(updd);
		}
		pnrnr = StringUtils.trimToEmpty(pnrnr).replaceAll("\r\n", "\n");
		patnr = StringUtils.trimToEmpty(patnr).replaceAll("\r", "\n");
		BeanUtils.setProperty(request, "pnrContent", pnrnr);
		BeanUtils.setProperty(request, "patContent", patnr);
	}
}
