package cn.vetech.vedsb.platpolicy.b2b.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.framework.bookticket.b2cnew.action.PaymentInfo;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZfzhServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.platpolicy.jzcp.B2bnewException;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bClient;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.request.B2bRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.OrderInfo;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.PassengerInfo;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.SysUtils;

@Service
public class B2bsession {
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpB2bZfzhServiceImpl jpB2bZfzhServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;

	public JpKhdd getjpKhdd(Shyhb user, String ddbh) {
		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(user.getShbh(), ddbh);
		// 航空公司大编码为空 或者是JR 并且JR的pnr内容或pat内容为空
		if (StringUtils.isBlank(jpKhdd.getCgHkgsPnr()) || ("JR".equals(jpKhdd.getHkgs()) && (StringUtils.isBlank(jpKhdd.getPnrLr()) || StringUtils.isBlank(jpKhdd.getPatLr())))) {
			jpKhdd = jpKhddServiceImpl.khddRtPnr(user, ddbh);
		}
		return jpKhdd;
	}

	/**
	 * 入库和支付队列
	 * 
	 * @param b2bmsg
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */

	public B2bRequest getB2bRequest(Shyhb user, JpKhdd jpKhdd) throws B2bnewException {
		String ddbh = jpKhdd == null ? "" : jpKhdd.getDdbh();
		List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getShbh());
		List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getShbh());
		if (jpKhdd == null || cjrList == null || cjrList.size() == 0 || hdList == null || hdList.size() == 0) {
			throw new B2bnewException("订单信息不全");
		}
		boolean hasChdOrInf = false;
		for (int i = 0; i < cjrList.size(); i++) {
			JpKhddCjr onecjr = cjrList.get(i);
			if (!"1".equals(onecjr.getCjrlx())) {
				hasChdOrInf = true;
				break;
			}
		}
		// if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
		// throw new Exception("B2B代购只支持成人单程");
		// }
		JpKhddHd hd = hdList.get(0);
		// //取JpPtzcZh表设置
		String hkgs = jpKhdd.getHkgs();

		/* 联系人信息 */
		JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), hkgs, "720102");
		if (jpB2bHkgs == null) {
			throw new B2bnewException("没有找到航司配置信息，请先配置");
		}
		// String lxrsjqz = jpB2bHkgs.getLxrsjqz();//联系人手机取值方式
		// String lxrptsj = jpB2bHkgs.getLxrptsj();
		String linker = jpB2bHkgs.getLxr();
		String linkMobile = jpB2bHkgs.getSj();
		String linkMail = jpB2bHkgs.getEmail();
		String mrwz = jpB2bHkgs.getMrwz();
		String spydm = jpB2bHkgs.getSpydm();
		String dkhbh = jpB2bHkgs.getDkhbm();

		// 组织请求参数
		/* 回调通知地址 */
		String businessIp = SysUtils.getTicketUrl() + "/webcontent/b2btz/cptz";
		B2bRequest order = new B2bRequest();

		order.setPnrnr(jpKhdd.getPnrLr());
		order.setPat(jpKhdd.getPatLr());

		order.setHkgspnr(jpKhdd.getCgHkgsPnr());
		order.setUserid(user.getBh());
		order.setOutOrderNo(ddbh);// ASMS订单编号
		// order.setAirwaysAccount(airwaysAccount);// 航空公司登录账号
		// order.setAirwaysPwd(airwaysPwd);// 航空公司登录密码
		order.setAirways(hkgs);// 航空公司
		order.setFlightNo(hd.getCgHbh());// 航班号
		// double oneprice = Arith.div(NumberUtils.toDouble(price), cjrList.size());

		// order.setPayInfo(bytes);// 支付信息
		// order.setAutopay(autopay);// 自动支付 1 自动0手动

		order.setLinker(linker);// 联系人
		order.setLinkMobile(linkMobile);// 联系人电话
		order.setLinkMail(linkMail);// 联系人邮件
		order.setNotifyurl(businessIp);// 商户IP+端口
		order.setPnr(jpKhdd.getCgPnrNo());// pnr编码

		// B2B信息
		order.setMrwz(mrwz);// 默认网址
		order.setSpydm(spydm);// 售票员代码

		// order.setDkhbh(sfxyh);// 大客户编号
		// order.setOffice(office);// 票号回填时传回来的office

		order.setPnrnr(jpKhdd.getPnrLr());// PNR内容;
		order.setPat(jpKhdd.getPatLr());// PAT内容;
		// order.setIfzhzg(ifzhzg);
		// order.setB2bpolicyid(b2bpolicyid);// B2B提取政策时返回的政策ID;
		// order.setSync("0");// 表示异步请求航司，快速返回是否下单代购系统成功

		return order;

	}

	public PaymentInfo getPaymentInfo(Shyhb user, String asmszfzhid, String payhand, String zflx) throws B2bnewException {
		PaymentInfo pmi = new PaymentInfo();
		if ("1".equals(payhand)) {
			pmi.setZflx(zflx);
			pmi.setPayhand(payhand);
			pmi.setZfxx7(asmszfzhid);//这里约定 如果是手动支付，asmszfzhid传的是cg_zfkm;
			// cg_zfkm = zfzh;
			// zfzh = "";
		} else {
			JpB2bZfzh b2bzfzhBean = new JpB2bZfzh();
			b2bzfzhBean.setId(asmszfzhid);
			b2bzfzhBean.setShbh(user.getShbh());
			List<JpB2bZfzh> b2bzfzhList = jpB2bZfzhServiceImpl.getMyBatisDao().select(b2bzfzhBean);
			if (b2bzfzhList == null || b2bzfzhList.size() == 0) {
				throw new B2bnewException("获取支付账号失败");
			}
			JpB2bZfzh b2bzfzh = b2bzfzhList.get(0);
			if (StringUtils.isBlank(b2bzfzh.getZfxx7())) {
				throw new B2bnewException("自动支付账号没有设置对应科目");
			}
			String cg_zfkm = b2bzfzh.getZfxx7();
			pmi.setPayhand(payhand);
			pmi.setCjtime(b2bzfzh.getCjtime());
			pmi.setCompid(b2bzfzh.getShbh());
			pmi.setId(b2bzfzh.getId());
			pmi.setSfkq(b2bzfzh.getSfkq());
			pmi.setSfqy(b2bzfzh.getSfqy());
			pmi.setUserid(b2bzfzh.getYhbh());
			pmi.setZflx(b2bzfzh.getZflx());
			pmi.setSfsyzzh(b2bzfzh.getSfsyzzh());
			pmi.setZfzh(b2bzfzh.getZfzh());
			pmi.setZfzzh(b2bzfzh.getZfzzh());
			pmi.setZfxx1(b2bzfzh.getZfxx1());
			pmi.setZfxx2(b2bzfzh.getZfxx2());
			pmi.setZfxx3(b2bzfzh.getZfxx3());
			pmi.setZfxx4(b2bzfzh.getZfxx4());
			pmi.setZfxx5(b2bzfzh.getZfxx5());
			pmi.setZfxx6(b2bzfzh.getZfxx6());
			pmi.setZfxx7(b2bzfzh.getZfxx7());
			pmi.setZfxx8(b2bzfzh.getZfxx8());
			pmi.setZfxx9(b2bzfzh.getZfxx9());
			pmi.setZfxx10(b2bzfzh.getZfxx10());
			pmi.setZfxx11(b2bzfzh.getZfxx11());
		}
		return pmi;
	}

	public final static ExecutorService AUTOCPSERVICE = Executors.newFixedThreadPool(50);

	public void addSAVEPAYMAP(Shyhb user, B2bRequest b2bRequest, JpKhdd jpKhdd, String asmspaykind, String asmszfzhid, String asmspolicyindex, String cg_zfkm, Map<String, String[]> request) throws B2bnewException {
		// B2bRequest b2bRequest = getB2bRequest(user, ddbh);
		// PaymentInfo getPaymentInfo = getPaymentInfo(user.getShbh(), asmszfzhid, autopay, paymethod);
		Runnable command = new CpCallable(user, b2bRequest, jpKhdd, asmspaykind, asmszfzhid, asmspolicyindex, cg_zfkm, request);

		AUTOCPSERVICE.execute(command);

	}

	class CpCallable implements Runnable {
		Shyhb user;
		B2bRequest b2bRequest;
		String asmspaykind;
		String asmszfzhid;
		String asmspolicyindex;
		String cg_zfkm;
		Map<String, String[]> request;
		JpKhdd jpKhdd;

		public CpCallable(Shyhb user, B2bRequest b2bRequest, JpKhdd jpKhdd, String asmspaykind, String asmszfzhid, String asmspolicyindex,String cg_zfkm,Map<String, String[]> request) {
			this.user = user;
			this.b2bRequest = b2bRequest;
			this.asmspaykind = asmspaykind;
			this.asmszfzhid = asmszfzhid;
			this.asmspolicyindex = asmspolicyindex;
			this.cg_zfkm = cg_zfkm;
			this.request = request;
			this.jpKhdd = jpKhdd;
		}

		@Override
		public void run() {
			try {
				b2bRequest.setB2bpolicyid(asmspolicyindex);
				B2bOrderResponse orderResponse = VetpsPurchasB2bClient.b2bSubmitOrder(b2bRequest, user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", b2bRequest.getOutOrderNo()));
				int status = orderResponse.getStatus();// 0成功 -1失败 .
				if (0 == status) {
					
				} else {
					//rtnmsg = "提交代购返回失败,错误原因：" + orderResponse.getErrorMessage();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void saveJpCgdd(String ddbh,Shyhb user,B2bOrderResponse orderResponse){
		String airwaysAccount = orderResponse.getStateInfo().getAirwaysAccount();//航司账号
		String zflx = orderResponse.getOrderInfo().getPayInfo().getZflx();
		String cg_zfkm = orderResponse.getOrderInfo().getPayInfo().getZfxx7();
		String zfzh = orderResponse.getOrderInfo().getPayInfo().getZfzh();
		double cwpj = orderResponse.getOrderInfo().getTotalPrice();
		double zfje = orderResponse.getOrderInfo().getPaymentMoney();
		String hkgsddbh = orderResponse.getOrderInfo().getAirwaysOrderNo();
		
		// TODO 写本地采购订单表 jp_cgdd
		JpCgdd _jpCgddBean = jpCgddServiceImpl.getJpCgddByCgly(ddbh, "B2B", hkgsddbh);
		if(_jpCgddBean!=null){//当存在支付信息的时候不记录
			return;
		}
		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(user.getShbh(), ddbh);
		List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getShbh());
		JpKhddHd hd = hdList.get(0);
		JpCgdd jpCgddBean = new JpCgdd();
		jpCgddBean.setId(Identities.randomLong() + "");
		jpCgddBean.setDdbh(jpKhdd.getDdbh());
		jpCgddBean.setZt("0");// 下单C站代购时，修改采购订单状态为等待支付
		jpCgddBean.setCjUserid(user.getBh());
		jpCgddBean.setCjdatetime(VeDate.getNow());
		jpCgddBean.setSubmitParam("");
		jpCgddBean.setOrderhkgs(jpKhdd.getHkgs());
		jpCgddBean.setHkgs(jpKhdd.getHkgs());
		jpCgddBean.setPnrno(jpKhdd.getCgPnrNo());
		jpCgddBean.setHkgsPnrno(jpKhdd.getCgHkgsPnr());
		jpCgddBean.setPaykind(zflx);
		jpCgddBean.setHkgszh(airwaysAccount);
		jpCgddBean.setHbh(hd.getCgHbh());
		jpCgddBean.setCw(hd.getCgCw());
		jpCgddBean.setCwpj(cwpj);
		jpCgddBean.setZfje(zfje);
		jpCgddBean.setZfzh(zfzh);
		jpCgddBean.setCgZfkm(cg_zfkm);
		jpCgddBean.setIfxepnr("1");// 不XEPNR
		jpCgddBean.setCgly("B2B");// 采购来源取ve_class表 id字段 lb='10014'
		jpCgddBean.setCgdw("B2B");//// 采购单位取ve_class表 ywmc字段 lb='10014'
		jpCgddBean.setShbh(jpKhdd.getShbh());
		jpCgddBean.setJyzt("0");
		jpCgddBean.setDgdh(hkgsddbh);
		jpCgddBean.setHkgsDdbh(hkgsddbh);
		try {
			jpCgddServiceImpl.insert(jpCgddBean);
		} catch (Exception e) {
		}
	}
	public String zjp(OrderInfo b2bOrder,JpCgdd jpCgdd){
		Shyhb user = shyhbServiceImpl.getMyBatisDao().getShyhb(jpCgdd.getShbh(), jpCgdd.getCjUserid());
		List<PassengerInfo> cjrlist = b2bOrder.getPassengerInfos();//乘机人信息
		Map<String,Object> publicParam=new HashMap<String, Object>();
		Map<String,Object> publicMap=new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (PassengerInfo cjr : cjrlist) {// 乘机人信息段
			String cjrxm = StringUtils.trimToEmpty(cjr.getPassenger());
			String ph = StringUtils.trimToEmpty(cjr.getTicketNo());
			if(StringUtils.length(ph)==13){
				ph = StringUtils.substring(ph, 0,3)+"-"+StringUtils.substring(ph,3);
			}
			Map<String,Object> tk = new HashMap<String, Object>();
			tk.put("CJR", cjrxm);
			tk.put("TKNO", ph);
			list.add(tk);
		}
		publicParam.put("TK", list);
		publicMap.put("DDBH", jpCgdd.getDdbh());
		publicMap.put("CG_DDBH", jpCgdd.getDgdh());
		publicMap.put("CGZFKM", jpCgdd.getCgZfkm());
		publicMap.put("B2BZH", b2bOrder.getAirwaysAccount());
		publicMap.put("OFFICEID", "");
		publicMap.put("AGENT", "");
		publicMap.put("PJ_XSJ", b2bOrder.getTotalPrice());
		publicMap.put("ZFJE", b2bOrder.getAgencyMoney());
		publicMap.put("CP_USERID", user.getBh());
		publicMap.put("CP_DEPTID", user.getShbmid());
		publicMap.put("SHBH", user.getShbh());
		publicMap.put("WCPDW", jpCgdd.getCgdw());
		publicMap.put("FKDH", "");
		publicMap.put("CPQD", "B2B");
		publicMap.put("CGLY", "B2B");
		publicMap.put("DATAFROM", "B2B代购出票");
		publicParam.put("PUBLIC", publicMap);
		LogUtil.getDgrz("B2B",jpCgdd.getDdbh()).error("B2B转机票入参:"+publicParam);
		pkgZjpSgServiceImpl.fzjpb2b(publicParam);
		LogUtil.getDgrz("B2B",jpCgdd.getDdbh()).error("B2B转机票返回:"+publicParam);
		Object  resultO= publicParam.get("result");
		resultO = resultO==null?publicParam.get("RESULT"):resultO;
		int result = resultO == null ? -1 : NumberUtils.toInt(resultO + "", -1);
		if(-1 == result){
			Object per=publicParam.get("perror");
			per = per==null?publicParam.get("PERROR"):per;
			String error = StringUtils.trimToEmpty((String)per);
			if(StringUtils.isBlank(error)){
				error = "B2B转机票失败";
			}
			LogUtil.getDgrz("B2B",jpCgdd.getDdbh()).error("B2B转机票返回:"+result + "===" + error);
			return error;
		}
		return "";
	}
}
