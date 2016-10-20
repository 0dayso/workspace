package cn.vetech.vedsb.platpolicy.cps.service;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.platpolicy.NoticeStatus;
import cn.vetech.vedsb.platpolicy.cps.request.pay.PayNotifyRequest;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class CpsPayNoticeService {
	@Autowired
	private CpsService cpsService;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	public NoticeStatus execute(JpCgdd jpCgdd, Map<String, String[]> params){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		NoticeStatus s = new NoticeStatus();
		JpPtLog ptLog=new JpPtLog();
		ptLog.add("采购支付采购通知"+PlatCode.CPS);
		ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		ptLog.setBy1("1001901");//国内机票
		ptLog.setYwlx(DictEnum.PTLOGYWLX.ZFTZ.getValue());
		ptLog.setCzsm("采购 支付返回通知");
		try {
			PayNotifyRequest request = this.cpsService.payNotify(params);
			if(null == request){
				throw new Exception("CPS通知没有返回数据");
			}
			String orderNo = request.getOrderNo();
			String orderType = request.getOrderType();
			String zffs = request.getPayment();
			if(StringUtils.isBlank(orderNo)){
				throw new Exception("CPS通知没有返回订单编号");
			}
			if(StringUtils.isBlank(orderType)){
				throw new Exception("CPS通知没有返回订单类型");
			}
			ptLog.add("传入支付方式【" + zffs + "】");
			if ("312013301".equals(zffs) || "支付宝".equals(zffs)) {// 支付宝
                zffs = "10063971";
            } else if ("312013304".equals(zffs) || "汇付天下".equals(zffs)) {// 汇付天下
                zffs = "1006394";
            } else if ("312013318".equals(zffs) || "财付通".equals(zffs)) {// 财付通
                zffs = "16063781";
            }else if ("312013300".equals(zffs) || "预存款".equals(zffs)) {// 预存款
                zffs = "312013300";
            }else if ("快钱".equals(zffs)) {
                zffs = "1606362";
            }
			ptLog.add("转换后支付方式【" + zffs + "】");
			JpPtzcFzsz jpPtzcFzsz = this.jpPtzcFzszServiceImpl.genZfkmByDjm("2",shbh,zffs,PlatCode.CPS);
			String zfkm = "";
			if(jpPtzcFzsz!=null){
				zfkm = jpPtzcFzsz.getXtZfkm();
			}
			if("1".equals(orderType)){//机票订单支付通知
				//JpCgdd jpCgdd = this.jpCgddServiceImpl.getDdByZflsh(orderNo);//查支付记录根据平台订单编号查
				this.jpkhddNotice(request,shbh,jpCgdd,zfkm);
			}else if("2".equals(orderType)){//改签订单支付通知
				
			}else{
				throw new Exception("传入订单类型错误");
			}
		} catch (Exception e) {
			ptLog.add(e.getMessage());
			e.printStackTrace();
			s.setMsg(e.getMessage());
			s.setStatus(-1);
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);//写日志
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	/**
	 * 机票订单支付通知
	 * @throws Exception
	 */
	private void jpkhddNotice(PayNotifyRequest request,String shbh,JpCgdd jpCgdd,String zffs) throws Exception{
		String orderNo = request.getOrderNo();
		JpKhdd jpkhdd = this.jpKhddServiceImpl.getJpKhddByCgDdbh(shbh, orderNo, PlatCode.CPS);
		 if (null == jpkhdd || StringUtils.isBlank(jpkhdd.getDdbh())) {
	            throw new Exception("在系统中没有找到对应的订单【" + orderNo + "】");
	     }
		 String tradeno = request.getPaymentTransaction();//支付流水号
		 jpkhdd.setCgZflsh(tradeno);
		 this.jpKhddServiceImpl.update(jpkhdd);//更新订单表
		 if(null!=jpCgdd){
			 JpCgdd jpCgdd0 = new JpCgdd();
			 jpCgdd0.setId(jpCgdd.getId());
			 jpCgdd0.setPlatzt("10");//支付完成,等待出票
			 jpCgdd0.setZt("1");//支付成功
			 jpCgdd0.setJyzt("1");//交易成功
			 jpCgdd0.setTradeNo(request.getPaymentTransaction());
			 jpCgdd0.setCgZfkm(zffs);
			 this.jpCgddServiceImpl.update(jpCgdd0);
		 }
	}
}
