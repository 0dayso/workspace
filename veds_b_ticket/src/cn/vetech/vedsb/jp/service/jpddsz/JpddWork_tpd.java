package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shfwkqsz;
import cn.vetech.vedsb.common.service.base.ShfwkqszServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.jpddsz.JpTpdGySuper;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;



/**
 * 接口导单入库
 */
@Service
public class JpddWork_tpd { 
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	@Autowired
	private JpTpdHandleServiceImpl jthsImpl;
	@Autowired
	private JpTpdServiceImpl jtsImpl;
	@Autowired
	private JpTpdMxServiceImpl jtmsImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ShfwkqszServiceImpl shfwkqszServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;
	/**
	 * 开启扫描导单
	 */
	public int  queryorder_tf(String wdid) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			List<TicketRefundBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if(jpDdsz==null||!"1".equals(jpDdsz.getDdKqtpdd())){//查不到导单配置，或者退废单业务未开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYTFDLASTDAY.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应退票单扫描");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			//判断商户服务是否开启
			Shfwkqsz shfwkqsz = shfwkqszServiceImpl.getShfwkqszByShbhLxFwlxid(jpDdsz.getShbh(), "1", "7006101");
			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())){
				ptlb.add("商户导单服务未开启，自动关闭供应退票单开关，请开启商务服务后重新打开供应退票单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqtpdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpTpdGySuper jpddGy = JpTpdGySuper.getJpTpdGySuper(jpDdsz);
			if(jpddGy==null){
				ptlb.add("没有实现该网店供应退票单功能。");
				return -1;
			}
			list = jpddGy.queryOrders_tf(ptlb);
			if(list==null||list.isEmpty()){
				return 0;
			}
			List<TicketRefundBean> newlist = new ArrayList<TicketRefundBean>();
			Iterator<TicketRefundBean> iterator = list.iterator();
			while(iterator.hasNext()){//先过滤 后入库
				TicketRefundBean tfBean = iterator.next();
				if(!validate(tfBean, jpDdsz, ptlb)){
					iterator.remove();
					continue;
				}
				//淘宝订单要合并
				List<TicketRefundBean> tfBeanList = otherHandle(tfBean,jpDdsz,ptlb);
				newlist.addAll(tfBeanList);
			}
			iterator = newlist.iterator();
			while(iterator.hasNext()){
				TicketRefundBean tfBean = iterator.next();
				jthsImpl.toDbTf(tfBean, jpDdsz, ptlb.getInfo());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(bl){
				try {
					jpPtLogServiceImpl.saveLastdayLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	public int  queryorder_tf(String wdid,String dateStr) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			List<TicketRefundBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if(jpDdsz==null||!"1".equals(jpDdsz.getDdKqtpdd())){//查不到导单配置，或者退废单业务未开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYSGTFD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应退废单手工扫描");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			//判断商户服务是否开启
			Shfwkqsz shfwkqsz = shfwkqszServiceImpl.getShfwkqszByShbhLxFwlxid(jpDdsz.getShbh(), "1", "7006101");
			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())||VeDate.getTwoDay(shfwkqsz.getYxq(), VeDate.getNow())<0){
				ptlb.add("商户导单服务未开启，或者过期无法扫描导单");
				return -1;
			}
			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())){
				ptlb.add("商户导单服务未开启，自动关闭供应退票单开关，请开启商务服务后重新打开供应退票单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqtpdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpTpdGySuper jpddGy = JpTpdGySuper.getJpTpdGySuper(jpDdsz);
			if(jpddGy==null){
				ptlb.add("没有实现该网店供应退票单功能。");
				return -1;
			}
			list = jpddGy.queryOrders_tf(ptlb,dateStr);
			if(list==null){
				return 0;
			}
			List<TicketRefundBean> newlist = new ArrayList<TicketRefundBean>();
			Iterator<TicketRefundBean> iterator = list.iterator();
			while(iterator.hasNext()){//先过滤 后入库
				TicketRefundBean tfBean = iterator.next();
				if(!validate(tfBean, jpDdsz, ptlb)){
					iterator.remove();
					continue;
				}
				//淘宝订单要合并
				List<TicketRefundBean> tfBeanList = otherHandle(tfBean,jpDdsz,ptlb);
				newlist.addAll(tfBeanList);
			}
			iterator = newlist.iterator();
			while(iterator.hasNext()){
				TicketRefundBean tfBean = iterator.next();
				jthsImpl.toDbTf(tfBean, jpDdsz, ptlb.getInfo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(bl){
				try {
					jpPtLogServiceImpl.saveLastdayLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	public Map<String,Object> getTpdMap(String tpdh, Shyhb shyhb) throws Exception{
		Map<String,Object> tpdMap = null;
		String shbh = shyhb.getShbh();
		try {
			tpdMap = jtsImpl.selectTpFhByTfdh(tpdh, shbh);
		} catch (Exception e) {
			e.getMessage();
		}
		if(tpdMap==null){
			throw new Exception("该退废单不存在。");
		}
		return tpdMap;
	}
	/**
	 * 根据退票单号获取退票信息接口
	 * @param tpdh
	 * @param shyhb
	 * @param ptlb
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getParam(String tpdh, Shyhb shyhb,JpPtLog ptlb) throws Exception{
		if(StringUtils.isBlank(tpdh)){
			throw new Exception("退票单号不能为空");
		}
		String shbh = shyhb.getShbh();
		Map<String,Object> tpdMap = getTpdMap(tpdh, shyhb);
		String gytfdh = StringUtils.trimToEmpty((String)tpdMap.get("WBDH"));
		String wbddbh =  StringUtils.trimToEmpty((String)tpdMap.get("WBDDBH"));
		String wdid = StringUtils.trimToEmpty((String)tpdMap.get("WDID"));
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		try {
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(jpDdsz==null||StringUtils.isBlank(jpDdsz.getWdpt())){
			throw new Exception("该退票单不属于网店导入，没有找到对应的网店配置");
		}
		String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
		String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			
		List<JpTpdMx> tpdMxList =jtmsImpl.getJpTpdMxByTpdh(tpdh, shbh);
		List<String> cjrs = new ArrayList<String>();
		List<String> wbcjrids = new ArrayList<String>();
		List<String> xsTpsxfs = new ArrayList<String>();
		List<String> xsZdjs = new ArrayList<String>();
		List<String> tknos = new ArrayList<String>();
		List<String> wbsxhs = new ArrayList<String>();
		List<String> ytpfs = new ArrayList<String>();
		for(int i=0;i<tpdMxList.size();i++){
			JpTpdMx tpdMx = tpdMxList.get(i);
			cjrs.add(StringUtils.trimToEmpty(tpdMx.getCjr()));
			wbcjrids.add(StringUtils.trimToEmpty(tpdMx.getWbcjrid()));
			xsTpsxfs.add(tpdMx.getXsTpsxf()+"");
			xsZdjs.add(tpdMx.getXsZdj()+"");
			tknos.add(StringUtils.trimToEmpty(tpdMx.getTkno()));
			wbsxhs.add(StringUtils.trimToEmpty(tpdMx.getWbsxh()));
			ytpfs.add(StringUtils.trimToEmpty(tpdMx.getYtpf()));
		}
		Map<String,Object> fhParams = new HashMap<String, Object>();
		fhParams.put("gytfdh", gytfdh);
		fhParams.put("WBDDBH", wbddbh);
		
		fhParams.put("cjrs", cjrs);
		fhParams.put("wbcjrids", wbcjrids);
		fhParams.put("xsTpsxfs", xsTpsxfs);
		fhParams.put("xsZdjs", xsZdjs);
		fhParams.put("tknos", tknos);
		fhParams.put("wbsxhs", wbsxhs);
		fhParams.put("ytpfs", ytpfs);
		return fhParams;
	}
	private boolean validate(TicketRefundBean tfBean,JpDdsz one,JpPtLog ptlb) throws Exception{
		//退废单重复判断 根据票号判断，如果系统存在有效的退废单（未取消），则不导。
		String wbdh = StringUtils.trimToEmpty(tfBean.getTfdMap().get("WBDH"));
		List<Map<String,String>> mxlist = tfBean.getTfdMxMap();
		String[] tknos = new String[mxlist.size()];
		for(int i=0;i<mxlist.size();i++){
			Map<String,String> tpmx = mxlist.get(i);
			tknos[i] =tpmx.get("TKNO");
		}
		if(tknos.length==0||StringUtils.isBlank(tknos[0])){//无票号订单要过滤
			ptlb.add("外部退票单号【"+wbdh+"】的退票单没有票号，无法入库");
			LogUtil.getGyrz("GLTPD").error("外部退票单号【"+wbdh+"】的退票单没有票号，无法入库\r\n");
			return false;
		}
		String wbtpzt = StringUtils.trimToEmpty(tfBean.getTfdMap().get("WBTPZT"));
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("shbh", one.getShbh());
		param.put("tknos", tknos);
		param.put("wbdh", wbdh);
		Map<String,String> rtnMap = jtsImpl.checkTpdByPh(param);
		if(rtnMap!=null&&!rtnMap.isEmpty()){
			String tknosStr = StringUtils.trimToEmpty(rtnMap.get("TKNOS"));
			String wbtpztsStr =  StringUtils.trimToEmpty(rtnMap.get("WBTPZTS"));
			if(StringUtils.isNotBlank(tknosStr)&&((StringUtils.isNotBlank(wbtpzt)&&wbtpztsStr.indexOf(wbtpzt)>-1)||StringUtils.isBlank(wbtpzt))){
				ptlb.add("外部退票单号【"+wbdh+"】票号【"+tknosStr+"】的退票单已经存在数据库中，无法入库");
				LogUtil.getGyrz("GLTPD").error("过滤退票订单【"+wbdh+"】,重复退票订单导入，系统票号是【"+tknosStr+"】\r\n");
				return false;
			}else if(StringUtils.isNotBlank(tknosStr)){//外部单号不同的情况 表示之前已经入库过一次，这次不触发自动退票
				tfBean.getTfdMap().put("IFTBZT", "1");
			}
		}
		return true;
	}
	/**
	 * 在执行入库前要做一些其他处理，
	 * 例如淘宝退票单的拆分
	 * @param trBean
	 * @return
	 */
	private List<TicketRefundBean> otherHandle(TicketRefundBean trBean,JpDdsz jpDdsz,JpPtLog ptlb){
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		if(PlatCode.TB.equals(jpDdsz.getWdpt())){
			Map<String,TicketRefundBean> map = new HashMap<String, TicketRefundBean>();
			Map<String,String> tfdMap = trBean.getTfdMap();
			String wbdh = StringUtils.trimToEmpty(tfdMap.get("WBDH"));
			List<Map<String,String>> tfdMxList = trBean.getTfdMxMap();
			List<String> tknos = new ArrayList<String>();
			for(int i=0;i<tfdMxList.size();i++){
				tknos.add(tfdMxList.get(i).get("TKNO"));
			}
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("tknos", tknos);
			List<Map<String,String>> tknoMapList = jpGqdServiceImpl.getTknoByGqTkno(param);
			if(CollectionUtils.isNotEmpty(tknoMapList)){
				for(int i=0;i<tknoMapList.size();i++){
					String tkno = StringUtils.trimToEmpty(tknoMapList.get(i).get("TKNO"));
					String gq_tkno = StringUtils.trimToEmpty(tknoMapList.get(i).get("GQ_TKNO"));
					Iterator<Map<String,String>> iterator = tfdMxList.iterator();
					while(iterator.hasNext()){
						Map<String,String> tfdMx = iterator.next();
						if(gq_tkno.equals(tfdMx.get("TKNO"))){
							tfdMx.put("TKNO", gq_tkno);
							tfdMx.put("OLD_TKNO", tkno);
						}
					}
				}
			}
			
			String wbddbh = tfdMap.get("WBDDBH");//正常单外部单号
			Map<String,Object> param1 = new HashMap<String, Object>();
			param1.put("wbdh", wbdh);
			param1.put("shbh", jpDdsz.getShbh());
			List<JpTpd> tpdList = jtsImpl.getTpdByWbdh(param1);
			
			List<JpJp> jpJpList =jpJpServiceImpl.getJpjpListByWbdh(wbddbh, jpDdsz.getWdid(), jpDdsz.getShbh());
			for(int i=0;i<jpJpList.size();i++){//按订单编号生成新的TicketRefundBean对象
				JpJp jpJp = jpJpList.get(i);
				String ddbh = jpJp.getDdbh();//订单编号
				String tkno = jpJp.getTkno();//票号
				TicketRefundBean newTfBean = map.get(ddbh);
				if(newTfBean==null){
					newTfBean = new TicketRefundBean();
					Map<String,String> newTfdMap = new HashMap<String, String>(tfdMap);
					newTfBean.setTfdMap(newTfdMap);
					List<Map<String,String>> newTfdMxList = new ArrayList<Map<String,String>>();
					newTfBean.setTfdMxMap(newTfdMxList);
					if(CollectionUtils.isNotEmpty(tpdList)){
						Iterator<JpTpd> iterator = tpdList.iterator();
						while (iterator.hasNext()) {
							JpTpd jpTpd = iterator.next();
							if(ddbh.equals(jpTpd.getDdbh())){
								newTfBean.getTfdMap().put("TPDH", jpTpd.getTpdh());
								iterator.remove();
								break;
							}
						}
					}
				}
				String tknosStr = StringUtils.trimToEmpty(newTfBean.getTfdMap().get("tknos"));
				newTfBean.getTfdMap().put("tknos", tknosStr+"|"+tkno);
				map.put(ddbh, newTfBean);

			}
			list.addAll(map.values());
			Iterator<TicketRefundBean> iterator_tfBean = list.iterator();
			boolean bl = true;
			while(iterator_tfBean.hasNext()){
				TicketRefundBean newTfBean = iterator_tfBean.next();
				String tknosStr = newTfBean.getTfdMap().get("tknos");
				Iterator<Map<String,String>> iterator = tfdMxList.iterator();
				while(iterator.hasNext()){
					Map<String,String> tfdMx = iterator.next();
					String tkno = tfdMx.get("TKNO");
					String old_tkno = StringUtils.trimToEmpty(tfdMx.get("OLD_TKNO"));
					if(tknosStr.indexOf(tkno)>-1||(StringUtils.isNotBlank(old_tkno)&&tknosStr.indexOf(old_tkno)>-1)){//之前的逻辑已经判断了票号不能为空
						newTfBean.getTfdMxMap().add(tfdMx);
						iterator.remove();
					}
				}
				if(newTfBean.getTfdMxMap().isEmpty()){
					iterator_tfBean.remove();
					continue;
				}else{
					newTfBean.getTfdMap().put("HC", newTfBean.getTfdMxMap().get(0).get("HC"));
					newTfBean.getTfdMap().put("XS_HBH", newTfBean.getTfdMxMap().get(0).get("XS_HBH"));
				}
				if(bl){
					bl = false;
				}else{
					newTfBean.getTfdMap().put("AUTO_TP_XS", "-1");//表示不自动与客户办理，原因：淘宝拆分的退票单只用提交淘宝一次就足够了
				}
				newTfBean.getTfdMap().remove("tknos");
			}
			if(CollectionUtils.isNotEmpty(tfdMxList)){//如果还存在没有匹配上票号的退票单，则记录日志
				for(int i=0;i<tfdMxList.size();i++){
					Map<String,String> tfdMx = tfdMxList.get(i);
					String tkno = StringUtils.trimToEmpty(tfdMx.get("TKNO"));
					String old_tkno = StringUtils.trimToEmpty(tfdMx.get("OLD_TKNO"));
					StringBuffer msg = new StringBuffer("外部退票单号【"+wbdh+"】票号【"+tkno+"】");
					if(StringUtils.isNotBlank(old_tkno)){
						msg.append("或【"+old_tkno+"】");
					}
					msg.append("在系统中不存在无法入库直接过滤");
					ptlb.add(msg.toString());
					LogUtil.getGyrz("GLTPD").error(msg.toString()+"\r\n");
				}
				
			}
		}else{
			list.add(trBean);
		}
		return list;
	}
}
