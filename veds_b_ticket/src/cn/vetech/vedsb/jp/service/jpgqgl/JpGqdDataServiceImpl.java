package cn.vetech.vedsb.jp.service.jpgqgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.BookTicketChange;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdData;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpGqdDataServiceImpl {
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private JpHdServiceImpl jpHdServiceImpl;
	
	@Autowired
	private JpGqdHdServiceImpl jpGqdHdServiceImpl;
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	
	/**
	 * 后台改签单数据修改保存
	 */
	public String updateJpGqdData(JpGqd jpGqdBean,
			String[]  jpGqdCjrIdArr,
			String[]  gqCgfyArr,
			String[]  gqXsfyArr,
			String[]  gqCgscfyArr,
			String[]  gqXsscfyArr,
			String[]  jpGqdHdIdArr,
			String[]  gqTknoArr,
			String[]  nCfsjArr,
			String[]  nDdsjArr,
			String[]  nXsHbhArr,
			String[]  nXsCwArr
			, Shyhb user, Logger logger) {
		
		JpGqd jpGqd = new JpGqd();
		jpGqd.setGqdh(jpGqdBean.getGqdh());
		jpGqd.setShbh(user.getShbh());
		
		List<Map<String, Object>> cjrList = new ArrayList<Map<String, Object>>();
		//修改改签单乘机人信息
		int cjrlen = jpGqdCjrIdArr.length;
		for (int i=0;i<cjrlen;i++) {
			JpGqdCjr jpGqdCjr = new JpGqdCjr();
			jpGqdCjr.setId(jpGqdCjrIdArr[i]);
			jpGqdCjr.setGqCgfy(new BigDecimal(gqCgfyArr[i]));
			if (gqTknoArr !=null && gqTknoArr.length != 0) {
				jpGqdCjr.setGqTkno(gqTknoArr[i]);
			}
			jpGqdCjr.setGqXsfy(new BigDecimal(gqXsfyArr[i]));
			jpGqdCjr.setGqCgly(jpGqdBean.getGqCgly());
			//采购升舱费用
			jpGqdCjr.setGqCgscfy(new BigDecimal(gqCgscfyArr[i]));
			//销售升舱费用
			jpGqdCjr.setGqXsscfy(new BigDecimal(gqXsscfyArr[i]));
			Map<String,Object> jpGqdCjrMap=Bean2Map.getMap(jpGqdCjr);
			cjrList.add(jpGqdCjrMap);
		}
		
		List<Map<String, Object>> hdList = new ArrayList<Map<String, Object>>();
		//生成改签单航段信息
		int hdlen = jpGqdHdIdArr.length;
		for (int i=0;i<hdlen;i++) {
			String[] jpGqdHdIds = StringUtils.split(jpGqdHdIdArr[i],",");
			for (int j=0;j<jpGqdHdIds.length;j++) {
				JpGqdHd jpGqdHd = new JpGqdHd();
				jpGqdHd.setId(jpGqdHdIds[j]);
				jpGqdHd.setnCfsj(nCfsjArr[i]);
				jpGqdHd.setnDdsj(nDdsjArr[i]);
				jpGqdHd.setnXsHbh(nXsHbhArr[i]);
				jpGqdHd.setnXsCw(nXsCwArr[i]);
				jpGqdHd.setSxh((short)((i+1)*(j+1)));
				Map<String,Object> jpGqdHdMap=Bean2Map.getMap(jpGqdHd);
				hdList.add(jpGqdHdMap);
			}
		}
		
		//生成改签单信息
		jpGqd.setGqCfrq(VeDate.strToDate(nCfsjArr[0]));
		jpGqd.setGqCfsj(StringUtils.substring(nCfsjArr[0], 10, nCfsjArr[0].length()));
		jpGqd.setGqCgCw(nXsCwArr[0]);
		jpGqd.setGqXsCw(nXsCwArr[0]);
		jpGqd.setGqCgHbh(nXsHbhArr[0]);
		jpGqd.setGqXsHbh(nXsHbhArr[0]);
		jpGqd.setGqlx(jpGqdBean.getGqlx());
		jpGqd.setGqzt(jpGqdBean.getGqzt());
		jpGqd.setGqyy(jpGqdBean.getGqyy());
		jpGqd.setSkzt(jpGqdBean.getSkzt());
		jpGqd.setSkkm(jpGqdBean.getSkkm());
		jpGqd.setNxr(jpGqdBean.getNxr());
		jpGqd.setNxdh(jpGqdBean.getNxdh());
		jpGqd.setNxsj(jpGqdBean.getNxsj());
		jpGqd.setGqXsPnrNo(jpGqdBean.getGqXsPnrNo());
		jpGqd.setGqCgPnrNo(jpGqdBean.getGqCgPnrNo());
		jpGqd.setGqBlr(jpGqdBean.getGqBlr());
		jpGqd.setGqBlsj(jpGqdBean.getGqBlsj());
		jpGqd.setGqCgdw(jpGqdBean.getGqCgdw());
		jpGqd.setGqCgkm(jpGqdBean.getGqCgkm());
		jpGqd.setBzbz(jpGqdBean.getBzbz());
		jpGqd.setXgly(jpGqdBean.getXgly());
		jpGqd.setXgyh(user.getBh());
		
		TicketChangeBean ticketChangeBean = new TicketChangeBean();
		Map<String, Object> gqdMap=Bean2Map.getMap(jpGqd);
		ticketChangeBean.setGqdMap(gqdMap);
		ticketChangeBean.setGqdMxMapList(cjrList);
		ticketChangeBean.setHdMapList(hdList);
		
		BookTicketChange bookTicketChange = new BookTicketChange(ticketChangeBean);
		logger.error("改签详保存入库参数：" + bookTicketChange.getParam());
		procedureServiceImpl.insertJpGqd(bookTicketChange);
		return bookTicketChange.getErrmsg();
	}
	
	/**
	 * 后台申请改签单保存入库
	 * @param jpGqdBean
	 * @param user
	 * @throws Exception 
	 */
	public String saveJpGqdDataForApply(JpGqd jpGqdBean,
			String[] gqXsfyArr, 
			String[]  khddCjrIdArr,
			String[]  jpHdIdArr,
			String[]  nCfsjArr,
			String[]  nDdsjArr,
			String[]  nXsHbhArr,
			String[]  nXsCwArr,
			Shyhb user, Logger logger) throws Exception {
		
		//生成改签单乘机人信息
		if (khddCjrIdArr == null || khddCjrIdArr.length == 0) {
			throw new Exception("未获取到乘机人信息");
		}
		//改签的乘机人（多个拼接）
		StringBuffer gqcjr = new StringBuffer();
		JpGqd jpGqd = new JpGqd();
		List<Map<String,Object>> jpGqdCjrList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> jpGqdHdList = new ArrayList<Map<String,Object>>();
		int cjrlen = khddCjrIdArr.length;
		for (int i=0;i<cjrlen;i++) {
			JpGqdCjr jpGqdCjr = new JpGqdCjr();
			JpKhddCjr khddCjr = new JpKhddCjr();
			khddCjr.setId(khddCjrIdArr[i]);
			khddCjr.setShbh(user.getShbh());
			JpKhddCjr jpKhddCjr = jpKhddCjrServiceImpl.getEntityById(khddCjr);
			if (jpKhddCjr == null || StringUtils.isBlank(jpKhddCjr.getId())) {
				throw new Exception("未找到对应正常订单乘机人信息");
			}
			jpGqdCjr.setCjr(jpKhddCjr.getCjr());
			gqcjr.append(jpKhddCjr.getCjr());
			gqcjr.append(",");
			jpGqdCjr.setCjrlx(jpKhddCjr.getCjrlx());
			jpGqdCjr.setSjhm(jpKhddCjr.getSjhm());
			jpGqdCjr.setTkno(jpKhddCjr.getTkno());
			jpGqdCjr.setXb(jpKhddCjr.getXb());
			jpGqdCjr.setZjhm(jpKhddCjr.getZjhm());
			jpGqdCjr.setZjlx(jpKhddCjr.getZjlx());
			jpGqdCjr.setZjqfg(jpKhddCjr.getZjqfg());
			jpGqdCjr.setZjyxq(jpKhddCjr.getZjyxq());
			jpGqdCjr.setGj(jpKhddCjr.getGj());
			jpGqdCjr.setXcdh(jpKhddCjr.getXcdh());
			jpGqdCjr.setSxh((short) i);
			jpGqdCjr.setGqXsfy(new BigDecimal(gqXsfyArr[i]));
			jpGqdCjr.setGqCgfy(new BigDecimal(0));
			Map<String,Object> cjrMap=Bean2Map.getMap(jpGqdCjr);
			jpGqdCjrList.add(cjrMap);
			//生成改签单航段信息
			int hdlen = jpHdIdArr.length;
			for (int j=0;j<hdlen;j++) {
				JpGqdHd jpGqdHd = new JpGqdHd();
				JpKhddHd khddhd = new JpKhddHd();
				khddhd.setId(jpHdIdArr[j]);
				khddhd.setShbh(user.getShbh());
				JpKhddHd jpKhddHd = jpKhddHdServiceImpl.getEntityById(khddhd);
				if (jpKhddHd == null || StringUtils.isBlank(jpKhddHd.getId())) {
					throw new Exception("未找到对应正常订单航段信息");
				}
				JpHd jpHd = new JpHd();
				jpHd.setDdhdid(jpKhddHd.getId());
				jpHd.setTkno(jpKhddCjr.getTkno());
				jpHd.setShbh(user.getShbh());
				JpHd jphd = jpHdServiceImpl.getJpHdByDdhdidAndTkno(jpHd);
				if (jphd == null || StringUtils.isBlank(jphd.getId())) {
					throw new Exception("未找到对应机票航段信息");
				}
				jpGqdHd.setJphdid(jphd.getId());
				jpGqdHd.setCfcity(jpKhddHd.getCfcity());
				jpGqdHd.setDdcity(jpKhddHd.getDdcity());
				jpGqdHd.setoCfhzl(jpKhddHd.getCfhzl());
				jpGqdHd.setoDdhzl(jpKhddHd.getDdhzl());
				jpGqdHd.setoCfsj(jpKhddHd.getCfsj());
				jpGqdHd.setoDdsj(jpKhddHd.getDdsj());
				jpGqdHd.setShbh(user.getShbh());
				jpGqdHd.setoFjjx(jpKhddHd.getFjjx());
				jpGqdHd.setoHdzt("XX");
				jpGqdHd.setnHdzt("HK");
				jpGqdHd.setoXsCw(jpKhddHd.getXsCw());
				jpGqdHd.setoXsHbh(jpKhddHd.getXsHbh());
				jpGqdHd.setoXsTgq(jpKhddHd.getXsTgq());
				jpGqdHd.setnCfsj(nCfsjArr[i]);
				jpGqdHd.setnDdsj(nDdsjArr[i]);
				jpGqdHd.setnXsHbh(nXsHbhArr[i]);
				jpGqdHd.setnXsCw(nXsCwArr[i]);
				jpGqdHd.setSxh((short)i);
				Map<String,Object> hdMap=Bean2Map.getMap(jpGqdHd);
				jpGqdHdList.add(hdMap);
			}
		}
	//	jpGqd.setCjrList(jpGqdCjrList);
	//	jpGqd.setHdList(jpGqdHdList);
		
		//生成改签单信息
		String ddbh = jpGqdBean.getDdbh();
		JpKhdd khdd = new JpKhdd();
		khdd.setDdbh(ddbh);
		khdd.setShbh(user.getShbh());
		JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(khdd);
		if (jpKhdd == null || StringUtils.isBlank(jpKhdd.getDdbh())) {
			throw new Exception("未找到对应正常订单信息");
		}
		jpGqd.setHc(jpKhdd.getHc());
		jpGqd.setHkgs(jpKhdd.getHkgs());
		jpGqd.setGqlx(jpGqdBean.getGqlx());
		jpGqd.setGqyy(jpGqdBean.getGqyy());
		jpGqd.setSkzt(jpGqdBean.getSkzt());
		jpGqd.setSkkm(jpGqdBean.getSkkm());
		jpGqd.setNxr(jpGqdBean.getNxr());
		jpGqd.setNxdh(jpGqdBean.getNxdh());
		jpGqd.setNxsj(jpGqdBean.getNxsj());
		jpGqd.setWbdh(jpGqdBean.getWbdh());
		jpGqd.setCfrq(jpKhdd.getCfrq());
		jpGqd.setCfsj(jpKhdd.getCfsj());
		jpGqd.setDdbh(ddbh);
		jpGqd.setCjr(gqcjr.substring(0, gqcjr.length()-1));
		jpGqd.setCjrs((short) cjrlen);
		jpGqd.setDdsj(VeDate.getNow());
		jpGqd.setDdyh(user.getBh());
		jpGqd.setDdbm(user.getShbmid());
		jpGqd.setFaid(jpKhdd.getFaid());
		jpGqd.setGngj(jpKhdd.getGngj());
		jpGqd.setGqzt(JpGqd.GQZT_YQR);
		jpGqd.setSfzdd(JpGqd.SFZDD_S);
		jpGqd.setShbh(user.getShbh());
		jpGqd.setWbdh(jpKhdd.getWbdh());
		jpGqd.setWdDdlx(jpKhdd.getWdDdlx());
		jpGqd.setWdid(jpKhdd.getWdid());
		jpGqd.setWdpt(jpKhdd.getWdpt());
		jpGqd.setWdZcdm(jpKhdd.getWdZcdm());
		jpGqd.setWdZclx(jpKhdd.getWdZclx());
		jpGqd.setCgCw(jpKhdd.getCgCw());
		jpGqd.setXsCw(jpKhdd.getXsCw());
		jpGqd.setCgHbh(jpKhdd.getCgHbh());
		jpGqd.setXsHbh(jpKhdd.getXsHbh());
		jpGqd.setCgHkgsPnr(jpKhdd.getCgHkgsPnr());
		jpGqd.setXsHkgsPnr(jpKhdd.getXsHkgsPnr());
		jpGqd.setCgPnrNo(jpKhdd.getCgPnrNo());
		jpGqd.setXsPnrNo(jpKhdd.getXsPnrNo());
		jpGqd.setZcLy(jpKhdd.getZcLy());
		jpGqd.setZcQd(jpKhdd.getZcQd());
		jpGqd.setGqCfrq(VeDate.strToDate(nCfsjArr[0]));
		jpGqd.setGqCfsj(StringUtils.substring(nCfsjArr[0], 10, nCfsjArr[0].length()));
		jpGqd.setGqCgCw(nXsCwArr[0]);
		jpGqd.setGqXsCw(nXsCwArr[0]);
		jpGqd.setGqCgHbh(nXsHbhArr[0]);
		jpGqd.setGqXsHbh(nXsHbhArr[0]);
		jpGqd.setGqCgPnrNo(jpKhdd.getCgPnrNo());
		jpGqd.setGqXsPnrNo(jpKhdd.getXsPnrNo());
		
		jpGqd.setXgly("后台-改签单申请");
		jpGqd.setXgyh(user.getBh());
		jpGqd.setDzshZt("0");
		jpGqd.setYwshZt("0");
		TicketChangeBean ticketChangeBean =new TicketChangeBean();
		
		Map<String,Object> gqdMap=Bean2Map.getMap(jpGqd);
		ticketChangeBean.setGqdMap(gqdMap);
		ticketChangeBean.setGqdMxMapList(jpGqdCjrList);
		ticketChangeBean.setHdMapList(jpGqdHdList);
		
		BookTicketChange bookTicketChange = new BookTicketChange(ticketChangeBean);
		logger.error("改签单入库参数：" + bookTicketChange.getParam());
		procedureServiceImpl.insertJpGqd(bookTicketChange);
		return bookTicketChange.getErrmsg();
	}
	
	public JpGqdData getJpGqdDataForApply(JpJp jpJp) throws Exception{
		JpGqdData gqdData = new JpGqdData();
		String ddbh = jpJp.getDdbh();
		String shbh = jpJp.getShbh();
		boolean isDdbhBlank = StringUtils.isBlank(ddbh);
		boolean isTknoBlank = StringUtils.isBlank(jpJp.getTkno());
		if (isDdbhBlank && isTknoBlank) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("xsPnrNo", jpJp.getXsPnrNo());
			param.put("shbh", shbh);
			param.put("cjr", jpJp.getCjr());
			param.put("sjrs", jpJp.getKsrq());
			param.put("sjrz", jpJp.getJsrq());
			List<JpKhdd> jpKhddList = jpKhddServiceImpl.getJpKhddForGqApply(param);
			if (jpKhddList.size() == 0) {
				throw new Exception("未获取到正常单信息");
			} else if (jpKhddList.size() == 1) {
				ddbh = jpKhddList.get(0).getDdbh();
			} else {
				attachService.wdzl("wdid").execute(jpKhddList);
				gqdData.setJpKhddList(jpKhddList);
				gqdData.setMoreOrder(true);
				return gqdData;
			}
		} else if (!isTknoBlank) {
			 JpJp jpjp = jpJpServiceImpl.getJpByTkno(jpJp);
			 if (jpjp == null) {
				 throw new Exception("未获取到机票信息");
			 } else {
				 ddbh = jpjp.getDdbh();
			 }
		}
		
		if (StringUtils.isBlank(ddbh)) {
			throw new Exception("未获取到订单编号");
		} else {
			JpKhdd jpKhdd = new JpKhdd();
			jpKhdd.setDdbh(ddbh);
			jpKhdd.setShbh(shbh);
			jpKhdd = jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			if (jpKhdd == null) {
				throw new Exception("未获取到正常单信息");
			} else {
				List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
				
				if (cjrList == null || cjrList.isEmpty()) {
					throw new Exception("未获取到正常单乘机人信息");
				}
				Iterator<JpKhddCjr> cjrItr = cjrList.iterator();
				while (cjrItr.hasNext()) {
					JpKhddCjr cjr = cjrItr.next();
					String cjrName = cjr.getCjr();
					jpJp.setCjr(cjrName);
					jpJp.setDdbh(ddbh);
					List<JpJp> jpList = jpJpServiceImpl.getJpByCjr(jpJp);
					if (jpList == null || jpList.isEmpty()) {
						cjrItr.remove();
					} else {
						cjr.setJpList(jpList);
						this.checkNfgq(cjr);
					}	
				} 
				
				if (cjrList == null || cjrList.isEmpty()) {
					throw new Exception("未获取到正常单乘机人机票信息");
				}
				
				List<JpKhddHd> ddhdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
				if (ddhdList == null || ddhdList.isEmpty()) {
					throw new Exception("未获取到正常单航段信息");
				}
				attachService.wdzl("wdid").execute(jpKhdd);
				gqdData.setJpKhdd(jpKhdd);
				gqdData.setJpKhddCjrList(cjrList);
				gqdData.setJpKhddHdList(ddhdList);
				
			}
		}
		return gqdData;
	}
	
	/**
	 * 验证当前乘机人的所有航段是否能够改签，进而判断该乘机人能否改签
	 * @param cjr
	 * @throws Exception 
	 */
	public void checkNfgq(JpKhddCjr cjr) throws Exception {
		List<JpJp> jpList = cjr.getJpList();
		Iterator<JpJp> tkItr = jpList.iterator();
		Shyhb user = SessionUtils.getShshbSession();
		int zzgqjps = 0;
		int gqwcjps = 0;
		int wgqjps = 0;
		int tfjps = 0;
		while (tkItr.hasNext()) {
			JpJp jpjp = tkItr.next();
			JpHd jphd = new JpHd();
			jphd.setTkno(jpjp.getTkno());
			jphd.setShbh(user.getShbh());
			List<JpHd> jphdList = jpHdServiceImpl.getJpHdByTkno(jphd);
			if (jphdList == null || jphdList.isEmpty()) {
				throw new Exception("未获取到机票航段信息");
			} else {
				//能改签航段数
				int zzgqhds = 0;
				int gqwchds = 0;
				int wgqhds = 0;
				int tfhds = 0;
				Iterator<JpHd> jphdItr = jphdList.iterator();
				while (jphdItr.hasNext()) {
					JpHd jpHd = jphdItr.next();
					//判断该航段是否申请了退废
					if (StringUtils.isNotBlank(jpHd.getTpmxid())) {
						int tpdnum = jpTpdMxServiceImpl.getJpTpdMxJl(jpHd.getTpmxid(), user.getShbh());
						if (tpdnum >= 1) {
							jpHd.setNfgq(JpJp.NFGQ_YTF);
							tfhds++;
						} else {
							jpHd.setNfgq(JpJp.NFGQ_WTF);
						}
					} else {
						//判断该航段是否改签中
						String jphdid = jpHd.getId();
						JpGqdHd jpGqdHd = new JpGqdHd();
						jpGqdHd.setJphdid(jphdid);
						jpGqdHd.setShbh(user.getShbh());
						List<Map<String,Object>> jpgqdHdList = jpGqdHdServiceImpl.getGqHdByJpHdId(jpGqdHd);
						if (jpgqdHdList == null || jpgqdHdList.isEmpty()) {
							jpHd.setNfgq(JpJp.NFGQ_WGQ);
							wgqhds++;
						} else {
							/**
							 * 根据申请改签的时间升序排列，取最近的一次改签申请的状态来判断机票能否改签
							 */
							Map<String,Object> lastJpGqdHd = jpgqdHdList.get(jpgqdHdList.size() - 1);
							String gqzt = String.valueOf(lastJpGqdHd.get("GQZT"));
							if (JpGqd.GQZT_YGQ.equals(gqzt)) {
								jpHd.setNfgq(JpJp.NFGQ_YGQ);
								gqwchds++;
							} else {
								jpHd.setNfgq(JpJp.NFGQ_GQZ);
								zzgqhds++;
							}
						}
					}
				}
				jpjp.setHdList(jphdList);
				/**
				 * 如果有一个航段改签完成或者未改签，则这张机票就可以改签
				 */
				if (wgqhds >= 1) {
					jpjp.setNfgq(JpJp.NFGQ_WGQ);
					wgqjps++;
				} else if (gqwchds >= 1) {
					jpjp.setNfgq(JpJp.NFGQ_YGQ);
					gqwcjps++;
				} else if (zzgqhds >= 1) {
					jpjp.setNfgq(JpJp.NFGQ_GQZ);
					zzgqjps++;
				} else if (tfhds >=1 ) {
					jpjp.setNfgq(JpJp.NFGQ_YTF);
					tfjps++;
				} else if (tfhds == 0) {
					jpjp.setNfgq(JpJp.NFGQ_WTF);
				}
			}
		}
		/**
		 * 如果有一张机票能够改签，则这个乘机人就可以改签
		 */
		if (wgqjps >= 1) {
			cjr.setNfgq(JpJp.NFGQ_WGQ);
 		} else if (gqwcjps >= 1) {
			cjr.setNfgq(JpJp.NFGQ_YGQ);
		} else if (zzgqjps >= 1) {
			cjr.setNfgq(JpJp.NFGQ_GQZ);
		} else if (tfjps >= 1) {
			cjr.setNfgq(JpJp.NFGQ_YTF);
		} else if (tfjps == 0) {
			cjr.setNfgq(JpJp.NFGQ_WTF);
		}
	}
	
	/**
	 * 通过机票航段id获取航段改签记录
	 * @return
	 */
	public Map<String,List<Map<String,Object>>> getJpGqdHdJlByJpHdid(String[] jp_hdids) {
		if (jp_hdids == null || jp_hdids.length == 0) {
			return null;
		}
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, List<Map<String,Object>>> hdMap = new HashMap<String, List<Map<String,Object>>>();
		JpGqdHd gqhd = new JpGqdHd();
		gqhd.setShbh(user.getShbh());
		for (String jp_hdid : jp_hdids) {
			gqhd.setJphdid(jp_hdid);
			List<Map<String,Object>> hdList = jpGqdHdServiceImpl.getGqHdByJpHdId(gqhd);
			if (hdList != null && !hdList.isEmpty()) {
				JpHd jphd = new JpHd();
				jphd.setShbh(user.getShbh());
				jphd = jpHdServiceImpl.getJpHdById(jphd);
				hdMap.put(jphd.getId(), hdList);
			}
		}
		return hdMap;
	}
}
