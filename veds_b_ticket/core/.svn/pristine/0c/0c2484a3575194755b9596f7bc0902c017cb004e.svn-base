package org.vetech.core.modules.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 转换对象
 * 
 * @author zhanglei
 *
 */
public class ObjectUtil {
	/**
	 * 对象转byte
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] obj2Byte(Object obj) throws IOException {
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		byte[] bytes = null;
		try {
			bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
		} finally {
			try {
				if (bo != null) {
					bo.close();
				}
				if (oo != null) {
					oo.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return bytes;
	}

	/**
	 * byte转对象
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byte2Obj(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		Object object = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			object = ois.readObject();
		} finally {
			try {
				if (bais != null) {
					bais.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return object;
	}

	/**
	 * 把对象转为字符串，一般用来记录日志
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		if (object == null) {
			return "";
		}
		return ReflectionToStringBuilder.toString(object, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
