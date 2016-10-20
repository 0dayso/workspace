package org.vetech.core.modules.mybatis.page;

import java.util.List;
/**
 * 分页对象
 * @author 章磊
 *
 */
@SuppressWarnings({"rawtypes" })
public class Page {
	/**
	 * 总记录数
	 */
	private long totalCount;
	 /**
	  * 当前页数
	  */
	private int pageNum;
	
	/**
	 * 每页显示的大小
	 */
	private int pageSize;
	
	/**
	 * 当前结果集合
	 */
	private List list;
	
	/**
	 * 总页数 
	 */
	private int pageAllCount;
	
	public Page(int pageNum,int pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	
	}
	


	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		int pagenumcount = (int)(totalCount/pageSize);
		if(totalCount%pageSize>0){
			pagenumcount ++;
		}
		this.pageAllCount = pagenumcount;
	}
	
	public int getStart(){
		return (getPageNum() - 1) * getPageSize() + 1;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageAllCount() {
		return pageAllCount;
	}

	public void setPageAllCount(int pageAllCount) {
		this.pageAllCount = pageAllCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	public int getCount(){
		return getPageSize() + (getPageNum() - 1) * getPageSize();
	}
	
}
