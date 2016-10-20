package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.List;
import java.util.Map;

import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;


/**
 * 
 * 平台交票需要传递的数据
 * 
 * @author Administrator
 * @version [版本号, Jul 29, 2012]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class PlatJpBean { 
	private String wdbh;//外部订单编号
	private List<String> ddbhs; //系统订单编号  一个外部订单编号可能对应多个系统订单编号
	private List<JpKhdd> JpKhddList;//
	private List<JpKhddKz> JpKhddKzList;//
	private Map<String,List<JpJp>> jpJpListMap;//多个票号
	private Map<String,List<JpKhddHd>> jpKhddHdListMap;//;多段航程
	private Map<String,List<JpKhddCjr>> jpKhddCjrListMap;//;多个乘机人
	public String getWdbh() {
		return wdbh;
	}
	public void setWdbh(String wdbh) {
		this.wdbh = wdbh;
	}
	public List<String> getDdbhs() {
		return ddbhs;
	}
	public void setDdbhs(List<String> ddbhs) {
		this.ddbhs = ddbhs;
	}
	public List<JpKhdd> getJpKhddList() {
		return JpKhddList;
	}
	public void setJpKhddList(List<JpKhdd> jpKhddList) {
		JpKhddList = jpKhddList;
	}
	public List<JpKhddKz> getJpKhddKzList() {
		return JpKhddKzList;
	}
	public void setJpKhddKzList(List<JpKhddKz> jpKhddKzList) {
		JpKhddKzList = jpKhddKzList;
	}
	public Map<String, List<JpJp>> getJpJpListMap() {
		return jpJpListMap;
	}
	public void setJpJpListMap(Map<String, List<JpJp>> jpJpListMap) {
		this.jpJpListMap = jpJpListMap;
	}
	public Map<String, List<JpKhddHd>> getJpKhddHdListMap() {
		return jpKhddHdListMap;
	}
	public void setJpKhddHdListMap(Map<String, List<JpKhddHd>> jpKhddHdListMap) {
		this.jpKhddHdListMap = jpKhddHdListMap;
	}
	public Map<String, List<JpKhddCjr>> getJpKhddCjrListMap() {
		return jpKhddCjrListMap;
	}
	public void setJpKhddCjrListMap(Map<String, List<JpKhddCjr>> jpKhddCjrListMap) {
		this.jpKhddCjrListMap = jpKhddCjrListMap;
	}
	
}
