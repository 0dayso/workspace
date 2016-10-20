package cn.vetech.web.webcontent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.ConnectUtil;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.jp.service.procedures.PkgQinfoServiceImpl;
import cn.vetech.vedsb.utils.Constants;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 接收PID发送过来的信息
 * 
 * @author houya
 *
 */
@Controller
public class PidinfoController extends AbstractBaseControl {
	@Autowired
	private PkgQinfoServiceImpl pkgQinfoServiceImpl;

	private static String Q = "Q";

	/**
	 * catalog=命令名&content=内容
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String receive(HttpServletRequest request) {
		final String catalog = StringUtils.trimToEmpty(request.getParameter("catalog"));
		final String content = request.getParameter("content");
		try {
			LogUtil.getPidinfo(catalog).error("输入 catalog:=" + catalog + "  content:= " + content);
			Constants.PID_INFO_EXECUTOR.execute(new Runnable() {
				@Override
				public void run() {
					if (Q.equals(catalog)) {
						pkgQinfoServiceImpl.proc(catalog, content);
					}
				}
			});
		} catch (Exception e) {
			LogUtil.getPidinfo(catalog).error("异常", e);
		}
		return "OK!";
	}

	public static void main(String[] args) throws Exception {
		String xml = FileUtils.readFileToString(new File("e:/阿里/qinfo.txt"));
		Map<String, String> params = new HashMap<String, String>();
		params.put("catalog", Q);
		params.put("content", xml);
		String b = ConnectUtil.doPost("http://127.0.0.1:8080/webcontent/pidinfo/receive", params, "UTF-8", 10000, 10000, null);
		System.out.println(b);
	}
}
