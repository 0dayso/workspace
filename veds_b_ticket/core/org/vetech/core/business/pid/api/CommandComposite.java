package org.vetech.core.business.pid.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.vetech.core.business.pid.pidbean.PidAvhBean;
import org.vetech.core.business.pid.pidbean.PidAvhResult;
import org.vetech.core.business.pid.pidbean.PidResult;

/**
 * 组合对象，组合各种指令接口，共同完成一项任务 该对象excute方法返回多个指令执行后的结果集合放入returnCompositeCommand这个属性中
 * 
 */
public class CommandComposite extends CommandAbstract {

	private List<CommandAbstract> commandAbstractList  = new ArrayList<CommandAbstract>();
	private Map<String, String> returnCommand  = new HashMap<String, String>();
	private String name = "";
	
	public CommandComposite(){
		
		
	}

	/**
	 * 循环执行指令对象集合，然后将结果装入组合对象结果集中，并且设置对象的名称 根据对象名称自动实例华该对象对应的解析器
	 */
	@Override
	public PidResult excute() throws Exception {
		PidAvhResult pidResult = new PidAvhResult();
		
		// 在BuilderDetectorComposite的builderCommandHandler()方法里通过反射添加了avh和avhFD两个指令对象,首先判断是否空
		if (!commandAbstractList.isEmpty()) {

			Iterator<CommandAbstract> it = commandAbstractList.iterator();// 遍历指令
			while (it.hasNext()) {
				CommandAbstract command = it.next();
				PidResult data = command.excute();
				name = name + command.getCommand().getName();
				returnCommand.put(command.getCommand().getName(), data.getResultXml());
			}
		}
		
		PidAvhBean avhBean = (PidAvhBean) getCommand();
		avhBean.setName(name);
		
		pidResult.setCommandMap(returnCommand);
		
		return pidResult;
	}
	
	@Override
	public void add(CommandAbstract command) {
		commandAbstractList.add(command);
	}

	@Override
	public void delete(String commandName) {

	}

	@Override
	public CommandAbstract get(String commandName) {
		return null;
	}
}
