package cn.vetech.vedsb.common.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.common.cache.BQxCache;
import cn.vetech.vedsb.common.entity.Shmkb;
import cn.vetech.vedsb.common.entity.Shmkgn;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.web.vedsb.SessionUtils;


class WapperedResponse extends HttpServletResponseWrapper {
	private ByteArrayOutputStream buffer = null;
	private ServletOutputStream out = null;
	private PrintWriter writer = null;

	public WapperedResponse(HttpServletResponse resp) throws IOException {
		super(resp);
		buffer = new ByteArrayOutputStream();// 真正存储数据的流
		out = new WapperedOutputStream(buffer);
		writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
	}

	// 重载父类获取outputstream的方法
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return out;
	}

	// 重载父类获取writer的方法
	@Override
	public PrintWriter getWriter() throws UnsupportedEncodingException {
		return writer;
	}

	// 重载父类获取flushBuffer的方法
	@Override
	public void flushBuffer() throws IOException {
		if (out != null) {
			out.flush();
		}
		if (writer != null) {
			writer.flush();
		}
	}

	@Override
	public void reset() {
		buffer.reset();
	}

	public byte[] getResponseData() throws IOException {
		flushBuffer();// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
		return buffer.toByteArray();
	}

	public String getResponseDataStr() throws IOException {
		flushBuffer();// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
		return buffer.toString(this.getCharacterEncoding());
	}

	// 内部类，对ServletOutputStream进行包装
	private class WapperedOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream bos = null;

		public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
			bos = stream;
		}

		@Override
		public void write(int b) throws IOException {
			bos.write(b);
		}
	}
}
/**
 * 模块和功能权限验证
 * 修改资源文件的到core工程地质
 * @author 章磊
 *
 */
public class AuthFilter implements Filter {
	private Pattern qxPattern = Pattern.compile("qx=\"([^\"]+)\"");
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// chain.doFilter(request, response);
		long filterTime = System.currentTimeMillis();
		HttpServletResponse resp = (HttpServletResponse) response;
		WapperedResponse wapper = new WapperedResponse(resp);
		HttpServletRequest httprequest = (HttpServletRequest) request;
		Shyhb shyhb = SessionUtils.getShshbSession(httprequest);
		if (shyhb==null || httprequest.getRequestURI().indexOf("/vedsb/sso")>-1|| httprequest.getRequestURI().indexOf("/vedsb/bcookies")>-1  || httprequest.getRequestURI().indexOf("/vedsb/main")>-1 || httprequest.getRequestURI().indexOf("/static/") > -1) {
			chain.doFilter(request, response);
		} else {
			long filterQxTime = System.currentTimeMillis();
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(httprequest.getSession().getServletContext());
			BQxCache qxCache = applicationContext.getBean(BQxCache.class);
			CsbCacheService csbCache = applicationContext.getBean(CsbCacheService.class);
			Map<String, Shmkb>  allMkbMap = (Map<String, Shmkb>) httprequest.getSession().getAttribute("bmklist");
			if(allMkbMap==null){
				allMkbMap = qxCache.getMk(shyhb.getDlzh());
				httprequest.getSession().setAttribute("bmklist", allMkbMap);
			}
			//去掉上下文
			String ctx = httprequest.getContextPath();
			String requestUrl = httprequest.getRequestURI();
			if (StringUtils.isNotBlank(ctx) && StringUtils.isNotBlank(requestUrl)) {
				int p = requestUrl.indexOf(ctx);
				if (p >= 0) {
					requestUrl = requestUrl.substring(p + ctx.length());
				}
			}
			
			if(requestUrl.indexOf("?")>-1){
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
			}
			if(requestUrl.indexOf("#")>-1){
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("#"));
			}
			if(requestUrl.indexOf("_")>-1){
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("_"));
			}
			Cookie cookie = WebUtils.getCookie(httprequest, "BMKURL");
			Shmkb vemkb = null;
			boolean dj = false;
			if (allMkbMap != null && !allMkbMap.isEmpty()) {//先判断请求的URL是不是公共模块的URL，如果是说明登记了，不需要后面的判断了
				vemkb = allMkbMap.get("/vedsb/main/index");
				if(vemkb!=null){
					for(Shmkgn vemkgn : vemkb.getShmkgnList()){
						if(requestUrl.equals(vemkgn.getGnurl())){
							dj = true;
							break;
						}
					}
				}
				
			}
			if(!dj && cookie!=null && StringUtils.isNotBlank(cookie.getValue())){
				if (allMkbMap != null && !allMkbMap.isEmpty()) {
					vemkb = allMkbMap.get(requestUrl);
					if(vemkb==null){//说明请求的URL不是模块URL,那么就是功能URL
						String mkurl = cookie.getValue();//从cookie中获取当前模块URL
						vemkb = allMkbMap.get(mkurl);
						if(vemkb==null){
							throw new ServletException("当前模块URL有问题!"+requestUrl+":"+mkurl);
						}
						for(Shmkgn vemkgn : vemkb.getShmkgnList()){
							if(requestUrl.equals(vemkgn.getGnurl())){
								dj = true;
								if("1".equals(vemkgn.getSfxsqx()) && !"1".equals(vemkgn.getIskq())){
									throw new ServletException("没有功能权限!");
								}
								break;
							}
						}
						if(!dj){//没有登记
							throw new ServletException("URL没有登记!ctx="+ctx+",url="+requestUrl);
						}
					}
					if(!"1".equals(vemkb.getIskq())){
						throw new ServletException("没有模块权限!");
					}
				}
			}
			httprequest.setAttribute(httprequest.getRequestURI()+"_mkfilter",System.currentTimeMillis()- filterQxTime);
			
			filterQxTime = System.currentTimeMillis();
			chain.doFilter(request, wapper);
			httprequest.setAttribute(httprequest.getRequestURI()+"_controllerfilter",System.currentTimeMillis()- filterQxTime);
			// byte[] b1=wapper.getResponseData();
			// //do something with b1 here
			// String content = new String(b1,resp.getCharacterEncoding());
			// byte[] b2=content.getBytes(resp.getCharacterEncoding());
			// //输出处理后的数据
			// ServletOutputStream output=response.getOutputStream();
			// output.write(b2);
			// output.flush();
			filterQxTime = System.currentTimeMillis();
			String b1 = wapper.getResponseDataStr();
			StringBuffer bufferString = new StringBuffer(b1);
			b1 = null;

			if (vemkb!=null && vemkb.getShmkgnList()!= null && !vemkb.getShmkgnList().isEmpty()) {

				Matcher matcher = qxPattern.matcher(bufferString);
				while (matcher.find()) {
					String url = matcher.group(1);

					if (StringUtils.isBlank(url)) {
						continue;
					}
					int end = matcher.end();
					for (Shmkgn shmkgn : vemkb.getShmkgnList()) {
						if (url.equals(shmkgn.getGnurl())) {
							if ("1".equals(shmkgn.getSfxsqx()) && "0".equals(shmkgn.getIskq())) {
								bufferString.insert(end, " style='display:none'");
							}
						}
					}
				}
			}
			httprequest.setAttribute(httprequest.getRequestURI()+"_gnhtmlfilter",System.currentTimeMillis()- filterQxTime);
			
			filterQxTime = System.currentTimeMillis();
			VecsbCache vecsb = csbCache.get("9003");
			if (vecsb != null && StringUtils.isNotBlank(vecsb.getCsz1())) {
				String p = "/static/core/data/";
				Pattern staticPattern = Pattern.compile("\"" + ctx + p);
				Matcher matcher = staticPattern.matcher(bufferString);
				while (matcher.find()) {

					int start = matcher.start();
					int end = matcher.end();
					bufferString.replace(start + 1, end, vecsb.getCsz1() + p);
				}
			}
			httprequest.setAttribute(httprequest.getRequestURI()+"_jsfilter",System.currentTimeMillis()- filterQxTime);
			// do something with b1 here
			// 输出处理后的数据
			response.setContentType(wapper.getContentType());
			//application/x-download不是下载的就用string输出
			if(StringUtils.isNotBlank(wapper.getContentType()) && wapper.getContentType().indexOf("application/x-download")==-1){
				PrintWriter output = response.getWriter();
				output.write(bufferString.toString());
				output.flush();
			}else{//application/x-download是下载的情况用byte输出
				OutputStream output = response.getOutputStream();
				output.write(wapper.getResponseData(),0,wapper.getResponseData().length);
				output.flush();
			}
		}
		httprequest.setAttribute(httprequest.getRequestURI()+"_filter",System.currentTimeMillis()- filterTime);

	}

	/** 对返回的html进行处理 */
	public String getDealContent(String result, ServletRequest request) {
		// 对html进行处理后返回处理结果
		return result;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		String p="\"/veds_b/static/core/data/";
		  Pattern staticPattern = Pattern.compile(p);
        StringBuffer bufferString=new StringBuffer("<script type=\"text/javascript\" src=\"/veds_b/static/core/data/gn_city.js?v=${VERSION}\"></script> \"/veds_b/static/core/data/gn_city.js?v=${VERSION}\"></script>");
		Matcher matcher = staticPattern.matcher(bufferString);
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			bufferString.replace(start+1, end, "http://192.168.110.135:10090/veds_core/static/core/data/");
			//bufferString.insert(start+1, "http://192.168.110.135:10090/veds_core");
		}
		System.out.println(bufferString);
	}

}
