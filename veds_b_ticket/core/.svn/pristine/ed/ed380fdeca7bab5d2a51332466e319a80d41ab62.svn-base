package org.vetech.core.modules.utils;

import java.io.InputStream;
import java.nio.charset.CodingErrorAction;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 基于 httpclient 4.3.1版本的 http工具类
 */

public class HttpClientUtil {
	private static int connectTimeout = 10000, soTimeout = 60000;
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;

	static {
		try {
			//SSLContext sslContext = SSLContexts.custom().useTLS().build();
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, null);

			// 域名验证 这种方式不对主机名进行验证，验证功能被关闭
			//SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", sslsf).build();

			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpclient = HttpClients.custom().setConnectionManager(connManager).build();
			// Create socket configuration
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			connManager.setDefaultSocketConfig(socketConfig);
			// Create message constraints
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();

			// Create connection configuration
			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
					.setMessageConstraints(messageConstraints).build();
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(200);
			connManager.setDefaultMaxPerRoute(20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String doGet(String url, Map<String, String> params, String charset) throws Exception {
		return doGet(url, params, charset, connectTimeout, soTimeout);
	}

	public static String doPost(String url, Map<String, String> params, String charset) throws Exception {
		return doPost(url, params, charset, connectTimeout, soTimeout);
	}

	/**
	 * 
	 * HTTP Get 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 * @throws Exception
	 * 
	 */

	public static String doGet(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value));
				}
			}
			url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
		}
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httpGet.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, "utf-8");
		}
		EntityUtils.consume(entity);
		response.close();
		return result;

	}

	/**
	 * 
	 * HTTP Post 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 * @throws Exception
	 * 
	 */
	public static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		List<NameValuePair> pairs = null;
		if (params != null && !params.isEmpty()) {
			pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value));
				}
			}
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		if (pairs != null && pairs.size() > 0) {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
		}
		CloseableHttpResponse response = httpclient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httpPost.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, "utf-8");
		}
		EntityUtils.consume(entity);
		response.close();
		return result;
	}

	/**
	 * 发送post数据流
	 * 
	 * @param url
	 *            地址
	 * @param b
	 *            字节流
	 * @param charset
	 *            发送的字符编发
	 * @param connectTimeout
	 *            链接超时时间(毫秒)
	 * @param soTimeout读取超时时间
	 *            (毫秒)
	 * @param proxyipport代理IP
	 *            :port 不使用代理传入null
	 * @return
	 * @throws Exception
	 */
	public static byte[] doPostStream(String url, byte[] b, Map<String, String> headmap, String charset, int connectTimeout, int soTimeout) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		// 请求处理页面
		HttpPost httppost = new HttpPost(url);
		httppost.setConfig(requestConfig);

		if (headmap != null) {
			for (String name : headmap.keySet()) {
				String value = headmap.get(name);
				httppost.setHeader(name, value);
			}
		}

		// 创建待处理的文件
		// 创建待处理的表单域内容文本
		ByteArrayEntity bae = new ByteArrayEntity(b);
		// 设置请求
		httppost.setEntity(bae);
		// 执行
		CloseableHttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		byte[] result = null;
		if (entity != null) {
			result = EntityUtils.toByteArray(entity);
		}
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httppost.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		EntityUtils.consume(entity);
		response.close();
		return result;

	}
	/**
	 * 发送post数据流
	 * @param url
	 * @param b
	 * @param headmap
	 * @param charset
	 * @param connectTimeout
	 * @param soTimeout
	 * @return
	 * @throws Exception
	 */
	public static byte[] doPostInputStream(String url, InputStream b, Map<String, String> headmap, String charset, int connectTimeout, int soTimeout) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		// 请求处理页面
		HttpPost httppost = new HttpPost(url);
		httppost.setConfig(requestConfig);

		if (headmap != null) {
			for (String name : headmap.keySet()) {
				String value = headmap.get(name);
				httppost.setHeader(name, value);
			}
		}

		// 创建待处理的文件
		// 创建待处理的表单域内容文本
		InputStreamEntity bae = new InputStreamEntity(b);
		// 设置请求
		httppost.setEntity(bae);
		// 执行
		CloseableHttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		byte[] result = null;
		if (entity != null) {
			result = EntityUtils.toByteArray(entity);
		}
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httppost.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		EntityUtils.consume(entity);
		response.close();
		return result;

	}
	
	/**
	 * 发送get请求
	 * @param url 请求路径
	 * @param params 请求参数
	 * @param headmap 头部参数
	 * @param charset 发送的字符编发
	 * @param connectTimeout 链接超时时间(毫秒)
	 * @param soTimeout 读取超时时间(毫秒)
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> params, Map<String, String> headmap, String charset, int connectTimeout, int soTimeout) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		List<NameValuePair> pairs = null;
		if (params != null && !params.isEmpty()) {
			pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value));
				}
			}
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		if (headmap != null) {
			for (String name : headmap.keySet()) {
				String value = headmap.get(name);
				httpPost.setHeader(name, value);
			}
		}
		
		if (pairs != null && pairs.size() > 0) {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
		}
		CloseableHttpResponse response = httpclient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httpPost.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, "utf-8");
		}
		EntityUtils.consume(entity);
		response.close();
		return result;
	}
	
	/**
	 * 发送get请求
	 * @param url 请求路径
	 * @param params 请求参数
	 * @param headmap 头部参数
	 * @param charset 发送的字符编发
	 * @param connectTimeout 链接超时时间(毫秒)
	 * @param soTimeout 读取超时时间(毫秒)
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, Map<String, String> params, Map<String, String> headmap, String charset, int connectTimeout, int soTimeout) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		HttpGet httpGet = new HttpGet(url);
		if (headmap != null) {
			for (String name : headmap.keySet()) {
				String value = headmap.get(name);
				httpGet.setHeader(name, value);
			}
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value));
				}
			}
			url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
		}
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			httpGet.abort();
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, "utf-8");
		}
		EntityUtils.consume(entity);
		response.close();
		return result;
	}
	
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "<INSUREQ>" + "	<HEAD>" + "		<TRANSRNO>2011</TRANSRNO>" + "		<PARTNERCODE>CQTP</PARTNERCODE>"
				+ "		<PARTNERSUBCODE>CQTP</PARTNERSUBCODE>" + "	</HEAD>" + "	<MAIN>" + "		<SERIALNUMBER>zh4597123134974</SERIALNUMBER>"
				+ "		<TRANSRDATE>2015-09-02 14:30:00</TRANSRDATE>" + "		<PRODTYPE>081F</PRODTYPE>" + "		<PRODUCTCODE>B0003</PRODUCTCODE>" + "	</MAIN>" + "	<BODY>" + "		<BASE>"
				+ "			<EFFDATE>2015-09-02 00:00:00</EFFDATE>" + "			<TERMDATE>2015-09-28 23:59:59</TERMDATE>" + "			<TBRNAME>杨唤</TBRNAME>" + "			<TBRTEL>1321773319</TBRTEL>"
				+ "			<BBRNAME>杨唤</BBRNAME>" + "			<BBRIDTYPE>464004</BBRIDTYPE>" + "			<BBRIDNO>420222199409074418</BBRIDNO>" + "			<BBRTEL>13212773319</BBRTEL>"
				+ "			<PREMIUM>10.00</PREMIUM>" + "		</BASE>" + "		<TGT>" + "			<PINTIME/>" + "			<GDTOTIME/>" + "			<SJTOTIME/>"
				+ "			<TICKETDISCOUNT/>" + "			<TICKETNO/>" + "			<TICKETCLASS/>" + "			<AIRLINECOMPANY/>" + "			<FLIGHTNO/>"
				+ "			<TICKETPRICE></TICKETPRICE>" + "			<ISPOL/>" + "			<COMPANYNAME/>" + "		</TGT>" + "	</BODY>" + "</INSUREQ>";
		String result = "";

		String url = "https://mapi.alipay.com/gateway.do";
		try {
			String charset = "UTF-8";
			byte[] b = xml.getBytes("UTF-8");

			Map<String, String> headmap = new HashMap<String, String>();

			headmap.put("Content-type", "text/xml;charset=GBK");
			headmap.put("GW_CH_TX", "2011");
			headmap.put("GW_CH_CODE", "CQTP");
			headmap.put("GW_CH_USER", "CQTP");
			headmap.put("GW_CH_PWD", "CQTP");
			String s=HttpClientUtil.doPost(url, null, charset);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
