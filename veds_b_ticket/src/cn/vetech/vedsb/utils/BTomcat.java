package cn.vetech.vedsb.utils;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

/**
 * 登记应用
 * 
 * @author Administrator
 * 
 */
@Entity
public class BTomcat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8288153797904975373L;
	private String id;
	private String tomname;// 应用服务器名称，没在环境变量中指定
	private String ip;// 机器部署在那个ip
	private String machine;
	private String appdir;
	private String tomdir;
	private String fun;// 功能 90 表示web页面 00 下载政策 10 投放政策 40淘宝网店投放 50 是不是对外输出政策和b2c抓取任务 00下载政策的只能有一个 -100随机名字 名字未定
	private String fundes;// 功能描叙
	private String tomstarttime;// tomcat启动时间
	private String tomruntime;// tomcat最近活动时间 每1分钟刷新一次

	private String info;// 机器实时信息 如内存

	/**
	 * 是不是Web应用服务器
	 * 
	 * @return
	 */
	public static boolean thisIsWeb(BTomcat b) {
		return thisIsFun("90", b);
	}

	/**
	 * 是不是政策同步应用服务器
	 * 
	 * @return
	 */
	public static boolean thisIsJob(BTomcat b) {
		return thisIsFun("00", b);
	}

	 

	/**
	 * 是否符合指定功能的tomcat
	 * 
	 * @param fun
	 * @return
	 */
	private static boolean thisIsFun(String fun, BTomcat b) {
		if (b != null) {
			if (fun.equals(b.getFun())) {
				return true;
			}
		}
		return false;
	}

	@Length(max = 20)
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTomname() {
		return tomname;
	}

	public void setTomname(String tomname) {
		this.tomname = tomname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	public String getFundes() {
		return fundes;
	}

	public void setFundes(String fundes) {
		this.fundes = fundes;
	}

	public String getTomstarttime() {
		return tomstarttime;
	}

	public void setTomstarttime(String tomstarttime) {
		this.tomstarttime = tomstarttime;
	}

	public String getTomruntime() {
		return tomruntime;
	}

	public void setTomruntime(String tomruntime) {
		this.tomruntime = tomruntime;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getAppdir() {
		return appdir;
	}

	public void setAppdir(String appdir) {
		this.appdir = appdir;
	}

	public String getTomdir() {
		return tomdir;
	}

	public void setTomdir(String tomdir) {
		this.tomdir = tomdir;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
