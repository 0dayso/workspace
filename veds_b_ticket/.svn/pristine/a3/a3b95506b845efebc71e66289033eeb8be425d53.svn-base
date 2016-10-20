package cn.vetech.vedsb.platpolicy.cps.response.ticket;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;


/**
 * DETR CPS接口响应
 * 
 * @author wangxuexing
 * @version [版本号, MAY 17, 2016]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS8000]
 */
@XmlRootElement(name = "response")
public class GetTicketByDetrResponse extends CpsResponse {
	/**
	 * DETR原始信息
	 */
	private String content;

	/**
	 * 机票信息
	 */
	private TknoInfo tknoInfo;

	/**
	 * 航段信息集合
	 */
	private List<Segment> segments;

	@XmlElement(name = "tknoInfo")
	public TknoInfo getTknoInfo() {
		return tknoInfo;
	}

	public void setTknoInfo(TknoInfo tknoInfo) {
		this.tknoInfo = tknoInfo;
	}

	@XmlElementWrapper(name = "segments")
	@XmlElement(name = "segment")
	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	@XmlElement(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toXmlString(){
		StringBuffer returnXml = new StringBuffer();
		Class<?> _class = this.getClass();
		Field[] fields = _class.getDeclaredFields();
		try{
			//1. 获取订单XML
			returnXml.append("<Detr>");
			for (Field f : fields) {
				Class<?> c = f.getType();
				String typeName = c.getSimpleName();
				if ("String".equals(typeName)) {
					String name = f.getName();
					String value = BeanUtils.getProperty(this, name);
				    if (null != value) {
						returnXml.append(XmlUtils.xmlnode(name.substring(0,1).toUpperCase()+name.substring(1), value));
					}
				}
			}
			
			returnXml.append("<TknoInfo>");
			Method getCjrListMethod = this.getClass().getMethod("getTknoInfo");
			// 调用getter方法获取属性值
			
			TknoInfo tknoInfo = (TknoInfo) getCjrListMethod.invoke(this);
			if(tknoInfo != null){
				returnXml.append(tknoInfo.toXmlString());
			}
			returnXml.append("</TknoInfo>");
			
			returnXml.append("<Segments>");
			Method getSegmentsMethod = this.getClass().getMethod("getSegments");
			List<Segment> segmentList = (List<Segment>) getSegmentsMethod.invoke(this);
			if(segmentList != null){
				for(Segment segment : segmentList){
					returnXml.append("<Segment>");
					returnXml.append(segment.toXmlString());
					returnXml.append("</Segment>");
				}
			}
			returnXml.append("</Segments>");
			returnXml.append("</Detr>");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return returnXml.toString();
	}
	
	public static void main(String[] srgs){
		String xml = "<response>"
				+"    <status>0</status>"
				+"    <freeuse>false</freeuse>"
				+"    <segments>"
				+"        <segment>"
				+"            <depCity>CAN</depCity>"
				+"            <depDatetime>2015-05-26 22:05:00</depDatetime>"
				+"            <seatClass>T</seatClass>"
				+"            <arrCity>WUH</arrCity>"
				+"            <flightNo>CZ3367</flightNo>"
				+"            <ticketStatus>OPEN FOR USE</ticketStatus>"
				+"            <baggage>20K</baggage>"
				+"        </segment>"
				+"    </segments>"
				+"    <tknoInfo>"
				+"        <ei>不得签转退票/变更收费 </ei>"
				+"        <name>方永林</name>"
				+"        <tkno>784-8542048767</tkno>"
				+"    </tknoInfo>"
				+"</response>";
		GetTicketByDetrResponse r = (GetTicketByDetrResponse) XmlUtils.fromXml(xml, GetTicketByDetrResponse.class);
		System.out.println(r.toXmlString().replace("<TknoInfo>", "<TknoInfo><DTERFS>"+1+"</DERT>"));
	}
	
}
