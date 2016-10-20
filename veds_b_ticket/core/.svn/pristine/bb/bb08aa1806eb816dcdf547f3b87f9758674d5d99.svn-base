package org.vetech.core.business.pid.api.createXcd;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * 
 * 行程单创建新接口 
 * @author  wangxuexing
 * @version  [版本号, 2016-0802]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreateXcd2{
	private CreateXcd2Param createXcd2Param;
	public CreateXcd2(CreateXcd2Param createXcd2Param){
		this.createXcd2Param = createXcd2Param;
	}
	
	public CreateXcd2Parser createXcd2() throws RemoteException, EtermException {
		valid();
		WebEtermService etermService = new WebEtermService(createXcd2Param.getUrl());
		String date = etermService.createXcd2(new Object[]{createXcd2Param.getTkno(),createXcd2Param.getXcdno(),createXcd2Param.getUserid(),createXcd2Param.getOfficeId(),"1","0"});
		CreateXcd2Parser createXcd2Parser = new CreateXcd2Parser();
		createXcd2Parser.parser(date);
		return createXcd2Parser;
	}
	
	private void valid() throws EtermException {
		if (StringUtils.isBlank(createXcd2Param.getTkno())) {
			throw new EtermException("票号不能为空");
		}
		if (StringUtils.isBlank(createXcd2Param.getXcdno())) {
			throw new EtermException("行程单号不能为空");
		}
	}
}
