package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpTpdSyncSerivceImpl;

@Service
public class JpTpdSyncProceJobService implements ProceClusterableJob{
	private static Logger logger = LoggerFactory.getLogger(JPTpdProceJobService.class);
	@Autowired
	private JpTpdSyncSerivceImpl jpTpdSyncSerivceImpl;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;//注册票号回填
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		int rtn =jpTpdSyncSerivceImpl.execute(wdid);
		if(rtn==-1){//注销同步淘宝退票状态job的同时要注销
			try {
				qrtzServiceImpl.del("10002", wdid);//注销供应退票单导单job
				qrtzServiceImpl.del("10006", wdid);//注销自动提交淘宝客户退票job
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.error(jobBean.getGroup());
		return rtn;
	}
}
