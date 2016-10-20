package cn.vetech.vedsb.jp.service.procedures;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.ParseXml;

import cn.vetech.vedsb.jp.dao.procedures.PkgQinfoDao;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 接收pid传入的清Q信息
 * 
 * @author houya
 *
 */
@Service
public class PkgQinfoServiceImpl extends ParamMap {
	@Autowired
	private PkgQinfoDao pkgQinfoDao;

	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;

	public void proc(String catalog, String xml) {
		long t = System.currentTimeMillis();
		ParseXml px = null;
		try {
			px = new ParseXml(xml);
			Element root = px.getRoot();
			String officeid = root.elementText("OFFICEID");
			String shbh = jpOfficeServiceImpl.selectShbhByOffice(officeid);
			LogUtil.getPidinfo(catalog).info("从系统中通过office:=" + officeid + "获取的商户号:=" + shbh);
			if (StringUtils.isBlank(shbh)) {
				shbh = "HZKFZX";
			}

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("p_xml", xml);
			super.xmlToJson(param);
			param.put("p_shbh", "HZKFZX");

			LogUtil.getPidinfo(catalog).info("调用过程入参" + param);

			pkgQinfoDao.f_qinfo(param);
			Object error = param.get("p_error");
			if (error != null && StringUtils.isNotBlank(error.toString())) {
				LogUtil.getPidinfo(catalog).info("调用过程返回的信息，" + error);
			}
		} catch (Exception e) {
			LogUtil.getPidinfo(catalog).error("调用过程失败", e);
		} finally {
			if (px != null) {
				px.close();
			}
			LogUtil.getPidinfo(catalog).error("调用过程完成，耗时" + (System.currentTimeMillis() - t));
		}
	}
}
