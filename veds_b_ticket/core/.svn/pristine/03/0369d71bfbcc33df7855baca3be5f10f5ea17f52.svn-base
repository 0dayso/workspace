package org.vetech.core.modules.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态加载class,这个不是远程加载，只是动态加载classes目录下的文件。
 * 
 * 应用场景是，直接覆盖claess目录下的文件，使用这种方式加载的class会动态加载
 * 
 * @author zhanglei
 *
 */
public class DynamicClassLoader extends ClassLoader {

	private final static Logger logger = LoggerFactory.getLogger(DynamicClassLoader.class);

	/**
	 * 动态加载的缓存，每隔10分钟判断是否需要重新加载
	 */
	private static Map<String, Long> CLAZZNAME_UPDATETIME = new HashMap<String, Long>();

	private static Map<String, Class<?>> CLAZZNAME_CLASS = new HashMap<String, Class<?>>();
	/**
	 * Class缓存的时间
	 */
	private int min = 10;

	public DynamicClassLoader(int min) {
		this.min = min;
	}

	public static Class<?> getClass(String name) throws ClassNotFoundException {
		DynamicClassLoader dcl = new DynamicClassLoader(10);
		return dcl.findClass(name);
	}

	/**
	 * 动态调用class文件
	 * 
	 * @param name
	 *            如 org.vetech.core.modules.utils.ParseXml
	 * 
	 * @param min
	 *            class文件缓存的分钟
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getClass(String name, int min) throws ClassNotFoundException {
		DynamicClassLoader dcl = new DynamicClassLoader(min);
		return dcl.findClass(name);
	}

	public DynamicClassLoader() {
		super(DynamicClassLoader.class.getClassLoader());// 使用Tomcat类加载器
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Long t = CLAZZNAME_UPDATETIME.get(name);
		Class<?> loadclass = CLAZZNAME_CLASS.get(name);
		long now = System.currentTimeMillis();
		if (loadclass == null || t == null || (now - t) > 1000 * 60 * min) {
			loadclass = defIneClass(name);
			CLAZZNAME_CLASS.put(name, loadclass);
			CLAZZNAME_UPDATETIME.put(name, now);
		}
		return loadclass;
	}

	private Class<?> defIneClass(String name) throws ClassNotFoundException, ClassFormatError {
		String classPath = DynamicClassLoader.class.getResource("/").getPath(); // 得到classpath
		String fileName = name.replace(".", "/") + ".class";
		logger.info("动态加载" + classPath   + fileName);
		File classFile = new File(classPath, fileName);
		if (!classFile.exists()) {
			logger.error("动态加载失败" + classFile.getPath() + " 不存在");
			throw new ClassNotFoundException(classFile.getPath() + " 不存在");
		}

		try {
			byte[] b = FileUtils.readFileToByteArray(classFile);
			return defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			logger.error("动态加载失败" + classFile.getPath(), e);
			throw new ClassNotFoundException(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		String name = "org.vetech.core.modules.utils.ParseXml";

		Class<?> c = getClass(name);
		System.out.println(c);

		c = getClass(name);
		System.out.println(c);

		c = getClass(name);
		System.out.println(c);

		c = getClass(name);
		System.out.println(c);

	}
}