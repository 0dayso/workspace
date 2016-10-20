package cn.vetech.vedsb.jp.entity.jpddgl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.vetech.core.modules.utils.XmlUtils;

public class JpDdCjrBean {
	/**ID号主键*/
	private String id;
	/**订单编号*/
	private String ddbh;
	/**商户编号*/
	private String shbh;
	/**顺序号*/
	private String sxh;
	/**乘机人类型1成人，2儿童，3婴儿*/
	private String cjrlx;
	/**乘机人*/
	private String cjr;
	/**证件类型*/
	private String zjlx;
	/**证件号码*/
	private String zjhm;
	/**手机号码*/
	private String sjhm;
	/**销售价*/
	private Double xs_pj;
	/**销售机建*/
	private Long xs_jsf;
	/**销售税费*/
	private Long xs_tax;
	/**销售航意险份数*/
	private Short xs_hyxfs;
	/**销售延误险份数*/
	private Short xs_ywxfs;
	/**销售航意险利润*/
	private Double xs_hyxlr;
	/**销售延误险利润*/
	private Double xs_ywxlr;
	/**销售邮寄费*/
	private double xs_yjf;
	/**销售金额*/
	private Double xs_je;
	/**采购账单价*/
	private Long cg_zdj;
	/**采购价*/
	private Double cg_pj;
	/**采购机建*/
	private Long cg_jsf;
	/**采购税费*/
	private Long cg_tax;
	/**出票状态0未出票，1已出票*/
	private String cpzt;
	/**票号*/
	private String tkno;
	/**行程单号*/
	private String xcdh;
	/**性别M男F女*/
	private String xb;
	/**国籍*/
	private String gj;
	/**出生日期*/
	private String csrq;
	/**证件有效期*/
	private String zjyxq;
	/**证件签发国*/
	private String zjqfg;
	/**外部乘机人ID*/
	private String wbcjrid;
	/**销售账单价*/
	private Long xs_zdj;

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
	public String getCjrlx() {
		return cjrlx;
	}
	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getCpzt() {
		return cpzt;
	}
	public void setCpzt(String cpzt) {
		this.cpzt = cpzt;
	}
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	public String getXcdh() {
		return xcdh;
	}
	public void setXcdh(String xcdh) {
		this.xcdh = xcdh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZjyxq() {
		return zjyxq;
	}
	public void setZjyxq(String zjyxq) {
		this.zjyxq = zjyxq;
	}
	public String getZjqfg() {
		return zjqfg;
	}
	public void setZjqfg(String zjqfg) {
		this.zjqfg = zjqfg;
	}
	public String getWbcjrid() {
		return wbcjrid;
	}
	public void setWbcjrid(String wbcjrid) {
		this.wbcjrid = wbcjrid;
	}
	
	@SuppressWarnings("unchecked")  
    public static <K, V> Map<K, V> Bean2Map(Object javaBean) {  
        Map<K, V> ret = new HashMap<K, V>();  
        try {  
            Method[] methods = javaBean.getClass().getDeclaredMethods();  
            for (Method method : methods) {
            	Type t = method.getGenericReturnType();
            	String s = t.toString();
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

	public Long getXs_jsf() {
		return xs_jsf;
	}

	public void setXs_jsf(Long xs_jsf) {
		this.xs_jsf = xs_jsf;
	}

	public Long getXs_tax() {
		return xs_tax;
	}

	public void setXs_tax(Long xs_tax) {
		this.xs_tax = xs_tax;
	}

	public Short getXs_hyxfs() {
		return xs_hyxfs;
	}

	public void setXs_hyxfs(Short xs_hyxfs) {
		this.xs_hyxfs = xs_hyxfs;
	}

	public Short getXs_ywxfs() {
		return xs_ywxfs;
	}

	public void setXs_ywxfs(Short xs_ywxfs) {
		this.xs_ywxfs = xs_ywxfs;
	}

	public Long getCg_jsf() {
		return cg_jsf;
	}

	public void setCg_jsf(Long cg_jsf) {
		this.cg_jsf = cg_jsf;
	}

	public Long getCg_tax() {
		return cg_tax;
	}

	public void setCg_tax(Long cg_tax) {
		this.cg_tax = cg_tax;
	}

	public double getXs_yjf() {
		return xs_yjf;
	}

	public void setXs_yjf(double xs_yjf) {
		this.xs_yjf = xs_yjf;
	}

	public Double getXs_pj() {
		return xs_pj;
	}

	public void setXs_pj(Double xs_pj) {
		this.xs_pj = xs_pj;
	}

	public Double getXs_hyxlr() {
		return xs_hyxlr;
	}

	public void setXs_hyxlr(Double xs_hyxlr) {
		this.xs_hyxlr = xs_hyxlr;
	}

	public Double getXs_ywxlr() {
		return xs_ywxlr;
	}

	public void setXs_ywxlr(Double xs_ywxlr) {
		this.xs_ywxlr = xs_ywxlr;
	}

	public Double getXs_je() {
		return xs_je;
	}

	public void setXs_je(Double xs_je) {
		this.xs_je = xs_je;
	}

	public Long getCg_zdj() {
		return cg_zdj;
	}

	public void setCg_zdj(Long cg_zdj) {
		this.cg_zdj = cg_zdj;
	}

	public Double getCg_pj() {
		return cg_pj;
	}

	public void setCg_pj(Double cg_pj) {
		this.cg_pj = cg_pj;
	}

	public Long getXs_zdj() {
		return xs_zdj;
	}

	public void setXs_zdj(Long xs_zdj) {
		this.xs_zdj = xs_zdj;
	}
}
