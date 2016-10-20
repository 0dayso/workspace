package org.vetech.core.business.pid.api.avh2;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.avh.AvhParser;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.business.pid.util.PidUtils;
import org.vetech.core.modules.utils.VeDate;

public class Avh2 {
	public String avh(Avh2Param avhParam) throws EtermException {
		// 验证参数
		valid(avhParam);
		WebEtermService etermService = new WebEtermService(avhParam.getUrl());
		String hc = avhParam.getCfcs() + avhParam.getDdcs();
		String cfdate = avhParam.getDepDate();
		cfdate = PidUtils.dateToCommandDate(cfdate.substring(0,10));
		String hkgs = avhParam.getHkgs();
		hkgs = StringUtils.trimToEmpty(hkgs);
		String isDirect = avhParam.getIsDirect();
		if (StringUtils.isBlank(isDirect)) {
			isDirect = "1";
		}
		String data = etermService.VEAVH2(hc, cfdate, avhParam.getHkgs(), isDirect, avhParam.getDepTime(), avhParam.getIfgxhb(),avhParam.getUserid(), "0");
		String bigPID = "";
		String hbdata = "";
		if (StringUtils.isNotBlank(data)) {

			Document db = null;
			try {
				db = DocumentHelper.parseText(data);
			} catch (DocumentException e) {
				e.printStackTrace();
				throw new EtermException("解析航班数据失败");
			}
			Element root = db.getRootElement();
			bigPID = root.elementText("BigPID");
			hbdata = root.elementText("Content");
			if ("**NO ROUTING**".equals(hbdata)) {
				throw new EtermException("没有航班数据");
			}

			String xml = "";
			if ("0".equals(bigPID)) { // 小配置
				xml = AvhParser.EtermToXml(AvhParser.parseEterm(hbdata));// 格式化文本数据
																			// 而后正则表达式，将文本解析封装为xml结构
				if (StringUtils.isNotEmpty(xml) && xml.indexOf("<HEAD") > -1) {
					String headStr = xml.indexOf("<HEAD />") > -1 ? "" : xml;

					String oldStr = "<HEAD />";
					if (StringUtils.isNotEmpty(headStr)) {
						headStr = headStr.substring(headStr.indexOf("<HEAD>") + 6, headStr.indexOf("</HEAD>"));
						oldStr = "<HEAD></HEAD>";
					}

					if (StringUtils.isEmpty(headStr)) {
						String head = spellHead(avhParam.getCfcs()+avhParam.getDdcs(), avhParam.getDepDate());
						xml = StringUtils.replace(xml, oldStr, head);

					}
				}
			} else if ("1".equals(bigPID)) { // 大配置
				hbdata = hbdata.replaceAll("<", "&lt;");
				xml = AvhParser.BigPidEtermToXml(AvhParser.parseEterm(hbdata));
			}
			if (StringUtils.isNotBlank(xml)) {
				return xml;
			} else {
				throw new EtermException("中文界面解析航班数据错误");
			}

		} else {
			throw new EtermException("没有航班数据");
		}
	}
	private void valid(Avh2Param avhParam) throws EtermException {
		if (StringUtils.isBlank(avhParam.getCfcs())) {
			throw new EtermException("出发城市不能为空");
		}
		if (StringUtils.isBlank(avhParam.getDdcs())) {
			throw new EtermException("到达城市不能为空");
		}
		if (StringUtils.isBlank(avhParam.getDepDate()) && !VeDate.isDate(avhParam.getDepDate(), "yyyy-MM-dd") && avhParam.getDepDate().length()==10) {
			throw new EtermException("出发日期格式必须是yyyy-mm-dd");
		}
		if (StringUtils.isBlank(avhParam.getDepTime()) && !VeDate.isDate(avhParam.getDepTime(), "HH:mm") && avhParam.getDepTime().length()==5) {
			throw new EtermException("出发日期格式必须是HH:mm");
		}
		if (StringUtils.isBlank(avhParam.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
	}
	/**
     * 针对使用信天游配置返回的数据中，HEAD节点解析不了，手动拼装HEAD节点
     * 
     * @param date 15dec12
     */
    private String spellHead(String hc, String date) {
        
        String week = VeDate.getWeekEn(date);
        week = StringUtils.upperCase(week);
        return "<HEAD>" + date + "(" + week + ") " + hc.toUpperCase() + "</HEAD>";
    }
}
