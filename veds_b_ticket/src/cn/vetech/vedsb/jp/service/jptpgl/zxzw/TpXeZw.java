package cn.vetech.vedsb.jp.service.jptpgl.zxzw;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cpsz.JpBspTpsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdCzrz;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.cpsz.JpBspTpszServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdCzrzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;

@Service
public class TpXeZw{

	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	
	@Autowired
	private JpHdServiceImpl jpHdServiceImpl;
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	
	@Autowired
	private  JpTpdCzrzServiceImpl jpTpdCzrzServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	
	@Autowired
	private JpBspTpszServiceImpl jpBspTpszServiceImpl;
	/**
	 * 自动xe座位
	 * 
	 * @throws Exception
	 */
	public void xezw(boolean isAuto, Shyhb sh_yhb, JpTpd jptpd,JpKhdd jpKhdd,JpPz jppz) throws Exception {
		
		TpXePassengerTx tpautotx = SpringContextUtil.getBean(TpXePassengerTx.class);// 提醒、
		String tpdh = jptpd.getTpdh();
		String shbh = sh_yhb.getShbh();
		
		List<JpTpdMx> mxList=jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
		if (CollectionUtils.isEmpty(mxList)) {
			String msg = "没有获取到PNR为"+jptpd.getCgPnrNo()+"的退废明细订单信息！";
			// 修改退废单主表 以免数据再次被查询
			TpXePassengerTx.updateJpTpd(isAuto, JpTpd.CG_ZDQXZT_QXSB,jptpd, msg, sh_yhb.getBh(),jpTpdServiceImpl);
			//TpXeZw.writeXezwOperationLog(isAuto, jpTpdCzrzServiceImpl, tpdh,sh_yhb.getShbh(), msg, sh_yhb);//退废票异动日志
			PzSuper.zdtpJkLog(isAuto,false,jptpd, msg, jpZdtpJkServiceImpl);
			if(isAuto){
				msg = msg+"请及时手工处理！";
				tpautotx.msgtx(msg, msg,sh_yhb, jptpd);// 消息提醒
			} else {//手动取消需要将信息提示给用户
				throw new Exception(msg);
			}
			return;
		}
		
		int ignoreCjrZjhm = 0;
		Object[] cjrInfo = null;
		String cjr = "";
		String hd = "";
		try{
			cjrInfo = getCjr(jptpd,mxList);
			cjr = (String)cjrInfo[0];
			ignoreCjrZjhm = ((Integer)cjrInfo[1]).intValue();
			hd=getHd(jptpd.getTpdh(),shbh);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "获取PNR为"+jptpd.getCgPnrNo()+"的乘机人信息或航段信息有误！";
			// 修改退废单主表 以免数据再次被查询
			TpXePassengerTx.updateJpTpd(isAuto, JpTpd.CG_ZDQXZT_QXSB,jptpd, msg, sh_yhb.getBh(),jpTpdServiceImpl);
			TpXeZw.writeXezwOperationLog(isAuto, jpTpdCzrzServiceImpl, tpdh, msg, sh_yhb);
			PzSuper.zdtpJkLog(isAuto,false,jptpd, msg, jpZdtpJkServiceImpl);
			if(isAuto){
				msg = msg+"请及时手工处理！";
				tpautotx.msgtx(msg, msg,sh_yhb, jptpd);// 消息提醒
			} else {//手动取消需要将信息提示给用户
				throw new Exception(msg);
			}
			return;
		}
		
		
		ignoreCjrZjhm=1;
		
		JpBspTpsz bspTpsz=new JpBspTpsz();
		bspTpsz.setShbh(shbh);
		bspTpsz=jpBspTpszServiceImpl.getEntityById(bspTpsz);
		if (bspTpsz != null) {
			ignoreCjrZjhm = NumberUtils.toInt(bspTpsz.getYzzjhm(), 1);
		}
		/*
		if(ignoreCjrZjhm == 0){
			Ve_csb_new cs9936 = this.ve_csb_new_dao.getVe_csb_new("9936", shbh);//否验证乘机人证件号码
			if(cs9936 == null || StringUtils.isBlank(cs9936.getYdhm())){
				throw new Exception("取消座位时无法获取否验证乘机人证件号码设置值！");
			}
			String cs9936Value = cs9936.getYdhm().trim();
			if("0".equals(cs9936Value)){//页面设置不需验证证件号码，也就是忽略乘机人证件号码
				ignoreCjrZjhm = 1;
			}
		}
		*/
		
		/** 执行自动取消座位的方法 */
		TpXePassenger tpauto = SpringContextUtil.getBean(TpXePassenger.class);
		tpauto.excute(sh_yhb, jpKhdd, jptpd,jppz, hd, cjr,ignoreCjrZjhm, isAuto);
	}
	
	/**
	 * 取退票航段信息
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明] 格式：航班号,出发城市,到达城市,出发时间，到达时间，仓位|…
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private String getHd(String tpdh,String shbh)  throws Exception {
		StringBuffer hdSb = null;
		List<JpHd> hdList = null;
		try {
			//hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			hdList = jpHdServiceImpl.getJpHdByTpdh(tpdh, shbh);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("取退票航段错误。" + e.getMessage());
		}
		
		if (CollectionUtils.isEmpty(hdList)) {
			throw new Exception("退票航段为空,请检查机票退票航段");
		}
		
		for(JpHd hd:hdList){
			String hbh = hd.getCgHbh();
			if(!"ARNK".equals(hbh)){
				if (hdSb == null) {
					hdSb = new StringBuffer();
				} else {
					hdSb.append("|");
				}
				hdSb.append(hbh);
				hdSb.append(",");
				hdSb.append(hd.getCfcity());
				hdSb.append(",");
				hdSb.append(hd.getDdcity());
				hdSb.append(",");
				String cfsj = hd.getCfsj();
				if (cfsj.length() > 10) {
					cfsj = cfsj.substring(0, 10);
				}
				hdSb.append(cfsj);
				hdSb.append(",");
				String cgCw=hd.getCgCw();
				//HD的舱位为子舱位是需要传主舱位
				if(StringUtils.isNotBlank(cgCw)){
					cgCw=StringUtils.trimToEmpty(cgCw);
					cgCw=cgCw.substring(0,1);
				}
				hdSb.append(cgCw);
			}
		}
		return hdSb.toString();
	}
	
	/**
	 * 取需XE乘机人
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明] 格式：乘机人姓名,证件号|…
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private Object[] getCjr(JpTpd jptpd,List<JpTpdMx> mxList) throws Exception {
		String returnStr = null;
		int ignoreCjrZjhm = 0;
		StringBuffer cjrSb =null;
		List<JpKhddCjr> cjrList = null;
		try {
			cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jptpd.getDdbh(), jptpd.getShbh());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("取订单乘机人错误。" + e.getMessage());
		}
		if (CollectionUtils.isEmpty(cjrList)) {
			throw new Exception("取订单乘机人错误，乘机人为空。错误：[未知]");
		}
		for (JpTpdMx mx: mxList) {
			if (cjrSb == null) {
				cjrSb = new StringBuffer();
			} else {
				cjrSb.append("|");
			}
			cjrSb.append(getCjrlx(mx.getCjr(),mx.getCjrlx())).append(",").append(mx.getZjhm());
		}
		if (cjrSb != null) {
			returnStr = cjrSb.toString();
		}
		if(StringUtils.isBlank(returnStr)){
			ignoreCjrZjhm = 1;
			returnStr=jptpd.getCjr().replaceAll(",",", |");
			returnStr+=",";
		}
		return new Object[]{returnStr, ignoreCjrZjhm};
	}
	
	/**
	 * 将取消座位成功或失败信息写入退废日志
	 * @param isAuto 是否自动Job扫描
	 * @param tpdh 退废ID
	 * @param cznr 操作内容
	 * @param jpTpdCzrzServiceImpl
	 * @param sh_yhb
	 * @throws Exception
	 */
	public static void writeXezwOperationLog(boolean isAuto,JpTpdCzrzServiceImpl  jpTpdCzrzServiceImpl,String tpdh, String cznr, Shyhb sh_yhb) throws Exception{
		
		JpTpdCzrz czrz=new JpTpdCzrz();
		czrz.setYwid(tpdh);
		czrz.setShbh(sh_yhb.getShbh());
		czrz.setCzsj(VeDate.getNow());
		czrz.setCzly((isAuto ? "自动" : "手动") + "取消座位");
		czrz.setCzr(sh_yhb.getBh());
		czrz.setCznr(VeStr.substrByte(cznr, 4000));//操作内容
		try {
			jpTpdCzrzServiceImpl.update(czrz);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 获取乘机人类型英文简写
	 * 当乘机人名称为拼音时乘机人类型与乘机人名称要空格间隔
	 * 例如: 王胜良CHD ;WANG/SHENGLIANG CHD
	 */
	public static String getCjrlx(String cjr,String cjrlx){
		//1.成人(ADULT)  2.儿童(CHD)  3.婴儿(INF)  ,
		String cjr_lx = "";
		if ("1".equals(cjrlx)) {
			cjr_lx = cjr;
		} else if ("2".equals(cjrlx)) {
			cjr_lx = cjr + (VeStr.isChinese(cjr) ? "CHD" : " CHD");
		}
		if ("3".equals(cjrlx)) {
			cjr_lx = cjr + (VeStr.isChinese(cjr) ? "INF" : " INF");
		}
		return cjr_lx;
	}
}
