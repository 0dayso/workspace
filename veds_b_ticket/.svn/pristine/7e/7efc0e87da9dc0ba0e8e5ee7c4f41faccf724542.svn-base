package cn.vetech.vedsb.jp.service.b2bsz;


import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.b2bsz.JpB2bHkgsxxDao;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;

/**
 * 
 * @author wangshengliang
 *
 */
@Service
public class JpB2bHkgsxxServiceImpl extends MBaseService<JpB2bHkgsxx, JpB2bHkgsxxDao>{
	
	public  JpB2bHkgsxx selectById(String shbh){
		return this.getMyBatisDao().selectById(shbh);
	}
	
	public  JpB2bHkgsxx selectByHkgs_Bca(String shbh,String hkgs,String bca){
		return this.getMyBatisDao().selectByHkgs_Bca(hkgs, bca);
	}
	
	public List<JpB2bHkgsxx>  selectByBca(String bca){
		
		return this.getMyBatisDao().selectByBca(bca);
	}
}
