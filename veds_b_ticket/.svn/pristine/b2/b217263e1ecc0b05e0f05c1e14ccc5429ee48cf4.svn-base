package cn.vetech.web.webcontent;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZfzhServiceImpl;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.LogUtil;

public class SignReturnController {

	@Autowired
	private JpB2bZfzhServiceImpl zfzhServiceImpl;
	/**
	 * 签约后返回
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "returnResult")
	public String returnResult(HttpServletRequest request) throws Exception {
		LogUtil.getZfzhQy("zhzhqy").error("收到签约返回信息:" + rq(request));
		String partner = request.getParameter("partner");
		String notify_type = request.getParameter("notify_type");
		String B2B_TENPAY_PARTNER = B2bpayutil.getPayparam("B2B_TENPAY_PARTNER");// 财付通
		String B2B_BHECARD_PARTNER = B2bpayutil.getPayparam("B2B_BHECARD_PARTNER");// 易生卡
		String OrgId = request.getParameter("OrgId");// 银联委托支付
		String B2B_CHINAPAY_ORGID = B2bpayutil.getPayparam("B2B_CHINAPAY_ORGID");// 银联委托支付
		String B2B_AIREPAY_ORGID = B2bpayutil.getPayparam("B2B_AIREPAY_ORGID");//易航宝

		JpB2bZfzh jpB2bZfzh = new JpB2bZfzh();
		String attach = "";
		if (StringUtils.isNotBlank(partner)) {
			if (partner.equalsIgnoreCase(B2B_TENPAY_PARTNER)) {// 如果是财付通
				jpB2bZfzh.setZflx("2");
				String payer = request.getParameter("payer");
				// 航空公司
				String spid = request.getParameter("spid");
				// 表的id
				attach = request.getParameter("attach");

				jpB2bZfzh.setZfxx1(spid);
				jpB2bZfzh.setSfqy("1");
				jpB2bZfzh.setSfkq("1");

			} else if (partner.equalsIgnoreCase(B2B_BHECARD_PARTNER)) {// 易生卡
				String email = request.getParameter("email");
				String customer_no = request.getParameter("customer_no");// zfxx2
				String nopasskey = request.getParameter("nopasskey");// zfxx3
				attach = request.getParameter("cpsid");

				jpB2bZfzh.setZfxx2(customer_no);
				jpB2bZfzh.setZfxx3(nopasskey);
				jpB2bZfzh.setSfqy("1");
				jpB2bZfzh.setSfkq("1");
			}
		}
		
		if (OrgId.equalsIgnoreCase(B2B_CHINAPAY_ORGID) || OrgId.equalsIgnoreCase(B2B_AIREPAY_ORGID)){// 如果是银联或易航宝
			String OrgUserNo = request.getParameter("OrgUserNo");// 代理人在机构中的用户名
			String YHBUserNo = request.getParameter("YHBUserNo");// 银联账号
			String OrgCode = request.getParameter("OrgCode");// 委托码
			String RespCode = request.getParameter("RespCode");// 响应码
			attach = request.getParameter("Reserved01");// ID
			if (StringUtils.isBlank(RespCode)) {
				return null;
			}
			jpB2bZfzh.setZfzh(YHBUserNo);
			String rtn = "";
			if (StringUtils.isNotBlank(OrgCode) && "00".equals(RespCode)) {
				jpB2bZfzh.setSfqy("1");
				jpB2bZfzh.setSfkq("1");
				jpB2bZfzh.setZfxx4(VeDate.getStringDate());
				rtn = "签约成功。";
			} else {
				jpB2bZfzh.setSfqy("0");
				jpB2bZfzh.setSfkq("0");
				rtn = "签约失败。";
			}
			jpB2bZfzh.setZfxx3(OrgUserNo);
			// 委托机构编号：${vc.zfxx2}代理人在机构中的用户名：${vc.zfxx3 }委托码：${vc.zfxx5 }
			jpB2bZfzh.setZfxx5(OrgCode);
		}
		LogUtil.getZfzhQy("zhzhqy").error("SUCCESS");

		int post = StringUtils.indexOf(attach, "_");
		String cpsid = StringUtils.substring(attach, 0, post);
		String cpsshbh = StringUtils.substring(attach, post + 1);

		if (StringUtils.isNotBlank(cpsid) && StringUtils.isNotBlank(cpsshbh)) {
			jpB2bZfzh.setId(cpsid);
			zfzhServiceImpl.getMyBatisDao().updateById(jpB2bZfzh);
		} else {
			throw new Exception("没找到对应的支付设置记录.");
		}
		return null;
	}

	private static String rq(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names != null && names.hasMoreElements()) {
			String name = (String) names.nextElement();
			sb.append("   " + name + ":=");
			String v[] = request.getParameterValues(name);
			if (v != null && v.length > 0) {
				for (String one : v) {
					sb.append(one + " ");
				}
			}
		}
		return sb.toString();
	}
}
