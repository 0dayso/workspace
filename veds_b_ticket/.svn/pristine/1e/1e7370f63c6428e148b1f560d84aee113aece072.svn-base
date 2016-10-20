package cn.vetech.vedsb.jp.service.b2bsz;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.b2bsz.JpB2bHkgsDao;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;

@Service
public class JpB2bHkgsServiceImpl extends MBaseService<JpB2bHkgs, JpB2bHkgsDao>{
	
	public JpB2bHkgs getB2bHkgsByBca(String shbh, String hkgs, String bca) {
		return this.getMyBatisDao().selectByHkgs_Bca(shbh, hkgs, bca);
	}
	
	public void updateByhkgs(JpB2bHkgs record) {
		this.getMyBatisDao().updateByhkgs(record);
	}
}
