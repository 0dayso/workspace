package cn.vetech.vedsb.utils.bankdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import cn.vetech.vedsb.jp.entity.jpcwgl.JpDzsz;

public class BankDataUtil {
	private List<Map<String,Object>> errorList=new ArrayList<Map<String,Object>>();//存放不能识别的数据
	/**
	 * 携程账单数据处理
	 * 第一步：区分正常单、改签单、退单、补差单,过滤无用的数据
	 * 第二步：处理收入支出列
	 * @param bankList
	 * @param headList
	 * @param jpDzsz
	 * @throws Exception 
	 */
	public void xcZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String[] srls={};
		String[] zcls={};
		if(StringUtils.isNotBlank(jpDzsz.getSr1())){
			String s=jpDzsz.getSr1().replace("||","|");
			srls=s.split("\\|");
		}
		if(StringUtils.isNotBlank(jpDzsz.getZc1())){
			String s=jpDzsz.getZc1().replace("||","|");
			zcls=s.split("\\|");
		}
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tpBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> gqBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> bcBankList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : bankList){
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			//区分正常、改签等，并过滤(业务类型不是以下的则过滤掉)
			if("出票".equals(map.get(jpDzsz.getBz()))){
				map.put("DDLX", "1");
				srzclCl(jpDzsz, map, srls, zcls);
				zcBankList.add(map);
			}else if ("退票".equals(map.get(jpDzsz.getBz()))) {
				map.put("DDLX", "2");
				srzclCl(jpDzsz, map, srls, zcls);
				tpBankList.add(map);
			}else if ("改签".equals(map.get(jpDzsz.getBz()))) {
				map.put("DDLX", "3");
				srzclCl(jpDzsz, map, srls, zcls);
				gqBankList.add(map);
			}else if ("付款调整".equals(map.get(jpDzsz.getBz())) || "申诉".equals(map.get(jpDzsz.getBz()))) {
				map.put("DDLX", "4");
				srzclCl(jpDzsz, map, srls, zcls);
				bcBankList.add(map);
			}else if("特殊款项".equals(map.get(jpDzsz.getBz()))){
				map.put("DDLX", "4");
				String jebz=BankDbUtil.objToString(map.get("金额备注"));
				if(StringUtils.isBlank(jebz)){
					continue;
				}
				jebz=BankDbUtil.getStrByReg(jebz, "[^0-9]+([0-9]+).*");
				map.put(jpDzsz.getDdhl(), jebz);
				srzclCl(jpDzsz, map, srls, zcls);
				bcBankList.add(map);
			}else {
				errorList.add(map);
			}
		}
		if(StringUtils.isNotBlank(jpDzsz.getHbl()) && StringUtils.isNotBlank(jpDzsz.getHbtj())){
			hbZd(zcBankList,jpDzsz);
			hbZd(tpBankList,jpDzsz);
			hbZd(gqBankList,jpDzsz);
			hbZd(bcBankList,jpDzsz);
		}
		bankList.clear();
		bankList.addAll(zcBankList);
		bankList.addAll(tpBankList);
		bankList.addAll(gqBankList);
		bankList.addAll(bcBankList);
		headList.remove("");
		headList.remove(jpDzsz.getDdhl());
		headList.add(0,jpDzsz.getDdhl());
		headList.add(1, "发生金额");
	}
	/**
	 * 同程账单数据处理
	 * 第一步：区分正常单、退单,过滤无用的数据
	 * 第二步：处理收入支出列
	 * @param bankList
	 * @param headList
	 * @param jpDzsz
	 * @throws Exception 
	 */
	public void tcZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String[] srls={};
		String[] zcls={};
		if(StringUtils.isNotBlank(jpDzsz.getSr1())){
			String s=jpDzsz.getSr1().replace("||","|");
			srls=s.split("\\|");
		}
		if(StringUtils.isNotBlank(jpDzsz.getZc1())){
			String s=jpDzsz.getZc1().replace("||","|");
			zcls=s.split("\\|");
		}
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tpBankList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : bankList){
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			//区分正常、改签等，并过滤(业务类型不是以下的则过滤掉)--同程只有正常单和退单,并获取订单号
			if("在线支付".equals(map.get(jpDzsz.getBz()))){
				map.put("DDLX", "1");
				srzclCl(jpDzsz, map, srls, zcls);//收入列处理
				zcBankList.add(map);
			}else if ("交易退款".equals(map.get(jpDzsz.getBz()))) {
				map.put("DDLX", "2");
				srzclCl(jpDzsz, map, srls, zcls);
				tpBankList.add(map);
			}else {
				errorList.add(map);
				continue;
			}
			String wbdh=BankDbUtil.objToString(map.get(jpDzsz.getDdhl()));
			if(StringUtils.isNotBlank(wbdh)){
				/**
				 * 同程支付供应商机票款[FA55CCBA642100353950],记录编号PNR:HPJ8M2
				 * FA55CCBA642100353950为订单号
				 */
				String reg=".*\\[(.+)\\].*";
				wbdh=BankDbUtil.getStrByReg(wbdh, reg);
				map.put("订单号", wbdh);
			}else {
				errorList.add(map);//不能识别的
			}
		}
		if(StringUtils.isNotBlank(jpDzsz.getHbl()) && StringUtils.isNotBlank(jpDzsz.getHbtj())){
			hbZd(zcBankList,jpDzsz);
			hbZd(tpBankList,jpDzsz);
		}
		bankList.clear();
		bankList.addAll(zcBankList);
		bankList.addAll(tpBankList);
		headList.remove("");
		headList.add(0,"订单号");
		jpDzsz.setDdhl("订单号");
		headList.add(1, "发生金额");
	}
	/**
	 * 去哪儿银行账单数据处理
	 * 第一步：区分正常单、改签单、退单、补差单,过滤无用的数据
	 * 第二步：处理收入支出列
	 * @param bankList
	 * @param headList
	 * @param jpDzsz
	 * @throws Exception 
	 */
	public void qnrYhZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String[] srls={};
		String[] zcls={};
		if(StringUtils.isNotBlank(jpDzsz.getSr1())){
			String s=jpDzsz.getSr1().replace("||","|");
			srls=s.split("\\|");
		}
		if(StringUtils.isNotBlank(jpDzsz.getZc1())){
			String s=jpDzsz.getZc1().replace("||","|");
			zcls=s.split("\\|");
		}
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tpBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> gqBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> bcBankList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : bankList){
			//区分正常、改签等，并过滤(业务类型不是以下的则过滤掉)
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			String ywmc=BankDbUtil.objToString(map.get(jpDzsz.getTjz1()));
			String zwlx=BankDbUtil.objToString(map.get(jpDzsz.getBz()));
			String ddh=BankDbUtil.objToString(map.get("业务线订单号"));//新版取此列
			String bz=BankDbUtil.objToString(map.get("备注"));
			if(StringUtils.isBlank(ddh)){
				errorList.add(map);
				continue;
			}
			if(StringUtils.isNotBlank(bz)){
				String reg="^\\d+.*$";
				if(ddh.matches(reg)){
					ddh=bz;
				}
			}
			if(ddh.contains("gqtransfer")){
				map.put("DDLX", "3");
				//xgx151030211533330gqtransfer2238698
				ddh=ddh.substring(0,18)+"001";//前18位加上001就是供应订单号
				map.put(jpDzsz.getDdhl(), ddh);
				srzclCl(jpDzsz, map, srls, zcls);
				gqBankList.add(map);
			}else if(("一次解冻入账".equals(zwlx) || "交易入账".equals(zwlx) || "担保确认入账".equals(zwlx))
					&& (ywmc.contains("机票TTS") || ywmc.contains("国际机票"))){
				map.put("DDLX", "1");
				//efu150515235938897或efu150515234159367001ota0000
				if(StringUtils.isBlank(ddh) || ddh.length()<18){
					errorList.add(map);
					continue;
				}
				if (ddh.contains("ota")) {//截取21位为订单号
					ddh=ddh.substring(0,21);
				}
				map.put(jpDzsz.getDdhl(), ddh);
				srzclCl(jpDzsz, map, srls, zcls);
				zcBankList.add(map);
			}else if ("现金到退款出账".equals(zwlx) && (ywmc.contains("机票TTS") || ywmc.contains("国际机票"))) {
				map.put("DDLX", "2");
				//efu150515235938897a00000000或efu150515234159367001ota0000
				if(StringUtils.isBlank(ddh) || ddh.length()<18){
					errorList.add(map);
					continue;
				}
				if (ddh.contains("ota")) {//截取21位为订单号
					ddh=ddh.substring(0,21);
				}
				map.put(jpDzsz.getDdhl(), ddh);
				srzclCl(jpDzsz, map, srls, zcls);
				tpBankList.add(map);
			}else if (StringUtils.isBlank(zwlx) && ywmc.contains("机票退改签")) {
				map.put("DDLX", "3");
				//efu150515235938897a00000000或efu150515234159367001ota0000
				if(StringUtils.isBlank(ddh) || ddh.length()<18){
					errorList.add(map);
					continue;
				}
				if (ddh.contains("ota")) {//截取21位为订单号
					ddh=ddh.substring(0,21);
				}
				map.put(jpDzsz.getDdhl(), ddh);
				srzclCl(jpDzsz, map, srls, zcls);
				gqBankList.add(map);
			}else if ("提现出账".equals(zwlx) && (ywmc.contains("机票TTS") || ywmc.contains("国际机票")) 
					&& StringUtils.isNotBlank(ddh) && ddh.contains("remit")) {
				map.put("DDLX", "4");
				if (ddh.contains("ota")) {//截取21位为订单号
					ddh=ddh.substring(0,21);
				}
				map.put(jpDzsz.getDdhl(), ddh);
				srzclCl(jpDzsz, map, srls, zcls);
				bcBankList.add(map);
			}else {
				errorList.add(map);
			}
		}
		if(StringUtils.isNotBlank(jpDzsz.getHbl()) && StringUtils.isNotBlank(jpDzsz.getHbtj())){
			hbZd(zcBankList,jpDzsz);
			hbZd(tpBankList,jpDzsz);
			hbZd(gqBankList,jpDzsz);
			hbZd(bcBankList,jpDzsz);
		}
		bankList.clear();
		bankList.addAll(zcBankList);
		bankList.addAll(tpBankList);
		bankList.addAll(gqBankList);
		bankList.addAll(bcBankList);
		headList.remove("");
		headList.remove(jpDzsz.getDdhl());
		headList.add(0,jpDzsz.getDdhl());
		headList.add(1, "发生金额");
	}
	/**
	 * 淘宝银行账单数据处理
	 * 第一步：处理收入支出列,过滤无用的数据,将数据分类为正常票、退票、改签、代扣佣金、保险分润
	 * 第二步：处理代扣
	 * @param bankList
	 * @param headList
	 * @param jpDzsz
	 * @throws Exception 
	 */
	public void tbZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String ddhlm="订单号";
		jpDzsz.setDdhl(ddhlm);
		String dkyjlm="代扣佣金";
		String qtdklm="其他代扣";
		String bxfrlm="保险分润";
		String jppklm="机票票款";
		String zcdh="正常单单号";//用于合并退改的其他费用
		String[] srls={};
		String[] zcls={};
		if(StringUtils.isNotBlank(jpDzsz.getSr1())){
			String s=jpDzsz.getSr1().replace("||","|");
			srls=s.split("\\|");
		}
		if(StringUtils.isNotBlank(jpDzsz.getZc1())){
			String s=jpDzsz.getZc1().replace("||","|");
			zcls=s.split("\\|");
		}
		String bz=jpDzsz.getBz();
		String cglsh=jpDzsz.getCglsh();
		String shddh=jpDzsz.getShddh();
		String startChar=jpDzsz.getStartchar();
		String splitChar=jpDzsz.getSplitchar();
		int splitNo=jpDzsz.getSplitno() == null ? 0 : jpDzsz.getSplitno();
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();//存放机票订单
		List<Map<String,Object>> tpBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> gqBankList=new ArrayList<Map<String,Object>>();
		//List<Map<String,Object>> bcBankList=new ArrayList<Map<String,Object>>();--支付宝分不出补差
		List<Map<String,Object>> dkzcList=new ArrayList<Map<String,Object>>();//存放正常单代扣
		List<Map<String,Object>> dktpList=new ArrayList<Map<String,Object>>();//存放退单代扣
		for(Map<String,Object> map : bankList){//理出机票订单与代扣订单
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			srzclCl(jpDzsz, map, srls, zcls);//处理收入支出
			String lsh=(String)map.get(cglsh);
			lsh=StringUtils.isNotBlank(lsh) ? lsh : "";
			String ddh=BankDbUtil.objToString(map.get(shddh));
			ddh=StringUtils.trimToEmpty(ddh);
			double fsje=BankDbUtil.obj2Double(map.get("发生金额"));
			if(StringUtils.isBlank(ddh)){
				errorList.add(map);
				continue;
			}
			String bznr=StringUtils.trimToEmpty(ObjectUtils.toString(map.get(bz)));//备注内容
			if(StringUtils.isBlank(bznr) && !"备注".equals(bz)){
				bznr=StringUtils.trimToEmpty(ObjectUtils.toString(map.get("备注")));//备注内容
			}
			if(bznr.contains("公益金") || bznr.contains("基金")){//基金不算入收入金额
				errorList.add(map);
				continue;
			}
			if(lsh.startsWith(startChar)){//代扣
				if(ddh.startsWith("et-insp-r")){//保险分润退款（退单）
					dktpList.add(map);
				}else if (ddh.startsWith("et-insp")) {//保险分润（正常单）
					dkzcList.add(map);
				}else if(ddh.startsWith("et-") && fsje>0){//其他正常单代扣（正常单）
					dkzcList.add(map);
				}else if(ddh.startsWith("et-") && fsje<0){//其他退单代扣（退单）
					dktpList.add(map);
				}else if (ddh.startsWith("HJCOM") && fsje<0) {//正常单佣金（正常单）
					dkzcList.add(map);
				}else if (ddh.startsWith("HJCOM") && fsje>0) {//退单佣金（退单）
					dktpList.add(map);
				}else if(ddh.startsWith("TRIPPE") || bznr.contains("代扣款")){
					map.put(shddh, bznr);
					if(bznr.contains("退款")){
						dktpList.add(map);
					}else{
						dkzcList.add(map);
					}
				}else {
					errorList.add(map);
				}
			}else {
				String[] ddhsplit=ddh.split(splitChar);
				map.put(jppklm, fsje);
				if (ddh.startsWith("et-p-m-") || ddh.startsWith("ie-p-m-")) {//改签单
					map.put(ddhlm,ddhsplit[4]);
					map.put(zcdh, ddhsplit[3]);
					map.put("DDLX", "3");
					gqBankList.add(map);
				}else if ((ddh.startsWith("et-p-") || ddh.startsWith("ie-p-")) && fsje>0) {//正常单
					map.put(ddhlm,ddhsplit[2]);
					map.put("DDLX", "1");
					zcBankList.add(map);
				}else if (ddh.startsWith("et-r-") || ddh.startsWith("ie-r-") || ((ddh.startsWith("et-p-") || ddh.startsWith("ie-p-")) && fsje<0)) {//退单
					map.put(ddhlm,ddhsplit[3]);
					map.put(zcdh, ddhsplit[2]);
					map.put("DDLX", "2");
					tpBankList.add(map);
				}else if(ddh.startsWith("et-m-r-")){//升舱退票后退款;按退票算，和退票合并(在系统中都是在退票单上面，网店分了两笔记录)
					map.put(ddhlm,ddhsplit[4]);
					map.put("DDLX", "2");
					map.put(zcdh, ddhsplit[3]);
					tpBankList.add(map);
				}else {
					errorList.add(map);
				}
			}
		}
		//合并正常单代扣款
		for(Map<String,Object> dkMap : dkzcList){
			String dkDh=(String)dkMap.get(shddh);//代扣款商户单号
			String bznr=(String)dkMap.get(bz);//备注内容
			bznr=StringUtils.isNotBlank(bznr) ? bznr : "";
			int no=splitNo-1;
			double dkk=BankDbUtil.obj2Double(dkMap.get("发生金额"));//代扣款
			boolean findFlag=false;
			for(Map<String,Object> jpMap : zcBankList){
				String shdh=BankDbUtil.objToString(jpMap.get(ddhlm));
				if(dkDh.contains(shdh)){//合并代扣款
					findFlag=true;
					if (dkDh.startsWith("et-insp")) {//保险分润
						double bxfr=BankDbUtil.addDouble(jpMap.get(bxfrlm), dkk);
						jpMap.put(bxfrlm,bxfr);
					}else if (dkDh.startsWith("HJCOM") && dkk<0) {//正常单佣金
						double yj=BankDbUtil.addDouble(jpMap.get(dkyjlm), dkk);
						jpMap.put(dkyjlm,yj);
					}else{
						double qtdkk=BankDbUtil.addDouble(jpMap.get(qtdklm), dkk);
						jpMap.put(qtdklm,qtdkk);
					}
					double jpje=BankDbUtil.obj2Double(jpMap.get("发生金额"));
					jpMap.put("发生金额", BankDbUtil.addDouble(jpje,dkk));
					break;
				}
			}
			if(!findFlag){
				errorList.add(dkMap);
			}
		}
		//合并退单代扣款
		for(Map<String,Object> dkMap : dktpList){
			String dkDh=(String)dkMap.get(shddh);//代扣款商户单号
			String bznr=(String)dkMap.get(bz);//备注内容
			bznr=StringUtils.isNotBlank(bznr) ? bznr : "";
			int no=splitNo-1;
			double dkk=BankDbUtil.obj2Double(dkMap.get("发生金额"));//代扣款
			boolean findFlag=false;
			for(Map<String,Object> jpMap : tpBankList){
				String shdh=BankDbUtil.objToString(jpMap.get(zcdh));//退票匹配代扣款必须用正常单单号匹配
				if(dkDh.contains(shdh)){//合并代扣款
					findFlag=true;
					if (dkDh.startsWith("et-insp-r")) {//保险分润
						double bxfr=BankDbUtil.addDouble(jpMap.get(bxfrlm), dkk);
						jpMap.put(bxfrlm,bxfr);
					}else if(dkDh.startsWith("et-") && dkk<0){//其他代扣
						double qtdkk=BankDbUtil.addDouble(jpMap.get(qtdklm), dkk);
						jpMap.put(qtdklm,qtdkk);
					}else if (dkDh.startsWith("HJCOM") && dkk>0) {//佣金
						double yj=BankDbUtil.addDouble(jpMap.get(dkyjlm), dkk);
						jpMap.put(dkyjlm,yj);
					}else{//其他代扣
						double qtdkk=BankDbUtil.addDouble(jpMap.get(qtdklm), dkk);
						jpMap.put(qtdklm,qtdkk);
					}
					double jpje=BankDbUtil.obj2Double(jpMap.get("发生金额"));
					jpMap.put("发生金额", BankDbUtil.addDouble(jpje,dkk));
					break;
				}
			}
			if(!findFlag){
				errorList.add(dkMap);
			}
		}
		bankList.clear();
		hbZd(zcBankList, jpDzsz);
		bankList.addAll(zcBankList);
		hbZd(tpBankList, jpDzsz);
		bankList.addAll(tpBankList);
		bankList.addAll(gqBankList);
		headList.remove("");
		headList.remove(jpDzsz.getDdhl());
		headList.add(0,jpDzsz.getDdhl());
		headList.add(1, "发生金额");
		headList.add(2,jppklm);
		headList.add(3,bxfrlm);
		headList.add(4,dkyjlm);
		headList.add(5,qtdklm);
	}
	/**
	 * 酷讯支付宝账单数据处理
	 * 第一步：过滤无用的数据，处理收入与支出
	 * 第二步：合并记录
	 * @param bankList
	 * @param headList
	 * @param jpDzsz
	 * @throws Exception 
	 */
	public void kxZfbZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String[] srls={};
		String[] zcls={};
		if(StringUtils.isNotBlank(jpDzsz.getSr1())){
			String s=jpDzsz.getSr1().replace("||","|");
			srls=s.split("\\|");
		}
		if(StringUtils.isNotBlank(jpDzsz.getZc1())){
			String s=jpDzsz.getZc1().replace("||","|");
			zcls=s.split("\\|");
		}
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : bankList){
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			String zflsh=BankDbUtil.objToString(map.get(jpDzsz.getCglsh()));
			if(StringUtils.isNotBlank(zflsh)){
				srzclCl(jpDzsz, map, srls, zcls);//收入列处理
				zflsh=zflsh.replace("`", "");
				map.put(jpDzsz.getCglsh(), zflsh);
				if(BankDbUtil.obj2Double(map.get("发生金额"))>0){
					map.put("DDLX", "1");
				}else {
					map.put("DDLX", "2");
				}
				zcBankList.add(map);
			}
		}
		//合并记录
		if(StringUtils.isNotBlank(jpDzsz.getHbl()) && StringUtils.isNotBlank(jpDzsz.getHbtj())){
			hbZd(zcBankList,jpDzsz);
		}
		bankList.clear();
		bankList.addAll(zcBankList);
		headList.remove("");
		headList.remove(jpDzsz.getCglsh());
		headList.add(0,jpDzsz.getCglsh());
		headList.add(1, "发生金额");
	}
	
	
	/**
	 * 直通车数据处理
	 * 第一步：处理收入支出列,过滤无用的数据,将数据分类为正常票、退票、改签、代扣佣金、保险分润
	 * 第二步：处理代扣
	 */
	public void ztcZdCl(List<Map<String,Object>> bankList,List<String> headList,JpDzsz jpDzsz) throws Exception {
		String shddh=jpDzsz.getShddh();
		List<Map<String,Object>> zcBankList=new ArrayList<Map<String,Object>>();//存放机票订单
		List<Map<String,Object>> tpBankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> qtpList=new ArrayList<Map<String,Object>>();//存放退单代扣
		for(Map<String,Object> map : bankList){//理出机票订单与代扣订单
			if(glZd(map, jpDzsz)){//过滤账单
				continue;
			}
			String ddh=BankDbUtil.objToString(map.get(shddh));
			if(StringUtils.isBlank(ddh)||ddh.length()<10){
				errorList.add(map);	
				continue;
			}
			String kplx=StringUtils.trimToEmpty(BankDbUtil.objToString(map.get("客票\n类型"))).toUpperCase();
			
			if(kplx.startsWith("RFND")){
				tpBankList.add(map);
			}else if(kplx.startsWith("TKTT")){
				zcBankList.add(map);
			}else{
				qtpList.add(map);
			}
		}
	 
		bankList.clear();
		bankList.addAll(zcBankList);
		bankList.addAll(tpBankList);
		bankList.addAll(qtpList);
	}
	
	/**
	 * 根据设置的合并条件合并账单
	 * @param bankList
	 * @param jpDzsz
	 */
	private void hbZd(List<Map<String,Object>> bankList,JpDzsz jpDzsz)throws Exception {
		if(bankList==null || bankList.isEmpty()){
			return;
		}
		String hbl=jpDzsz.getHbl();
		String hbtj=jpDzsz.getHbtj();
		if(StringUtils.isBlank(hbl)||StringUtils.isBlank(hbtj)){
			return;
		}
		if("1".equals(hbtj)){//值相同合并
			zxdHb(bankList, jpDzsz);
		}else if ("0".equals(hbtj)) {//值为空合并
			zwkHb(bankList, jpDzsz);
		}
	}
	/**
	 * 值相等合并
	 * @throws Exception 
	 */
	private void zxdHb(List<Map<String,Object>> bankList,JpDzsz jpDzsz)throws Exception{
		String hbl=jpDzsz.getHbl();
		if(bankList!=null&&!bankList.isEmpty()){
			BankDbUtil.toSortData(bankList, hbl);
			List<Map<String,Object>> bankListRq=new ArrayList<Map<String,Object>>();//存放处理过的数据
			Map<String, Object> hb_bank=new HashMap<String, Object>();//存放合并后的数据
			Map<String, Object> one = null;
			for (int i = 0; i < bankList.size(); i++) {
				one = (Map<String, Object>) bankList.get(i);
				String hblnr1=StringUtils.trimToEmpty(BankDbUtil.objToString(one.get(hbl)));
				String hblnr2=StringUtils.trimToEmpty(BankDbUtil.objToString(hb_bank.get(hbl)));
				if(StringUtils.isNotBlank(hblnr1) && StringUtils.isNotBlank(hblnr2) && hblnr1.equals(hblnr2)){
					BankDbUtil.hbje(hb_bank, one, "发生金额");
					String szjel=jpDzsz.getSzjel();
					if(StringUtils.isNotBlank(szjel)){//合并数字金额列
						szjel=szjel.replace("||","|");
						String[] szjels=szjel.split("\\|");
						for(String lm:szjels){
							BankDbUtil.hbje(hb_bank, one, lm);
						}
					}
					String wzl=jpDzsz.getWzl();
					if(StringUtils.isNotBlank(wzl)){//合并文字列
						wzl=wzl.replace("||","|");
						String[] wzls=wzl.split("\\|");
						for(String lm:wzls){
							BankDbUtil.hbwz(hb_bank, one, lm);
						}
					}
				}else{
					hb_bank=one;
					bankListRq.add(one);
				}
			}
			bankList.clear();
			bankList.addAll(bankListRq);
		}
	}
	/**
	 * 值为空合并
	 */
	private void zwkHb(List<Map<String,Object>> bankList,JpDzsz jpDzsz)throws Exception{
		String hbl=jpDzsz.getHbl();
		if(bankList!=null&&!bankList.isEmpty()){
			List<Map<String,Object>> bankListRq=new ArrayList<Map<String,Object>>();//存放处理过的数据
			Map<String, Object> hb_bank=new HashMap<String, Object>();//存放合并后的数据
			Map<String, Object> one = null;
			for (int i = 0; i < bankList.size(); i++) {
				one = (Map<String, Object>) bankList.get(i);
				String hblnr1=StringUtils.trimToEmpty(BankDbUtil.objToString(one.get(hbl)));
				String hblnr2=StringUtils.trimToEmpty(BankDbUtil.objToString(hb_bank.get(hbl)));
				if(StringUtils.isBlank(hblnr1) && StringUtils.isNotBlank(hblnr2)){
					BankDbUtil.hbje(hb_bank, one, "发生金额");
					String szjel=jpDzsz.getSzjel();
					if(StringUtils.isNotBlank(szjel)){//合并数字金额列
						szjel=szjel.replace("||","|");
						String[] szjels=szjel.split("\\|");
						for(String lm:szjels){
							BankDbUtil.hbje(hb_bank, one, lm);
						}
					}
					String wzl=jpDzsz.getWzl();
					if(StringUtils.isNotBlank(wzl)){//合并文字列
						wzl=wzl.replace("||","|");
						String[] wzls=wzl.split("\\|");
						for(String lm:wzls){
							BankDbUtil.hbwz(hb_bank, one, lm);
						}
					}
				}else{
					hb_bank=one;
					bankListRq.add(one);
				}
			}
			bankList.clear();
			bankList.addAll(bankListRq);
		}
	}
	/**
	 * 处理收入支出列
	 * @param jpDzsz
	 * @param map
	 * @param srls
	 * @param zcls
	 * @throws Exception
	 */
	private void srzclCl(JpDzsz jpDzsz,Map<String,Object> map,String[] srls,String[] zcls) throws Exception{
		double srje=0;
		double zcje=0;
		for(String srl:srls){//收入金额处理
			String srje1 = StringUtils.replace(BankDbUtil.objToString(map.get(srl)), "￥", "");
			if(StringUtils.isBlank(srje1)){
				System.out.println(1);
			}
			srje1=srje1.replaceAll(",", "");
			srje1=srje1.replaceAll("，", "");
			if(StringUtils.isNotBlank(srje1)&&srje1.indexOf("/")>0){//携程金额列：80/120/800
				String[] srs=srje1.split("/");
				srje1=srs[0];
				for(int f=1;f<srs.length;f++){
					srje1=BankDbUtil.addDouble(srje1, srs[f])+"";
				}
			}
			srje=BankDbUtil.addDouble(srje,srje1);
		}
		for(String zcl:zcls){//支出金额处理
			String zcje1 = StringUtils.replace(BankDbUtil.objToString(map.get(zcl)), "￥", "");
			zcje=BankDbUtil.addDouble(zcje,zcje1);
		}
		//要合并的数字金额列处理
		String szjel=jpDzsz.getSzjel();
		if(StringUtils.isNotBlank(szjel)){
			szjel=szjel.replace("||","|");
			String[] jels=szjel.split("\\|");
			for(String jel:jels){
				String je=StringUtils.replace(BankDbUtil.objToString(map.get(jel)), "￥", "");
				if(StringUtils.isNotBlank(je)&&je.indexOf("/")>0){//携程金额列：80/120/800
					String[] srs=je.split("/");
					je=srs[0];
					for(int f=1;f<srs.length;f++){
						je=BankDbUtil.addDouble(je, srs[f])+"";
					}
				}
				map.put(jel, BankDbUtil.obj2Double(je));
			}
		}
		if(StringUtils.isNotBlank(jpDzsz.getSr1()) && StringUtils.isNotBlank(jpDzsz.getZc1()) 
				&& jpDzsz.getSr1().equals(jpDzsz.getZc1())){//当收入列与支出列相同，正数收入，负数为支出
			String tjmc=BankDbUtil.objToString(map.get(jpDzsz.getTjmc()));
			String tjnr=jpDzsz.getTjnr();
			if(StringUtils.isNotBlank(tjmc)){
				if (tjmc.contains(tjnr)) {//为支出
					srje=0;
				}else{
					zcje=0;
				}
			}else{
				if(srje<0){
					srje=0;
				}else {
					zcje=0;
				}
			}
			
		}
		if(zcje>0){//支出金额按负数显示
			zcje=-1*zcje;
		}		
		map.put("发生金额",BankDbUtil.addDouble(srje, zcje));
	}
	/**
	 * 过滤账单
	 */
	private boolean glZd(Map<String,Object> map,JpDzsz jpDzsz){
		String gltj=jpDzsz.getGltj();
		String gll=jpDzsz.getGll();
		if(StringUtils.isBlank(gltj)||StringUtils.isBlank(gll)){
			return false;
		}
		gltj=gltj.replace("||","|");
		gll=gll.replace("||","|");
		String[] gltjs=gltj.split("\\|");
		String[] glls=gll.split("\\|");
		for(int i=0;i<glls.length;i++){
			String zgll=(String)map.get(glls[i]);
			if(StringUtils.isNotBlank(zgll) && zgll.contains(gltjs[i])){
				errorList.add(map);
				return true;
			}
		}
		return false;
	}
	public List<Map<String, Object>> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<Map<String, Object>> errorList) {
		this.errorList = errorList;
	}
}
