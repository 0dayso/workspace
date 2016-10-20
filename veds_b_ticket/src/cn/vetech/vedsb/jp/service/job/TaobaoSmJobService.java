package cn.vetech.vedsb.jp.service.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.AtsrOrderFlightInfo;
import com.taobao.api.domain.AtsrOrderTravelerInfo;
import com.taobao.api.request.AlitripOrderinfoGetRequest;
import com.taobao.api.response.AlitripOrderinfoGetResponse;

/**
 * 淘宝C站自动扫描订单状态JOB
 * 针对全自动出票、集中出票选择淘宝C站下单后，此JOB自动扫描订单，把机票信息同步到ASMS系统
 * @author  zhangming
 * @version  [版本号]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class TaobaoSmJobService implements ProceClusterableJob {

	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
//	@Override
//	public int dataProce(JobBean jobBean) {
//		String id = jobBean.getData();
//		JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddById(id);
//		if(jpCgdd==null){
//			return -1;
//		}
//		String ddbh = jpCgdd.getDdbh();
//		String tbddbh = ddbh.replaceAll("JP", "98");
//		String shbh = jpCgdd.getShbh();
//		String todayStr = VeDate.getStringDateShort();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String cjdateStr = sdf.format(jpCgdd.getCjdatetime());
//		if(VeDate.getTwoDay(cjdateStr, todayStr)>1){//只扫描1天内下单成功的淘宝订单
//			return -1;
//		}
//		JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.CPS, shbh);
//		if(ptzh==null){
//			LogUtil.getDgrz("TB",ddbh).error("没有找到CPS配置");
//			return 0;
//		}
//		JpPtzcZh tbptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.CPS, shbh);
//		if(tbptzh==null){
//			LogUtil.getDgrz("TB",ddbh).error("没有找到淘宝代购配置");
//			return 0;
//		}
//		CpsLinkTBTZRequest tbtzReq = new CpsLinkTBTZRequest();
//		tbtzReq.setAsmsddbhs(tbddbh);
//		tbtzReq.setAppkey(tbptzh.getUser1());
//		tbtzReq.setTzsj(VeDate.getStringDateShort());
//		tbtzReq.setService("orderNoticeInfo");
//		String retXml;
//		try {
//			LogUtil.getDgrz("TB",ddbh).error("请求CPSLINK淘宝通知信息参数："+XmlUtils.toXml(tbtzReq));
//			retXml = CpsLinkClient.send(tbtzReq, ptzh);
//			LogUtil.getDgrz("TB",ddbh).error("请求CPSLINK淘宝通知信息返回："+retXml);
//			CpsLinkTBTZResponse tbtzRes = (CpsLinkTBTZResponse)XmlUtils.fromXml(retXml, CpsLinkTBTZResponse.class);
//			if(!"0".equals(tbtzRes.getStatus())){
//				return 0;
//			}
//			List<CpsLinkTbtz> tbtzList = tbtzRes.getTzinfo();
//			boolean hasph = true;
//			for(int i=0;i<tbtzList.size();i++){
//				CpsLinkTbtz tbtz = tbtzList.get(i);
//				String hkgs_ddbh = tbtz.getPtddbh();//淘宝订单号
//				String _ddbh = tbtz.getDdbh();//胜意订单编号
//				String ddzt = tbtz.getDdzt();//订单状态 1订单失败  2出票中  3出票成功  4下单成功但是未获取票号
//				String totalMoney = tbtz.getPaidPrice();//支付金额
//				String cjr = tbtz.getCjr();
//				String ph = tbtz.getPh();
//				if(!"3".equals(ddzt)||StringUtils.isBlank(ph)){
//					hasph = false;
//					break;//票号回填成功结束扫描
//				}
//			}
//			if(hasph){
//				zjp(tbtzRes, jpCgdd);
//				return -1;//结束job
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return 0;
//	}
	@Override
	public int dataProce(JobBean jobBean) {
		String id = jobBean.getData();
		JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddById(id);
		if(jpCgdd==null){
			return -1;
		}
		String ddbh = jpCgdd.getDdbh();
		String tbddbh = jpCgdd.getDgdh();//代购单号
		String shbh = jpCgdd.getShbh();
		String todayStr = VeDate.getStringDateShort();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String cjdateStr = sdf.format(jpCgdd.getCjdatetime());
		if(VeDate.getTwoDay(todayStr, cjdateStr)>1){//只扫描1天内下单成功的淘宝订单
			LogUtil.getDgrz("TB",ddbh).error("该订单一天内没有出票");
			return -1;
		}
		JpPtzcZh tbptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, shbh);
		if(tbptzh==null){
			LogUtil.getDgrz("TB",ddbh).error("没有找到淘宝代购配置");
			return 0;
		}
        String url = tbptzh.getUrl1();
        String appkey = tbptzh.getUser1();
        String secret = tbptzh.getPwd1();
        String sessionKey = tbptzh.getUrl2();
        /*url = "http://112.124.54.173:30001/airs/conver.shtml";
        appkey = "21549144";
        secret = "97582f944eeee2a12322360cd9a81a39";
        sessionKey = "61005023e61a6953833632249a2a3351835569b58a7ad122592421237";*///华正的正式账号
        
        String channelName = "胜意";
        String password = MD5Tool.MD5Encode("123456","utf-8");
        Long tbid = NumberUtils.toLong(tbddbh.replace("TB",""));
        AlitripOrderinfoGetRequest req = new AlitripOrderinfoGetRequest();
        req.setChannelName(channelName);
        req.setPassword(password);
        req.setOrderId(tbid);
        TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret,"xml");  
		try {
			AlitripOrderinfoGetResponse res = client.execute(req,sessionKey);
	        Map<String,String> map = res.getParams();
	        if(map!=null) {
	            String loginfo = "";
	            for(String key : map.keySet()) {
	                loginfo += key+"-->"+map.get(key)+"&";
	            }
	            LogUtil.getDgrz("TB",ddbh).error("请求参数："+loginfo);
	        }
	        LogUtil.getDgrz("TB",ddbh).error("请求淘宝查询订单接口返回：" + res.getBody());
	        List<Map<String,String>> cjrMapList = getCjrMapList(res);
	        String rtn = zjp(cjrMapList, jpCgdd);
	        if(StringUtils.isNotBlank(rtn)){
	        	return 0;
	        }else{
	        	return -1;
	        }
		} catch (ApiException e) {
			e.printStackTrace();
			 LogUtil.getDgrz("TB",ddbh).error("请求淘宝查询订单接口异常：",e);
			return 0;
		}
	}
	public List<Map<String,String>> getCjrMapList(AlitripOrderinfoGetResponse res){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<AtsrOrderTravelerInfo> travelerInfoList = res.getTravelerInfoList();//乘客信息
		List<AtsrOrderFlightInfo> orderFlightInfoList = res.getFlightInfoList();//机票信息
//      double cg_zdj = jpKhdd.getCgZdj();
//      double jj_ry = Arith.add(jpKhdd.getCgJsf(), jpKhdd.getCgJsf());
//      double dlfje = Arith.sum(cg_zdj,jj_ry -NumberUtils.toDouble(actual_pay, 0));
        for(int i=0;i<travelerInfoList.size();i++){
        	AtsrOrderTravelerInfo orderTravelerInfo = travelerInfoList.get(i);
        	String passengerNam = orderTravelerInfo.getPassengerName();
        	String tkno = orderTravelerInfo.getTicketNo();
        	Map<String,String> cjrMap = new HashMap<String, String>();
        	cjrMap.put("CJR", passengerNam);
        	cjrMap.put("TKNO", tkno);
        	list.add(cjrMap);
        }
		return list;
	}
	private String zjp(List<Map<String,String>> cjrMapList,JpCgdd jpCgdd){
		Shyhb user = shyhbServiceImpl.getMyBatisDao().getShyhb(jpCgdd.getShbh(), jpCgdd.getCjUserid());
		Map<String,Object> publicParam=new HashMap<String, Object>();
		Map<String,Object> publicMap=new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (Map<String,String> cjrMap : cjrMapList) {// 乘机人信息段
			String cjrxm = StringUtils.trimToEmpty(cjrMap.get("CJR"));
			String ph = StringUtils.trimToEmpty(cjrMap.get("TKNO"));
			if(StringUtils.isBlank(ph)||ph.length()<13||ph.length()>14){
				LogUtil.getDgrz("TB",jpCgdd.getDdbh()).error("没有获取到票号信息");
				return "没有获取到票号信息";
			}
			Map<String, Object> tk = new HashMap<String, Object>();
			tk.put("CJR", cjrxm);
			tk.put("TKNO", ph);
			list.add(tk);
		}
		String datafrom = "手工转机票";
		String ifqzdcp = StringUtils.trimToEmpty(jpCgdd.getIfqzdcp());
		if("1".equals(ifqzdcp)){
			datafrom = "淘宝全自动代购出票";
		}
		publicParam.put("TK", list);
		
		publicMap.put("CG_DDBH", jpCgdd.getDgdh());
		publicMap.put("DDBH", jpCgdd.getDdbh());
		publicMap.put("CGZFKM", jpCgdd.getCgZfkm());
		publicMap.put("CGLY", jpCgdd.getCgly());
		publicMap.put("PTZCBS", jpCgdd.getCgdw());
		publicMap.put("CP_USERID", user.getBh());
		publicMap.put("CP_DEPTID", user.getShbmid());
		publicMap.put("SHBH", user.getShbh());
		publicMap.put("CGJE", jpCgdd.getZfje());
		publicMap.put("DATAFROM", datafrom);
		publicParam.put("PUBLIC", publicMap);
		
		try {
			LogUtil.getDgrz("TB",jpCgdd.getDdbh()).error("TB转机票入参原始:"+publicParam);
			pkgZjpSgServiceImpl.fzjppt(publicParam);
			int result = (Integer)publicParam.get("result");
			if(-1 == result){
				String error = StringUtils.trimToEmpty((String)publicParam.get("perror"));
				if(StringUtils.isBlank(error)){
					error = "AIRTB转机票失败";
				}
				LogUtil.getDgrz("TB",jpCgdd.getDdbh()).error("TB转机票返回:"+result + "===" + error);
				return error;
			}else{
				LogUtil.getDgrz("TB",jpCgdd.getDdbh()).error("TB转机票成功！");
			}
			return "";
		} catch (Exception e) {
			LogUtil.getDgrz("TB",jpCgdd.getDdbh()).error("TB转机票失败",e);
			return "转机票失败";
		}
	}
	public static void main(String[] args) throws ApiException {
		String url = "http://112.124.54.173:30001/airs/conver.shtml";
		String appkey = "21549144";
		String secret = "97582f944eeee2a12322360cd9a81a39";
		String sessionKey = "610130834df2afb810d6074274480f2a1e119da056837022318854220";
        String channelName = "胜意";
        String password = MD5Tool.MD5Encode("123456","utf-8");
        Long tbid = NumberUtils.toLong("245218844220");
        AlitripOrderinfoGetRequest req = new AlitripOrderinfoGetRequest();
        req.setChannelName(channelName);
        req.setPassword(password);
        req.setOrderId(tbid);
        TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret,"xml");
		AlitripOrderinfoGetResponse res = client.execute(req,sessionKey);
        Map<String,String> map = res.getParams();
	}
}
