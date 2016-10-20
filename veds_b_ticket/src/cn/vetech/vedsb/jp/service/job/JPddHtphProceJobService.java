package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_jp;
/**
 * 网店正常单票号回填
 * @author zhanglei
 *
 */
@Service
public class JPddHtphProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JPddHtphProceJobService.class);
	
	@Autowired
	private JpddWork_jp jpddWork_jp;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;//注册票号回填
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		int rtn = jpddWork_jp.ptjp_auto(wdid);
		if(rtn==-1){//注销票号回填的同时要注销正常单入库
			try {
				qrtzServiceImpl.del("10001", wdid);
			} catch (Exception e) {
				e.printStackTrace();
			}//注册票号回填
		}
		logger.error(jobBean.getGroup());
		return rtn;
	}

}
