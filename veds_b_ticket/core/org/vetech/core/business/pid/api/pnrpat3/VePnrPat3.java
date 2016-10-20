package org.vetech.core.business.pid.api.pnrpat3;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * VEETDZ2
 * 
 * @author houya
 */
public class VePnrPat3{
	public String excuted(VePnrPat3Command cmd) throws EtermException {
		WebEtermService service=new WebEtermService(cmd.getUrl());
		String xml = cmd.toXml();
		String rtnXml = StringUtils.trimToEmpty(service.generalCmdProcess(xml));
		if (rtnXml.indexOf("<FLAG>") > 0) {
			int start = rtnXml.indexOf("<ERRTXT>") + "<ERRTXT>".length();
			int end = rtnXml.indexOf("</ERRTXT>");
			String ERRTXT = StringUtils.substring(rtnXml, start, end);
			throw new EtermException(ERRTXT);
		}
		//当接口不支持的时候
		if (rtnXml.indexOf("<VEPNRPAT3>") < 0) {
			throw new EtermException(rtnXml);
		}
		return rtnXml;
	}
}
