package cn.vetech.vedsb.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {
	/**
	 * 当前启动的应用服务器的信息
	 */
	public static BTomcat BTOMCAT = new BTomcat();

	public final static ExecutorService PID_INFO_EXECUTOR = Executors.newFixedThreadPool(5);

	/**
	 * 扫描待出票订单放入出票队列
	 */
	public final static ExecutorService JP_ZDCP_EXECUTOR = Executors.newFixedThreadPool(30);

}
