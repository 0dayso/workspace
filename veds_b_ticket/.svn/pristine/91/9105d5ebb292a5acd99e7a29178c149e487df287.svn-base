package cn.vetech.vedsb.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vetech.core.business.cache.BcityCache;
import org.vetech.core.business.cache.HkgsCache;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTicket;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTickethd;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.xcdgl.ParseXcd;

public class XcdUtils {
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	
	/**
	 * 将客户订单对象转换成T_xcd_ticket对象
	 * @param request
	 * @param jpKhdd
	 * @param cjrList
	 * @param hdList
	 * @param datafrom
	 * @return
	 */
	public static Map<String, JpXcdTicket> convertKhddToJpXcdTicket(HttpServletRequest request,JpKhdd jpKhdd,List<JpKhddCjr> cjrList,List<JpKhddHd> hdList,String datafrom){
		Map<String, JpXcdTicket> xcdMap = new HashMap<String, JpXcdTicket>();
		if(jpKhdd==null){
			jpKhdd=new JpKhdd();
		}
		if(hdList==null){
			hdList=new ArrayList<JpKhddHd>();
		}
		
		if(cjrList!=null){
			for(JpKhddCjr cjr:cjrList){
				JpXcdTicket xcdTicket=new JpXcdTicket();
				xcdTicket.setDatafrom(datafrom);
				xcdTicket.setCplx(jpKhdd.getCgly());
				xcdTicket.setPnrNo(jpKhdd.getCgPnrNo());
				xcdTicket.setLkxm(cjr.getCjr());
				xcdTicket.setZjhm(cjr.getZjhm());
				xcdTicket.setQz("");
				xcdTicket.setPjZdj(cjr.getCgZdj()+"");
				xcdTicket.setPjJsf(cjr.getCgJsf()+"");
				xcdTicket.setPjTax(cjr.getCgTax()+"");
				xcdTicket.setPjBx("0");
				xcdTicket.setPjQt("0");
				xcdTicket.setIfgq(cjr.getNfgq());//标记是否改签
				Double gqfy = (Double)request.getAttribute("gqfy");//改签费用在外层java中获取，set到request中。
//				ticket.setGqfy(String.valueOf(gqfy));
				if("3".equals(datafrom) || "4".equals(datafrom)){//如果是detr指令、中航信返回原始数据，合计项 直接取 pjTotal
					xcdTicket.setPjHj(cjr.getCgPj()+"");
				}else{
					double hj= cjr.getCgZdj()+cjr.getCgJsf()+cjr.getCgTax();
					xcdTicket.setPjHj(hj+"");
				}
				xcdTicket.setTkrq(VeDate.getStringDateShort());
				String tkno=cjr.getTkno();
				xcdTicket.setTkno(tkno);
//				Map<String,String> offmap=getOfficeInfo(jpjp, tkno,request);
//				if(offmap!=null){
//					xcdTicket.setOffice(offmap.get("SVALUE"));
//					xcdTicket.setDwdh(offmap.get("BH"));
//				}
				
				String[] cjrArray = cjr.getTkno().split(",");
				List<String> tknoList = Arrays.asList(cjrArray);
				String secondTkno="";
				int tknocnt=0;
				if(tknoList!=null&&tknoList.size()>0){
					Iterator<String> iter=tknoList.iterator();	
					while(iter.hasNext()){
						String tknoTemp=iter.next();
						if(StringUtils.isNotBlank(tknoTemp)&&tknoTemp.length()==13){
							tknocnt++;
							if(tknoTemp.equals(tkno)){
								continue;
							}
							secondTkno=tknoTemp;
						}
					}
				}
				String splitStr=tknocnt>2 ? "-" : "/";//连续客票分隔符
				//航段处理
				boolean sfqkcbool=false;//是否缺口程
				List<JpXcdTickethd> xcdhdList=new ArrayList<JpXcdTickethd>();
				if(hdList.size()>=1){
					String ddcityTmp="";//当下一段的出发不是上一段的到达时，虽然没有ARNK，但依然视为缺口程
					for(int i=0;i<hdList.size();i++){
						if(i==5){
							break;
						}
						JpKhddHd hd=hdList.get(i);
						JpXcdTickethd tikethd=new JpXcdTickethd();
						setHzl(tikethd, hd);
						tikethd.setJcmc(XcdUtils.getJcmcByJcszm(hd.getCfcity()));
						tikethd.setHbh(hd.getCgHbh());
						tikethd.setHkgsjc(getHkgsJcByHkgsEzm(jpKhdd.getHkgs()));
						tikethd.setZwdj(hd.getCgCw());
						String cfsj=hd.getCfsj();
						if(StringUtils.isNotBlank(cfsj)&&cfsj.length()>=16){//2011-01-01 12:00:00 or 2011-01-01 12:00 
							tikethd.setCfrq(cfsj.substring(0,10));
							tikethd.setCfsj(cfsj.substring(11,16));
						}else if(StringUtils.isNotBlank(cfsj)&&cfsj.length()>=10){
							tikethd.setCfrq(cfsj.substring(0,10));
						}
//						tikethd.setKpjb(hd.getCwjb());
//						tikethd.setMfxl(hd.getXl());
//						tikethd.setYxrq(hd.getYxrq());
//						tikethd.setJzrq(hd.getJzrq());
						xcdhdList.add(tikethd);
						if(StringUtils.isNotBlank(ddcityTmp)){
							if(!ddcityTmp.equals(hd.getCfcity())){
								JpKhddHd kh_khddhdb=hdList.get(i-1);
								tikethd=new JpXcdTickethd();
								setHzl(tikethd, kh_khddhdb);
								tikethd.setJcmc(XcdUtils.getJcmcByJcszm(kh_khddhdb.getDdcity()));
								tikethd.setHbh("VOID");
								tikethd.setHkgsjc("VOID");
								xcdhdList.add(xcdhdList.size()-1,tikethd);
							}
						}
						ddcityTmp=hd.getDdcity();
					}
					if(hdList.size()<5){
						JpKhddHd hd=hdList.get(hdList.size()-1);
						JpXcdTickethd tikethd=new JpXcdTickethd();
						tikethd.setJcmc(XcdUtils.getJcmcByJcszm(hd.getDdcity()));
						if(hdList.size()<4){
							tikethd.setHbh("VOID");
						}
						xcdhdList.add(tikethd);
						if(xcdhdList.size()<4){
							tikethd=new JpXcdTickethd();
							tikethd.setJcmc("VOID");
							xcdhdList.add(tikethd);
						}
					}
				}

				xcdTicket.setHdlist(xcdhdList);
				if(StringUtils.isNotBlank(secondTkno)){
					xcdTicket.setLxkp(tkno+splitStr+secondTkno.substring(11));
				}
				xcdTicket.setId(cjr.getId());
				xcdMap.put(xcdTicket.getTkno(), xcdTicket);
				
				
				//一个乘机人多张票
				if(hdList.size()>=5&&!sfqkcbool){//多程时乘机人添加两次
					xcdhdList=new ArrayList<JpXcdTickethd>();
					JpXcdTicket ticket2=new JpXcdTicket();
					try {
						BeanUtils.copyProperties(ticket2, xcdTicket);
					} catch (Exception e) {
						continue;
					}
					ticket2.setId(cjr.getId()+"1");
					//已出票
					if(secondTkno!=""){
						ticket2.setTkno(secondTkno);
						ticket2.setLxkp(secondTkno+splitStr+tkno.substring(11));
					}
					for(int i=4;i<hdList.size();i++){
						if(i==10){
							break;
						}
						JpKhddHd hd=hdList.get(i);
						JpXcdTickethd tikethd=new JpXcdTickethd();
						setHzl(tikethd, hd);
						tikethd.setJcmc(XcdUtils.getJcmcByJcszm(hd.getCfcity()));
						tikethd.setHbh(hd.getCgHbh());
						tikethd.setHkgsjc(getHkgsJcByHkgsEzm(hd.getCgHbh()));
						tikethd.setZwdj(hd.getCgCw());
						String cfsj=hd.getCfsj();
						if(StringUtils.isNotBlank(cfsj)&&cfsj.length()>=16){//2011-01-01 12:00:00 or 2011-01-01 12:00 
							tikethd.setCfrq(cfsj.substring(0,10));
							tikethd.setCfsj(cfsj.substring(11,16));
						}
//						tikethd.setKpjb(hd.getCwjb());
//						tikethd.setMfxl(hd.getXl());
//						tikethd.setYxrq(hd.getYxrq());
//						tikethd.setJzrq(hd.getJzrq());
						xcdhdList.add(tikethd);
					}
					if(hdList.size()<10){
						JpKhddHd hd=hdList.get(hdList.size()-1);
						JpXcdTickethd tikethd=new JpXcdTickethd();
						tikethd.setJcmc(XcdUtils.getJcmcByJcszm(hd.getDdcity()));
						if(hdList.size()<9){
							tikethd.setHbh("VOID");
						}
						xcdhdList.add(tikethd);
						if(xcdhdList.size()<9){
							tikethd=new JpXcdTickethd();
							tikethd.setJcmc("VOID");
							xcdhdList.add(tikethd);
						}
					}
					ticket2.setHdlist(xcdhdList);
					xcdMap.put(ticket2.getTkno(), ticket2);
				}
			}
		}
		return xcdMap;
	}
	
	/**
	 * 设置出发到达航站楼
	 */
	private static void setHzl(JpXcdTickethd tikethd,JpKhddHd hd){
		if(hd!=null){
			tikethd.setCfhzl(StringUtils.trimToEmpty(hd.getCfhzl()));
			tikethd.setDdhzl(StringUtils.trimToEmpty(hd.getDdhzl()));
		}
	}
	
	//根据机场三字码找机场名称
	public static String getJcmcByJcszm(String szm)  {
		if(StringUtils.isBlank(szm)){
			return "";
		}
		BcityCache city = FunctionCode.getBcity(szm);
		return city.getMc();
	}
	
	//根据根据航空公司二字码，查找航空公司名称
	public static String getHkgsJcByHkgsEzm(String ezm)  {
		if(StringUtils.isBlank(ezm)){
			return "";
		}
		HkgsCache hkgs = FunctionCode.getHkgs(ezm);
		return hkgs.getAirway();
	}
	
//	public static Map<String, String> getOfficeInfo(JpKhdd jpKhdd, String tkno)  {
//		String office = jp.getCpOfficeid();
//		String cpzd =StringUtils.trimToEmpty(jp.getCpzd());
//		String jsm = "";//航空公司结算码
//		if (StringUtils.isNotBlank(tkno) && tkno.length() > 3) {
//			String strPattern = "[^/]+/([^<]+)<([^>]+)>";
//			Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
//			Matcher matcher = pattern.matcher(cpzd);
//			String officetmp = "";
//			if (matcher.find()) {
//				officetmp = matcher.group(1);
//				jsm = matcher.group(2);
//			}
//			if ("784".equals(tkno.substring(0, 3))) {
//				office=officetmp;
//			}
//		}
//		Map<String, String> m = new HashMap<String, String>();
//		m.put("ID", "");
//		m.put("BH", jsm);
//		m.put("SVALUE", office);
//		m.put("BY1", "");
//		return m;
//	}
	
	//将接口原始返回信息保存
		public String doSaveCreateXcdInfo(String retVal,String tkno,String xcdh){
			JpXcd jpXcd = new JpXcd ();
			jpXcd.setJkfhsj(retVal);
			jpXcd.setXcdNo(xcdh);
			try {
				jpXcdServiceImpl.update(jpXcd);
			} catch (Exception e) {
				ParseXcd.logXcdException("CreateXcd2返回数据解析后保存失败："+e.getMessage(), tkno);
				e.printStackTrace();
			}
			return "";
		}
		
		/**
		 * 创建行程单后更新相关数据表  
		 * 更新机票表，乘机人表，行程单表；
		 */
		@SuppressWarnings("unchecked")
		public String createxcd_update(Shyhb user,String tkno,String xcdh){
			String sql = "";
			String returnvalue = "0";
			List paramList = new ArrayList();
			StringBuffer xml=new StringBuffer("<SQL>");
	        xml.append(XmlUtils.xmlnode("TKNO", tkno.substring(3, tkno.length())));
	        xml.append(XmlUtils.xmlnode("XCDH", xcdh));
	        xml.append(XmlUtils.xmlnode("COMPID",user.getShbh()));
	        xml.append(XmlUtils.xmlnode("DEPTID", user.getShbmid()));
	        xml.append(XmlUtils.xmlnode("USERID", user.getBh()));
	        xml.append("</SQL>");
	        System.out.println("行程单创建传参XML："+xml.toString());
	        //sql = "{call pro_createxcd_update(?,?,?,?,?,?)}";
	        paramList.add(xml.toString());
	      //  sql="{call pro_createxcd_update(?" + OracleTypes.VARCHAR + ",?o" + OracleTypes.VARCHAR + ")}";
			try {
				// 返回-1:失败，1：成功；
			  //  List list=tmp_service.executeProcedure(sql, paramList);
	          //  returnvalue=(String) list.get(0);
				if ("-1".equals(returnvalue)) {
					ParseXcd.logXcdException("创建行程单后更新相关数据表失败,入参："+xml.toString(), tkno);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ParseXcd.logXcdException("创建行程单后更新相关数据表异常"+e.getMessage(), tkno);
			}
			return "";
		}

		
		/**
		 * 保存行程单的打印信息
		 */
		public static String doSavePrintXcdInfo(JpXcdTicket xcdinfo,Shyhb user,String ip){
			String realtkno=xcdinfo.getRealtkno();
			if(StringUtils.isBlank(realtkno)){
				return "";
			}
			if(realtkno.length()!=13){
				return "";
			}
			
			String sql="insert into t_xcd_ticket(id, xcdh, pnr_no, tkno, lkxm, zjhm, qz, pj_zdj, pj_jsf, pj_tax, pj_qt, pj_hj, yzm, tsxx, pj_bx, office, dwdh, tkdw, tkrq, jkfhsj, zgs, by1, by2, by3,cj_datetime,cj_compid,cj_deptid,cj_userid,lxkp) " +
					" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			List<Object> paramList=new ArrayList<Object>();
			String xcid=VeDate.getNo(4);
			paramList.add(xcid);
			paramList.add(xcdinfo.getXcdh());
			paramList.add(StringUtils.isBlank(xcdinfo.getPnrNo()) ? "  " : xcdinfo.getPnrNo());
			paramList.add(xcdinfo.getRealtkno());
			paramList.add(xcdinfo.getLkxm());
			paramList.add(xcdinfo.getZjhm());
			paramList.add(xcdinfo.getQz());
			paramList.add(xcdinfo.getPjZdj());
			paramList.add(xcdinfo.getPjJsf());
			paramList.add(xcdinfo.getPjTax());
			paramList.add(xcdinfo.getPjQt());
			paramList.add(xcdinfo.getPjHj());
			paramList.add(xcdinfo.getYzm());
			paramList.add(xcdinfo.getTsxx());
			paramList.add(xcdinfo.getPjBx());
			paramList.add(xcdinfo.getOffice());
			paramList.add(xcdinfo.getDwdh());
			paramList.add(xcdinfo.getTkdw());
			paramList.add(xcdinfo.getTkrq());
			paramList.add(xcdinfo.getJkfhsj());
			paramList.add(user.getShbh());
			paramList.add(ip);
			paramList.add(xcdinfo.getDatafrom());
			paramList.add(xcdinfo.getCplx()+"$"+xcdinfo.getGp_no());
			paramList.add(VeDate.getStringDate());
			paramList.add(user.getShbh());
			paramList.add(user.getShbmid());
			paramList.add(user.getBh());
			
			
			paramList.add(xcdinfo.getLxkp());
			try {
				//UtilComp.execute(tmp_service.getJdbcTempSource().getDataSource(), sql, paramList);
				List<JpXcdTickethd> hdList=xcdinfo.getHdlist();
				if(hdList!=null){
					int cnt=-1;
					for(JpXcdTickethd hd:hdList){
						cnt++;
						String hdsql="insert into t_xcd_tickethd (id, xcdh, sxh, jcszm, jcmc, cfhzl, ddhzl, hkgsrzm, hkgsjc, hbh, zwdj, cfrq, cfsj, kpjb, yxrq, jzrq, mfxl, by1, by2, by3,xcid) " +
								" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						 paramList=new ArrayList<Object>();
						 paramList.add(VeDate.getNo(4));
						 paramList.add(xcdinfo.getXcdh());
						 paramList.add(cnt);
						 paramList.add(hd.getJcszm());
						 paramList.add(hd.getJcmc());
						 paramList.add(hd.getCfhzl());
						 paramList.add(hd.getDdhzl());
						 paramList.add(hd.getHkgsrzm());
						 paramList.add(hd.getHkgsjc());
						 paramList.add(hd.getHbh());
						 paramList.add(hd.getZwdj());
						 paramList.add(hd.getCfrq());
						 paramList.add(hd.getCfsj());
						 paramList.add(hd.getKpjb());
						 paramList.add(hd.getYxrq());
						 paramList.add(hd.getJzrq());
						 paramList.add(hd.getMfxl());
						 paramList.add("");
						 paramList.add("");
						 paramList.add("");
						 paramList.add(xcid);
						// UtilComp.execute(tmp_service.getJdbcTempSource().getDataSource(), hdsql, paramList);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				ParseXcd.logXcdException("CreateXcd2返回数据解析后保存失败："+e.getMessage(), xcdinfo.getTkno());
			}
			
			String sqlPrintNum="update ticket_xcd set print_num=nvl(print_num,0)+1 where xcd_no= ? ";
			paramList=new ArrayList<Object>();
			paramList.add(xcdinfo.getXcdh());
			try {
				//UtilComp.execute(tmp_service.getJdbcTempSource().getDataSource(), sqlPrintNum, paramList);
			} catch (Exception e) {
				e.printStackTrace();
				ParseXcd.logXcdException("CreateXcd2返打印次数更新失败："+e.getMessage(), xcdinfo.getTkno());
			}
			
			return "";
		}
		
		public JpXcdServiceImpl getJpXcdServiceImpl() {
			return jpXcdServiceImpl;
		}

		public void setJpXcdServiceImpl(JpXcdServiceImpl jpXcdServiceImpl) {
			this.jpXcdServiceImpl = jpXcdServiceImpl;
		}	
}
