package cn.vetech.vedsb.jp.service.b2bsz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.b2bsz.JpB2bDlzhDao;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlzh;

/**
 * 
 * @author wangshengliang
 *
 */
@Service
public class JpB2bDlzhServiceImpl extends MBaseService<JpB2bDlzh, JpB2bDlzhDao> {

	/**
	 * 获得b2b等录账号分页对象
	 * 
	 * @param dlzh
	 * @return
	 */
	public Page getjpdlzhPage(JpB2bDlzh dlzh) {
		List<JpB2bDlzh> dlzhList = this.getMyBatisDao().query(dlzh);
		int dlzhCount = this.getMyBatisDao().selectCount(dlzh);
		Page page = new Page(dlzh.getPageNum(), dlzh.getPageSize());
		if (dlzhList == null) {
			dlzhList = new ArrayList<JpB2bDlzh>();
		}
		if (dlzhList.isEmpty()) {
			JpB2bDlzh dlzhbean = new JpB2bDlzh();
			dlzhList.add(dlzhbean);
		}
		page.setList(dlzhList);
		page.setTotalCount(dlzhCount);
		return page;
	}

	public void delDlzh(String id, String bca, String shbh) {
		this.getMyBatisDao().delDlzh(id, bca, shbh);
	}

	public void updateByIdAndBca(JpB2bDlzh record) {
		this.getMyBatisDao().updateByIdAndBca(record);
	}

	public List<JpB2bDlzh> getDlzhList(String zh, String hkgs, String bca, String shbh, String yhbh) {
		return this.getMyBatisDao().getDlzhListByZh(zh, hkgs, bca, shbh, yhbh);
	}

	public List<JpB2bDlzh> getDlzhListByBean(JpB2bDlzh record) {
		return this.getMyBatisDao().getDlzhListByBean(record);
	}

	/**
	 * 出票的时候自动保存
	 * @param record
	 */
	public void saveDlzh(JpB2bDlzh record) {
		try {
			record.setCjtime(VeDate.getStringDate());
			List<JpB2bDlzh> list = this.getMyBatisDao().getDlzhListByZh(record.getZh(), record.getHkgs(), record.getBca(), record.getShbh(), record.getYhbh());
			if (list != null && list.size() > 0) {// 修改
				record.setId(list.get(0).getId());
				updateByIdAndBca(record);
			} else {// 新增
				record.setSfsy("0");
				insert(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取航司登录账号信息
	 * 
	 * @param shbh
	 * @param hkgs
	 * @return
	 */
	public List<JpB2bDlzh> getJpB2bDlzh(String shbh,String yhbh, String hkgs) {
		JpB2bDlzh dlzhBean = new JpB2bDlzh();
		dlzhBean.setShbh(shbh);
		dlzhBean.setYhbh(yhbh);
		dlzhBean.setBca("720102");
		dlzhBean.setHkgs(hkgs);
		// 按创建时间倒叙
		dlzhBean.setOrderBy("cjtime desc");
		List<JpB2bDlzh> dlzhList = getMyBatisDao().select(dlzhBean);
		return dlzhList;
	}
}
