package org.vetech.core.business.pid.api;

import org.vetech.core.business.pid.pidbean.PidAvhBean;

/**
 * 构建DetectorCommandComposite对象
 * 
 * @author 章磊
 * 
 */
public class BuilderDetectorComposite implements BuilderCommand {

	private CommandAbstract commandComposite = new CommandComposite(); // 组合指令对象

	// 装饰组合指令对象
	private DetectorCommandComposite detectorCommandComposite = new DetectorCommandComposite();

	private PidAvhBean avhBean;

	public BuilderDetectorComposite(PidAvhBean avhBean) {
		this.avhBean = avhBean;
	}

	public void builderCommandHandler() {
		commandComposite.setCommand(avhBean);
		
		try {
			
			// 获得AVH指令,通过设置的AVH,AVHFD类型，反射得到相应的执行命令对象
			CommandAbstract avh = CommandFactory.getCommand(avhBean.getSearchType());
			avh.setCommand(avhBean); 	// 设置指令
			
			// 获得FD指令
			CommandAbstract fd = CommandFactory.getCommand(avhBean.getFdType());
			fd.setCommand(avhBean);		// 设置指令
			
			commandComposite.add(avh); 	// 添加AVH指令到组合对象中
			commandComposite.add(fd); 	// 添加FD指令到组合对象中
			
			detectorCommandComposite.setCommandAbstract(commandComposite); // 像装饰对象中设置被装饰对象
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	public CommandAbstract getCommandHandler() {
		return detectorCommandComposite;
	}
	
}