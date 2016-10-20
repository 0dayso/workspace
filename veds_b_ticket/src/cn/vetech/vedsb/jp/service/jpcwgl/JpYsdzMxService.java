package cn.vetech.vedsb.jp.service.jpcwgl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.service.MBaseService;


public abstract class JpYsdzMxService<T extends AbstractPageEntity, M extends Mapper<T>> extends MBaseService<T,M>{
	/**
	 * 检查是否已对账
	 * @param zbid
	 * @param shbh
	 * @return
	 */
	public abstract int genCompareCount(String zbid,String shbh);
	/**
	 * 检查当前日期之后是否已对账
	 * @param syrq
	 * @param shbh
	 * @param wdid
	 * @return
	 */
	public abstract int genAfterCompareCount(String syrq,String shbh,String wdid);
	
	/**
	 * 获取需要对账的未到账数据或漏单数据
	 * @param shbh
	 * @param wdid
	 * @param jglx
	 * @param syrqs
	 * @param syrqz
	 * @return
	 */
	public abstract List<Map<String, Object>> genWdzOrLd(String shbh,String wdid,String jglx,String syrqs,String syrqz);
	/**
	 * 删除对账结果
	 * @param shbh
	 * @param zbid
	 * @return
	 */
	public abstract void delResult(String shbh,String zbid);
	/**
	 * 清除到账状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	public abstract void clearDzzt(String shbh,String wdid,String dzrq);
	/**
	 * 清除补单状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	public abstract void clearBdzt(String shbh,String wdid,String dzrq);
	
}
