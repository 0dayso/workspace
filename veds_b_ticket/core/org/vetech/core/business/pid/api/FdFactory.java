package org.vetech.core.business.pid.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.vetech.core.business.pid.pidbean.PidFdBean;

public class FdFactory {

	private PidFdBean fdBean;
	private static ExecutorService service;
	private static FdFactory factory = new FdFactory();

	private FdFactory() {
	}

	@SuppressWarnings("static-access")
	public static synchronized FdFactory getInstance(PidFdBean fdBean) {
		
		if (null == factory.service) {
			factory.service = Executors.newFixedThreadPool(10);
		}
		
		factory.fdBean = fdBean;
		return factory;
	}

	public void notifya() {
		try {
			if ("FD".equals(fdBean.getName())) {
				//FdUpdate updateFD = new FdUpdate(fdBean);// 更新运价
				//service.execute(updateFD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
