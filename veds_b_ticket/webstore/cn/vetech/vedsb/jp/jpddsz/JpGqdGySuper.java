package cn.vetech.vedsb.jp.jpddsz;

import java.util.List;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy_gq;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_gq;
import cn.vetech.vedsb.utils.PlatCode;

public abstract class JpGqdGySuper {
	
	public static JpGqdGySuper getJpGqdGySuper(JpDdsz jpDdsz){
		if(PlatCode.XC.equals(jpDdsz.getWdpt())&&"0".equals(jpDdsz.getDdGngj())){
			return new CtripGy_gq(jpDdsz);
		}else if(PlatCode.TB.equals(jpDdsz.getWdpt())){
			return new TaobaoGy_gq(jpDdsz);
		}
		return null;
	}
	/**
	 * 查询退票列表
	 * 自动
	 */
	public abstract List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb) throws Exception;
	/**
	 * 查询退票列表
	 * 手工
	 */
	public abstract List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb,String dateStr) throws Exception;
//	/**
//	 * 改签处理
//	 * @param ptlb
//	 * @param fhParams
//	 * @return
//	 */
//	public abstract Map<String,String> gq(JpPtLog ptlb,Map<String,Object> fhParams);
}
