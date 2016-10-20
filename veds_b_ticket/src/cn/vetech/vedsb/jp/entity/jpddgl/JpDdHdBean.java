package cn.vetech.vedsb.jp.entity.jpddgl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.vetech.core.modules.utils.XmlUtils;

public class JpDdHdBean {
	/**ID号主键*/
	private String id;
	/**订单编号*/
	private String ddbh;
	/**商户编号*/
	private String shbh;
	/**顺序号*/
	private String sxh;
	/**出发城市*/
	private String cfcity;
	/**到达城市*/
	private String ddcity;
	/**出发时间存日期和时间*/
	private String cfsj;
	/**到达时间存日期和时间*/
	private String ddsj;
	/**出发航站楼*/
	private String cfhzl;
	/**到达航站楼*/
	private String ddhzl;
	/**飞机机型*/
	private String fjjx;
	/**航段状态*/
	private String hdzt;
	/**销售航班号*/
	private String xs_hbh;
	/**销售舱位*/
	private String xs_cw;
	/**销售退改签*/
	private String xs_tgq;
	/**采购航班号*/
	private String cg_hbh;
	/**采购舱位*/
	private String cg_cw;
	/**采购退改签*/
	private String cg_tgq;
	/**外部航段序号*/
	private String wbsxh;

	public String toXmlString(){
		StringBuffer xml = new StringBuffer();
		Class<?> _class = this.getClass();
		Field[] fields = _class.getDeclaredFields();
		for (Field f : fields) {
			Class<?> c = f.getType();
			String typeName = c.getSimpleName();
			if ("String".equals(typeName)) {
				String name = f.getName();
				String value = null;
				try {
					value = BeanUtils.getProperty(this, name);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			    if (null != value) {
					xml.append(XmlUtils.xmlnode(name.toUpperCase(), value));
				}
			}
		}
		return xml.toString();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getCfcity() {
		return cfcity;
	}
	public void setCfcity(String cfcity) {
		this.cfcity = cfcity;
	}
	public String getDdcity() {
		return ddcity;
	}
	public void setDdcity(String ddcity) {
		this.ddcity = ddcity;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getDdsj() {
		return ddsj;
	}
	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
	}
	public String getCfhzl() {
		return cfhzl;
	}
	public void setCfhzl(String cfhzl) {
		this.cfhzl = cfhzl;
	}
	public String getDdhzl() {
		return ddhzl;
	}
	public void setDdhzl(String ddhzl) {
		this.ddhzl = ddhzl;
	}
	public String getFjjx() {
		return fjjx;
	}
	public void setFjjx(String fjjx) {
		this.fjjx = fjjx;
	}
	public String getHdzt() {
		return hdzt;
	}
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	public String getXs_hbh() {
		return xs_hbh;
	}
	public void setXs_hbh(String xs_hbh) {
		this.xs_hbh = xs_hbh;
	}
	public String getXs_cw() {
		return xs_cw;
	}
	public void setXs_cw(String xs_cw) {
		this.xs_cw = xs_cw;
	}
	public String getXs_tgq() {
		return xs_tgq;
	}
	public void setXs_tgq(String xs_tgq) {
		this.xs_tgq = xs_tgq;
	}
	public String getCg_hbh() {
		return cg_hbh;
	}
	public void setCg_hbh(String cg_hbh) {
		this.cg_hbh = cg_hbh;
	}
	public String getCg_cw() {
		return cg_cw;
	}
	public void setCg_cw(String cg_cw) {
		this.cg_cw = cg_cw;
	}
	public String getCg_tgq() {
		return cg_tgq;
	}
	public void setCg_tgq(String cg_tgq) {
		this.cg_tgq = cg_tgq;
	}
	public String getWbsxh() {
		return wbsxh;
	}
	public void setWbsxh(String wbsxh) {
		this.wbsxh = wbsxh;
	}
	
	@SuppressWarnings("unchecked")  
    public static <K, V> Map<K, V> Bean2Map(Object javaBean) {  
        Map<K, V> ret = new HashMap<K, V>();  
        try {  
            Method[] methods = javaBean.getClass().getDeclaredMethods();  
            for (Method method : methods) {  
                if (method.getName().startsWith("get")) {  
                    String field = method.getName();  
                    field = field.substring(field.indexOf("get") + 3);  
                    field = field.toLowerCase().charAt(0) + field.substring(1);  
                    Object value = method.invoke(javaBean, (Object[]) null);
                    if(value != null){
                    	ret.put((K) field, (V) (null == value ? "" : value));
                    }
                }  
            }  
        } catch (Exception e) {  
        }  
        return ret;  
    }
}
