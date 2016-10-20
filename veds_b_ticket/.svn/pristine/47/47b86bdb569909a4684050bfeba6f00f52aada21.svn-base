package cn.vetech.vedsb.platpolicy.taobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpzdcpWork;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;
@Service
public class TaoBaoGyAutoCpService {
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddKzServiceImpl jkksImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	@Autowired
	private JpzdcpWork jpzdcpWork;
	public int TBOrderDetailByDddh(String wbdh_shbh) {
		if(StringUtils.isBlank(wbdh_shbh)||wbdh_shbh.split("_").length==1){
			return -1;
		}
		String[] strArr = wbdh_shbh.split("_");
		String wbdh = strArr[0];
		String shbh = strArr[1];
		int rtn = _TBOrderDetailByWbdh(wbdh,shbh);
		if(rtn == 1){
			//转机票成功后停止job执行
			rtn = -1;
		}
		return rtn;
	}
	private int _TBOrderDetailByWbdh(String wbdh,String shbh){
		List<JpKhdd> jpKhddList = jpKhddServiceImpl.getKhddByWbdh(shbh,wbdh);
		if(CollectionUtils.isEmpty(jpKhddList)){//没有查到订单的不执行
			LogUtil.getGyrz("TB",wbdh).error("没有查到订单，结束任务转人工处理");
			return -1;
		}
		JpKhdd jpKhdd = jpKhddList.get(0);
		String wdid = jpKhdd.getWdid();
		
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		jpDdsz = jpDdszImpl.getEntityById(jpDdsz);
		if(jpDdsz==null||!PlatCode.TB.equals(jpDdsz.getWdpt())){//如果查不到导单设置，或者导单设置没有开启,或者不是淘宝网店宝
			LogUtil.getGyrz("TB",wbdh).error("网店导单设置未开启，结束本次扫描");
			return 0;
		}
		String ddzt = jpKhdd.getDdzt();
		if(StringUtils.isNotBlank(ddzt)&&"3,4,5".indexOf(ddzt)>-1){
			LogUtil.getGyrz("TB",wbdh).error("该订单已经完成或者已经取消，注销该订单扫描job");
			return -1;
		}
//		Date ddsj = jpKhdd.getDdsj();
//		Date tmpdate = VeDate.getPreDay(VeDate.getNow(), -1);
//		if(ddsj.before(tmpdate)){//导单超过一天的不执行,表示出票失败
//			LogUtil.getGyrz("TB",ddbh).error("导单超过一天，视为出票失败，结束任务转人工处理");
//			updateKhddDdztByDdbh(ddbh, shbh, jpDdsz.getDdUserid(), "2", "1");//淘宝出票失败修改修改外部出票状态为  1
//			return -1;
//		}
		//找出系统中外部单号相同的订单
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("shbh",shbh);
		param.put("wdbh",wbdh);
		
		List<OrderBean> list = null;

		TaobaoGy jpddGy = new TaobaoGy(jpDdsz);
		JpPtLog ptlb = new JpPtLog();
		String msg = "";
		try {
			list = jpddGy.getBdetailByWbdh(wbdh, ptlb);
			msg = ptlb.getInfo();
			LogUtil.getGyrz("TB",wbdh).error(ptlb.getInfo());
		} catch (Exception e) {
			LogUtil.getGyrz("TB",wbdh).error("通过外部单号查询网店订单详情报错：",e);
		}
		if(CollectionUtils.isEmpty(list)){//没有解析出来订单详情时 结束本次扫描
			if(msg.indexOf("App Call Limited")>-1){
				LogUtil.getGyrz("TB",wbdh).error("没有获取到订单详情,结束本次扫描");
				return 0;
			}
			LogUtil.getGyrz("TB",wbdh).error("没有获取到订单详情时，停止任务，转人工处理");
			return -1;
		}
		return canzjp(list, jpKhdd, jpDdsz);
	}
	private int canzjp(List<OrderBean> list,JpKhdd jpKhdd,JpDdsz jpDdsz){
		OrderBean orderBean = list.get(0);
		String iftbcpsb = VeStr.getValue(orderBean.getKhddMap(),"IFTBCPSB");//是否淘宝出票失败
		String cg_ddbh = VeStr.getValue(orderBean.getKhddMap(),"WBDH");
		if(!cg_ddbh.equals(jpKhdd.getCgDdbh())){
			updateKhddDdztByWbdh(jpKhdd.getWbdh(), jpDdsz.getShbh(), jpDdsz.getDdUserid(), "", "",cg_ddbh);//修改采购订单编号
		}
		if("1".equals(iftbcpsb)){
//			LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("淘宝返回出票失败，从新执行全自动出票");
			//修改订单表数据
			LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("淘宝返回出票失败，修改订单为已订座");
			updateKhddDdztByWbdh(jpKhdd.getWbdh(), jpDdsz.getShbh(), jpDdsz.getDdUserid(), "1", "3","");//淘宝出票失败修改订单状态为已订座，修改外部出票状态为出票失败3，并清空采购订单编号
//			final String _ddbh = ddbh;
//			final String _shbh = jpDdsz.getShbh();
//    		if("1".equals(jpDdsz.getDdGngj())){//国际
//    			
//    		}else{//国内
//    			LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("开始全自动出票【国内】");
//        		new Thread(){
//        			@Override
//        			public void run() {
//        				try {
//        					jpzdcpWork.start(_ddbh, _shbh);
//        				} catch (Exception e) {
//        					e.printStackTrace();
//        				}
//        			}
//        		}.start();
//    		}
			return -1;
		}
		if(!checkTkno(list)){
			LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("订单信息中存在未出票信息,结束本次扫描");
			return 0;
		}else{
			LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("开始转机票");
	        JpPtzcFzsz jpPtzcFzsz = jpPtzcFzszServiceImpl.genZfkmByDjm("2", jpKhdd.getShbh(), "10063971", PlatCode.TB);//10063971是支付宝对接码
	        if(jpPtzcFzsz==null){
	        	LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("请先配置淘宝支付对接码");
	        	return 0;
	        }
			String rtn = zjp(list,jpKhdd.getWbdh(),jpDdsz,jpPtzcFzsz);
			if(StringUtils.isBlank(rtn)){
				//修改扩展表数据 考虑到拆分订单一起转机票，这里修改数据时要一起修改
				updateKhddDdztByWbdh(jpKhdd.getWbdh(), jpDdsz.getShbh(), jpDdsz.getDdUserid(), "", "2", null);
				//订单表
				Map<String,Object> jpKhddParam = new HashMap<String, Object>();
				jpKhddParam.put("wbdh", jpKhdd.getWbdh());
				jpKhddParam.put("shbh", jpKhdd.getShbh());
				List<JpKhdd> jpKhddList = (List<JpKhdd>) jpKhddServiceImpl.selectJpByWbdh(jpKhddParam);
				if(CollectionUtils.isEmpty(jpKhddList)){
					return -1; 
				}
				List<String> ddbhs = new ArrayList<String>();
				for(int i=0;i<jpKhddList.size();i++){
					ddbhs.add(jpKhddList.get(i).getDdbh());
				}
				Map<String, Object> updateKzParam = new HashMap<String, Object>();
				updateKzParam.put("ddbhs", ddbhs);
				updateKzParam.put("shbh", jpDdsz.getShbh());
				updateKzParam.put("phhtzt", "1");//回填成功
				updateKzParam.put("phhtsbyy", "回填成功");
				try {
					jkksImpl.updateJpZtByDdbh(updateKzParam);
				} catch (Exception e) {
					LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("修改扩展表失败",e);
				}
				LogUtil.getGyrz("TB",jpKhdd.getWbdh()).error("转机票成功");
				//转机票成功修改jpCgdd表
				double price = NumberUtils.toDouble(orderBean.getKhddMap().get("TMP_XS_PJ"));
				double zfje = NumberUtils.toDouble(orderBean.getKhddMap().get("TMP_XS_JE"));
				String orderNo = orderBean.getKhddMap().get("WBDH");
				saveJpCgdd(jpKhdd, "1", jpDdsz, price, zfje, orderNo, jpPtzcFzsz);
				return 1;
			}
		}
		return 0;
	}
    private void saveJpCgdd(JpKhdd jpKhdd,String zfzt,JpDdsz jpDdsz,double price,double zfje,String orderNo,JpPtzcFzsz jpPtzcFzsz){
    	if(StringUtils.isBlank(zfzt)){
	    	zfzt = "1";
	    }
    	//TODO 写本地采购订单表 jp_cgdd
		JpCgdd jpCgddBean = new JpCgdd();
		jpCgddBean.setId(Identities.randomLong()+"");
		jpCgddBean.setDdbh(jpKhdd.getDdbh());
		jpCgddBean.setZt(zfzt);//下单淘宝代购时，修改采购订单状态为支付成功
		jpCgddBean.setCjUserid(jpDdsz.getDdUserid());
		jpCgddBean.setCjdatetime(VeDate.getNow());
		jpCgddBean.setSubmitParam("");
		jpCgddBean.setOrderhkgs(jpKhdd.getHkgs());
		jpCgddBean.setHkgs(jpKhdd.getHkgs());
		jpCgddBean.setPnrno(jpKhdd.getCgPnrNo());
		jpCgddBean.setHkgsPnrno(jpKhdd.getCgHkgsPnr());
		jpCgddBean.setPaykind("1");
		jpCgddBean.setHkgszh("");
		jpCgddBean.setHbh(jpKhdd.getCgHbh());
		jpCgddBean.setCw(jpKhdd.getCgCw());
		jpCgddBean.setCwpj(price);
		jpCgddBean.setZfzh("");
		jpCgddBean.setZfje(zfje);
	    jpCgddBean.setCgZfkm(jpPtzcFzsz.getXtZfkm());
	    jpCgddBean.setIfxepnr("1");//这里先暂时默认为1
	    jpCgddBean.setCgly("TB");//采购来源取ve_class表 id字段 lb='10014'
	    jpCgddBean.setCgdw(PlatCode.TB);////采购单位取ve_class表 ywmc字段 lb='10014'
	    jpCgddBean.setShbh(jpDdsz.getShbh());
	    jpCgddBean.setJyzt("1");
	    jpCgddBean.setDgdh(orderNo);
	    jpCgddBean.setHkgsDdbh(orderNo);
		try {
			jpCgddServiceImpl.insert(jpCgddBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	private String zjp(List<OrderBean> obList,String wbdh,JpDdsz jpDdsz,JpPtzcFzsz jpPtzcFzsz){
		Shyhb user = shyhbServiceImpl.getMyBatisDao().getShyhb(jpDdsz.getShbh(), jpDdsz.getDdUserid());
		Map<String,Object> publicParam=new HashMap<String, Object>();
		Map<String,Object> publicMap=new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		double xs_je_all = 0.0;
		double xs_pj_all = 0.0;
		double jjryhj = 0.0;
		for(int i=0;i<obList.size();i++){
			OrderBean orderBean = obList.get(i);
			List<Map<String,String>> cjrlist = orderBean.getCjrList();
			List<Map<String,String>> hdlist = orderBean.getHdList();
			String xs_pnr_no = orderBean.getKhddMap().get("XS_PNR_NO");
			for (Map<String,String> cjr : cjrlist) {// 乘机人信息段
				String cjrxm = StringUtils.trimToEmpty(cjr.get("CJR"));
				String ph = StringUtils.trimToEmpty(cjr.get("TKNO"));
				String zjhm = StringUtils.trimToEmpty(cjr.get("ZJHM"));
				double one_xs_je = NumberUtils.toDouble(cjr.get("XS_JE"));
				double one_xs_jsf = NumberUtils.toDouble(cjr.get("XS_JSF"));
				double one_xs_tax = NumberUtils.toDouble(cjr.get("XS_TAX"));
				double one_xs_pj = NumberUtils.toDouble(cjr.get("XS_PJ"));
				Map<String, Object> tk = new HashMap<String,Object>();
				
				tk.put("CJR", cjrxm);
				tk.put("TKNO", ph);
				tk.put("ZJHM", zjhm);
				Map<String,String> hdMap = hdlist.get(0);
				tk.put("HC", hdMap.get("CFCITY")+hdMap.get("DDCITY"));
				tk.put("CFSJ", hdMap.get("CFSJ"));
				tk.put("DDSJ", hdMap.get("DDSJ"));
				tk.put("HBH", hdMap.get("XS_HBH"));
				tk.put("CW", hdMap.get("XS_CW"));
				tk.put("CG_JE", one_xs_je);
				tk.put("CG_JSF", one_xs_jsf);
				tk.put("CG_TAX", one_xs_tax);
				tk.put("PNR", xs_pnr_no);
				list.add(tk);
				xs_je_all = Arith.add(xs_je_all, one_xs_je);
				xs_pj_all = Arith.add(xs_pj_all, one_xs_pj);
				jjryhj = Arith.sum(jjryhj,one_xs_jsf,one_xs_tax);
			}
		}
		double jsfl = Arith.div(Arith.sum(xs_pj_all,jjryhj, -xs_je_all), xs_pj_all, 2);
		String cg_ddbh = obList.get(0).getKhddMap().get("WBDH");
		
		publicParam.put("TK", list);
		publicMap.put("DDLY", "淘宝自动出票");
		publicMap.put("WBDH", wbdh);
		publicMap.put("SHBH", jpDdsz.getShbh());
		publicMap.put("CGLY", "TB");
		publicMap.put("CP_USERID", user.getBh());
		publicMap.put("CP_DEPTID", user.getShbmid());
		publicMap.put("CGZFKM", jpPtzcFzsz.getXtZfkm());
		publicMap.put("CG_DDBH", cg_ddbh);//
		publicMap.put("CGJE", xs_je_all);//
		publicMap.put("JSFL", jsfl);//
		publicMap.put("WBBM", "");
		publicMap.put("NEWPNR", "");
		publicMap.put("AGENT", "");
		publicMap.put("PTZCBS", jpDdsz.getWdpt());
		publicParam.put("PUBLIC", publicMap);
		LogUtil.getGyrz("TB",wbdh).error("淘宝自动出票转机票入参:"+publicParam);
		pkgZjpSgServiceImpl.fzjppd(publicParam);
		int result = (Integer)publicParam.get("result");
		if(-1 == result){
			String error = StringUtils.trimToEmpty((String)publicParam.get("perror"));
			if(StringUtils.isBlank(error)){
				error = "转机票失败";
			}
			LogUtil.getGyrz("TB",wbdh).error("淘宝自动出票转机票返回:"+result + "===" + error);
			return error;
		}
		LogUtil.getGyrz("TB",wbdh).error("淘宝自动出票转机票成功！");
		return "";
	}
	/**
	 * 淘宝出票失败修改订单表数据
	 * @param ddbh
	 * @param shbh
	 * @param yhbh
	 * @param ddzt
	 */
	private void updateKhddDdztByWbdh(String wbdh,String shbh,String yhbh,String ddzt,String sfwbcpz,String cgDdbh){
		JpKhdd jpkhdd = new JpKhdd();
		jpkhdd.setWbdh(wbdh);
		jpkhdd.setShbh(shbh);
		jpkhdd.setXgly("淘宝自动出票");
		jpkhdd.setXgyh(yhbh);
		jpkhdd.setDdzt(ddzt);//将订单状态修改为1;
		jpkhdd.setSfwbcpz(sfwbcpz);//将订单状态修改为1;
		jpkhdd.setCgDdbh(cgDdbh);
		try {
			jpKhddServiceImpl.updateJpKhddDdztByWbdh(jpkhdd);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.getGyrz("TB",wbdh).error("修改订单状态报错：",e);
		}
	}
	/**
	 * 只有当详情中存在全部票号时才允许转机票
	 * @param list
	 * @return
	 */
	private boolean checkTkno(List<OrderBean> list){//检查票号，只有全部出票才允许转机票
		for(int i=0;i<list.size();i++){//
			OrderBean orderBean = list.get(i);
			List<Map<String,String>> cjrlist = orderBean.getCjrList();
			for(int j=0;j<cjrlist.size();j++){
				String tkno = StringUtils.trimToEmpty(cjrlist.get(j).get("TKNO"));
				if(StringUtils.isBlank(tkno)||tkno.length()<10){
					return false;
				}
			}
		}
		return true;
	}
}
