package cn.vetech.vedsb.app;

import java.net.InetAddress;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.utils.Constants;

@Component
public class AutoRunService {
	private static Logger logger = LoggerFactory.getLogger(AutoRunService.class);
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SystemJobCanDoServiceImpl systemJobCanDoServiceImpl;

	public void autoload() {
		// 给BTomcat赋值
		logger.info("系统启动时加载数据开始,内存信息" + WebUtils.getMemory());
		loadBTomcat();
		systemJobCanDoServiceImpl.systemLoad();
	}

	/**
	 * 系统启动的时候初始化BTomcat
	 */
	private void loadBTomcat() {
		String tomname_fun = "";
		String realpath = "";
		String tomcathome = "";
		if (applicationContext instanceof ClassPathXmlApplicationContext) {
			ClassPathXmlApplicationContext xpc = (ClassPathXmlApplicationContext) applicationContext;
			tomname_fun = (String) xpc.getEnvironment().getSystemProperties().get("tomname");
			realpath = AutoRunService.class.getResource("").getPath();
		} else if (applicationContext instanceof XmlWebApplicationContext) {
			XmlWebApplicationContext xwp = (XmlWebApplicationContext) applicationContext;
			tomname_fun = xwp.getServletContext().getInitParameter("tomname");
			realpath = xwp.getServletContext().getRealPath("");
			tomcathome = System.getProperty("catalina.home");
		}
		logger.error("web.xml中定义的tomname名字是" + tomname_fun);

		if (StringUtils.isBlank(tomname_fun)) {
			logger.error("tomcat 没有定义名称，请在tomcat\\conf\\web.xml 中增加,\r\n<context-param>\r\n<param-name>tomname</param-name>\r\n<param-value>为应用定义唯一的名字,功能</param-value>\r\n</context-param>");
			return;
		}

		String[] tfs = tomname_fun.split(",");
		if (tfs.length != 2) {
			logger.error("tomcat 定义的格式不对");
			return;
		}

		Constants.BTOMCAT.setTomname(tfs[0]);
		Constants.BTOMCAT.setFun(tfs[1]);

		Constants.BTOMCAT.setTomstarttime(VeDate.getStringDate());
		Constants.BTOMCAT.setTomdir(tomcathome);
		Constants.BTOMCAT.setAppdir(realpath);
		String ip = "";
		String hostname = "";
		try {
			InetAddress s = InetAddress.getLocalHost();
			ip = s.getHostAddress();
			hostname = s.getHostName();
		} catch (Exception e) {
			logger.error("获得机器IP和机器名错误", e);
		}
		Constants.BTOMCAT.setIp(ip);
		Constants.BTOMCAT.setMachine(hostname);
	}
}
