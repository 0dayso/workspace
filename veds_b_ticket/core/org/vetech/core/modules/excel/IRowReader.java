package org.vetech.core.modules.excel;

import java.util.List;
/**
 * 解析固定行行后回调这个方法
 * 例如每1000行调用getRows这样可以批量插入到数据库
 * @author 章磊
 *
 */
public interface IRowReader {
	 /**业务逻辑实现方法 
     * @param sheetIndex 
     * @param curRow 
     * @param rowlist 
     */  
    public  void getRows(int sheetIndex,int curRow, List<Object> rowBeanlist);  
    
    /**
     * 解析每列并调用此方法
     * @param sheetIndex
     * @param curRow 当前行
     * @param col 当前列
     * @param title 表头
     * @param value 当前列的值
     * @return
     */
    public Object getCol(int sheetIndex,int curRow, int curCol,String title,String value );
}
