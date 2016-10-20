package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.BookTicketRefund;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.jpddsz.JpTpdGySuper;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy_tf;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_tf;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;

@Service
public class JpTpdHandleServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(JpTpdHandleServiceImpl.class);
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ShyhbServiceImpl  shyhbServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private  JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	/**
	 * 退票单入库
	 */
	public void toDbTf(TicketRefundBean tfBean,JpDdsz one,String info){
		JpPtLog ptlb = new JpPtLog();
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYTFD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
			ptlb.setPtzcbs(one.getWdpt());
			ptlb.setShbh(one.getShbh());
			ptlb.setYhbh(one.getDdUserid());
			ptlb.setCzsm(one.getDdJkzh()+"供应退废单入库");
			String ddgngj = one.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.add2(info);
			//执行入库
			//入库前业务
			
			toDbBefore(tfBean,one,ptlb);
			BookTicketRefund bookTicketRefund = new BookTicketRefund(tfBean);
			ptlb.add("退票单入库XML："+bookTicketRefund.getParam());
			logger.error("退票单入库XML："+bookTicketRefund.getParam());
			Map<String,Object> param=bookTicketRefund.getParam();
			Map<String,Object> m = procedureServiceImpl.createJpTpd(param);
			int result= (Integer)m.get("result");
			if(result!=0){
				String errmsg = StringUtils.trimToEmpty((String)m.get("errmsg"));
				ptlb.add("退票单入库失败，数据库返回："+errmsg);
				logger.error("退票单入库失败，数据库返回："+errmsg);
				return;
			}
			String tpdh = StringUtils.trimToEmpty((String)m.get("tpdh"));
			ptlb.add("退票单入库成功，退票单号["+tpdh+"]");
			logger.error("退票单入库成功，退票单号["+tpdh+"]");
			JpTpd jpTpd = jpTpdServiceImpl.getJpTpdByTpdh(tpdh, one.getShbh());
			ptlb.setDdbh(jpTpd.getDdbh());
			ptlb.setTfid(VeStr.getValue(tfBean.getTfdMap(), "WBDH"));
			ptlb.setWdid(one.getWdid());
			ptlb.setWdmc(one.getWdmc());
			ptlb.setPnrNo(jpTpd.getXsPnrNo());
			String iftbzt = StringUtils.trimToEmpty(tfBean.getTfdMap().get("IFTBZT"));//是否同步状态
			if("1".equals(iftbzt)){//表示不自动处理退票单
				return;
			}
			//入库后业务
			toDbAfter(tfBean,one,ptlb,jpTpd);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("退票单入库失败，失败原因："+e.getMessage());
			logger.error("退票单入库失败，失败原因："+e.getMessage());
		} finally{
			try {
				jpPtLogServiceImpl.insertLog(ptlb);
			} catch (Exception e) {
				//防止记录日志报错
				e.printStackTrace();
			}
			
		}		
	}
	private void toDbBefore(TicketRefundBean tfBean,JpDdsz one,JpPtLog ptlb) throws Exception{
		Map<String,String> tfdMap = tfBean.getTfdMap();
		//填充公共参数
		tfdMap.put("XGLY", "网店供应导退废单入库");
	}
	private void toDbAfter(TicketRefundBean tfBean,JpDdsz one,JpPtLog ptlb,JpTpd jptpd){
		if(PlatCode.XC.equals(one.getWdpt())){//携程退废单入库后要调[退票确认接口] 
			CtripGy_tf tf = (CtripGy_tf)JpTpdGySuper.getJpTpdGySuper(one);
			tf.refundConfirm(ptlb, tfBean);
		}

		Shyhb shyhb = new Shyhb();
		shyhb.setBh(jptpd.getDdyh());
		shyhb.setShbh(jptpd.getShbh());
		shyhb = shyhbServiceImpl.getEntityById(shyhb);
		
		//自动处理客户
		String auto_tp_xs = StringUtils.trimToEmpty(tfBean.getTfdMap().get("AUTO_TP_XS"));//-1表示不用对销售处理
		ticketRefundHandlXS(jptpd, shyhb, one,auto_tp_xs,ptlb);
		//自动处理采购
		PzSuper.ticketRefundHandle(true, jptpd, shyhb, jpZdtpJkServiceImpl);
	}
	/**
	 * 退票单对销售处理
	 */
	private void ticketRefundHandlXS(JpTpd jptpd, Shyhb shyhb, JpDdsz one,String auto_tp_xs,JpPtLog ptlb){
		String tpdh = jptpd.getTpdh();
		String wbdh = jptpd.getWbdh();
		String xsSfzytp = jptpd.getXsSfzytp(); //退票类型 1自愿，0非自愿
		try {
			if("-1".equals(auto_tp_xs)){
				ptlb.add("这是拆分退票单不用自动与客户办理退废票（多张拆分订单只用提交一次）");
				return;
			}
			Shcsb csBean=shcsbServiceImpl.findCs("1006", shyhb.getShbh());
			String csz1 = StringUtils.trimToEmpty(csBean.getCsz1()) ;
			if(StringUtils.isBlank(csz1)){//默认值为1
				csz1 = "0";
			}
			ptlb.add("参数【1006】（控制是否开启自动与客户办理退废票 0关闭 1开启。默认0）值为："+csz1);
			if("0".equals(csz1)){
				return;
			}
			boolean bl = PzSuper.getKpzt(jptpd, shyhb, jpTpdMxServiceImpl,ptlb);
			ptlb.add("判断是否可以自动办理："+bl);
			if(bl){//表示自动处理客户
				if(PlatCode.TB.equals(one.getWdpt())){
					ptlb.add("该淘宝退票单符合自动与客户办理条件");
					TaobaoGy_tf tbgytf = (TaobaoGy_tf)JpTpdGySuper.getJpTpdGySuper(one);
					if(tbgytf==null){
						ptlb.add("没有实现该网店供应退票单功能。");
						return;
					}
					String wbtpzt = jptpd.getWbtpzt();
					ptlb.add("淘宝退票单外部状态为【"+wbtpzt+",】【1初始；2接受；3确认；8完成】;退票单类型【"+xsSfzytp+"】【0非自愿；1自愿】");
					if("2".equals(wbtpzt)&&"1".equals(xsSfzytp)){
						ptlb.add("淘宝退票单外部状态为【接受（2）】");
						//回填手续费
//						ptlb.add("请求回填手续费接口……");
//						Map<String,String> rtnMap = tbgytf.fillfee(tpdh, shyhb, ptlb, jpddWork_tpd);
//						String status = rtnMap.get("status");
//						if(!"ture".equals(status)){
//							String message = rtnMap.get("message");
//							ptlb.add("请求回填手续费接口返回失败，失败原因："+message);
//							return;
//						}
						//确认退票
						Map<String,String> rtnMap = tbgytf.confirmreturn(wbdh, shyhb, ptlb);
						String status = rtnMap.get("status");
						if(!"true".equals(status)){
							String message = rtnMap.get("message");
							ptlb.add("请求确认退票接口返回失败，失败原因："+message);
							return;
						}
						JpTpd newJptpd = new JpTpd();
						newJptpd.setWbdh(wbdh);
						newJptpd.setShbh(one.getShbh());
						newJptpd.setXsTpzt("1");
						newJptpd.setWbtpzt("3");
						newJptpd.setXgly("淘宝自动确认退票");
						newJptpd.setXgyh(one.getDdUserid());
						jpTpdServiceImpl.updateTpdByWbdh(newJptpd);
						//确认退款
						rtnMap = tbgytf.refundmoneyConfirm(wbdh, shyhb, ptlb);
						status = rtnMap.get("status");
						if(!"true".equals(status)){
							String message = rtnMap.get("message");
							ptlb.add("请求确认退款接口返回失败，失败原因："+message);
							return;
						}
						newJptpd = new JpTpd();
						newJptpd.setWbdh(wbdh);
						newJptpd.setShbh(one.getShbh());
						newJptpd.setXsTpzt("2");
						newJptpd.setWbtpzt("8");
						newJptpd.setXgly("淘宝自动确认退款");
						newJptpd.setXgyh(one.getDdUserid());
						jpTpdServiceImpl.updateTpdByWbdh(newJptpd);
					}else if("3".equals(wbtpzt)){
						ptlb.add("淘宝退废单外部状态为【确认（3）】");
						//确认退票
//						Map<String,String> rtnMap = tbgytf.confirmreturn(wbdh, shyhb, ptlb);
//						String status = rtnMap.get("status");
//						if(!"ture".equals(status)){
//							String message = rtnMap.get("message");
//							ptlb.add("请求确认退票接口返回失败，失败原因："+message);
//							return;
//						}
						//确认退款
						Map<String,String> rtnMap = tbgytf.refundmoneyConfirm(wbdh, shyhb, ptlb);
						String status = rtnMap.get("status");
						if(!"true".equals(status)){
							String message = rtnMap.get("message");
							ptlb.add("请求确认退款接口返回失败，失败原因："+message);
							return;
						}
						JpTpd newJptpd = new JpTpd();
						newJptpd.setWbdh(wbdh);
						newJptpd.setShbh(one.getShbh());
						newJptpd.setXsTpzt("2");
						newJptpd.setWbtpzt("8");
						newJptpd.setXgly("淘宝自动确认退款");
						newJptpd.setXgyh(one.getDdUserid());
						jpTpdServiceImpl.updateTpdByWbdh(newJptpd);
					}else{
						ptlb.add("不符合与客户办理的条件。");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			LogUtil.getTprz(tpdh).error(ptlb.getInfo());
		}

	}
}
