package org.vetech.core.modules.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Des3Encode {

	/**
	 * ECB加密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * ECB解密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] ees3DecodeECB(byte[] key, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC加密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC解密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * 另一种cbc加密 艺龙系统
	 * 
	 * @param key
	 * @param keyiv
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC2(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		Key k = new SecretKeySpec(key, "DES");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, k, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * 另一种cbc解密 艺龙系统
	 * 
	 * @param key
	 * @param keyiv
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC2(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		Key k = new SecretKeySpec(key, "DES");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, k, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	public static void main(String[] args) throws Exception {
		byte[] key = "12345678".getBytes();
		System.out.println(key.length);
		byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
		byte[] data = "12345#6789012345".getBytes("UTF-8");
		key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		// System.out.println("ECB加密解密");
		// byte[] str3 = des3EncodeECB(key,data );
		// byte[] str4 = ees3DecodeECB(key, str3);
		// System.out.println(new String(str4, "UTF-8"));
		// System.out.println();
		System.out.println("CBC加密解密");
		byte[] str5 = des3EncodeCBC(key, keyiv, data);
		byte[] str6 = des3DecodeCBC(key, keyiv, str5);
		System.out.println(Base64.encodeBase64String(str5));
		System.out.println(new String(str6, "UTF-8"));
	}

	public static void main2(String[] args) throws Exception {
		byte[] keyiv = "12345678".getBytes();
		byte[] data = "12345#6789012345".getBytes("UTF-8");
		byte[] key = "12345678".getBytes();

		byte[] str5 = des3EncodeCBC2(key, keyiv, data);

		System.out.println(Encodes.encodeHex(str5));

		str5 = des3DecodeCBC2(key, keyiv, str5);

		System.out.println(new String(str5));

	}
}
