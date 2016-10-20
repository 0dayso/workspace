package org.vetech.core.modules.utils;

import java.util.Map;

public class RSATester {

	static String publicKey;
	static String privateKey;

	static {
		try {
			Map<String, Object> keyMap = RSAUtils.genKeyPair();
			publicKey = RSAUtils.getPublicKey(keyMap);
			privateKey = RSAUtils.getPrivateKey(keyMap);
			
//			publicKey = AllinpayUtils.getPublic();
//		    privateKey = AllinpayUtils.getPrivate();
			System.err.println("公钥: \n\r" + publicKey);
			System.err.println("私钥： \n\r" + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		//test();
		//testSign();
		testLyws();
	}
	
	

	static void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		System.out.println("\r加密前文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);
	}

	static void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String source = "这是一行测试RSA数字签名的无意义文字";
		System.out.println("原文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		System.out.println("加密后：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		System.out.println("解密后: \r\n" + target);
		System.err.println("私钥签名——公钥验证签名");
		String sign = RSAUtils.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);
		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		System.err.println("验证结果:\r" + status);
	}
	
	static void testLyws() throws Exception{
		String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgXHjkMOkx6PIhtpiPYVPOTjGn" +
				"lJSO97tKX6r5JGDA7BDeaNxBJt+OJ5EWNwyoaoeMx9C/dI5+FQ9mFtRLnd9Eb4l0" +
				"jWAx7egCdtOrzE2qEkiKz0kMMZEdb2qAab6Ju7GC/RkL2CWVBqPFwbeqCY+xFeHn" +
				"66lCzk7rB9vKLiaDtQIDAQAB";
		
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：\r\n" +RSAUtils.encodeBase64(encodedData) );
		
		String  privateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBceOQw6THo8iG2mI9hU85OMaeUlI73u0pfqvkkYMDsEN5o3EEm344nkRY3DKhqh4zH0L90jn4VD2YW1Eud30RviXSNYDHt6AJ206vMTaoSSIrPSQwxkR1vaoBpvom7sYL9GQvYJZUGo8XBt6oJj7EV4efrqULOTusH28ouJoO1AgMBAAECgYAA/HCV3qMgdnE+udO4Vf3g7+QoRqafNnDFZU801v9tUEZOR9DK6cVIJ4Ah5dbDFlydBgGHm9zgc5qzowJNMLxVueeiihw6SfqW2IywmslX/9phURK+7l9xXWC7cesLBhGP/akgrseX4qMZOlzuhsG0SooJLvPRoQUkM02vZBmewQJBAND5E+bW6MXt7l+pjG4pfLd+YFuYp7Up7vuKc1g6U0XJAhhJvhfHQArec207uoLpwBtzpT2OxcJpS3I0y0TG6hECQQDEct/cC0Fm8UvnLniiJPLJtwA9BOBIE3890Y2bsBS43u/rFjtjct9RibH1GOi8fylcDJxBegorzfrGo2Df1ntlAkByYeOi1r9Sl4mCkytqoQrC5UmY2VXys6E7zeyUvlgWOpAK+1Ixcb58NIa5o5fTUF72p7Yqm5tjbn79ZChe3QIBAkAbF3SD1hGI7tH26dO9GLda1h/0Sk3b4sRzdAjBZcEV1bjkhMZf6qGdjw25rVu7UxYTGbIa3Ye1jsx8xF9Bv0R5AkEAp3sHooP+1+4hrtHE/wfSzBsPcybHDalMdfuWFZ+sbUwX3P0I180Pl1HCsmr+fCf6/lB3IPpc6qQ/XUJ5uYlFkA==";
		
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);
		
	}

}