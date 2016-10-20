package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.JpKhddJplb;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;



/**
 * 接口导单入库
 */
@Service
public class JpddWork_jp { 
	protected final Logger logger = LoggerFactory.getLogger(JpddWork_jp.class);
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	@Autowired
	private JpKhddKzServiceImpl jkksImpl;
	@Autowired
	private JpKhddHdServiceImpl jkhsImpl;
	@Autowired
	private JpKhddCjrServiceImpl jkcsImpl;
	@Autowired
	private JpJpServiceImpl jjsImpl;
	@Autowired
	private JpKhddServiceImpl jksImpl;
	@Autowired
	private JpKhddJplbServiceImpl jkjsImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	/**
	 * 供应回填票号查询订单的时间范围 这里设为48小时内的订单可以回填
	 */
	public static final Integer ZWHTSJ = 60 * 48;

	/**
	 * 交票错误后 下次重复交票间隔时间 分钟 重复交票次数减少
	 */
	private Integer[] JPJJSJ = new Integer[] {3, 10, 30 };

	/**
	 * 允许最多重复交票的次数
	 */
	private int JPJJCOUNT = JPJJSJ.length;
	
	private static Map<String,String> tmpMap = new HashMap<String, String>();//用外部单号控制不要重复回填。
	public JpddWork_jp(){
		
	}
	public int ptjp_auto(String wdid){
		JpDdsz _jpDdsz = new JpDdsz();
		_jpDdsz.setWdid(wdid);
		JpDdsz jpDdsz = jpDdszImpl.getEntityById(_jpDdsz);
		if(jpDdsz==null||!"1".equals(jpDdsz.getDdKqzcdd())){//如果查不到导单设置，或者导单设置没有开启
			return -1;
		}
		String nowdate = VeDate.getStringDate();
		String zwhtsj = VeDate.getPreMin(nowdate, -ZWHTSJ);
		HashMap<String,String> JpKhddJplbParam = new HashMap<String, String>();
		JpKhddJplbParam.put("wdid", wdid);
		JpKhddJplbParam.put("shbh", jpDdsz.getShbh());
		JpKhddJplbParam.put("zwhtsj",zwhtsj);
		List<JpKhddJplb> list = jkjsImpl.selectByJp(JpKhddJplbParam);
		if(CollectionUtils.isEmpty(list)){
			return 0;
		}
		Iterator<JpKhddJplb> iterator = list.iterator();

		if (!PlatCode.XC.equals(jpDdsz.getWdpt())) {
			String tmpStr = "";
			while (iterator.hasNext()) {// 多个相同WBDH的过滤掉
				JpKhddJplb jplb = iterator.next();
				String wbdh = jplb.getWbdh();
				if (tmpStr.indexOf(wbdh) >= 0) {// 相同外部单号只用回填一次
					tmpStr += "," + wbdh;
					iterator.remove();
					logger.error("平台交票重复判断" + wbdh);
				}
			}
		}
		ExecutorService JP_HT_EXECUTOR = Executors.newFixedThreadPool(3);
		try {
			for (int i = 0; i < list.size(); i++) {
				JpKhddJplb t = list.get(i);
				JP_HT_EXECUTOR.execute(new Command(t, jpDdsz));
			}
		} finally {
			JP_HT_EXECUTOR.shutdown();
		}
		try {
			JP_HT_EXECUTOR.awaitTermination(10L, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error("回填票号超时", e);
		}
		return 0;
	}
	
	class Command implements Runnable {
		JpKhddJplb t;
		JpDdsz jpDdsz;

		public Command( JpKhddJplb t, JpDdsz jpDdsz) {
			this.t = t;
			this.jpDdsz = jpDdsz;
		}

		@Override
		public void run() {
			JpPtLog ptlb = new JpPtLog();
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYPHHT.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setShbh(jpDdsz.getShbh());
			if(PlatCode.XC.equals(jpDdsz.getWdpt())){//携程票号回填是一个PNR回填一次
				_ptjp2(t, jpDdsz, ptlb);
			}else{//其他网店是合并订单一起回填
				_ptjp(t, jpDdsz, ptlb);
			}
			
		}
	}
	/**
	 * 手工票号回填
	 * @param wbdh
	 * @param jpDdsz
	 */
	public String ptjp_sd(String wbdh,String ddbh, JpDdsz jpDdsz){
		JpPtLog ptlb = new JpPtLog();
		ptlb.setYwlx(DictEnum.PTLOGYWLX.GYSGDD.getValue());
		ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptlb.setPtzcbs(jpDdsz.getWdpt());
		ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
		ptlb.setYhbh(jpDdsz.getDdUserid());
		ptlb.setShbh(jpDdsz.getShbh());
		Map<String,Object> JpKhddJplbParam = new HashMap<String, Object>();
		JpKhddJplbParam.put("wbdh", wbdh);
		JpKhddJplbParam.put("ddbh", ddbh);
		JpKhddJplbParam.put("shbh", jpDdsz.getShbh());
		JpKhddJplb jplb = null;
		try {
			List<JpKhddJplb> jpKhddList = (List<JpKhddJplb>)jkjsImpl.selectDynamicSQL(JpKhddJplbParam);
			if(CollectionUtils.isNotEmpty(jpKhddList)){
				jplb = jpKhddList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jplb == null) {
			return "根据WBDH[" + wbdh + "];DDBH[" + ddbh + "]没有查到交票对象";
		}
		if(PlatCode.XC.equals(jpDdsz.getWdpt())){//携程票号回填是一个PNR回填一次
			return _ptjp2(jplb, jpDdsz, ptlb);
		}else{//其他网店是合并订单一起回填
			return _ptjp(jplb, jpDdsz, ptlb);
		}
	}
	/**
	 * 平台交票
	 * @throws  
	 */
	private String _ptjp(JpKhddJplb t,JpDdsz jpDdsz,JpPtLog ptlb){
		
		if(t == null){//没有找到要回填的订单
			ptlb.add("不符合票号回填的条件，请检查该订单是否在jp_khdd_jplb是否存在");
			return "不符合票号回填的条件，请检查该订单是否在jp_khdd_jplb是否存在";
		}
		String wbdh = t.getWbdh();
		//String ddbh = t.getDdbh();
		String shbh = t.getShbh();
		boolean rtnbl = true; 
		String errMsg = "回填成功";
		PlatJpBean pjb = null;
		boolean bl = false;
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYPHHT.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setShbh(shbh);
			String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			if(StringUtils.isNotBlank(tmpMap.get(wbdh))) {//根据外部单号进行锁单
				ptlb.add("该订单正在回填中");
				return "该订单正在回填中";
			}
			tmpMap.put(wbdh, wbdh);
			if(StringUtils.isBlank(wbdh)){//没有找到要回填的订单
				ptlb.add("不符合票号回填的条件，请检查外部单号【"+wbdh+"】的订单是否存在");
				return "不符合票号回填的条件，请检查外部单号【"+wbdh+"】的订单是否存在";
			}
			if("1".equals(t.getPhhtzt())){//分离订单票号回填时，其中一张订单回填时，另一张订单无需再次回填
				ptlb.add("该订单已经回填，无需再次回填");
				return "该订单已经回填，无需再次回填";
			}
			
			try {
				pjb = getPlatJpBean(wbdh, "", shbh);
			} catch (Exception e) {
				return e.getMessage();
			}
			if(pjb==null){
				return "票号回填必要信息不足无法回填";
			}
			bl = true;
			ptlb.setDdbh(pjb.getDdbhs().get(0));
			ptlb.setShbh(shbh);
			ptlb.setPnrNo(pjb.getJpKhddList().get(0).getXsPnrNo());
			ptlb.setCzsm(jpDdsz.getWdmc() + "供应票号回填");
			ptlb.setTfid(wbdh);
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			JpddGySuper jpddGy = JpddGySuper.getJpddGySuper(jpDdsz);
			rtnbl = jpddGy.orderOutTicket(ptlb, pjb);
		}catch (Exception e) {
			rtnbl = false;
			e.printStackTrace();
			ptlb.setCzsm(ptlb.getCzsm()+"供应票号回填错误");
			ptlb.add("供应票号回填异常"+ e.getMessage());
			errMsg="供应票号回填异常，失败原因："+e.getMessage()+"\n";
		} finally {
			if(bl){
				try {
					jpPtLogServiceImpl.insertLog(ptlb);
					updateJpZtByDdbh(rtnbl, errMsg, pjb.getDdbhs(),jpDdsz);
					updateJpKhddJplb(rtnbl, errMsg, t, jpDdsz,"");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			tmpMap.remove(wbdh);
		}
		return "";
	}
	/**
	 * 平台交票
	 * @throws  
	 */
	private String _ptjp2(JpKhddJplb t,JpDdsz jpDdsz,JpPtLog ptlb){
		
		if(t == null){//没有找到要回填的订单
			ptlb.add("不符合票号回填的条件，请检查该订单是否在jp_khdd_jplb是否存在");
			return "不符合票号回填的条件，请检查该订单是否在jp_khdd_jplb是否存在";
		}
		String wbdh = t.getWbdh();
		String ddbh = t.getDdbh();
		String shbh = t.getShbh();
		boolean rtnbl = true; 
		String errMsg = "回填成功";
		PlatJpBean pjb = null;
		boolean bl = false;
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYPHHT.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setShbh(shbh);
			String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			
			if(StringUtils.isNotBlank(tmpMap.get(ddbh))) {//根据外部单号进行锁单
				ptlb.add("该订单正在回填中");
				return "该订单正在回填中";
			}
			tmpMap.put(ddbh,ddbh);
			if (StringUtils.isBlank(wbdh)) {// 没有找到要回填的订单
				ptlb.add("不符合票号回填的条件，请检查外部单号【" + wbdh + "】的订单是否存在");
				return "不符合票号回填的条件，请检查外部单号【" + wbdh + "】的订单是否存在";
			}
			if("1".equals(t.getPhhtzt())){//分离订单票号回填时，其中一张订单回填时，另一张订单无需再次回填
				ptlb.add("该订单已经回填，无需再次回填");
				return "该订单已经回填，无需再次回填";
			}
			
			try {
				pjb = getPlatJpBean(wbdh, ddbh, shbh);
			} catch (Exception e) {
				return e.getMessage();
			}
			if(pjb==null){
				return "票号回填必要信息不足无法回填";
			}
			bl = true;
			ptlb.setDdbh(pjb.getDdbhs().get(0));
			ptlb.setShbh(shbh);
			ptlb.setPnrNo(pjb.getJpKhddList().get(0).getXsPnrNo());
			ptlb.setCzsm(jpDdsz.getWdmc() + "供应票号回填");
			ptlb.setTfid(wbdh);
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			JpddGySuper jpddGy = JpddGySuper.getJpddGySuper(jpDdsz);
			rtnbl = jpddGy.orderOutTicket(ptlb, pjb);
		}catch (Exception e) {
			rtnbl = false;
			e.printStackTrace();
			ptlb.setCzsm(ptlb.getCzsm()+"供应票号回填错误");
			ptlb.add("供应票号回填异常"+ e.getMessage());
			errMsg="供应票号回填异常，失败原因："+e.getMessage()+"\n";
		} finally {
			if(bl){
				try {
					jpPtLogServiceImpl.insertLog(ptlb);
					updateJpZtByDdbh(rtnbl, errMsg, pjb.getDdbhs(),jpDdsz);
					updateJpKhddJplb(rtnbl, errMsg, t, jpDdsz,ddbh);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			tmpMap.remove(ddbh);
		}
		//修改票号回填状态
		return "";
	}
	private PlatJpBean getPlatJpBean(String wbdh,String ddbh,String shbh) throws Exception{
		List<String> ddbhs = new ArrayList<String>();
		List<JpKhddKz> jpKhddKzList = null;
		PlatJpBean pjb = new PlatJpBean();
		//订单表
		Map<String,Object> jpKhddParam = new HashMap<String, Object>();
		jpKhddParam.put("wbdh", wbdh);
		jpKhddParam.put("ddbh", ddbh);
		jpKhddParam.put("shbh", shbh);
		List<JpKhdd> jpKhddList = (List<JpKhdd>) jksImpl.selectJpByWbdh(jpKhddParam);
		if(CollectionUtils.isEmpty(jpKhddList)){
			throw new Exception("订单信息不存在无法进行票号回填"); 
		}
		for(int i=0;i<jpKhddList.size();i++){
			ddbhs.add(jpKhddList.get(i).getDdbh());
		}
		pjb.setWdbh(wbdh);
		pjb.setDdbhs(ddbhs);
		pjb.setJpKhddList(jpKhddList);
		//订单扩展表
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("ddbhs", ddbhs);
		param.put("shbh", shbh);
		jpKhddKzList = jkksImpl.selectJpByDdbh(param);
		pjb.setJpKhddKzList(jpKhddKzList);
		//航段信息
		List<JpKhddHd> jpKhddHdList = (List<JpKhddHd>) jkhsImpl.selectJpByDdbh(param);
		Map<String,List<JpKhddHd>> jpKhddHdListMap = new HashMap<String, List<JpKhddHd>>();
		for(int i=0;i<jpKhddHdList.size();i++){
			JpKhddHd oneHd = jpKhddHdList.get(i);
		 	String _ddbh = oneHd.getDdbh();
		 	List<JpKhddHd> tmpList = jpKhddHdListMap.get(_ddbh);
		 	if(tmpList==null){
		 		tmpList = new ArrayList<JpKhddHd>();
		 	}
		 	tmpList.add(oneHd);
		 	jpKhddHdListMap.put(_ddbh, tmpList);
		}
		pjb.setJpKhddHdListMap(jpKhddHdListMap);
		//乘机人信息
		List<JpKhddCjr> jpKhddCjrList = jkcsImpl.selectJpByDdbh(param);
		Map<String,List<JpKhddCjr>> jpKhddCjrListMap = new HashMap<String, List<JpKhddCjr>>();
		for(int i=0;i<jpKhddCjrList.size();i++){
			JpKhddCjr oneCjr = jpKhddCjrList.get(i);
		 	String _ddbh = oneCjr.getDdbh();
		 	List<JpKhddCjr> tmpList = jpKhddCjrListMap.get(_ddbh);
		 	if(tmpList==null){
		 		tmpList = new ArrayList<JpKhddCjr>();
		 	}
		 	tmpList.add(oneCjr);
		 	jpKhddCjrListMap.put(_ddbh, tmpList);
		}
		pjb.setJpKhddCjrListMap(jpKhddCjrListMap);
		//票号信息
		List<JpJp> jpJpList = (List<JpJp>) jjsImpl.selectJpByDdbh(param);
		Map<String,List<JpJp>> jpJpListMap = new HashMap<String, List<JpJp>>();
		for(int i=0;i<jpJpList.size();i++){
			JpJp oneJp = jpJpList.get(i);
		 	String _ddbh = oneJp.getDdbh();
		 	List<JpJp> tmpList = jpJpListMap.get(_ddbh);
		 	if(tmpList==null){
		 		tmpList = new ArrayList<JpJp>();
		 	}
		 	tmpList.add(oneJp);
		 	jpJpListMap.put(_ddbh, tmpList);
		}
		if(jpJpListMap.size()!=ddbhs.size()){//表示存在分离订单，但是只有部分出票
			throw new Exception("存在分离订单，同时分离订单没有出票");
		}
		pjb.setJpJpListMap(jpJpListMap);
		return pjb;
	}
	public void updateJpKhddJplb(boolean rtnbl,String errMsg,JpKhddJplb t,JpDdsz jpDdsz,String ddbh){
		JpKhddJplb jplb = new JpKhddJplb();
		jplb.setWbdh(t.getWbdh());
		jplb.setShbh(jpDdsz.getShbh());
		jplb.setDdbh(ddbh);
		if(rtnbl){
			jplb.setPhhtzt("1");//回填成功
			jplb.setPhhtsbyy("回填成功");
		}else{
			jplb.setPhhtzt("2");//回填失败
			if(StringUtils.isNotBlank(errMsg)&&errMsg.length()>500){
				errMsg = errMsg.substring(0,500);
			}
			jplb.setPhhtsbyy(errMsg);
		}			
		try {
			jplb.setXgsj(new Date());
			jplb.setXgyh(jpDdsz.getDdUserid());
			String xchtsj = "";
			if (t.getPhhtsbcs() < JPJJCOUNT) {// 小于交票次数
				xchtsj = VeDate.getPreMin(VeDate.getStringDate(), JPJJSJ[t.getPhhtsbcs()]);
			}else{
				xchtsj = "2099-01-01 00:00:00";
			}
			jplb.setPhhtsbcs(t.getPhhtsbcs()+1);
			jplb.setXchtsj(VeDate.formatToDate(xchtsj, "yyyy-MM-dd hh:mm:ss"));
			jkjsImpl.updateJplbByBean(jplb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateJpZtByDdbh(boolean rtnbl,String errMsg,List<String> ddbhs,JpDdsz jpDdsz){
		//修改回填状态
		try {
				//这里是为了记录异动
			Map<String,Object> updateJpKhddParam = new HashMap<String, Object>();
			updateJpKhddParam.put("ddbhs", ddbhs);
			updateJpKhddParam.put("shbh",jpDdsz.getShbh());
			updateJpKhddParam.put("xgyh", jpDdsz.getDdUserid());
			updateJpKhddParam.put("xgly", "供应票号回填");
			jksImpl.updateXglyByDdbh(updateJpKhddParam);
			
			Map<String,Object> updateKzParam = new HashMap<String, Object>();
			if(rtnbl){
				updateKzParam.put("phhtzt", "1");//回填成功
				updateKzParam.put("phhtsbyy", "回填成功");
			}else{
				if(StringUtils.isNotBlank(errMsg)&&errMsg.length()>500){
					errMsg = errMsg.substring(0,500);
				}
				updateKzParam.put("phhtsbyy", errMsg);
				updateKzParam.put("phhtzt", "2");//回填失败
			}
			updateKzParam.put("ddbhs", ddbhs);
			updateKzParam.put("shbh", jpDdsz.getShbh());
			jkksImpl.updateJpZtByDdbh(updateKzParam);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
