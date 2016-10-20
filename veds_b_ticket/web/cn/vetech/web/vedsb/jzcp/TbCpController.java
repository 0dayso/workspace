package cn.vetech.web.vedsb.jzcp;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.business.cache.BairwaycwCache;
import org.vetech.core.business.cache.BairwaycwCacheService;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.cpsz.JpCpymSz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpCpymSzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.CabinSeats;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Flight;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Segment;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripFlightinfoGetRequest;
import com.taobao.api.request.AlitripOrderinfoAirbookRequest;
import com.taobao.api.response.AlitripFlightinfoGetResponse;
import com.taobao.api.response.AlitripOrderinfoAirbookResponse;

@Controller
public class TbCpController extends AbstractBaseControl{
	private static Logger logger = LoggerFactory.getLogger(TbCpController.class);
	public static Map<String,Segment> tbSegMap = new HashMap<String, Segment>();
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private VeclassCacheService veclassCacheService;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private JpCpymSzServiceImpl JpCpymSzServiceImpl;
	@Autowired
	private BairwaycwCacheService bairwaycwCacheService;
	@RequestMapping
    public String tbPolicy(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		// 航班号 舱位 价格
		String flightno = request.getParameter("flightno");
		String cabin = request.getParameter("cabin");
		long price = 0;
//		ddbh="JP160424000480";
//		flightno="*SC1334";
//		cabin="Y";
		//联系人信息
//		String lxr = request.getParameter("lxr");
//		String lxrsj = request.getParameter("lxrsj");
//		String lxremail = request.getParameter("lxremail");
		Segment segment = new Segment();
		try {
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("订单编号不能为空");
			}
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			bean.setShbh(user.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getShbh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getShbh());
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
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, user.getShbh());
			if(ptzh==null){
				throw new Exception("请先配置淘宝采购信息");
			}
			JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(user.getShbh(), "TB");
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
	//		//获取相关信息
			/**
			 * 1	14111318250798031	0050	VETECH	
			 * 淘宝b2c数据同步资料， 
			 * 参数1 appkey@@App Secret@@sessionkey     
			 * 参数2 url@@ChannelName@@Password	
			 * 23058691@@94bbe1c6a45633041980459cb84c0258@@6102b20dc5febc988a0f5cffc46c154b25ec8c32b5e4a5b78079581	
			 * http://121.196.129.162:30001/airs/conver.shtml@@胜意@@123456	LIADMIN	2014/11/28 9:25:07
			 */
	//		String url = "http://112.124.54.173:30001/airs/conver.shtml";
	//		String appkey = "21549144";
	//		String secret = "97582f944eeee2a12322360cd9a81a39";
	//		String sessionKey = "6100e05b17afd17b05005ed6e44608e44367992812e8c872708151637";
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
	//		String IgnoredShopNames = Function.acsb("104002", ve_yhb.getZgscompid(), "YDHM", "");	
			req.setIgnoredShopNames(IgnoredShopNames);
			req.setReservationCode(reservationCode);
	        //控制是否走淘宝原编码出票模式,此模式下淘宝询价多出一个参数has_pnr（如该参数为true，则搜索产品时不再做AV过滤，即：没有余票的产品也将返回）。
	//      String cs104008_1=Function.acsb("104008", ve_yhb.getZgscompid(), "YDHM", "0");//默认0;
			String tbcpfs = StringUtils.trimToEmpty(jpCpymSz.getTbCpfs());
			LogUtil.getDgrz("TB",ddbh).error("判断是否走原编码流程参数tbcpfs["+tbcpfs+"]" );
		    if("1".equals(tbcpfs)){//0全部走换编码流程；1全部走原编码流程；2先走还编码流程如果下单返回余票不足时，用原编码下单。
		       req.setHasPnr(true);
		    }else{
		       req.setHasPnr(false);
		    }
			
			AlitripFlightinfoGetResponse res = client.execute(req , sessionKey);
			Map<String,String> map = res.getParams();
			if(map!=null) {
				String loginfo = "";
				for(String key : map.keySet()) {
					loginfo += key+"-->"+map.get(key)+"&";
				}
				LogUtil.getDgrz("TB",ddbh).error("请求参数：" + loginfo);
			}
			LogUtil.getDgrz("TB",ddbh).error("请求淘宝查询接口返回：" + res.getBody());
			//解析返回
			ParseXml pXml = new ParseXml(res.getBody());
			Element lowest_product_list = pXml.ele("lowest_product_list");
			
			Flight f = new Flight();
			List<CabinSeats> cs = new ArrayList<CabinSeats>();
			
			//用于过滤产品类型不展示
//			String cs104001 = Function.acsb("104001", ve_yhb.getZgscompid(), "YDHM", "");
			
		 	List<Element> lowest_products = lowest_product_list.elements("lowest_product");
		 	
		 	//获取数据字典中类别为7500的数据
		 	List<VeclassCache> listclass = veclassCacheService.getLb("7500");
		 	Map<String, String> map_class = new HashMap<String, String>();
		 	if(listclass!=null) {
		 		for(VeclassCache c : listclass){
		 			map_class.put(c.getYwmc(), c.getMc());
		 		}
		 	}
		 	double billPrice = jpKhdd.getCgZdj();
		 	String cgglcp = StringUtils.trimToEmpty(jpCpymSz.getCgGlCp());
		 	LogUtil.getDgrz("TB",ddbh).error("产品过滤参数cgglcp["+cgglcp+"]" );
		 	for(Element e : lowest_products) {
//            	String zws_str = e.elementTextTrim("amount");
//            	double zws = NumberUtils.toDouble(zws_str, 10);
		 		if(cgglcp.indexOf(e.elementTextTrim("product_type"))>-1) {
		 			continue;
		 		}
		 		CabinSeats cabinseats = new CabinSeats();
	 			cabinseats.setSeats(e.elementTextTrim("amount"));
	 			cabinseats.setPrice(e.elementTextTrim("cabin_price"));
	 			cabinseats.setTicket_price(billPrice+"");
	 			cabinseats.setCabin(e.elementTextTrim("cabin"));
	 			cabinseats.setBuildTax(e.elementTextTrim("fee"));//基建
	 			cabinseats.setFualTax(e.elementTextTrim("tax"));//燃油
	 			String product_type = StringUtils.trimToEmpty(e.elementTextTrim("product_type")).toUpperCase();
	 			cabinseats.setType(map_class.get(product_type)== null ? product_type : map_class.get(product_type));
	 			cabinseats.setCplx(product_type);
	 			Element coupon_infoEl = e.element("coupon_info");
	 			if(coupon_infoEl!=null){
	 				String discountInfo = coupon_infoEl.elementTextTrim("act_desc");
	 				String name = coupon_infoEl.elementTextTrim("name");
	 				String benefitValue = coupon_infoEl.elementTextTrim("benefit_value");
	 				if(StringUtils.isBlank(discountInfo)&&StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(benefitValue)){
		 				discountInfo = name+" "+benefitValue+"元";
	 				}
	 				if(StringUtils.isNotBlank(discountInfo)){
	 					cabinseats.setDiscountInfo(discountInfo+"，实际以下单为准");
	 				}
	 			}
	 			double _price = NumberUtils.toDouble(cabinseats.getPrice(),0);
	 			if(_price>0) {
	 				cs.add(cabinseats);
	 			}
		 	}
		 	Collections.sort(cs, new Comparator(){
				@Override
				public int compare(Object o1, Object o2) {
					CabinSeats cabinSeats1 = (CabinSeats)o1;
					CabinSeats cabinSeats2 = (CabinSeats)o2;
					double price1= NumberUtils.toDouble(cabinSeats1.getPrice(), 0.0);
					double price2= NumberUtils.toDouble(cabinSeats2.getPrice(), 0.0);
					String cplx2 = cabinSeats2.getCplx();
					if(price1>price2){
						return 1;
					}else if(price1 == price2){
						if("HS".equals(cplx2)){
							return 1;
						}else{
							return 0;
						}
					}else{
						return 0;
					}
				}	 		
		 	});
			//机建税费，退改签
			
			f.setDepTime(pXml.textTrim("dep_time"));
			f.setArrTime(pXml.textTrim("arr_time"));
			f.setFlightNo(pXml.textTrim("flight_no"));
			
			segment.setDepCity(pXml.textTrim("dep_airport"));
			segment.setArrCity(pXml.textTrim("arr_airport"));
			segment.setAirline(pXml.textTrim("airline"));
			segment.setFlightDate(pXml.textTrim("dep_date"));

			f.setCabins(cs);
			
			List<Flight> l = new ArrayList<Flight>();
			l.add(f);
			
			segment.setFlights(l);
			if(segment!=null){
				request.setAttribute("segment", segment);
				//将查出来的政策存缓存
				tbSegMap.put(user.getShbh()+"@"+user.getBh()+"@"+ddbh, segment);
			}
		} catch (Exception e) {
			logger.error("提取TB政策报错",e);
			e.printStackTrace();
		}
		return viewpath("tbolicynew");
	}
	@RequestMapping
	public String tbexportpage(HttpServletRequest request, HttpServletResponse response){
		Shyhb user = SessionUtils.getShshbSession();
		Segment segment = new Segment();

		String hkgs = request.getParameter("hkgs");
		String ddbh = request.getParameter("ddbh");
		// 航班号 舱位 价格
		String flightno = request.getParameter("flightno");
		String cabin = request.getParameter("cabin");
		long price = NumberUtils.toLong(request.getParameter("price"));//总价
		try {
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("订单编号不能为空");
			}
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getBh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getBh());
			
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
				throw new Exception("C站代购只支持成人单程");
			}
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, user.getBh());
			if(ptzh==null){
				throw new Exception("请先配置淘宝采购信息");
			}
			JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(user.getShbh(), "TB");
			//先检查是否存在jpCgdd信息
			JpCgdd jpCgddBean = new JpCgdd();
			jpCgddBean.setDdbh(ddbh);
			jpCgddBean.setCgly("100146");
			jpCgddBean.setCgdw("TB");
			JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddByCgly(ddbh,"100146", user.getShbh());//cgly来至veclass里面lb='10014'参数
			if(jpCgdd!=null&&StringUtils.isNotBlank(jpCgdd.getDgdh())) {//如果已经下单到淘宝了
				return "";
			}
	        String bookTravelerInfo ="";     
	        bookTravelerInfo = bookTravelerInfo +"[";
	        for(int i=0;i<cjrList.size();i++) {
	        	JpKhddCjr cjr = cjrList.get(i);
				String zjhm = StringUtils.trimToEmpty(cjr.getZjhm());
				String cjrlx = cjr.getCjrlx();
				if(!"1".equals(cjrlx)){
					throw new Exception("非成人订单不询价");
				}
				String zjlx = cjr.getZjlx();
	            String zjlxT = zjlx;
	            if("NI".equals(zjlx)){
	            	zjlxT = "NI";
	            }else if("P".equals(zjlx)||"PP".equals(zjlx)){
	                zjlxT = "PP";
	            }else{//淘宝下单其他是TH
	            	zjlxT = "TH";
	            }
	            String brithday= "";
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
	
			JpKhddHd hc = hdList.get(0);
			String dep_airport = hc.getCfcity();
			String arr_airport = hc.getDdcity();
			String cfsj = hc.getCfsj();
			String dep_date = StringUtils.substring(cfsj, 0, 10);
			String airline_code = flightno.replaceAll("\\*", "").substring(0, 2);
			String reservationCode = ddbh.replaceAll("TK", "99");
			//获取相关信息
			String url = ptzh.getUrl1();
			String appkey = ptzh.getUser1();
			String secret = ptzh.getPwd1();
			String sessionKey = ptzh.getUrl2();
	//		String url = "http://112.124.54.173:30001/airs/conver.shtml";
	//		String appkey = "21549144";
	//		String secret = "97582f944eeee2a12322360cd9a81a39";
	//		String sessionKey = "6100e05b17afd17b05005ed6e44608e44367992812e8c872708151637";
	
			String channelName = "胜意";
			String password = MD5Tool.MD5Encode("123456");
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret, "xml");
			AlitripFlightinfoGetRequest req = new AlitripFlightinfoGetRequest();
			req.setAirlineCode(airline_code);
			req.setCabin(cabin);
			req.setChannelName(channelName);
			req.setPassword(password);
			req.setPrice(price);
			req.setFlightNo(flightno);
			req.setDepAirport(dep_airport);
			req.setArrAirport(arr_airport);
			req.setDepDate(dep_date);
			req.setBookTravelerList(bookTravelerInfo);
			
	//		String IgnoredShopNames = Function.acsb("104002", ve_yhb.getZgscompid(), "YDHM", "");	
			String tbgldp = StringUtils.trimToEmpty(jpCpymSz.getTbGlDp());
			req.setReservationCode(reservationCode);
			req.setIgnoredShopNames(tbgldp);
			String tbcpfs = jpCpymSz.getTbCpfs();
			LogUtil.getDgrz("TB",ddbh).error("判断是否走原编码流程参数tbcpfs["+tbcpfs+"]" );
		    if("1".equals(tbcpfs)){//0全部走换编码流程；1全部走原编码流程；2先走还编码流程如果下单返回余票不足时，用原编码下单。
		       req.setHasPnr(true);
		    }else{
		       req.setHasPnr(false);
		    }
			
			AlitripFlightinfoGetResponse res = client.execute(req, sessionKey);
			Map<String,String> map = res.getParams();
			if(map!=null) {
				String loginfo = "";
				for(String key : map.keySet()) {
					loginfo += key+"-->"+map.get(key)+"&";
				}
				LogUtil.getDgrz("TB",ddbh).error("请求参数："+loginfo);
			}
			LogUtil.getDgrz("TB",ddbh).error("请求淘宝查询接口返回：" + res.getBody());
	//		String sql1 = "select cjrsxz from B_AIRWAY where ezdh = ?";	
	//		List<String> param1 = new ArrayList<String>();
	//		param1.add(hkgs);
	//		String cjrsxz_str = "";//取乘机人数限制
	//		try {
	//			cjrsxz_str = (String)UtilComp.OneValue(tmp_service.getJdbcTempSource().getDataSource(), sql1, param1);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		Double cjrsxz = NumberUtils.toDouble(cjrsxz_str, 9);
			
			//解析返回
			ParseXml pXml = new ParseXml(res.getBody());
			Flight f = new Flight();
			CabinSeats cabinseats = new CabinSeats();
			String priceType = request.getParameter("priceType");
		 	double billPrice = jpKhdd.getCgZdj();
		 	Element lowest_product_list = pXml.ele("lowest_product_list");
		 	List<Element> lowest_products = lowest_product_list.elements("lowest_product");
		 	for(Element e : lowest_products) {
		 		if(priceType.equals(e.elementTextTrim("product_type"))) {
		 			cabinseats = new CabinSeats();
		 			cabinseats.setSeats(e.elementTextTrim("amount"));
		 			cabinseats.setPrice(e.elementTextTrim("cabin_price"));
		 			cabinseats.setTicket_price(billPrice+"");
		 			cabinseats.setCabin(e.elementTextTrim("cabin"));
		 			cabinseats.setBuildTax(e.elementTextTrim("fee"));
		 			cabinseats.setFualTax(e.elementTextTrim("tax"));
		 			Element coupon_infoEl = e.element("coupon_info");
		 			if(coupon_infoEl!=null){
		 				String discountInfo = coupon_infoEl.elementTextTrim("act_desc");
		 				String name = coupon_infoEl.elementTextTrim("name");
		 				String benefitValue = coupon_infoEl.elementTextTrim("benefit_value");
		 				if(StringUtils.isBlank(discountInfo)&&StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(benefitValue)){
			 				discountInfo = name+" "+benefitValue+"元";
		 				}
		 				if(StringUtils.isNotBlank(discountInfo)){
		 					cabinseats.setDiscountInfo(discountInfo+"，实际以下单为准");
		 				}
		 			}
		 		}
		 	}
			//机建税费，退改签
			f.setDepTime(pXml.textTrim("dep_time"));
			f.setArrTime(pXml.textTrim("arr_time"));
			f.setFlightNo(pXml.textTrim("flight_no"));

			segment.setDepCity(pXml.textTrim("dep_airport"));
			segment.setArrCity(pXml.textTrim("arr_airport"));
			segment.setAirline(pXml.textTrim("airline"));
			segment.setFlightDate(pXml.textTrim("dep_date"));

			List<CabinSeats> cs = new ArrayList<CabinSeats>();
			cs.add(cabinseats);
			f.setCabins(cs);

			List<Flight> l = new ArrayList<Flight>();
			l.add(f);

			segment.setFlights(l);
			
			
			String tbnxr = StringUtils.trimToEmpty(jpCpymSz.getTbNxr());//联系人信息
			String linker = "";
			String linkMobile = "";
			String linkerEmail = "";
			try {
				String[] strArr = tbnxr.split("\\|\\|");
				linker = strArr[0];
				linkMobile = strArr[1];
				linkerEmail = strArr[2];
			} catch (Exception e) {
				//如果报错,不能影响
				linker = "";
				linkMobile = "";
				linkerEmail = "";
			}
			if(StringUtils.isBlank(linker)){
				linker=jpKhdd.getNxr();
				linkMobile = jpKhdd.getNxr();
				linkerEmail = "";
			}
			String rtnMsg = "";
//			String pnr_zt = jpKhdd.getPnrLr();
//			if("RR".equals(pnr_zt)){
//				rtnMsg = "该订单的PNR状态为RR，为了避免重复出票，不能进行代购！";
//				request.setAttribute("rtnMsg", rtnMsg);
//			}else if(StringUtils.isBlank(linker)||StringUtils.isBlank(linkMobile)||StringUtils.isBlank(linkerEmail)){
//				rtnMsg = "联系人信息不全不能下单";
//				request.setAttribute("rtnMsg", rtnMsg);
//			}
			//将查出来的政策存缓存
			tbSegMap.put(user.getShbh()+"@"+user.getBh()+"@"+ddbh, segment);
			request.setAttribute("jpKhdd", jpKhdd);
			request.setAttribute("cjrList", cjrList);
			request.setAttribute("segment", segment);
			request.setAttribute("linker", linker);
			request.setAttribute("linkMobile", linkMobile);
			request.setAttribute("linkerEmail", linkerEmail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("打开淘宝代购确认页面报错",e);
		}
		return "";
	}
	@RequestMapping
	public String tbexportsave(HttpServletRequest request, HttpServletResponse response){
		Shyhb user = SessionUtils.getShshbSession();
		String hkgs = request.getParameter("hkgs");
		String ddbh = request.getParameter("ddbh");
		String cgzfkm = request.getParameter("cgzfkm");
		
		
		String lxrxm = request.getParameter("lxrxm");
		String lxrsj = request.getParameter("lxrsj");
		String lxremail = request.getParameter("lxremail");
		String rtnmsg = "";
		try {
			// 如果修改了乘机人或者联系人则修改订单
//			Map m = request.getParameterMap();
//			b2c_service.updateCjrLxr(ve_yhb, b2bmsg, m);
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("订单编号不能为空");
			}
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getBh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getBh());
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
				throw new Exception("C站代购只支持成人单程");
			}
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, user.getBh());
			if(ptzh==null){
				throw new Exception("请先配置淘宝采购信息");
			}
			JpCpymSz jpCpymSz = JpCpymSzServiceImpl.selectByShbh(user.getShbh(), "TB");
			//准备数据
			Segment seg = tbSegMap.get(user.getShbh()+"@"+user.getBh()+"@"+ddbh);
			Flight flight = seg.getFlights().get(0);
			CabinSeats cabin = flight.getCabins().get(0);
			
			//获取相关信息
			String url = ptzh.getUrl1();
			String appkey = ptzh.getUser1();
			String secret = ptzh.getPwd1();
			String sessionKey = ptzh.getUrl2();
			String channelName = "胜意";
			String password = MD5Tool.MD5Encode("123456");
			
			int salePrice = (int)NumberUtils.toDouble(cabin.getPrice());
			int fee = (int)NumberUtils.toDouble(cabin.getFualTax());
			int tax = (int)NumberUtils.toDouble(cabin.getBuildTax());
			String extra="";
			
			if(0==fee) {
				fee = (int)Arith.div(jpKhdd.getCgJsf(),cjrList.size());
			}
			if(0==tax) {
				tax = (int)Arith.div(jpKhdd.getCgTax(),cjrList.size());
			}
			
			int oneMoney = (int)NumberUtils.toDouble(Arith.add(Arith.add(salePrice,fee),tax)+"");
			
			int totalMoney = oneMoney*cjrList.size();
			//修改连接超时时限为30秒，读取超时时限为2分钟
			TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret,"xml",30*1000,2*60*1000);
			String reservationCode= ddbh.replace("TK", "99");//把我們系統订单编号前缀TK改成99,
			AlitripOrderinfoAirbookRequest req=new AlitripOrderinfoAirbookRequest();
			
			req.setChannelName(channelName);
			req.setPassword(password);
			req.setReservationCode(reservationCode);
			req.setTotalMoney(totalMoney+"");
			req.setFee(fee+"");
			req.setTax(tax+"");
			req.setExtra(extra);
			
			String priceType = request.getParameter("priceType");//从页面获取要采购的产品类型
			req.setProductType(priceType);
			String tbgldp = StringUtils.trimToEmpty(jpCpymSz.getTbGlDp());
			req.setIgnoredShopNames(tbgldp);

			String dep_airport = seg.getDepCity();
			String arr_airport = seg.getArrCity();
			String dep_date = seg.getFlightDate();
			String airline_code = seg.getAirline();
			String flight_no = flight.getFlightNo();
			String cw = cabin.getCabin();
			
			String dep_time = flight.getDepTime();//2014-12-25 16:10:00
			String arr_time = flight.getArrTime();
			
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
			
			String bookTravelerInfo =""; 
			String bookArrangerInfo ="";
			
			bookTravelerInfo = bookTravelerInfo +"[";
			List<String> listPassengerDocId = new ArrayList<String>();
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
				listPassengerDocId.add(cjr.getZjhm());
				bookTravelerInfo = bookTravelerInfo +"\"passenger_name\":\""+cjr.getCjr()+"\"," +
													"\"passenger_type\":\""+cjrlx+"\"," +
													"\"doc_id\":\""+cjr.getZjhm()+"\"," +
													"\"doc_type\":\""+cjr.getZjlx()+"\"," +
													"\"birth_date\":\""+cjr.getCsrq()+"\"}";
			}
			bookTravelerInfo = bookTravelerInfo +"]";
			
			bookArrangerInfo = bookArrangerInfo +"{\"contact_name\":\""+lxrxm+"\"," +
												"\"contact_phone\":\""+lxrsj+"\"," +
												"\"contact_email\":\""+lxremail+"\"}";
			
			req.setBookArrangerInfo(bookArrangerInfo);
			req.setBookFlightSegmentList(bookFlightSegment);
			req.setBookTravelerList(bookTravelerInfo);
			
			
			String tbcpfs =  StringUtils.trimToEmpty(jpCpymSz.getTbCpfs());
	        if("1".equals(tbcpfs)){//原编号出票要授权
//	        	String office = PlatSuperUtil.getOffficeByddbh(tmp_service, asmsddbh);
//	        	if(StringUtils.isBlank(office)){
//	        		office = jpKhdd.get;
//	        	}
//	        	String office = "";
//	        	List<PnrDo> listPnrDo = new ArrayList<PnrDo>();
//	        	PnrDo pnrdo = new PnrDo();
//	        	pnrdo.setPnrCode(jpKhdd.getCgPnrNo());
//	        	pnrdo.setOfficeCode(office);
//	        	pnrdo.setListPassengerDocId(listPassengerDocId);
//	        	req.setListPnrDo(listPnrDo);
//		        String pnr_lr = StringUtils.trimToEmpty(kh_khdd.getPnr_lr());
//		        if (pnr_lr.length() < 10) {
//		            try {
//		                pnr_lr = PlatSuperUtil.rtPnr(kh_khdd.getDdbh(), kh_khdd.getPnr_no(), office, ve_yhb, platpolicy_dao);
//		                platpolicy_dao.updatePnrlr(asmsddbh, pnr_lr);
//		            } catch (Exception e) {
//		                e.printStackTrace();
//		            }
//		        }
//		        if(pnr_lr.length()<10){
//		        	throw new Exception("获取PNR内容失败");
//		        }
//				String targetOffice = cs104008_2;
//				String[] rtnArr = TaobaoUtils.tbAuthPnr(targetOffice, pnr_lr, kh_khdd.getPnr_no(), office, ve_yhb);
//	        	if("-1".equals(rtnArr[0])){
//	        		throw new Exception(rtnArr[1]);
//	        	}
	        }
	        String ifxepnr = "1";
	        boolean bl = false;
	        int i=0;
	        while(i==0||bl){//当cs104008_1为2，并且换编码下单失败返回"余票不足"是
	        	i++;//第i次请求
	        	ifxepnr = "1".equals(tbcpfs) || ("2".equals(tbcpfs) &&i==2) ? "1" : "0";
				AlitripOrderinfoAirbookResponse res = client.execute(req, sessionKey);
				
				Map<String,String> map = res.getParams();
				if(map!=null) {
					String loginfo = "";
					for(String key : map.keySet()) {
						loginfo += key+"-->"+map.get(key)+"&";
					}
					LogUtil.getDgrz("TB",ddbh).error("第"+i+"次请求参数："+loginfo);
				}
				LogUtil.getDgrz("TB",ddbh).error("第"+i+"次请求淘宝下单接口返回：" + res.getBody());
				if (StringUtils.isNotBlank(res.getOrderid())) {
					int tbTotalMoney = (int)NumberUtils.toDouble(res.getTotalMoney(),0);
					if(tbTotalMoney!=0){
						int cj = (int)Arith.sub(totalMoney, tbTotalMoney);
						totalMoney = tbTotalMoney;
						salePrice = (int)Arith.sub(salePrice, Arith.div(cj, cjrList.size()));
					}
					bl=false;//下单成功，不继续请求
					rtnmsg ="订单创建成功单号是:" +res.getOrderid()+"支付金额是：" + totalMoney;
					LogUtil.getDgrz("TB",ddbh).error("订单创建成功单号是:" +res.getOrderid()+"支付金额是：" + totalMoney);
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
		}catch(SocketTimeoutException e){
			e.printStackTrace();
			rtnmsg="本地订单创建失败，接口连接超时异常，请上淘宝官网检查订单是否成功！" + e.getMessage();
		}catch (Exception e) {
			e.printStackTrace();
			rtnmsg = "订单创建失败:" + e.getMessage();
		}
		return rtnmsg;
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
	
}
