package org.vetech.core.modules.mybatis.page;
/**
 * 启动分页
 * 只要在任何查询前面执行了start那么就会启动分页拦截器
 * @author 章磊
 *
 */
public class PageHelper {
	private final static ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();
	public static void start(Page page){
		LOCAL_PAGE.set(page);
	}
	public static Page get(){
		return LOCAL_PAGE.get();
	}
	public static void end(){
		LOCAL_PAGE.remove();
	}
	/**
	 * 
	 * @param pageNum  起始页从1开始
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	public int getStart(int pageNum,int pageSize){
		return (pageNum - 1) * pageSize + 1;
	}
	public int getCount(int pageNum,int pageSize){
		return pageSize + (pageNum - 1) * pageSize;
	}
}
