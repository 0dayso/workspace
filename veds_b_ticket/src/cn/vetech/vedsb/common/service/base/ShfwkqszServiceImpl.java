package cn.vetech.vedsb.common.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.ShfwkqszDao;
import cn.vetech.vedsb.common.entity.base.Shfwkqsz;


/**
 * 商户开启表Service
 * @author lishanliang
 *
 */
@Service
public class ShfwkqszServiceImpl extends MBaseService<Shfwkqsz,ShfwkqszDao>{
	/**
	 * 根据商户编号查询出本商户所有的服务设置
	 * @param shbh
	 * @return
	 */
	public List<Shfwkqsz> getShfwkqszByShbhAndLx(String shbh,String lx){
		return this.getMyBatisDao().getShfwkqszByShbhAndLx(shbh,lx);
	}

	public Shfwkqsz getShfwkqszByShbhLxFwlxid(String shbh, String lx, String fw_lxid) {
		return this.getMyBatisDao().getShfwkqszByShbhLxFwlxid(shbh,lx,fw_lxid);
	}

	public Shfwkqsz getShzdbjszByShbhLxFwlxid(String shbh, String lx,String fw_lxid) {
		return this.getMyBatisDao().getShfwkqszByShbhLxFwlxid(shbh,lx,fw_lxid);
	}
}
