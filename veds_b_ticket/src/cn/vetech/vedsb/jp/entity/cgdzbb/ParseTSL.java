package cn.vetech.vedsb.jp.entity.cgdzbb;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 解析接口返回的tsl数据
 * @author vetech
 *
 */
public class ParseTSL {
	
	
	/**
	 * xml转化为bean
	 * @param xmlStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JpTslBean parseTSLXml(String xmlStr){
		if(StringUtils.isBlank(xmlStr)){
			return null;
		}
		JpTslBean bean = new JpTslBean();
		bean.setXmlStr(xmlStr);
		try {
			Document document = DocumentHelper.parseText(xmlStr);
			Element  root= document.getRootElement();
			Element status = root.element("STATUS");
			if(status == null){
				return null;
			}
			bean.setStatus(status.getText());
			Element description = root.element("DESCRIPTION");
			if(description == null){
				return null;
			}
			bean.setDescription(description.getText());
			Element result = description.element("Result");
			if(result!=null){
				bean.setResult(result.getText());
				List<Element> childList=result.elements();
				if(CollectionUtils.isNotEmpty(childList)){
					for(int i=0;i<childList.size();i++){
						Element resultChild=childList.get(i);
						if(resultChild!=null){
							String name = resultChild.getName();
							if("PID".equals(name)){
								bean.setPid(resultChild.getText());
							}else if("NOTIMPORTED".equals(name)){
								bean.setNotimported(resultChild.getText());
							}else if("Tickets".equals(name)){
								List<Element> Tickets=resultChild.elements();
								if(CollectionUtils.isNotEmpty(Tickets)){
									for(int j=0;j<Tickets.size();j++){
										Element ticket=Tickets.get(j);
										if(ticket!=null){
											if("Ticket".equals(ticket.getName())){
												JpTslBean ticketBean=new JpTslBean();
												ticketBean.setPid(bean.getPid());
												Element ticketno=ticket.element("TicketNO");
												if(ticketno!=null){
													ticketBean.setTicketno(ticketno.getText());
												}
												Element continual=ticket.element("Continual");
												if(continual!=null){
													ticketBean.setContinual(continual.getText());
												}
												Element origdest=ticket.element("ORIGDEST");
												if(origdest!=null){
													ticketBean.setOrigdest(origdest.getText());
													String origdestValue=origdest.getText();
													String isVoid="0"; 
													String refund="0";
													if(origdestValue!=null){
														if(origdestValue.length()==7){
															//航程信息
														}else if(origdestValue.length()==4){
															isVoid="1";//废票
														}else{
															refund="1";//退票
														}
													}
													ticketBean.setIsRefund(refund);
													ticketBean.setIsVoid(isVoid);
												}
												Element collection=ticket.element("Collection");
												if(collection!=null){
													ticketBean.setCollection(collection.getText());
												}
												Element taxs=ticket.element("TAXS");
												if(taxs!=null){
													ticketBean.setTaxs(taxs.getText());
												}
												Element comm=ticket.element("COMM");
												if(comm!=null){
													ticketBean.setComm(comm.getText());
												}
												Element pnr=ticket.element("PNR");
												if(pnr!=null){
													ticketBean.setPnr(pnr.getText());
												}
												Element agent=ticket.element("AGENT");
												if(agent!=null){
													ticketBean.setAgent(agent.getText());
												}
												bean.getTicketList().add(ticketBean);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.print("xml转bean失败");
			e.printStackTrace();
		}
		return bean;
	}
	
	
	public static void main(String ages[]){
		String s = "<RESULT><STATUS>0</STATUS><DESCRIPTION><Result><PID>78135</PID><CONTENT></CONTENT>"
			+"<Tickets><Ticket><Index>1</Index><TicketNO>160-1791075187</TicketNO><Continual></Continual><ORIGDEST>LON LON</ORIGDEST>"
					+"<Collection>7610.00</Collection><TAXS>1157.00</TAXS><COMM>3.00</COMM><PNR>KT2FGD</PNR><AGENT>5358</AGENT></Ticket></Tickets><NOTIMPORTED></NOTIMPORTED></Result></DESCRIPTION></RESULT>";
		JpTslBean j = ParseTSL.parseTSLXml(s);
		System.out.print(j.toString());
	}
}
