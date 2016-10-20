package cn.vetech.web.vedsb.jpcwgl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.excel.RowReader;

import cn.vetech.vedsb.utils.bankdb.BankDbUtil;

public class BankImport extends RowReader{
	private List<String> headRow=new ArrayList<String>();
	private List<Map<String,Object>> bankList=new ArrayList<Map<String,Object>>();
	private int headNo;
	public BankImport(int headNo) {
		this.headNo=headNo;
	}
	@Override
	public void getRows(int sheetIndex, int curRow, List<Object> rowlist) {
		for(int i=0;i<rowlist.size();i++){
			Map<String, Object> map = (Map) rowlist.get(i);
			Collection<Object> values=map.values();
			boolean isNotNull=false;
			for(Object obj : values){
				String val=BankDbUtil.objToString(obj);
				if(StringUtils.isNotBlank(val)){
					isNotNull=true;
					break;
				}
			}
			if(isNotNull){
				bankList.add(map);
			}
		}
	}
	
	@Override
	public Object getCol(int sheetIndex, int curRow, int curCol, String title, String value) {
		if(curRow==headNo+1){
			headRow.add(title);
		}
		return value;
	}
	public List<String> getHeadRow() {
		return headRow;
	}
	public void setHeadRow(List<String> headRow) {
		this.headRow = headRow;
	}
	public List<Map<String, Object>> getBankList() {
		return bankList;
	}
	public void setBankList(List<Map<String, Object>> bankList) {
		this.bankList = bankList;
	}
	public int getHeadNo() {
		return headNo;
	}
	public void setHeadNo(int headNo) {
		this.headNo = headNo;
	}
}
