package org.vetech.core.business.pid.api;

/**
 * 构建CommandAbstract系列对象的接口
 */
public interface BuilderCommand {
	
	public CommandAbstract getCommandHandler();
	
	public void builderCommandHandler();
	
}
