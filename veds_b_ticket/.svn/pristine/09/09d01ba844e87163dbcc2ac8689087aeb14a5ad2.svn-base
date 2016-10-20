package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Exceptions;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.base.Shfwkqsz;
import cn.vetech.vedsb.common.service.base.ShfwkqszServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 接口导单入库
 */
@Service
public class JpddWork {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	@Autowired
	private JpKhddHandleServiceImpl jkhsImpl;
	@Autowired
	private JpKhddKzServiceImpl jkksImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ShfwkqszServiceImpl shfwkqszServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddImpl;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddCheckedSerivce jpKhddCheckedSerivce;
	/**
	 * 判断订单重复用Map
	 * 
	 */
	private static Map<String, Map<String, String>> validateMap = new HashMap<String, Map<String, String>>();

	private static String nextDate;// 下次清空validateMap时间

	/**
	 * 开启扫描导单
	 */
	public int queryorder(String wdid) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			long t = System.currentTimeMillis();
			List<OrderBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if (jpDdsz == null || !"1".equals(jpDdsz.getDdKqzcdd())) {// 如果查不到导单设置，或者导单设置没有开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYDDLASTDAY.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应订单扫描");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			String ddgngj = jpDdsz.getDdGngj();// 订单国内国际 0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; // 默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			
			// 判断商户服务是否开启
			Shfwkqsz shfwkqsz = shfwkqszServiceImpl.getShfwkqszByShbhLxFwlxid(jpDdsz.getShbh(), "1", "7006101");
			if (shfwkqsz == null || "0".equals(shfwkqsz.getSfkq())) {
				ptlb.add("商户导单服务未开启，自动关闭供应导单开关，请开启商务服务后重新打开供应导单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqzcdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpddGySuper jpddGy = JpddGySuper.getJpddGySuper(jpDdsz);
			if (jpddGy == null) {
				ptlb.add("没有实现该网店供应导单功能。");
				return -1;
			}
			list = jpddGy.queryOrders(ptlb);
			if (CollectionUtils.isEmpty(list)) {
				return 0;
			}
			long t1 = System.currentTimeMillis();
			ptlb.add("调用淘宝接口耗时" + (t1 - t) + "，单数 " + list.size());
			
			Iterator<OrderBean> iterator = list.iterator();
			Map<String,List<OrderBean>> listMap = new HashMap<String,List<OrderBean>>();
			while (iterator.hasNext()) {//按是否派单业务拆分集合
				OrderBean o = iterator.next();
				String wbdh = o.getKhddMap().get("WBDH");
				List<OrderBean> tmpList = listMap.get(wbdh);
				if(tmpList == null){
					tmpList = new ArrayList<OrderBean>();
					listMap.put(wbdh, tmpList);
				}
				tmpList.add(o);
			}
			ExecutorService JP_RK_EXECUTOR = Executors.newFixedThreadPool(5);
			try {
				// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
				Iterator<String> iteratorKey = listMap.keySet().iterator();
				while (iteratorKey.hasNext()) {
					String wbdh = iteratorKey.next();
					List<OrderBean> oblist= listMap.get(wbdh);
					JP_RK_EXECUTOR.execute(new Command(wbdh, oblist, jpDdsz, ptlb));
				}
			} finally {
				JP_RK_EXECUTOR.shutdown();
			}
			try {
				boolean rtn = JP_RK_EXECUTOR.awaitTermination(20L, TimeUnit.MINUTES);
				ptlb.add("导单入库阻断返回" + rtn);
			} catch (InterruptedException e) {
				ptlb.add("导单入库超时" + e.getMessage());
			}
			ptlb.add("导单入库完成");
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add2("导单出错," + Exceptions.getMessageAsString(e));
		} finally {
			if (bl) {
				try {
					jpPtLogServiceImpl.saveLastdayLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return 0;
	}
	/**
	 * 手动扫描导单
	 */
	public int queryorder(String wdid,String dateStr) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			long t = System.currentTimeMillis();
			List<OrderBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if (jpDdsz == null || !"1".equals(jpDdsz.getDdKqzcdd())) {// 如果查不到导单设置，或者导单设置没有开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYDDLASTDAY.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "手工供应订单入库【扫描订单列表】");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			String ddgngj = jpDdsz.getDdGngj();// 订单国内国际 0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; // 默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			
			// 判断商户服务是否开启
			Shfwkqsz shfwkqsz = shfwkqszServiceImpl.getShfwkqszByShbhLxFwlxid(jpDdsz.getShbh(), "1", "7006101");
			if (shfwkqsz == null || "0".equals(shfwkqsz.getSfkq())) {
				ptlb.add("商户导单服务未开启，自动关闭供应导单开关，请开启商务服务后重新打开供应导单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqzcdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpddGySuper jpddGy = JpddGySuper.getJpddGySuper(jpDdsz);
			if (jpddGy == null) {
				ptlb.add("没有实现该网店供应导单功能。");
				return -1;
			}
			list = jpddGy.queryOrders(ptlb,dateStr);
			if (list == null) {
				return 0;
			}
			long t1 = System.currentTimeMillis();
			ptlb.add("调用淘宝接口耗时" + (t1 - t) + "，单数 " + list.size());
			Iterator<OrderBean> iterator = list.iterator();
			Map<String,List<OrderBean>> listMap = new HashMap<String,List<OrderBean>>();
			while (iterator.hasNext()) {//按是否派单业务拆分集合
				OrderBean o = iterator.next();
				String wbdh = o.getKhddMap().get("WBDH");
				List<OrderBean> tmpList = listMap.get(wbdh);
				if(tmpList == null){
					tmpList = new ArrayList<OrderBean>();
					listMap.put(wbdh, tmpList);
				}
				tmpList.add(o);
			}
			ExecutorService JP_RK_EXECUTOR = Executors.newFixedThreadPool(5);
			try {
				// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
				Iterator<String> iteratorKey = listMap.keySet().iterator();
				while (iteratorKey.hasNext()) {
					String wbdh = iteratorKey.next();
					List<OrderBean> oblist= listMap.get(wbdh);
					JP_RK_EXECUTOR.execute(new Command(wbdh, oblist, jpDdsz, ptlb));
				}
			} finally {
				JP_RK_EXECUTOR.shutdown();
			}
			try {
				boolean rtn = JP_RK_EXECUTOR.awaitTermination(20L, TimeUnit.MINUTES);
				ptlb.add("导单入库阻断返回" + rtn);
			} catch (InterruptedException e) {
				ptlb.add("导单入库超时" + e.getMessage());
			}
			ptlb.add("导单入库完成");
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add2("导单出错," + Exceptions.getMessageAsString(e));
		} finally {
			if (bl) {
				try {
					jpPtLogServiceImpl.saveLastdayLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return 0;
	}
	class Command implements Runnable {
		String wbdh;
		List<OrderBean> list;
		JpDdsz jpDdsz;
		JpPtLog ptlb;

		public Command(String wbdh,List<OrderBean> list, JpDdsz one, JpPtLog ptlb) {
			this.wbdh = wbdh;
			this.list = list;
			this.jpDdsz = one;
			this.ptlb = ptlb;
		}

		@Override
		public void run() {
			// 订单入库
			List<OrderBean> newlist = jpKhddCheckedSerivce.execute(wbdh, list, jpDdsz, ptlb);
			if(CollectionUtils.isEmpty(newlist)){
				return;
			}
			for(int i=0;i<newlist.size();i++){
				jkhsImpl.toDb(newlist.get(i), jpDdsz, ptlb.getInfo());
			}
			
		}
	}
	public void register_tbcp_job_byDateStr(String wdid,String dateStr){
		if(dateStr.length()>10){
			dateStr = StringUtils.substring(dateStr, 0, 10);
		}
		logger.error("手动注册淘宝派单扫描JOB");
		long t=System.currentTimeMillis();
		String kssj = dateStr;
		String jssj = VeDate.getPreDay(dateStr, 1);
		try {
			List<JpKhdd> list = jpKhddImpl.getKhddBySfwbcpz(kssj,jssj, wdid);
			if(CollectionUtils.isNotEmpty(list)){
				logger.error("手动注册淘宝派单扫描JOB,个数"+list.size());
				for(int i=0;i<list.size();i++){
					JpKhdd jpKhdd = list.get(i);
					register_tbcp_job_byDdbh(jpKhdd.getDdbh(), jpKhdd.getShbh());
					if(i!=0&&i%10==0){//每注册20个休息10秒
						try {
							Thread.sleep(10000);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("手动注册淘宝派单扫描JOB完成,耗时"+(System.currentTimeMillis() -t));
	}
	public void register_tbcp_job_byDdbh(String ddbh,String shbh){
		if(StringUtils.isBlank(ddbh)||StringUtils.isBlank(shbh)){
			return;
		}
		String data = ddbh+"_"+shbh;
		try {
			qrtzServiceImpl.add("10005", data);
			LogUtil.getGyrz("TB",ddbh).error("手工注册淘宝派单扫描job成功");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.getGyrz("TB",ddbh).error("手工注册淘宝派单扫描job失败");
		}
	}
	/**
	 * 手工导入正常单
	 */
	public String getByWbdh(String wbdh, String wdid) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			if (StringUtils.isBlank(wdid) && StringUtils.isBlank(wbdh)) {
				return "网店标识、外部订单编号不能为空";
			}
			List<OrderBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if (jpDdsz == null) {// 如果查不到导单设置，或者导单设置没有开启
				bl = false;
				return "没有找到对应的网店导单设置";
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYSGDD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "手工供应订单导入【按外部单号】");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setWdid(wdid);
			String ddgngj = jpDdsz.getDdGngj();// 订单国内国际 0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; // 默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			JpddGySuper jpddGy = JpddGySuper.getJpddGySuper(jpDdsz);
			if (jpddGy == null) {
				bl = false;
				return "没有对接该网店导单";
			}
			try {
				list = jpddGy.getByWbdh(wbdh, ptlb);
			} catch (Exception e) {
				ptlb.add(e.getMessage());
			}
			if (list == null || list.size() == 0) {
				return "没有查询到要导入的订单";
			}
			// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
			Iterator<OrderBean> iterator = list.iterator();
			Map<String,List<OrderBean>> listMap = new HashMap<String,List<OrderBean>>();
			while (iterator.hasNext()) {//按是否派单业务拆分集合
				OrderBean o = iterator.next();
				List<OrderBean> tmpList = listMap.get(wbdh);
				if(tmpList == null){
					tmpList = new ArrayList<OrderBean>();
					listMap.put(wbdh, tmpList);
				}
				tmpList.add(o);
			}
			// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
			// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
			Iterator<String> iteratorKey = listMap.keySet().iterator();
			while (iteratorKey.hasNext()) {
				String _wbdh = iteratorKey.next();
				List<OrderBean> oblist= listMap.get(_wbdh);
				// 订单入库
				List<OrderBean> newlist = jpKhddCheckedSerivce.execute(wbdh, oblist, jpDdsz, ptlb);
				if(CollectionUtils.isEmpty(newlist)){
					continue;
				}
				for(int i=0;i<newlist.size();i++){
					jkhsImpl.toDb(newlist.get(i), jpDdsz, ptlb.getInfo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bl) {
				try {
					jpPtLogServiceImpl.insertLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/**
	 * 修改网店订单扩展表
	 * 
	 * @return
	 */
	private void updateKzByCpdh(Map<String, String> oneMap) {
		if (oneMap == null) {
			return;
		}
		JpKhddKz kz = new JpKhddKz();
		kz.setCpdh(oneMap.get("CPDH"));
		kz.setShbh(oneMap.get("SHBH"));
		kz.setJjd(oneMap.get("JJD"));
		kz.setCpcs(NumberUtils.toInt(oneMap.get("CPCS"), 0));
		try {
			jkksImpl.updateKzByCpdh(kz);
		} catch (Exception e) {

		}
	}

	/**
	 * 验证订单是否重复
	 * 
	 * @return false表示重复入库要过滤;true表示不重复
	 */
	public boolean validate(OrderBean orderBean, String shbh, JpDdsz jpDdsz,JpPtLog ptlb ) {
		String wbdh = orderBean.getKhddMap().get("WBDH");
		String wbddzt = StringUtils.trimToEmpty(orderBean.getKhddMap().get("WBDDZT"));
		String ddxh = StringUtils.trimToEmpty(orderBean.getKhddMap().get("DDXH"));
		String cpdh = StringUtils.trimToEmpty(orderBean.getKhddMap().get("CPDH"));
		String wdid = jpDdsz.getWdid();
		String nowtime = VeDate.getStringDate();
		String wbddzt_ddxh = wbddzt+"_"+ddxh;
		Map<String, String> tmpMap = validateMap.get(wdid);// 一个网店平台一个Map,用于订单重复判断验证的。
		if (tmpMap == null) {
			tmpMap = new HashMap<String, String>();
			validateMap.put(wdid, tmpMap);
			nextDate = VeDate.getPreMin(nowtime, 10);
		} else {
			if (StringUtils.isBlank(nextDate) || VeDate.getTwoMin(nowtime, nextDate) >= 0) {
				// 清空validateMap并保存下次清空时间
				tmpMap.clear();
				nextDate = VeDate.getPreMin(nowtime, 10);
			}
		}
		String ddbh_wbddzt_ddxh = tmpMap.get(wbdh+"_"+cpdh+"_"+ddxh);
		if(StringUtils.isNotBlank(ddbh_wbddzt_ddxh)&&("_".equals(wbddzt_ddxh)||ddbh_wbddzt_ddxh.endsWith(wbddzt_ddxh))){//表示判断重复
			String xt_ddbh = ddbh_wbddzt_ddxh.split("_",-1)[0];
			String e = "过滤订单【"+wbdh+"】,重复订单导入，系统订单编号是【"+xt_ddbh+"】";
			ptlb.add(e);
			LogUtil.getGyrz("GLDD").error(e);
			return false;
		}else{//表示不过滤
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("wbdh", wbdh);
			param.put("shbh", shbh);
			param.put("ddxh", ddxh);
			param.put("cpdh", cpdh);
			try {
				List<JpKhdd> list = null;
				try {
					list = jpKhddImpl.selectJpByWbdh(param);
				} catch (Exception e) {
					e.printStackTrace();
					ptlb.add("执行订单重复判断验证SQL报错,本次视为重复入库:"+e.getMessage());
					return false;
				}
				
				if (list == null || list.isEmpty()) {
					ddbh_wbddzt_ddxh = "";
				} else if (list.size() == 1) {
					ddbh_wbddzt_ddxh = list.get(0).getDdbh() + "_" + list.get(0).getWbddzt() + "_" + ddxh;
				} else {
					for (int i = 0; i < list.size(); i++) {
						JpKhdd _jpKhdd = list.get(i);
						String xs_pnr_no = StringUtils.trimToEmpty(orderBean.getKhddMap().get("XS_PNR_NO"));
						if (StringUtils.isNotBlank(xs_pnr_no)&&xs_pnr_no.equals(_jpKhdd.getXsPnrNo())) {
							ddbh_wbddzt_ddxh = _jpKhdd.getDdbh() + "_" + _jpKhdd.getWbddzt() + "_" + ddxh;
							break;
						}else if(StringUtils.isBlank(xs_pnr_no)){
							String _cjrStr = StringUtils.trimToEmpty(orderBean.getCjrList().get(0).get("CJR")).toUpperCase();
							String _xshbhStr = StringUtils.trimToEmpty(orderBean.getHdList().get(0).get("XS_HBH")).toUpperCase();
							String cjrStr = StringUtils.trimToEmpty(_jpKhdd.getCjr()).toUpperCase();
							String xshbhStr = StringUtils.trimToEmpty(_jpKhdd.getXsHbh()).toUpperCase();
							if(cjrStr.indexOf(_cjrStr)>-1&&xshbhStr.indexOf(_xshbhStr)>-1){//当订单中乘机人信息以及航班信息中
								ddbh_wbddzt_ddxh = _jpKhdd.getDdbh() + "_" + _jpKhdd.getWbddzt() + "_" + ddxh;
								break;
							}
						}
					}
					if(StringUtils.isBlank(ddbh_wbddzt_ddxh)){//如果还找不到数据，这里记录日志，并且过滤调本次回填
						String e="过滤订单【" + wbdh + "】,外部单号在系统中存在，但是没有找到系统中对应的DDBH。";
						ptlb.add(e);
						LogUtil.getGyrz("GLDD").error(e);
						return false;
					}
				}
				tmpMap.put(wbdh+"_"+cpdh+"_"+ddxh, ddbh_wbddzt_ddxh);
			} catch (Exception e) {
				logger.error("执行订单重复判断验证SQL报错:", e);
			}
		}
		ptlb.add("外部单号 " + wbdh + "商户" + shbh + "判断重复，从数据库获取的订单和状态是" + ddbh_wbddzt_ddxh);
		if (StringUtils.isBlank(ddbh_wbddzt_ddxh)) {// 表示判断不重复
			return true;
		}
		String xt_ddbh = ddbh_wbddzt_ddxh.split("_", -1)[0];
		if (("_".equals(wbddzt_ddxh)||ddbh_wbddzt_ddxh.endsWith(wbddzt_ddxh))) {
			String e="过滤订单【" + wbdh + "】,重复订单导入，系统订单编号是【" + xt_ddbh + "】";
			ptlb.add(e);
			LogUtil.getGyrz("GLDD").error(e);
			return false;
		} else {// 判断不重复，但是不能执行自动出票
			orderBean.getKhddMap().put("DDBH", xt_ddbh);
			orderBean.getKhddMap().put("IFTBZT", "1");//表示这个是同步状态订单，不能执行
			return true;
		}
	}
	public List<OrderBean> pdlistMapHandle(Map<String,List<OrderBean>> pdlistMap,String shbh){
		List<OrderBean> list = new ArrayList<OrderBean>();
		if(pdlistMap==null||pdlistMap.size()==0){
			return list;
		}
		Iterator<String> iterator = pdlistMap.keySet().iterator();
		while(iterator.hasNext()){
			String wbdh = iterator.next();
			List<OrderBean> obList = pdlistMap.get(wbdh);
			//根据外部单号查数量
			List<JpKhdd> khddList = jpKhddImpl.getKhddByWbdh(shbh, wbdh);
			if(CollectionUtils.isEmpty(khddList)){//第一种情况，系统中不存在该派单订单
				list.addAll(obList);
				continue;
			}else if(obList.size()!=khddList.size()){//第二种情况，系统中订单数量等于再次入库订单数量
				//按外部单号进行拆单
				int rtn = jpKhddImpl.deleteAllByWbdh(wbdh, shbh);
				if(rtn == 1 ){//删除成功要继续入库
					list.addAll(obList);
				}
				continue;
			}else{//当订单数量相等时，开始比较乘机人、XS_PNR_NO信息
				boolean[] blArr = new boolean[khddList.size()];
				for(int i=0;i<khddList.size();i++){
					JpKhdd jpKhdd = khddList.get(i);
					String xsPnrNo = jpKhdd.getXsPnrNo();
					if(StringUtils.startsWith(xsPnrNo, "O")){
						xsPnrNo = "";
					}
					for(int j=0;j<obList.size();j++){
						OrderBean ob = obList.get(j);
						String _xsPnrNo = StringUtils.trimToEmpty(ob.getKhddMap().get("XS_PNR_NO"));
						if(_xsPnrNo.equals(xsPnrNo)){//如果PNR相同，则检查乘机人数
							List<JpKhddCjr> jpKhddCjrList= jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), shbh);
							List<Map<String,String>> cjrMapList = ob.getCjrList();
							if(jpKhddCjrList.size()==cjrMapList.size()){
								blArr[i] = true;
								break;
							}
						}
					}
				}
				boolean bl = false;
				for(int i=0;i<blArr.length;i++){//只要系统订单有一个和再次入库的订单信息不匹配，bl=true
					if(!blArr[i]){
						bl = true;
						break;
					}
				}
				if(bl){
					//按外部单号进行拆单
					int rtn = jpKhddImpl.deleteAllByWbdh(wbdh, shbh);
					if(rtn == 1 ){//删除成功要继续入库
						list.addAll(obList);
					}
				}
				list.addAll(obList);
				continue;
			}
		}
		return list;
	}
}
