package cn.vetech.vedsb.jp.service.b2bsz;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.b2bsz.JpB2bZffsDao;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZffs;


@Service
public class JpB2bZffsServiceImpl extends MBaseService<JpB2bZffs, JpB2bZffsDao>{
	public void updateZffsById(JpB2bZffs record) {
		this.getMyBatisDao().updateById(record);
	}
}