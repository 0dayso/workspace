/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.vetech.core.modules.service;

import org.vetech.core.modules.utils.VeDate;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author calvin
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1401593546385403720L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(VeDate.getStringDate() + "==>" + message);
	}

	public ServiceException(Throwable cause) {
		super(VeDate.getStringDate() + "==>", cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(VeDate.getStringDate() + "==>" + message, cause);
	}
}
