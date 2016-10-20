package cn.vetech.vedsb.jp.service.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.platpolicy.taobao.TaoBaoGyAutoCpService;
/**
 * 网店正常单导单
 * @author zhanglei
 *
 */
@Service
public class TaobaoGyOrderDetailSmJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(TaobaoGyOrderDetailSmJobService.class);
	
	@Autowired
	private TaoBaoGyAutoCpService taoBaoGyAutoCpService;
	@Override
	public int dataProce(JobBean jobBean) {
		String ddbh_shbh = jobBean.getData();
		try {
			logger.error(jobBean.getGroup());
			Date startdate = jobBean.getStartdate();
			if(startdate!=null){//当当前时间超过startdate1天时不再执行扫描
				long l = VeDate.getTwoDay(VeDate.getNow(), startdate);
				if(l>=1){
					return -1;
				}
			}
			return taoBaoGyAutoCpService.TBOrderDetailByDddh(ddbh_shbh);
		} catch (Exception e) {
			logger.error("执行淘宝出票扫描业务报错【"+ddbh_shbh+"】",e);
			return -1;
		}
	}
	public static void main(String[] args) throws ParseException {
		String date1 = "2016-08-17 13:49:00";
		String date2 = "2016-08-16 13:48:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long l = VeDate.getTwoDay(sdf.parse(date1), sdf.parse(date2));
		System.out.println(l);
	}
}
