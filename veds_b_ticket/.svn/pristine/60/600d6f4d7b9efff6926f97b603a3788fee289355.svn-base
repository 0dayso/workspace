package cn.vetech.vedsb.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class DesEncode {

	private final static String DES = "DES";

	/**
	 * 
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * 
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * 
	 * @return 返回加密后的数据
	 * 
	 * @throws Exception
	 * 
	 */

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源

		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成

		// 一个SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 现在，获取数据并加密

		// 正式执行加密操作

		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * 
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * 
	 * @return 返回解密后的原始数据
	 * 
	 * @throws Exception
	 * 
	 */

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源

		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

		// 一个SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象

		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 现在，获取数据并解密

		// 正式执行解密操作

		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 二行制转字符串
	 * 
	 * @param b
	 * 
	 * @return
	 * 
	 */

	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)

			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {

			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);

		}

		return b2;
	}

	private static final String PASSWORD_CRYPT_KEY = "__jDqaw123log_";

	public final static String decryptdb(String data) {

		try {

			return new String(decrypt(hex2byte(data.getBytes()),

					PASSWORD_CRYPT_KEY.getBytes()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public final static String encryptdb(String password) {

		try {
			byte[] b = encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes());
			return byte2hex(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 使用base64 UTF-8 编码byte的方式 加密
	 * 
	 * @param data
	 * @return
	 */
	public final static String encryptBase64(String data) {
		if(StringUtils.isBlank(data)){
			return null;
		}
		try {
			byte[] b = encrypt(data.getBytes(), PASSWORD_CRYPT_KEY.getBytes());
			return new String(Base64.encodeBase64(b), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密base64 UTF-8 加密的密码
	 * 
	 * @param data
	 * @return
	 */
	public final static String decryptBase64(String data) {
		if(StringUtils.isBlank(data)){
			return data;
		}
		try {
			byte[] b = Base64.decodeBase64(data.getBytes("UTF-8"));
			byte[] b2 = decrypt(b, PASSWORD_CRYPT_KEY.getBytes());
			return new String(b2, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			String en = encryptBase64("111111中文测试111111");
			System.out.println(en);
			String de = decryptBase64("RgEfI2BDMirpHufwNHGiQ4zOpx03AlhhG2lLuOkdjrc0UazyHNfQ8g==");
			System.out.println(de);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
