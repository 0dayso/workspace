package cn.vetech.web.vedsb.jpgqgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.business.pid.exception.EtermException;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;

/**
 * 
 * @author vetech
 *
 */
public class JpGqdPnrCheck {
	
	private String gqdh;

	private Shyhb shyhb;
	
	private JpGqdServiceImpl jpGqdServiceImpl;
	
	private JpKhddServiceImpl JpKhddServiceImpl;
	
	private JpPz jpPz;

	/**
	 * PNR是否有效
	 */
	private boolean isPnrValid = false;

	/**
	 * 是否全部人改签
	 */
	private boolean isAllPassnerChange = false;

	private JpGqd jpGqd;

	/**
	 * RT黑屏原始内容
	 */
	private String pnrnr;
	
	public JpGqdPnrCheck(String gqdh, Shyhb shyhb, HttpServletRequest request, JpPz jpPz, JpGqdServiceImpl jpGqdServiceImpl, JpKhddServiceImpl JpKhddServiceImpl) throws Exception {
		this.gqdh = gqdh;
		this.shyhb = shyhb;
		this.jpGqdServiceImpl = jpGqdServiceImpl;
		this.JpKhddServiceImpl = JpKhddServiceImpl;
		this.jpPz = jpPz;
		initJpGqd();
		rt();
	}
	
	/**
	 * 获取当前改签单信息
	 * @throws Exception 
	 */
	public void initJpGqd() throws Exception {
		try {
			jpGqd = jpGqdServiceImpl.getJpGqdByGqdh(gqdh, shyhb.getShbh());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取改签单失败，传入改签单号【" + gqdh + "】，错误：" + (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
		}
		if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
			throw new Exception("获取改签单失败，请核对改签单号【" + gqdh + "】是否存在");
		}
		
	}
	
	/**
	 * 通过rt编码看该编码是否有效
	 */
	public void rt() throws Exception {
		String pnrno = jpGqd.getXsPnrNo();
		PnrRtParam param = new PnrRtParam();
		param.setPnrno(pnrno);
		param.setShbh(shyhb.getShbh());
		param.setUrl(jpPz.getPzIp() + ":" + jpPz.getPzPort());
		param.setUserid(shyhb.getPidyh());
		param.setPassword(shyhb.getMm());
		Pnr pnr = null;
		try {
			pnr = IbeService.rtPnr(param);
		} catch (EtermException e) {
			e.printStackTrace();
			throw new Exception("RT失败，PNR【" + pnrno + "】，错误：" + e.getMessage());
		}
		if (pnr != null) {
			String pnr_lx = pnr.getPnr_lx();
			if (!"2".equals(pnr_lx)) {
				isPnrValid = true;
				pnrnr = pnr.getPnr_lr();
			}
			checkIsAllPassnerChange(pnr);
		}
	}
	
	/**
	 * 验证是否所有乘机人改签
	 * @throws Exception 
	 */
	public void checkIsAllPassnerChange(Pnr pnr) throws Exception {
		int gqCjrs = jpGqd.getCjrs();
		int khddCjrs = 0;
		if (isPnrValid) {
			List<PnrCjr> cjrList = pnr.getCjrlist();
			khddCjrs = cjrList.size();
		} else {
			String ddbh = jpGqd.getDdbh();
			JpKhdd jpKhdd = new JpKhdd();
			jpKhdd.setDdbh(ddbh);
			jpKhdd.setShbh(shyhb.getShbh());
			JpKhdd jpkhdd = null;
			try {
				jpkhdd = JpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("获取原订单信息失败，传入改签单号【" + gqdh + "】，错误：" + (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
			}
			if (jpkhdd == null || StringUtils.isBlank(jpkhdd.getDdbh())) {
				throw new Exception("获取原订单信息失败，请核对改签单号【" + gqdh + "】对应原订单是否存在");
			}
			khddCjrs = jpkhdd.getCjrs();
		}
		if (gqCjrs == khddCjrs) {
			isAllPassnerChange = true;
		}
	}
	
	public String getGqdh() {
		return gqdh;
	}

	public void setGqdh(String gqdh) {
		this.gqdh = gqdh;
	}

	public Shyhb getShyhb() {
		return shyhb;
	}

	public void setShyhb(Shyhb shyhb) {
		this.shyhb = shyhb;
	}

	public boolean isPnrValid() {
		return isPnrValid;
	}

	public void setPnrValid(boolean isPnrValid) {
		this.isPnrValid = isPnrValid;
	}

	public boolean isAllPassnerChange() {
		return isAllPassnerChange;
	}

	public void setAllPassnerChange(boolean isAllPassnerChange) {
		this.isAllPassnerChange = isAllPassnerChange;
	}

	public JpGqd getJpGqd() {
		return jpGqd;
	}

	public void setJpGqd(JpGqd jpGqd) {
		this.jpGqd = jpGqd;
	}

	public String getPnrnr() {
		return pnrnr;
	}

	public void setPnrnr(String pnrnr) {
		this.pnrnr = pnrnr;
	}
	
	
}
