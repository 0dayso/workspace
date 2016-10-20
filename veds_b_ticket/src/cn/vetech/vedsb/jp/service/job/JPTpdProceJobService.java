package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_tpd;
/**
 * 网店退票单导单
 * @author zhanglei
 *
 */
@Service
public class JPTpdProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JPTpdProceJobService.class);
	
	@Autowired
	private JpddWork_tpd jpddWork_tpd;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;//注册票号回填
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		int rtn = jpddWork_tpd.queryorder_tf(wdid);
		if(rtn==-1){//注销供应退票单导单job的同时要注销
			try {
				qrtzServiceImpl.del("10006", wdid);//注册自动提交淘宝客户退票job
				qrtzServiceImpl.del("10007", wdid);//注册同步淘宝退票状态job
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.error(jobBean.getGroup());
		return rtn;
	}

}
