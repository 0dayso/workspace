package org.vetech.core.business.pid.api.rtkt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.vetech.core.modules.utils.XmlUtils;


@XmlRootElement(name="RESULT")
public class RtKtResult {

	/**
	 * -1 失败;1 成功
	 */
	private String status;
	
	private String description;
	
	private Result  result;
	
	@XmlElement(name="STATUS")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public static void main(String[] args) {
		//String data=" <RESULT><STATUS>1</STATUS><DESCRIPTION><RESULT><CONTENT>aaaaa</CONTENT><RTKT><AGENTFEE>5</AGENTFEE></RTKT></RESULT></DESCRIPTION></RESULT>";
		String data="<RESULT><STATUS>-1</STATUS><DESCRIPTION>RTKT处理失败(NO TICKET FOUND,CHECK TKT NUMBER)</DESCRIPTION></RESULT>";
		 RtKtResult r = new RtKtResult();
		 if(data.contains("<STATUS>1</STATUS>")){
			r.setStatus("1");
			int start=data.indexOf("<DESCRIPTION>");
			start = start > -1 ? start + 13 : start;
			int end=data.indexOf("</DESCRIPTION>");
			String description=data.substring(start,end);
			System.out.println(description);
			Result result= (Result) XmlUtils.fromXml(description, Result.class);
			r.setResult(result);
		}else{
			r = (RtKtResult) XmlUtils.fromXml(data, RtKtResult.class);
		}
		System.out.println(r.getDescription());
	}


	
}
