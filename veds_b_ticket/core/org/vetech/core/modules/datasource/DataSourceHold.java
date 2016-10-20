package org.vetech.core.modules.datasource;

public class DataSourceHold {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	public static void  set(String id){
		threadLocal.set(id);
	}
	public static String get(){
		return threadLocal.get();
	}
	public static void clear(){
		threadLocal.remove();
	}
}
