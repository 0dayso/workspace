package org.vetech.core.business.pid.pidbean;

/**
 * 指令父类JavaBean
 * 
 * @author gengxianyan
 * @version [版本号, Apr 11, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class Command {

	private String name; // 指令名称

	private String command; // 黑屏执行的指令

	private String pass; // 登陆密码

	private String url; // 连接地址

	private String userid; // 登陆用户

	private String fs; // 直通和匹配数据库处理0,1 0-直通 1-匹配数据库

	private String psfs; // 配色方案

	private String commandFomat; // 指令格式，暂时没用

	private String office; // office号

	private String plat;// 平台

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCommandFomat() {
		return commandFomat;
	}

	public void setCommandFomat(String commandFomat) {
		this.commandFomat = commandFomat;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPsfs() {
		return psfs;
	}

	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}
}
