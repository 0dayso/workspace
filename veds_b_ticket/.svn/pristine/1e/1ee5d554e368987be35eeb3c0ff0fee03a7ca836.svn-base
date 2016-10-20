package cn.vetech.vedsb.platpolicy.cps.response.pay;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * //	String res = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
 *<Response>" +
 *	<Status>" +
 *		<StatusCode>OK</StatusCode>" +
 *		<StatusMessage></StatusMessage>" +
 *		<StatusDesc>getShzhglList</StatusDesc>" +
 *	</Status>" +
 *	<Result>" +
 *		<Shzh>" +
 *			<Id>1212251902328269</Id>" +
 *			<Zhbh>1212251902323160</Zhbh>" +
 *			<Zhmc>默认账户</Zhmc>" +
 *			<Kyye>4974</Kyye>" +
 *			<Zhye>4974</Zhye>" +
 *			<Cpmc>机场服务,机票,旅游,火车票,保险,票证,班车,酒店,机票追位</Cpmc>" +
 *			<Cpbh>
 *				312012430, 312012401, 312012402, 312012403, 312012404,
 *				312012405, 312012406, 312012409, 312012410
 *			</Cpbh>
 *			<Djye>0</Djye>" +
 *			<Sfqy>0</Sfqy>" +
 *		</Shzh>" +
 *	</Result>" +
 *</Response>"; 
 * @author win7
 *
 */
@XmlRootElement(name="Response")
public class CpsZhResult {
	private CpsZhStatus status;
	
	private List<CpsShzhData> zhdata;
	
	@XmlElement(name="Status")
	public CpsZhStatus getStatus() {
		return status;
	}

	public void setStatus(CpsZhStatus status) {
		this.status = status;
	}
	@XmlElement(name="Shzh")
	@XmlElementWrapper(name="Result")
	public List<CpsShzhData> getZhdata() {
		return zhdata;
	}

	public void setZhdata(List<CpsShzhData> zhdata) {
		this.zhdata = zhdata;
	}
	
}
