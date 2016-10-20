package org.vetech.core.modules.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.common.gzip.GZIPInInterceptor;
import org.apache.cxf.transport.common.gzip.GZIPOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.vetech.core.modules.service.ServiceException;

/**
 * 通讯工具
 * 
 * @author houya
 * 
 */
public class ConnectUtil {
	/**
	 * 为CXF 通讯设置 代理IP和 是否使用压缩传输
	 * 
	 * @param port
	 * @param proxyipport
	 * @param iszip
	 */
	public static void setCxfTime(Object port, String proxyipport, boolean iszip) {
		Client proxy = ClientProxy.getClient(port);
		HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setAllowChunking(false);
		// 连接服务器超时时间 3分钟
		policy.setConnectionTimeout(180 * 1000);
		// 等待服务器响应超时时间 5分钟
		policy.setReceiveTimeout(300 * 1000);

		if (StringUtils.isNotBlank(proxyipport)) {
			policy.setProxyServer(getIp(proxyipport));
			policy.setProxyServerPort(getPort(proxyipport));
		}
		if (iszip) {
			policy.setAcceptEncoding("gzip, deflate");
			Endpoint endpoint = proxy.getEndpoint();
			endpoint.getInInterceptors().add(new GZIPInInterceptor());
			endpoint.getOutInterceptors().add(new GZIPOutInterceptor());
		}
		conduit.setClient(policy);
	}

	private static String getIp(String proxyipport) {
		return StringUtils.substring(proxyipport, 0, proxyipport.indexOf(":"));
	}

	private static int getPort(String proxyipport) {
		return NumberUtils.toInt(StringUtils.substring(proxyipport, proxyipport.indexOf(":") + 1));
	}

	/**
	 * 拼接URL地址
	 * 
	 * @param host
	 * @param url
	 * @return
	 */
	public static String jionUrl(String host, String url) {
		if (host.endsWith("/")) {
			host = host.substring(0, host.length() - 1);
		}
		if (url.startsWith("/")) {
			return host + url;
		} else {
			return host + "/" + url;
		}
	}

	/**
	 * get 方法
	 * 
	 * @param url
	 *            地址
	 * @param params
	 *            map key value 形式的参数
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
	public static String doGet(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout, String proxyipport) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpclient = null;
		if (StringUtils.isNotBlank(proxyipport)) {
			HttpHost proxyHost = new HttpHost(getIp(proxyipport), getPort(proxyipport));// 代理
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
			httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		} else {
			httpclient = HttpClients.custom().build();
		}

		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		try {
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
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			response = httpclient.execute(httpGet);
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
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
			httpclient.close();
		}

	}
	
	/**
	 * get 方法
	 * 
	 * @param url
	 *            地址
	 * @param params
	 *            map key value 形式的参数
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
	public static byte[] doGetByte(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout, String proxyipport) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpclient = null;
		if (StringUtils.isNotBlank(proxyipport)) {
			HttpHost proxyHost = new HttpHost(getIp(proxyipport), getPort(proxyipport));// 代理
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
			httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		} else {
			httpclient = HttpClients.custom().build();
		}

		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		try {
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
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			response = httpclient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			byte[] result = null;
			if (entity != null) {
				result = EntityUtils.toByteArray(entity);
			}
			EntityUtils.consume(entity);
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
			httpclient.close();
		}

	}

	/**
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            map key value 形式的参数
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
	 * 
	 */
	public static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout, String proxyipport) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpclient = null;
		if (StringUtils.isNotBlank(proxyipport)) {
			HttpHost proxyHost = new HttpHost(getIp(proxyipport), getPort(proxyipport));// 代理
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
			httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		} else {
			httpclient = HttpClients.custom().build();
		}
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		try {
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
			httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
			}
			response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			if (statusCode != 200) {
				httpPost.abort();
				throw new ServiceException("HttpClient,error status code :" + statusCode + " content:" + result);
			}
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
			httpclient.close();
		}
	}
	
	/**
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            map key value 形式的参数
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
	 * 
	 */
	public static byte[]  doPostByte(String url, Map<String, String> params, String charset, int connectTimeout, int soTimeout, String proxyipport) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpclient = null;
		if (StringUtils.isNotBlank(proxyipport)) {
			HttpHost proxyHost = new HttpHost(getIp(proxyipport), getPort(proxyipport));// 代理
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
			httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
		} else {
			httpclient = HttpClients.custom().build();
		}
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		try {
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
			httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
			}
			response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			byte[]  result = null;
			if (entity != null) {
				result = EntityUtils.toByteArray(entity);
			}
			EntityUtils.consume(entity);
			if (statusCode != 200) {
				httpPost.abort();
				throw new ServiceException("HttpClient,error status code :" + statusCode + " content:" + result);
			}
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
			httpclient.close();
		}
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param xml
	 * @param sendEncode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static String sendHttpUrlForPost(String url, String xml, String sendEncode) throws Exception {
		OutputStream out = null;
		HttpURLConnection connect = null;
		InputStream is = null;
		String response = "";
		if (StringUtils.isBlank(sendEncode)) {
			sendEncode = "UTF-8";
		}
		try {
			URL _url = new URL(url);
			connect = (HttpURLConnection) _url.openConnection();
			connect.setDoOutput(true);
			connect.setDoInput(true);
			connect.setReadTimeout(1000 * 60);
			connect.setConnectTimeout(1000 * 60);
			connect.setRequestProperty("Content-Type", "application/xml");
			connect.setRequestProperty("Accept", "application/xml");

			out = connect.getOutputStream();
			out.write(xml.getBytes(sendEncode));
			out.flush();
			is = connect.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] receiveBuffer = new byte[2048];
			int readBytesSize;
			readBytesSize = is.read(receiveBuffer);
			while (readBytesSize != -1) {
				bos.write(receiveBuffer, 0, readBytesSize);
				readBytesSize = is.read(receiveBuffer);
			}
			response = new String(bos.toByteArray(), sendEncode);
			return response;

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (connect != null) {
				try {
					connect.disconnect();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main11(String[] args) throws IOException {
		String urlaa = "http://fuwu.kuxun.cn/exinterface/post/policies/batch/xml/?siteno=511";
		int soTimeout = 0;
		int connectTimeout = 0;
		CloseableHttpResponse response = null;
		HttpPost httppost = null;
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.custom().build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
			// 请求处理页面
			httppost = new HttpPost(urlaa);
			httppost.setConfig(requestConfig);
			// 创建待处理的文件
			// 创建待处理的表单域内容文本
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

			multipartEntityBuilder.addBinaryBody("uploadfile", "".getBytes(), ContentType.APPLICATION_OCTET_STREAM, "uploadfile");
			multipartEntityBuilder.addTextBody("siteno", "511");
			// 对请求的表单域进行填充
			// 设置请求
			httppost.setEntity(multipartEntityBuilder.build());
			// 设置请求
			// 执行
			response = httpclient.execute(httppost);
			// HttpEntity resEntity = response.getEntity();
			// System.out.println(response.getStatusLine());
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// 显示内容
				if (entity != null) {
					System.out.println(EntityUtils.toString(entity));
				}
				EntityUtils.consume(entity);
			}
		} finally {
			if (response != null) {
				response.close();
			}
			if (httppost != null) {
				httppost.releaseConnection();
			}
			httpclient.close();
		}
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
	public static byte[] doPostStream(String url, byte[] b, String charset, int connectTimeout, int soTimeout, String proxyipport) throws Exception {
		CloseableHttpResponse response = null;
		HttpPost httppost = null;
		CloseableHttpClient httpclient = null;
		try {
			if (StringUtils.isNotBlank(proxyipport)) {
				HttpHost proxyHost = new HttpHost(getIp(proxyipport), getPort(proxyipport));// 代理
				DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
				httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
			} else {
				httpclient = HttpClients.custom().build();
			}

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
			// 请求处理页面
			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			// 创建待处理的文件
			// 创建待处理的表单域内容文本
			if(b!=null){
				ByteArrayEntity bae = new ByteArrayEntity(b);
				// 设置请求
				httppost.setEntity(bae);
			}
			// 执行
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			byte[] result  = null;
			if (entity != null) {
				result = EntityUtils.toByteArray(entity);
			}
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httppost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode + "  " + result);
			}
			EntityUtils.consume(entity);
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
			if (httppost != null) {
				httppost.releaseConnection();
			}
			httpclient.close();
		}
	}

	/**
	 * 通过Url 下载文件到 本地磁盘,读取超时时间是5分钟
	 * 
	 * @param requestURL
	 *            url地址
	 * @param filePath
	 *            保存的绝对目录地址
	 * @param filename
	 *            保存的文件名
	 * @return
	 * @throws Exception
	 */
	public static File downloadFile(String requestURL, String filePath, String filename) throws Exception {

		if (StringUtils.isBlank(requestURL)) {
			return null;
		}
		URL url = new URL(requestURL);
		// 判断目录是否存在，不存在则现创建目录
		File pFile = new File(filePath);
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		if (StringUtils.isBlank(filename)) {
			filename = StringUtils.substring(requestURL, requestURL.lastIndexOf("/") + 1);
		}
		File file = new File(filePath + "/" + filename);
		FileUtils.copyURLToFile(url, file, 60 * 1000, 10 * 60 * 1000);
		return file;

	}
	public static void main111(String[] args) throws Exception {
		String url = "http://192.168.3.97:8080/openapi/b2b/veopen";
		String charset = "utf-8";
		int connectTimeout=10000;
		int soTimeout=20000;
		String proxyipport="";
		
		String xml =FileUtils.readFileToString(new File("d:/xml.xml"));
		url = url+"?data="+URLEncoder.encode(xml, "UTF-8");
		
		
		
		byte [] b=FileUtils.readFileToByteArray(new File("d:/temp/国际票ER项系统5-20.xls"));
		doPostStream(url, b, charset, connectTimeout, soTimeout, "");
		
		Map<String,String> params=new HashMap<String,String>();

		
		params.put("data", xml);
		//	doPost(url, params, charset, connectTimeout, soTimeout, proxyipport);
	}
	
	public static void main(String[] args) throws Exception {
		String xml="<request><businessNo>SYSCB</businessNo><compid>VESYKJ</compid><operateTime>2016-06-12 11:39:26</operateTime><service>getHotelList</service><sign>a59429e2951fe56988328d5e52dec525</sign><systemId>VETECH</systemId><userId>SYSCBADMIN</userId><beginPrice>0</beginPrice><channels>31200801,31200804,31200805,31200815,31200817</channels><city>10119</city><count>20</count><distance>0.0</distance><endPrice>999999</endPrice><ismap>false</ismap><payment>0</payment><start>0</start></request>";
		xml="<request><businessNo>SYSCB</businessNo><operateTime>2016-06-15 11:34:52</operateTime><service>sceneryProductSync</service><sign>bfd978601f6c5ed46b747404b0d2c77c</sign>   <systemId>VETECH</systemId>    <userId>TCLYADMIN</userId>    <merchantNo>TCLY</merchantNo>    <page>1</page>    <syncType>2</syncType>    <totalPage>1</totalPage></request>";
		//	String xml=FileUtils.readFileToString(new File("d:/1606151121416404tt.txt"));
		// String url="http://veopen.vetech.cn:40006/veopen";
		//String url = "http://192.168.101.101:40000/veopen";
		 String url = "http://192.168.1.69:1321/veopen";
		String charset = "utf-8";
		int connectTimeout=10000;
		int soTimeout=200000; 
		String proxyipport="";
		Map<String,String> params=new HashMap<String,String>();
		params.put("data", xml);
	//  String a = doPost(url, params, charset, connectTimeout, soTimeout, proxyipport);
		
		byte[] b=FileUtils.readFileToByteArray(new File("d:/temp/1.zip"));
		url+="?data="+URLEncoder.encode(xml, charset);
		byte[] a = doPostStream(url, b, charset, connectTimeout, soTimeout, proxyipport);
 	System.out.println(new String(a));
	}
}
