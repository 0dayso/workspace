package cn.vetech.vedsb.utils.bankdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.vetech.core.modules.dao.BatchMapperUtils;
import org.vetech.core.modules.mybatis.mapper.Mapper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.jpbcd.JpBcdDao;
import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddDao;
import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdDao;
import cn.vetech.vedsb.jp.dao.jptpgl.JpTpdDao;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDbParam;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.jpcwgl.JpYsdzServiceImpl;

public class BankCompareUtil<T extends AbstractPageEntity, M extends Mapper<T>>{
	private String wdid;//网店对应的分销商编号
	private String zbid;//网店工作台主表id
	private String dzrq;//对账日期
	private String wdpt;//网店平台
	// 供应订单号对比正确而金额错误的数据
	private List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	// 对比正确数据
	private List<Map<String, Object>> correctList = new ArrayList<Map<String, Object>>();

	// 系统存在而银行账单不存在的数据
	private List<Map<String, Object>> sysList = new ArrayList<Map<String, Object>>();

	// 银行账单存在而系统不存在的数据
	private List<Map<String, Object>> bList = new ArrayList<Map<String, Object>>();
	// 外部订单号
	private String ddhl = "";
	private String errorZb;//更新对账结果主表状态失败
	private String shbh;
	private String user;
	private String deptid;
	private String[] gsdy;
	private Class<T> beanClass;
	private Class<M> daoClass;
	private SqlSessionTemplate sqlSessionJp;
	private JpYsdzServiceImpl jpYsdzServiceImpl;
	@SuppressWarnings("unchecked")
	public BankCompareUtil(JpDbParam param,Class<T> beanClass,Class<M> daoClass,JpYsdzServiceImpl jpYsdzServiceImpl,SqlSessionTemplate sqlSessionJp) {
		this.ddhl = param.getDdhl();
		this.wdid=param.getWdid();
		this.wdpt=param.getWdpt();
		this.shbh=param.getShbh();
		this.user=param.getUser();
		this.deptid=param.getDeptid();
		this.dzrq=param.getDzrq();
		this.zbid=param.getZbid();
		this.gsdy=param.getGsdy();
		this.beanClass=beanClass;
		this.daoClass=daoClass;
		this.jpYsdzServiceImpl=jpYsdzServiceImpl;
		this.sqlSessionJp=sqlSessionJp;
	}
	/**
	 * 携程对账
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void xcdz(List<Map<String, Object>> detail_list, List<Map<String, Object>> bank_list) throws Exception{
		wbdhDb(detail_list, bank_list);
		insertResult();
	}
	/**
	 * 同程程对账
	 */
	public void tcdz(List<Map<String, Object>> detail_list, List<Map<String, Object>> bank_list) throws Exception{
		wbdhDb(detail_list, bank_list);
		insertResult();
	}
	/**
	 * 去哪儿银行对账
	 */
	@SuppressWarnings("unchecked")
	public void qnryhdz(List<Map<String, Object>> detail_list, List<Map<String, Object>> bank_list) throws Exception{
		wbdhDb(detail_list, bank_list);
		insertResult();
	}
	/**
	 * 淘宝对账
	 */
	@SuppressWarnings("unchecked")
	public void tbdz(List<Map<String, Object>> detail_list, List<Map<String, Object>> bank_list) throws Exception{
		Map<String, Map<String, Object>> bankMap = new HashMap<String, Map<String, Object>>();//银行账单数据容器
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();//系统数据容器
		// 处理ASMS系统中的数据 以订单号分类
		for (int i = 0; i < detail_list.size(); i++) {
			Map<String, Object> map = detail_list.get(i);
			String wbdh = BankDbUtil.objToString(map.get("WBDH"));
			//如果订单号不存在，则放入只存在系统中，网店必须有供应订单号;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh) && !"0".equals(wbdh)){
				detailMap.put(getTbKey(wbdh,map,detailMap), map);
			}else {
				sysList.add(map);
			}
		}
		/**
		 * 处理银行账单的数据
		 */
		for (int i = 0; i < bank_list.size(); i++) {
			Map<String, Object> map = bank_list.get(i);
			String wbdh = BankDbUtil.objToString(map.get(ddhl));
			//如果订单号不存在，则放入只存在银行账单中，网店必须有供应订单号,没有的基本是其他数据;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh)){
				bankMap.put(getTbKey(wbdh,map,bankMap), map);
			}else {
				bList.add(map);
			}
		}
		//开始按供应订单号对比
		for (String key : detailMap.keySet()) {
			/**
			 * 取出对应的两边对应的数据
			 */
			Map<String, Object> xtMap = detailMap.get(key);
			Map<String, Object> yhMap = bankMap.get(key);// 在银行账单容器中查找
			if (yhMap == null) {// 如果没找到，则只存在系统，找到了就比对金额
				sysList.add(xtMap);
			}else {//比对金额
				jeDb(yhMap,xtMap);
			}
			bank_list.remove(yhMap);//将银行账单中对比过的删除
		}
		if(!bank_list.isEmpty()){
			bList.addAll(bank_list);
		}
		insertResult();
	}
	/**
	 * 供应订单号对比
	 */
	private void wbdhDb(List<Map<String, Object>> detail_list, List<Map<String, Object>> bank_list){
		Map<String, Map<String, Object>> bankMap = new HashMap<String, Map<String, Object>>();//银行账单数据容器
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();//系统数据容器
		// 处理ASMS系统中的数据 以订单号分类
		for (int i = 0; i < detail_list.size(); i++) {
			Map<String, Object> map = detail_list.get(i);
			String wbdh = BankDbUtil.objToString(map.get("WBDH"));
			//如果订单号不存在，则放入只存在系统中，网店必须有供应订单号;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh) && !"0".equals(wbdh)){
				detailMap.put(getKey(wbdh,map), map);
			}else {
				sysList.add(map);
			}
		}
		/**
		 * 处理银行账单的数据
		 */
		for (int i = 0; i < bank_list.size(); i++) {
			Map<String, Object> map = bank_list.get(i);
			String wbdh = BankDbUtil.objToString(map.get(ddhl));
			//如果订单号不存在，则放入只存在银行账单中，网店必须有供应订单号,没有的基本是其他数据;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh)){
				bankMap.put(getKey(wbdh,map), map);
			}else {
				bList.add(map);
			}
		}
		//开始按供应订单号对比
		for (String key : detailMap.keySet()) {
			/**
			 * 取出对应的两边对应的数据
			 */
			Map<String, Object> xtMap = detailMap.get(key);
			Map<String, Object> yhMap = bankMap.get(key);// 在银行账单容器中查找
			if (yhMap == null) {// 如果没找到，则只存在系统，找到了就比对金额
				sysList.add(xtMap);
			}else {//比对金额
				jeDb(yhMap,xtMap);
			}
			bank_list.remove(yhMap);//将银行账单中对比过的删除
		}
		if(!bank_list.isEmpty()){
			bList.addAll(bank_list);
		}
	}
	/**
	 * 写入对账结果
	 * @throws Exception
	 */
	private void insertResult()throws Exception{
		/**
		 * 1.取5天内未到账的数据，与只存在银行账单的数据对比
		 * 2.取5天内漏单的数据，与只存在系统中的数据对比
		 */
		int days=5;
		String maxNexDay=VeDate.getNextDay(dzrq, "-"+days)+" 00:00:00";
		List<Map<String, Object>> lsdzq=new ArrayList<Map<String,Object>>();//历史单当日到账金额正确
		List<Map<String, Object>> lsdyc=new ArrayList<Map<String,Object>>();//历史单当日到账金额异常
		List<Map<String, Object>> lsdzzq=new ArrayList<Map<String,Object>>();//当日单历史到账正确
		List<Map<String, Object>> lsdzyc=new ArrayList<Map<String,Object>>();//当日但历史到账异常
		List<Map<String, Object>> wdzList=null;//历史未到账需要参与对账
		List<Map<String, Object>> ldList=null;//历史漏单需要参与对账
		if(bList.size()>0){
			wdzList=jpYsdzServiceImpl.genWdzOrLd(wdpt, shbh, wdid, "3", dzrq+" 00:00:00", maxNexDay);
		}
		if(sysList.size()>0){
			ldList=jpYsdzServiceImpl.genWdzOrLd(wdpt, shbh, wdid, "6", dzrq+" 00:00:00", maxNexDay);
		}
		/**未到账对账*/
		if(CollectionUtils.isNotEmpty(wdzList)){
			wdzDz(wdzList,lsdzq,lsdyc);
		}
		/**漏单对账*/
		if(CollectionUtils.isNotEmpty(ldList)){
			ldzDz(ldList, lsdzzq, lsdzyc);
		}
		List<Map<String, Object>> firstLdZq=new ArrayList<Map<String,Object>>();//历史单当日到账金额正确--最开始对账
		List<Map<String, Object>> firstLdYc=new ArrayList<Map<String,Object>>();//历史单当日到账金额异常--最开始对账
		firstDz(firstLdZq, firstLdYc);
		String dzrqs="";
		List<Map<String, Object>> lsdzParam=new ArrayList<Map<String,Object>>();//历史未到账的数据今日到账了就需要修改其状态
		int ts=300;
		int count=0;//分批插入，否则数据量大了会内存溢出(1次批量插入500);
		List<T> insertBeans=new ArrayList<T>();
		List<Map<String, String>> ywdds=new ArrayList<Map<String,String>>();//存放对比正确的订单编号
		//历史单当日到账金额正确--最开始对账
		for (Map<String, Object> map : firstLdZq) {
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "4","1");
			putBankCs(pa, map, "4");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
			Map<String, String> ddMap=new HashMap<String, String>();
			ddMap.put("ddbh", BankDbUtil.objToString(map.get("DDBH")));
			ddMap.put("ddlx", BankDbUtil.objToString(map.get("DDLX")));
			ywdds.add(ddMap);
		}
		for (Map<String, Object> map : firstLdYc) {
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "5","2");
			putBankCs(pa, map, "5");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		for (Map<String, Object> map : correctList) {//当日到账正确
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "1","1");
			putBankCs(pa, map, "1");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
			Map<String, String> ddMap=new HashMap<String, String>();
			ddMap.put("ddbh", BankDbUtil.objToString(map.get("DDBH")));
			ddMap.put("ddlx", BankDbUtil.objToString(map.get("DDLX")));
			ywdds.add(ddMap);
		}
		for (Map<String, Object> map : returnList) {//当日到账金额不正确
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "2","2");
			putBankCs(pa, map, "2");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		for (Map<String, Object> map : sysList) {//未到账
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "3","");
			putBankCs(pa, map, "3");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		for (Map<String, Object> map : bList) {//漏单
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "6", "");
			putBankCs(pa, map, "6");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		for (Map<String, Object> map : lsdzq) {//历史单今日到账正确
			String id=String.valueOf(map.get("ID"));
			String syrq=String.valueOf(map.get("SYRQ"));
			syrq=syrq.substring(0,10);
			Map<String, Object> parm=new HashMap<String, Object>();
			parm.put("lsdid", id);
			parm.put("dzje", map.get("发生金额"));
			lsdzParam.add(parm);
			if(!dzrqs.contains(syrq)){
				dzrqs+=","+syrq;
			}
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "4","1");
			putBankCs(pa, map, "4");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
			Map<String, String> ddMap=new HashMap<String, String>();
			ddMap.put("ddbh", BankDbUtil.objToString(map.get("DDBH")));
			ddMap.put("ddlx", BankDbUtil.objToString(map.get("DDLX")));
			ywdds.add(ddMap);
		}
		for (Map<String, Object> map : lsdyc) {//历史单今日到账异常
			String id=String.valueOf(map.get("ID"));
			String syrq=String.valueOf(map.get("SYRQ"));
			syrq=syrq.substring(0,10);
			Map<String, Object> parm=new HashMap<String, Object>();
			parm.put("lsdid", id);
			parm.put("dzje", map.get("发生金额"));
			lsdzParam.add(parm);
			if(!dzrqs.contains(syrq)){
				dzrqs+=","+syrq;
			}
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "5","2");
			putBankCs(pa, map, "5");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		List<T> bdParam=new ArrayList<T>();
		for (Map<String, Object> map : lsdzzq) {//今日单历史到账正确
			String id=String.valueOf(map.get("ID"));
			String syrq=String.valueOf(map.get("SYRQ"));
			syrq=syrq.substring(0,10);
			if(!dzrqs.contains(syrq)){
				dzrqs+=","+syrq;
			}
			Map bdMap=new HashMap();
			bdMap.put("id", id);
			bdMap.put("shbh", shbh);
			bdMap.put("bdSfybd", "1");
			bdMap.put("bdBdrq",map.get("WCSJ"));
			bdMap.put("bdBdr", map.get("DDYH"));
			addInsertOrUpdateBeans(bdMap, bdParam);
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "7","1");
			putBankCs(pa, map, "7");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
			Map<String, String> ddMap=new HashMap<String, String>();
			ddMap.put("ddbh", BankDbUtil.objToString(map.get("DDBH")));
			ddMap.put("ddlx", BankDbUtil.objToString(map.get("DDLX")));
			ywdds.add(ddMap);
		}
		for (Map<String, Object> map : lsdzyc) {//今日单历史到账不正确
			String id=String.valueOf(map.get("ID"));
			String syrq=String.valueOf(map.get("SYRQ"));
			syrq=syrq.substring(0,10);
			if(!dzrqs.contains(syrq)){
				dzrqs+=","+syrq;
			}
			Map bdMap=new HashMap();
			bdMap.put("id", id);
			bdMap.put("shbh", shbh);
			bdMap.put("bdSfybd", "1");
			bdMap.put("bdBdrq",map.get("WCSJ"));
			bdMap.put("bdBdr", map.get("DDYH"));
			addInsertOrUpdateBeans(bdMap, bdParam);
			Map<String,Object> pa=new HashMap<String, Object>();
			pa.put("id",Identities.randomLong()+"");
			putSysCs(pa, map, "7","2");
			putBankCs(pa, map, "7");//银行数据
			addInsertOrUpdateBeans(pa, insertBeans);
			count++;
			if(count==ts){
				insertDzjg(insertBeans);
				count=0;
			}
		}
		//剩下的一次插入
		if(count>0){
			insertDzjg(insertBeans);
		}
		/**更新漏单的补单状态,业务订单没有完成的将漏单的补单状态改为2（即未完成）*/
		String ddsjs=VeDate.getPreDay(dzrq,-5)+" 00:00:00";
		String ddsjz=VeDate.getPreDay(dzrq,1)+" 23:59:59";
		jpYsdzServiceImpl.updateLdzt(shbh, zbid, wdpt,ddsjs, ddsjz);
		if(!lsdzParam.isEmpty()){//更新到账状态
			int ldcnt=0;//控制批量修改的数量
			int ldts=500;//一次修改的条数
			List<T> updateBeans=new ArrayList<T>();
			for(Map<String, Object> m : lsdzParam){
				Map<String, Object> pa=new HashMap<String, Object>();
				pa.put("dzSfdz", "1");
				pa.put("dzDzrq", VeDate.strToDate(dzrq));
				pa.put("dzDzr", user);
				pa.put("by6", m.get("dzje"));
				pa.put("id", m.get("lsdid"));
				pa.put("shbh", shbh);
				addInsertOrUpdateBeans(pa, updateBeans);
				ldcnt++;
				if(ldcnt==ldts){
					updateDzjg(updateBeans);
				}
			}
			if (ldcnt>0) {
				updateDzjg(updateBeans);
			}
		}
		//更新补单状态
		updateDzjg(bdParam);
		dzrqs=dzrq+dzrqs;
		updateDzZbZt(dzrqs);//修改对账主表状态
		updateYwddZt(ywdds);//修改业务表状态
	}
	/**
	 * 批量修改已对账正确的业务单对账状态
	 * @param ywdds
	 */
	private void updateYwddZt(List<Map<String, String>> ywdds){
		List<JpKhdd> ddList=new ArrayList<JpKhdd>();
		List<JpTpd> tpdList=new ArrayList<JpTpd>();
		List<JpGqd> gqdList=new ArrayList<JpGqd>();
		List<JPBcd> bcdList=new ArrayList<JPBcd>();
		for (Map<String, String> one : ywdds) {
			String[] ywdhs=StringUtils.split(one.get("ddbh"),",");
			if("1".equals(one.get("ddlx"))){
				for (String ddbh : ywdhs) {
					JpKhdd khdd=new JpKhdd();
					khdd.setShbh(shbh);
					khdd.setDdbh(ddbh);
					khdd.setXsdzzt("1");
					ddList.add(khdd);
				}
			}else if("2".equals(one.get("ddlx"))){
				for (String ddbh : ywdhs) {
					JpTpd tpd=new JpTpd();
					tpd.setShbh(shbh);
					tpd.setTpdh(ddbh);
					tpd.setXsdzzt("1");
					tpdList.add(tpd);
				}
			}else if("3".equals(one.get("ddlx"))){
				for (String ddbh : ywdhs) {
					JpGqd gqd=new JpGqd();
					gqd.setShbh(shbh);
					gqd.setGqdh(ddbh);
					gqd.setXsdzzt("1");
					gqdList.add(gqd);
				}
			}else if("4".equals(one.get("ddlx"))){
				for (String ddbh : ywdhs) {
					JPBcd bcd=new JPBcd();
					bcd.setShbh(shbh);
					bcd.setId(ddbh);
					bcd.setXsdzzt("1");
					bcdList.add(bcd);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(ddList)){
			new BatchMapperUtils<JpKhddDao>(sqlSessionJp,JpKhddDao.class){
				@Override
				public void exe(JpKhddDao dao, Object o) {
					dao.updateByPrimaryKeySelective((JpKhdd)o);
				}
			}.batchInsert(ddList, 100);
		}
		if(CollectionUtils.isNotEmpty(tpdList)){
			new BatchMapperUtils<JpTpdDao>(sqlSessionJp,JpTpdDao.class){
				@Override
				public void exe(JpTpdDao dao, Object o) {
					dao.updateByPrimaryKeySelective((JpTpd)o);
				}
			}.batchInsert(tpdList, 100);
		}
		if(CollectionUtils.isNotEmpty(gqdList)){
			new BatchMapperUtils<JpGqdDao>(sqlSessionJp,JpGqdDao.class){
				@Override
				public void exe(JpGqdDao dao, Object o) {
					dao.updateByPrimaryKeySelective((JpGqd)o);
				}
			}.batchInsert(gqdList, 100);
		}
		if(CollectionUtils.isNotEmpty(bcdList)){
			new BatchMapperUtils<JpBcdDao>(sqlSessionJp,JpBcdDao.class){
				@Override
				public void exe(JpBcdDao dao, Object o) {
					dao.updateByPrimaryKeySelective((JPBcd)o);
				}
			}.batchInsert(bcdList, 100);
		}
	}
	/**
	 * 更新主表状态
	 * @param wdpt
	 * @param wdbh
	 * @param zwrq
	 * @throws AsmsException 
	 */
	@SuppressWarnings("unchecked")
	private void updateDzZbZt(String zwrq) throws Exception{
		try {
			String perror=jpYsdzServiceImpl.procupdateysdz(wdpt, wdid, zwrq, user, deptid, shbh);
			if(StringUtils.isNotBlank(perror) && !"null".equals(perror)){
				throw new Exception(perror);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.errorZb="更新"+zwrq+"这几天的对账状态失败，请联系管理员！";
			throw new Exception(errorZb);
		}
	}
	private void addInsertOrUpdateBeans(Map<String, Object> pa,List<T> insertBeans) throws Exception{
		T t=beanClass.newInstance();
		BeanUtils.populate(t, pa);
		insertBeans.add(t);
	}
	/**
	 * 批量插入对账结果
	 * @param insertBeans
	 * @throws Exception 
	 */
	private void insertDzjg(List<T> insertBeans) throws Exception{
		try {
			if (CollectionUtils.isNotEmpty(insertBeans)) {
				new BatchMapperUtils<M>(sqlSessionJp,daoClass){
					@Override
					public void exe(M dao, Object o) {
						dao.insert((T)o);
					}
				}.batchInsert(insertBeans, 100);
				insertBeans.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("对账结果保存失败，请联系管理员!");
		}
	}
	/**
	 * 批量修改对账结果
	 * @param updateBeans
	 * @throws Exception 
	 */
	private void updateDzjg(List<T> updateBeans) throws Exception{
		try {
			if (CollectionUtils.isNotEmpty(updateBeans)) {
				new BatchMapperUtils<M>(sqlSessionJp,daoClass){
					@Override
					public void exe(M dao, Object o) {
						dao.updateByPrimaryKeySelective((T)o);
					}
				}.batchInsert(updateBeans, 300);
				updateBeans.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("对账结果保存失败，请联系管理员!");
		}
	}
	/**
	 * 如果是最开始的5天对账，漏单的数据需要检查业务，
	 */
	private void firstDz(List<Map<String, Object>> firstLdZq,List<Map<String, Object>> firstLdYc) throws Exception{
		if(CollectionUtils.isEmpty(bList)){
			return;
		}
		int days=5;
		String dzrqz=VeDate.getNextDay(dzrq, "-"+days);
		List<Map<String,Object>> lswdList=jpYsdzServiceImpl.checkFirstDaysCompare(shbh, wdid, dzrq, dzrqz);
		String yw_ksrq="";
		String yw_jsrq="";
		boolean firstDz=true;
		if (lswdList==null || lswdList.isEmpty()) {
			yw_ksrq=VeDate.getNextDay(dzrq, "-"+days);
			yw_jsrq=VeDate.getNextDay(dzrq, "-1");
		}else if(lswdList.size()<=days){
			yw_ksrq=VeDate.getNextDay(dzrq, "-"+days);
			int cnt=0;//对过账的数量
			for (Map<String, Object> map : lswdList) {
				String dzzt=String.valueOf(map.get("DZ_ZT"));
				if(!"0".equals(dzzt)){
					cnt++;
				}
			}
			if(cnt==days){//days天前的账都对过了,则不是第一次对账
				firstDz=false;
			}else {
				yw_jsrq=VeDate.getNextDay(dzrq, "-"+(cnt+1));
			}
		}
		if(!firstDz){
			return;
		}
		List<Map<String, Object>> bListRq=new ArrayList<Map<String,Object>>();
		for(Map<String, Object> ldMap : bList){
			List<Map<String, Object>> ywList=getLdYwsjList(ldMap, yw_ksrq,yw_jsrq);//查找系统中对应的业务数据
			if (ywList==null || ywList.isEmpty()) {
				bListRq.add(ldMap);//没找到的银行账单数据
				continue;
			}
			List<Map<String, Object>> asmsList=ywList;
			if(ywList.size()>1){
				asmsList=BankDbUtil.hbWbdh(ywList);
			}
			firstDzldDb(asmsList.get(0), ldMap, firstLdZq, firstLdYc);
		}
		bList.clear();
		bList.addAll(bListRq);
	}
	@SuppressWarnings("unchecked")
	private void firstDzldDb(Map asms,Map<String, Object> ldMap,List<Map<String, Object>> firstLdZq,List<Map<String, Object>> firstLdYc){
		double xtJe=BankDbUtil.obj2Double(asms.get("ZFJE"));
		double yhJe=BankDbUtil.obj2Double(ldMap.get("发生金额"));
		Map<String, Object> map=new HashMap<String, Object>();
		map.putAll(asms);
		map.putAll(ldMap);
		if(Math.abs(Arith.round(xtJe, 2)) == Math.abs(Arith.round(yhJe, 2))){//金额正确
			firstLdZq.add(map);
		}else {//金额错误
			firstLdYc.add(map);
		}
	}
	/**
	 * 未到账对账
	 * @param wdzList
	 * @param lsdzq
	 * @param lsdyc
	 */
	@SuppressWarnings("unchecked")
	private void wdzDz(List<Map<String, Object>> wdzList,List<Map<String, Object>> lsdzq,List<Map<String, Object>> lsdyc){
		Map<String, Map<String, Object>> bankMap = new HashMap<String, Map<String, Object>>();//银行账单数据容器
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();//系统数据容器
		// 处理ASMS系统中的数据 以订单号分类
		for (int i = 0; i < wdzList.size(); i++) {
			Map<String, Object> map = wdzList.get(i);
			String wbdh = BankDbUtil.objToString(map.get("GYDDH"));
			//如果订单号不存在，则放入只存在系统中，网店必须有供应订单号;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh) && !"0".equals(wbdh)){
				detailMap.put(getKey(wbdh,map), map);
			}else {
				//历史未到账，没供应订单号不做处理
			}
		}
		/**
		 * 处理银行账单的数据
		 */
		for (int i = 0; i < bList.size(); i++) {
			Map<String, Object> map = bList.get(i);
			String wbdh = BankDbUtil.objToString(map.get(ddhl));
			//如果订单号不存在，则放入只存在银行账单中，网店必须有供应订单号,没有的基本是其他数据;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh)){
				bankMap.put(getKey(wbdh,map), map);
			}else {
				
			}
		}
		//开始按供应订单号对比
		for (String key : detailMap.keySet()) {
			/**
			 * 取出对应的两边对应的数据
			 */
			Map<String, Object> xtMap = detailMap.get(key);
			Map<String, Object> yhMap = bankMap.get(key);// 在银行账单容器中查找
			if (yhMap != null) {// 如果没找到，则不做处理，找到了就比对金额
				double xtJe=BankDbUtil.obj2Double(xtMap.get("ZFJE"));
				double yhJe=BankDbUtil.obj2Double(yhMap.get("发生金额"));
				Map<String, Object> map=new HashMap<String, Object>();
				map.putAll(xtMap);
				map.putAll(yhMap);
				if(Math.abs(Arith.round(xtJe, 2)) == Math.abs(Arith.round(yhJe, 2))){//金额正确
					lsdzq.add(map);
				}else {//金额错误
					lsdyc.add(map);
				}
				bList.remove(yhMap);//将银行账单中对比过的删除
			}
		}
	}
	/**
	 * 漏单对账
	 * @param wdzList
	 * @param lsdzq
	 * @param lsdyc
	 */
	@SuppressWarnings("unchecked")
	private void ldzDz(List<Map<String, Object>> ldList,List<Map<String, Object>> lsdzzq,List<Map<String, Object>> lsdzyc){
		Map<String, Map<String, Object>> bankMap = new HashMap<String, Map<String, Object>>();//银行账单数据容器
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();//系统数据容器
		// 处理ASMS系统中的数据 以订单号分类
		for (int i = 0; i < sysList.size(); i++) {
			Map<String, Object> map = sysList.get(i);
			String wbdh = BankDbUtil.objToString(map.get("WBDH"));
			//如果订单号不存在，则放入只存在系统中，网店必须有供应订单号;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh) && !"0".equals(wbdh)){
				detailMap.put(getKey(wbdh,map), map);
			}else {
				//历史未到账，没供应订单号不做处理
			}
		}
		/**
		 * 处理银行账单的数据
		 */
		for (int i = 0; i < ldList.size(); i++) {
			Map<String, Object> map = ldList.get(i);
			String wbdh = BankDbUtil.objToString(map.get("WD_DDH"));
			//如果订单号不存在，则放入只存在银行账单中，网店必须有供应订单号,没有的基本是其他数据;以供应订单号做key，后面用key查找比对
			if(StringUtils.isNotBlank(wbdh)){
				bankMap.put(getKey(wbdh,map), map);
			}else {
				
			}
		}
		//开始按供应订单号对比
		for (String key : detailMap.keySet()) {
			/**
			 * 取出对应的两边对应的数据
			 */
			Map<String, Object> xtMap = detailMap.get(key);
			Map<String, Object> yhMap = bankMap.get(key);// 在银行账单容器中查找
			if (yhMap != null) {// 如果没找到，则不做处理，找到了就比对金额
				double xtJe=BankDbUtil.obj2Double(xtMap.get("ZFJE"));
				double yhJe=BankDbUtil.obj2Double(yhMap.get("WD_FSJE"));
				Map<String, Object> map=new HashMap<String, Object>();
				map.putAll(yhMap);
				map.putAll(xtMap);
				if(Math.abs(Arith.round(xtJe, 2)) == Math.abs(Arith.round(yhJe, 2))){//金额正确
					lsdzzq.add(map);
				}else {//金额错误
					lsdzyc.add(map);
				}
				sysList.remove(xtMap);//将银行账单中对比过的删除
			}
		}
	}
	private List<Map<String, Object>> getLdYwsjList(Map<String, Object> ldMap,String ksrq,String jsrq) throws Exception{
		String ddlx=String.valueOf(ldMap.get("DDLX"));
		ksrq+=" 00:00:00";
		jsrq+=" 23:59:59";
		String wbdh=BankDbUtil.objToString(ldMap.get(ddhl));
		return jpYsdzServiceImpl.genDzDataByWbdh(shbh, ddlx,wbdh, ksrq, jsrq);
	}
	/**
	 * <功能详细描述> 对比银行账单和系统中已对应上的数据的金额
	 * 
	 * @param asms_key
	 * @param yhMap
	 * @param xtMap
	 * @param detail_list
	 * @param bank_list
	 *            [参数说明]
	 */
	private void jeDb(Map<String, Object> yhMap, Map<String, Object> xtMap) {
		double xtJe=BankDbUtil.obj2Double(xtMap.get("ZFJE"));
		double yhJe=BankDbUtil.obj2Double(yhMap.get("发生金额"));
		Map<String, Object> map=new HashMap<String, Object>();
		map.putAll(xtMap);
		map.putAll(yhMap);
		if(Math.abs(Arith.round(xtJe, 2)) == Math.abs(Arith.round(yhJe, 2))){//金额正确
			correctList.add(map);
		}else {//金额错误
			returnList.add(map);
		}
	}
	private String getKey(String key,Map<String, Object> sysMap) {
		String ddlx=BankDbUtil.objToString(sysMap.get("DDLX"));
		if("1".equals(ddlx)){
			return key+"zcd";
		}else if ("2".equals(ddlx)) {
			return key+"tpd";
		}else if ("3".equals(ddlx)) {
			return key+"gqd";
		}else {
			return key+"bcd";
		}
	}
	private String getTbKey(String key,Map<String, Object> sysMap,Map<String, Map<String, Object>> bankMap) {
		String ddlx=BankDbUtil.objToString(sysMap.get("DDLX"));
		if("1".equals(ddlx)){
			return key+"zcd";
		}else if ("2".equals(ddlx)) {
			return key+"tpd";
		}else if ("3".equals(ddlx)) {
			return key+"gqd";
		}else {
			if(bankMap.get(key+"tpd") !=null){
				return key+"tpdcf";
			}
			return key+"tpd";
		}
	}
	/**
	 * 系统数据字段名相同
	 * @param param
	 * @param map
	 * @param jglx
	 * @param jezqf
	 */
	private void putSysCs(Map<String,Object> param,Map<String, Object> map,String jglx,String jezqf){
		param.put("shbh", shbh);
		param.put("zbid",zbid);
		param.put("wdid",wdid);
		param.put("jglx",jglx);
		param.put("ddlx",map.get("DDLX"));
		param.put("syrq",VeDate.strToDate(dzrq));
		if("6".equals(jglx)){
			param.put("bdSfybd","0");
		}else{
			param.put("ddbh",map.get("DDBH"));
			if("1".equals(jglx) || "2".equals(jglx) || "3".equals(jglx) || "7".equals(jglx) 
					|| StringUtils.isNotBlank(BankDbUtil.objToString(map.get("WBDH")))){
				param.put("gyddh",map.get("WBDH"));
				param.put("pnrno",map.get("PNRNO"));
				param.put("tkno",map.get("TKNOS"));
			}else {
				param.put("gyddh",map.get("GYDDH"));
				param.put("pnrno",map.get("PNRNO"));
				param.put("tkno",map.get("TKNO"));
			}
			param.put("zfje",map.get("ZFJE"));
			param.put("jesfzq",jezqf);
			param.put("","");
			param.put("","");
			if("3".equals(jglx)){
				param.put("dzSfdz","0");
			}
			if("7".equals(jglx)){
				String dzrq=BankDbUtil.objToString(map.get("WCSJ"));
				if(StringUtils.isNotBlank(dzrq)){
					param.put("dzDzrq",dzrq);
				}
			}
		}
	}
	/**
	 * 插入网店账单数据
	 * @param pa
	 * @param map
	 * @param jglx
	 */
	private void putBankCs(Map<String, Object> param,Map<String, Object> map,String jglx){
		if("1".equals(jglx) || "2".equals(jglx) || "4".equals(jglx) || "5".equals(jglx) || "6".equals(jglx)){
			for(String gs : gsdy){//WD_ZFJE:发生金额|WD_DDBH:订单号
				String[] dys=gs.split(":");
				String key=EntityHelper.underlineToCamelhump(dys[0].toLowerCase());//如果有下划线转成驼峰
				param.put(key, map.get(dys[1]));
			}
		}else if("7".equals(jglx)){
			for(String gs : gsdy){
				String[] dys=gs.split(":");
				String key=EntityHelper.underlineToCamelhump(dys[0].toLowerCase());//如果有下划线转成驼峰
				param.put(key,map.get(dys[0]));
			}
		}
	}
}
