package cn.vetech.vedsb.jp.jpddsz.taobao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;
import cn.vetech.vedsb.utils.LogUtil;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.Itinerary;
import com.taobao.api.domain.TripBaseInfo;
import com.taobao.api.domain.TripFlightInfo;
import com.taobao.api.domain.TripFlightPassenger;
import com.taobao.api.domain.TripOrder;
import com.taobao.api.request.JipiaoAgentOrderBdetailRequest;
import com.taobao.api.request.JipiaoAgentOrderDetailRequest;
import com.taobao.api.request.JipiaoAgentOrderSearchRequest;
import com.taobao.api.request.JipiaoAgentOrderTicketRequest;
import com.taobao.api.response.JipiaoAgentOrderBdetailResponse;
import com.taobao.api.response.JipiaoAgentOrderDetailResponse;
import com.taobao.api.response.JipiaoAgentOrderSearchResponse;
import com.taobao.api.response.JipiaoAgentOrderTicketResponse;

public class TaobaoGy extends JpddGySuper{
//	public static Map<String,TripOrder> tmpMap = new HashMap<String, TripOrder>();
	private String URL;

	private String SessionKey;
	private TaobaoClient client;
	private JpDdsz jpDdsz;
	
	
	public TaobaoGy(JpDdsz jpDdsz){
		this.jpDdsz = jpDdsz;
		this.URL = jpDdsz.getDdJkdz();
		String appkey = jpDdsz.getDdJkzh();
		String secret = jpDdsz.getDdJkmm();
		this.SessionKey = jpDdsz.getDdAqm1();
		this.client = new DefaultTaobaoClient(URL, appkey, secret, "json", 20 * 1000, 20 * 1000);
	}
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		String sfsmpdyw = StringUtils.trimToEmpty(jpDdsz.getSfsmpdyw());
		List<OrderBean> list = new ArrayList<OrderBean>();
		int min = jpDdsz.getTbsmsjjg()==null ? 120 : jpDdsz.getTbsmsjjg();
		if(min<120){
			min=120;
		}
		try {
			List<OrderBean> dcplist = _queryOrders(ptlb,2l,sfsmpdyw,min,"");//2 已支付等待出票订单入库
			list.addAll(dcplist);
		} catch (Exception e) {
			ptlb.add("查询淘宝订单搜索（待出票）接口出错："+e.getMessage());
		}
		try {
			List<OrderBean> qzcglist = _queryOrders(ptlb,3l,sfsmpdyw,min,"");
			list.addAll(qzcglist);
		} catch (Exception e) {
			ptlb.add("查询淘宝订单搜索（强制成功）接口出错："+e.getMessage());
		}
		return list;
	}
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		String sfsmpdyw = StringUtils.trimToEmpty(jpDdsz.getSfsmpdyw());
		List<OrderBean> list = new ArrayList<OrderBean>();
		int min = 60 * 24;//手工扫描时扫描24小时内订单
		try {
			List<OrderBean> dcplist = _queryOrders(ptlb,2l,sfsmpdyw,min,dateStr);//2 已支付等待出票订单入库
			list.addAll(dcplist);
		} catch (Exception e) {
			ptlb.add("查询淘宝订单搜索（待出票）接口出错："+e.getMessage());
		}
		try {
			List<OrderBean> qzcglist = _queryOrders(ptlb,3l,sfsmpdyw,min,dateStr);
			list.addAll(qzcglist);
		} catch (Exception e) {
			ptlb.add("查询淘宝订单搜索（强制成功）接口出错："+e.getMessage());
		}
		return list;
	}
	private List<OrderBean> _queryOrders(JpPtLog ptlb, long status, String sfsmpdyw, int min,String dateString) throws Exception {
		JipiaoAgentOrderSearchRequest req = new JipiaoAgentOrderSearchRequest();
		// 请求一段时间内的所有订单获得订单号
		String now = dateString;
		if(StringUtils.isBlank(now)){
			now = VeDate.getStringDate();
		}
		String apppretime = VeDate.getPreMin(now, -min);
		req.setBeginTime(VeDate.strToDateLong(apppretime));
		req.setEndTime(VeDate.strToDateLong(now));
		req.setStatus(status);
		if("1".equals(sfsmpdyw)){
			String extra = "{\"needFilterAutobook\": false,\"isNoPnr\": false }";
			req.setExtra(extra);
		}
		ptlb.add("淘宝新接口扫描【状态："+status+"】："+ URL+ " 接口名：JipiaoAgentOrderSearchRequest 参数："+ req.getTextParams().toString());
		JipiaoAgentOrderSearchResponse  response = execute(req);
//		JipiaoAgentOrderSearchResponse  response = new JipiaoAgentOrderSearchResponse();
		ptlb.add("扫描新接口返回【状态："+status+"】："+ response.getBody());
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		if (!response.isSuccess()) {
			ptlb.add("扫描新接口返回失败【状态："+status+"】："+ response.getBody());
			return listOrder;
		}
		List<Long> orderidList = response.getSearchResult().getOrderIds();
		if (orderidList != null && orderidList.size() > 0) {
			for (int i = 0; i < orderidList.size(); i++) {
				try{
					Long orderid = orderidList.get(i);
					TripOrder at = getAtOrder(orderid, ptlb);
					orderinfo(at, listOrder, ptlb, status, "0");
				}catch (Exception e) { 
					e.printStackTrace();
				}
			}
		}
		return listOrder;
	}
	
	private void orderinfo(TripOrder at, List<OrderBean> listOrder, JpPtLog ptlb,long status,String type) throws Exception {
		if(StringUtils.isBlank(type)){//0解析正常单详情  1解析采购单详情
			type="0";
		}
		TripBaseInfo bif = at.getBaseInfo();
		
		Map<String, String> khddMap = new HashMap<String, String>();
		Map<String,String> khdd_cp = new HashMap<String, String>();
		Map<String,String> khdd_zcid = new HashMap<String, String>();
		Map<String,String> khdd_zclx = new HashMap<String, String>();
		Map<String,String> khdd_zcsm = new HashMap<String, String>();
		String orderid = bif.getOrderId() + "";	
		try{
			String ticketExtra = StringUtils.trimToEmpty(at.getExtra());//根据此节点判断是否自动出票
			if(StringUtils.isNotBlank(ticketExtra)) {
				ptlb.add("解析Extra，判断是否自动出票");
				JSONObject itemobject = JSONObject.parseObject(ticketExtra);
				String orderType = itemobject.getString("orderType");
				if("金牌服务".equals(orderType)){
					khddMap.put("WD_DDLX", orderType);
				}
				int bPayStatus = itemobject.getIntValue("bPayStatus");
				int failType = itemobject.getIntValue("failType");
				if(bPayStatus==4 || (bPayStatus==6 && failType==100)){//判断淘宝出票失败
					khddMap.put("IFTBCPSB", "1");
				}
				String itemstr = itemobject.getString("item");
				JSONObject arr = JSONObject.parseObject(itemstr);
				for(String str : arr.keySet()) {
					JSONObject obj = arr.getJSONObject(str);
					String pnr = obj.getString("pnr");
					String zdcp = obj.getString("isAutoBook");
					String channel = obj.getString("channel");
					String zcsm = obj.getString("fareMemo");
					khdd_zcsm.put(pnr,zcsm);
					khdd_cp.put(pnr, zdcp);
					String msg = "pnr:"+pnr+",淘宝自动出票状态[isAutoBook]:"+zdcp+",渠道号为："+channel;
					
					ptlb.add(msg);
					//解析是否淘宝出票失败
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			ptlb.add("解析Extra异常！");
		}
		String nxr = StringUtils.trimToEmpty(bif.getRelationName());
		String nxsj = StringUtils.trimToEmpty(bif.getRelationMobile());
		String nxdh = StringUtils.trimToEmpty(bif.getRelationPhoneBak());
//		String reg1="^(0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8})$";//手机号码正则
//		String reg2="^(((d{3,4})|d{3,4}-)?d{7,8}(-d{3}))*$";//中国电话号码正则
		//从js中拷过来的验证方式
		String reg = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";
		if(!Pattern.matches(reg, nxsj)){
			nxsj = "";
		}
		if(!Pattern.matches(reg, nxdh)){
			nxdh = "";
		}
		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", orderid);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("CPCS", "0");
		khddMap.put("NOSX", "");
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("DDLX", "1");
		khddMap.put("DDZT", "1");
		khddMap.put("WBDDZT", status+"");

		Itinerary iti = at.getItinerary();
		String xs_yjf = "0";//邮递费用
		if (iti != null) {
			iti.getPrice();
			nxr = StringUtils.trimToEmpty(iti.getName());
			String nxsj_tmp = iti.getMobile();//收件人手机
			String nxdh_tmp = iti.getMobileBak();//收件人电话
			//当行程单中存在
			ptlb.add("存在行程单信息，联系人手机和电话信息取mobile和mobile_bak节点");
			if(Pattern.matches(reg, nxsj_tmp)){			
				nxr = nxsj_tmp;
			}
			if(Pattern.matches(reg, nxdh)){		
				nxdh = nxdh_tmp;
			}
			String name = iti.getName();
			String address = iti.getAddress();
			String price =iti.getPrice();//行程单价格 单位分 
			xs_yjf = FenToYuan(price);//
			khddMap.put("SFYJXCD", "1");//是否邮寄行程单
			khddMap.put("SJR", name);//收件人
			khddMap.put("YZBM", "");//邮政编码
			khddMap.put("XJDZ", address);//收件地址
		}
		khddMap.put("NXR", nxr);
		khddMap.put("NXSJ", nxsj);
		khddMap.put("NXDH", nxdh);
		String baseInfoExtra = StringUtils.trimToEmpty(bif.getExtra());//扩展信息
		boolean ifpd = false;//是否派单订单
		if(StringUtils.isNotBlank(baseInfoExtra)){
			JSONObject baseInfoExtraJO = JSONObject.parseObject(baseInfoExtra);
			Boolean bflag = baseInfoExtraJO.getBoolean("bflag");
			if(bflag!=null&&bflag){
				//是否淘宝出票中节点
				ifpd = true;
				khddMap.put("SFWBCPZ", "1");
				khddMap.put("IFAUTOCP", "false");
				khddMap.put("DDZT", "2");
				khddMap.put("TMP_ZCSMJOB", "1");
				khddMap.put("CP_SDR", jpDdsz.getDdUserid());
				khddMap.put("CP_SDSJ", VeDate.getStringDate());
			}
		}

		List<TripFlightInfo> tbhd = at.getFlightInfos();
		//一个PNR对应ASMS中一个订单，所以每个PNR下面有自己的航段集合
		Map<String, List<Map<String, String>>> pnr_hd_map_tmp=new HashMap<String, List<Map<String, String>>>();
		Map<String, List<Map<String, String>>> pnr_cjr_map=new HashMap<String, List<Map<String, String>>>();
		
		String commissionTotal=bif.getCommission();
		if(StringUtils.isBlank(commissionTotal)||"NULL".equalsIgnoreCase(commissionTotal)){
			commissionTotal="0";
//			ptlb.add("淘宝佣金为空，通过销售价乘以0.015来计算佣金，保留2位小数");
		}
		commissionTotal = FenToYuan(commissionTotal);
		int tbhdsize=tbhd.size();
		double ttP=0;
		try{
			for (int i = 0; i < tbhdsize; i++) {
				TripFlightInfo  si = tbhd.get(i);//航班信息
				double hdTolPri = 0.0; //航段总销售价
				for(int j=0;j<si.getPassengers().size();j++){//只有成人票才参与计算
					TripFlightPassenger tfp = si.getPassengers().get(j);
					if(!"1".equals(tfp.getPassengerType()+"")){
						hdTolPri+=NumberUtils.toDouble(FenToYuan(tfp.getPrice()+""));
					}				
				}
				ttP+=hdTolPri;
			}
			ptlb.add("该订单下面所有航段总销售价（除去儿童销售价，后面用来计算佣金）："+ttP);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, String> cfcjrValiMap=new HashMap<String, String>();
		for (int i = 0; i < tbhdsize; i++) {
			TripFlightInfo  si = tbhd.get(i);//航班信息
			List<TripFlightPassenger> passengerList= si.getPassengers();
			if(CollectionUtils.isEmpty(passengerList)){
//				ptlb.add("航段"+(i+1)+"下面乘机人数量："+passengerList.size());
				return;
			}
			String cfcity = StringUtils.trimToEmpty(si.getDepAirportCode());
			String ddcity = StringUtils.trimToEmpty(si.getArrAirportCode());
			String cfsj = StringUtils.trimToEmpty(si.getDepTime());
			cfsj = StringUtils.substring(cfsj, 0, 19);
			String ddsj = StringUtils.trimToEmpty(si.getArrTime());
			ddsj = StringUtils.substring(ddsj, 0, 19);
			String xs_hbh = StringUtils.trimToEmpty(si.getFlightNo());
			String hkgs = StringUtils.trimToEmpty(si.getAirlineCode());
			String Carrier = StringUtils.trimToEmpty(si.getCarrier());
			Long ticketPrice=si.getTicketPrice();//当前航段票面价格，单位：分
			String ticketPriceS=FenToYuan(ticketPrice + "");
			
			String TAOBAO_HD_KEY= cfcity+ddcity+cfsj;
			double jlall = 0;
			double jl = 0;
			for (int k=0;k<passengerList.size();k++) {//乘机人信息
				TripFlightPassenger p=passengerList.get(k);
				String pnr=StringUtils.trimToEmpty(p.getPnr());
				String CJRXM = StringUtils.trimToEmpty(p.getName());
				
				String fjjx = StringUtils.trimToEmpty(si.getFlightType());
				String cw=StringUtils.trimToEmpty(p.getCabinCode());
				String xs_tgq=StringUtils.trimToEmpty(p.getTuigaiqian());
				// 航段信息
				Map<String, String> hdMap = new HashMap<String, String>();
				if (StringUtils.isNotBlank(Carrier) && !Carrier.equalsIgnoreCase(xs_hbh)) {
					if (xs_hbh.indexOf("*") < 0) {
						xs_hbh = "*" + xs_hbh;
					}
					hdMap.put("OP_HKGS", Carrier);
				}
				hdMap.put("XS_HBH", xs_hbh);
				hdMap.put("CFSJ", cfsj);
				hdMap.put("DDSJ", ddsj);
				hdMap.put("CFCITY", cfcity);
				hdMap.put("DDCITY", ddcity);
				hdMap.put("XS_CW", cw);
				hdMap.put("FJJX", fjjx);
				hdMap.put("XS_TGQ", xs_tgq);
				hdMap.put("TAOBAO_HD_KEY",TAOBAO_HD_KEY);
				List<Map<String, String>> hdList=pnr_hd_map_tmp.get(pnr+"_"+hkgs);
				if(hdList==null){
//					ptlb.add("航段"+(i+1)+"乘机人"+(k+1)+"下面PNR:"+pnr+"  加载第一个航段信息");
					hdList=new ArrayList<Map<String,String>>();
					hdList.add(hdMap);
					pnr_hd_map_tmp.put(pnr+"_"+hkgs, hdList);
				}else{
//					ptlb.add("航段"+(i+1)+"乘机人"+(k+1)+"下面PNR:"+pnr+"  发现第"+(hdList.size()+1)+"个航段信息");
					boolean hdcz=false;//同一个PNR下面，不能存在相同航段信息，如果发现，则忽略
					for (Map<String, String> hdMapTmp:hdList) {
						String TAOBAO_HD_KEY_TMP=hdMapTmp.get("TAOBAO_HD_KEY");
						if(TAOBAO_HD_KEY.equals(TAOBAO_HD_KEY_TMP)){
							hdcz=true;
							break;
						}
					}
					if(!hdcz){
						hdList.add(hdMap);
					}
				}
				String TAOBAO_CJR_KEY= cfcity+ddcity+cfsj+pnr+"_"+CJRXM;
				if(StringUtils.isNotBlank(cfcjrValiMap.get(TAOBAO_CJR_KEY))){//重复航段跳过
					ptlb.add("航段"+(i+1)+"乘机人"+(k+1)+"下面PNR:"+pnr+"，乘机人姓名："+CJRXM+"  重复");
				}else{
					cfcjrValiMap.put(TAOBAO_CJR_KEY, TAOBAO_CJR_KEY);
					//乘机人信息
					List<Map<String, String>> cjrList=pnr_cjr_map.get(pnr+"_"+hkgs);
					if(cjrList==null){
						cjrList=new ArrayList<Map<String,String>>();
						pnr_cjr_map.put(pnr+"_"+hkgs, cjrList);
					}
					Map<String, String> cjrMap = new HashMap<String, String>();
					String ZJLX = StringUtils.trimToEmpty(p.getCertType() + "");
					// 0：身份证，1：护照，2：学生证，3：军人证，4：回乡证，5：台胞证，6：港澳台胞，7：国际海员，8：外国人永久居留证，9：其他
					if ("0".equals(ZJLX)) {
						ZJLX = "NI";
					} else if ("1".equals(ZJLX)) {
						ZJLX = "PP";
					} else {
						ZJLX = "ID";
					}
					String cjrlx="1";
					String ZJHM = StringUtils.trimToEmpty(p.getCertNo());
					String passenger_type = p.getPassengerType() + "";// 乘机人类型：0，成人；1，儿童；
					if("1".equals(passenger_type)){
						cjrlx="2";
					}
					String birthday =p.getBirthday();//1982-01-01 乘机人生日：yyyy-mm-dd  
					Long policy_type =p.getPolicyType();// 0 机票政策类型：0，默认；1，自定义
					Long price =p.getPrice();//80000 销售价格，单位：分 
					Long fee =p.getFee();//5000 航班机建费，单位：分 
					Long tax =p.getTax();//13000 航班燃油税，单位：分 
				
					String feeS=FenToYuan(fee + "");
					String priceS=FenToYuan(price + "");
					String taxS=FenToYuan(tax + "");
						
					//添加节点
					String policyTypemc = policy_type+"";
					//1普通、2特价、3特殊、4预付、5包机、6申请
					if(policy_type!=null) {
						if (1==policy_type) {
							policyTypemc = "普通";
						} else if (2==policy_type) {
							policyTypemc = "特价";
						}else if (3==policy_type) {
							policyTypemc = "特殊";
						}else if (4==policy_type) {
							policyTypemc = "预付";
						}else if (5==policy_type) {
							policyTypemc = "包机";
						}else if (6==policy_type) {
							policyTypemc = "申请";
						}
					}
					khdd_zclx.put(pnr,policyTypemc);
					//解析政策代码
					try{
						String pextra = StringUtils.trimToEmpty(p.getExtra());//根据此节点获取政策代码
						if(StringUtils.isNotBlank(pextra)) {
							ptlb.add("解析Extra，获取政策代码");
							JSONObject itemobject = JSONObject.parseObject(pextra);
							String outId = itemobject.getString("outId");
							ptlb.add("pnr:"+pnr+",淘宝政策代码:"+outId);
							khdd_zcid.put(pnr,outId);
						}
					}catch(Exception e){
						e.printStackTrace();
						ptlb.add("解析Extra获取政策代码异常！");
					}
					double jjry = Arith.add(NumberUtils.toDouble(feeS), NumberUtils.toDouble(taxS));
					double cjrpj = NumberUtils.toDouble(priceS);
					double xs_je = Arith.add(cjrpj, jjry);
					double pmj = cjrpj;
					double xs_yj = 0.0;//单人佣金
					if("1".equals(cjrlx)&&"0".equals(type)){//只有成人票计算佣金
						if(ttP!=0.0){//防止除零错误
							if(k==(passengerList.size()-1)){
								jl = Arith.sub(NumberUtils.toDouble(commissionTotal), jlall);
							}else{
								double bl=Arith.div(NumberUtils.toDouble(priceS), ttP);
								jl = Arith.round(NumberUtils.toDouble(Arith.mul(NumberUtils.toDouble(commissionTotal), bl)+""), 2);
							}
							jlall = Arith.add(jlall, jl);
						}

//						if(jl==0){//如果获取的淘宝佣金为0，则通过销售价乘以0.015获取
//							jl=Arith.round(Arith.mul(NumberUtils.toDouble(priceS), 0.015),2);
//						}
						xs_yj = jl;
						pmj = Arith.round(Arith.sub(cjrpj, jl), 2);
					}
					xs_je = Arith.add(pmj, jjry);
					//票号信息
					String ticket_no = StringUtils.trimToEmpty(p.getTicketNo());
					if("0".equals(type)&&!ifpd&&StringUtils.isNotBlank(ticket_no)){//解析正常单详情时，非派单并且存在票号的情况下，不做入库
						ptlb.add("外部单号["+orderid+"]上存在票号["+ticket_no+"]本次扫描过滤");
						LogUtil.getGyrz("GLDD").error("外部单号["+orderid+"]上存在票号["+ticket_no+"]本次扫描过滤");
						return;
					}
					cjrMap.put("TKNO", ticket_no);
					
					String key=CJRXM+"|"+passenger_type+"|"+ZJLX+"|"+ZJHM+"|"+ticket_no;
					cjrMap.put("TAOBAO_CJRKEY", key);//非入库字段
					cjrMap.put("CJR", CJRXM);
					cjrMap.put("CJRLX", cjrlx);
					cjrMap.put("ZJLX", ZJLX);
					cjrMap.put("ZJHM", ZJHM);
					cjrMap.put("SJHM", "");
					cjrMap.put("XB", "");
					cjrMap.put("GJ", "");
					cjrMap.put("CSRQ", "");
					cjrMap.put("ZJYXQ", birthday);
					cjrMap.put("ZJQFG", "");
					//价格项信息
					cjrMap.put("XS_JSF",feeS);
					cjrMap.put("XS_TAX", taxS);
					cjrMap.put("XS_ZDJ", ticketPriceS+"");
					cjrMap.put("XS_PJ", cjrpj+"");
					cjrMap.put("XS_JE", xs_je + "");
					cjrMap.put("XS_YJ", xs_yj + "");
					if(k==0){
//						cjrMap.put("XCDH",xcdh);
						cjrMap.put("XS_YJF", StringUtils.isBlank(xs_yjf) ? "0" : xs_yjf);
					}
					cjrMap.put("SXH",cjrList.size()+1+"");
					if(cjrList.size()==0){
						cjrList.add(cjrMap);
					}else{
						boolean sfcz=false;
						for (Map<String, String> cjrMapTmp:cjrList) {
							String keyTmp=cjrMapTmp.get("TAOBAO_CJRKEY");
							if(key.equals(keyTmp)){//同一个编码，同一个乘机人，只需要将价格累加
								double jsf_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_JSF"));
								double tax_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_TAX"));
								double xs_zdj_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_ZDJ"));
								double xs_pj_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_PJ"));
								double xs_je_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_JE"));
								double xs_yj_tmp=NumberUtils.toDouble(cjrMapTmp.get("XS_YJ"));
								cjrMapTmp.put("XS_JSF",Arith.add(jsf_tmp,NumberUtils.toDouble(feeS))+"");
								cjrMapTmp.put("XS_TAX", Arith.add(tax_tmp,NumberUtils.toDouble(taxS))+"");
								cjrMapTmp.put("XS_ZDJ", Arith.add(xs_zdj_tmp,NumberUtils.toDouble(ticketPriceS))+"");
								cjrMapTmp.put("XS_PJ", Arith.add(xs_pj_tmp,cjrpj)+"");
								cjrMapTmp.put("XS_JE", Arith.add(xs_je_tmp,xs_je)+"");
								cjrMapTmp.put("XS_YJ", xs_yj_tmp + "");
								sfcz=true;
								break;
							}
						}
						if(!sfcz){
							cjrList.add(cjrMap);
						}
					}
				}
			}
		}	
//		khddMap.put("XS_YJ", jlall + "");
		//Set<String> pnrSet=pnr_hd_map.keySet();//有几个PNR就有几个订单
		Set<String> pnrhkgsSet=pnr_hd_map_tmp.keySet();//有几个PNR就有几个订单
		//ptlb.add("发现"+pnrSet.size()+"个PNR，每个PNR将单独生成订单入库");
		ptlb.add("发现"+pnrhkgsSet.size()+"个PNR，每个PNR将单独生成订单入库");
		int addcnt=0;
		int index = 1;//订单序号,由1开始
		for (String pnrhkgs:pnrhkgsSet) {
			try{
				List<Map<String, String>> hdList=pnr_hd_map_tmp.get(pnrhkgs);
				List<Map<String, String>> cjrList=pnr_cjr_map.get(pnrhkgs);
				for(int i=0;i<cjrList.size();i++){
					cjrList.get(i).put("SXH",i+1+"");
				}
//				ptlb.add("pnr_hkgs："+pnrhkgs+"，航段数量："+hdList.size()+",乘机人数量："+cjrList.size());
				OrderBean orderBean = new OrderBean();
				orderBean.setCjrList(cjrList);
				orderBean.setHdList(hdList);
				Map<String, String> khddMap_new = new HashMap<String, String>(khddMap);
				double xs_yj_hj =0.0;
				for(int i=0;i<cjrList.size();i++){
					double xs_yj = NumberUtils.toDouble(StringUtils.trimToEmpty((String)cjrList.get(i).get("XS_YJ")), 0);
					xs_yj_hj = Arith.add(xs_yj_hj,xs_yj);
					khddMap_new.put("XS_YJ", xs_yj_hj+"");
				}
				String str[] = pnrhkgs.split("_");
				String pnr = "";
				if(str.length>1) {
					pnr = str[0];
				}
				khddMap_new.put("XS_PNR_NO", pnr);
				khddMap_new.put("DDXH", index+"");
				index++;
				String zdcp = khdd_cp.get(pnr);
//				if("true".equals(zdcp)) {
//					khddMap_new.put("ISAUTOBOOK", "true");
//				}
//				String[] tbcpztStrArr = StringUtils.trimToEmpty(khdd_tbcpzt.get(pnr)).split("_",-1);
//				int bPayStatus = NumberUtils.toInt(tbcpztStrArr[0], 0);
//				int failType = NumberUtils.toInt(tbcpztStrArr[1],0);
//				khdd_tbcpzt.put(pnr, bPayStatus+"_"+failType);
//				if(bPayStatus==4 || (bPayStatus==6 && failType==100)){
//					khddMap.put("IFTBCPSB", "1");
//				}
				//添加政策类型节点(按PNR)
				khddMap_new.put("WD_ZCLX", khdd_zclx.get(pnr));
				khddMap_new.put("WD_ZCDM", khdd_zcid.get(pnr));
				//解析政策代码字符串
				resolveZCDM(khdd_zcid.get(pnr),khddMap_new);
				//解析政策说明字符串
				resolveZCSM(khdd_zcsm.get(pnr),khddMap_new.get("ZC_QD"),khddMap_new);
				orderBean.setKhddMap(khddMap_new);
				listOrder.add(orderBean);
				addcnt++;
			}catch (Exception e) {
				e.printStackTrace();
				ptlb.add("pnr："+pnrhkgs+"入库对象封装异常:"+e.getMessage());
				continue;
			}
		}
		ptlb.add("外部订单编号："+orderid+"对应系统订单数量："+addcnt);
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

		String info = "";//航班号1;旧乘机人姓名1;新乘机人姓名1;票号1  这是一组，每组以英文逗号分隔
		List<JpKhdd> jpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpJpListMap = pjb.getJpJpListMap();
		Map<String,List<JpKhddHd>> jpKhddHdListMap =  pjb.getJpKhddHdListMap();
		for(int i=0;i<jpKhddList.size();i++){
			JpKhdd oneJpKhdd = jpKhddList.get(i);
			List<JpKhddHd> jpKhddHdList = jpKhddHdListMap.get(oneJpKhdd.getDdbh());
			List<JpJp> jpJpList = jpJpListMap.get(oneJpKhdd.getDdbh());
			for(int j=0;j<jpKhddHdList.size();j++){
				JpKhddHd oneJpKhddHd = jpKhddHdList.get(j);
				String hbh = StringUtils.trimToEmpty(oneJpKhddHd.getXsHbh()).replace("*", "");
				for(int m=0;m<jpJpList.size();m++){
					JpJp oneJpJp = jpJpList.get(m);
					info +=","+hbh+";"+oneJpJp.getCjr()+";"+oneJpJp.getCjr()+";"+oneJpJp.getTkno();
				}
			}
		}
		if(StringUtils.isNotBlank(info)){
			info = info.substring(1);
		}
		ptlb.add("回填SuccessInfo："+info);
		JipiaoAgentOrderTicketRequest  req = new JipiaoAgentOrderTicketRequest();
		req.setOrderId(NumberUtils.toLong(pjb.getWdbh()));
		req.setSuccessInfo(info);		
		ptlb.add("回填使用的APPKEY"+jpDdsz.getDdJkzh());
		ptlb.add("淘宝票号回填请求："+ URL+ " 接口名：JipiaoAgentOrderDetailRequest 参数："+ req.getTextParams().toString());
		JipiaoAgentOrderTicketResponse  response = execute(req);
		String body = StringUtils.trimToEmpty(response.getBody());
		if (StringUtils.isBlank(body)) {
			body = StringUtils.trimToEmpty(response.getMsg());
		}
		ptlb.add("票号回填返回："+ body);
		if (response.isSuccess()) {
			return true;
		} else {
			//如果票号回填返回失败，则直接查询详情。
			try {
				// 查询订单详细检查是否已经解冻
				ptlb.add("回填失败，检索订单详细，检查淘宝上是否已经解冻");
				TripOrder at = getAtOrder(NumberUtils.toLong(pjb.getWdbh()), ptlb);
				if (at != null && at.getBaseInfo() != null && at.getBaseInfo().getStatus() == 3) {
					ptlb.add("订单已经预定成功，表示票号已经回填了");
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
			TripOrder at = getAtOrder(NumberUtils.toLong(wbdh, 0l), ptlb);
			orderinfo(at, list, ptlb,3l,"0");
		} catch (Exception e) {
			ptlb.add("订单ID:"+wbdh+"请求订单详细失败，失败原因:"+e.getMessage());
		}
		return list;
	}
	private TripOrder getAtOrder(Long orderid, JpPtLog ptlb) throws Exception {
		try {
			JipiaoAgentOrderDetailRequest  req = new JipiaoAgentOrderDetailRequest();
			req.setOrderIds(orderid + "");
			ptlb.add("新接口根据订单id查询订单JipiaoAgentOrderDetailRequest："+ orderid);
			JipiaoAgentOrderDetailResponse response = execute(req);
			ptlb.add("返回："+ response.getBody());
			if (response.isSuccess()) {
				List<TripOrder> list = response.getOrders();
				TripOrder at = list.get(0);
				return at;
			} else {
				throw new Exception(response.getMsg());
			}

		} catch (Exception e) {
			ptlb.add("错误："+ e.getMessage());
			throw e;
		}
	}
	
	public List<OrderBean> getBdetailByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		List<OrderBean> list = new ArrayList<OrderBean>();
		if(StringUtils.isBlank(wbdh)||NumberUtils.toLong(wbdh)==0l){
			ptlb.add("没有传入WBDH");
			return list;
		}
		try {
			TripOrder at = getBdetailAtOrder(NumberUtils.toLong(wbdh, 0l), ptlb);
			orderinfo(at, list, ptlb,3l,"1");
		} catch (Exception e) {
			ptlb.add("订单ID:"+wbdh+"请求订单详细失败，失败原因:"+e.getMessage());
		}
		return list;
	}
	private TripOrder getBdetailAtOrder(Long orderid, JpPtLog ptlb) throws Exception {
		try {
			JipiaoAgentOrderBdetailRequest  req = new JipiaoAgentOrderBdetailRequest();
			req.setOrderId(orderid);
			ptlb.add("新接口根据订单id查询订单JipiaoAgentOrderBdetailRequest："+ orderid);
			JipiaoAgentOrderBdetailResponse response = execute(req);
			ptlb.add("返回："+ response.getBody());
			if (response.isSuccess()) {
				List<TripOrder> list = response.getOrders();
				TripOrder at = list.get(0);
				return at;
			} else {
				throw new Exception(response.getMsg());
			}

		} catch (Exception e) {
			ptlb.add("错误："+ e.getMessage());
			throw e;
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
	/**
	 * 解析政策说明字符串，并将其存放进khddMap里面
	 * 根据政策渠道不同，政策说明字符串也不相同
	 * G 官网[J,3U2200.0,FD0,返点0.0,留点-1.38,留钱0.0,3U2][12 10:17]
	 * Z 自有[M,FD1890,1890,返点3.1,0,留点0.0,留钱-4.0,d174bf64fd5d474aa7799610a56d9979][12 06:27]
     * P 八千翼[H,FD880,返点0.2,留点0.0,留钱0.0,225837027][12 09:24]
     * N NFD[Z,NFD340,返点0.0,留点0.0,留钱1.0,17866466][12 12:27]
     * T 旗舰店[L,3U830,FD830,返点0.0,留点0.0,留钱2.0,null][12 12:01]
     * B
	 */
	private static void resolveZCSM(String zcsm,String zcqd,Map<String,String> khddMap){
		zcsm = StringUtils.trimToEmpty(zcsm);
		String tmpZcsm = zcsm.toUpperCase();
		if(tmpZcsm.indexOf("PAT:A＃C")>-1||tmpZcsm.indexOf("PAT:A#C")>-1){//当fareMemo的格式为"CA大客户，PAT:A#C9CNA003L"
			tmpZcsm = StringUtils.replace(tmpZcsm, "PAT:A＃C", "PAT:A#C");
			String[] zcsmArr = tmpZcsm.split("PAT:A#C",-1);
			String xyh = zcsmArr[1];
			khddMap.put("XYH", xyh);//解析协议号
			return;
		}
		if(StringUtils.isBlank(zcqd)){//默认当做自有政策格式处理
			zcqd = "Z";
		}
		String zcfd = ""; //政策返点
		String wdld = ""; //网店留点
		String wdlq = ""; //网店留钱
		String zc_tfid = ""; //政策ID
		try {
			List<String> tempArr = new ArrayList<String>();
			//51BOOK[E,FD890,返点6.2,留点-0.5,留钱-2.0,155295997][12 08:09]
			if(StringUtils.isNotBlank(zcsm)){
				Pattern pattern = Pattern.compile("(?<=\\[)[^\\]]+");      
				Matcher matcher = pattern.matcher(zcsm);
				
				while(matcher.find())  {
					tempArr.add(matcher.group());
				}
			}
			if(tempArr.size()<=0){
				return;
			}
			String[] str1Arr = tempArr.get(0).split(",");
			if("Z".indexOf(zcqd)>-1){
				zcfd = str1Arr[3].replace("返点", ""); //政策返点
				wdld = str1Arr[5].replace("留点", ""); //网店留点
				wdlq = str1Arr[6].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[7];
			}else if("G,T".indexOf(zcqd)>-1){
				zcfd = str1Arr[3].replace("返点", ""); //政策返点
				wdld = str1Arr[4].replace("留点", ""); //网店留点
				wdlq = str1Arr[5].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[6];
			}else if("P,N".indexOf(zcqd)>-1){
				zcfd = str1Arr[2].replace("返点", ""); //政策返点
				wdld = str1Arr[3].replace("留点", ""); //网店留点
				wdlq = str1Arr[4].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[5];
			}else{
				return;
			}
			double zcfd_d = 0.0;
			try {
				zcfd_d = Arith.div(NumberUtils.toDouble(zcfd), 100);
				khddMap.put("ZC_FD", zcfd_d+""); //政策返点
			} catch (Exception e) {
				khddMap.put("ZC_FD", zcfd);
			}
			khddMap.put("ZC_LD", wdld); //政策留点
			khddMap.put("ZC_LQ", wdlq); //政策留钱
			khddMap.put("ZC_TFID", zc_tfid); //政策ID
		} catch (Exception e) {
//			e.printStackTrace();
//			PlatUtils.ptlog(TaobaoGy2.plat, "", "外部单号"+khddMap.get("GY_DDBH")+",解析政策说明字符串报错"+zcsm);
		}
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
	public static void main(String[] args) throws ApiException {
//		JipiaoAgentOrderDetailRequest  req = new JipiaoAgentOrderDetailRequest();
//		req.setOrderIds("266772527417");
//		TaobaoClient client=	new DefaultTaobaoClient("http://121.41.176.68:30001/airs/conver.shtml", "23422430", "cc8611c55b918ff04e31be47455cbfd6", "json", 20 * 1000, 20 * 1000);
//		JipiaoAgentOrderDetailResponse response= client.execute(req, "61017188f6293cc93698e7936513192ff85f8cde58c09162994593924");
//		System.out.println(response.getBody());	
//		String str = "{\"isSpeedyTicket\":true,\"bPayStatus\":6}";
//		JSONObject jsom = JSONObject.parseObject(str);
//		System.out.println(jsom.getIntValue("bPayStatus"));
//		System.out.println(jsom.getIntValue("abc"));
		String str = "协议价代码PAT:A＃CDK5402041";
		str = StringUtils.replace(str, "PAT:A＃C", "PAT:A#C");
		String[] zcsmArr = str.split("PAT:A#C",-1);
		String xyh = zcsmArr[1];
		System.out.println(xyh);
	}
}
