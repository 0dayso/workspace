package cn.vetech.vedsb.platpolicy.cpslink.service;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.platpolicy.cpslink.request.PayNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.response.NoticeRes;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;

@Service
public class CpslinkPayNoticeService {
	
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	public NoticeRes execute(JpPtLog log,PayNotifyRequest preq,JpPtzcZh jpPtzcZh,String shbh) throws Exception{
//		Shyhb user = SessionUtils.getShshbSession();
//		String shbh = user.getShbh();
		Shshb shshb = shshbServiceImpl.getShbhByBh(shbh);
		NoticeRes res = new NoticeRes();
		log.add("采购支付通知"+PlatCode.getPtname(preq.getPlatcode()));
		log.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		log.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		log.setBy1("1001901");//国内机票
		log.setYwlx(DictEnum.PTLOGYWLX.ZFTZ.getValue());
		log.setCzsm("采购支付通知");
		log.setPtzcbs(preq.getPlatcode());
		log.setShbh(shbh);
		log.setYhbh(shshb.getBh());
		try {
			String status = preq.getPayStatus();
			String czztmc = "";
			String orderid = preq.getOutOrderNo();
			if(StringUtils.isBlank(orderid)){
				throw new Exception("没有传入订单编号");
			}
			JpKhdd jpkhdd = this.jpKhddServiceImpl.getJpKhddByCgDdbh(shbh, orderid, preq.getPlatcode());
			if(jpkhdd == null || StringUtils.isBlank(jpkhdd.getDdbh())){
				throw new Exception("在系统中没有找到对应的订单【"+orderid+"】");
			}
			String zflsh = preq.getPayNumber();
			jpkhdd.setCgZflsh(zflsh);
			this.jpKhddServiceImpl.update(jpkhdd);//更新订单表(写入支付流水号)
			log.setDdbh(jpkhdd.getDdbh());
			JpCgdd jpCgdd = this.jpCgddServiceImpl.getDdByZflsh(orderid);
			if(jpCgdd == null){
				throw new Exception("查询采购订单为空!");
			}
			jpCgdd.setCgdw(preq.getPlatcode());
			if("0".equals(status)){
				jpCgdd.setZfje(NumberUtils.toDouble(preq.getPaymentAmount()));//支付金额
				jpCgdd.setJyzt("1");//支付成功
				jpCgdd.setZt("1");//支付成功
				jpCgdd.setTradeNo(preq.getPayNumber());
				czztmc = "支付完成，等待供应方出票";
				String zffs = preq.getPayment();
				jpCgdd.setPaykind(zffs);//支付对接码
				log.add("传入的支付方式:"+zffs);
				JpPtzcFzsz jpPtzcFzsz = this.jpPtzcFzszServiceImpl.genZfkmByDjm("2",shbh,zffs,preq.getPlatcode());
				if(jpPtzcFzsz == null){
					log.add("按平台未找到系统中对应支付科目");
					throw new Exception("按平台未找到系统中对应支付科目");
				}
				zffs = jpPtzcFzsz.getXtZfkm();
				log.add("转换后的支付科目:"+zffs);
				jpCgdd.setCgZfkm(zffs);
			}else{
				jpCgdd.setJyzt("2");
				jpCgdd.setZt("2");//支付失败
				czztmc = "订单支付失败";
				res.setStatus(NoticeRes.FAILL);
			}
			log.add(czztmc);
			this.jpCgddServiceImpl.update(jpCgdd);
		} catch (Exception e) {
			e.printStackTrace();
			log.add("平台订单支付异常");
			res.setStatus(NoticeRes.FAILL);
			res.setMsg(e.getMessage());
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(log);//写日志
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return res;
	}
}
