package cn.vetech.vedsb.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrauth.PnrAuthParam;
import org.vetech.core.business.pid.api.pnrpat2.PNRPAT2;
import org.vetech.core.business.pid.api.pnrpat2.PNRPAT2Param;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat3.VePnrPat3;
import org.vetech.core.business.pid.api.pnrpat3.VePnrPat3Command;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;

public class DataUtils {
	private static Logger logger = LoggerFactory.getLogger(DataUtils.class);
	public static Map<String, String> getPapykindmap() {
		Map<String, String> Papykindmap = new TreeMap<String, String>();
		Papykindmap.put("1", "支付宝");
		Papykindmap.put("2", "财付通");
		Papykindmap.put("3", "汇付");
		Papykindmap.put("4", "易宝");
		Papykindmap.put("5", "快钱");
		Papykindmap.put("6", "御航宝");
		Papykindmap.put("7", "易商旅");
		Papykindmap.put("8", "易生卡");
		Papykindmap.put("9", "易航宝");
		return Papykindmap;
	}
	/**
	 * 支付账号的检查
	 * 
	 * @param zfzh
	 * @param info
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String checkZfzh(JpB2bZfzh zfzh) {
		String info = "";
		String zflx = zfzh.getZflx();
		if ("1".equals(zfzh.getSfkq())) {
			if ("1".equals(zflx)) {// 支付宝
				if (!"1".equals(zfzh.getSfqy())) {
					info = "支付宝账号需要签约才能使用。";
				} else {
					// 当这里存储的不是isNoSecurityCode时表示需要安全码
					if ("isnosecuritycode".equals(zfzh.getZfxx4())) {

					} else {
						// 是否有授权码 支付宝的授权码每天取一次 不是当天授权的
						if (StringUtils.isBlank(zfzh.getZfxx2())
								|| StringUtils.isBlank(zfzh.getZfxx3())
								|| !VeDate.getStringDateShort().equals(
										StringUtils.substring(zfzh.getZfxx4(), 0, 10))) {
							//info = "支付宝每天需要取得一次授权码。建议开启自动支付，无需安全码";
						}
					}
				}
			} else if ("2".equals(zflx)) {// 财付通
				if (!"1".equals(zfzh.getSfqy())) {
					info = "财付通账号需要签约才能使用。";
				}
			} else if ("3".equals(zflx)) {// 汇付价资料是否填写完整
				if (StringUtils.isBlank(zfzh.getZfxx1()) || StringUtils.isBlank(zfzh.getZfxx2())
						|| StringUtils.isBlank(zfzh.getZfxx3())) {
					info = "汇付天下账号信息不全。";

				}
			} else if ("4".equals(zflx)) {// 易宝资料是否填写完整
				if (!"YEEPAYWKZF".equals(zfzh.getZfxx1())&&!"1000000-NET".equals(zfzh.getZfxx1()) && !"YEEPAYCREDIT".equals(zfzh.getZfxx1()) && !"YEEPAYB2CZDZF".equals(zfzh.getZfxx1())) {
					info = "易宝支付账号类型不正确。";
				} else if (StringUtils.isBlank(zfzh.getZfxx2())) {
					info = "易宝支付账号和支付密码为空。";
				}else if("YEEPAYB2CZDZF".equals(zfzh.getZfxx1()) && (StringUtils.isBlank(zfzh.getZfxx3()) || StringUtils.isBlank(zfzh.getZfxx4()) || StringUtils.isBlank(zfzh.getZfxx5()) || StringUtils.isBlank(zfzh.getZfxx6()))){
					info = "易宝B2C账号信息不全。";
				}
			} else if ("5".equals(zflx)) {// 快钱资料是否填写完整

			} else if ("6".equals(zflx)) {// 御航宝
				if (!"1".equals(zfzh.getSfqy())) {
					info = "御航宝账号需要签约才能使用。";
				}

			} else if ("7".equals(zflx)) {// 易商旅
				if (!"1".equals(zfzh.getSfqy())) {
					info = "易商旅账号需要签约才能使用。";
				}
			} else if ("8".equals(zflx)) {// 易生卡
				if (!"1".equals(zfzh.getSfqy())) {
					info = "易生卡账号需要签约才能使用。";
				} else if (!"1".equals(zfzh.getSfqy())) {
					info = "易生卡账号需要签约才能使用。";
				}
			} else if ("9".equals(zflx)) {// 易航宝
				if (!"1".equals(zfzh.getSfqy())) {
					info = "易航宝账号需要签约才能使用。";
				}

			}
		} else {
			info = "已被禁用，不得使用该账号。";
		}
		return info;
	}
	public static Pnr getPnrPat(String pnrno, String cjrlx, JpPz jppz,Shyhb yhb,String sfxyh){
		try {
			PNRPAT2Param param = new PNRPAT2Param();
			param.setShbh(jppz.getShbh());
			param.setUserid(yhb.getPidyh());
			param.setPassword(yhb.getMm());
			param.setUrl(jppz.getPzIp() + ":" + jppz.getPzPort());
			param.setOfficeId(jppz.getOfficeid());
			String pat = "PAT:A";
			if ("2".equals(cjrlx)) {
				pat = "PAT:A*CH";
			} else if ("3".equals(cjrlx)) {
				pat = "PAT:A*IN";
			} else if (StringUtils.isNotBlank(sfxyh)) {
				pat += "#C" + sfxyh;
			}
			param.setPat(pat);
			param.setPnrno(pnrno);
			PNRPAT2 serice = new PNRPAT2();
			return serice.PNRPAT2(param);
		} catch (Exception e) {
			logger.error("调用PNRPAT2接口失败", e);
		}
		return null;
	}
	public static Pnr rtPnr(String pnrno,JpPz jpPz,Shyhb yhb) throws Exception {
		PnrRtParam pnrRtParam = new PnrRtParam();
		pnrRtParam.setShbh(jpPz.getShbh());
		pnrRtParam.setUserid(yhb.getPidyh());
		pnrRtParam.setPassword(yhb.getMm());
		pnrRtParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
		pnrRtParam.setOfficeId(jpPz.getOfficeid());
		pnrRtParam.setPnrno(pnrno);//"HFHMP1"
		try {
			Pnr pnrObject = IbeService.rtPnr(pnrRtParam);
			return pnrObject;
		} catch (Exception e) {
			logger.error("调用PNRPAT2接口失败", e);
			throw new Exception(
					"【调用PID配置用户：" + yhb.getPidyh() + "，IP:" + jpPz.getPzIp() + "，端口:" + jpPz.getPzPort() + "】连接异常：" + e.getMessage());
		}
	}
	public static boolean pnrAuth(String pnrno,String bstrOffice,String targetOffice,String deloffice,JpPz jpPz,Shyhb yhb) throws Exception {
		PnrAuthParam authParam=new PnrAuthParam();
		authParam.setBstrOffice(bstrOffice);
		authParam.setOfficeId(targetOffice);
		authParam.setBstrOfficesToDelete(deloffice);
		authParam.setPnrno(pnrno);
		authParam.setShbh(jpPz.getShbh());
		authParam.setUserid(yhb.getPidyh());
		authParam.setPassword(yhb.getMm());
		authParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
		authParam.setOfficeId(jpPz.getOfficeid());
		try {
			return IbeService.pnrAuth(authParam);
		} catch (Exception e) {
			throw new Exception(
					"【调用PID配置用户：" + yhb.getPidyh() + "，IP:" + jpPz.getPzIp() + "，端口:" + jpPz.getPzPort() + "】连接异常：" + e.getMessage());
		}
	}
	/**
	 * <功能详细描述>
	 * 给对象集合排序
	 * @param list
	 * @param orderBys 排序内容: ["payfee,desc","worktime,asc"]
	 */
	public static void sortList(List list,String[] orderBys){
		ArrayList<Object> beanComparators=new ArrayList<Object>();
		Comparator comparator=ComparableComparator.getInstance();
		for (String orderBy : orderBys) {
			String[] orderByArr=orderBy.split(",");
			if("desc".equals(orderByArr[1].toLowerCase())){
				comparator=ComparatorUtils.reversedComparator(comparator);
			}
			comparator=ComparatorUtils.nullLowComparator(comparator);
			beanComparators.add(new BeanComparator(orderByArr[0],comparator));
		}
		ComparatorChain comparatorChain=new ComparatorChain(beanComparators);
		Collections.sort(list, comparatorChain);
	}
	/**
	 * <添加协议号>
	 * 
	 * @param jpPz
	 * @param shyhb
	 * @param pnrno
	 * @param sfxyh 三方协议号
	 * @param scny 总运价 账单价+机建燃油
	 * @param cjrs
	 * @return
	 * @throws Exception [参数说明]
	 */
	public static String pnrPat3(JpPz jpPz,Shyhb shyhb,String pnrno,String sfxyh,String scny,String cjrs) throws Exception {
		VePnrPat3Command cmd=new VePnrPat3Command();
		cmd.setUrl(jpPz.getPzIp() + jpPz.getPzPort());
		cmd.setUserid(shyhb.getPidyh());
		cmd.setPass("123456");
		cmd.setPnrno(pnrno);
		cmd.setOffice(jpPz.getOfficeid());
		cmd.setPat("PAT:A#C" + sfxyh);
		cmd.setScny(scny);// 总运价,总燃油税,总基建
		cmd.setIstest("0");
		cmd.setSelectlowprice("0");
		cmd.setPersons(cjrs);
		VePnrPat3 vePnrPat3=new VePnrPat3();
		return vePnrPat3.excuted(cmd);
	}
	/**
	 * 根据正则表达式获取字符串
	 * @param infoold
	 * @param reg
	 * @return
	 */
	public static String getStrByReg(String infoold, String reg) {
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher mp = p.matcher(infoold);
		String value = "";
		if (mp.find()) {
			value = mp.group(1);
		}
		return value;
	}
}
