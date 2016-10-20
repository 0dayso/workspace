package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.base.Shfwkqsz;
import cn.vetech.vedsb.common.service.base.ShfwkqszServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpGqdGySuper;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;



/**
 * 接口导单入库
 */
@Service
public class JpddWork_gqd { 
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	@Autowired
	private JpGqdHandleServiceImpl jghsImpl;
	@Autowired
	private JpGqdServiceImpl gqdImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ShfwkqszServiceImpl shfwkqszServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	/**
	 * 开启扫描导单
	 */
	public int  queryorder_gq(String wdid) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			List<TicketChangeBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if(jpDdsz==null||!"1".equals(jpDdsz.getDdKqgqdd())){//查不到导单配置，或者改签单业务未开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYGQDLASTDAY.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.GQ.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应改签单扫描");
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
//			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())||VeDate.getTwoDay(shfwkqsz.getYxq(), VeDate.getNow())<0){
//				ptlb.add("商户导单服务未开启，或者过期无法扫描导单");
//				return -1;
//			}
			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())){
				ptlb.add("商户导单服务未开启，自动关闭供应改签单开关，请开启商务服务后重新打开供应改签单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqgqdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpGqdGySuper jpddGy = JpGqdGySuper.getJpGqdGySuper(jpDdsz);
			if(jpddGy==null){
//				bl = false;
				ptlb.add("没有实现该网店供应改签单功能。");
				return -1;
			}
			list = jpddGy.queryOrders_gq(ptlb);
			if(list==null){
				return 0;
			}
			Iterator<TicketChangeBean> iterator = list.iterator();
			while(iterator.hasNext()){
				TicketChangeBean tcBean = iterator.next();
				if(!validate(tcBean, jpDdsz, ptlb)){
					iterator.remove();
				}
			}
			iterator = list.iterator();
			while(iterator.hasNext()){
				TicketChangeBean tcBean = iterator.next();
				fillTicketChangeBean(tcBean,jpDdsz,ptlb);
				jghsImpl.toDbGq(tcBean,jpDdsz,ptlb);
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
	/**
	 * 手工扫描导改签单
	 */
	public int queryorder_gq(String wdid,String dateStr) {
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			List<TicketChangeBean> list = null;
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
			if(jpDdsz==null||!"1".equals(jpDdsz.getDdKqgqdd())){//查不到导单配置，或者改签单业务未开启
				bl = false;
				return -1;
			}
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYSGGQD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.GQ.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应改签单扫描");
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
//			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())||VeDate.getTwoDay(shfwkqsz.getYxq(), VeDate.getNow())<0){
//				ptlb.add("商户导单服务未开启，或者过期无法扫描导单");
//				return -1;
//			}
			if(shfwkqsz==null||"0".equals(shfwkqsz.getSfkq())){
				ptlb.add("商户导单服务未开启，自动关闭供应改签单开关，请开启商务服务后重新打开供应改签单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqgqdd("0");
				jpDdszImpl.update(_jpDdsz);
				return -1;
			}
			JpGqdGySuper jpddGy = JpGqdGySuper.getJpGqdGySuper(jpDdsz);
			if(jpddGy==null){
//				bl = false;
				ptlb.add("没有实现该网店供应改签单功能。");
				return -1;
			}
			list = jpddGy.queryOrders_gq(ptlb,dateStr);
			if(list==null){
				return 0;
			}
			Iterator<TicketChangeBean> iterator = list.iterator();
			while(iterator.hasNext()){
				TicketChangeBean tcBean = iterator.next();
				if(!validate(tcBean, jpDdsz, ptlb)){
					iterator.remove();
				}
			}
			iterator = list.iterator();
			while(iterator.hasNext()){
				TicketChangeBean tcBean = iterator.next();
				fillTicketChangeBean(tcBean,jpDdsz,ptlb);
				jghsImpl.toDbGq(tcBean,jpDdsz,ptlb);
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
	/**
	 * 补齐TicketChangeBean数据信息
	 */
	private void fillTicketChangeBean(TicketChangeBean tcBean,JpDdsz jpDdsz,JpPtLog ptlb){
		if(PlatCode.TB.equals(jpDdsz.getWdpt())){//淘宝改签单要补齐GqdMx信息
			String wbddbh = VeStr.getValue(tcBean.getGqdMap(), "WBDDBH");
			List<Map<String,Object>> gqdMxMapList = tcBean.getGqdMxMapList();
			List<Map<String,Object>> hdMapList = tcBean.getHdMapList();
			String tknoStr = StringUtils.trimToEmpty((String)gqdMxMapList.get(0).get("TKNO"));
			String xs_pnr_no = StringUtils.trimToEmpty((String)gqdMxMapList.get(0).get("XS_PNR_NO"));
			if(StringUtils.isNotBlank(tknoStr)&&StringUtils.isNotBlank(xs_pnr_no)){//如果有票号则不传
				return;
			}
			String travelRange = "";
			for(int i=0;i<hdMapList.size();i++){
				Map<String,Object> hdMap = hdMapList.get(i);
				if(StringUtils.isBlank(travelRange)){
					travelRange = VeStr.getValue(hdMap, "CFCITY")+VeStr.getValue(hdMap,"DDCITY");
				}else{
					int index = travelRange.length()-3;
					if(travelRange.substring(index).equals(hdMap.get("CFCITY"))){
						travelRange += VeStr.getValue(hdMap, "DDCITY");
					}else{
						travelRange += VeStr.getValue(hdMap, "CFCITY")+VeStr.getValue(hdMap,"DDCITY");
					}
				}
			}
			List<JpJp> jpJpList = jpJpServiceImpl.getJpjpListByWbdh(wbddbh, jpDdsz.getWdid(), jpDdsz.getShbh());
			
			Map<String,String> tknoMap = new HashMap<String, String>();
//			Map<String,String> tmpStrMap = new HashMap<String, String>();
			String tmp_xs_pnr_no = "";
			for(int i=0;i<jpJpList.size();i++){
				JpJp jpJp = jpJpList.get(i);
				String hc = jpJp.getHc();
				String cjr = jpJp.getCjr().toUpperCase().replaceAll("CHD", "").replaceAll("INF", "");
				String _tkno = jpJp.getTkno();
//				String o_hbh = jpJp.getCgHbh();
//				String o_cw = jpJp.getCgCw();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				String o_cfsj = sdf.format(jpJp.getCfrq())+" "+jpJp.getCfsj();
				if(travelRange.indexOf(hc)==-1){
					continue;
				}
				tmp_xs_pnr_no = jpJp.getXsPnrNo();
//				String tmpStr = tmpStrMap.get(hc);
//				String _tmpStr = o_hbh+";"+o_cw+";"+o_cfsj;
//				if(StringUtils.isNotBlank(tmpStr)){
//					tmpStr += "|"+_tmpStr;
//				}else{
//					tmpStr = _tmpStr;
//				}
//				tmpStrMap.put(hc, tmpStr);
//				
				String tmpTkno = tknoMap.get(cjr);
				if(StringUtils.isNotBlank(tmpTkno)){
					tmpTkno += "|"+_tkno;
				}else{
					tmpTkno = _tkno;
				}
				tknoMap.put(cjr, tmpTkno);
			}
			tcBean.getGqdMap().put("XS_PNR_NO", tmp_xs_pnr_no);
			tcBean.getGqdMap().put("CG_PNR_NO", tmp_xs_pnr_no);
//			for(int i=0;i<hdMapList.size();i++){
//				Map<String,String> hdMap = hdMapList.get(i);
//				String hc = hdMap.get("CFCITY")+hdMap.get("DDCITY");
//				String tmpStr = tmpStrMap.get(hc);
//				if(StringUtils.isNotBlank(tmpStr)){
//					String[] tmpStrArr = tmpStr.split(";",-1);
//					hdMap.put("O_XS_HBH", tmpStrArr[0]);
//					hdMap.put("O_XS_CW", tmpStrArr[1]);
//					hdMap.put("O_CFSJ", tmpStrArr[2]);
//				}
//			}
			for(int i=0;i<gqdMxMapList.size();i++){
				Map<String,Object> gqdMxMap = gqdMxMapList.get(i);
				String cjr = VeStr.getValue(gqdMxMap, "CJR").toUpperCase().replaceAll("CHD", "").replaceAll("INF", "");
				gqdMxMap.put("TKNO", tknoMap.get(cjr));
			}
		}
	}
	private boolean validate(TicketChangeBean tcBean,JpDdsz one,JpPtLog ptlb) throws Exception{
		String wbdh = VeStr.getValue(tcBean.getGqdMap(), "WBDH");
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wbdh", wbdh);
		paramMap.put("shbh", one.getShbh());
		String wbgqzt = VeStr.getValue(tcBean.getGqdMap(), "WBGQZT");
		List<JpGqd> jpGqdList = gqdImpl.checkGqdByWbdh(paramMap);
		if(jpGqdList!=null&&jpGqdList.size()>0){
			if(StringUtils.isNotBlank(wbgqzt)&&!wbgqzt.equals(jpGqdList.get(0).getWbgqzt())){

			}else{
				ptlb.add("外部改签单号【"+wbdh+"】已经在系统中存在，本次改签单过滤");
				LogUtil.getGyrz("GLGQD").error("外部改签单号【"+wbdh+"】已经在系统中存在，本次改签单过滤\r\n");
				return false;
			}
		}
		String wbddbh = VeStr.getValue(tcBean.getGqdMap(), "WBDDBH");
		List<JpJp> jpJpList = jpJpServiceImpl.getJpjpListByWbdh(wbddbh, one.getWdid(), one.getShbh());
		if(CollectionUtils.isEmpty(jpJpList)){
			ptlb.add("关联原订单不存在，或者关联原订单未出票【"+wbddbh+"】，改签单过滤");
			LogUtil.getGyrz("GLGQD").error("关联原订单不存在，或者关联原订单未出票【"+wbddbh+"】，改签单过滤\r\n");
			return false;
		}
//		String tkno = VeStr.getValue(tcBean.getGqdMxMapList().get(0),"TKNO");
//		boolean bl = false;
//		for(int i=0;i<jpJpList.size();i++){
//			JpJp jpJp = jpJpList.get(i);
//			if(jpJp.getTkno().equals(tkno)){
//				bl = true;
//				break;
//			}
//		}
//		if(!bl){
//			ptlb.add("关联原订单票号不存在，或者关联原订单未出票【"+gyddbh+"】，改签单过滤");
//			LogUtil.getGyrz("GLGQD").error("关联原订单票号不存在，或者关联原订单未出票【"+gyddbh+"】，改签单过滤\r\n");
//		}
		return true;
	}
//	/**
//	 * 改签操作
//	 * 返回Map,里面有两个返回值status和message
//	 * status:true或false
//	 * message:当status为false时，返回错误信息。
//	 * @param gqid
//	 * @param ve_yhb
//	 * @return
//	 */
//	public Map<String,String> gqcl(String gqdh,Shyhb shyhb){
//		Map<String,String> retMap = new HashMap<String, String>();
//		if(StringUtils.isBlank(gqdh)){
//			retMap.put("status", "false");
//			retMap.put("message", "改签单号不能为空");
//			return retMap;
//		}
//		JpGqd jpGqd = null;
//		List<JpGqdCjr> gqCjrList = null;
//		List<JpGqdHd> gqHdList = null;
//		String shbh=shyhb.getShbh();
//		try {
//			jpGqd = gqdImpl.getJpGqdByGqdh(gqdh, shbh);
//			gqCjrList = gqdCjrImpl.getCjrListByGqdh(gqdh, shbh);
//			gqHdList = gqdHdImpl.getHdListByGqdh(gqdh, shbh);
//			jpGqd.setCjrList(gqCjrList);
//			jpGqd.setHdList(gqHdList);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		if(jpGqd==null||gqCjrList==null||gqHdList==null){
//			retMap.put("status", "false");
//			retMap.put("message", "该改签单不存在");
//			return retMap;
//		}
//		JpDdsz jpDdsz = new JpDdsz();
//		jpDdsz.setWdid(jpGqd.getWdid());
//		try {
//			jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		if(jpDdsz==null||StringUtils.isBlank(jpDdsz.getWdpt())){
//			retMap.put("status", "false");
//			retMap.put("message", "该改签单不属于网店导入，没有找到对应的网店配置");
//			return retMap;
//		}
//		Map<String,Object> gqParams = new HashMap<String, Object>();
//		gqParams.put("jpGqd", jpGqd);
//		
//		JpPtLog ptlb = new JpPtLog();
//		ptlb.setDdbh(jpGqd.getDdbh());
//		ptlb.setPnrNo(jpGqd.getXsPnrNo());
//		ptlb.setTfid(gqdh);
//		ptlb.setYhbh(shyhb.getBh());
//		ptlb.setShbh(shyhb.getShbh());
//		ptlb.setYwlx(DictEnum.PTLOGYWLX.GYGQDGQ.getValue());
//		ptlb.setDdlx(DictEnum.PTLOGDDLX.GQ.getValue());
//		ptlb.setPtzcbs(jpDdsz.getWdpt());
//		String ddgngj = jpDdsz.getDdGngj();//订单国内国际  0国内 1国际
//		String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
//		ptlb.setBy1(by1);
//		ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
//		JpGqdGySuper jpddGy = JpGqdGySuper.getJpGqdGySuper(jpDdsz);
//		if(jpddGy == null){
//			retMap.put("status", "false");
//			retMap.put("message", "不支持该网店改签单操作业务");
//			return retMap;
//		}
//		retMap = jpddGy.gq(ptlb, gqParams);
//		try {
//			jpPtLogServiceImpl.insertLog(ptlb);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retMap;
//	}
}
