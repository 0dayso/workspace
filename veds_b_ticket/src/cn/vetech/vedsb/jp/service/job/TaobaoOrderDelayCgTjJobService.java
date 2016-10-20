package cn.vetech.vedsb.jp.service.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.TaobaoCg;

/**
 * 淘宝订单延迟采购提交,赌航变
 * 
 * @author wangshengliang
 * 
 */
@Service
public class TaobaoOrderDelayCgTjJobService {

	private static Logger logger = LoggerFactory.getLogger(TaobaoGyOrderDetailSmJobService.class);

    @Autowired
   	private JpTpdServiceImpl jpTpdServiceImpl;
    
    @Autowired
    private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
    
    @Autowired
    private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
    
    @Autowired
    private ShyhbServiceImpl shyhbServiceImpl;
    

    public void doJob() throws JobExecutionException {
		logger.info("进入淘宝订单延迟采购提交任务");
		/**
		 *  1. 获取访问淘宝接口地址和账号信息
		 */
		List<JpPtzcZh> zhList=jpPtzcZhServiceImpl.getPtzhByPtBsList(TaobaoCg.plat);
		Map<String,Shyhb>  shyhbMap=new HashMap<String, Shyhb>();
		if(CollectionUtils.isEmpty(zhList)){
			return ;
		}
		for (JpPtzcZh zh : zhList) {
			
			String shbh = zh.getShbh();
			/**
			 * 1.采购状态为待提交  销售状态为未取消
			 * 2.起飞日期小于当前日期
			 */
			String jsrq=VeDate.getStringDate();
		    String ksrq=VeDate.getNextDay(jsrq, "-1")+" "+VeDate.getStringTime();
		    
		    Map<String,Object> param=new HashMap<String, Object>();
		    param.put("cg_tpzt", "0");
			param.put("shbh",shbh);
			param.put("wdpt",TaobaoCg.plat);
			param.put("cgly", "TB");
		    param.put("ksrq", ksrq);
		    param.put("jsrq", jsrq);
		    
			List<JpTpd> jptpdList = jpTpdServiceImpl.getTaobaoDgByShbh(param);
			if(CollectionUtils.isEmpty(jptpdList)){
				return ;
			}
			
			for (JpTpd jptpd : jptpdList) {
				Shyhb shyhb = shyhbMap.get(shbh);
				if (shyhb == null) {
					shyhb = shyhbServiceImpl.getMyBatisDao().getShyhb(shbh, jptpd.getDdyh());
				}
				if(shyhb != null){
					shyhbMap.put(shbh, shyhb);
					PzSuper.ticketRefundHandle(true, jptpd, shyhb, jpZdtpJkServiceImpl);
				}
			}
		}
	}
    public static void main(String[] args) {
    	String jsrq=VeDate.getStringDate();
	    String ksrq=VeDate.getNextDay(jsrq, "-1")+" "+VeDate.getStringTime();
	    System.out.println(ksrq);
	    System.out.println(jsrq);
	}
}
