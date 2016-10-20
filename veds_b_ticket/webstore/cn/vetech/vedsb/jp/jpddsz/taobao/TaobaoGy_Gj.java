package cn.vetech.vedsb.jp.jpddsz.taobao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.IeBaseOrderVo;
import com.taobao.api.domain.IeBookFlightVo;
import com.taobao.api.domain.IeBookOrderVo;
import com.taobao.api.domain.IeBookTicketVo;
import com.taobao.api.domain.IeContactsVo;
import com.taobao.api.domain.IeItemVo;
import com.taobao.api.domain.IeItineraryVo;
import com.taobao.api.domain.IeOrderVo;
import com.taobao.api.domain.IePassgenerVo;
import com.taobao.api.domain.TripOrder;
import com.taobao.api.request.AlitripIeAgentOrderGetRequest;
import com.taobao.api.request.AlitripIeAgentOrderSearchRequest;
import com.taobao.api.request.AlitripIeAgentTicketIssueRequest;
import com.taobao.api.request.AlitripIeAgentTicketIssueRequest.IeBookPnrVo;
import com.taobao.api.request.AlitripIeAgentTicketIssueRequest.IeIssueTicketVO;
import com.taobao.api.request.AlitripIeAgentTicketIssueRequest.IePassengerTicketVO;
import com.taobao.api.response.AlitripIeAgentOrderGetResponse;
import com.taobao.api.response.AlitripIeAgentOrderSearchResponse;
import com.taobao.api.response.AlitripIeAgentTicketIssueResponse;



public class TaobaoGy_Gj extends JpddGySuper {
	public static Map<String,TripOrder> tmpMap = new HashMap<String, TripOrder>();
	private String URL;
	private String SessionKey;
	private TaobaoClient client;
	private JpDdsz jpDdsz;

	public TaobaoGy_Gj(JpDdsz jpDdsz) {
		this.jpDdsz = jpDdsz;
		this.URL = jpDdsz.getDdJkdz();
		String appkey = jpDdsz.getDdJkzh();
		String secret = jpDdsz.getDdJkmm();
		this.SessionKey = jpDdsz.getDdAqm1();
		this.client = new DefaultTaobaoClient(URL, appkey, secret, "json", 20 * 1000, 20 * 1000);
	}

	/**
	 *自动扫描导单 
	 */
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		int min = jpDdsz.getTbsmsjjg()==null ? 120 : jpDdsz.getTbsmsjjg();
		if(min<120){
			min=120;
		}
		listOrder.addAll(_queryOrders(ptlb, min, ""));
		return listOrder;
	}
	/**
	 *手动扫描导单 
	 */
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		int min = 24*60;
		listOrder.addAll(_queryOrders(ptlb, min, dateStr));
		return listOrder;
	}
	private List<OrderBean> _queryOrders(JpPtLog ptlb,int min,String dateStr) throws Exception {
		String now = dateStr;
		if(StringUtils.isBlank(now)){
			now = VeDate.getStringDate();
		}
		String apppretime = VeDate.getPreMin(now, -60 * 24);
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		long currentPage = 1;//请求的当前页码
		AlitripIeAgentOrderSearchResponse response = null;
		String status = "2";//默认2
		//接口中订单状态Init(0, "初始状态"),BookSuccess(1,"预定成功"),PaySuccess(2,"付款成功"),TicketSuccess(3,"订单成功"),Close(4,"订单关闭");
		String order_status = "PaySuccess";
		if("0".equals(status)){
			order_status = "Init";
		}else if("1".equals(status)){
			order_status = "BookSuccess";
		}else if("2".equals(status)){
			order_status = "PaySuccess";
		}else if("3".equals(status)){
			order_status = "TicketSuccess";
		}else if("all".equals(status)){//查询所有
			order_status = "";
		}
		do {		
			AlitripIeAgentOrderSearchRequest req=new AlitripIeAgentOrderSearchRequest();
			req.setOrderStatus("PaySuccess");//只导入付款成功的订单入库

			req.setCurrentPage(currentPage);
			req.setBeginTime(VeDate.strToDateLong(apppretime));
			req.setEndTime(VeDate.strToDateLong(now));
			ptlb.add("淘宝新接口扫描："+ URL+ " 接口名：AlitripIeAgentOrderSearchRequest 参数："+ req.getTextParams().toString());
			response = execute(req);
	
			ptlb.add("淘宝国际票导单接口返回："+ response.getBody());
			if (!response.isSuccess()) {
				ptlb.add("淘宝国际票导单接口返回错误");
				throw new Exception("出错：" + response.getBody());
			}
			List<com.taobao.api.response.AlitripIeAgentOrderSearchResponse.IeBaseOrderVo> voList = response.getBaseOrderVos();
			if (CollectionUtils.isNotEmpty(voList)) {
				for (int i = 0; i < voList.size(); i++) {
					Long orderid = voList.get(i).getTradeOrderId();
					try {
						IeOrderVo vo = getIeOrder(voList.get(i).getTradeOrderId(),ptlb);
						orderinfo(vo, listOrder, ptlb);
					} catch (Exception e) {
						ptlb.add("订单ID:"+orderid+"请求订单详细失败，失败原因:"+e.getMessage());
					}
				}
			}
			currentPage++;//页码+1
		} while (response.getHasNext()!=null&&response.getHasNext());
		return listOrder;
	}
	private IeOrderVo getIeOrder(Long orderid, JpPtLog ptlb) throws Exception {
		try {
			AlitripIeAgentOrderGetRequest req=new AlitripIeAgentOrderGetRequest();
			req.setTradeOrderId(orderid);//交易订单编号
//			req.setAgentId(1501L);
			AlitripIeAgentOrderGetResponse response = execute(req);	
			ptlb.add("淘宝国际机票查询订单详接口根据订单id查询订单AlitripIeAgentOrderGetRequest："+ orderid);
			ptlb.add("返回："+ response.getBody());
			
			if (response.isSuccess()) {
				IeOrderVo vo = response.getOrderVo();
				return vo;
			} else {
				throw new Exception(response.getMsg());
			}
		} catch (Exception e) {
			ptlb.add("错误："+ e.getMessage());
			throw e;
		}
	}
	
	private void orderinfo(IeOrderVo vo, List<OrderBean> listOrder, JpPtLog ptlb) throws Exception {
		List<IePassgenerVo> passgener_vos = vo.getPassgenerVos(); //乘机人信息集合
		IeItineraryVo itinerary_vo = vo.getItineraryVo();//行程单信息
		IeBaseOrderVo base_order_vo = vo.getBaseOrderVo();//订单基本信息
		String trade_order_id = vo.getTradeOrderId()+"";//交易订单编号
		IeContactsVo contacts_vo = vo.getContactsVo();//联系人信息
		IeItemVo item_vo = vo.getItemVo();//搜索产品信息
		List<IeBookOrderVo> book_order_vos = vo.getBookOrderVos();//预定订单信息
		
		Boolean ifjpfw = base_order_vo.getGoldMedalOrder();//是否金牌服务
		ifjpfw = (ifjpfw!=null) ? ifjpfw : false;

		//淘宝一张订单里面有多个预定信息
		int addcnt = 0;
		ptlb.add("外部订单编号："+trade_order_id+",有"+book_order_vos.size()+"个预定信息");
		
		String bzTmp = "淘宝订单号：" + trade_order_id;
		if(item_vo!=null&&StringUtils.isNotBlank(item_vo.getRestriction())){
			bzTmp+="  退改签："+item_vo.getRestriction();
		}
		if(item_vo!=null&&StringUtils.isNotBlank(item_vo.getBaggageRule())){
			bzTmp+="  行李规则："+item_vo.getBaggageRule();
		}
		Map<String, String> khddMap = new HashMap<String, String>();
		//订单基本信息
		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", trade_order_id);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("NOSX", "");
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("DDLX", "1");
			
		khddMap.put("NXR", StringUtils.trimToEmpty(contacts_vo.getName()));
		khddMap.put("NXSJ", StringUtils.trimToEmpty(contacts_vo.getPhone()));
		khddMap.put("NXDH", StringUtils.trimToEmpty(contacts_vo.getPhone()));	
		String trip_type = base_order_vo.getTripType();// OneWay单程   RoundTrip往返    暂时无联程
		String hclx = "1";//1单程  2往返   3联程
		if("OneWay".equals(trip_type)){
			hclx = "1";
		}else if("RoundTrip".equals(trip_type)){
			hclx = "2";
		}
		if(ifjpfw){
			khddMap.put("WD_DDLX", "金牌服务");
		}
		String xcdh = "";
		String xcdjg = "";
		if(itinerary_vo!=null){
			String postcode = itinerary_vo.getPostcode();//邮政编码
			String address = itinerary_vo.getAddress();//收件地址
			String mobile = itinerary_vo.getMobile();//手机
			String telephone = itinerary_vo.getTelephone();//电话
			String send_type = itinerary_vo.getSendType();//配送方式
			String xcdzt = itinerary_vo.getStatus();//状态
			//分转元
			xcdjg = getLongToStr(itinerary_vo.getPrice());
			xcdjg = Arith.div(NumberUtils.toDouble(xcdjg,0), 100 , 2)+"";
			xcdh = itinerary_vo.getItineraryNo();//行程单号
		}
		//淘宝国际票总佣金
		Long commissionPrice_L = base_order_vo.getEstCommissionPrice();
		Double commissionPrice = Arith.div(commissionPrice_L,100,2);
		ptlb.add("该订单的总佣金为："+commissionPrice);
//		循环计算总的销售价、每个预定信息的销售价	
		double ttP=0;
		for (int i = 0; i < book_order_vos.size(); i++) {
			IeBookOrderVo book_order_vo = book_order_vos.get(i);//预定信息
			double ydTolPri = 0.0; //航段总销售价
			for(int j =0; j<book_order_vo.getBookTicketVos().size();j++){
				IeBookTicketVo book_ticket_vo = book_order_vo.getBookTicketVos().get(j); //票信息
				if(!"Child".equals(book_ticket_vo.getPassengerType())){//非儿童票才参与价格计算
					ydTolPri += book_ticket_vo.getTicketPrice();
				}			
			}
			ttP += ydTolPri;
		}
		ttP = Arith.div(ttP, 100 , 2);
		ptlb.add("该订单的总销售价（除去儿童销售价，后面用来计算佣金）为："+ttP);
		for(int i=0;i<book_order_vos.size();i++){
			Map<String, Map<String, String>> hdMap = new ListOrderedMap();
			Map<String, List<Map<String, String>>> cjrMap = new ListOrderedMap();
			//
			List<Map<String, String>> hdList = new ArrayList<Map<String,String>>();
			
			IeBookOrderVo book_order_vo = book_order_vos.get(i);
			List<IeBookTicketVo> book_ticket_vos = book_order_vo.getBookTicketVos();
			String adt_ticket_price = getLongToStr(book_order_vo.getAdtTicketPrice());
			String chd_icket_price = getLongToStr(book_order_vo.getChdTicketPrice());
			String xs_pj_cj = Arith.div(NumberUtils.toDouble(adt_ticket_price,0), 100 , 2)+"";//成人票面价
			String xs_pj_et = Arith.div(NumberUtils.toDouble(chd_icket_price,0), 100 , 2)+"";//成人票面价
			
			String xs_zdj_str = "";
//			//乘机人信息
			double jlAll = 0;
			double jl = 0;
			boolean hasXcd = false;
			if(StringUtils.isNotBlank(xcdh)){
				hasXcd = true;
			}
			for(int j=0;j<book_ticket_vos.size();j++){
				IeBookTicketVo book_ticket_vo = book_ticket_vos.get(j); //票信息
				Map<String, String> cjr = new HashMap<String, String>();
				String cjrlx = "1";//Adult 成人, Child 儿童, StudentAbroad 留学生
				if("Adult".equals(book_ticket_vo.getPassengerType())){
					cjrlx = "1";
					xs_zdj_str = xs_pj_cj;
				}else if("Child".equals(book_ticket_vo.getPassengerType())){
					cjrlx = "2";
					xs_zdj_str = xs_pj_et;
				}else if("StudentAbroad".equals(book_ticket_vo.getPassengerType())){
					cjrlx ="1";
					xs_zdj_str = xs_pj_cj;
				}
				if(hasXcd&&"1".equals(cjrlx)){//行程单放在第一个成人乘客信息上
					hasXcd = false;
					cjr.put("XCDH",xcdh);
					cjr.put("XS_QDF", xcdjg);
				}
				/**
				 * Passport : "护照",
				 * Hongkong_Macao  :  "港澳通行证",
				 * Taiwan_MTP: "台胞证" //Mainland  Travel  Permit 台湾居民来往大陆通行证
				 * Home_Return_Permit: "回乡证",
				 * Taiwan_Pass: "台湾通行证",
				 * Entry_Taiwan_Permit:"入台证";
				 */
				String zjlx = "ID";//默认ID
				if ("Passport".equals(book_ticket_vo.getPassengerCertType())) {
					zjlx = "PP";
				} else {
					zjlx = "ID";
				}
				cjr.put("CJR", book_ticket_vo.getPassengerName());
				cjr.put("CJRLX",cjrlx);
				cjr.put("ZJLX", zjlx);
				cjr.put("ZJHM", book_ticket_vo.getPassengerCertNo());
				
				double xs_tax = NumberUtils.toDouble(getLongToStr(book_ticket_vo.getTicketTax()));
				xs_tax = Arith.div(xs_tax, 100 , 2);
				double xs_pj = NumberUtils.toDouble(getLongToStr(book_ticket_vo.getTicketPrice()));
				xs_pj = Arith.div(xs_pj, 100 , 2);
				double ticket_price = xs_pj;
				double xs_je = NumberUtils.toDouble(getLongToStr(book_ticket_vo.getTicketPrice()+book_ticket_vo.getTicketTax()));	
				xs_je = Arith.div(xs_je, 100 , 2);
				double xs_yj = 0.0;
				if("1".equals(cjrlx)){//只有成人票参与佣金计算
					if(ttP!=0.0){
						if(j==(book_ticket_vos.size()-1)){
							jl = Arith.sub(commissionPrice, jlAll);
						}else{
							double bl=Arith.div(ticket_price, ttP);
							jl = Arith.round(Arith.mul(commissionPrice, bl), 2);
							jlAll = Arith.add(jlAll, jl);
						}
						
					}
					//销售金额要减去佣金
					xs_je = Arith.round(Arith.sub(xs_je, jl), 2);
					xs_pj = Arith.sub(xs_je, xs_tax);
					xs_yj = jl;
				}
				
				if(StringUtils.isBlank(xs_zdj_str)||"0.0".equals(xs_zdj_str)){
					xs_zdj_str = ticket_price+"";
				}
				cjr.put("XS_TAX", xs_tax+"");
				cjr.put("XS_JSF", "0");
				cjr.put("XS_ZDJ", xs_zdj_str);
				cjr.put("XS_PJ", ticket_price+"");
				cjr.put("XS_JE", xs_je+"");
				cjr.put("XS_YJ", xs_yj + "");
				
				String pnr_id = getLongToStr(book_ticket_vo.getPnrId());//对应PNR的ID
				List<Map<String, String>> list = null;
				if(cjrMap.get(pnr_id)==null){
					list = new ArrayList<Map<String,String>>();
					cjrMap.put(pnr_id, list);
					
				}else{
					list = cjrMap.get(pnr_id);
				}
				cjr.put("SXH", list.size()+1+"");
				list.add(cjr);
			}
			//目前只支持单程或者往返
			List<IeBookFlightVo> book_flight_vos = book_order_vo.getBookFlightVos();
//			String outbound_dep_time = getDateToStr(base_order_vo.getOutboundDepTime());//第一程出发时间
//			String outbound_arr_time = getDateToStr(base_order_vo.getOutboundArrTime());//第一程到达时间
//			String dep_airport = base_order_vo.getDepAirport();//出发机场
//			String arr_airport = base_order_vo.getArrAirport();//到达机场
			//解析航段信息
			for(int j=0;j<book_flight_vos.size();j++){
				Map<String, String> hd = new HashMap<String, String>();
				IeBookFlightVo book_flight_vo = book_flight_vos.get(j);//预定航班信息
				String dep_airport = book_flight_vo.getDepAirport();//出发机场
				String arr_airport =  book_flight_vo.getArrAirport();//到达机场
				String dep_time = getDateToStr(book_flight_vo.getDepTime());//起飞时间
				String arr_time = getDateToStr(book_flight_vo.getArrTime());//到达时间
				String segment_rph = getLongToStr(book_flight_vo.getSegmentRph()); //第几段
				String flight_number = book_flight_vo.getFlightNumber(); //实际承运航班号;
				String operating_flight_number = book_flight_vo.getOperatingFlightNumber();//共享航班号
				String operating_air_line = book_flight_vo.getOperatingAirLine();//共享航空公司
				Boolean code_share = book_flight_vo.getCodeShare();//是否共享航班
				String marketing_airline = book_flight_vo.getMarketingAirline();//市场航司
				String flight_cabin = book_flight_vo.getFlightCabin();//舱位
				String equip_type = book_flight_vo.getEquipType();//飞机机型
				String hbh = flight_number;
				if(code_share!=null&&code_share){//如果是共享航班
					hbh = "*"+flight_number;
					hd.put("OP_HKGS", operating_flight_number);//实际承运航班号;
				}
				hd.put("XS_HBH", hbh);
				hd.put("CFSJ", dep_time);
				hd.put("DDSJ", arr_time);
				hd.put("CFCITY", dep_airport);
				hd.put("DDCITY", arr_airport);
				hd.put("XS_CW", flight_cabin);
				hd.put("FJJX", equip_type);
				hd.put("TMP_KP_HKGS", marketing_airline);
				if(item_vo!=null&&StringUtils.isNotBlank(item_vo.getRestriction())){
					hd.put("XS_TGQ", item_vo.getRestriction());
				}		
				//目前淘宝国际票航段和票信息没有关联
				hdList.add(hd);
			}
			//解析PNR信息，因为淘宝订单有多个PNR
			List<com.taobao.api.domain.IeBookPnrVo> book_pnr_vos = book_order_vo.getBookPnrVos();
			ptlb.add("第"+(i+1)+"个预定信息中,有"+book_order_vos.size()+"个PNR");
			for(int j=0;j<book_pnr_vos.size();j++){
				//一个PNR产生一张订单
				OrderBean orderBean = new OrderBean();
				com.taobao.api.domain.IeBookPnrVo book_pnr_vo = book_pnr_vos.get(j);
				String id = getLongToStr(book_pnr_vo.getId());
				String pnr = book_pnr_vo.getPnrNo();   //PNR号
				String pnrType = book_pnr_vo.getPnrType();//PNR类型
				Map<String, String> khddMap_new = new HashMap<String, String>(khddMap);
				List<Map<String, String>> cjrList = new ArrayList<Map<String,String>>();
				khddMap_new.put("XS_PNR_NO", pnr);
				khddMap_new.put("DDXH", j+1+"");
				String kp_hkgs = StringUtils.trimToEmpty(hdList.get(0).get("TMP_KP_HKGS"));
				khddMap_new.put("KP_HKGS", kp_hkgs);
				orderBean.setKhddMap(khddMap_new);
				for(String key : cjrMap.keySet()){
					if(key.equals(id)){//PNR相同的乘机人存放进一张订单
						cjrList.addAll(cjrMap.get(key));
					}
				}
				//key为0表示没有传pnr_id节点的乘机人,这些乘机人表示所有PNR下面的。
				if(cjrMap.get("0")!=null){
					cjrList.addAll(cjrMap.get("0"));
				}
				double xs_yj_hj =0.0;
				for(int x=0;x<cjrList.size();x++){
					double xs_yj = NumberUtils.toDouble(StringUtils.trimToEmpty((String)cjrList.get(x).get("XS_YJ")), 0);
					xs_yj_hj = Arith.add(xs_yj_hj,xs_yj);
					khddMap_new.put("XS_YJ", xs_yj_hj+"");
				}
				orderBean.setCjrList(cjrList);
				orderBean.setHdList(hdList);
				listOrder.add(orderBean);
				addcnt++;
			}
		}
		ptlb.add("外部订单编号："+trade_order_id+"对应系统订单数量："+addcnt);
	}
	//将Long值转换成字符串
	private String getLongToStr(Long l){
		return l == null ? "0" : l+"";
	}
	private String getDateToStr(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	private String getDateShort(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}


	private <T extends TaobaoResponse> T execute(TaobaoRequest<T> paramTaobaoRequest) throws Exception {
		int trycount = 3;
		Exception eee = null;
		while (trycount > 0) {
			try {
				return client.execute(paramTaobaoRequest, SessionKey);
			} catch (Exception e) {
				eee = e;
				e.printStackTrace();
				String error = StringUtils.trimToEmpty(e.getMessage());
				Map m = paramTaobaoRequest.getTextParams();
				if (error.indexOf("ConnectException") >= 0 || error.indexOf("SocketTimeoutException") >= 0) {
					trycount--;
					continue;
				} else {
					break;
				}
			}
		}
		if (eee == null) {
			eee = new Exception("与淘宝通讯异常");
		}
		throw eee;
	}

	/**
	 * 回填票号
	 * 
	 */
	@Override
	public boolean orderOutTicket(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		AlitripIeAgentTicketIssueRequest req=new AlitripIeAgentTicketIssueRequest();
		IeIssueTicketVO vo = new IeIssueTicketVO();
		List<IePassengerTicketVO> passengerTicketVos = new ArrayList<IePassengerTicketVO>();		
		List<IeBookPnrVo> updatePnrVos = new ArrayList<IeBookPnrVo>();
		
		List<JpKhdd> jpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpJpList = pjb.getJpJpListMap();
		
		Map<String,IePassengerTicketVO> passMap = new HashMap<String, IePassengerTicketVO>();
		for(int i=0;i<jpKhddList.size();i++){
			JpKhdd oneJpKhdd = jpKhddList.get(i);
			String ddbh = oneJpKhdd.getDdbh();
			IeBookPnrVo bookPnrVo = new IeBookPnrVo();
			bookPnrVo.setPnrNo(oneJpKhdd.getXsPnrNo());
			updatePnrVos.add(bookPnrVo);
			List<JpJp> oneJpList = jpJpList.get(ddbh);
			for(int j=0;j<oneJpList.size();j++){
				JpJp oneJp = oneJpList.get(j);
				IePassengerTicketVO passengerTicketVo = passMap.get(oneJp.getCjr()+"_"+oneJp.getCjrlx());
				if(passengerTicketVo ==null){
					passengerTicketVo = new IePassengerTicketVO();
					passengerTicketVo.setPassengerName(oneJp.getCjr());
					passengerTicketVo.setCertNo(oneJp.getZjhm());
					List<String> ticketNos = new ArrayList<String>();
					passengerTicketVo.setTicketNos(ticketNos);
				}
				passengerTicketVo.getTicketNos().add(oneJp.getTkno());
			}
		}
		for(String key : passMap.keySet()){
			passengerTicketVos.add(passMap.get(key));
		}
		vo.setPassengerTicketVos(passengerTicketVos);
		vo.setUpdatePnrVos(updatePnrVos);
		vo.setBookOrderId(NumberUtils.toLong(pjb.getWdbh()));
		req.setIssueTicketVO(vo);
		ptlb.add("淘宝票号回填请求："+ URL+ " 接口名：AlitripIeAgentTicketIssueRequest 参数："+ req.getTextParams().toString());
		
		AlitripIeAgentTicketIssueResponse response = execute(req);
		String body = StringUtils.trimToEmpty(response.getBody());
		if (StringUtils.isBlank(body)) {
			body = StringUtils.trimToEmpty(response.getMsg());
		}
		ptlb.add("票号回填返回："+ body);
		if (response.getTicketSuccess()!=null&&response.getTicketSuccess()) {
			return true;
		} else {
			try {
				ptlb.add("回填失败，检索订单详细，检查淘宝上是否已经解冻");
				IeOrderVo ie = getIeOrder(NumberUtils.toLong(pjb.getWdbh()), ptlb);
				if(ie!=null&&"TicketSuccess".equals(ie.getBaseOrderVo().getOrderStatus())){
					ptlb.add("订单已经出票成功了！");
					return true;
				}
			} catch (Exception e) {
				ptlb.add("获得订单详细异常");
			}
			throw new Exception(body);
		}	
	}
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		List<OrderBean> list = new ArrayList<OrderBean>();
		try {
			IeOrderVo vo = getIeOrder(NumberUtils.toLong(wbdh, 0l),ptlb);
			orderinfo(vo, list, ptlb);
		} catch (Exception e) {
			ptlb.add("订单ID:"+wbdh+"请求订单详细失败，失败原因:"+e.getMessage());
		}
		return list;
	}
	/**
	 * 解析政策说明字符串，并将其存放进khddMap里面
	 */
	private static void resolveZCSM(String zcsm,Map<String,String> khddMap){
//		String tfid = ""; //投放ID
		String zcfd = ""; //政策返点
		String wdld = ""; //网店留点
		String wdlq = ""; //网店留钱
		String wdjz = ""; //网店降折
		String wdpmjj = ""; //网店票面加价
		String wdldlx = ""; //网店留点类型
		try {
			List<String> tempArr = new ArrayList<String>();
			//八千翼(22066793,1251830911,6.5,1400.0,0.8)留(-3.0,-4.0,0.0,0.0,1)[21 10:44]
			if(StringUtils.isNotBlank(zcsm)){
				Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");      
				Matcher matcher = pattern.matcher(zcsm);
				
				while(matcher.find())  {
					tempArr.add(matcher.group());
				}
			}
			if(tempArr.size()==2){
				String[] str1Arr = tempArr.get(0).split(",");
				String[] str2Arr = tempArr.get(1).split(",");
//				tfid = str1Arr[1]; //投放ID
				zcfd = str1Arr[2]; //政策返点
				wdld = str2Arr[0]; //网店留点
				wdlq = str2Arr[1]; //网店留钱
				wdjz = str2Arr[2]; //网店降折
				wdpmjj = str2Arr[3]; //网店票面加价
				wdldlx = str2Arr[4]; //网店留点类型
				khddMap.put("ZC_FD", zcfd);
				khddMap.put("ZC_LD", wdld);
				khddMap.put("ZC_LQ", wdlq);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			PlatUtils.ptlog(TaobaoGy2.plat, "", "外部单号"+khddMap.get("GY_DDBH")+",解析政策说明字符串报错"+zcsm);
		}
	}
	/**
	 * 解析政策代码，取出投放方案代码翻入TFID节点中
	 * V50_GJDYIHKWEE314635422
	 * @return
	 */
	private static void resolveZCDM(String zcdm,Map<String,String> khddMap){
		String zc_qd = "";
		if(StringUtils.isBlank(zcdm)){//不能为空
			return;
		}
		if(!StringUtils.startsWith(zcdm, "V")&&!StringUtils.startsWith(zcdm, "v")){//必须V开头
			return;
		}
		if(StringUtils.indexOf(zcdm, "_")==-1){//必须包含下划线
			return;
		}
		int index = zcdm.indexOf("_");
		zc_qd = zcdm.substring(index+1,index+2);
		khddMap.put("ZC_QD", zc_qd);
	}

}
