package cn.vetech.vedsb.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityEncode {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	public static String MD5Encode(String origin, String encode) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (encode == null || "".equals(encode))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(encode)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	public static String SHA1(String origin) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] data1 = origin.getBytes();

			sha.update(data1);
			byte[] msgDigest = sha.digest();

			return new String(msgDigest);
		} catch (Exception ex) {

		}
		return "";
	}

	public static String decoderBase64(String s, String encoding) {
		if (s == null)
			return null;
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b, encoding);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encodeBase64(String s, String encoding) {
		if (s == null)
			return null;
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		BASE64Encoder encode = new BASE64Encoder();
		byte[] b = null;
		try {
			b = s.getBytes(encoding);
			return encode.encode(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param message
	 *            需要加密的内容
	 * @param keyBytes
	 *            密匙必须是24字节
	 * @param ivBytes
	 *            向量必须是8字节
	 * @return
	 * @throws Exception
	 */

	public static byte[] encryptDes(String message, byte[] keyBytes, byte[] ivBytes) throws Exception {
		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(ivBytes);
		final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		final byte[] plainTextBytes = message.getBytes("utf-8");
		final byte[] cipherText = cipher.doFinal(plainTextBytes);
		return cipherText;
	}

	public static String decryptDes(byte[] message, byte[] keyBytes, byte[] ivBytes) throws Exception {
		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(ivBytes);
		final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE, key, iv);
		final byte[] plainText = decipher.doFinal(message);
		return new String(plainText, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		String s = "asdfdsaf";
		String key = "12345678";
	}

}
