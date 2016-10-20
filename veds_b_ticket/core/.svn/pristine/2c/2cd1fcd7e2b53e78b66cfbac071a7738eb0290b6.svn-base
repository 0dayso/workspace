package org.vetech.core.business.pid.api.autoetdz;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.Encodes;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 这个接口跟ETDZ基本一样，但是接口参数多了‘是否自动选择低价出票’入参。这个主要是支持CPS平台出票。传入具体参数格式如下：
 * ddbh,userid,pass,ip,port,dylx是必填项
 * 其他项目可以通过自动出票接口获得
 * 如果想直接调用webservice接口，那么其他项都传入
 * @author zz
 *
 */
public class EtdzParam {
	private String ip;
	private String port;
	private String url;
	private String ddbh;
	private String shbh;
	private String yhbh;
	private String bmbh;
	private String userid;
	private String pass;
	private String pat;
	private String ei;
	private String office;
	private String pnrno;
	private String scny;
	private String persons;//PNR人数
	private String dylx;
	private String bbh;
	private String patvalue; //如果有重复运价就将PAT结果加到票价项上面
    private String istest;
	private String selectlowprice;//是否自动选择低价出票 值为0或1
	private String dpaypassword;//dpay密码
	private String cpqdlx;//出票渠道类型 业务逻辑字段，与接口无关
	private String uatp;
	private String uatpyxq;
	private String xyh;//协议号
	
	//userid不能为空，如果OFFICE 参数传入为空，那么共享根据该用户的VEETDZ指令的权限确定来选择恰当的pid去进行执行指令。
    public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEETDZ2</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		xml.append(XmlUtils.xmlnode("USER", userid));
		xml.append(XmlUtils.xmlnode("PWD", pass));
		if (StringUtils.isNotBlank(office) && !office.contains(",")) {
			office += ",,";
		}
		xml.append(XmlUtils.xmlnode("OFFICE", office));
		xml.append(XmlUtils.xmlnode("EI", ei));
		xml.append(XmlUtils.xmlnode("PAT", pat));
		xml.append(XmlUtils.xmlnode("SCNY", scny));
		xml.append(XmlUtils.xmlnode("PERSONS", persons));
		xml.append(XmlUtils.xmlnode("DDBH", ddbh));
		xml.append(XmlUtils.xmlnode("DYLX", dylx));
		xml.append(XmlUtils.xmlnode("ISTEST", istest));
		xml.append(XmlUtils.xmlnode("SELECTLOWPRICE", selectlowprice));
		if (StringUtils.isNotBlank(uatp)){
            xml.append(XmlUtils.xmlnode("UATPCARDID", uatp));
        }
		if (StringUtils.isNotBlank(uatpyxq)){
            xml.append(XmlUtils.xmlnode("UATPVALIDDATE", uatpyxq));
        }
        if(StringUtils.isNotBlank(dpaypassword)){
			dpaypassword = StringUtils.trimToEmpty(dpaypassword);
			String base64pass="";
			try {
				base64pass = Encodes.encodeBase64(dpaypassword.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			xml.append(XmlUtils.xmlnode("DPAYPASSWORD", base64pass));
			xml.append(XmlUtils.xmlnode("ETDZTYPE", "DPAYD"));
			xml.append(XmlUtils.xmlnode("ETDZCMD", "ETDZ:D"));
		}
        
        if(StringUtils.isNotBlank(xyh)){
        	xml.append(XmlUtils.xmlnode("XYH", xyh));
        }
		xml.append("</PARA>");
		xml.append("</INPUT>");
		return xml.toString();
	}
   
	public String getUatp()
    {
        return uatp;
    }
    public void setUatp(String uatp)
    {
        this.uatp = uatp;
    }
    public String getUatpyxq()
    {
        return uatpyxq;
    }
    public void setUatpyxq(String uatpyxq)
    {
        this.uatpyxq = uatpyxq;
    }
    public String getPatvalue() {
		return patvalue;
	}
	public void setPatvalue(String patvalue) {
		this.patvalue = patvalue;
	}
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getDylx() {
		return dylx;
	}
	public void setDylx(String dylx) {
		this.dylx = dylx;
	}
	public String getEi() {
		return ei;
	}
	public void setEi(String ei) {
		this.ei = ei;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getScny() {
		return scny;
	}
	public void setScny(String scny) {
		this.scny = scny;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSelectlowprice() {
		return selectlowprice;
	}
	public void setSelectlowprice(String selectlowprice) {
		this.selectlowprice = selectlowprice;
	}
	public String getDpaypassword() {
		return dpaypassword;
	}
	public void setDpaypassword(String dpaypassword) {
		this.dpaypassword = dpaypassword;
	}
	public String getCpqdlx() {
		return cpqdlx;
	}
	public void setCpqdlx(String cpqdlx) {
		this.cpqdlx = cpqdlx;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getBmbh() {
		return bmbh;
	}
	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIstest() {
		return istest;
	}
	public void setIstest(String istest) {
		this.istest = istest;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getXyh() {
		return xyh;
	}

	public void setXyh(String xyh) {
		this.xyh = xyh;
	}
	
	
}
