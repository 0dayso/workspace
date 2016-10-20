package cn.vetech.vedsb.jp.jpddsz;

import java.util.List;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy_tf;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_tf;
import cn.vetech.vedsb.utils.PlatCode;

public abstract class JpTpdGySuper {
	
	public static JpTpdGySuper getJpTpdGySuper(JpDdsz jpDdsz){
		if(PlatCode.TB.equals(jpDdsz.getWdpt())&&"0".equals(jpDdsz.getDdGngj())){
			return new TaobaoGy_tf(jpDdsz);
		}else if(PlatCode.XC.equals(jpDdsz.getWdpt())){
			return new CtripGy_tf(jpDdsz);
		}
		return null;
	}
	/**
	 * 查询退票列表
	 * 手工
	 */
	public abstract List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb,String dateStr) throws Exception;
	
	/**
	 * 查询退票列表
	 * 自动
	 */
	public abstract List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb) throws Exception;
	
	/**
	 * 查询退票列表
	 * 
	 */
	public abstract List<TicketRefundBean> getByWbtpdh(String wbtpdh, JpPtLog ptlb) throws Exception;
//	/**
//	 * 退票复核接口（回填退票手续费给网店）
//	 * @param ptlb
//	 * @param fhParams
//	 * @return
//	 */
//	public abstract Map<String,String> fh(JpPtLog ptlb,Map<String,Object> fhParams);
}
