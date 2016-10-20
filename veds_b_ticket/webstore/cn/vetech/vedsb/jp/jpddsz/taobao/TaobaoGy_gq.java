package cn.vetech.vedsb.jp.jpddsz.taobao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.jpddsz.JpGqdGySuper;
import cn.vetech.vedsb.jp.jpddsz.taobao.model.SearchGqdRequest;
import cn.vetech.vedsb.jp.jpddsz.taobao.model.SearchGqdResponse;
import cn.vetech.vedsb.jp.jpddsz.taobao.model.TicketChangeDealGy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.TripOrder;
import com.taobao.api.request.AlitripSellerModifyListRequest;
import com.taobao.api.response.AlitripSellerModifyListResponse;
import com.taobao.api.response.AlitripSellerModifyListResponse.Flight;
import com.taobao.api.response.AlitripSellerModifyListResponse.Passenger;
import com.taobao.api.response.AlitripSellerModifyListResponse.SyncOrderDo;

public class TaobaoGy_gq extends JpGqdGySuper{
	public static Map<String,TripOrder> tmpMap = new HashMap<String, TripOrder>();
	private String appkey;
	private String URL;
	private String SessionKey;
	private TaobaoClient client;
	private JpDdsz jpDdsz;
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	public TaobaoGy_gq(JpDdsz jpDdsz){
		this.jpDdsz = jpDdsz;
		this.URL = jpDdsz.getDdJkdz();
		this.appkey = jpDdsz.getDdJkzh();
		String secret = jpDdsz.getDdJkmm();
		this.SessionKey = jpDdsz.getDdAqm1();
		this.client = new DefaultTaobaoClient(URL, appkey, secret, "json", 20 * 1000, 20 * 1000);
	}

//	@Override
//	public Map<String, String> gq(JpPtLog ptlb, Map<String, Object> gqParams) {
//		Map<String,String> retMap = new HashMap<String, String>();
//		JpGqd jpGqd = (JpGqd)gqParams.get("jpGqd");
//		
//		String gygqdh = jpGqd.getWbdh();
//		List<JpGqdCjr> cjrList = jpGqd.getCjrList();
//		List<JpGqdHd> hdList = jpGqd.getHdList();
//		
//		String passenger = "";
//		String travelRange = "";
//		String ticketNo = "";
//		for(int i=0;i<cjrList.size();i++){
//			JpGqdCjr jpGqdCjr = cjrList.get(i);
//			passenger+="|"+jpGqdCjr.getCjr();
//
//			ticketNo+="|"+jpGqdCjr.getTkno();
//		}
//		for(int i=0;i<hdList.size();i++){
//			JpGqdHd jpGqdHd = hdList.get(i);
//			travelRange+="|"+jpGqdHd.getCfcity()+jpGqdHd.getDdcity();
//		}
//		passenger = passenger.substring(1);
//		travelRange = travelRange.substring(1);
//		ticketNo = ticketNo.substring(1);
//		HandleGqdRequest req = new HandleGqdRequest();
//		req.setUsername(jpDdsz.getGqdlzh());
//		req.setChangeNo(gygqdh);
//		req.setPassenger(passenger);
//		req.setTravelRange(travelRange);
//		req.setTicketNo(ticketNo);
//		String shbh = jpDdsz.getShbh();
//		req.setZgs(shbh);
//		try {
//			String url = jpDdsz.getGqddz()+"/crawlorder/handleGqd";
//			String data = XmlUtils.toXml(req);
//			ptlb.add("淘宝改签单操作接口："+ url+ " 接口名：handleGqd 参数：username="+jpDdsz.getGqdlzh()+";shbh="+shbh+";请求参数:"+data);
//			Map<String,String> parmas = new HashMap<String, String>();
//			parmas.put("data", data);
//			String retXml = HttpClientUtil.doPost(url, parmas, "UTF-8");
//			ptlb.add("淘宝改签单操作接口返回："+retXml);
//			HandleGqdResponse res = (HandleGqdResponse) XmlUtils.fromXml(retXml, HandleGqdResponse.class);
//			if("1".equals(res.getState())){
//				retMap.put("status", "true");
//			}else{
//				//这里本来应该插入登录失败邮件提醒功能
////				try {
////					if("-2".equals(res.getState())){//登录失败
////							Tmp_service tmp_service = (Tmp_service)WebAppUtil.getComponentInstance("tmp_service", sc);
////							T_email_sz_dao t_email_sz_dao = (T_email_sz_dao)WebAppUtil.getComponentInstance("t_email_sz_dao", sc);
////							Ve_yhb_dao ve_yhb_dao = (Ve_yhb_dao)WebAppUtil.getComponentInstance("ve_yhb_dao", sc);	
////							MessageWarnning msgSender = new MessageWarnning(ve_yhb, tmp_service, t_email_sz_dao);
////							//登录失败
////							msgSender.msgSendForTaobao(t_ptzc_zh_gy.getCompid());
////				    }
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
//				retMap.put("status", "false");
//				retMap.put("message", "接口返回错误原因："+res.getErrMsg());
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			retMap.put("status", "false");
//			retMap.put("message", "操作改签单出错，错误原因"+e.toString());
//		}
//		return retMap;
//	}
	public List<TicketChangeBean> queryOrders_gq2(JpPtLog ptlb)
			throws Exception {
		String dateStr = VeDate.getStringDateShort();
//		String dateStr = "2016-03-01";
		SearchGqdRequest req = new SearchGqdRequest();
		req.setUsername(jpDdsz.getGqdlzh());
		req.setDateStr(dateStr);
		String shbh = jpDdsz.getShbh();
		req.setZgs(shbh);
		List<TicketChangeBean> gqList = null;
		try {
//			String cs11000_1 = Function.acsb("11000", zgs, "YDHM", "http://127.0.0.1:8081");
			//http://192.168.203.71:8152
//			String url = "http://localhost:8081/crawlorder/searchGqd";
			String url = jpDdsz.getGqddz()+"/crawlorder/searchGqd";

			String data = XmlUtils.toXml(req);
			ptlb.add("淘宝改签单扫描："+ url+ " 接口名：searchGqd 参数：username="+jpDdsz.getGqdlzh()+";shbh="+shbh+";请求参数:"+data);
			Map<String,String> parmas = new HashMap<String, String>();
			parmas.put("data", data);
			String retXml = HttpClientUtil.doPost(url, parmas, "UTF-8");
			ptlb.add("淘宝改签单扫描接口返回："+retXml);
			gqList = parseTicketChangeBeanList2(retXml,ptlb);
		}  catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return gqList;
	}
	/**
	 * 自动扫描
	 */
	@Override
	public List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb)
			throws Exception {

		List<TicketChangeBean> list = new ArrayList<TicketChangeBean>();
		String nowtime = VeDate.getStringDate();
		//5分钟扫描一次
		String pretime = PRETIMEMAP.get(appkey+URL+"GQ");
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 5).compareTo(nowtime) >= 0) {
			return list;
		}
		PRETIMEMAP.put(appkey+URL+"GQ", nowtime);
		List<TicketChangeBean> list1 = _queryOrders(ptlb, nowtime, 1l);
		List<TicketChangeBean> list2 = _queryOrders(ptlb, nowtime, 2l);
		List<TicketChangeBean> list3 = _queryOrders(ptlb, nowtime, 3l);
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		return list;
	}
	/**
	 * 手动触发
	 */
	@Override
	public List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb,String dateStr)
			throws Exception {
		List<TicketChangeBean> list = new ArrayList<TicketChangeBean>();
		List<TicketChangeBean> list1 = _queryOrders(ptlb, dateStr, 1l);
		List<TicketChangeBean> list2 = _queryOrders(ptlb, dateStr, 2l);
		List<TicketChangeBean> list3 = _queryOrders(ptlb, dateStr, 3l);
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		return list;
	}	
	/**
	 * 查询 status= 1 初始  2 已改签 3 已拒绝 
	 * @param ptlb
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public List<TicketChangeBean> _queryOrders(JpPtLog ptlb,String dateStr,long status)
		throws Exception {
		List<TicketChangeBean> list = new ArrayList<TicketChangeBean>();
		int smsjfw = 180;//扫描时间范围 单位小时	
		Date modifyDateEnd = VeDate.formatToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		Date modifyDateStart = VeDate.getPreMin(modifyDateEnd, -smsjfw);
		try {
			List<SyncOrderDo> sodlist = null;
			long currentPage = 1l;
			long pagesize=100l;
			long totalItem = pagesize;
			do {
				AlitripSellerModifyListRequest req = new AlitripSellerModifyListRequest();
				req.setModifyDateStart(modifyDateStart);
				req.setModifyDateEnd(modifyDateEnd);
//				req.setApplyDateStart(applyDateStart);
//				req.setApplyDateEnd(applyDateEnd);
				req.setStatus(status);
				req.setPageSize(pagesize);
				req.setCurrentPage(currentPage);
				ptlb.add("淘宝改签扫描【状态："+status+"】:"+ URL+ " 接口名：AlitripSellerModifyListRequest 参数："+ req.getTextParams().toString());
				AlitripSellerModifyListResponse response = execute(req);
				ptlb.add("请求淘宝改签扫描接口返回【状态："+status+"】："+response.getBody());
				sodlist = response.getOrderList();
				if(sodlist==null){
					return list;
				}
				list.addAll(parseTicketChangeBeanList(sodlist, status+"", ptlb));
				totalItem = response.getTotalItem()==null?0:response.getTotalItem();
				currentPage++;
			} while (totalItem >=pagesize);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求淘宝改签扫描异常："+e.getMessage());
		}
		return list;
	}
	/**
	 * 按PNR合并改签单对象
	 * @param sodlist
	 * @param wbgqzt
	 * @param ptlb
	 * @return
	 */
	private List<TicketChangeBean> parseTicketChangeBeanList(List<SyncOrderDo> sodlist,String wbgqzt,JpPtLog ptlb){
		List<TicketChangeBean> list = new ArrayList<TicketChangeBean>();
		Map<String,TicketChangeBean> gqdMxListTmpMap = new HashMap<String, TicketChangeBean>();
		for(int i=0;i<sodlist.size();i++){
			SyncOrderDo syncOrderDo = sodlist.get(i);
			TicketChangeBean one = _parseTicketChangeBeanList(syncOrderDo, wbgqzt, ptlb);
			String xs_pnr_no = VeStr.getValue(one.getGqdMap(),"XS_PNR_NO");//PNR
			String gq_xs_pnr_no = VeStr.getValue(one.getGqdMap(),"GQ_XS_PNR_NO");//PNR
			String wbdh = VeStr.getValue(one.getGqdMap(),"WBDH");//外部改签单号
			String gq_xsfy_tmp1 = VeStr.getValue(one.getGqdMap(),"GQ_XSFY");//改期费用
			String gq_xsscfy_tmp1 = VeStr.getValue(one.getGqdMap(),"GQ_XSSCFY");//升舱费用
			List<Map<String, Object>> gqdMxList = one.getGqdMxMapList();
			String key = gq_xs_pnr_no+"_"+xs_pnr_no+"_"+wbdh;//不同PNR的不合并
			TicketChangeBean newTcBean = gqdMxListTmpMap.get(key);
			if(newTcBean==null){
				newTcBean = new TicketChangeBean();
				newTcBean.setGqdMap(one.getGqdMap());
				newTcBean.setHdMapList(one.getHdMapList());
				newTcBean.setGqdMxMapList(new ArrayList<Map<String,Object>>());
				gqdMxListTmpMap.put(key, newTcBean);
				list.add(newTcBean);
			}else{
				String gq_xsfy_tmp2 = VeStr.getValue(newTcBean.getGqdMap(),"GQ_XSFY");//改期费用
				String gq_xsscfy_tmp2 = VeStr.getValue(newTcBean.getGqdMap(),"GQ_XSSCFY");//升舱费用
				String gq_xsfy_new = Arith.add(NumberUtils.toDouble(gq_xsfy_tmp1, 0), NumberUtils.toDouble(gq_xsfy_tmp2, 0))+"";
				String gq_xsscfy_new = Arith.add(NumberUtils.toDouble(gq_xsscfy_tmp1, 0), NumberUtils.toDouble(gq_xsscfy_tmp2, 0))+"";
				newTcBean.getGqdMap().put("GQ_XSFY", gq_xsfy_new);
				newTcBean.getGqdMap().put("GQ_XSSCFY", gq_xsscfy_new);
			}
			newTcBean.getGqdMxMapList().addAll(gqdMxList);
		}
		return list;
	}
	/**
	 * 解析单个SyncOrderDo对象
	 * @param syncOrderDo
	 * @param wbgqzt
	 * @param ptlb
	 * @return
	 */
	private TicketChangeBean _parseTicketChangeBeanList(SyncOrderDo syncOrderDo,String wbgqzt,JpPtLog ptlb){
		long applyid = syncOrderDo.getApplyId();//改签申请单ID
		long orderid = syncOrderDo.getOrderId();//订单ID
		String cabin = StringUtils.trimToEmpty(syncOrderDo.getCabin());//改签后舱位
		String last_cabin = StringUtils.trimToEmpty(syncOrderDo.getLastCabin());//改签前舱位
		String memo = syncOrderDo.getMemo();//改签备注
		String modify_fee = FenToYuan(syncOrderDo.getModifyFee()+"");//改签费
		String upgrade_fee = FenToYuan(syncOrderDo.getUpgradeFee()+"");//升舱费
		
		Flight modifyBeforeFlight = syncOrderDo.getModifyBeforeFlight(); //改签前航班
		Flight modifyAfterFlight = syncOrderDo.getModifyAfterFlight();//改签后航班
		Passenger passenger = syncOrderDo.getPassenger();//乘机人信息
		
		String bf_AirLineCode = modifyBeforeFlight.getAirLineCode();//改签前航司二字码
		String bf_DepAirport = modifyBeforeFlight.getDepAirport();//改签前出发机场三字码
		String bf_ArrAirport = modifyBeforeFlight.getArrAirport();//改签前到达机场三字码
		String bf_DepDate = modifyBeforeFlight.getDepDate();//改签前出发时间
		String bf_FlightNo = StringUtils.trimToEmpty(modifyBeforeFlight.getFlightNo());//改签前航班号

		String af_AirLineCode = modifyAfterFlight.getAirLineCode();//改签后航司二字码
		String af_DepAirport = modifyAfterFlight.getDepAirport();//改签后出发机场三字码
		String af_ArrAirport = modifyAfterFlight.getArrAirport();//改签后到达机场三字码
		String af_DepDate = modifyAfterFlight.getDepDate();//改签后出发时间
		String af_FlightNo = modifyAfterFlight.getFlightNo();//改签后航班号
		
		String birthday = passenger.getBirthday();//乘客生日
		String certNum = passenger.getCertNum();//证件号
		String passengerName = passenger.getPassengerName();//乘客姓名
		String pnr = passenger.getPnr();//PNR
		String ticket_no = StringUtils.trimToEmpty(passenger.getTicketNo());//改签后票号
		String old_ticket_no = StringUtils.trimToEmpty(passenger.getOldTicketNo());//改签后票号

		TicketChangeBean tcBean = new TicketChangeBean();
		List<Map<String,Object>> hdMapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> hdMap = new HashMap<String,Object>();
		hdMap.put("O_XS_HBH", bf_FlightNo);
		hdMap.put("N_XS_HBH", af_FlightNo);
		hdMap.put("O_XS_CW", last_cabin);
		hdMap.put("N_XS_CW", cabin);
		bf_DepDate = StringUtils.substring(bf_DepDate, 0, 19);
		af_DepDate = StringUtils.substring(af_DepDate, 0, 19);
		hdMap.put("O_CFSJ", bf_DepDate);
		hdMap.put("N_CFSJ", af_DepDate);
		hdMap.put("CFCITY", bf_DepAirport);
		hdMap.put("DDCITY", bf_ArrAirport);
		hdMapList.add(hdMap);
		tcBean.setHdMapList(hdMapList);
		
		String hkgs = StringUtils.substring(bf_FlightNo.replaceAll("\\*", ""), 0, 2);
		Map<String,Object> gqdMap = new HashMap<String, Object>();
		gqdMap.put("SHBH", jpDdsz.getShbh());
		gqdMap.put("WBDDBH", orderid+"");
		gqdMap.put("HKGS", hkgs);
		gqdMap.put("WBDH", applyid+"");
		gqdMap.put("DDYH", jpDdsz.getDdUserid());
		gqdMap.put("DDBM", jpDdsz.getDdBmid());
		
		gqdMap.put("WBGQZT", wbgqzt);
		String gqzt = "0"; //默认0  改签状态  0待确认  1已确认 3改签中 4已改签 7客户消 8已取消
		String gqpnr = "";
		if("1".equals(wbgqzt)){
			gqzt = "0";
		}else if("2".equals(wbgqzt)){
			gqzt = "4";
			gqpnr = pnr;
			pnr = "";
		}else if("3".equals(wbgqzt)){
			gqzt = "8";
		}
		gqdMap.put("GQ_XS_PNR_NO",gqpnr);
		gqdMap.put("GQ_CG_PNR_NO", gqpnr);
		gqdMap.put("XS_PNR_NO",pnr);
		gqdMap.put("CG_PNR_NO", pnr);
		gqdMap.put("GQZT", gqzt);
		String gqlx = "2";//默认升舱
		if(!bf_DepDate.equalsIgnoreCase(af_DepDate)&&StringUtils.isNotBlank(af_DepDate)){//如果改签前后时间不相同则表示改期
			gqlx = "1";
		}
		gqdMap.put("GQLX", gqlx);//默认改签  1改期、2升舱
		gqdMap.put("GQ_XSFY", modify_fee);//改签费用
		gqdMap.put("GQ_XSSCFY", upgrade_fee);//升舱费用
		if(StringUtils.isBlank(memo)){
			memo = "其他改签原因";
		}
		gqdMap.put("GQYY", memo);//淘宝改签单默认
		tcBean.setGqdMap(gqdMap);
		
		List<Map<String,Object>> gqdMxMapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> gqdMxMap = new HashMap<String, Object>();
		gqdMxMap.put("CJR", passengerName);
//		gqdMxMap.put("ZJHM", certNum);
		gqdMxMap.put("GQTKNO", ticket_no);
		gqdMxMap.put("TKNO", old_ticket_no);
		gqdMxMap.put("GQ_XSFY", modify_fee);//改签费用
		gqdMxMap.put("GQ_XSSCFY", upgrade_fee);//升舱费用
		gqdMxMapList.add(gqdMxMap);	
		tcBean.setGqdMxMapList(gqdMxMapList);
		return tcBean;
	}
	private List<TicketChangeBean> parseTicketChangeBeanList2(String retXml,JpPtLog ptlb){
		SearchGqdResponse response = (SearchGqdResponse) XmlUtils.fromXml(retXml, SearchGqdResponse.class);
		if(!"0".equals(response.getState())){
			ptlb.add(response.getErrMsg());
			return null;
		}
		List<TicketChangeDealGy> list = response.getGqdList();
		List<TicketChangeBean> tcdBeanList = new ArrayList<TicketChangeBean>();
		for(int i=0;i<list.size();i++){
			try {
				TicketChangeDealGy one = list.get(i);
				String orderNo = one.getOrderNo(); //原订单编号
				String changeNo = one.getChangeNo();//原改签单号
//				String changeType = one.getChangeType();//原改签类型  1改期  2升舱
//				String changeStatus = one.getChangeStatus();//待确认、已支付待办理，传状态值
//				String changeReason = one.getChangeReason();//改签原因
				String passenger = StringUtils.trimToEmpty(one.getPassenger());//乘机人姓名 格示示例：乘机人名1｜乘机人名2  
				String travelRange = StringUtils.trimToEmpty(one.getTravelRange());//航程 WUHPEK|PEKSHA  
//				String ticketNo = StringUtils.trimToEmpty(one.getTicketNo());//票号 格示示例：999-1234567890|999-1234567891
				String fromDatetime = StringUtils.trimToEmpty(one.getFromDatetime());// 改签后出发时间 格示示例：2013-01-01  12:00|2013-01-03  13:00
//				String toDatetime = StringUtils.trimToEmpty(one.getToDatetime());// 改期后到达时间  格示示例：2013-01-01  12:00|2013-01-03  13:00  
				String newFlightNo = StringUtils.trimToEmpty(one.getNewFlightNo());//改签后航班号  格示示例：CA1232|CA2345  
				String newSeatClass = StringUtils.trimToEmpty(one.getNewSeatClass());// 改签后舱位  格示示例：Y|Y  
//				String planeType = StringUtils.trimToEmpty(one.getPlaneType());//  飞机机型  格式示例：738｜319 
//				String tofromterminal = StringUtils.trimToEmpty(one.getTofromterminal());//  出发航站楼 格式示例：T2|A  
//				String terminal = StringUtils.trimToEmpty(one.getTerminal());// 到达航站楼   格式示例：A|T2
//				String gaiqianRetirement = one.getGaiqianRetirement();  //退改签规定
//				String payment = one.getPayment();//支付方式  传入CPS的支付方式的编号。如312013301，ASMS里设置对应关系进行匹配。  
//				String paymentTransaction = one.getPaymentTransaction();//交易流水号 
//				String ideType = StringUtils.trimToEmpty(one.getIdeType());//证件类型
//				String ideNo = StringUtils.trimToEmpty(one.getIdeNo());//证件号码
//				String n_ideType = StringUtils.trimToEmpty(one.getN_ideType());//新证件类型
//				String n_ideNo = StringUtils.trimToEmpty(one.getN_ideNo());//新证件号码
				String[] passengerArr = passenger.split("\\|",-1);
				String[] travelRangeArr = travelRange.split("\\|",-1);
//				String[] ticketNoArr = ticketNo.split("|",-1);
				String[] fromDatetimeArr = fromDatetime.split("\\|",-1);
//				String[] toDatetimeArr = toDatetime.split("|",-1);
				String[] newFlightNoArr = newFlightNo.split("\\|",-1);
				String[] newSeatClassArr = newSeatClass.split("\\|",-1);
//				String[] planeTypeArr = planeType.split("|",-1);
//				String[] tofromterminalArr = tofromterminal.split("|", -1);
//				String[] terminalArr = terminal.split("|",-1);
//				String[] ideTypeArr = ideType.split("|",-1);
//				String[] ideNoArr = ideNo.split("|",-1);
//				String[] n_ideTypeArr = n_ideType.split("|",-1);
//				String[] n_ideNoArr = n_ideNo.split("|",-1);
				TicketChangeBean tcBean = new TicketChangeBean();
				List<Map<String,Object>> hdMapList = new ArrayList<Map<String,Object>>();
				for(int j=0;j<travelRangeArr.length;j++){
					Map<String,Object> hdMap = new HashMap<String,Object>();
					hdMap.put("O_XS_HBH", "");
					hdMap.put("N_XS_HBH", newFlightNoArr[j]);
					hdMap.put("O_XS_CW", "");
					hdMap.put("N_XS_CW", newSeatClassArr[j]);
					hdMap.put("O_CFSJ", "");
					hdMap.put("N_CFSJ", fromDatetimeArr[j]);
					hdMap.put("CFCITY", travelRangeArr[j].substring(0,3));
					hdMap.put("DDCITY", travelRangeArr[j].substring(3,6));
					hdMapList.add(hdMap);
				}
				tcBean.setHdMapList(hdMapList);
				Map<String,Object> gqdMap = new HashMap<String, Object>();
				gqdMap.put("SHBH", jpDdsz.getShbh());
				gqdMap.put("GQZT", "0");
				gqdMap.put("WBDDBH", orderNo);
				gqdMap.put("WBDH", changeNo);
				gqdMap.put("DDYH", jpDdsz.getDdUserid());
				gqdMap.put("XS_PNR_NO","");
				gqdMap.put("GQLX", "2");//默认改签  1改期、2升舱
				gqdMap.put("GQYY", "其他改签原因");//淘宝改签单默认
				gqdMap.put("GQ_XS_PNR_NO", "");
				tcBean.setGqdMap(gqdMap);
				
				List<Map<String,Object>> gqdMxMapList = new ArrayList<Map<String,Object>>();
				for(int j=0;j<passengerArr.length;j++){
					Map<String,Object> gqdMxMap = new HashMap<String, Object>();
					gqdMxMap.put("CJR", passengerArr[j]);
					gqdMxMapList.add(gqdMxMap);
				}
				tcBean.setGqdMxMapList(gqdMxMapList);
				tcdBeanList.add(tcBean);
			} catch (Exception e) {
				ptlb.add("解析第"+(i+1)+"个改签单报错。错误原因："+e.getMessage());
			}

		}
		//查询出机票结算三字代号
		ptlb.add("本次扫描共"+tcdBeanList.size()+"个改签单");
		return tcdBeanList;
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
	 * 分转为元
	 * 
	 * @param fen
	 * @return
	 */
	private static String FenToYuan(String fen) {
		if(StringUtils.isBlank(fen)||"null".equalsIgnoreCase(fen)){
			fen = "0";
		}
		BigDecimal b1 = new BigDecimal(fen);
		BigDecimal b2 = new BigDecimal("100");
		String amount2 = b1.divide(b2).toString();
		return amount2;
	}
	public static void main(String[] args) {
		String str = "";
		JSONObject jsonObj = JSONObject.parseObject(str);
		JSONArray jsonArr =  jsonObj.getJSONObject("alitrip_seller_modify_list_response").getJSONObject("order_list").getJSONArray("sync_order_do");
		Set<String> tmpSet = new HashSet<String>();
		for(int i=0;i<jsonArr.size();i++){
			String order_id =  jsonArr.getJSONObject(i).getString("order_id");
			tmpSet.add(order_id);
		}
		System.out.println(tmpSet.size());
	}
}
