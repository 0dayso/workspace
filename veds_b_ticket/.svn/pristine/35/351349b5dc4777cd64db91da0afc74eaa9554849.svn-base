package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.autoetdz.EtdzEterm;
import org.vetech.core.business.pid.api.autoetdz.EtdzParam;
import org.vetech.core.business.pid.api.autoetdz.EtdzResult;
import org.vetech.core.business.pid.api.autoetdz.EtdzResultTkno;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.Threads;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpCssz;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.entity.office.JpOffice;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpzdbwServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXeZw;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgAutoEtdzServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 自动出票接口 1.出票前检查 2.调用出票接口 3.调用转机票接口和信用额度接口 4.所有错误状态返回都是0，-1
 * 
 */
@Service
public class AutoEtdz { 
	
	@Autowired
	private PkgAutoEtdzServiceImpl pkgAutoEtdzServiceImpl;
	@Autowired
	private JpKhddKzServiceImpl jpKhddKzServiceImpl;
	@Autowired
	private JpPzServiceImpl  jpPzServiceImpl;
	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;
	@Autowired
	private JpZdcpCsszServiceImpl jpZdcpCsszServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpzdbwServiceImpl jpzdbwServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	
	
	//出票时锁订单
	static Map<String,Object> DDHMAP=new ConcurrentHashMap<String,Object>();

	public static final int FAULSE = -1; // 失败

	public static final int SUCCESS = 0; // 成功

	public static final int RETURNPAT = 1; // 如果PAT价格有重复的话返回PAT选择列表页面
	
	public static final int RETURNCP = 2; //德付通支付成功,但是ETDZ执行失败!
	
	public static final int TZCP = -2; //停止出票，列如已出过票

	/**
	 * 1.检查适用条件 2.判断是不是分销商是的话，检查信用额度 3.生成pat,ei,office -- 返回是否能够，及调用PID接口的相关信息 function f_auto_ifetdz_pat (p_ddbh
	 * varchar2,p_userid varchar2,p_pat out varchar2,p_ei out varchar2,p_office out varchar2, p_pnr out varchar2,p_xsj
	 * out varchar2,p_rs out varchar2, p_error out varchar2)
	 * 
	 * @return
	 */
	
	public Map<String,Object> pre_etdz(EtdzParam etdzParam,JpZdcpJk jk) {
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("sfzdcp", "1");//默认自动出票
		Map<String, Object> param = new HashMap<String, Object>();
		String error="";
		param.put("p_ddbh", etdzParam.getDdbh());
		param.put("p_shbh", etdzParam.getShbh());
		param.put("p_userid", etdzParam.getYhbh());
		param.put("p_version", "1");
		param.put("p_cpqdlx", etdzParam.getCpqdlx());
		jk.add("出票前检查pre_etdz入参:【" + param + "】");
		try {
			pkgAutoEtdzServiceImpl.checkEtdz(param);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("zt", FAULSE);
			m.put("error", e.getMessage());
			return m;
		}
		
		int flag = (Integer) param.get("result");
		error = VeStr.getValue(param, "p_error");
		if (flag == FAULSE) {
			m.put("zt", flag);
			m.put("error", error);
			return m;
		}
		
		etdzParam.setPat(VeStr.getValue(param, "p_pat"));
		etdzParam.setEi(VeStr.getValue(param, "p_ei"));
		String Office = VeStr.getValue(param, "p_office");
		if (StringUtils.isBlank(etdzParam.getOffice())) {
			etdzParam.setOffice(Office);
		}
		etdzParam.setPnrno(VeStr.getValue(param, "p_pnr"));
		etdzParam.setScny(VeStr.getValue(param, "p_xsj")); // 销售价 格式：总运价,总燃油税,总基建
		etdzParam.setPersons(VeStr.getValue(param, "p_rs"));
		String dpaypassword = VeStr.getValue(param, "p_mm");
		
		//手动出票 ,后台没有返回BOP密码和Office 
		if(!etdzParam.getOffice().contains(",")){
			m.put("sfzdcp", "0");//手动出票
			JpPz jppz=jpPzServiceImpl.getJpPzByOfficeid(etdzParam.getOffice(), etdzParam.getShbh());
			String pz=etdzParam.getCpqdlx();
			if(jppz != null){
				if("BOP".equals(pz)){
					JpOffice jo= new JpOffice();
					jo.setShbh(etdzParam.getShbh());
					jo.setOfficeid(etdzParam.getOffice());
					jo.setSfbopcp("1");
					jo=jpOfficeServiceImpl.getJpOffice(jo);
					if (jo != null) {
						dpaypassword=jo.getBopmm();
					}
					etdzParam.setOffice(jppz.getOfficeid()+","+jppz.getAgent()+",");
				}else if("BSP".equals(pz)){
					etdzParam.setOffice(jppz.getOfficeid()+","+jppz.getAgent()+","+jppz.getPrintno());
				}
			}
		}
		
		if ("BOP".equals(etdzParam.getCpqdlx()) && StringUtils.isBlank(dpaypassword)) {
			if (StringUtils.isBlank(error)) {
				error = "错误:未获取到BOP出票渠道对应的BOP支付密码!请检查设置！";
			}
			m.put("zt", FAULSE);
			m.put("error", error);
			return m;
		}
		etdzParam.setDpaypassword(dpaypassword);
		m.put("zt", flag);
		return m;
	}
	/**
	 * - <ETDZRESULT> <FLAG>0000</FLAG> <ERRTXT>ETDZ成功</ERRTXT>
	 * <P>
	 * 曹连胜
	 * </P>
	 * <TN>4791710219499</TN> <ACNY>1580.00</ACNY> <OFFICEID>ZUH212</OFFICEID> <PRINTERID>1</PRINTERID> <PID>80489</PID> -
	 * <TSL> <TKTNUM>479-1710219499</TKTNUM> <FORMCITY>CAN</FORMCITY> <TOCITY>CGQ</TOCITY> <COLLECT>1460.00</COLLECT>
	 * <TAXS>120.00</TAXS> <COMM>3.00</COMM> <AGENT>75599</AGENT> </TSL> </ETDZRESULT>
	 */
	public Map<String,Object> etdz(EtdzParam etdzParam,JpZdcpJk jk) {
		EtdzResult etdzResult=new EtdzResult();
		Map<String,Object> m=new HashMap<String, Object>();
		String returnEterm="";
		String error ="";
		String newScny = etdzParam.getScny();
		if (StringUtils.isNotBlank(etdzParam.getPatvalue())) {
			// 3080.00,100.00,280.00;07 M+M FARE:CNY3080.00 TAX:CNY100.00 YQ:CNY280.00 TOTAL:3460.00
	        //SFN:01   02 T FARE:CNY400.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:450.00 
			String patval2 = etdzParam.getPatvalue().replace("TEXEMPTYQ", "CNY0.00");
			//etdzParam.setPatvalue(patval);
			newScny = "";
			String reg = "CNY(\\d+\\.\\d{2})";
			Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			Matcher mp = p.matcher(patval2);
			while (mp.find()) {
				String cny = mp.group(1);
				newScny += cny + ",";
			}
			newScny = newScny.substring(0, newScny.length() - 1);
			newScny = newScny + ";" + etdzParam.getPatvalue();
			// newScny=人数*单价
			int rs = NumberUtils.toInt(etdzParam.getPersons());
			if (rs > 1) {
				String totalScny = newScny.split(";")[0];
				double tpj = NumberUtils.toDouble(totalScny.split(",")[0]) * rs;
				double tjsf = NumberUtils.toDouble(totalScny.split(",")[1]) * rs;
				double ttax = NumberUtils.toDouble(totalScny.split(",")[2]) * rs;
				String tpjS = (tpj + "").replaceAll("\\.0+", ".00");
				String tjsfS = (tjsf + "").replaceAll("\\.0+", ".00");
				String ttaxS = (ttax + "").replaceAll("\\.0+", ".00");
				newScny = tpjS + "," + tjsfS + "," + ttaxS + newScny.replace(totalScny, "");
			}
			Map<String,Object> m_pre_etdz=pre_etdz(etdzParam,jk);
			int zt=(Integer) m_pre_etdz.get("zt");
			if (zt == FAULSE) {// 检查适用条件失败,如果是-2,跳到选择PAT和EI页面
				m.put("zt", zt);
				return m;
			}
		}
		try {
			EtdzEterm etdzEterm = new EtdzEterm(etdzParam);
			LogUtil.getAutoEtdz().error("全自动出票指令入参=>"+etdzParam.toXml());
			jk.add("调用etdz接口入参:【"+"pnr:"+etdzParam.getPnrno()+"|office号:"+etdzParam.getOffice()+"|IP地址:"+etdzParam.getIp()
					+"|端口号:"+etdzParam.getPort()+"|订单编号:"+etdzParam.getDdbh()+"|是否自动选择低价出票:"+etdzParam.getSelectlowprice()+"】");
			returnEterm = etdzEterm.etdz();
			m.put("returnEterm", returnEterm);
			LogUtil.getAutoEtdz().error("全自动出票指令回参=>"+returnEterm);
		} catch (Exception e1) {
			e1.printStackTrace();
			m.put("zt", FAULSE);
			m.put("error", e1.getMessage());
			return m;
		}
		if (StringUtils.isBlank(returnEterm)) {
			m.put("zt", FAULSE);
			m.put("error", "错误:出票后返回结果为空!");
			return m;
		}
		try {
			//纯文字直接返回
			if(!returnEterm.contains("<")){
				m.put("zt", FAULSE);
				m.put("error",returnEterm);
				return m;
			}
			
			ParseXml xmlParse=new ParseXml(returnEterm);
			String flag =xmlParse.text("FLAG");
		    error = StringUtils.trimToEmpty(xmlParse.text("ERRTXT"));
			if ("0000".equals(flag)) {
				String p = xmlParse.text("P");
				String tn =xmlParse.text("TN");
				String acny = xmlParse.text("ACNY");
				if (StringUtils.isBlank(p)) {
					error += "错误:乘机人列表为空!";
					m.put("zt", FAULSE);
					m.put("error",error);
					return m;
				}
				if (StringUtils.isBlank(tn)) {
					error += "错误:票号列表为空!";
					m.put("zt", FAULSE);
					m.put("error",error);
					return m;
				}
				if (StringUtils.isBlank(acny) || "0".equals(acny)) {
//					error += "错误:出票金额为空或者为0!";  //不管出票金额，只要有票号表示出票成功
//					return FAULSE;
				}
				String[] cjrList = p.split(",");
				String[] tnList = tn.split(",");
				// 判断返回的2个列表的长度是否相等,不等返回错误
				if (cjrList.length != tnList.length) {
					error += "错误:乘机人列表和票号列表的数量不等";
				}
				etdzResult.setAcny(acny);

				int bs = tnList.length / cjrList.length;
				for (int i = 0; i < cjrList.length; i++) {
					if (StringUtils.isBlank(cjrList[i])) {
						error += "错误:乘机人列表中含有空的姓名," + p;
						m.put("zt", FAULSE);
						m.put("error",error);
						return m;
					}
					if (StringUtils.isBlank(tnList[i])) {
						error += "错误:票号列表中含有空的票号," + tn;
						m.put("zt", FAULSE);
						m.put("error",error);
						return m;
					}
					EtdzResultTkno etdzResultTkno = new EtdzResultTkno();
					etdzResultTkno.setCjrName(cjrList[i]);
					if (bs == 1) {// 票数和乘机人数相等
						etdzResultTkno.setTkno(tnList[i]);
					} else {// 一个乘机人多张票。如果重复只显示一张
						String tknos = "";
						for (int j = 0; j < bs; j++) {
							String tkno = tnList[i * bs + j];
							if (tknos.indexOf(tkno) < 0) {
								tknos += tkno + ",";
							}
						}
						tknos = tknos.substring(0, tknos.length() - 1);
						etdzResultTkno.setTkno(tknos);
					}
					etdzResult.add(etdzResultTkno);
				}

			} else if ("0099".equals(flag)) { // 出现相同的运价
				error = xmlParse.text("ERRTXT");
				m.put("zt", RETURNPAT);
				m.put("error",error);
				return m;
			} else if("0100".equals(flag)) {//航信分配了票号，但可能出票未成功，但也可能成功，需要手工验证
				error = xmlParse.text("ERRTXT");
				m.put("zt", RETURNCP);
				m.put("error",error);
				return m;
			}else {
				String acny = xmlParse.text("ACNY");
				if ("".equals(acny) || "0".equals(acny)) {
					error += "错误:出票不成功!";
				}
				error  += "corder:" + flag + "," ;
				m.put("zt", FAULSE);
				m.put("error",error);
				return m;
			}

		} catch (Exception e) {
			e.printStackTrace();
			error = "自动出票:解析xml错误" + returnEterm;
			System.out.println(error);
			m.put("zt", FAULSE);
			m.put("error",error);
			return m;
		}
		// 解析返回数据，并返回一个结构.
		m.put("zt", SUCCESS);
		return m;
	}

	/**
	 * 1.调用转机票接口
	 * 
	 */
	public Map<String,Object> etdzSuccess(String cp_xml, String ddbh, String ei, String userid,String shbh,String bmbh,String yhbh,JpZdcpJk jk) {
		Map<String, Object> param=new HashMap<String, Object>();
		Map<String, Object> m=new HashMap<String, Object>();
		String error="";
		param.put("p_xml", cp_xml);
		param.put("p_ddbh", ddbh);
		param.put("p_shbh", shbh);
		param.put("p_ei", ei);
		param.put("p_userid", yhbh);
		param.put("p_bmbh", bmbh);
		jk.add("出票成功开始转机票,转机票etdzSuccess入参:【"+param+"】");
		try {
			pkgAutoEtdzServiceImpl.autoEtdz(param);
		} catch (UncategorizedSQLException e) {
			error = e.getMessage();
			e.printStackTrace();
			m.put("zt", FAULSE);
			m.put("error", error);
			return m;
		}catch (Exception e) {
			error = e.getMessage();
			e.printStackTrace();
			m.put("zt", FAULSE);
			m.put("error", error);
			return m;
		}
		int flag = (Integer) param.get("result");
		error = VeStr.getValue(param, "p_error");
		m.put("zt", flag);
		m.put("error", error);
		return m;
	}
	/**
	 * 自动转机票
	 * 
	 * @return
	 */
	public Map<String,Object> auto_etdz(EtdzParam etdzParam,JpZdcpJk jk) {
		Object lock = DDHMAP.get(etdzParam.getDdbh());
		if (lock == null) {
			lock = new Date();
			DDHMAP.put(etdzParam.getDdbh(), lock);
		}
		synchronized (lock) {
			/**
			 * 检查适用条件以及生成pat,和ei和office
			 */
			int zt = 0;
			Map<String,Object> m=pre_etdz(etdzParam,jk);
			zt=(Integer) m.get("zt");
			String sfzdcp = VeStr.getValue(m, "sfzdcp");//1自动出票  0手动出票
			System.out.print("出票前检查" + zt);
			if (zt == FAULSE || zt == -2) {// 检查适用条件失败,如果是-2,跳到选择PAT和EI页面
				return m;
			}
		
			/**
			 * 第一次执行出票
			 */
			m = etdz(etdzParam, jk);
			zt = (Integer) m.get("zt");// -1是失败
			String errmsg=VeStr.getValue(m, "error");
			
			/**
			 * 第一次出票失败处理
			 */
			if(StringUtils.isNotBlank(errmsg) && zt == FAULSE){
				m = firstCpFailHandle(etdzParam, errmsg,jk);
				zt = (Integer) m.get("zt");// -1是失败  -2是停止出票
				if (zt == TZCP) {
                   return m;
				}
			}
			
			/**
			 * 自动出票失败后进行重试
			 */
			LogUtil.getAutoEtdz().error("PNR:"+etdzParam.getPnrno()+("1".equals(sfzdcp) ? " 自动出票" : " 手动出票")+" 第一次出票返回["+m+"]");
			if("1".equals(sfzdcp) && zt == FAULSE ){
				JpZdcpCssz jzc = new JpZdcpCssz();
				jzc.setCgly(etdzParam.getCpqdlx());//采购来源,BOP/BSP等
				jzc.setZt("1");//0禁用 1启用 2删除
				List<JpZdcpCssz> jzcList = jpZdcpCsszServiceImpl.getMyBatisDao().selectAllJpZdcpCssz(jzc);
				jzc=isCpsbCs(jzcList, errmsg);
				if (jzc != null) {
				    int loop = jzc.getCscs();//取第一条记录的重试次数
				    int sjjg=jzc.getSjjg();  //取第一条记录的时间间隔
					for(int i = 0;i < loop;i++){
						LogUtil.getAutoEtdz().error("PNR:"+etdzParam.getPnrno()+"出票失败,进行第"+(i+1)+"次重试出票,返回信息["+m+"]");
						Threads.sleep(sjjg*1000);
						m = etdz(etdzParam, jk);
						zt = (Integer) m.get("zt");// -1是失败
						errmsg= VeStr.getValue(m, "error");
						if(zt == FAULSE){
							if (isCpsbCs(jzcList, errmsg) == null) {
						    	break;
						    }
						}else{
							LogUtil.getAutoEtdz().error("PNR:"+etdzParam.getPnrno()+" 进行第"+(i+1)+"次重试出票成功,返回信息["+m+"]");
							break;
						}
					}
				}else{
					m.put("error", "失败原因:<font color='red'>"+errmsg+"</font>");
				}
			}
			
			updateddzt(zt, etdzParam.getShbh(),etdzParam.getDdbh(), VeStr.getValue(m, "error"));
			
			if (zt == FAULSE) {
				m.put("zt", FAULSE);
				return m;
			}
			if (zt == RETURNCP) {
				m.put("zt", RETURNCP);
				return m;
			}
			// 返回PAT数据
			if (zt == RETURNPAT) {
				m.put("zt", RETURNPAT);
				return m;
			}
			
			m= etdzSuccess(VeStr.getValue(m, "returnEterm"), etdzParam.getDdbh(),etdzParam.getEi(), etdzParam.getYhbh(), etdzParam.getShbh(),etdzParam.getBmbh(),etdzParam.getYhbh(),jk);
			zt =(Integer) m.get("zt");
			if (zt != SUCCESS) {
				m.put("zt", FAULSE);
				return m;
			}
			m.put("zt", SUCCESS);
			return m;
			
		}
	}

	/**
	 * 失败是否重试  返回 null 不需要重试
	 * @param jzcList
	 * @param errmsg
	 * @return
	 */
    public JpZdcpCssz  isCpsbCs(List<JpZdcpCssz> jzcList,String errmsg){
    	if(CollectionUtils.isEmpty(jzcList)){
    		return null;
    	}
    	for(JpZdcpCssz cssz:jzcList){
			if(errmsg.contains(cssz.getCwxx())){
				return cssz;
			}
		}
    	return null;
    }
    
	
	public void updateddzt(int zt,String shbh,String ddbh,String yy){
		try {
			JpKhddKz jpKhddKz = new JpKhddKz();
			jpKhddKz.setDdbh(ddbh);
			jpKhddKz.setShbh(shbh);
			jpKhddKz.setZdcpzt(zt == 0 ? "1" : "2");
			jpKhddKz.setZdcpsbyy(VeStr.substrByte(yy, 350));
			jpKhddKzServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpKhddKz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第一次出票失败进行处理
	 * @param errmsg
	 */
	public Map<String,Object> firstCpFailHandle(EtdzParam etdzParam,String errmsg,JpZdcpJk jk){
		Map<String,Object> m=new HashMap<String, Object>();
		
		String shbh = etdzParam.getShbh();
		String ddbh = etdzParam.getDdbh();
		try {
			m.put("zt", TZCP);//默认需要停止出票
			if(errmsg.contains("需要授权")){
				m.put("error", errmsg);
				return m;
			}
			
			/**
			 * PNR状态NO时进行补位,和PNR已出票处理
			 */
			if (!errmsg.contains("航班座位已经被NO掉了!") && !errmsg.contains("PNR已经被出过票!")) {
				m.put("zt", FAULSE);
				m.put("error", errmsg);
				return m;
			}
			
			JpKhdd jpKhdd = new JpKhdd();
			jpKhdd.setShbh(shbh);
			jpKhdd.setDdbh(ddbh);
			jpKhdd = jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			if (jpKhdd == null) {
				m.put("error", "订单编号【"+ddbh+"】对应的订单不存在!");
				return m;
			}
			String cg_pnrno = jpKhdd.getCgPnrNo();
			// 假编码
			if (cg_pnrno.startsWith("O")) {
				m.put("error", "订单编号【"+ddbh+"】对应PNR["+cg_pnrno+"]是假PNR");
				return m;
			}
			
			// 订单乘机人信息
			List<JpKhddCjr> jpKhddCjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
			if (CollectionUtils.isEmpty(jpKhddCjrList)) {
				m.put("error", "订单编号【"+ddbh+"】对应的乘机人信息不存在!");
				return m;
			}

			// 航段信息
			List<JpKhddHd> khddHdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			if (CollectionUtils.isEmpty(khddHdList)) {
				m.put("error", "订单编号【"+ddbh+"】对应的航段信息不存在!");
				return m;
			}

			/**
			 * 取Office配置
			 */
			List<JpOffice> officelist = jpOfficeServiceImpl.selectJpOfficeByShbh(shbh);
			if (CollectionUtils.isEmpty(officelist)) {
				m.put("error", "商户编号【"+shbh+"】对应Office配置不存在");
				return m;
			}
			JpOffice jpoffice = officelist.get(0);

			// RT获取PNR内容
			PnrRtParam pnrRtParam = new PnrRtParam();
			pnrRtParam.setShbh(shbh);
			pnrRtParam.setUserid(etdzParam.getUserid());
			pnrRtParam.setPassword(etdzParam.getPass());
			pnrRtParam.setUrl(etdzParam.getUrl());
			pnrRtParam.setPnrno(cg_pnrno);
			Pnr pnrObject = null;
			try {
				pnrObject = IbeService.rtPnr(pnrRtParam);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// RT失败
			if (pnrObject == null) {
				m.put("error", "订单编号【"+ddbh+"】对应PNR["+cg_pnrno+"]RT返回内容为空!");
				return m;
			}

			if (errmsg.contains("航班座位已经被NO掉了!")) {
				JpPtLog jpPtLog = new JpPtLog();
				JpPz jppz = new JpPz();
				jppz.setPzIp(etdzParam.getIp());
				jppz.setPzPort(etdzParam.getPort());
				jppz.setOfficeid(etdzParam.getOffice());
				String result=jpzdbwServiceImpl.jpzdbw(pnrObject, jppz, jpPtLog,etdzParam.getUserid(), ddbh);
				m.put("zt", "1".equals(result) ? FAULSE : TZCP);//补位成功可以继续出票
				if(!"1".equals(result)){
					m.put("error","订单编号【"+ddbh+"】对应PNR["+cg_pnrno+"]补位失败!");
					jk.add("PNR["+cg_pnrno+"]<font color='red'>补位失败!</font>");
				}else{
					jk.add("PNR["+cg_pnrno+"]<font color='green'>补位成功!</font>");
				}
                 
			} else if (errmsg.contains("PNR已经被出过票!")) {
				// 已经出过票的进行转机票操作
				List<PnrCjr> pnrcjrList = pnrObject.getCjrlist();
				if (CollectionUtils.isEmpty(pnrcjrList)) {
					m.put("error", "订单编号【"+ddbh+"】对应PNR["+cg_pnrno+"]解析的乘机人为空!");
					return m;
				}
				Map<String, String> cjrTkno = new HashMap<String, String>();
				for (PnrCjr pc : pnrcjrList) {
					String Tk = StringUtils.trimToEmpty(pc.getTkno());
					if (StringUtils.isNotBlank(Tk) && Tk.length() == 13) {
						pc.setTkno(Tk.substring(0, 3) + "-" + Tk.substring(3, 13));
					}
					String key = pc.getCjr();
					String tkno = cjrTkno.get(key);
					cjrTkno.put(key, (StringUtils.isBlank(tkno) ? "" : tkno+ ",")+ pc.getTkno());
				}

				for (int i = 0, cjrsize = jpKhddCjrList.size(); i < cjrsize; i++) {
					JpKhddCjr jkc = jpKhddCjrList.get(i);
					String key = TpXeZw.getCjrlx(jkc.getCjr(), jkc.getCjrlx());
					jkc.setTkno(VeStr.getValue(cjrTkno, key));
				}

				// 订单节点
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Map<String, Object> publicMap = new HashMap<String, Object>();
				publicMap.put("DATAFROM", "自动转机票");
				publicMap.put("DDBH", ddbh);
				publicMap.put("SHBH", shbh);
				publicMap.put("CP_YHBH", etdzParam.getYhbh());
				publicMap.put("CP_BMBH", etdzParam.getBmbh());
				publicMap.put("GNGJ", "1");
				publicMap.put("CP_OFFICEID", jpoffice.getOfficeid());
				publicMap.put("CGLY", "BOP");
				publicMap.put("CG_PNR_NO", jpKhdd.getCgPnrNo());
				publicMap.put("CG_HKGS_PNR", jpKhdd.getCgHkgsPnr());
				publicMap.put("CGKM", jpoffice.getBopcgkm());
				paramMap.put("PUBLIC", publicMap);
				String hcids = "";
				for (JpKhddHd hd : khddHdList) {
					hcids += (StringUtils.isNotBlank(hcids) ? "," : "") + hd.getId();
				}
				// 乘机人节点
				List<Map<String, Object>> cjrList = new ArrayList<Map<String, Object>>();
				for (int i = 0, cjrsize = jpKhddCjrList.size(); i < cjrsize; i++) {
					Map<String, Object> cjrMap = new HashMap<String, Object>();
					JpKhddCjr cjr = jpKhddCjrList.get(i);
					cjrMap.put("CJRID", cjr.getId());
					List<Map<String, Object>> hcList = new ArrayList<Map<String, Object>>();
					Map<String, Object> hcMap = new HashMap<String, Object>();
					hcMap.put("HC", hcids);
					hcMap.put("TKNO", cjr.getTkno());
					hcMap.put("CG_ZDJ", cjr.getCgZdj());
					hcMap.put("CG_PJ", cjr.getCgPj());
					hcMap.put("CG_JSF", cjr.getCgJsf());
					hcMap.put("CG_TAX", cjr.getCgTax());
					hcMap.put("XS_PJ", cjr.getXsPj());
					hcMap.put("XS_JSF", cjr.getXsJsf());
					hcMap.put("XS_TAX", cjr.getXsTax());
					hcMap.put("XS_ZDJ", cjr.getXsZdj());
					hcMap.put("XS_JE", cjr.getXsJe());
					hcMap.put("LXKP", "0");
					hcList.add(hcMap);
					cjrMap.put("TK", hcList);
					cjrList.add(cjrMap);
				}
				paramMap.put("CJR", cjrList);
				pkgZjpSgServiceImpl.sgZjp(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			m.put("zt", TZCP);
			m.put("error", "订单编号【"+ddbh+"】解析的乘机人为空!");
		}
		return m;
	}
}
