package cn.vetech.vedsb.jp.service.office;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.office.JpOfficeDao;
import cn.vetech.vedsb.jp.entity.office.JpOffice;

/**
 * @author wangshengliang
 */
@Service
public class JpOfficeServiceImpl extends MBaseService<JpOffice, JpOfficeDao> {
	public JpOffice getJpOffice(JpOffice t) {
		return this.getMyBatisDao().getJpOffice(t);
	}

	public List<JpOffice> selectJpOfficeByShbh(String shbh) {
		return this.getMyBatisDao().selectJpOfficeByShbh(shbh);
	}

	/**
	 * 通过officeid获取对应的商户编号
	 * 
	 * @param officeid
	 * @return
	 */
	public String selectShbhByOffice(String officeid) {
		if (StringUtils.isBlank(officeid)) {
			return null;
		}
		officeid = StringUtils.trimToEmpty(officeid).toUpperCase();
		List<JpOffice> list = this.getMyBatisDao().selectJpOfficeByOffice(officeid);
		if (list != null && list.size() > 0) {
			JpOffice one = list.get(0);
			if (one != null) {
				return one.getShbh();
			}
		}
		return null;
	}
}
