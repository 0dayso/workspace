package cn.vetech.vedsb.platpolicy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.jpddsz.ctrip.CtripGy;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.PlatCode;

@Service
public class PnrAuthService {
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpDdszServiceImpl jpDdszImpl;
	
	private final int SQ_COUNT=2;
	
	public String authPnr(String ptzcbs,String ptoffice,JpKhdd jpKhdd,Shyhb yhb,JpPtLog ptLog,String policyType,String isHbm) throws Exception{
		String authoZt="0";//0不授权，-1授权失败，1授权成功
		String pnrno=jpKhdd.getCgPnrNo();
		String pnrnr=jpKhdd.getPnrLr();//pnr内容
		String shbh=yhb.getShbh();
		if(StringUtils.isBlank(pnrno)){
			ptLog.add("pnr为空，不授权");
			return authoZt;
		}
		if(StringUtils.isBlank(ptoffice)){
			ptLog.add("平台返回的OFFICE号为空，不授权");
			return authoZt;
		}
		if(pnrno.startsWith(pnrno)){//假编码不授权
			ptLog.add("pnr:"+pnrno+"为假编码，不授权");
			return authoZt;
		}
		if("1".equals(isHbm)){
			ptLog.add("换编码政策，不授权");
			return authoZt;
		}
		if(StringUtils.isNotBlank(policyType) && (policyType.contains("BP") || policyType.contains("B2B"))){
			ptLog.add("B2B政策，不授权");
			return authoZt;
		}
		authoZt="-1";
		JpPz jppz =null;
		if(StringUtils.isBlank(pnrnr)){
			try {
				jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
				if(jppz==null){
					throw new Exception("没获取到商户"+shbh+"的pid配置");
				}
				Pnr pnrObj=DataUtils.rtPnr(jpKhdd.getCgPnrNo(),jppz,yhb);
				if(pnrObj==null){
					throw new Exception("没获提取到PNR");
				}
				pnrnr=pnrObj.getPnr_lr();
			} catch (Exception e) {
				ptLog.add("pnr授权前RTPNR失败："+e.getMessage());
			}
		}
		// 先rt查看授权了几个office，如果已经有2个了则需要叉掉第一个
		String result = "";
		if (StringUtils.isNotBlank(pnrnr)) {
			// 查找pnr内容中是否有授权的office信息
			Pattern pattern = Pattern.compile("RMK\\s*TJ\\s*AUTH\\s*(\\w*)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(pnrnr);
			while (matcher.find()) {
				String str = matcher.group(1);
				if (StringUtils.isBlank(result)) {
					result = str;
				} else {
					result = result + "," + str;
				}
			}
			ptLog.add("PNR中解析的office是：" + result + "\npnr内容是：" + pnrnr);
		}
		String[] officesz = null;
		if (StringUtils.isBlank(result)) {
			officesz = new String[0];
		} else {
			officesz = result.split(",");
		}
		try {
			// 如果已经在授权里面则不在此授权,或者获取pnr内容失败
			if (!result.contains(ptoffice) || StringUtils.isBlank(pnrnr)) {
				String deloffice = "";
				// 如果超过授权数需要删掉一个 删除后面一个
				if (officesz.length >= SQ_COUNT) {
					deloffice = officesz[officesz.length - 1];
					officesz[officesz.length - 1] = ptoffice;
				} else {
					officesz = (String[]) ArrayUtils.add(officesz, ptoffice);
				}
				
				String dp_office=StringUtils.trimToEmpty(jpKhdd.getDpOffice()).toUpperCase();
				if (dp_office.length() < 4) {
					dp_office = "";
				}
				deloffice = StringUtils.trimToEmpty(deloffice);
				ptLog.add("开始授权PNR是" + pnrno + "原订票office是" + dp_office + "目标office是" + ptoffice + "需要删除的office是"
						+ deloffice + "授权用户是" + yhb.getBh());
				boolean b = false;// 是否授权成功
				String wbdh = jpKhdd.getWbdh();
				String wdpt = jpKhdd.getWdpt();
				if (PlatCode.XC.equals(wdpt)) {// 是携程网店的供应订单
					ptLog.add("该订单属于携程导入,授权将使用携程接口进行授权");
					try {
						JpDdsz t = new JpDdsz();
						t.setWdid(jpKhdd.getWdid());
						JpDdsz jpDdsz = jpDdszImpl.getEntityById(t);
						if(jpDdsz==null){
							throw new Exception("未获取到网点"+jpKhdd.getWdid()+"的导单设置");
						}
						CtripGy ctripGy = new CtripGy(jpDdsz);
						String[] ret = ctripGy.PnrPermission(jpKhdd.getDdbh(), wbdh, ptoffice, pnrno,ptLog);
						if (!"0".equals(ret[0])) {// 授权失败
							throw new Exception(ret[1]);
						} else {
							authoZt="1";
						}
					} catch (Exception e) {
						e.printStackTrace();
						ptLog.add("使用携程接口进行授权失败："+e.getMessage());
					}
				}else {//使用本地授权
					try {
						if(jppz==null){
							ptLog.add("没获取到商户"+shbh+"的pid配置");
							return authoZt;
						}
						boolean bdsq=DataUtils.pnrAuth(pnrno, dp_office, ptoffice, deloffice, jppz, yhb);
						authoZt=bdsq ? "1" : authoZt;
					} catch (Exception e) {
						ptLog.add("调用PID授权失败："+e.getMessage());
					}
				}
			} else {
				authoZt = "1";
				ptLog.add("PNR内容中已经授权了该office号");
			}
		} catch (Exception e) {
			ptLog.add("PNR授权失败" + e.getMessage());
		}
		return authoZt;
	}
}
