package cn.vetech.vedsb.jp.jpddsz;

import java.util.List;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy;
import cn.vetech.vedsb.jp.jpddsz.kuxun.KuxunGy;
import cn.vetech.vedsb.jp.jpddsz.qunar.QunarGy;
import cn.vetech.vedsb.jp.jpddsz.qunar.QunarGy_GJ;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_Gj;
import cn.vetech.vedsb.jp.jpddsz.tongcheng.TongchengGy;
import cn.vetech.vedsb.utils.PlatCode;

public abstract class JpddGySuper {
	
	public static JpddGySuper getJpddGySuper(JpDdsz jpDdsz){
		if(PlatCode.TB.equals(jpDdsz.getWdpt())){
			if("1".equals(jpDdsz.getDdGngj())){
				return new TaobaoGy_Gj(jpDdsz);
			}else{
				return new TaobaoGy(jpDdsz);
			}
		}else if(PlatCode.XC.equals(jpDdsz.getWdpt())){
			return new CtripGy(jpDdsz);
		}else if(PlatCode.QN.equals(jpDdsz.getWdpt())){
			if("1".equals(jpDdsz.getDdGngj())){
				return new QunarGy_GJ(jpDdsz);
			}else{
				return new QunarGy(jpDdsz);
			}
		}else if(PlatCode.KX.equals(jpDdsz.getWdpt())){
			return new KuxunGy(jpDdsz);
		}else if(PlatCode.TC.equals(jpDdsz.getWdpt())){
			return new TongchengGy(jpDdsz);
		}
		return null;
	}
	/**
	 * 查询列表支付成功等待出票
	 * 
	 */
	public abstract List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception;
	/**
	 * 手工查询列表支付成功等待出票
	 * 
	 */
	public abstract List<OrderBean> queryOrders(JpPtLog ptlb, String dateStr) throws Exception;
	/**
	 * 回填票号
	 * 
	 */
	public abstract boolean orderOutTicket(JpPtLog ptlb,PlatJpBean pjb) throws Exception;
	
	/**
	 * 根据外部单号导入订单
	 * @param ptlb
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrderBean> getByWbdh(String wbdh,JpPtLog ptlb) throws Exception;
	
}
