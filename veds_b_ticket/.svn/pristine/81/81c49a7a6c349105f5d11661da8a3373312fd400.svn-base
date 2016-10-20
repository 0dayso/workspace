package cn.vetech.vedsb.specialticket.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;
import cn.vetech.vedsb.specialticket.request.KwOrderRequest;

public class KwUtil {
	/**
	 * 根据追位申请单拼装KwOrderRequest
	 * @param jpTjsq
	 * @return
	 */
	public static KwOrderRequest tjsqToKwRequest(JpTjsq jpTjsq,List<JpTjsqCjr> cjrList){
		KwOrderRequest request=new KwOrderRequest();
		request.setSqdh(jpTjsq.getSqdh());
		request.setHkgs(jpTjsq.getHkgs());
		request.setZwfs(jpTjsq.getZwfs());
		request.setCfcity(jpTjsq.getCfcity());
		request.setDdcity(jpTjsq.getDdcity());
		request.setCjrqs(VeDate.dateToStr(jpTjsq.getCjrqs()));
		request.setCjrqz(VeDate.dateToStr(jpTjsq.getCjrqz()));
		String pjfw=jpTjsq.getPjfw() == null ? "0" : jpTjsq.getPjfw().toString();
		request.setPjfw(pjfw);
		request.setCjrs(jpTjsq.getCjrs().toString());//乘机人数，不可能为空
		StringBuffer cjrxx=new StringBuffer();
		for (JpTjsqCjr cjr : cjrList) {
			cjrxx.append(cjr.getId() + "^" + cjr.getZjhm() + "^" + cjr.getCjr() + ";");
		}
		request.setCjres(cjrxx.toString());
		request.setHbsks("00:00");
		request.setHbskz("23:59");
		request.setYxq(VeDate.dateToStr(jpTjsq.getYxq()));
		request.setZf_fkf(jpTjsq.getZfFkf());
		request.setCk_datetime(VeDate.dateToStrLong(jpTjsq.getCkDatetime()));
		request.setGngj(jpTjsq.getGngj());
		String ddlx="1";
		if(StringUtils.isBlank(jpTjsq.getDdbh())){
			ddlx="2";
		}
		request.setDdlx(ddlx);//1追位单 2预约单
		request.setHbh(jpTjsq.getHbh());
		request.setCw(jpTjsq.getCw());
		request.setGxhbsfzw(jpTjsq.getGxhbsfzw());
		String gxhbcj=jpTjsq.getGxhbcj() == null ? "0" : jpTjsq.getGxhbcj().toString();
		request.setGxhbcj(gxhbcj);
		if("3".equals(jpTjsq.getZwlx())){//0非隔天追位 3隔天追位
			request.setZwlx("3");
		}else {
			request.setZwlx("0");
		}
		return request;
	}
}
