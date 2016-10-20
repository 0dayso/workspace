package cn.vetech.vedsb.jp.service.cgdzbb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.cgdzbb.JpCgdzDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdz;

@Service
public class JpCgdzServiceImpl extends MBaseService<JpCgdz, JpCgdzDao> {
	@Autowired
	private JpCgdzDao jpCgdzDao;

	/**
	 * 查询采购对账数据
	 * 
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> genCgdzData(Map<String, Object> param) {
		return jpCgdzDao.genCgdzData(param);
	}

	/**
	 * 创建一条对账记录
	 */
	public int createCgdz(Shyhb shyhb, JpCgdz jpCgdz) {
		// 查询是否重复
		JpCgdz record = new JpCgdz();
		record.setShbh(shyhb.getShbh());
		record.setDzrq(jpCgdz.getDzrq());
		int count = this.getMyBatisDao().selectCount(record);
		if (count == 0) {
			jpCgdz.setId(VeDate.getNo(6));
			jpCgdz.setShZt("0");
			jpCgdz.setDzZt("0");
			jpCgdz.setShbh(shyhb.getShbh());
			jpCgdz.setDzUserid(shyhb.getBh());
			jpCgdz.setDzDeptid(shyhb.getShbmid());
			jpCgdz.setDzDatetime(new Date());
			this.getMyBatisDao().insert(jpCgdz);
			return 1;
		}
		return 0;
	}

	/**
	 * 获取一条采购对账主表
	 */
	public JpCgdz getCgdz(Shyhb shyhb, String id) {
		JpCgdz jpCgdz = new JpCgdz();
		jpCgdz.setShbh(shyhb.getShbh());
		jpCgdz.setId(id);
		try {
			List<JpCgdz> list = queryList(jpCgdz);
			if (CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * 查询采购对账数据
	 * 
	 * shbh 商户编号
	 * 
	 * zctp 1正常票 2正常退废票 3退废票
	 * 
	 * cglys 数组 BSPET BSP
	 * 
	 * ksrq 日期段
	 * 
	 * jsrq 日期段
	 * 
	 * rqtj 1 退废票的提交日期 2退废票的回款日期
	 * 
	 * @return
	 */
	public List<Map<String, Object>> genHxdzData(String shbh, String zctp, String[] cglys, String ksrq, String jsrq, String rqtj) {
		if (cglys == null || cglys.length < 1) {
			// cglys = new String[] { "BSPET", "BOP" };
		}
		if (StringUtils.isBlank(rqtj)) {
			rqtj = "1";
		}
		if (StringUtils.isBlank(ksrq)) {
			ksrq = VeDate.getPreDay(VeDate.getStringDateShort(), -1);
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("shbh", shbh);
		param.put("cglys", cglys);
		param.put("kssj", VeStr.KsrqString(ksrq));
		if (StringUtil.isBlank(jsrq)) {
			jsrq = ksrq;
		}
		param.put("jssj", VeStr.JsrqString(jsrq));
		param.put("rqtj", rqtj);

		List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> zcplist = null;
		List<Map<String, Object>> tfplist = null;

		if ("1".equals(zctp) || "2".equals(zctp)) {
			zcplist = jpCgdzDao.genZcpData(param);
		}
		if ("2".equals(zctp) || "3".equals(zctp)) {
			tfplist = jpCgdzDao.genTfpData(param);
		}

		if (zcplist != null && zcplist.size() > 0) {
			all.addAll(zcplist);
		}
		if (tfplist != null && tfplist.size() > 0) {
			all.addAll(tfplist);
		}
		return all;
	}
}
