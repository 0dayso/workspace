package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.vetech.core.modules.excel.RowReader;
import org.vetech.core.modules.service.ServiceException;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqDrServiceImpl;

public class JpTjsqDrImport extends RowReader{
	private String shbh;
	private String cjyh;
	private String info;
	private String userid;
	private String deptid;
	private Integer succCount=0;//成功条数
	private Integer failCount=0;//失败条数
	private JpTjsqDrServiceImpl jpTjsqDrServiceImpl;
	private List<Map> drsbList = new ArrayList<Map>();
	private SqlSessionTemplate sqlSession;
	public JpTjsqDrImport(JpTjsqDrServiceImpl jpTjsqDrServiceImpl,SqlSessionTemplate sqlSession){
		super();
		this.jpTjsqDrServiceImpl = jpTjsqDrServiceImpl;
		this.sqlSession=sqlSession;
	}
	@Override
	public void getRows(int sheetIndex, int curRow, List<Object> rowlist) {
		Map map = null;
		List<JpTjsqDr> newRowList = new ArrayList<JpTjsqDr>();
		for(int i=0;i<rowlist.size();i++){//循环excel行
			 map = (Map)rowlist.get(i);//得到excel当前行
			if (map !=null && !map.isEmpty()){
				JpTjsqDr jptjsqdr = new JpTjsqDr();
				try {
					String errorMessage = this.verification(map, jptjsqdr);
					if(StringUtils.isBlank(errorMessage)){
						jptjsqdr.setId(VeDate.getNo(8));
						//jptjsqdr.setSqdh(VeDate.getNo(6));//申请单号
						jptjsqdr.setShbh(shbh);
						jptjsqdr.setDrsj(new Date());
						jptjsqdr.setSftjzw("0");//是否提交追位
						jptjsqdr.setZkfw(0.00f);
						jptjsqdr.setSqUserid(userid);
						jptjsqdr.setSqDeptid(deptid);
						jptjsqdr.setSqDatetime(new Date());
						this.jpTjsqDrServiceImpl.insert(jptjsqdr);
						//newRowList.add(jptjsqdr);
						succCount++;
					}else{
						failCount++;
						map.put("errMsg", errorMessage);
						drsbList.add(map);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException("导入追位订单失败!");
				}
			}
		}
//		if(CollectionUtils.isNotEmpty(newRowList)){
//			JpTjsqBatchInsert batch = new JpTjsqBatchInsert(sqlSession,JpTjsqDrDao.class);
//			batch.batchInsert(newRowList, 1000);//批量插入,每次插入1000条数据
//		}
	}
	
	/**
	 * excel导入数据格式验证
	 * @param map
	 * @param jptjsqdr
	 * @return
	 * @throws Exception
	 */
	public String verification(Map map,JpTjsqDr jptjsqdr) throws Exception{
		String pnrcw="";//舱位
		String cfcity="";//出发城市
		String ddcity="";//到达城市
		String pnrHkgs="";//航空公司
		StringBuffer tempErrorMessage = new StringBuffer();
		String pnr = objToString(map.get("PNR"));
		String hbh = objToString(map.get("航班号"));
		String cw = objToString(map.get("舱位"));
		String xc = objToString(map.get("行程"));
		String cpsj = objToString(map.get("出票时间"));
		String cjsj = objToString(map.get("乘机时间"));
		String ck = objToString(map.get("乘机人"));
		String zjhm = objToString(map.get("证件号码"));
		String lxr = objToString(map.get("联系人"));
		String lxdh = objToString(map.get("联系电话"));
		String ph = objToString(map.get("票号"));
		String pj = objToString(map.get("票价"));
		String mbcw = objToString(map.get("目标舱位"));
		String zjlx = objToString(map.get("证件类型"));
		
		if(StringUtils.isBlank(pnr)){
			tempErrorMessage.append("pnr不能为空");
		}else if(pnr.getBytes("GBK").length>10){
			tempErrorMessage.append("pnr长度超出范围");
		}
		jptjsqdr.setPnrNo(pnr);
		
		if(StringUtils.isBlank(hbh)){
			tempErrorMessage.append("航班号不能为空");
		}else{
			pnrHkgs = hbh.replaceAll("\\*", "").substring(0, 2);//拿到航空公司
		}
		jptjsqdr.setPnrHkgs(pnrHkgs);
		jptjsqdr.setPnrHb(hbh);
		
		if(StringUtils.isBlank(cw)){
			tempErrorMessage.append("舱位不能为空");
		}else{
			pnrcw = cw.replaceAll("\\d+","");
		}
		jptjsqdr.setPnrCw(pnrcw);
		
		if(StringUtils.isBlank(xc)){
			tempErrorMessage.append("行程不能为空");
		}else if(xc.length() == 7){
			cfcity = xc.substring(0, 3);
			ddcity = xc.substring(4, 7);
		}else if (xc.length()==6) {
			cfcity = xc.substring(0, 3);
			ddcity = xc.substring(3, 6);
		}else{
			tempErrorMessage.append("行程格式不正确");
		}
		jptjsqdr.setPnrHc(xc);
		jptjsqdr.setCfcity(cfcity);
		jptjsqdr.setDdcity(ddcity);
		
		if(StringUtils.isBlank(cpsj)){
			tempErrorMessage.append("出票时间不能为空");
		}else if(!VeDate.isDatetime(cpsj)){
			tempErrorMessage.append("出票时间格式不正确;");
		}else{
			jptjsqdr.setCpDatetime(VeDate.strToDateLong(cpsj));
		}
		
		if(StringUtils.isBlank(cjsj)){
			tempErrorMessage.append("乘机时间不能为空");
		}else if(!VeDate.isDatetime(cjsj)){
			tempErrorMessage.append("乘机时间格式不正确");
		}else{
			String today = VeDate.getStringDate();
			if(StringUtils.isNotBlank(cjsj)&&Long.valueOf(cjsj.replaceAll("[-\\s:]","")) <= Long.valueOf(today.replaceAll("[-\\s:]",""))){
				tempErrorMessage.append("航班已起飞,不能追位。");
			}
			jptjsqdr.setPnrCfrqsj(cjsj);
			jptjsqdr.setCjrqs(VeDate.strToDateLong(cjsj));
			jptjsqdr.setCjrqz(VeDate.strToDateLong(cjsj));
		}
		
		if(StringUtils.isBlank(ck)){
			tempErrorMessage.append("乘客不能为空");
		}else if(ck.getBytes("GBK").length>40){
			tempErrorMessage.append("乘客长度超出范围");
		}
		jptjsqdr.setPnrCjr(ck);
		
		if(StringUtils.isBlank(zjlx)){
			tempErrorMessage.append("证件类型不能为空");
		}
		jptjsqdr.setPnrCjrzjlx(zjlx);
		
		if(StringUtils.isBlank(zjhm)){
			tempErrorMessage.append("证件号码不能为空");
		}else if(zjhm.getBytes("GBK").length > 40){
			tempErrorMessage.append("证件号码超过长度");
		}
		jptjsqdr.setPnrCjrzjh(zjhm);
		
		if(StringUtils.isNotBlank(lxr)){
			if(lxr.getBytes("GBK").length>40){
				tempErrorMessage.append("联系人长度超出范围");
			}
		}
		jptjsqdr.setLxr(lxr);
		
		if(StringUtils.isNotBlank(lxdh)){
			if(lxdh.getBytes().length>30){
				tempErrorMessage.append("联系电话长度超出范围");
			}
		}
		jptjsqdr.setLxdh(lxdh);
		
		if(StringUtils.isBlank(ph)){
			tempErrorMessage.append("票号不能为空");
		}else if(StringUtils.isNotBlank(ph)){
			String phs = ph.replaceAll("-", "");
			if(!ValidationUtil.isNumericLoose(phs) || phs.length()!=13){
			tempErrorMessage.append("票号格式不正确");
			}
		}
		jptjsqdr.setDdbh(ph);
		
		if(StringUtils.isBlank(pj)){
			tempErrorMessage.append("票价不能为空");
		}
		jptjsqdr.setPjfw(NumberUtils.toDouble(pj));
		
		if(StringUtils.isNotBlank(mbcw)){
			if(!ValidationUtil.isCws(mbcw)){
				tempErrorMessage.append("目标舱位格式不正确");
			}
		}
		jptjsqdr.setMbcw(mbcw);
		
		return tempErrorMessage.toString();
	}
	
	/**
	 * 从EXCIL中导入的不符合规格的日志格式转换
	 * EXCIL中的日期始从 1899-12-30开始的
	 * @param d
	 * @return
	 */
	private String doubleToStrDate(Double d){
		String itemDateStr="";
		try{   
	           Calendar base = Calendar.getInstance();   
	           //Delphi的日期类型从1899-12-30开始
	           base.set(1899, 11, 30, 0, 0, 0);
	           base.add(Calendar.DATE, d.intValue());   
	           base.add(Calendar.MILLISECOND,(int)((d % 1) * 24 * 60 * 60 * 1000));   
	          // t=outFormat.format(base.getTime()); 
	           itemDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(base.getTime());
	    }catch(Exception e) {   
	         e.printStackTrace();      
	    } 
	    return itemDateStr;
	}
	
	private String objToString(Object obj){
		String str= obj==null ? "" : String.valueOf(obj);
		return StringUtils.trimToEmpty(str);
	}
	
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getCjyh() {
		return cjyh;
	}
	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getSuccCount() {
		return succCount;
	}
	public void setSuccCount(Integer succCount) {
		this.succCount = succCount;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<Map> getDrsbList() {
		return drsbList;
	}
	public void setDrsbList(List<Map> drsbList) {
		this.drsbList = drsbList;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
}
