package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Threads;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpzdcpWork;
import cn.vetech.vedsb.utils.Constants;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 机票自动出票Job
 */
@Service
public class JpddWork_zdcp {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JpDdszServiceImpl jpDdszServiceImpl;
	@Autowired
	private JpZdcpJkServiceImpl jpZdcpJkServiceImpl;
	@Autowired
	private JpzdcpWork jpzdcpWork;

	public int queryorder(String wdid) {
		long t0 = System.currentTimeMillis();
		// 获取网店导单设置
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		jpDdsz = jpDdszServiceImpl.getEntityById(jpDdsz);
		if (jpDdsz == null) {
			LogUtil.getAutoCp().error("没有找到网店【" + wdid + "】对应的导单设置");
			return -1;
		}

		if (!"1".equals(jpDdsz.getDdautocp())) {
			LogUtil.getAutoCp().error("网店【" + wdid + "】没有开启订单自动出票");
			return -1;
		}
		// 出票线程情况
		LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况" + Threads.getQueueInfo(Constants.JP_ZDCP_EXECUTOR));
		ThreadPoolExecutor exec_ = (ThreadPoolExecutor) Constants.JP_ZDCP_EXECUTOR;
		// 每个tomcat并发出票数量,这里以后可以传入变量
		int pertomcpcount = 25;

		int poolSize = exec_.getCorePoolSize();
		// 排队数
		int queue = exec_.getQueue().size();
		// 不能超过线程最大个数
		if (pertomcpcount > poolSize && poolSize > 0) {
			pertomcpcount = poolSize;
		}
		int activeCount = exec_.getActiveCount();
		int rowcount = pertomcpcount - activeCount;

		LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况,正在执行"+activeCount+",排队数" + queue + "最大并发数" + pertomcpcount + ",当前还可以执行的线程数" + rowcount);
		if (rowcount <= 0 || queue > 0) {
			LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况,已经达到最大并发出票数" + pertomcpcount + "或排队数" + queue + "大于0，不添加任务");
			return 0;
		}
		String shbh = jpDdsz.getShbh();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("shbh", jpDdsz.getShbh());
		param.put("gngj", "1".equals(jpDdsz.getDdGngj()) ? "0" : "1");// jpddsz表里面的0是国内1是国际
		param.put("wdid", jpDdsz.getWdid());
		param.put("count", rowcount);

		long t1 = System.currentTimeMillis();
		List<Map<String, Object>> ddList = jpZdcpJkServiceImpl.getZdcpDdByWdid(param);
		int len = ddList == null ? 0 : ddList.size();
		LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况,获得的待出票订单个数[" + len + "]耗时:" + (System.currentTimeMillis() - t1));

		if (len < 1) {
			LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况,没有需要出票的订单");
			return 0;
		}
		for (int i = 0; i < len; i++) {
			String ddbh = VeStr.getValue(ddList.get(i), "DDBH");
			Constants.JP_ZDCP_EXECUTOR.execute(new Command(shbh, ddbh));
		}
		LogUtil.getAutoCp().error("网店" + wdid + "线程执行情况本轮出票JOB运行完成，添加前队列中正在出票数" + activeCount + ",添加后数" + exec_.getActiveCount() + ",耗时" + (System.currentTimeMillis() - t0));
		return 0;
	}

	class Command implements Runnable {
		String shbh;
		String ddbh;

		public Command(String shbh, String ddbh) {
			this.shbh = shbh;
			this.ddbh = ddbh;
		}

		@Override
		public void run() {
			try {
				jpzdcpWork.start(ddbh, shbh);
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.getAutoCp().error("自动出票过程中出现异常" + ddbh + "," + shbh + ":" + e.getMessage());
			}
		}
	}

}
