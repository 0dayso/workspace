package cn.vetech.web.vedsb.jzcp;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.framework.bookticket.b2cnew.action.PaymentInfo;
import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bDlzhServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZfzhServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.platpolicy.jzcp.B2cnewException;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Passenger;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Segment;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasClient;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cOrderRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.SegmentRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cSubmitOrderResponse;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.vedsb.utils.SysUtils;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class B2cCpController extends AbstractBaseControl {
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private JpB2bDlzhServiceImpl jpB2bDlzhServiceImpl;
	@Autowired
	private JpB2bZfzhServiceImpl jpB2bZfzhServiceImpl;
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;
	@Autowired
	private WdzlszServiceImpl WdzlszServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired 
	private ShcsbServiceImpl shcsbServiceImpl;
	@RequestMapping
    public String b2cPolicy(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		JpKhdd jpKhdd = null;
		try {
			String ddbh = request.getParameter("ddbh");
//			ddbh = "JP160527000611";
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("订单编号不能为空");
			}
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			bean.setShbh(user.getShbh());
			jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getShbh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getShbh());
			if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
				throw new Exception("订单信息不全");
			}
			boolean hasChdOrInf = false;
			for(int i=0;i<cjrList.size();i++){
				JpKhddCjr onecjr = cjrList.get(i);
				if(!"1".equals(onecjr.getCjrlx())){
					hasChdOrInf = true;
					break;
				}
			}
			if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
				throw new Exception("C站代购只支持成人单程");
			}
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.CPS, user.getShbh());
			if(ptzh==null){
				throw new Exception("请先配置CPS信息");
			}
			Segment segment= getSegment(jpKhdd, cjrList ,hdList, ptzh, user);
			//
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setShbh(user.getShbh());
			shzfkm.setSffkkm("1");
			shzfkm.setSfqy("1");
			List<Shzfkm> shzfkmList = shzfkmServiceImpl.getShzfkmList(shzfkm);
			
			String shbh = user.getShbh();
			String hkgs = jpKhdd.getHkgs();
			JpB2bHkgsxx jpHkgsxx = jpB2bHkgsxxServiceImpl.selectByHkgs_Bca(user.getShbh(), jpKhdd.getHkgs(), "720104");
			JpB2bHkgs jpHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(shbh, hkgs, "720104");
			if(jpHkgsxx==null||jpHkgs==null){
				throw new Exception("没有查到航司基础数据");
			}
			
			String zdzfzc = jpHkgsxx.getZdzfzc();
			if(StringUtils.isBlank(zdzfzc)){
				if("CZ".equals(jpKhdd.getHkgs())){
					zdzfzc = "1/2/3/4/6/";
				}else{
					zdzfzc = "1/2/3/4/";
				}
			}
			//手动
			List<String> arrsd = jpHkgs.getZffsList();//支持的手工支付方式
			List<String> arrzd = new ArrayList<String>();
			for(int i=0;i<arrsd.size();i++){
				if(zdzfzc.indexOf(arrsd.get(i))>-1){
					arrzd.add(arrsd.get(i));
				}
			}
			Map<String,String> papykindmap = DataUtils.getPapykindmap();
			request.setAttribute("asmspaykindzd", arrzd);
			request.setAttribute("asmspaykindsd", arrsd);
			request.setAttribute("shzfkmList", shzfkmList);
			request.setAttribute("segment", segment);
			request.setAttribute("papykindmap",papykindmap);
			request.setAttribute("shyhb", user);
			request.setAttribute("CPSKYYE", "100");
			request.setAttribute("jpKhdd", jpKhdd);
			request.setAttribute("adtnum", cjrList.size());
		} catch (Exception e) {
			e.printStackTrace();
			//返回了错误信息
			request.setAttribute("errmsg",e.getMessage());
			request.setAttribute("jpKhdd",jpKhdd);
			return viewpath("b2cerrpage");
		}
		return viewpath("b2cpolicynew");
	}
	//下单到代购系统
	@RequestMapping
	@ResponseBody
    public String order2dgxt(HttpServletRequest request, HttpServletResponse response) {
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh = request.getParameter("ddbh");//订单编号
		String autopaystr = request.getParameter("autopay");//自动支付   0 自动1手动
		String source = request.getParameter("source");//1-官网 2-淘宝 3-携程
		String zfzh = request.getParameter("zfzh");//支付账号  如果是手工支付 这里传的是支付科目
		String paymethod = request.getParameter("paymethod");//支付平台   1-9  对应  支付宝  财付通 。。。。易航宝
		String coupon = request.getParameter("coupon");//优惠代码 对应CabinSeats对象的discountCode字段

		String flightno = request.getParameter("flightno");
		String cabin = request.getParameter("cabin");
		String price = request.getParameter("price");
		String cplx = request.getParameter("type");
		
		String rtnmsg = "";
		try {	
			JpKhdd bean = new JpKhdd();
			bean.setDdbh(ddbh);
			bean.setShbh(user.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(bean);
			List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getShbh());
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, user.getShbh());
			
			if (jpKhdd == null || CollectionUtils.isEmpty(cjrList) || CollectionUtils.isEmpty(hdList)) {
				throw new Exception("订单信息不全");
			}
			boolean hasChdOrInf = false;
			for(int i=0;i<cjrList.size();i++){
				JpKhddCjr onecjr = cjrList.get(i);
				if(!"1".equals(onecjr.getCjrlx())){
					hasChdOrInf = true;
					break;
				}
			}
			if(!"1".equals(jpKhdd.getHclx())||hdList.size()!=1||hasChdOrInf){
				throw new Exception("C站代购只支持成人单程");
			}
			JpKhddHd hd = hdList.get(0);
			//取JpPtzcZh表设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.CPS, user.getShbh());
			if(ptzh==null){
				throw new Exception("请先配置CPS采购账号信息");
			}
			String hkgs = jpKhdd.getHkgs();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String  fromDatetime = sdf.format(jpKhdd.getCfrq());
			fromDatetime = fromDatetime.substring(0,16);
			String  toDatetime = sdf.format(jpKhdd.getDdsj());
			toDatetime = toDatetime.substring(0,16);
			/*登录相关信息*/
			//取航司登录账号信息
			JpB2bDlzh dlzhBean = new JpB2bDlzh();
			dlzhBean.setShbh(user.getShbh());
			dlzhBean.setBca("720104");
			dlzhBean.setHkgs(hkgs);
			dlzhBean.setSfsy("0");
			if("0".equals(autopaystr)&&"CZ".equals(hkgs)&&"6".equals(paymethod)){//如果是南航御航宝
				dlzhBean.setDlfs("7");
			}
			//按创建时间倒叙
			dlzhBean.setOrderBy("cjtime desc");
			List<JpB2bDlzh> dlzhList = jpB2bDlzhServiceImpl.getMyBatisDao().select(dlzhBean);
			if(CollectionUtils.isEmpty(dlzhList)){
				throw new Exception("没有找到航司登录账号信息，请先配置");
			}
			JpB2bDlzh dlzh = dlzhList.get(0);
			String airwaysAccount = dlzh.getZh();
			String airwaysPwd = dlzh.getMm();
			String loginType = dlzh.getDlfs();
			
			/*支付相关信息*/
			//获取支付账号的记录，序列化后传入
			byte[] bytes = null;
			String cg_zfkm = "";//采购支付科目
			if("0".equals(autopaystr)) {
				JpB2bZfzh b2bzfzhBean = new JpB2bZfzh();
				b2bzfzhBean.setZfzh(zfzh);
				b2bzfzhBean.setShbh(user.getShbh());
				b2bzfzhBean.setZflx(paymethod);
				List<JpB2bZfzh> b2bzfzhList = jpB2bZfzhServiceImpl.getMyBatisDao().select(b2bzfzhBean);
				if(CollectionUtils.isEmpty(b2bzfzhList)){
					throw new Exception("获取支付账号失败");
				}
				JpB2bZfzh b2bzfzh = b2bzfzhList.get(0);
				if(StringUtils.isBlank(b2bzfzh.getZfxx7())) {
					throw new Exception("自动支付账号没有设置对应科目");
				}
				cg_zfkm = b2bzfzh.getZfxx7();
				PaymentInfo pmi = new PaymentInfo();
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
				try {
					bytes = obj2Byte(pmi);
				} catch (Exception e) {
					throw new Exception("获取支付信息失败。"+e.getMessage());
				}
			}else{
				cg_zfkm = zfzh;
				zfzh = "";
			}
			/*联系人信息*/
			JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), hkgs, "720104");
			if(jpB2bHkgs==null){
				throw new Exception("没有找到航司配置信息，请先配置");
			}
			Wdzlsz wdzlsz = WdzlszServiceImpl.getMyBatisDao().selectByPrimaryKey(jpKhdd.getWdid());
			String lxrsjqz = jpB2bHkgs.getLxrsjqz();//联系人手机取值方式 
			String lxrptsj = jpB2bHkgs.getLxrptsj();
			String linker = jpB2bHkgs.getLxr();
			String linkMobile = jpB2bHkgs.getSj();
			String linkMail = jpB2bHkgs.getLxr();
			//如果是网店订单，按设置判断是否取订单上面的联系人手机和邮件
			if("1".equals(lxrsjqz)&&wdzlsz!=null&&StringUtils.isNotBlank(lxrptsj)&&lxrptsj.indexOf(wdzlsz.getWdpt())>-1) {
				//2015-09-14 添加手机号格式验证
				String telno = StringUtils.trimToEmpty(jpKhdd.getNxsj());
				if(StringUtils.isNotBlank(telno)) {
					Map<String, String> map = ifMobile(telno);
					if("1".equals(map.get("ismobile"))) {
						linkMobile = telno;
					}
				}
			}
			//组织请求参数
			/*回调通知地址*/
			String businessIp = SysUtils.getTicketUrl()+"/webcontent/b2ctz/cptz";
			B2cOrderRequest order = new B2cOrderRequest();
			order.setHkgspnr(jpKhdd.getCgHkgsPnr());
			order.setUserid(user.getBh());
			
			order.setOutOrderNo(ddbh);//ASMS订单编号
			order.setSource(source);//0-官网 1-淘宝 2-携程
			
			order.setAirwaysAccount(airwaysAccount);//航空公司登录账号
			order.setAirwaysPwd(airwaysPwd);//航空公司登录密码
			order.setLoginType(loginType);//登录方式
			
			order.setAirways(hkgs);//航空公司
			order.setFlightNo(flightno);//航班号
			order.setFromCity(hd.getCfcity());//出发城市
			order.setToCity(hd.getDdcity());//到达城市
			order.setFromDatetime(fromDatetime);//起飞时间  yyyy-mm-dd hh:mm
			order.setToDatetime(toDatetime);//到达时间  yyyy-mm-dd hh:mm
			order.setSeatClass(cabin);//舱位
			order.setSalePrice(price);//价格
			order.setCplx(cplx);
			
			order.setPayment(paymethod);//支付平台
			order.setPayAccount(zfzh);//支付账号
			order.setPayInfo(bytes);//支付信息
			order.setAutopay(autopaystr);//自动支付   0 自动1手动
			order.setLinker(linker);//联系人
			order.setLinkMobile(linkMobile);//联系人电话
			order.setLinkMail(linkMail);//联系人邮件
			order.setBusinessIp(businessIp);//商户IP+端口
			order.setPnr(jpKhdd.getCgPnrNo());//pnr编码
			
		    order.setPwd1(ptzh.getPwd1());
		    order.setCompid(ptzh.getShbh());
		    order.setUser1(ptzh.getUser1());
		    order.setUser2(ptzh.getUser2());
		    order.setUrl1(ptzh.getUrl1());
		    order.setUserphone(user.getDh());//用户手机
		    order.setCoupon(coupon);//优惠码
		    
	//	    if(null != sz){//暂时还没有T_cz_cplx_sz设置表
	//	        order.setCplxSetting(sz.getCplxid());//用户设置的产品类型过滤
	//	    }
			
		    /*乘机人信息*/
			List<Passenger> passengers = new ArrayList<Passenger>();
//			String[] sjhStr = new String[]{"18117176282","18627803901","13117502522"};
			Shcsb csBean=shcsbServiceImpl.findCs("104011", user.getShbh());
			String sjh="18117176282,18627803901,13117502522";
			if(csBean!=null){
				if(StringUtils.isNotBlank(csBean.getCsz2())){
					sjh=csBean.getCsz2();
				}
			}
			String [] arrsjh = sjh.split(",");
			for(int i=0;i<cjrList.size();i++) {
				JpKhddCjr onecjr = cjrList.get(i);
				Passenger passenger = new Passenger();
				String passeng = onecjr.getCjr();//乘机人
				String cardType = onecjr.getZjlx();//证件类型 身份证NI 护照P 其他ID
				String cardId = onecjr.getZjhm();//证件号码
//				String passengerMobile = onecjr.getSjhm();//乘机人电话
				
				passenger.setPassenger(passeng);//乘机人
				passenger.setCardType(cardType);//证件类型 身份证NI 护照P 其他ID
				passenger.setCardId(cardId);//证件类型
				String passengerMobile = onecjr.getSjhm();
				if(StringUtils.isBlank(passengerMobile)){
					passengerMobile = i<arrsjh.length ? arrsjh[i] : "";
				}
				passenger.setPassengerMobile(passengerMobile);//乘机人电话
				passengers.add(passenger);
			}
			
			//目前没有104011参数设置
	//		String sjh = Function.acsb("104011", ve_yhb.getZgscompid(), "YDHM", "");
	//		String [] arrsjh = sjh.split(",");
	//		int sjhlength = arrsjh.length;
	//
	//		for (int i = 0; i < passengers.size(); i++) {
	//			if (StringUtils.isBlank(passengers.get(i).getPassengerMobile()) || !StringUtils.isNumeric(passengers.get(i).getPassengerMobile())) {
	//				if (sjhlength > 0 && StringUtils.isNotBlank(arrsjh[sjhlength - 1])) {
	//					passengers.get(i).setPassengerMobile(arrsjh[sjhlength - 1]);
	//					sjhlength = sjhlength - 1;
	//				}
	//			}
	//		}
			
			order.setPassengers(passengers);
			B2cSubmitOrderResponse orderResponse = null;
			try {
				orderResponse = VetpsPurchasClient.b2cSubmitOrder(order, ptzh,LogUtil.getDgrz("B2C",jpKhdd.getDdbh()));
			} catch (Exception e) {
				throw new Exception("请求接口失败;"+e.getMessage());
			}
			int status = orderResponse.getStatus();//0成功 -1失败 .
			if(0==status) {
			    //TODO 写本地采购订单表 jp_cgdd
				JpCgdd jpCgddBean = new JpCgdd();
				jpCgddBean.setId(Identities.randomLong()+"");
				jpCgddBean.setDdbh(jpKhdd.getDdbh());
				jpCgddBean.setZt("0");//下单C站代购时，修改采购订单状态为等待支付
				jpCgddBean.setCjUserid(user.getBh());
				jpCgddBean.setCjdatetime(VeDate.getNow());
			    String orderXml = XmlUtils.toXml(order);
			    if (StringUtils.isNotBlank(orderXml)) {
		            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		            Matcher m = p.matcher(orderXml);
		            orderXml = m.replaceAll("");
		        }
				jpCgddBean.setSubmitParam(orderXml);
				jpCgddBean.setOrderhkgs(jpKhdd.getHkgs());
				jpCgddBean.setHkgs(jpKhdd.getHkgs());
				jpCgddBean.setPnrno(jpKhdd.getCgPnrNo());
				jpCgddBean.setHkgsPnrno(jpKhdd.getCgHkgsPnr());
				jpCgddBean.setPaykind(paymethod);
				jpCgddBean.setHkgszh(airwaysAccount);
				jpCgddBean.setHbh(flightno);
				jpCgddBean.setCw(cabin);
				jpCgddBean.setCwpj(NumberUtils.toDouble(price));
				jpCgddBean.setZfzh(zfzh);
			    jpCgddBean.setCgZfkm(cg_zfkm);
			    jpCgddBean.setIfxepnr("0");//这里先暂时默认为0
			    jpCgddBean.setCgly("100144");//采购来源取ve_class表 id字段 lb='10014'
			    jpCgddBean.setCgdw("B2C");////采购单位取ve_class表 ywmc字段 lb='10014'
			    jpCgddBean.setShbh(user.getShbh());
			    jpCgddBean.setJyzt("0");
			    jpCgddBean.setDgdh(orderResponse.getOrderNo());
				jpCgddServiceImpl.insert(jpCgddBean);
				
			    //设置cookie
//			    Cookie cookie = new Cookie("taskFlag", "true");  
//			    cookie.setMaxAge(60 * 10);
//			    response.addCookie(cookie);
			    rtnmsg = "已提交代购成功，请到看板查看";
			}else {
				rtnmsg = "提交代购返回失败,错误原因："+orderResponse.getErrMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			rtnmsg = "提交代购失败,错误原因:"+e.getMessage();
		}
		return rtnmsg;
	}
	/**
	 * @description:通过代购获取政策信息	
	 * @author:longm
	 * @date:2015-8-27 上午08:56:40
	 */
    private Segment getSegment(JpKhdd jpKhdd,List<JpKhddCjr> cjrList,List<JpKhddHd> hdList, JpPtzcZh ptzh, Shyhb user) throws B2cnewException {
    	JpKhddHd oneHd = hdList.get(0);
    	SegmentRequest sr = new SegmentRequest();
    	sr.setAircom(jpKhdd.getHkgs());
    	sr.setDeptCity(oneHd.getCfcity());
    	sr.setArrCity(oneHd.getDdcity());
    	String flightDate = oneHd.getCfsj();
    	if(flightDate.length()>10){
    		flightDate = StringUtils.substring(flightDate, 0, 10);
    	}
//    	flightDate = "2016-06-28 08:40";
    	sr.setFlightDate(flightDate);//2016-05-24 16:09
    	sr.setUserid(user.getBh());
    	sr.setFlightNo(oneHd.getCgHbh());
    	sr.setDdbh(jpKhdd.getDdbh());
	    
	    Segment segment = null;
        try {
            segment = VetpsPurchasClient.getSegment(sr, ptzh,LogUtil.getDgrz("B2C",jpKhdd.getDdbh()));
//        	String str ="<Segment><account>WDB</account><airline>MU</airline><airlineType>0</airlineType><arrCity>HGH</arrCity><datetime>2016-06-03 14:04:54</datetime><depCity>TYN</depCity><flightDate>2016-06-28 08:40</flightDate><flights><arrTime>10:40</arrTime><cabins><cabin>Z</cabin><nprice>310.0</nprice><price>310.0</price><seats>10</seats><type>超值经济舱</type></cabins><cabins><cabin>Z</cabin><nprice>310.0</nprice><price>310.0</price><seats>10</seats><type>青老年特惠</type></cabins><cabins><cabin>R</cabin><nprice>620.0</nprice><price>620.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>P</cabin><nprice>1000.0</nprice><price>1000.0</price><seats>2</seats><type>超值头等舱</type></cabins><cabins><cabin>B</cabin><nprice>1170.0</nprice><price>1170.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>Y</cabin><nprice>1240.0</nprice><price>1240.0</price><seats>10</seats><type>标准经济舱</type></cabins><cabins><cabin>F</cabin><nprice>2480.0</nprice><price>2480.0</price><seats>10</seats><type>标准头等舱</type></cabins><depTime>08:40</depTime><flightNo>MU5235</flightNo></flights><flights><arrTime>19:05</arrTime><cabins><cabin>V</cabin><nprice>480.0</nprice><price>480.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>P</cabin><nprice>1000.0</nprice><price>1000.0</price><seats>4</seats><type>超值头等舱</type></cabins><cabins><cabin>B</cabin><nprice>1120.0</nprice><price>1120.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>Y</cabin><nprice>1240.0</nprice><price>1240.0</price><seats>10</seats><type>标准经济舱</type></cabins><cabins><cabin>F</cabin><nprice>2480.0</nprice><price>2480.0</price><seats>10</seats><type>标准头等舱</type></cabins><depTime>17:00</depTime><flightNo>MU5241</flightNo></flights><flights><arrTime>14:10</arrTime><cabins><cabin>S</cabin><nprice>540.0</nprice><price>540.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>P</cabin><nprice>1000.0</nprice><price>1000.0</price><seats>4</seats><type>超值头等舱</type></cabins><cabins><cabin>B</cabin><nprice>1120.0</nprice><price>1120.0</price><seats>10</seats><type>折扣经济舱</type></cabins><cabins><cabin>Y</cabin><nprice>1240.0</nprice><price>1240.0</price><seats>10</seats><type>标准经济舱</type></cabins><cabins><cabin>F</cabin><nprice>2480.0</nprice><price>2480.0</price><seats>8</seats><type>标准头等舱</type></cabins><depTime>12:05</depTime><flightNo>MU5486</flightNo></flights></Segment>";
//        	segment = (Segment)XmlUtils.fromXml(str, Segment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return segment;
	}
    private static byte[] obj2Byte(Object obj){
        ByteArrayOutputStream bo = null;  
        ObjectOutputStream oo = null;  
        byte[] bytes = null;
        try {  
            bo = new ByteArrayOutputStream();  
            oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
            bytes = bo.toByteArray();  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage(), e);  
        } finally {  
            try {  
                bo.close();  
                oo.close();  
            } catch (Exception e) {  
                throw new RuntimeException(e.getMessage(), e);  
            }  
        }
        return bytes;
    }
	/**
	 * 判断是电话号码是手机还是座机
	 */
	private static Map<String, String> ifMobile(String telno) {
		Map<String, String> map = new HashMap<String, String>();
		if (telno.startsWith("1") && telno.length() == 11 && telno.indexOf("-") < 0) { // 以1开头，长度11判定为手机号码
			map.put("ismobile", "1"); // 是否手机号码 1是 2座机
			map.put("mbhd", telno.substring(0, 7)); // 手机中间4位号段
		}

		String[] schar = { "0", "086", "86", "+86", "860" }; // 号码前面一截需截取的字符串
		String sno = telno;
		int ti = 0;
		for (int i = 0; i < schar.length; i++) {
			if (telno.startsWith(schar[i])) {
				ti = i;
				sno = telno.substring(schar[i].length()); // 截取特定字符串后的号码
				if (sno.startsWith("1") && sno.length() == 11 && sno.indexOf("-") < 0) { // 以1开头，长度11判定为手机号码
					map.put("ismobile", "1");
					map.put("mbhd", sno.substring(0, 7));
				}
			}
		}

		if (null == map || map.size() <= 0) {
			map.put("ismobile", "2");
			map.put("mbhd", ti == 0 ? telno : sno); // 返回电话号码,如果是前缀为0截取的，返回原始号码，否则返回过滤后的号码
		}
		return map;
	}
}
