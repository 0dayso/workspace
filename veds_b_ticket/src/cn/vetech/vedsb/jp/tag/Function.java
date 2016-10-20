package cn.vetech.vedsb.jp.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.service.SpringContextUtil;

import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;

/**
 * VFN标签
 */
public class Function {

	/**
	 * 通过商户编号获取jp_office集合
	 * @return
	 */
	public static List<String>  officeIdList(String shbh){
		 JpOfficeServiceImpl jpOfficeServiceImpl = SpringContextUtil.getBean("jpOfficeServiceImpl",JpOfficeServiceImpl.class);
		 List<String> jpofficelist = jpOfficeServiceImpl.getMyBatisDao().selectOfficeIdByShbh(shbh);
		 return jpofficelist;
	}
	
	
	/**
	 * 
	 * 通过商户编号和国内国际获取网店集合
	 * @param shbh
	 * @param gngj 0国际  1国内
	 * @return
	 */
	public static List<Wdzlsz> wdList(String shbh, String gngj) {
		
		WdzlszServiceImpl wdzlszServiceImpl = SpringContextUtil.getBean("wdzlszServiceImpl", WdzlszServiceImpl.class);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("shbh", shbh);
		
		if("0".equals(gngj)){
			gngj="1001902";//国际
		}else{
			gngj="1001901";//国内
		}
		
		if (StringUtils.isNotBlank(gngj)) {
			param.put("ywlxs", new String[] { gngj });// 1001902国际 1001901 国内
		} else {
			param.put("ywlxs", new String[] { "1001902", "1001901" });// 传入的国内国际标识为空，则默认查询国内和国际
		}

		List<Wdzlsz> list = wdzlszServiceImpl.getWdZlszListByYwlx(param);
		return list;
	}

}
