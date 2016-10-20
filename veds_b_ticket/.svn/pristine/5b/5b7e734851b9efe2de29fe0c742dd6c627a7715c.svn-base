package cn.vetech.web.vedsb.jzcp;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.ObjectUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.AbstractBaseControl;
import org.vetech.core.modules.web.JsonBean;

import cn.vetech.framework.bookticket.b2cnew.action.PaymentInfo;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bDlzhServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.platpolicy.b2b.service.B2bsession;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.bean.B2bpolicyBean;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bClient;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.request.B2bRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bLoginResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bPolicyResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bStateResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.OrderInfo;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.PayInfo;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.StateInfo;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class B2bCpController extends AbstractBaseControl {

	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpB2bDlzhServiceImpl jpB2bDlzhServiceImpl;
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private B2bsession b2bsession;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	/**
	 * 引导到主页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String b2bmain(HttpServletRequest request) {
		return viewpath("b2bmain");
	}

	/**
	 * 导航
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		try {
			B2bOrderResponse orderResponse = VetpsPurchasB2bClient.getB2bOrderInfo(user.getShbh(), user.getBh(), ddbh, LogUtil.getDgrz("B2B", ddbh));
			if (orderResponse != null) {
				OrderInfo orderInfo = orderResponse.getOrderInfo();
				StateInfo stateInfo = orderResponse.getStateInfo();
				if (orderInfo != null && orderInfo.isTicketOk()) { // 转机票
					b2bzjp(ddbh, user, orderResponse);
					request.setAttribute("b2borderresponse", orderResponse);
					return viewpath("b2binfo");
				}
				// 如果登陆成功的
				if (stateInfo != null && stateInfo.isIsisloginok()) {
					if (orderInfo == null || StringUtils.isBlank(orderInfo.getAirwaysOrderNo())) {
						// 提取政策
						return b2bPolicy(request, response);
					} else if(orderInfo != null &&!"T".equals(orderInfo.getPayInfo().getSuccess())){//下单成功等待支付
						request.setAttribute("b2borderresponse", orderResponse);
						return b2bpayselect(request, response);
					} else if(orderInfo != null && "T".equals(orderInfo.getPayInfo().getSuccess())){//表示支付成功待出票
						b2bsession.saveJpCgdd(ddbh,user,orderResponse);
						request.setAttribute("b2borderresponse", orderResponse);
						return viewpath("b2binfo");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b2bLoginpage(request);
	}
	/**
	 * 弹出支付页面
	 */
	@RequestMapping
	public String paypage(HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		try {
			B2bOrderResponse orderResponse = VetpsPurchasB2bClient.getB2bOrderInfo(user.getShbh(), user.getBh(), ddbh, LogUtil.getDgrz("B2B", ddbh));
			request.setAttribute("b2borderresponse", orderResponse);
			return viewpath("payturnUtf");
		}catch(Exception e){
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(user.getShbh(), ddbh);
			request.setAttribute("jpKhdd", jpKhdd);
			request.setAttribute("errmsg", e.getMessage());
			return viewpath("b2berrpage");
		}
	}
	

	/**
	 * 刷新验证码
	 */
	@RequestMapping
	public @ResponseBody JsonBean getYzmImage(HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		String hkgs = request.getParameter("hkgs");
		String hszh = request.getParameter("hszh");
		JsonBean jb = null;
		try {
			B2bPolicyResponse bpresponse = VetpsPurchasB2bClient.getYzmImage(user.getShbh(), user.getBh(), ddbh, hkgs, hszh, LogUtil.getDgrz("B2B", ddbh));
			if ("1".equals(bpresponse.getRequireLogin())) {// 表示需要手工登录
				String image = bpresponse.getImage();// 验证码图片字符串
				if (StringUtils.isNotBlank(image)) {// 返回验证码路径表示登录失败
					String yzmpath = readImage(image);// 报错验证图片并返回路径
					jb = new JsonBean(yzmpath);
				}
			}
		} catch (Exception e) {
			jb = new JsonBean("", e.getMessage());
		}
		return jb;
	}

	/**
	 * 登录
	 */
	@RequestMapping
	public @ResponseBody JsonBean b2bLogin(HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		String hkgs = request.getParameter("hkgs");
		String hszh = request.getParameter("hszh");
		String hsmm = request.getParameter("hsmm");
		String yzm = request.getParameter("yzm");
		JsonBean jb = null;
		try {
			B2bLoginResponse bpresponse = VetpsPurchasB2bClient.b2bLogin(user.getShbh(), user.getBh(), ddbh, hkgs, hszh, hsmm, yzm, LogUtil.getDgrz("B2B", ddbh));
			if (bpresponse.getStatus() == 0) {
				// 登录成功后记录账号
				JpB2bDlzh dlzh = new JpB2bDlzh();
				dlzh.setBca("720102");
				dlzh.setHkgs(hkgs);
				dlzh.setShbh(user.getShbh());
				dlzh.setYhbh(user.getBh());
				dlzh.setZh(hszh);
				dlzh.setMm(hsmm);
				jpB2bDlzhServiceImpl.saveDlzh(dlzh);
			}

			jb = new JsonBean(bpresponse.getStatus(), bpresponse.getErrorMessage());
		} catch (Exception e) {
			jb = new JsonBean("", e.getMessage());
		}
		return jb;
	}

	/**
	 * 退出
	 */
	@RequestMapping
	public @ResponseBody JsonBean b2bLogout(HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		String hkgs = request.getParameter("hkgs");
		JsonBean jb = null;
		try {
			B2bLoginResponse bpresponse = VetpsPurchasB2bClient.b2bLogout(user.getShbh(), user.getBh(), ddbh, hkgs, "", LogUtil.getDgrz("B2B", ddbh));
			jb = new JsonBean(bpresponse.getStatus(), bpresponse.getErrorMessage());
		} catch (Exception e) {
			jb = new JsonBean("", e.getMessage());
		}
		return jb;
	}

	/**
	 * 登录页面
	 */
	public String b2bLoginpage(HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");

		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(user.getShbh(), ddbh);
		// 取航司登录账号信息
		List<JpB2bDlzh> dlzhList = jpB2bDlzhServiceImpl.getJpB2bDlzh(user.getShbh(), user.getBh(), jpKhdd.getHkgs());

		JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), jpKhdd.getHkgs(), "720102");

		request.setAttribute("jpKhdd", jpKhdd);
		request.setAttribute("dlzhList", dlzhList);
		request.setAttribute("jpB2bHkgs", jpB2bHkgs);
		return viewpath("b2bLoginpage");
	}

	/**
	 * 提取政策
	 */
	@RequestMapping
	public String b2bPolicy(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");
		String sfxyh = request.getParameter("sfxyh");
		try {
			JpKhdd jpKhdd = b2bsession.getjpKhdd(user, ddbh);
			request.setAttribute("jpKhdd", jpKhdd);

			B2bRequest brequest = b2bsession.getB2bRequest(user, jpKhdd);
			brequest.setDkhbh(sfxyh);// 大客户编码

			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setShbh(user.getShbh());
			shzfkm.setSffkkm("1");
			List<Shzfkm> shzfkmList = shzfkmServiceImpl.getShzfkmList(shzfkm);
			request.setAttribute("shzfkmList", shzfkmList);

			B2bPolicyResponse bpresponse = VetpsPurchasB2bClient.getB2bPolicy(brequest, user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", ddbh));
			if(bpresponse == null){
				throw new Exception("b2b代购提取政策接口返回为空！");
			}
			if (bpresponse.getStatus() == -1) {
				throw new Exception(bpresponse.getErrorMessage());
			}
			List<B2bpolicyBean> b2bpolicyBeanList = bpresponse.getB2bPolicyBean();
			request.setAttribute("b2bpolicyBeanList", b2bpolicyBeanList);
			
			//手动
			JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), jpKhdd.getHkgs(), "720102");
			JpB2bHkgsxx jpB2bHkgsxx = jpB2bHkgsxxServiceImpl.selectByHkgs_Bca(user.getShbh(), jpKhdd.getHkgs(), "720102");
			if(jpB2bHkgs==null||jpB2bHkgsxx==null){
				throw new Exception("没有查到航司基础数据");
			}
			request.setAttribute("jpB2bHkgs", jpB2bHkgs);
			List<String> zffsStr = jpB2bHkgs.getZffsList();//支持的手工支付方式
			request.setAttribute("zffsStr", zffsStr);
			request.setAttribute("jpB2bHkgsxx", jpB2bHkgsxx);
			request.setAttribute("jpKhdd",jpKhdd);
			request.setAttribute("yhbh", user.getBh());
			request.setAttribute("shzfkmList", shzfkmList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", e.getMessage());
			return viewpath("b2berrpage");
		}
		return viewpath("b2bpolicynew");
	}

	/**
	 * 下单到代购系统
	 * @return 
	 */
	@RequestMapping
	public @ResponseBody JsonBean b2bPnr2Db(HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");// 订单编号
		String payhand = request.getParameter("payhand");// 支付方式 1自动  其他手动
		String asmszfzhid = request.getParameter("zfzhid");// 支付账号id 如果是手工支付 这里传的是支付科目
		String zflx = request.getParameter("zflx");// 支付平台 1-9 对应 支付宝 财付通 。。。。易航宝
		String sfxyh = request.getParameter("sfxyh");// 大客户编号

		String office = StringUtils.trimToEmpty(request.getParameter("office"));
		String asmspolicyindex = StringUtils.trimToEmpty(request.getParameter("b2bpolicyid"));
		String price = StringUtils.trimToEmpty(request.getParameter("price"));// 总的采购价
		String ifzhzg = StringUtils.trimToEmpty(request.getParameter("ifzhzg"));

		JpKhdd jpKhdd = b2bsession.getjpKhdd(user, ddbh);
		JsonBean jb = new JsonBean("0", "");
		
		try {
			B2bRequest brequest = b2bsession.getB2bRequest(user, jpKhdd);
			PaymentInfo paymentinfo = b2bsession.getPaymentInfo(user, asmszfzhid, payhand, zflx);
			brequest.setPayInfo(ObjectUtil.obj2Byte(paymentinfo));
			b2bsession.addSAVEPAYMAP(user, brequest, jpKhdd, zflx, asmszfzhid, asmspolicyindex, paymentinfo.getZfxx7(), request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
			jb = new JsonBean(-1, e.getMessage());
		}
		return jb;
	}
	/**
	 * 下单到代购系统
	 */
	@RequestMapping
	public String b2bpay(HttpServletRequest request,HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");// 订单编号
		String payhand = request.getParameter("payhand");// 支付方式 1自动  其他手动
		String asmszfzhid = request.getParameter("zfzhid");// 支付账号id 如果是手工支付 这里传的是支付科目
		String zflx = request.getParameter("zflx");// 支付平台 1-9 对应 支付宝 财付通 。。。。易航宝
		JpKhdd jpKhdd = b2bsession.getjpKhdd(user, ddbh);

		try {
			B2bRequest brequest = b2bsession.getB2bRequest(user, jpKhdd);
			PaymentInfo paymentinfo = b2bsession.getPaymentInfo(user, asmszfzhid, payhand, zflx);
			brequest.setPayInfo(ObjectUtil.obj2Byte(paymentinfo));
			
			B2bOrderResponse orderResponse = VetpsPurchasB2bClient.b2bSubmitOrder(brequest, user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", brequest.getOutOrderNo()));
			request.setAttribute("b2borderresponse", orderResponse);
			int status = orderResponse.getStatus();// 0成功 -1失败
			
			if(status==0){//支付成功跳转支付页面或详情页面
				String errormessge = orderResponse.getErrorMessage();
				if(StringUtils.isNotBlank(errormessge)){
					errormessge = errormessge.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
					request.setAttribute("errormsg", errormessge);
					return b2bpayselect(request,response);
				}
				if("1".equals(payhand)){
					return viewpath("payturnUtf");
				}else{//自动支付跳转支付成功页面
					PayInfo payInfo = orderResponse.getOrderInfo().getPayInfo();
					if("T".equals(payInfo.getSuccess())){//返回支付成功页面
						return viewpath("b2bInfo");
					}else{
						errormessge = payInfo.getError();
						errormessge = errormessge.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
						request.setAttribute("errormsg", errormessge);
						return b2bpayselect(request,response);
					}
				}
			}else{
				return index(request, response);//或者跳回原页面
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", e.getMessage());
			return viewpath("b2berrpage");
		}
	}
	/**
	 * 获取状态
	 */
	@RequestMapping
	@ResponseBody
	public String b2bstate(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		B2bStateResponse r = null;
		try {
			r = VetpsPurchasB2bClient.getB2bState(user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = JsonMapper.nonEmptyMapper().toJson(r);
		return json;
	}
	/**
	 * 跳转支付页面
	 * @throws Exception 
	 * 
	 */
	private String b2bpayselect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ddbh = request.getParameter("ddbh");
		Shyhb user = SessionUtils.getShshbSession();
		JpKhdd jpKhdd = b2bsession.getjpKhdd(user, ddbh);
		Shzfkm shzfkm = new Shzfkm();
		shzfkm.setShbh(user.getShbh());
		shzfkm.setSffkkm("1");
		shzfkm.setSfqy("1");
		List<Shzfkm> shzfkmList = shzfkmServiceImpl.getShzfkmList(shzfkm);
		request.setAttribute("shzfkmList", shzfkmList);
		//手动
		JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), jpKhdd.getHkgs(), "720102");
		JpB2bHkgsxx jpB2bHkgsxx = jpB2bHkgsxxServiceImpl.selectByHkgs_Bca(user.getShbh(), jpKhdd.getHkgs(), "720102");
		if(jpB2bHkgs==null||jpB2bHkgsxx==null){
			throw new Exception("没有查到航司基础数据");
		}
		request.setAttribute("jpB2bHkgs", jpB2bHkgs);
		List<String> zffsStr = jpB2bHkgs.getZffsList();//支持的手工支付方式
		request.setAttribute("zffsStr", zffsStr);
		request.setAttribute("jpB2bHkgsxx", jpB2bHkgsxx);
		request.setAttribute("shzfkmList", shzfkmList);
		return viewpath("b2bpayselect");
	}
	private String b2bzjp(String ddbh,Shyhb user,B2bOrderResponse orderResponse) throws Exception{
		//判断是否存在票号
		OrderInfo orderInfo = orderResponse.getOrderInfo();
		List<JpJp> jpList = jpJpServiceImpl.getJpListByDDbh(ddbh, user.getShbh());
		if(jpList==null||jpList.isEmpty()){//执行转机票
			String hkgsddbh = orderInfo.getAirwaysOrderNo();
			JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddByCgly(ddbh, "B2B", hkgsddbh);
			if(jpCgdd==null){
				return "没有找到对应采购记录";
			}
			return b2bsession.zjp(orderInfo,jpCgdd);
		}
		return "";
	}
	private static String readImage(String data) throws Exception {
		String pathtmp = B2bCpController.class.getResource("/").getPath();
		pathtmp = pathtmp.replaceAll("/WEB-INF/classes", "");
		String path = pathtmp + "gif/";
		if (!new File(path).isDirectory()) {// 重建目录
			new File(path).mkdirs();
		}
		byte[] b = Base64.decodeBase64(data);
		String no = VeDate.getNo(6);
		String file = path + no + ".gif";
		File storeFile = new File(file);
		FileUtils.writeByteArrayToFile(storeFile, b);
		String infoold = "/gif/" + no + ".gif";
		return infoold;
	}
}
