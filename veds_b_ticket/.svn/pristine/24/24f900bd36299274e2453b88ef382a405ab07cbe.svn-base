package cn.vetech.vedsb.jp.service.jpddsz;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.BookTicketChange;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
@Service
public class JpGqdHandleServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(JpGqdHandleServiceImpl.class);
	@Autowired
	private JpGqdServiceImpl jgsImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	/**
	 * 改签单入库
	 */
	public void toDbGq(TicketChangeBean tcBean,JpDdsz one,JpPtLog ptconlog){
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYGQD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.GQ.getValue());
			ptlb.setPtzcbs(one.getWdpt());
			ptlb.setShbh(one.getShbh());
			ptlb.setYhbh(one.getDdUserid());
			ptlb.setCzsm(one.getDdJkzh()+"供应改签单入库");
			String ddgngj = one.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.add2(ptconlog.getInfo());
			toDbBefore(tcBean, one, ptlb);
			//执行入库
			BookTicketChange bookTicketChange = new BookTicketChange(tcBean);
			procedureServiceImpl.insertJpGqd(bookTicketChange);
			ptlb.add("改签单入库参数："+bookTicketChange.getP_xml());
			if (0 != bookTicketChange.getResult()) {
				String errmsg = StringUtils.trimToEmpty(bookTicketChange.getErrmsg());
				ptlb.add("改签单入库失败，数据库返回："+bookTicketChange.getErrmsg());
				logger.error("改签单入库失败，数据库返回："+bookTicketChange.getErrmsg());
				if(errmsg.indexOf("不存在对应订单")>-1){
					bl=false;
				}
				return;
			}
			String gqdh = bookTicketChange.getGqdh();
			ptlb.add("改签单入库成功，改签单号["+gqdh+"]");
			logger.error("改签单入库成功，改签单号["+gqdh+"]");
			JpGqd jpgqd = jgsImpl.getJpGqdByGqdh(gqdh, one.getShbh());
			ptlb.setDdbh(gqdh);
			ptlb.setTfid(VeStr.getValue(tcBean.getGqdMap(), "WBDH"));
			ptlb.setWdid(one.getWdid());
			ptlb.setWdmc(one.getWdmc());
			if(jpgqd!=null){
				ptlb.setPnrNo(jpgqd.getXsPnrNo());
			}
			//入库后业务
			toDbAfter(tcBean,one,ptlb);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("改签单入库失败，失败原因："+e.getMessage());
		} finally{
			if(bl){
				try {
					jpPtLogServiceImpl.insertLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
	}
	/**
	 * 改签单入库前执行业务
	 * @return
	 */
	private void toDbBefore(TicketChangeBean tcBean, JpDdsz one,JpPtLog ptlb){
		
	}
	/**
	 * 改签单入库后执行业务
	 * @return
	 */
	private void toDbAfter(TicketChangeBean tcBean,JpDdsz one,JpPtLog ptlb){
		
	}
}
