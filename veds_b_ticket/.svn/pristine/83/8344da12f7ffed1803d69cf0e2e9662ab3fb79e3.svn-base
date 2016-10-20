package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.PNRPAT2Parser;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.BookOrder;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpzdbwServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpzdcpWork;
import cn.vetech.vedsb.jp.service.procedures.ProcPkgPnrServiceImpl;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
@Service
public class JpKhddHandleServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(JpKhddJplbServiceImpl.class);
	@Autowired
	private JpKhddServiceImpl jpKhddImpl;
	@Autowired
	private JpPzServiceImpl jppzImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private ProcPkgPnrServiceImpl procPkgPnrServiceImpl;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	@Autowired 
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpzdbwServiceImpl jpzdbwServiceImpl;
	@Autowired
	private JpzdcpWork jpzdcpWork;
	
	/**
	 * 导单入库
	 */
	public void toDb(OrderBean orderBean,JpDdsz one,String info){
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYDD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(one.getWdpt());
			ptlb.setShbh(one.getShbh());
			ptlb.setYhbh(one.getDdUserid());
			ptlb.setWdid(one.getWdid());
			ptlb.setWdmc(one.getWdmc());
			ptlb.setCzsm("供应导单");
			String ddgngj = one.getDdGngj();//订单国内国际  0国内 1国际
			String by1 = "1".equals(ddgngj) ? "1001902" : "1001901"; //默认算国内
			ptlb.setBy1(by1);
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.add2(info);
			ptlb.setTfid(orderBean.getKhddMap().get("WBDH"));
			//订单入库前业务
			toDbBefore(orderBean,one,ptlb);
			//执行入库
			BookOrder bean = new BookOrder(orderBean);
			ptlb.add("订单入库参数："+bean.getParam());
			logger.error("订单入库参数："+bean.getParam());
			procPkgPnrServiceImpl.insertJpKhdd(bean,ptlb);
			if(0!=bean.getResult()){
				ptlb.add("订单入库失败，数据库返回："+bean.getErrmsg());
				logger.error("订单入库失败，数据库返回："+bean.getErrmsg());
				if (-4 == bean.getResult()) {
					bl = false;
					ptlb.add("订单入库重复");
				}
				return;
			}
			String ddbh = bean.getDdbh();
			String iftbzt =  StringUtils.trimToEmpty(orderBean.getKhddMap().get("IFTBZT"));
			if("1".equals(iftbzt)){//TB同步状态的订单表示第一次已经入库过，所以DDBH要重传参取
				ddbh = StringUtils.trimToEmpty(orderBean.getKhddMap().get("DDBH"));
			}
			ptlb.setDdbh(ddbh);
			ptlb.add("订单入库成功，订单编号["+ddbh+"]");
			logger.error("订单入库成功，订单编号["+ddbh+"]");
			orderBean.getKhddMap().put("VEDSBDDBH", ddbh);
			String pnr_no = orderBean.getKhddMap().get("XS_PNR_NO");
			if(StringUtils.isBlank(pnr_no)){// 如果入库时不存在PNR则通过订单表查询
				JpKhdd t = new JpKhdd();
				t.setDdbh(ddbh);
				t.setShbh(one.getShbh());
				JpKhdd jpKhdd=jpKhddImpl.getKhddByDdbh(t);
				pnr_no = jpKhdd.getXsPnrNo();
			}
			orderBean.getKhddMap().put("XS_PNR_NO",pnr_no);
			ptlb.setPnrNo(pnr_no);
			ptlb.setDdbh(ddbh);
			if("1".equals(iftbzt)){//TB同步状态的订单表示第一次已经入库过，所以再入库时不能触发后续业务。
				return;
			}
			//入库后业务
			toDbAfter(orderBean,one,ptlb);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("订单入库失败，失败原因："+e.getMessage());
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
	 * 入库前处理
	 * @return
	 */
	private void toDbBefore(OrderBean orderBean,JpDdsz one,JpPtLog ptlb){
		Map<String,String> khddMap = orderBean.getKhddMap();
		String xs_pnr_no = khddMap.get("XS_PNR_NO");
		if(StringUtils.startsWith(xs_pnr_no, "O")||"000000".equals(xs_pnr_no)){//假编码入库时不传
			khddMap.put("XS_PNR_NO", "");
			ptlb.setPnrNo(xs_pnr_no);
		}
		orderBean.getKhddMap().put("XGLY", one.getWdmc() + "(" + one.getWdpt() + ")供应平台导单");
	}
	private void toDbAfter(OrderBean orderBean,JpDdsz one,JpPtLog ptlb){
		Map<String,String> khddMap = orderBean.getKhddMap();
		List<Map<String,String>> cjrList = orderBean.getCjrList();
		String ddbh = khddMap.get("VEDSBDDBH");
		String wbdh = khddMap.get("WBDH");
		String pnr = khddMap.get("XS_PNR_NO");
		boolean ifzspnr = true;//是否真实PNR
		if((StringUtils.startsWith(pnr, "O")||StringUtils.length(pnr)>6)){
			ifzspnr = false;
		}
		//携程业务
		if(PlatCode.XC.equals(one.getWdpt())&&ifzspnr){//只有真实PNR才能自动授权		
			CtripGy ctripGy = (CtripGy)JpddGySuper.getJpddGySuper(one);
			String officeNo = one.getSqoffice();
			String cpdh = khddMap.get("CPDH");
			try {
				//PNR授权
				ptlb.add("携程订单自动授权");
				String[] retZt = ctripGy.PnrPermission(wbdh, cpdh, officeNo, pnr, ptlb);
				if(!"0".equals(retZt[0])){
					ptlb.add("自动授权操作失败："+retZt[1]);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ptlb.add("自动授权操作失败："+e.getMessage());
			}
			//认领
			if("1".equals(one.getDdSfqr())){
				try {
					ptlb.add("携程订单入库自动认领");
					ctripGy.IBAssignTo(wbdh, cpdh, "1", ptlb);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ptlb.add("携程订单入库自动认领失败："+e.getMessage());
				}
			}
		}
		//淘宝自动出票业务 注册扫描淘宝出票信息job
		String tmp_zcsmjob = StringUtils.trimToEmpty(orderBean.getKhddMap().get("TMP_ZCSMJOB"));
		if(PlatCode.TB.equals(one.getWdpt())&&"1".equals(tmp_zcsmjob)){//淘宝自动出票订单不执行全自动出票
			ptlb.add("该订单属于淘宝自动出票订单！不执行全自动出票！");
			ptlb.add("注册扫描淘宝票号业务job");
			try {
				qrtzServiceImpl.add("10005", wbdh+"_"+one.getShbh());
			} catch (Exception e) {
				e.printStackTrace();
				ptlb.add("注册扫描淘宝票号业务job失败，请转手工回填票号。");
			}
		}
		//自动PAT业务
		JpKhdd jpKhdd = new JpKhdd();
		jpKhdd.setDdbh(ddbh);
		jpKhdd = jpKhddImpl.getEntityById(jpKhdd);
		boolean ifpat = false;
    	String sfzdpat = StringUtils.trimToEmpty(one.getDdZdpat());
		if(jpKhdd != null && "0".equals(jpKhdd.getGngj())){
			ptlb.add("国际票订单，不自动PAT");
		}else if("1".equals(sfzdpat)) {
			ptlb.add("所有订单自动PAT");
			ifpat = true;
		}else if("2".equals(sfzdpat)){
			ptlb.add("账单价为0自动PAT");
			if(jpKhdd.getXsZdj()==0l){
				ifpat = true;
			}
		}else if("3".equals(sfzdpat)){
			ptlb.add("根据政策代码自动PAT");
			String zcdmpat = one.getDdZcdmpat();
			ptlb.add("按订单中政策代码进行PAT，设置的字符串为:"+zcdmpat);
			String wd_zcdm = StringUtils.trimToEmpty(khddMap.get("WD_ZCDM"));
			ptlb.add("该订单政策代码（WD_ZCDM）为:"+wd_zcdm);
			if(StringUtils.isBlank(zcdmpat)){
				ptlb.add("设置的字符串为空，不执行自动PAT");
			}else{
				String[] tmpStrArr = zcdmpat.split(",");			
				for(int j=0;j<tmpStrArr.length;j++){
					if(wd_zcdm.indexOf(tmpStrArr[j])>-1){
						ifpat = true;
						break;
					}
				}
			}
		}
		//检查NO位时限
    	String nowsx = orderBean.getKhddMap().get("TMP_ADTK");
		if(StringUtils.isNotBlank(nowsx)){
			ptlb.add("接口返回了ADTK时间直接用");
			ptlb.add("NO位时限为："+nowsx);
			logger.error("NO位时限为："+nowsx);
		}
		Pnr pnrObj = null;
		if(ifpat&&ifzspnr){//调用PAT接口,并修改订单
	    	Map<String,Double> patPriceMap = new HashMap<String, Double>();
	    	String cjrlxTmp = "";
	    	for(int i=0;i< cjrList.size();i++){
	    		String cjrlx = cjrList.get(i).get("CJRLX");
	    		if(cjrlxTmp.indexOf(cjrlx)==-1){
	    			pnrObj = getPnrPat(pnr,cjrlx,one,ptlb);
	    			if(pnrObj!=null){
						String patnr = pnrObj.getPat();
				    	ptlb.add("PAT内容为："+patnr);
				    	logger.error("PAT内容为："+patnr);
				    	String pj = PNRPAT2Parser.parsePatTopjNormal(patnr).split("_")[0];
				    	double patPrice = NumberUtils.toDouble(pj,0);
				    	if(patPrice>0){
				    		patPriceMap.put(cjrlx, patPrice);
				    	}
	    			}else{
				    	ptlb.add("执行RT/PAT失败");
				    	logger.error("执行RT/PAT失败");
	    			}
	    		}
	    		cjrlxTmp += cjrlx+",";
	    	}
	    	if(patPriceMap.size()>0){//修改账单价
				updateDd(patPriceMap,orderBean,one,ptlb,ddbh);
	    	}
	    	if(pnrObj!=null&&StringUtils.isBlank(nowsx)){//直接从PNR内容中获取NOWSX
		    	nowsx = pnrObj.getNowsx();
		    	ptlb.add("解析PNR获得的NO位时限为："+nowsx);
		    	logger.error("解析PNR获得的NO位时限为："+nowsx);
	    	}
		}
		if("1".equals(one.getDdNwsx())&&StringUtils.isBlank(nowsx)&&ifzspnr){//是否检查NO位时限
			ptlb.add("开始自动检查NO位时限");				
			//调用NO为时限接口，并修改订单
			pnrObj = getPnrPat(pnr,cjrList.get(0).get("CJRLX"),one,ptlb);
	    	if(pnrObj!=null){
		    	nowsx = pnrObj.getNowsx();
		    	ptlb.add("解析PNR获得的NO位时限为："+nowsx);
	    	}
		}
		JpKhdd t = new JpKhdd();
		t.setDdbh(ddbh);
    	if(StringUtils.isNotBlank(nowsx)){//修改订单NO位时限
    		ptlb.add("修改订单NO位时限为"+nowsx);
    		t.setNosj(VeDate.formatToDate(nowsx, "yyyy-MM-dd hh:mm:ss"));
    	}
		if (pnrObj != null) {//将pnr内容修改进
    		ptlb.add("修改订单PNR内容为"+pnrObj.getPnr_lr()+"PNR状态为："+pnrObj.getPnr_zt()+";航司大编码为："+pnrObj.getBigPnrno());
    		t.setPnrLr(pnrObj.getPnr_lr());
    		t.setPatLr(pnrObj.getPat());
    		t.setXsPnrZt(pnrObj.getPnr_zt());
    		t.setCgPnrZt(pnrObj.getPnr_zt());
    		t.setCgHkgsPnr(pnrObj.getBigPnrno());
    		t.setXsHkgsPnr(pnrObj.getBigPnrno());
    		if("NO".equals(StringUtils.trimToEmpty(pnrObj.getPnr_zt()).toUpperCase())){//如果做RT时发现PNR NO位了；这里要调用补位接口
    			jpzdbw(pnrObj, one, ptlb, ddbh);
    		}
    	}
    	if(t.getNosj()!=null||StringUtils.isNotBlank(t.getPnrLr())){
    		try {
    			jpKhddImpl.getMyBatisDao().updateByPrimaryKeySelective(t);
    		} catch (Exception e) {
    			ptlb.add("修改订单信息失败"+e.getMessage());
    		}
    	}
    	
		
		final String _ddbh = ddbh;
		final String _shbh = one.getShbh();
		// 全自动出票
		String ifautocp = StringUtils.trimToEmpty(khddMap.get("IFAUTOCP"));// 等于false表示不走全自动出票
		if (!"false".equals(ifautocp)) {// 不走全自动出票的订单有：1淘宝自动出票 2同步外部状态的淘宝订单（因为已经入库过一次）

			if (!"1".equals(one.getDdautocp())) {
				ptlb.add("导单立即触发全自动");

				if ("1".equals(one.getDdGngj())) {// 国际

				} else {// 国内
					ptlb.add("开始全自动出票【国内】");
					new Thread() {
						@Override
						public void run() {
							try {
								jpzdcpWork.start(_ddbh, _shbh);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}.start();
				}
			} else {
				ptlb.add("导单不触发全自动，出票通过扫描待出票实现");
			}
		}
 
    	
    	
    	//自有政策扣位
    	String zc_qd = orderBean.getKhddMap().get("ZC_QD");
    	String zc_tfid = orderBean.getKhddMap().get("ZC_TFID");
    	if("1000201".equals(zc_qd)&&StringUtils.isNotBlank(zc_tfid)){//当时自有政策并且政策id不为空时  调用自有政策扣位接口
    		zyzckw(one.getShbh(), zc_tfid, orderBean.getCjrList().size(), ptlb);
    	}
	}
	private void zyzckw(String shbh,String platid,int zws,JpPtLog ptlb){
	    try {
//			String url = "http://zyveds.abctrip.net/veds/stock/otherpolicy/income";
			String url = B2bpayutil.getPayparam("ZYVEDS_URL") + "/veds/stock/otherpolicy/income";//做成配置
			Map<String,String> params = new HashMap<String, String>();
			params.put("fb_shbh", shbh);//商户编号
			params.put("platid", platid);//政策id
			params.put("zws", zws+"");//座位数
			ptlb.add("调用自有政策扣位接口:"+url+"参数:"+params.toString());
			String rtnXm = HttpClientUtil.doPost(url, params, "utf-8");
			ptlb.add("调用自有政策扣位接口返回:"+rtnXm);
		} catch (Exception e) {
			ptlb.add("调用自有政策扣位接口失败,错误信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	//调用PNRPAT2接口
	private Pnr getPnrPat(String pnrno,String cjrlx,JpDdsz jpDdsz, JpPtLog ptlb){
		JpPz jppz = jppzImpl.getJpPzByShbh(jpDdsz.getShbh());
		if (jppz == null) {
			ptlb.add("没有找到PID请求配置，无法做PAT和RT指令");
			return null;
		}
		ptlb.add("执行RTPNR :"+pnrno);
		Shyhb yhb = shyhbServiceImpl.getMyBatisDao().getShyhb(jpDdsz.getShbh(), jpDdsz.getDdUserid());
		return DataUtils.getPnrPat(pnrno, cjrlx, jppz, yhb, "");
	}
	//调用PNRPAT2接口
	private void jpzdbw(Pnr pnrObj,JpDdsz jpDdsz, JpPtLog ptlb,String ddbh){
		ptlb.add("pnr状态为NO，自动调用补位接口");
		JpPz jppz = jppzImpl.getJpPzByShbh(jpDdsz.getShbh());
		if (jppz == null) {
			ptlb.add("没有找到PID请求配置，无法做PAT和RT指令");
			return;
		}
		Shyhb yhb = shyhbServiceImpl.getMyBatisDao().getShyhb(jpDdsz.getShbh(), jpDdsz.getDdUserid());
		try {
			jpzdbwServiceImpl.jpzdbw(pnrObj, jppz, ptlb, yhb.getPidyh(), ddbh);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void updateDd(Map<String,Double> patPriceMap,OrderBean orderBean,JpDdsz jpDdsz,JpPtLog ptlb,String ddbhrtn) {
		try {
			Map<String, Object> otherMap =new HashMap<String, Object>();
			otherMap.put("XGLY", "导单自动PAT修改订单价格");
			List<Map<String, String>> cjrList = orderBean.getCjrList();
			List<Map<String,String>> newCjrList = new ArrayList<Map<String,String>>();
			for(int i=0;i<cjrList.size();i++){
				Map<String,String> tmpcjr = cjrList.get(i);
				Map<String,String> newCjr = new HashMap<String, String>();
				newCjr.put("XS_TAX", tmpcjr.get("XS_TAX"));
				newCjr.put("XS_JSF", tmpcjr.get("XS_JSF"));
				newCjr.put("XS_ZDJ", patPriceMap.get(tmpcjr.get("CJRLX"))+"");
				newCjr.put("XS_PJ", tmpcjr.get("XS_PJ"));
				newCjr.put("XS_JE", tmpcjr.get("XS_JE"));
				newCjr.put("CJRLX", tmpcjr.get("CJRLX"));
				newCjr.put("CJR", tmpcjr.get("CJR"));
				newCjr.put("ZJHM", tmpcjr.get("ZJHM"));
				newCjrList.add(newCjr);
			}
			OrderBean neworderBean = new OrderBean();
			neworderBean.setCjrList(newCjrList);
			Map<String, String> khddMap = new HashMap<String, String>();
			khddMap.put("DDBH", ddbhrtn);
			khddMap.put("XGYH", jpDdsz.getDdUserid());
			BookOrder bean = new BookOrder(orderBean);
			ptlb.add("修改订单XML："+bean.getOrderXml());
			logger.error("修改订单XML："+bean.getOrderXml());
			procPkgPnrServiceImpl.insertJpKhdd(bean,null);
			logger.error("修改订单返回："+bean.getResult());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
    }
}
