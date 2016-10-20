package cn.vetech.vedsb.jp.jpddsz.taobao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;
import cn.vetech.vedsb.jp.jpddsz.JpTpdGySuper;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_tpd;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.TripOrder;
import com.taobao.api.request.AlitripSellerRefundConfirmreturnRequest;
import com.taobao.api.request.AlitripSellerRefundFillfeeRequest;
import com.taobao.api.request.AlitripSellerRefundGetRequest;
import com.taobao.api.request.AlitripSellerRefundRefusereturnRequest;
import com.taobao.api.request.AlitripSellerRefundSearchRequest;
import com.taobao.api.request.AlitripSellerRefundmoneyConfirmRequest;
import com.taobao.api.response.AlitripSellerRefundConfirmreturnResponse;
import com.taobao.api.response.AlitripSellerRefundFillfeeResponse;
import com.taobao.api.response.AlitripSellerRefundGetResponse;
import com.taobao.api.response.AlitripSellerRefundRefusereturnResponse;
import com.taobao.api.response.AlitripSellerRefundSearchResponse;
import com.taobao.api.response.AlitripSellerRefundmoneyConfirmResponse;
import com.taobao.api.response.AlitripSellerRefundGetResponse.ReturnApplyPassenge;
import com.taobao.api.response.AlitripSellerRefundGetResponse.ReturnTicketDetail;
import com.taobao.api.response.AlitripSellerRefundGetResponse.ReturnTicketSegment;
import com.taobao.api.response.AlitripSellerRefundSearchResponse.ReturnTicketDo;

public class TaobaoGy_tf extends JpTpdGySuper{
	public static Map<String,TripOrder> tmpMap = new HashMap<String, TripOrder>();
	private String appkey;
	private String URL;
	private String SessionKey;
	private TaobaoClient client;
	private JpDdsz jpDdsz;
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	public TaobaoGy_tf(JpDdsz jpDdsz){
		this.jpDdsz = jpDdsz;
		this.URL = jpDdsz.getDdJkdz();
		this.appkey = jpDdsz.getDdJkzh();
		String secret = jpDdsz.getDdJkmm();
		this.SessionKey = jpDdsz.getDdAqm1();
		this.client = new DefaultTaobaoClient(URL, appkey, secret, "json", 20 * 1000, 20 * 1000);
	}
	public List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb){
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		// 控制只能1分钟扫描一次
		String nowtime = VeDate.getStringDate();
		String pretime = PRETIMEMAP.get(appkey+URL+"TF");
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 1).compareTo(nowtime) >= 0) {
			return list;
		}
		PRETIMEMAP.put(appkey+URL+"TF", nowtime);
		try {
//			String _nowtime = "2016-08-09 15:50:00";
			Date startDate = VeDate.strToDateLong(VeDate.getPreMin(nowtime, -30));//每次扫描30分钟范围内数据
			Date endDate =VeDate.strToDateLong(nowtime);
			AlitripSellerRefundSearchRequest req = new AlitripSellerRefundSearchRequest();
			req.setEndTime(endDate);
			req.setStartTime(startDate);
//			req.setStatus();//扫描所有状态的退废申请单入库 1初始 2接受 3确认 4失败 5买家处理 6卖家处理 7等待小二回填 8退款成功
			ptlb.add("淘宝退废扫描:"+ URL+ " 接口名：AlitripSellerRefundSearchRequest 参数："+ req.getTextParams().toString());
			AlitripSellerRefundSearchResponse response = execute(req);
			ptlb.add("请求淘宝退废扫描接口返回："+response.getBody());
			List<ReturnTicketDo> rtlist = response.getResult().getResults();
			if(rtlist==null){
				return list;
			}
			for(int i=0;i<rtlist.size();i++){
				ReturnTicketDo rtd = rtlist.get(i);
				long status = rtd.getStatus();//状态
				if(("1,2,3,8,").indexOf(status+",")==-1){//只导2,3,8状态的退票单
					continue;
				}
				long applyid = rtd.getApplyId();//退废申请单ID
				AlitripSellerRefundGetRequest req1 = new AlitripSellerRefundGetRequest();
				req1.setApplyId(applyid);
				ptlb.add("请求淘宝退废详情接口AlitripSellerRefundGetRequest："+req1.getTextParams().toString());
				AlitripSellerRefundGetResponse response1 = execute(req1);
				ptlb.add("淘宝退废详情接口返回："+response1.getBody());
				List<TicketRefundBean> _list = retinfo(response1, ptlb,"0");
				list.addAll(_list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求淘宝退废扫描异常："+e.getMessage());
		}
		return list;
	}
	public List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb,String dateStr){
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		try {
//			String _nowtime = "2016-08-09 15:50:00";
			Date startDate = VeDate.strToDateLong(VeDate.getPreMin(dateStr, -30));//每次扫描30分钟范围内数据
			Date endDate =VeDate.strToDateLong(dateStr);
			AlitripSellerRefundSearchRequest req = new AlitripSellerRefundSearchRequest();
			req.setEndTime(endDate);
			req.setStartTime(startDate);
//			req.setStatus();//扫描所有状态的退废申请单入库 1初始 2接受 3确认 4失败 5买家处理 6卖家处理 7等待小二回填 8退款成功
			ptlb.add("淘宝退废扫描:"+ URL+ " 接口名：AlitripSellerRefundSearchRequest 参数："+ req.getTextParams().toString());
			AlitripSellerRefundSearchResponse response = execute(req);
			ptlb.add("请求淘宝退废扫描接口返回："+response.getBody());
			List<ReturnTicketDo> rtlist = response.getResult().getResults();
			if(rtlist==null){
				return list;
			}
			for(int i=0;i<rtlist.size();i++){
				ReturnTicketDo rtd = rtlist.get(i);
				long status = rtd.getStatus();//状态
				if(("1,2,3,8,").indexOf(status+",")==-1){//只导2,3,8状态的退票单
					continue;
				}
				long applyid = rtd.getApplyId();//退废申请单ID
				long orderid = rtd.getOrderId();//订单ID
				AlitripSellerRefundGetRequest req1 = new AlitripSellerRefundGetRequest();
				req1.setApplyId(applyid);
				ptlb.add("请求淘宝退废详情接口AlitripSellerRefundGetRequest 参数："+req1.getTextParams().toString());
				AlitripSellerRefundGetResponse response1 = execute(req1);
				ptlb.add("淘宝退废详情接口返回："+response1.getBody());
				List<TicketRefundBean> _list = retinfo(response1, ptlb,"0");
				list.addAll(_list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求淘宝退废扫描异常："+e.getMessage());
		}
		return list;
	}
	private List<TicketRefundBean> retinfo(AlitripSellerRefundGetResponse response,JpPtLog ptlb,String type) throws Exception {
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		if(StringUtils.isBlank(type)){
			type="0";
		}
		ReturnTicketDetail rtdetail = response.getResult().getResults();
		long apply_id = rtdetail.getApplyId();//退票申请单号
		long order_id = rtdetail.getOrderId();//原订单编号
		long apply_reason_type = rtdetail.getApplyReasonType();//退票原因
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String apply_time;
		if(rtdetail.getApplyTime()!=null){
			apply_time = sdf.format(rtdetail.getApplyTime());//退票申请时间
		}
		String first_process_time;
		if(rtdetail.getFirstProcessTime()!=null){
			first_process_time = sdf.format(rtdetail.getFirstProcessTime());//退款成功时间
		}
		String reason = rtdetail.getReason();//退票原因
		String refund_fee = FenToYuan(rtdetail.getRefundFee()==null ? "0":(rtdetail.getRefundFee()+""));//退款手续费
		String refund_money = FenToYuan(rtdetail.getRefundMoney()+"");//退款金额（退给买家的钱）
		long status = rtdetail.getStatus();//退票申请单状态 
		if("1".equals(type)&&"4,8".indexOf(status+"")==-1){//如果是同步退废状态扫描，这里只扫描4已取消 8，已办理退票单
			return list;
		}
//		ROUTE_CHANGE(1,"我要改变行程计划、我不想飞了",null),
//		DATE_OR_FLIGHT_ERROR(2,"下单时选错日期/航班","您可以先行尝试与卖家联系改签机票。"),
//		PASSENGER_OR_RELATION_ERROR(3,"下单时填错乘机人/联系人信息","您可以先行尝试与卖家联系为您修改信息。"),
//		MORE_PROMOTION(4,"在其他渠道发现更优惠的机票",null),
//		INVOLUNTARY(5,"非自愿退票（其他）","非自愿退票需卖家核实，核实后将为您全额退款。"),
//		INVOLUNTARY_AIRWAYS(6,"非自愿退票（航班延误或取消、航班时刻变更等航司原因)","非自愿退票需卖家核实，核实后将为您全额退款。"),
//		INVOLUNTARY_ILL_HAS_CERTIFICATE(21,"非自愿退票（身体原因且有二级甲等医院<含>以上的医院证明）",null),
//		//买家
//		ERROR_IPNUT_DATA(31,"填错名字、选错日期、选错航班",null),
//		MISS_FLIGHT_OR_NO_PASSPORT(32,"没赶上飞机、证件忘带了",null),
//		ILL_NO_CERTIFICATE(33,"生病了无法乘机（无二甲医院证明）",null),
		
		
		/**
		 * 我们系统
		 * 1001501 "客人自愿退票，按客规收取手续费"
		 * 1001502 "因航班取消延误，申请全退"
		 * 1001503 "升舱换开，申请全退"
		 * 1001504 "名字错换开重出，申请全退"
		 * 1001505 "客人因病无法乘机，申请全退"
		 * 1001506 "其它退票情况"
		 */
		String xs_sfzytp = "1";
		String refundType = "自愿退票";
		String tply = "1001501";
		if(apply_reason_type==1){//我要改变行程计划、我不想飞了
			xs_sfzytp = "1";
			refundType = "我要改变行程计划、我不想飞了";
			tply = "1001501";
		}else if(apply_reason_type==2){//下单时选错日期/航班
			xs_sfzytp = "1";
			refundType = "下单时选错日期/航班";
			tply = "1001501";
		}else if(apply_reason_type==3){//下单时填错乘机人/联系人信息
			xs_sfzytp = "1";
			refundType = "下单时填错乘机人/联系人信息";
			tply = "1001501";
		}else if(apply_reason_type==4){//在其他渠道发现更优惠的机票
			xs_sfzytp = "1";
			refundType = "在其他渠道发现更优惠的机票";
			tply = "1001501";
		}else if(apply_reason_type==5){//非自愿退票（其他）
			xs_sfzytp = "0";
			refundType = "非自愿退票（其他）";
			tply = "1001506";
		}else if(apply_reason_type==6){//非自愿退票（航班延误或取消、航班时刻变更等航司原因)
			xs_sfzytp = "0";
			refundType = "非自愿退票（航班延误或取消、航班时刻变更等航司原因)";
			tply = "1001502";
		}else if(apply_reason_type==21){//非自愿退票（身体原因且有二级甲等医院<含>以上的医院证明）
			xs_sfzytp = "0";
			refundType = "非自愿退票（身体原因且有二级甲等医院<含>以上的医院证明）";
			tply = "1001505";
		}else if(apply_reason_type==31){//买家 填错名字、选错日期、选错航班
			xs_sfzytp = "1";
			refundType = "填错名字、选错日期、选错航班";
			tply = "1001501";
		}else if(apply_reason_type==32){//买家 没赶上飞机、证件忘带了
			xs_sfzytp = "1";
			refundType = "没赶上飞机、证件忘带了";
			tply = "1001501";
		}else if(apply_reason_type==33){//买家 生病了无法乘机（无二甲医院证明）
			xs_sfzytp = "1";
			refundType = "生病了无法乘机（无二甲医院证明）";
			tply = "1001501";
		}
		//人的费用信息
		List<ReturnApplyPassenge> raPassengeList = rtdetail.getReturnApplyPassenge();

		String xs_tpzt = "0";//默认0  0已申请，1已审核，2已办理，9已取消
		if(status==1l){
			xs_tpzt = "0";
		}else if(status==2l){
			xs_tpzt = "0";
		}else if(status==3l){
			xs_tpzt = "1";
		}else if(status==4l){
			xs_tpzt = "9";
		}else if(status==8l){
			xs_tpzt = "2";
		}
		Map<String,String> tfdMap = new HashMap<String, String>();
		tfdMap.put("WBDDBH", order_id+"");
		tfdMap.put("WBDH", apply_id+"");
		tfdMap.put("SHBH", jpDdsz.getShbh());
		tfdMap.put("DDYH", jpDdsz.getDdUserid());
		tfdMap.put("DDBM", jpDdsz.getDdBmid());
		tfdMap.put("XS_SFZYTP", xs_sfzytp); //0自愿  1非自愿
		tfdMap.put("XS_TPLY", tply); //退票原因
		tfdMap.put("XS_TPBZ", refundType); //
		tfdMap.put("XS_TPZT", xs_tpzt);
		tfdMap.put("XS_PNR_NO", "");
		tfdMap.put("WBTPZT", status+"");
		if(status==1l){//状态为1的不自动办理
			tfdMap.put("AUTO_TP", "-1");
		}
		TicketRefundBean trBean = new TicketRefundBean();
		trBean.setTfdMap(tfdMap);
		List<Map<String,String>> tfdMxMapList = new ArrayList<Map<String,String>>();
		trBean.setTfdMxMap(tfdMxMapList);
		Map<String,Map<String,String>> tmpTfdMxMap = new HashMap<String, Map<String,String>>();
		String RefundFee = "";
		for(int i=0;i<raPassengeList.size();i++){
			ReturnApplyPassenge onePassenge = raPassengeList.get(i);
			long passenger_id = onePassenge.getId();//乘机人id要存临时表方便回填手续费时调用
			String passenger_name = onePassenge.getPassengerName();//乘机人姓名
			long passenger_type = onePassenge.getPassengerType();//乘机人类型
			String refund_fee_oneP = FenToYuan(onePassenge.getRefundFee()+"");//退款手续费_单人
			String refund_money_oneP = FenToYuan(onePassenge.getRefundMoney()==null ? "0":(onePassenge.getRefundMoney()+""));//退款金额_单人
			String ticket_price_oneP = FenToYuan(onePassenge.getTicketPrice()==null ? "0":(onePassenge.getTicketPrice()+""));//票面价_单人
			String discount_ticket_price_oneP = FenToYuan(onePassenge.getDiscountTicketPrice()==null ? "0":(onePassenge.getDiscountTicketPrice()+""));//优惠后票价
			String voucher_price_oneP = FenToYuan(onePassenge.getVoucherPrice()==null ? "0":(onePassenge.getVoucherPrice()+""));//优惠券金额_单人
			RefundFee = refund_fee_oneP;
			//退款航段信息
			List<ReturnTicketSegment> rtSegmentList = onePassenge.getReturnTicketSegment();
			Collections.sort(rtSegmentList, new Comparator<ReturnTicketSegment>(){
				@Override
				public int compare(ReturnTicketSegment o1,
						ReturnTicketSegment o2) {
					if(o1.getDepTime().after(o2.getDepTime())){
						return 1;
					}else {
						return -1;
					}
				}
			});
			for(int j=0;j<rtSegmentList.size();j++){
				ReturnTicketSegment oneSegment = rtSegmentList.get(j);
				String arr_airport_code = oneSegment.getArrAirportCode();//CAN 到达机场三字码
				String arr_city = oneSegment.getArrCity();//广州 到达城市
				String build_fee = FenToYuan(oneSegment.getBuildFee()+"");//单个航段机场建设费用
				String dep_airport_code = oneSegment.getDepAirportCode();//出发机场三字码
				String dep_city = oneSegment.getDepCity();//出发城市
				String flight_no = oneSegment.getFlightNo();//航班号
				String oil_tax = FenToYuan(oneSegment.getOilTax()+"");//单个航段机场建燃油费用
				String refund_modify_fee = FenToYuan(oneSegment.getRefundModifyFee()+"");//改签手续费
				String refund_upgrade_fee = FenToYuan(oneSegment.getRefundUpgradeFee()+"");//升舱手续费
				Boolean suspend = oneSegment.getSuspend();//票状态是否挂起
				String ticket_no = StringUtils.trimToEmpty(oneSegment.getTicketNo());//784-XXXXXXX票号信息
				long trip_type = oneSegment.getTripType();//去程0 回程1
				String dep_time = sdf.format(oneSegment.getDepTime());
				String tmpHc = dep_airport_code + arr_airport_code;//相同乘机人下，多个票号要拆分退票单
				Map<String,String> tfdMxMap = tmpTfdMxMap.get(ticket_no);//相同票号处理再一起。
				if(tfdMxMap==null){
					tfdMxMap = new HashMap<String, String>();
					tfdMxMap.put("CJR", passenger_name);
					tfdMxMap.put("WBCJRID", passenger_id+"");
					tfdMxMap.put("XS_TPSXF", refund_fee_oneP);
					tfdMxMap.put("XS_TKJE",refund_money_oneP);
					tfdMxMap.put("XS_ZDJ", discount_ticket_price_oneP);
					tfdMxMap.put("XS_JSF", build_fee);
					tfdMxMap.put("XS_TAX", oil_tax);
					tfdMxMap.put("TKNO", ticket_no);
					tfdMxMap.put("HC", tmpHc);
					tfdMxMap.put("XS_HBH", flight_no);
				}else{
					double xs_tpsxf_old = NumberUtils.toDouble(tfdMxMap.get("XS_TPSXF"), 0);
					double xs_tkje_old = NumberUtils.toDouble(tfdMxMap.get("XS_TKJE"), 0);
					double xs_jsf_old = NumberUtils.toDouble(tfdMxMap.get("XS_JSF"), 0);
					double xs_tax_old = NumberUtils.toDouble(tfdMxMap.get("XS_TAX"), 0);
					String hc_old = StringUtils.trimToEmpty(tfdMxMap.get("HC"));
					String xs_hbh_old = StringUtils.trimToEmpty(tfdMxMap.get("XS_HBH"));
					double xs_tpsxf_new = Arith.add(NumberUtils.toDouble(refund_fee_oneP, 0), xs_tpsxf_old);
					double xs_tkje_new = Arith.add(NumberUtils.toDouble(refund_money_oneP, 0), xs_tkje_old);
					double xs_jsf_new = Arith.add(NumberUtils.toDouble(build_fee, 0), xs_jsf_old);
					double xs_tax_new = Arith.add(NumberUtils.toDouble(oil_tax, 0), xs_tax_old);
					String hc_new = hc_old;
					if(tmpHc.substring(0,3).endsWith(hc_old)){
						hc_new += tmpHc.substring(3);
					}else{
						hc_new +=tmpHc;
					}
					String xs_hbh_new = xs_hbh_old;
					if(xs_hbh_old.indexOf(flight_no)==-1){
						xs_hbh_new +="|"+ flight_no;
					}
					tfdMxMap.put("XS_TPSXF",xs_tpsxf_new+"");
					tfdMxMap.put("XS_TKJE",xs_tkje_new+"");
					tfdMxMap.put("XS_JSF", xs_jsf_new+"");
					tfdMxMap.put("XS_TAX", xs_tax_new+"");
					tfdMxMap.put("HC", hc_new);
					tfdMxMap.put("XS_HBH", xs_hbh_new);
				}
				tmpTfdMxMap.put(ticket_no, tfdMxMap);
			}
		}
		tfdMxMapList.addAll(tmpTfdMxMap.values());
		trBean.getTfdMap().put("HC", trBean.getTfdMxMap().get(0).get("HC"));
		trBean.getTfdMap().put("XS_HBH", trBean.getTfdMxMap().get(0).get("XS_HBH"));
		list.add(trBean);
		return list;
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
	 * 回填手续费
	 * 【回填手续费】接口
	 * @param ptlb
	 * @param fhParams
	 * @return
	 */
	public Map<String,String> fillfee(String tpdh,Shyhb shyhb,JpPtLog ptlb,JpddWork_tpd JpddWork_tpd){
		Map<String,String> retMap = new HashMap<String, String>();
		if(JpddWork_tpd==null){
			retMap.put("status", "false");
			retMap.put("message", "缺少JpddWork_tpd对象");
			return retMap;
		}
		Map<String, Object> params = new HashMap<String, Object>();;
		try {
			Map<String, Object> _params = JpddWork_tpd.getParam(tpdh, shyhb, ptlb);
			params.putAll(_params);
		} catch (Exception e1) {
			e1.printStackTrace();
			retMap.put("status", "false");
			retMap.put("message", e1.getMessage());
			return retMap;
		}
		String gytfid = (String)params.get("gytfdh");
		List<String> cjrs = (List<String>)params.get("cjrs");
		List<String> wbcjrids = (List<String>)params.get("wbcjrids");
		List<String> xsTpsxfs = (List<String>)params.get("xsTpsxfs");
		List<String> xsZdjs = (List<String>)params.get("xsZdjs");
		
		Map<String,Integer> feePriceMap = new HashMap<String, Integer>();
		Map<String,Integer> ticketPriceMap = new HashMap<String, Integer>();
		long applyId = NumberUtils.toLong(gytfid);
		for(int i=0;i<cjrs.size();i++){
			String wbcjrid = wbcjrids.get(i);
			Integer tpsxf = feePriceMap.get(wbcjrid)==null ? 0 : feePriceMap.get(wbcjrid);
			tpsxf = (int)Arith.add(tpsxf, Arith.mul(NumberUtils.toDouble(xsTpsxfs.get(i),0), 100.0));
			Integer pmj = ticketPriceMap.get(wbcjrid)==null ? 0 : ticketPriceMap.get(wbcjrid);;
			pmj = (int)Arith.add(pmj, Arith.mul(NumberUtils.toDouble(xsZdjs.get(i),0), 100));
			feePriceMap.put(wbcjrid, tpsxf);
			ticketPriceMap.put(wbcjrid,pmj);
		}
		String feepriceMapString = JsonMapper.nonDefaultMapper().toJson(feePriceMap);
		String ticketPriceMapString = JsonMapper.nonDefaultMapper().toJson(ticketPriceMap);
		
		AlitripSellerRefundFillfeeRequest req = new AlitripSellerRefundFillfeeRequest();
		req.setApplyId(applyId);
		req.setFeePriceMapString(feepriceMapString);
		req.setModifyFeeString("");
		req.setTicketPriceMapString(ticketPriceMapString);
		req.setUpgradeFeeString("");
		AlitripSellerRefundFillfeeResponse response = null;
		try {
			ptlb.add("淘宝【回填手续费】："+ URL+ " 接口名：AlitripSellerRefundFillfeeRequest 参数："+ req.getTextParams().toString());
			response = execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求【回填手续费】接口异常，异常原因："+e.getMessage());
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【回填手续费】接口异常，异常原因："+e.getMessage());
			return retMap;
		}
		if(response.getResult()!=null&&response.getResult()){
			retMap.put("status", "true");
			ptlb.add("请求【回填手续费】接口返回："+response.getBody());
		}else{
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【回填手续费】接口返回失败，失败原因:"+response.getBody());
		}
		return retMap;
	}
	/**
	 * 【拒绝退票】接口
	 * 根据超级电商退票单号查
	 * @param ptlb
	 * @param fhParams
	 * @return
	 */
	public Map<String,String> refusereturn(String tpdh,String tpyy,Shyhb shyhb,JpPtLog ptlb,JpddWork_tpd JpddWork_tpd){
		
		Map<String, Object> tpdMap = null;
		try {
			tpdMap = JpddWork_tpd.getTpdMap(tpdh, shyhb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tpdMap==null||StringUtils.isBlank(StringUtils.trimToEmpty((String)tpdMap.get("WBDH")))){
			Map<String,String> retMap = new HashMap<String, String>();
			ptlb.add("查询改签单信息失败！");
			retMap.put("status", "false");
			retMap.put("message", "查询改签单信息失败！");
			return retMap;
		}
		String wbdh = StringUtils.trimToEmpty((String)tpdMap.get("WBDH"));

		return _refusereturn(wbdh, tpyy, shyhb, ptlb);
	}
	/**
	 * 【拒绝退票】接口
	 * 根据外部退票单号查
	 * @param ptlb
	 * @param fhParams
	 * @return
	 */
	public Map<String,String> refusereturn(String wbdh,String tpyy,Shyhb shyhb,JpPtLog ptlb){
		return _refusereturn(wbdh, tpyy, shyhb, ptlb);
	}
	private Map<String,String> _refusereturn(String wbdh,String tpyy,Shyhb shyhb,JpPtLog ptlb){
		Map<String,String> retMap = new HashMap<String, String>();
		long applyId = NumberUtils.toLong(wbdh);
		AlitripSellerRefundRefusereturnRequest req = new AlitripSellerRefundRefusereturnRequest();
		req.setApplyId(applyId);
		req.setReason(tpyy);
		AlitripSellerRefundRefusereturnResponse response = null;
		try {
			ptlb.add("淘宝【拒绝退票】："+ URL+ " 接口名：AlitripSellerRefundRefusereturnRequest 参数："+ req.getTextParams().toString());
			response = execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求【拒绝退票】接口异常，异常原因："+e.getMessage());
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【回填手续费】接口异常，异常原因："+e.getMessage());
			return retMap;
		}
		if(response!=null&&response.getResult()!=null&&response.getResult().getResult()!=null&&response.getResult().getResult()){
			retMap.put("status", "true");
			ptlb.add("请求【拒绝退票】接口返回："+response.getBody());
		}else{
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【回填手续费】接口返回失败，失败原因:"+response.getBody());
		}
		return retMap;
	}
	
	/**
	 * 【确认退票】接口
	 * @param tpdh
	 * @param shyhb
	 * @param ptlb
	 * @param JpddWork_tpd
	 * @return
	 */
	public Map<String,String> confirmreturn(String tpdh,Shyhb shyhb,JpPtLog ptlb,JpddWork_tpd JpddWork_tpd){
		Map<String, Object> tpdMap = null;
		try {
			tpdMap = JpddWork_tpd.getTpdMap(tpdh, shyhb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tpdMap==null||StringUtils.isBlank(StringUtils.trimToEmpty((String)tpdMap.get("WBDH")))){
			Map<String,String> retMap = new HashMap<String, String>();
			ptlb.add("查询改签单信息失败！");
			retMap.put("status", "false");
			retMap.put("message", "查询改签单信息失败！");
			return retMap;
		}
		String wbdh = StringUtils.trimToEmpty((String)tpdMap.get("WBDH"));
		return _confirmreturn(wbdh,shyhb,ptlb);
	}
	/**
	 * 【确认退票】接口
	 * @param tpdh
	 * @param shyhb
	 * @param ptlb
	 * @param JpddWork_tpd
	 * @return
	 */
	public Map<String,String> confirmreturn(String wbdh,Shyhb shyhb,JpPtLog ptlb){
		return _confirmreturn(wbdh,shyhb,ptlb);
	}
	/**
	 * 【确认退票】接口
	 * @param tpdh
	 * @param shyhb
	 * @param ptlb
	 * @param JpddWork_tpd
	 * @return
	 */
	public Map<String,String> _confirmreturn(String wbdh,Shyhb shyhb,JpPtLog ptlb){
		Map<String,String> retMap = new HashMap<String, String>();
		long applyId = NumberUtils.toLong(wbdh);
		AlitripSellerRefundConfirmreturnRequest req = new AlitripSellerRefundConfirmreturnRequest();
		req.setApplyId(applyId);
		AlitripSellerRefundConfirmreturnResponse response = null;
		try {
			ptlb.add("淘宝【确认退票】："+ URL+ " 接口名：AlitripSellerRefundConfirmreturnRequest 参数："+ req.getTextParams().toString());
			response = execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求【确认退票】接口异常，异常原因："+e.getMessage());
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【确认退票】接口异常，异常原因："+e.getMessage());
			return retMap;
		}
		if(response!=null&&response.getResult()!=null&&response.getResult()){
			retMap.put("status", "true");
			ptlb.add("请求【确认退票】接口返回："+response.getBody());
		}else{
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【确认退票】接口返回失败，失败原因:"+response.getBody());
		}
		return retMap;
	}
	/**
	 * 【确认退款】接口
	 * @return
	 */
	public Map<String,String> refundmoneyConfirm(String tpdh,Shyhb shyhb,JpPtLog ptlb,JpddWork_tpd JpddWork_tpd){
		Map<String, Object> tpdMap = null;
		try {
			tpdMap = JpddWork_tpd.getTpdMap(tpdh, shyhb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tpdMap==null||StringUtils.isBlank(StringUtils.trimToEmpty((String)tpdMap.get("WBDH")))){
			Map<String,String> retMap = new HashMap<String, String>();
			ptlb.add("查询改签单信息失败！");
			retMap.put("status", "false");
			retMap.put("message", "查询改签单信息失败！");
			return retMap;
		}
		String wbdh = StringUtils.trimToEmpty((String)tpdMap.get("WBDH"));
		return _refundmoneyConfirm(wbdh, shyhb, ptlb);
	}
	/**
	 * 【确认退款】接口
	 * @return
	 */
	public Map<String,String> refundmoneyConfirm(String wbdh,Shyhb shyhb,JpPtLog ptlb){
		return _refundmoneyConfirm(wbdh,shyhb,ptlb);
	}
	/**
	 * 【确认退款】接口
	 * @return
	 */
	public Map<String,String> _refundmoneyConfirm(String wbdh,Shyhb shyhb,JpPtLog ptlb){
		Map<String,String> retMap = new HashMap<String, String>();
		long applyId = NumberUtils.toLong(wbdh);
		AlitripSellerRefundmoneyConfirmRequest req = new AlitripSellerRefundmoneyConfirmRequest();
		req.setApplyId(applyId);
		AlitripSellerRefundmoneyConfirmResponse response = null;
		try {
			ptlb.add("淘宝【确认退款】："+ URL+ " 接口名：AlitripSellerRefundmoneyConfirmRequest 参数："+ req.getTextParams().toString());
			response = execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求【确认退款】接口异常，异常原因："+e.getMessage());
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【确认退款】接口异常，异常原因："+e.getMessage());
			return retMap;
		}
		if(response !=null && response.isSuccess()){
			retMap.put("status", "true");
			ptlb.add("请求【确认退款】接口返回："+response.getBody());
		}else{
			retMap.put("status", "false");
			retMap.put("message", "调用淘宝【确认退款】接口返回失败，失败原因:"+response.getBody());
		}
		return retMap;

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
	@Override
	public List<TicketRefundBean> getByWbtpdh(String wdtpdh, JpPtLog ptlb)
			throws Exception {
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		AlitripSellerRefundGetRequest req1 = new AlitripSellerRefundGetRequest();
		req1.setApplyId(NumberUtils.toLong(wdtpdh, 0l));
		ptlb.add("请求淘宝退废详情接口AlitripSellerRefundGetRequest："+req1.getTextParams().toString());
		AlitripSellerRefundGetResponse response1 = execute(req1);
		ptlb.add("淘宝退废详情接口返回："+response1.getBody());
		list = retinfo(response1, ptlb,"1");
		return list;
	}
	public static void main(String[] args) throws Exception {
		AlitripSellerRefundGetRequest req1 = new AlitripSellerRefundGetRequest();
		req1.setApplyId(18974405L);
		TaobaoClient client=	new DefaultTaobaoClient("http://112.124.54.173:30001/airs/conver.shtml", "21549144", "97582f944eeee2a12322360cd9a81a39", "json", 20 * 1000, 20 * 1000);
		AlitripSellerRefundGetResponse response=	 client.execute(req1, "6102010e4da5a630ef1b3d20c4b8321f9fb4652903858bc2927925573");
		System.out.println(response.getBody());	
	}
}
