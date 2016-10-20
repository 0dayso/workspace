package cn.vetech.vedsb.jp.service.qxxgl;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.qxxgl.BQinfoDao;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfo;
@Service
public class BQinfoServiceImpl extends MBaseService<BQinfo, BQinfoDao> {
	public boolean isBqinfo(BQinfo  t){
		return this.getMyBatisDao().isBqinfo(t) > 0 ;
	}
}
