package cn.vetech.vedsb.common.service.base;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.WdXsfaDao;
import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.WdXsfa;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class WdXsfaServiceImpl extends MBaseService<WdXsfa, WdXsfaDao> {
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;

	public Page queryPage(WdXsfa wdxsfa, int pageNum, int pageSize) {
		Page page = new Page(pageNum, pageSize);
		List<?> list = this.getMyBatisDao().getWdXsfaList(wdxsfa);
		int totalCount = this.getMyBatisDao().getTotalCount(wdxsfa);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}

	/**
	 * 修改方案状态
	 * 
	 * @param uptBean
	 */
	public void uptZt(WdXsfa uptBean) {
		this.getMyBatisDao().uptZt(uptBean);
	}

	public WdXsfa getWdXsfaByBean(WdXsfa xsfa) {
		return this.getMyBatisDao().getWdXsfaByBean(xsfa);
	}

	public List<WdXsfa> getWdXsfaList2(WdXsfa temp) {
		return this.getMyBatisDao().getWdXsfaList2(temp);
	}

	public void updateFamm(String faid, String newpswd, String xgyh, Date xgsj) {
		this.getMyBatisDao().updateFamm(faid, newpswd, xgyh, xgsj);
	}

	public void batchUptZt(String xsfaids, String zt) {
		Shyhb user = SessionUtils.getShshbSession();
		String[] arr = xsfaids.split(",");
		WdXsfa uptBean = new WdXsfa();
		uptBean.setZt(zt);
		uptBean.setXgsj(new Date());
		uptBean.setXgyh(user.getBh());
		uptBean.setCzpt("B");
		for (String s : arr) {
			uptBean.setId(s);
			this.getMyBatisDao().updateByPrimaryKeySelective(uptBean);
		}
	}

	/**
	 * 根据多条件批量修改方案状态
	 * @param wdxsfa
	 */
	public void batchAllUptZt(WdXsfa wdxsfa) {
		this.getMyBatisDao().batchAllUptZt(wdxsfa);
	}

	/**
	 * 获取参数表数据
	 * @param string
	 * @return
	 */
	public Shcsb getShcsb(Shcsb searchCsb) {
		return shcsbServiceImpl.getMyBatisDao().findCs(searchCsb.getBh(), searchCsb.getShbh());
	}
}
