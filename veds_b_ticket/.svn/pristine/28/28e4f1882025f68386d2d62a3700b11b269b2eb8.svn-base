package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpddWork;
/**
 * 网店正常单导单
 * @author zhanglei
 *
 */
@Service
public class JPddProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JPddProceJobService.class);
	
	@Autowired
	private JpddWork jpddWork;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;//注册票号回填
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		int rtn = jpddWork.queryorder(wdid);
		if(rtn==-1){//注销供应导单job的同时要注销票号回填
			try {
				qrtzServiceImpl.del("10004", wdid);
			} catch (Exception e) {
				e.printStackTrace();
			}//注册票号回填
		}
		logger.error(jobBean.getGroup());
		return rtn;
	}

}
