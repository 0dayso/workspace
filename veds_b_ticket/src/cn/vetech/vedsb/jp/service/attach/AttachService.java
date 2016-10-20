package cn.vetech.vedsb.jp.service.attach;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.business.cache.BcityCacheService;
import org.vetech.core.business.cache.HkgsCacheService;
import org.vetech.core.business.cache.VeclassCacheService;

import cn.vetech.vedsb.common.attach.AttachClass;
import cn.vetech.vedsb.common.attach.AttachExecute;
import cn.vetech.vedsb.common.service.base.ShjcsjServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;

/**
 * 链式对象，通过代号取对应的数据。
 * 
 * 如通过数据字典的ID获取bclass对象
 * 
 * 
 * List<Map> lmap = new ArrayList<Map>();
 * 
 * Map sm = new HashMap();
 * 
 * sm.put("BZBZ", "0002039");
 * 
 * sm.put("DH", "0002042");
 * 
 * sm.put("YHBH", "XIAOXINADMIN");
 * 
 * lmap.add(sm);
 * 
 * attachService.veclass("BZBZ").veclass("DH").shyhb("YHBH", "XIAOXIN").execute(lmap);
 * 
 * @author zhanglei
 *
 */
@Service
public class AttachService {
	private static ThreadLocal<Set<AttachClass>> attachThread = new ThreadLocal<Set<AttachClass>>();

	// 需要引入的服务
	@Autowired
	private VeclassCacheService veclassCacheService;
	@Autowired
	private HkgsCacheService hkgsCacheService;
	@Autowired
	private BcityCacheService bcityCacheService;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private ShjcsjServiceImpl shjcsjServiceImpl;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;

	private void set(AttachInf attachInf, String[] attrname, Object[] fixedvalue) {
		Set<AttachClass> set = attachThread.get();
		if (set == null) {
			set = new HashSet<AttachClass>();
			attachThread.set(set);
		}
		AttachClass ac = new AttachClass();
		ac.setAttachInf(attachInf);
		ac.setFixedvalue(fixedvalue);
		ac.setAttrname(attrname);
		set.add(ac);
	}

	private void set(AttachInf attachInf, String[] attrname) {
		set(attachInf, attrname, null);
	}

	public void execute(Object list) {
		Set<AttachClass> localset = attachThread.get();
		attachThread.remove();
		AttachExecute.execute(list, localset);
	}

	/**
	 * 通过数据字典ID 获取数据字典的Bean对象
	 * 
	 * @param attr
	 * 
	 *            存入的是 @VeclassCache 对象
	 * 
	 * @return
	 */
	public AttachService veclass(String attr) {
		set(veclassCacheService, new String[] { attr });
		return this;
	}

	/**
	 * 通过航空公司二字码获取航空公司对象
	 * 
	 * @param attr
	 *            bean中或map中 属性名称 如：getHkgs 那么传入 "Hkgs"
	 * 
	 *            存入的是 @org.vetech.core.business.cache.HkgsCache 对象
	 * @return
	 */
	public AttachService hkgs(String attr) {
		set(hkgsCacheService, new String[] { attr });
		return this;
	}

	/**
	 * 通过NBBH获取bcity对象
	 * 
	 * @param attr
	 *            存入的是 @BcityCache 对象
	 * @return
	 */
	public AttachService bcity(String attr) {
		set(bcityCacheService, new String[] { attr });
		return this;
	}

	/**
	 * 获取用户对象
	 * 
	 * @param yhbhattr
	 *            用户编号所在的属性名
	 * @param shbhvalue
	 *            商户编号的值
	 * @return
	 */
	public AttachService shyhb(String yhbhattr, String shbhvalue) {
		set(shyhbServiceImpl, new String[] { yhbhattr }, new Object[] { shbhvalue });
		return this;
	}

	/**
	 * 获取用户对象
	 * 
	 * @param yhbhattr
	 *            用户编号所在的属性名称
	 * @param shbhattr
	 *            商户编号所在的属性名称
	 * @return
	 */
	public AttachService shyhb2(String yhbhattr, String shbhattr) {
		set(shyhbServiceImpl, new String[] { yhbhattr, shbhattr }, null);
		return this;
	}

	/**
	 * 获取基础数据对象
	 * 
	 * @param bhattr
	 *            编号所在属性的名称
	 * @param shbhvalue
	 *            商户编号的值
	 * @return
	 */
	public AttachService shjcsj(String bhattr, String shbhvalue) {
		set(shjcsjServiceImpl, new String[] { bhattr }, new Object[] { shbhvalue });
		return this;
	}

	/**
	 * 获取基础数据对象
	 * 
	 * @param bhattr
	 *            编号所在属性的名称
	 * @param shbhvalue
	 *            商户编号所在的属性名称
	 * @return
	 */
	public AttachService shjcsj2(String bhattr, String shbhattr) {
		set(shjcsjServiceImpl, new String[] { bhattr, shbhattr }, null);
		return this;
	}
	
	/**
	 * 通过网店id获取网店对象
	 * 
	 * @param wdidattr
	 *            网店id所在的属性名
	 * @return
	 */
	public AttachService wdzl(String wdidattr) {
		set(wdzlszServiceImpl, new String[] {wdidattr});
		return this;
	}
	
	/**
	 * 通过网店id获取网店对象
	 * 
	 * @param wdidattr
	 *            网店id所在的属性名
	 * @return
	 */
	public AttachService zfkm(String zfkmattr) {
		set(shzfkmServiceImpl, new String[] {zfkmattr});
		return this;
	}
}
