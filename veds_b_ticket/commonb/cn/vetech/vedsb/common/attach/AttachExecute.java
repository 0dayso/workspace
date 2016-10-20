package cn.vetech.vedsb.common.attach;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.Reflections;

/**
 * 实际执行方法
 * 
 * @author zhanglei
 *
 */
public class AttachExecute {
	private static Logger logger = LoggerFactory.getLogger(AttachExecute.class);
	private static final String GETTER_PREFIX = "get";

	public static void execute(Object object, Set<AttachClass> localset) {
		if (localset.isEmpty()) {
			return;
		}
		if (object == null) {
			return;
		}
		List listnew = null;
		if (object instanceof List) {
			listnew = (List) object;
		} else {
			listnew = new ArrayList();
			listnew.add(object);
		}

		int count = listnew.size();
		if (count < 1) {
			return;
		}
		Object listone = listnew.get(0);
		if (listone instanceof AbstractPageEntity) {
			AbstractPageEntity a = (AbstractPageEntity) listone;
			// 获取这个bean的Method
			Map<String, Method> methodMap = new HashMap<String, Method>();
			for (AttachClass ac : localset) {
				String[] attrArray = ac.getAttrname();
				if (attrArray == null || attrArray.length < 1) {
					continue;
				}
				for (String attr : attrArray) {
					String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(attr);
					Method method = Reflections.getAccessibleMethodByName(a, getterMethodName);
					if (method == null) {
						continue;
					}
					methodMap.put(attr, method);
				}
			}
			// 循环List
			for (int i = 0; i < count; i++) {
				AbstractPageEntity a1 = (AbstractPageEntity) listnew.get(i);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				// 调用Method先获取到所有Bean中数据
				Set<String> mkeyset = methodMap.keySet();
				for (String key : mkeyset) {
					Object o = null;
					try {
						o = methodMap.get(key).invoke(a1);
						dataMap.put(key, o);
					} catch (Exception e) {
						logger.error("执行反射失败" + key, e);
					}
				}

				// 调用服务类，获取数据
				for (AttachClass ac : localset) {
					String[] attrArray = ac.getAttrname();
					Object[] attrObject = null;
					String storename = ac.getStorename();
					if (attrArray != null && attrArray.length > 0) {
						attrObject = new Object[attrArray.length];
						for (int p = 0; p < attrArray.length; p++) {
							Object o = dataMap.get(attrArray[p]);
							attrObject[p] = o;
						}
						storename = attrArray[0].toUpperCase();
					}
					Object mo = ac.getAttachInf().getBean(ac.getFixedvalue(), attrObject);
					a1.getEx().put(storename, mo);
				}
			}
		} else if (listone instanceof Map) {
			for (int i = 0; i < count; i++) {
				Map a1 = (Map) listnew.get(i);
				Map ex = new HashMap();
				for (AttachClass ac : localset) {
					String[] attrArray = ac.getAttrname();
					Object[] attrObject = null;
					String storename = ac.getStorename();
					if (attrArray != null && attrArray.length > 0) {
						attrObject = new Object[attrArray.length];
						for (int p = 0; p < attrArray.length; p++) {
							Object o = a1.get(attrArray[p]);
							attrObject[p] = o;
						}
						storename = attrArray[0].toUpperCase();
					}
					Object mo = ac.getAttachInf().getBean(ac.getFixedvalue(), attrObject);
					ex.put(storename, mo);
				}
				a1.put("EX", ex);
			}
		}
	}
}
