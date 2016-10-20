package org.vetech.core.business.pid.api.detrxml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Detr")
public class DetrResult {
	
	TknoInfo tknoinfo;
	
	List<Segment> segments;
	
	private String content;
	
	private String fopcontent;
	
	@XmlElement(name="TknoInfo")
	public TknoInfo getTknoinfo() {
		return tknoinfo;
	}
	public void setTknoinfo(TknoInfo tknoinfo) {
		this.tknoinfo = tknoinfo;
	}
	@XmlElementWrapper(name="Segments")
	@XmlElement(name="Segment")
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	
	@XmlElement(name="Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlElement(name="FOPCONTENT")
	public String getFopcontent() {
		return fopcontent;
	}
	public void setFopcontent(String fopcontent) {
		this.fopcontent = fopcontent;
	}
	
	
}
