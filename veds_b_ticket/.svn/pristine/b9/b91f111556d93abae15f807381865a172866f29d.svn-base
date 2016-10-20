package cn.vetech.vedsb.jp.service.job;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.TaobaoCg;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.pay.payUtil.Md5Encrypt;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.AtsrOrderRefundInfo;
import com.taobao.api.request.AlitripOrderrefundinfoGetRequest;
import com.taobao.api.response.AlitripOrderrefundinfoGetResponse;

/**
 * @category 查询提交到淘宝退废单的状态
 * 
 * @author wangshengliang
 * @version [版本号, 2015-12-11]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
@Service
public class TaobaoTfpSyncJobService{

    @Autowired
    private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
    @Autowired
    private JpTpdMxServiceImpl jpTpdMxServiceImpl;
    
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;

	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;

	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	
	
	public int doJob(){
		String shbh="";
		try {
				long t0 = System.currentTimeMillis();
			    /**
			     * 1.获取账号信息
			     */
				// 获取访问淘宝接口地址和账号信息
				List<JpPtzcZh> zhList=jpPtzcZhServiceImpl.getPtzhByPtBsList(TaobaoCg.plat);
				if (CollectionUtils.isEmpty(zhList)) {
					throw new Exception("下单相关信息没有设置，请检查平台账号中淘宝相关信息是否设置完整!");
				}

				for (JpPtzcZh zh : zhList) {
					
					/**
					 * 2. 获取淘宝订单信息
					 */
					//select TPDH,DDBH,WBDDBH,(select dgdh from jp_cgdd  where ddbh=t.ddbh) dgdh from   jp_tpd t 
					//where  1=1 and t.wdpt='10100011'  and t.shbh ='HZKFZX' and t.cg_tpzt='2' and t.xs_tpzt <> '9'
				    shbh = zh.getShbh();
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("cg_tpzt", "2");
					param.put("shbh",shbh);
					param.put("wdpt",TaobaoCg.plat);
					param.put("cgly", "TB");
					String data = VeDate.getStringDate();
					param.put("ksrq", VeDate.getNextDay(data, "-1") + " "+ VeDate.getStringTime());
					param.put("jsrq", data);
					List<JpTpd> listdd =jpTpdServiceImpl.getTaobaoDgByShbh(param);
	
					if (CollectionUtils.isEmpty(listdd)) {
						log("",shbh, "未扫描到订单");
						continue;
					}
 					for (JpTpd jptpd : listdd) {
						String tpdh = jptpd.getTpdh(); //退票单号
						String ddbh = jptpd.getDdbh(); //订单编号
						Long taobaoorderid = NumberUtils.toLong(jptpd.getCgDdbh());
	
						TaobaoCg taobaoCg = new TaobaoCg();
						AlitripOrderrefundinfoGetResponse rsp = taobaoCg.orderRefundInfo(zh, taobaoorderid);
						List<AtsrOrderRefundInfo> list = rsp.getRefundInfoList();
						if (CollectionUtils.isEmpty(list)) {
							continue;
						}
	
						String status = list.get(0).getRefundStatus();// 退票状态  1：退票处理中 2：退票成功 3：退票失败
						//1：退票处理中
						if ("1".equals(status)) {
							log(ddbh,shbh, "退票单：" + tpdh + " 退票处理中");
							continue;
						}
						log(ddbh,shbh, rsp.getBody());
	
						//3：退票失败
						if ("3".equals(status)) {
							log(ddbh,shbh, "退票单：" + tpdh + " 退票失败");
							PzSuper.zdtpJkLog(true,false,jptpd, "退票失败:"+rsp.getBody(), jpZdtpJkServiceImpl);
							continue;
						}
						
						List<JpTpdMx> mxList=jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
						if (CollectionUtils.isEmpty(mxList)) {
							log(ddbh,shbh, "查询不到对应的退废单明细信息");
							continue;
						}
						if (mxList.size() != list.size()) {
							log(ddbh,shbh, "退废单明细与淘宝返回信息有差异");
							continue;
						}
						
						PzSuper.zdtpJkLog(true,true,jptpd, "采购退票成功!", jpZdtpJkServiceImpl);
						Map<String,Object> param_cgwc=new HashMap<String, Object>();
						param_cgwc.put("TPDH", tpdh);
						param_cgwc.put("USERID",jptpd.getDdyh());
						param_cgwc.put("TJZT", "");
						param_cgwc.put("SBYY", "");
						param_cgwc.put("CZLX", "2");
						param_cgwc.put("CGST", rsp.getRefundMoney());
						double single_refundMoney=rsp.getRefundMoney()/mxList.size();
						List<Map<String,Object>> tklist=new ArrayList<Map<String,Object>>();
						for(AtsrOrderRefundInfo tr:list){
							Map<String,Object> tkMap=new HashMap<String, Object>();
							tkMap.put("TKNO", tr.getTicketNo());
							tkMap.put("TK_SXF", tr.getRefundFee());
							tkMap.put("TK_JE", single_refundMoney);
							tklist.add(tkMap);
						}
						param_cgwc.put("TK", tklist);
						
						try{
							procedureServiceImpl.f_cgtp(param_cgwc);
						}catch(Exception e){
							e.printStackTrace();
						}
						log(ddbh, shbh,"采购办理f_cgtp入参=>"+VeStr.getValue(param_cgwc, "p_xml"));
						PzSuper.zdtpJkLog(true,jptpd, "采购办理f_cgtp入参=>"+VeStr.getValue(param_cgwc, "p_xml"), jpZdtpJkServiceImpl);
					}
				
				}
				log("",shbh, "查询淘宝退票状态运行结束总耗时" + (System.currentTimeMillis() - t0));
		} catch (Exception e) {
			log("",shbh, e.getMessage());
		}
		
		return 0;
	}
	private static void log(String ddbh,String shbh,String info){
        FileWriter fileWriter = null;
        try {
            String ml = System.getProperty("catalina.home");
            ml = ml + "/logs/taobaotfp/"+shbh+"/";
            File f = new File(ml);
            
            if (!f.exists()) {
                f.mkdir();
            }
            fileWriter = new FileWriter(ml +VeDate.getStringDateShort() +"_"+shbh+ ".log", true);
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
	public  static void main(String[]args){
		
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
		                   
		String channelName = "胜意";
		String password = Md5Encrypt.md5("123456");
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlitripOrderrefundinfoGetRequest req = new AlitripOrderrefundinfoGetRequest();
		req.setChannelName(channelName);//必填 接入方提供的用户名
		req.setPassword(password);//必填 接入方提供的密码，以MD5方式加密后传入
		req.setOrderId(245218844220L);
		AlitripOrderrefundinfoGetResponse rsp=null;
		try {
			rsp = client.execute(req, sessionKey);
			System.out.println(rsp.isSuccess());
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
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
