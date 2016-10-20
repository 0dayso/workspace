package cn.vetech.vedsb.jp.service.jptpgl.zdtp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.pay.payUtil.Md5Encrypt;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripOrderrefundinfoGetRequest;
import com.taobao.api.request.AlitripRefundticketCalculateRequest;
import com.taobao.api.request.AlitripRefundticketRefundapplyRequest;
import com.taobao.api.request.AlitripRefundticketRefundapplyRequest.RefundVoucherInfoDO;
import com.taobao.api.request.AlitripRefundticketUploadvoucherRequest;
import com.taobao.api.response.AlitripOrderrefundinfoGetResponse;
import com.taobao.api.response.AlitripRefundticketCalculateResponse;
import com.taobao.api.response.AlitripRefundticketRefundapplyResponse;
import com.taobao.api.response.AlitripRefundticketUploadvoucherResponse;

/**
 * 对接淘宝C站采购流程接口
 * @author  wangshengliang
 */
@Service
public class TaobaoCg{
    
	/**
	 * 淘宝平台标识 10100011
	 */
    public static final String plat = "10100011";
    
    public static final  String channelName = "胜意";
    
    public static final  String password = Md5Encrypt.md5("123456");
    
    
    @Autowired
   	private JpTpdServiceImpl jpTpdServiceImpl;
    
    @Autowired
    private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
    
    @Autowired
    private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;

    /**
	 * @category 淘宝订单提交采购退票申请接口
	 * @return [参数说明]
	 * @param isAuto  true是自动提交到淘宝退票 false是手动提交到淘宝退票
	 * @return HttpServletRequest [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String applyTicketRefund(boolean isAuto,boolean ishbyw,Shyhb sh_yhb,JpTpd jptpd,JpZdtfpGzsz gzsz){
		try {
			log(jptpd.getDdbh(),"开始提交退废票:"+(isAuto ? "自动退票" : "手动退票"));
			PzSuper.zdtpJkLog(isAuto,jptpd, jptpd.getCgly()+"开始"+("1".equals(jptpd.getCgSfzytp()) ? "自愿退票":"非自愿退票"), jpZdtpJkServiceImpl);
			/**
			 * 1. 获取访问淘宝接口地址和账号信息
			 */
			JpPtzcZh zh=jpPtzcZhServiceImpl.getPtzhByPtBs(TaobaoCg.plat, sh_yhb.getShbh());
			if (null == zh) {
				log(jptpd.getDdbh(),"请检查淘宝平台账号信息是否设置完整!");
				PzSuper.zdtpJkLog(isAuto,jptpd, "请检查淘宝平台账号信息是否设置完整!", jpZdtpJkServiceImpl);
				throw new Exception("下单相关信息没有设置，请检查平台账号中淘宝相关信息是否设置完整!");
			}
			
			/**
			 * 3.获取淘宝订单信息
			 */
			Map<String,Object> tknoMap = jpTpdServiceImpl.getTaobaoDg(sh_yhb.getShbh(), jptpd.getTpdh());
			if (null == tknoMap) {
				log(jptpd.getDdbh(),"未查询到该退票单号的退废信息!");
				PzSuper.zdtpJkLog(isAuto,jptpd, "未查询到该退票单号的退废信息!", jpZdtpJkServiceImpl);
				return "未查询到该订单编号的退废信息!";
			}
			
			
			/**
			 * 6.有无航变
			 * 1)有航变 ,提交非自愿退票
			 * 2)无航变 ,按照退票规则退票
			 */
			String refundTicketType = "NonVoluntary";
			/*
			 * 1：自愿退票（我要改变行程计划、我不想飞）；
			 * 31：自愿退票（填错名字、选错日期、选错航班）；
			 * 32：自愿退票（没赶上飞机、证件忘带了）；
			 * 33：自愿退票（生病了无法乘机（无二甲医院证明））；
			 * 99：自愿退票（其他）；
			 * 6：非自愿退票（航班延误或取消、航班时刻变更等航司原因）；
			 * 21：非自愿退票（身体原因且有二级甲等医院<含>以上的医院证明）； 
			 */
			String refund_reason_type="";
			if (ishbyw) {
				refund_reason_type = "6";
				return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type, tknoMap);
			}
			
			
			/**
			 * 7.判断是否自愿退票
			 * 1)不赌航变直接提交自愿退票
			 * 2)赌航变走延迟提交流程
			 */
			
			//自愿退票
			
			if("1".equals(jptpd.getCgSfzytp())){
				refundTicketType = "Voluntary";
				if(gzsz == null || gzsz.getIshb().equals("1")) {// Ishb 是否赌航变  0 赌航变  1 不赌航变
					return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type,tknoMap);
				}else{
					log(jptpd.getDdbh(),"自愿退票赌航变,走延迟提交退票流程");
					/**
					 * 赌航变
					 * 1)提前起飞时间量
					 * 2)判断最小退票费
					 * 3)判断最小利润率
					 * 4)判断最小利润值
					 */
					//1)判断提前起飞时间量
					if(VeDate.getTwoHourShort(jptpd.getCfsj(),VeDate.getStringDate())<=gzsz.getHbTqsj().doubleValue()){
						log(jptpd.getDdbh(),"按提前起飞时间量提交退票");
						return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type,tknoMap);
					}
					
					//2)判断最小退票费
					if((jptpd.getXsTpsxf().doubleValue()-gzsz.getHbZxtpf().doubleValue())<0){
						log(jptpd.getDdbh(),"按最小退票费提交退票");
						return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type,tknoMap);
					}
					
					//3)判断最小利润率    票面价*退票费率
					if(jptpd.getXsTpfl().doubleValue()-gzsz.getHbZxlrl().doubleValue()<0){
						log(jptpd.getDdbh(),"按最小利润率提交退票");
						return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type,tknoMap);
					}
					
					//4)判断最小利润    
					if(jptpd.getXsTpfl().doubleValue()-gzsz.getHbZxlr().doubleValue()<0){
						log(jptpd.getDdbh(),"按最小利润提交退票");
						return TaobaoTp(sh_yhb, jptpd, isAuto, zh, false,refundTicketType, refund_reason_type,tknoMap);
					}
					
					return "自愿退票赌航变,走延迟提交退票流程";
				}
			}
			
			//非自愿退票
			if(StringUtils.isBlank(jptpd.getCgTpzm())){
				log(jptpd.getDdbh(),"非自愿退票赌航变,无退票证明或航班延误信息");
				return "非自愿退票赌航变,无退票证明或航班延误信息";
			}
			
			return  TaobaoTp(sh_yhb, jptpd, isAuto, zh, true,refundTicketType, refund_reason_type,tknoMap);

		} catch (Exception e) {
			//log("",e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @category 上传附件
	 * @param sh_yhb
	 * @param jptpd
	 * @param isAuto
	 * @param zh
	 * @param isuploadfj 是否上传附件： true上传附件  false 不上传附件
	 * @param refundTicketType
	 * @param refund_reason_type
	 * @return
	 * @throws Exception
	 */
	private String TaobaoTp(Shyhb sh_yhb, JpTpd jptpd, boolean isAuto,JpPtzcZh zh, boolean isuploadfj, String refundTicketType,String refund_reason_type,Map<String,Object>  tknoMap) throws Exception {
		String tknos=VeStr.getValue(tknoMap, "TKNO");
		long orderId=NumberUtils.toLong(VeStr.getValue(tknoMap, "CG_DDBH"));
		/**
		 * 1.非自愿退票上传附件
		 */
		
		List<RefundVoucherInfoDO> rvilist = null;
		if (isuploadfj) {// 退票上传凭证
			rvilist = new ArrayList<RefundVoucherInfoDO>();
			String[] fj_arr = jptpd.getCgTpzm().split(",");
			for (int i = 0; i < fj_arr.length; i++) {
				AlitripRefundticketUploadvoucherResponse rsp = uploadvoucher(zh, jptpd.getCgTpzm(), orderId);
				if (!rsp.isSuccess()) {
					log(jptpd.getDdbh(), "上传退票凭证失败:" + rsp.getBody());
					PzSuper.zdtpJkLog(isAuto,jptpd, "上传退票凭证失败:" + rsp.getBody(), jpZdtpJkServiceImpl);
					throw new Exception("上传退票凭证失败:" + rsp.getBody());
				}
				RefundVoucherInfoDO ri = new RefundVoucherInfoDO();
				ri.setFileId(rsp.getResult());
				rvilist.add(ri);
			}
		}
		
		/**
		 * 2.计算退票费
		 */
		AlitripRefundticketCalculateResponse refundticketCalculateResponse=calculate(zh, tknos, refundTicketType, orderId);
		Long refundfee=null;
		if(refundticketCalculateResponse.isSuccess()){
			refundfee=refundticketCalculateResponse.getRefundFee();
		}
		
		/**
		 * 3.提交退票申请
		 */
		String refund_reason=VeStr.getValue(tknoMap, "ZFYY");
		if(StringUtils.isBlank(refund_reason)){
			/*
			 * 1：自愿退票（我要改变行程计划、我不想飞）；
			 * 31：自愿退票（填错名字、选错日期、选错航班）；
			 * 32：自愿退票（没赶上飞机、证件忘带了）；
			 * 33：自愿退票（生病了无法乘机（无二甲医院证明））；
			 * 99：自愿退票（其他）；
			 * 6：非自愿退票（航班延误或取消、航班时刻变更等航司原因）；
			 * 21：非自愿退票（身体原因且有二级甲等医院<含>以上的医院证明）； 
			 */
			if("6".equals(refund_reason_type)){
				refund_reason="因航班取消延误，申请全退";
			}else if("21".equals(refund_reason_type)){
				refund_reason="由于身体原因，申请病退";
			}
		}
		AlitripRefundticketRefundapplyResponse rsp=refundApply(zh,tknos,refundTicketType,refund_reason_type,refund_reason,refundfee,orderId,rvilist);
		log(jptpd.getDdbh(), rsp.getBody());

		// 采购申请
		jptpd.setCgTjr(sh_yhb.getBh());
		jptpd.setCgTjsj(VeDate.getNow());
		jptpd.setXgly(isAuto ? "自动提交到淘宝退票" : "手动提交到淘宝退票");
		jptpd.setXgsj(VeDate.getNow());
		jptpd.setXgyh(sh_yhb.getBh());

		// 申请成功或者淘宝已经退票
		if (rsp.isSuccess()	|| (!rsp.isSuccess() && "atr_refund_segment_refund".equals(rsp.getSubCode()))) {
			jptpd.setCgTpdh(String.valueOf(rsp.getApplyId()));// 申请单id
			if (isAuto) {
				// ticket_return.setJp_pjhk(ticket_return.getJp_jphk());//
				// 采购退款手续费取客户退票手续费
				// ticket_return.setTp_hxje(ticket_return.getTp_custje());//
				// 采购应退金额 取客户应退金额
			}
			jptpd.setCgTpzt(JpTpd.CG_TPZT_YTJ);
			PzSuper.zdtpJkLog(isAuto,true,jptpd, "提交到淘宝平台退票成功", jpZdtpJkServiceImpl);
		} else {
			jptpd.setCgTpzt(JpTpd.CG_TPZT_YJD);
			String Ptsbyy = rsp.getSubCode() + " " + rsp.getSubMsg();
			if (StringUtils.isBlank(Ptsbyy)) {
				Ptsbyy = "退票申请出现系统异常,请到网页上进行处理!";
			}
			jptpd.setCgZdtpsbyy(Ptsbyy);
			PzSuper.zdtpJkLog(isAuto,false,jptpd, "提交到淘宝平台退票失败:" + Ptsbyy, jpZdtpJkServiceImpl);
		}
		jpTpdServiceImpl.update(jptpd);
		return jptpd.getCgZdtpsbyy();
	}
	
	/**
	 * @category 淘宝退票申请
	 * @param zh    平台账号信息
	 * @param tknos 票号
	 * @param refundTicketType   自愿退票/非自愿退票
	 * @param refund_reason_type 退票原因类型
	 * @param refund_reason      退票原因
	 * @param refundfee          退票金额
	 * @param taobaoOrderId      淘宝订单号
	 * @param fjid               退票证明附件ID
	 * @return
	 */
	public  AlitripRefundticketRefundapplyResponse refundApply(JpPtzcZh zh,String tknos,String refundTicketType,String refund_reason_type,String refund_reason,Long refundfee,long taobaoOrderId,List<RefundVoucherInfoDO> rvilist ){
		// API地址 http://open.taobao.com/doc2/apiDetail.htm?spm=a219a.7395905.0.0.A66SBD&apiId=25783
		/**回参
		 * apply_id     Number  109832432  系统自动生成 
		 * is_success   Boolean true      请求结果是否成功
		 * refund_fee   Number  100                   退款手续费
		 * refund_money Number  500                   退款金额
		 */
		
		AlitripRefundticketRefundapplyResponse rsp=null;
		String url = zh.getUrl1();
		String appkey = zh.getUser1();
		String secret = zh.getPwd1();
		String sessionKey = zh.getUrl2();
		
		
		AlitripRefundticketRefundapplyRequest req = new AlitripRefundticketRefundapplyRequest();
		req.setChannelName(channelName);// 必填 接入方提供的用户名
		req.setPassword(password);// 必填 接入方提供的密码，以MD5方式加密后传入
		req.setRefundTicketType(refundTicketType);// 必填Voluntary:自愿，NonVoluntary:非自愿
		req.setTicketNumbers(tknos);// 需退票的票号，一个订单仅有1个乘机人时无须填写（部分航司同时仅支持1个票号提交退票）
		req.setRefundFee(refundfee);
		req.setOrderId(taobaoOrderId);// 必填 淘宝订单号
		req.setRefundTicketDetail(refund_reason);// 退票原因说明（非自愿退票必填）
		if(StringUtils.isNotBlank(refund_reason_type)){
			req.setRefundReasonType(NumberUtils.toLong(refund_reason_type));
		}
		req.setVoucherInfos(rvilist);
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		try {
			rsp = client.execute(req, sessionKey);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	
	
	/**
	  * @category 京杭查询订单退票信息详情
	  * @param orderid 淘宝订单号
	  * @return [参数说明]
	  * @return AlitripOrderrefundinfoGetResponse [返回类型说明]
	  */
	public  AlitripOrderrefundinfoGetResponse orderRefundInfo(JpPtzcZh zh,Long orderid){
		// API地址 http://open.taobao.com/doc2/apiDetail.htm?spm=a219a.7395905.0.0.4TGr0g&apiId=23999
		AlitripOrderrefundinfoGetResponse rsp=null;
		try {
			String url = zh.getUrl1();
			String appkey = zh.getUser1();
			String secret = zh.getPwd1();
			String sessionKey = zh.getUrl2();
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlitripOrderrefundinfoGetRequest req = new AlitripOrderrefundinfoGetRequest();
			req.setChannelName(channelName);// 必填 接入方提供的用户名
			req.setPassword(password);// 必填 接入方提供的密码，以MD5方式加密后传入
			req.setOrderId(orderid);  //必填 淘宝订单号
			rsp = client.execute(req, sessionKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	/**
	 * @category 京杭退票计算退票费
	 * @param zh
	 * @param tknos
	 * @param refundTicketType
	 * @param taobaoOrderId
	 * @return
	 */
	public  AlitripRefundticketCalculateResponse calculate(JpPtzcZh zh,String tknos,String refundTicketType,long taobaoOrderId){
		//API地址 http://open.taobao.com/doc2/apiDetail.htm?spm=a219a.7395905.0.0.wQvXPf&apiId=25780
		
		/** 回参
		 * can_send_bonus       Boolean false 是否可发红包
		 * is_flight_change     Boolean false 是否航班变更
		 * is_success           Boolean true  请求结果是否成功 
		 * need_try             Boolean false 是否需要重试
		 * quick_refund_support Boolean true  是否支持快速退款 
		 * refund_fee           Number  50          退票手续费
		 * refund_money         Number  500        退款金额
		 */
		AlitripRefundticketCalculateResponse refundticketCalculateResponse=null;
		String url = zh.getUrl1();
		String appkey = zh.getUser1();
		String secret = zh.getPwd1();
		String sessionKey = zh.getUrl2();
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlitripRefundticketCalculateRequest refundticketCalculateRequest = new AlitripRefundticketCalculateRequest();
		refundticketCalculateRequest.setChannelName(channelName);//接入方提供的用户名
		refundticketCalculateRequest.setRefundTicketType(refundTicketType);//Voluntary:自愿，NonVoluntary:非自愿
		refundticketCalculateRequest.setTicketNumbers(tknos);//（最多支持20张票号）需退票的票号，不填写则认为该订单所有票均需要退  例如：781-8511813918,781-8511813919
		refundticketCalculateRequest.setPassword(password);//接入方提供的密码，以MD5方式加密后传入
		refundticketCalculateRequest.setOrderId(taobaoOrderId);//淘宝订单号
		try {
			 refundticketCalculateResponse = client.execute(refundticketCalculateRequest, sessionKey);
		} catch (ApiException e) {
			e.printStackTrace();
		}

		return refundticketCalculateResponse;
	}
	
	
	/**
	 * @category 京杭退票上传凭证
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public  AlitripRefundticketUploadvoucherResponse uploadvoucher(JpPtzcZh zh,String fj,long taobaoOrderId){
		//API地址 http://open.taobao.com/doc2/apiDetail.htm?spm=a219a.7395905.0.0.sMwspk&apiId=26142
		AlitripRefundticketUploadvoucherResponse rsp=null;
		String url = zh.getUrl1();
		String appkey = zh.getUser1();
		String secret = zh.getPwd1();
		String sessionKey = zh.getUrl2();
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlitripRefundticketUploadvoucherRequest req = new AlitripRefundticketUploadvoucherRequest();
		req.setChannelName(channelName);
		req.setOrderId(taobaoOrderId);
		req.setPassword(password);
		req.setFileContent(new FileItem(fj));
		try {
			rsp = client.execute(req, sessionKey);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	private static void log(String ddbh,String info){
        FileWriter fileWriter = null;
        try {
            String ml = System.getProperty("catalina.home");
            ml = ml + "/logs/taobaotfp/";
            File f = new File(ml);
            
            if (!f.exists()) {
                f.mkdir();
            }
            fileWriter = new FileWriter(ml +VeDate.getStringDateShort() + "_apply.log", true);
            if(StringUtils.isNotBlank(ddbh)){
            	ddbh="【" + ddbh + "】";
            }
            fileWriter.write(ddbh + VeDate.getStringDate() + ":" + info + "\r\n");
          
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null != fileWriter){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	public static void main(String []args){
		
		
		/*
    	T_ptzc_zh t_ptzc_zh = new T_ptzc_zh();
		t_ptzc_zh.setUrl1("http://192.168.203.71:8152/gqcgcrawlorder/submitGqd");
		SubmitGqdRequest req = new SubmitGqdRequest();
		req.setZgs("KFZX");
		req.setUsername("baihanbaihan01");
		req.setHbh("SC4661");
		req.setHc("TAOSHA");
		req.setN_qfsj("2016-03-08 07:25");
		req.setOrderId("164961465674");
		req.setCfcity("TAO");
		req.setDdcity("SHA");
		req.setCfjc("TAO");
		req.setDdjc("SHA");
		req.setPassengerInfo("adult,周雪,G");
		String retXml = null;
		try {
			retXml = CpsBuyerInvoke.send(req, t_ptzc_zh);
			System.out.println("接口返回："+retXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(retXml != null){
			SubmitGqdResponse response = (SubmitGqdResponse) XmlUtils.fromXml(retXml, SubmitGqdResponse.class);
	    	if("1".equals(response.getState())){
	    		System.out.println(response.getErrMsg());
	    	} else {
	    		System.out.println(response.getErrMsg());
	    	}
		}*/
    	String url="http://112.124.54.173:30001/airs/conver.shtml";
		String appkey="21549144";
		String secret="97582f944eeee2a12322360cd9a81a39";
		String sessionKey="610130834df2afb810d6074274480f2a1e119da056837022318854220";
		                   
		
		AlitripRefundticketRefundapplyRequest req = new AlitripRefundticketRefundapplyRequest();
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		req.setChannelName(channelName);//必填 接入方提供的用户名
		req.setPassword(password);//必填 接入方提供的密码，以MD5方式加密后传入
		req.setRefundTicketType("Voluntary");//必填Voluntary:自愿，NonVoluntary:非自愿
		req.setTicketNumbers("324-2367051803");// 需退票的票号，一个订单仅有1个乘机人时无须填写（部分航司同时仅支持1个票号提交退票）
		req.setOrderId(260064940220L);//必填 淘宝订单号
		//req.setRefundTicketDetail("因航班取消延误，申请全退");// 退票原因说明（非自愿退票必填） 
		//req.setRefundReasonType(6L);
		AlitripRefundticketRefundapplyResponse rsp=null;
		try {
			rsp = client.execute(req, sessionKey);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		//System.out.println(rsp.getBody());
		
		//{"alitrip_refundticket_refundapply_response":{"apply_id":14600348,"is_success":true,"request_id":"11iese1luctkc"}}
		//{"alitrip_refundticket_refundapply_response":{"apply_id":14600348,"is_success":true,"request_id":"11iep9ssab6ed"}}
		//{"error_response":{"code":15,"msg":"Remote service error","sub_code":"atr_refund_segment_refund","request_id":"zqcjjgh8bgd6"}}
		//{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.invalid-orderid:NO_DATA","sub_msg":"订单数据不存在","request_id":"ishjug0oc2to"}}
          
		/*
	    
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlitripOrderrefundinfoGetRequest req = new AlitripOrderrefundinfoGetRequest();
		req.setChannelName(channelName);//必填 接入方提供的用户名
		req.setPassword(password);//必填 接入方提供的密码，以MD5方式加密后传入
		req.setOrderId(130971664513L);
		try {
			AlitripOrderrefundinfoGetResponse rsp = client.execute(req, sessionKey);
			System.out.println(rsp.isSuccess());
			System.out.println(rsp.getBody());
			//{"alitrip_orderrefundinfo_get_response":{"refund_info_list":{"atsr_order_refund_info":[{"passenger_name":"宋臣浩","refund_segment":"WUH-WNZ","refund_status":"1","refund_type":"1","ticket_no":"811-2308820571"}]},"refund_money":0,"request_id":"z25f3ow4kj1e"}}
			//{"alitrip_orderrefundinfo_get_response":{"refund_info_list":{"atsr_order_refund_info":[{"passenger_name":"宋臣浩","refund_segment":"WUH-WNZ","refund_status":"2","refund_type":"1","ticket_no":"811-2308820571"}]},"refund_money":346,"request_id":"zddsumvov4l5"}}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlitripRefundticketUploadvoucherRequest req = new AlitripRefundticketUploadvoucherRequest();
		req.setChannelName("feiye");
		req.setOrderId(974032987432L);
		req.setPassword("7b1e6dd7e98f6e84f246cb4783880449");
		req.setFileContent(new FileItem("/tmp/file.txt"));
		AlitripRefundticketUploadvoucherResponse rsp=null;
		try {
			rsp = client.execute(req, sessionKey);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}*/
		/*
		 {
			"alitrip_orderrefundinfo_get_response": {
			    "refund_info_list": {
			        "atsr_order_refund_info": [
			            {
			                "passenger_name": "郑世辉",
			                "refund_segment": "CSX-FOC",
			                "refund_status": "2",
			                "refund_type": "1",
			                "ticket_no": "731-2399949000"
			            },
			            {
			                "passenger_name": "林锦清",
			                "refund_segment": "CSX-FOC",
			                "refund_status": "2",
			                "refund_type": "1",
			                "ticket_no": "731-2399948999"
			            }
			        ]
			    },
			    "refund_money": 250,
			    "request_id": "10fc2lg1wj7d7"
			}
         }
	*/
    }
}
