package cn.vetech.vedsb.jp.service.jpzwgl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqJcszDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqJcsz;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
@Service
public class JpTjsqJcszServiceImpl extends MBaseService<JpTjsqJcsz, JpTjsqJcszDao>{
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	/**
	 * 保存追位基础数据
	 * @param jcszBeanList
	 * @throws Exception
	 */
	@Transactional
	public void saveTjsqJcsz(List<JpTjsqJcsz> jcszBeanList) throws Exception {
		for (JpTjsqJcsz jcszBean : jcszBeanList) {
			if("SFZW".equals(jcszBean.getZdywm()) && "1".equals(jcszBean.getZdz())){
				//触发追位job
				qrtzServiceImpl.add("20001", jcszBean.getShbh());
			}else {
				qrtzServiceImpl.del("20001", jcszBean.getShbh());
			}
			if (StringUtils.isBlank(jcszBean.getId())) {
				this.insert(jcszBean);
			} else {
				this.getMyBatisDao().updateByPrimaryKeySelective(jcszBean);
			}
		}
	}
}
