package cn.vetech.vedsb.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.cache.BcityCache;
import org.vetech.core.business.cache.BcityCacheService;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.tknetrdx.JpGqdBpResult;
import org.vetech.core.business.pid.api.tknetrdx.TkneTrdxResult;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxDescription;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxIndex;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxParam;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxPnr;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxPnrXml;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxPrice;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdCjrBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdHdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpgqgl.AsmsOrderSplitBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdHd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdStepEnum;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;

/**
 * 白屏改签工具类
 * @author vetech
 *
 */
public class JpGqdBpUtils {
	
	/**
	 * 获取改签步骤
	 * @param commandType
	 * @param isPnrValid
	 * @param isRetainPnr
	 * @param isAllPassnerChange
	 * @return
	 */
	public static List<JpGqdStepEnum> getStepList(String commandType, boolean isPnrValid, boolean isRetainPnr,
			boolean isAllPassnerChange) {
		List<JpGqdStepEnum> stepList = new ArrayList<JpGqdStepEnum>();
		if (isPnrValid) {
			// PNR有效
			if (isRetainPnr) {
				// 保留原PNR
				if (isAllPassnerChange) {
					// 原PNR所有人改签
					stepList.add(JpGqdStepEnum.YDHD);
					stepList.add(JpGqdStepEnum.XEHD);
				} else {
					// 原PNR部分人改签
					stepList.add(JpGqdStepEnum.SP);
					stepList.add(JpGqdStepEnum.YDHD);
					stepList.add(JpGqdStepEnum.XEHD);
				}
			} else {
				// 不保留原PNR
				if (isAllPassnerChange) {
					// 原PNR所有人改签
					stepList.add(JpGqdStepEnum.YDPNR);
					stepList.add(JpGqdStepEnum.XEPNR);
				} else {
					// 原PNR部分人改签
					stepList.add(JpGqdStepEnum.SP);
					stepList.add(JpGqdStepEnum.XEPNR);
				}
			}
		} else {
			// PNR无效
			stepList.add(JpGqdStepEnum.YDPNR);
		}
		if ("TKNE".equals(commandType)) {
			stepList.add(JpGqdStepEnum.TKNE);
		} else {
			stepList.add(JpGqdStepEnum.TRDX);
		}
		return stepList;
	}

	/**
	 * 白屏改签（暂时只支持国内）
	 * 
	 * @param gqdh
	 *            改签单号
	 * @param commandType
	 *            指令
	 * @param isPnrValid
	 *            PNR是否有效
	 * @param ve_yhb
	 * @param request
	 *            [参数说明]
	 * @throws Exception 
	 * 
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public JpGqdBpResult changeTicket(String gqdh, String commandType, String osict, boolean isPnrValid,
			boolean isRetainPnr, String scny, Shyhb shyhb, JpGqdCjrServiceImpl jpGqdCjrServiceImpl,
			JpGqdHdServiceImpl jpGqdHdServiceImpl, JpKhddServiceImpl jpKhddServiceImpl,
			JpGqdServiceImpl jpGqdServiceImpl, JpJpServiceImpl jpJpServiceImpl,
			JpPzServiceImpl jpPzServiceImpl, BcityCacheService bcityCacheService, HttpServletRequest request) throws Exception {
		VeTkneTrdxParam cmd = new VeTkneTrdxParam();
		JpGqd jpGqd = null;
		JpGqdBpResult result = new JpGqdBpResult();
		StringBuffer error = new StringBuffer();
		String shbh = shyhb.getShbh();
		try {
			jpGqd = jpGqdServiceImpl.getJpGqdByGqdh(gqdh, shyhb.getShbh());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取改签单失败，传入改签单号【" + gqdh + "】，错误："
					+ (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
		}
		if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
			throw new Exception("获取改签单失败，请核对改签单号【" + gqdh + "】是否存在");
		}
		
		JpKhdd jp_khdd = null;
		try {
			if (StringUtils.isNotBlank(jpGqd.getDdbh())) {
				JpKhdd jpkhdd = new JpKhdd();
				jpkhdd.setDdbh(jpGqd.getDdbh());
				jpkhdd.setShbh(shbh);
				jp_khdd = jpKhddServiceImpl.getKhddByDdbh(jpkhdd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取订单失败，传入订单号【" + jpGqd.getDdbh() + "】，错误："
					+ (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
		}
		if (jp_khdd == null || StringUtils.isBlank(jp_khdd.getDdbh())) {
			throw new Exception("获取订单失败，请核对订单号【" + jpGqd.getDdbh() + "】是否存在");
		}
		
		Map<String,List<JpGqdCjr>> jpGqdCjrMap = null;
		try {
			jpGqdCjrMap = jpGqdCjrServiceImpl.getCjrListByGqdhGroup(gqdh, shbh);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取改签单乘机人失败，传入改签单号【" + gqdh + "】，错误："
					+ (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
		}
		StringBuffer persons = null;
		String tkno = null;
		if (jpGqdCjrMap == null || jpGqdCjrMap.isEmpty()) {
			throw new Exception("获取改签单乘机人失败，请核对改签单号【" + gqdh + "】是否存在");
		} else {
			for (Map.Entry<String, List<JpGqdCjr>> cjrEntry : jpGqdCjrMap.entrySet()) {
				List<JpGqdCjr> cjrList = cjrEntry.getValue();
				JpGqdCjr jpGqdCjr = cjrList.get(0);
				String cjrlx = jpGqdCjr.getCjrlx();
				if (persons == null) {
					persons = new StringBuffer();
				} else {
					persons.append("|");
				}
				String zjhm = jpGqdCjr.getZjhm();
				if (".".equals(zjhm)) {
					zjhm = "";
				}
				persons.append(jpGqdCjr.getCjr()).append(",").append(zjhm).append(",");
				if ("1".equals(cjrlx)) {
					persons.append("ADULT");
				} else if ("2".equals(cjrlx)) {
					persons.append("CHD");
				} else if ("3".equals(cjrlx)) {
					persons.append("INF");
				} else {
					persons.append("ADULT");
				}

				persons.append(",");
				// 如果乘机人类型是儿童或婴儿，那么电话填写的是出生日期，必填，格式为 DDMMMYY
				if ("2".equals(cjrlx) || "3".equals(cjrlx)) {
					String csrq = jpGqdCjr.getCsrq();
					if (StringUtils.isBlank(csrq)) {
						throw new Exception("乘机人【" + jpGqdCjr.getCjr() + "】出生日期为空");
					}
					csrq = VeDate.dateCommandTime(csrq);
					if (StringUtils.isBlank(csrq)) {
						throw new Exception("乘机人【" + jpGqdCjr.getCjr() + "】出生日期格式错误");
					}
					persons.append(StringUtils.trimToEmpty(csrq));
				} else {
					persons.append(StringUtils.trimToEmpty(jpGqdCjr.getSjhm()));
				}
				persons.append(",");
				StringBuffer tknoes = new StringBuffer();
				for (JpGqdCjr cjr : cjrList) {
					tknoes.append(cjr.getTkno()).append("#");
					tkno = cjr.getTkno();
				}
				persons.append(tknoes.substring(0, tknoes.length()-1));
			}
				
		}
		List<JpGqdHd> hdList = null;
		try {
			hdList = jpGqdHdServiceImpl.getHdListByGqdh(gqdh, shbh);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取改签单航段失败，传入改签单号【" + gqdh + "】，错误："
					+ (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
		}
		StringBuffer hdToXe = null;
		StringBuffer flights = null;
		if (hdList == null || hdList.isEmpty()) {
			throw new Exception("获取改签单航段失败，请核对改签单号【" + gqdh + "】是否存在");
		} else {
			Iterator<JpGqdHd> hdItr = hdList.iterator();
			int k = 0;
			while (hdItr.hasNext()) {
				JpGqdHd hd = hdItr.next();
				if (hdToXe == null) {
					hdToXe = new StringBuffer();
					flights = new StringBuffer();
				}
				
				// 联程
				if (jp_khdd.getHclx() == "3") {
					if (k == 1) {
						flights.append("(#");
					}
				} 
				
				String cfcity = hd.getCfcity();
				String ddcity = hd.getDdcity();
				Date n_cfdatetime = VeDate.strToDate(hd.getnCfsj());
				Date n_dddatetime = VeDate.strToDate(hd.getnDdsj());
				hdToXe.append(hd.getoXsHbh()).append(",").append(cfcity).append(",").append(ddcity)
						.append(",").append(hd.getoCfsj()).append(",").append(hd.getoXsCw());
				flights.append(hd.getnXsHbh()).append(",").append(hd.getnXsCw())
						.append(",").append(VeDate.getEDate(hd.getnCfsj())).append(",").append(cfcity)
						.append(ddcity).append(",").append(JpGqdBpUtils.getMMss(n_cfdatetime)).append(",");
				long diff = (n_dddatetime.getTime() - n_cfdatetime.getTime()) / (24 * 60 * 60 * 1000);
				if (diff >= 1) {
					flights.append(JpGqdBpUtils.getMMss(n_dddatetime)).append("(+1)");
				} else {
					flights.append(JpGqdBpUtils.getMMss(n_dddatetime));
				}
				
				hdToXe.append("|");
				if (jp_khdd.getHclx() == "3") {
					flights.append("#"); 
				} else {
					flights.append("|"); 
				}
			}
		}
		
		String hdToXeStr = hdToXe.substring(0, hdToXe.length() - 1);
		String flightsStr = flights.substring(0, flights.length() - 1);
		if (jp_khdd.getHclx() == "3") {
			flightsStr += ")";
		}

		String pnrno = jpGqd.getXsPnrNo();
		if ("TRDX".equals(commandType) && "3".equals(jpGqd.getBpgqzt())) {
			if (StringUtils.isNotBlank(jpGqd.getGqXsPnrNo())) {
				pnrno = jpGqd.getGqXsPnrNo();
			}
		}
		cmd.setPnrno(pnrno);
		if ("1".equals(jpGqd.getGngj())) {
			cmd.setDori("D");
		} else {
			cmd.setDori("I");
		}

		// 组织OIINFFO数据
		String cpcs = null; // 出票城市三字码
		String hxh = null;// 航协号
		String cpdate = null;// 出票日期DDMMYY
		String officeid = null;//office号
		if (StringUtils.isNotBlank(tkno)) {
			JpJp jpJp = null;
			JpJp jpjp = new JpJp();
			jpjp.setTkno(tkno);
			jpjp.setShbh(shbh);
			try {
				jpJp = jpJpServiceImpl.getJpByTkno(jpjp);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("获取改签机票信息失败，传入改签单号【" + gqdh + "】，票号【" + tkno + "】，错误："
						+ (e.getMessage() == null ? e.getCause().getMessage() : e.getMessage()));
			}
			if (jpJp == null || StringUtils.isBlank(jpJp.getTkno())) {
				throw new Exception("获取改签机票信息失败，传入改签单号【" + gqdh + "】，票号【" + tkno + "】，未知错误");
			}
			cpdate = VeDate.getEDate(VeDate.dateToStr(jpJp.getCpDatetime()));
			officeid = jpJp.getCpOfficeid();
		}
		JpPz jppz = jpPzServiceImpl.getJpPzByOfficeid(officeid, shbh);
		Shshb shb = shyhb.getShshb();
		if (null == shb) {
			throw new Exception("未获取到操作用户所属的商户信息！");
		}
		String cpcsbh = shb.getSzcs();
		BcityCache city = bcityCacheService.getBycsbh(cpcsbh);
		if (null == city) {
			throw new Exception("未获取商户所在城市！");
		}
		cpcs = city.getNbbh();
		hxh = jppz.getIata();
		cmd.setOffice(officeid);
		cmd.setType(commandType);
		cmd.setPnrValid(isPnrValid ? "1" : "0");
		cmd.setPnrRemain(isRetainPnr ? "1" : "0");
		cmd.setPersons(persons == null ? "" : persons.toString());
		if ("TRDX".equals(commandType)) {
			if ("3".equals(jpGqd.getBpgqzt())) {
				cmd.setHdToXe("NOTHING");
			} else {
				cmd.setHdToXe(hdToXeStr == null ? "" : hdToXeStr);
			}
		} else {
			cmd.setHdToXe(hdToXeStr == null ? "" : hdToXeStr);
		}
		
		cmd.setFlights(flightsStr == null ? "" : flightsStr);
		cmd.setOsict(osict);
		if ("TRDX".equals(commandType)) {
			if (StringUtils.isBlank(hxh)) {
				error.append("航协号为空，请在基础数据，officeID维护中设置【对应的IATA号】");
				throw new Exception("航协号为空，请在基础数据，officeID维护中设置【对应的IATA号】");
			}
			cmd.setOiInfo(StringUtils.trimToEmpty(cpcs) + "," + StringUtils.trimToEmpty(cpdate) + ","
					+ StringUtils.trimToEmpty(hxh));
			
			// 已经做过运价，此时需要TRDX封口
			if ("3".equals(jpGqd.getBpgqzt())) {
				cmd.setQueryPriceForTRDX("0");
				
				// 把得到的运价封装，传入指令
				if (StringUtils.isBlank(scny)) {
					error.append("TRDX封口时未传入运价");
					throw new Exception("TRDX封口时未传入运价");
				}
				
				String[] priceArr = scny.split("\\|");
				List<VeTkneTrdxIndex> trdxIndexs = new ArrayList<VeTkneTrdxIndex>();
				for (int i = 0; i < priceArr.length; i++) {
					VeTkneTrdxIndex trdxIndex = new VeTkneTrdxIndex();
					String[] price = priceArr[i].split("&");
					trdxIndex.setIndex(price[0]);
					trdxIndex.setFoid(price[1]);
					trdxIndex.setPerson(price[2]);
					trdxIndex.setPrice(price[3]);
					trdxIndexs.add(trdxIndex);
				}
				cmd.setTrdxIndexs(trdxIndexs);
			} 
			// 若还未做白屏改签，则先查询运价
			else if (StringUtils.isBlank(jpGqd.getBpgqzt()) || "0".equals(jpGqd.getBpgqzt())) {
				cmd.setQueryPriceForTRDX("1");  
			} else {
				throw new Exception("白屏改签状态为成功或失败，请检查数据");
			}
			
		}
		cmd.setUrl(jppz.getPzIp() + ":" + jppz.getPzPort());
		cmd.setUserid(shyhb.getPidyh());
		cmd.setPassword(shyhb.getMm());
		IbeService service = new IbeService();
		TkneTrdxResult cmdResult = null;
		try {
			cmdResult = service.tkneTrdx(cmd);
		} catch (EtermException e) {
			e.printStackTrace();
			throw new Exception("调用白屏改签接口失败，错误：" + e.getMessage());
		}
		
		String n_pnr = null;
		VeTkneTrdxPnrXml spPnrXml = null;
		if (cmdResult == null) {  
			throw new Exception("调用白屏改签接口失败，未知错误");
		} else {
			int status = cmdResult.getStatus();
			result.setStatus(status);
			result.setError(cmdResult.getErrorInfo());
			
			VeTkneTrdxDescription description = cmdResult.getDescription();
			if (description == null) {
				throw new Exception("调用白屏改签接口失败，未获取详细的PNR操作信息");
			} 
			// 当出错只有error节点返回时
			else if (description != null && StringUtils.isNotBlank(description.getError())) {
				error.append(description.getError());
			}
			// 当description 节点内不为xml格式时，用正则表达式匹配
			else if (description.getPnrBook() == null && description.getPnrOld() == null 
					&& description.getPnrSps() == null && description.getPnrXepnr() == null
					&& description.getError() == null) {
//				String iniXml = cmdResult.getResultXml();
				String iniXml = null;
				if (iniXml != null) {
					String strPattern = "<DESCRIPTION>([\\s\\S]*)</DESCRIPTION>";
					Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(iniXml);
					while (matcher.find()){
						error.append(matcher.group(1));
					}
				}
			} 
			// 当description 节点内为xml格式时，根据各个返回的节点解析xml
			else {
				VeTkneTrdxPnr pnrBook = description.getPnrBook();
				if (pnrBook != null) {
					result.setPnrBookStatus(pnrBook.getFlag());
					if (NumberUtils.toInt(result.getPnrBookStatus()) > 0) {
						if(pnrBook.getPnrXml() != null) {
							result.setPnrBookDetails(pnrBook.getPnrXml().getPnrContent());
						}
					} else {
						result.setPnrBookDetails(pnrBook.getError());
					}					
				}
				
				VeTkneTrdxPnr pnrOld = description.getPnrOld();
				if (pnrOld != null) {
					result.setPnrOldStatus(pnrOld.getFlag());
					if (NumberUtils.toInt(result.getPnrOldStatus()) > 0) {
						if (pnrOld.getPnrXml() != null) {
							result.setPnrOldDetails(pnrOld.getPnrXml().getPnrContent());
						}
					} else {
						result.setPnrOldDetails(pnrOld.getError());
					}
				}
				
				List<VeTkneTrdxPnr> pnrSps = description.getPnrSps();
				if (pnrSps != null && pnrSps.size() > 0) {
					VeTkneTrdxPnr pnrSp = null;
					for (int i = 0; i < pnrSps.size(); i++) {
						pnrSp = pnrSps.get(i);
						if ("CHILD".equals(pnrSp.getType())) {
							result.setPnrSpStatus(pnrSp.getFlag());
							if (NumberUtils.toInt(result.getPnrSpStatus()) > 0) {
								if (pnrSp.getPnrXml() != null) {
									spPnrXml = pnrSp.getPnrXml();
									n_pnr = spPnrXml.getPnrno();
									result.setPnrSpDetails(spPnrXml.getPnrContent());
								}
							} else {
								result.setPnrSpDetails(pnrSp.getError());
							}							
						}
					}
				}
				
				VeTkneTrdxPnr pnrXepnr = description.getPnrXepnr();
				if (pnrXepnr != null) {
					result.setPnrXeStatus(pnrXepnr.getFlag());
					if (NumberUtils.toInt(result.getPnrXeStatus()) > 0) {
						if (StringUtils.isNotBlank(pnrXepnr.getInfo())) {
							result.setPnrXeDetails(pnrXepnr.getInfo());
						}
					} else {
						result.setPnrXeDetails(pnrXepnr.getError());
					}
				}
				
				// TRDX运价错误信息
				String trdxErrorInfo = description.getTrdxError();
				if (StringUtils.isNotBlank(trdxErrorInfo)) {
					result.setTrdxError(trdxErrorInfo);
				}
				
				// TRDX运价信息
				if (StringUtils.isBlank(trdxErrorInfo)) {
					VeTkneTrdxPrice trdxPrice = description.getTrdxPrice();
					List<VeTkneTrdxIndex> trdxIndexs = null;
					if (trdxPrice != null) {
						trdxIndexs = trdxPrice.getTrdxIndexs();
						if (trdxIndexs != null && !trdxIndexs.isEmpty()) {
							result.setTrdxIndexs(trdxIndexs);
						}
					}
				}
 			}
			
			result.setError(result.getError() + error.toString());
		}
		
		// TRDX第一次调用时修改bpgqzt
		if ("TRDX".equals(commandType) && (StringUtils.isBlank(jpGqd.getBpgqzt()) || "0".equals(jpGqd.getBpgqzt()))) {
			jpGqd.setBpgqzt("3");
			jpGqdServiceImpl.updateJpGqdById(jpGqd);
		}
		// TRDX选了价格后修改状态
		else {
			if (result.getStatus() > 0) {
				jpGqd.setBpgqzt("1");
			} else {
				jpGqd.setBpgqzt("2");
			}
		}
		
		// 若有新PNR产生，则分离订单及修改改签单
		if (StringUtils.isNotBlank(n_pnr)) {
			jpGqd.setGqXsPnrNo(n_pnr);
			jpGqdServiceImpl.updateJpGqdById(jpGqd);
			
			// 新SP出来PNR，要分离原订单
			AsmsOrderSplitBean newOrder = new AsmsOrderSplitBean();
			if (spPnrXml == null) {
				throw new Exception("PNR分离时未获取分离XML！分离出的PNR为：【" + n_pnr + "】");
			} else {
				try {
					newOrder.setOld_pnrno(pnrno);
					newOrder.setShyhb(shyhb);
					newOrder.setDdFrom(jp_khdd.getCgly());
					getAsmsOrderSplitBean(newOrder, spPnrXml);
					jpGqdServiceImpl.newOrder(newOrder);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("SP后订单分离失败，错误：" + e.getMessage());
				}
			}
		}

		return result;
	}
	
	private static void getAsmsOrderSplitBean(AsmsOrderSplitBean newOrder, VeTkneTrdxPnrXml spPnrXml) {
		newOrder.setNew_pnrno(spPnrXml.getPnrno());
		newOrder.setPnr_nr(spPnrXml.getPnrContent());
		newOrder.setBig_pnrno(spPnrXml.getBigPnrno());
		newOrder.setDp_dpyid(newOrder.getShyhb().getBh());
		newOrder.setXg_userid(newOrder.getShyhb().getBh());
		newOrder.setCzFrom("白屏改签订单分离");
		newOrder.setDp_officeid(spPnrXml.getOffice());
		newOrder.setPnr_tlDateTime(spPnrXml.getTl());
		
		// 乘机人串
		// 返回的字符串格式：2|0,陈鹏,421223199112105735,NI,1,,,,7844877501897;7844877501897,,,,|1,何二,429001198809183331,NI,1,,,,7844877501898;7844877501898,,,,
		String cjrs = spPnrXml.getCjr();
		List<JpDdCjrBean> splitCjrs = new ArrayList<JpDdCjrBean>();
		if (StringUtils.isNotBlank(cjrs)) {
			String[] cjrList = cjrs.split("\\|");
			for (int i = 1; i < cjrList.length; i++) {
				String cjrStr = cjrList[i];
				String[] cjrInfo = cjrStr.split(",");
				
				JpDdCjrBean cjr = new JpDdCjrBean();
				cjr.setCjr(cjrInfo[1]);
				cjr.setZjhm(cjrInfo[2]);
				cjr.setZjlx(cjrInfo[3]);
				cjr.setCjrlx("1");
				splitCjrs.add(cjr);
 			}
		}
		newOrder.setCjrList(splitCjrs);
		
		// 航段串
		// 返回的格式：2|0,CZ3169,E,29APR,WUH,XIY,RR,2014-04-29 08:15:00,2014-04-29 09:30:00,,,-T3,2,|1,CZ3762,K,01MAY,XIY,WUH,RR,2014-05-01 13:20:00,2014-05-01 14:40:00,,,T3-,2,
		String hds = spPnrXml.getHd();
		List<JpDdHdBean> splitHds = new ArrayList<JpDdHdBean>();
		if (StringUtils.isNotBlank(hds)) {
			String[] hdList = hds.split("\\|");
			for (int i = 1; i < hdList.length; i++) {
				String hdStr = hdList[i];
				String[] hdInfo = hdStr.split(",");
				
				JpDdHdBean hd = new JpDdHdBean();
				hd.setCfcity(hdInfo[4]);
				hd.setDdcity(hdInfo[5]);
				hd.setCfsj(hdInfo[7]);
				hd.setDdsj(hdInfo[8]);
				hd.setXs_hbh(hdInfo[1]);
				hd.setXs_cw(hdInfo[2]);
				hd.setCfhzl(hdInfo[11]);
				hd.setHdzt(hdInfo[6]);
				splitHds.add(hd);
			}
		}
		newOrder.setHdList(splitHds);
	}
	
	/**
	 * 得到一个日期的时间，格式：HHmm
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getMMss(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
		String dateString = formatter.format(date);
		return dateString;
	}
}
