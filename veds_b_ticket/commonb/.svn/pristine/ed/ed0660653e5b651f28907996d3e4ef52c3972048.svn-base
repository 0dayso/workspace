package cn.vetech.vedsb.common.service;

import java.util.List;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.BYdrzCzrzDao;
import cn.vetech.vedsb.common.entity.BYdrzCzrz;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.web.vedsb.SessionUtils;
/**
 * 操作日志service
 */
@Service
public class BYdrzCzrzServiceImpl extends MBaseService<BYdrzCzrz,BYdrzCzrzDao>{
	/**
	 * 注入批量操作Service
	 */
	@Autowired
	public BYdrzCzrzMxServiceImpl YdrzCzrzMxServiceImpl;
	/**
	 * 一条数据记录异动
	 * @param o    数据库的数据
	 * @param n    新的数据
	 * @param id   业务id
	 * @param zts  状态 ，做的什么操作（新增，删除，修改……）
	 * @param czr  操作人 
	 * @param czid 操作id
	 * @param ckfw 查看范围（0查看所有,A,B）
	 * @param shbh 商户编号
	 * @param bz   备注
	 * czr 操作人;  czid 操作id;  shbh 商户编号 ; 从Session中取值
	 */
	 public void xzrz(Object o,Object n,String id,String zts,String czr,String czid,String ckfw,String shbh,String bz){
		Class cls = n.getClass();
		BYdrzCzrz ydrzCzrz =new BYdrzCzrz();
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		String bm = "";
		if(cls.isAnnotationPresent(Table.class)){
			Table table = (Table)cls.getAnnotation(Table.class);
			bm = table.name()+"_czrz";
		}
		LogUtils log =new LogUtils();
		String czlr="";
		try {
			if(o==null){
				ydrzCzrz.setCznr(zts);
			}else{
				czlr=log.getCznr(o, n);
				if(StringUtils.isBlank(czlr)){
					return;
				}
				ydrzCzrz.setCznr(czlr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ydrzCzrz.setBm(bm);
		ydrzCzrz.setYwid(id);
		ydrzCzrz.setCzr(czr);
		ydrzCzrz.setPt("B");
		ydrzCzrz.setCzly(zts);
		if(StringUtils.isNotBlank(ydrzCzrz.getCzip())){
			ydrzCzrz.setCzip(user.getIp());   //操作IP
		}else{
			ydrzCzrz.setCzip("127.0.0.1");   //操作IP
		}
		ydrzCzrz.setCzjqm("0");   //操作机器名
		ydrzCzrz.setCkfw(ckfw);   //查看范围0表示A\B系统都可查看
		ydrzCzrz.setShbh(shbh);
		ydrzCzrz.setBzbz(bz);	
		
		if(StringUtils.isNotBlank(ydrzCzrz.getCznr())){//操作内容为空时不记录日志
			this.getMyBatisDao().xzrz(ydrzCzrz);
		}
	}
	
	/**
	 * 多条操作，记录异动
	 * @param obj
	 * @param zts  状态 ，做的什么操作（批量删除，批量启用……）
	 * @param czr  操作人 
	 * @param czid 操作id
	 * @param ckfw 查看范围（0查看所有,A,B）
	 * @param shbh 商户编号
	 * @param bz   备注
	 * @param ywids  业务id传回数组
	 */
	public void xzrzMx(Object obj,String zts,String czr,String czid,String ckfw,String shbh,String bz,String[] ywids){
		Class cls = obj.getClass();
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		String bm = "";
		if(cls.isAnnotationPresent(Table.class)){
			Table table = (Table)cls.getAnnotation(Table.class);
			bm = table.name()+"_czrz";
		}
		BYdrzCzrz ydrzCzrz =new BYdrzCzrz();
		ydrzCzrz.setBm(bm);
		ydrzCzrz.setYwid("---");
		ydrzCzrz.setCzr(czr);
		ydrzCzrz.setPt("B");
		ydrzCzrz.setCzly(zts);
		ydrzCzrz.setCznr(zts);
		ydrzCzrz.setCzip(user.getIp()); //操作IP
		ydrzCzrz.setCzjqm("0");//操作机器名
		ydrzCzrz.setCkfw(ckfw); //查看范围0表示A\B系统都可查看
		ydrzCzrz.setShbh(shbh);
		ydrzCzrz.setBzbz(bz);	
		
		this.getMyBatisDao().xzrz(ydrzCzrz);
		String id = ydrzCzrz.getId();
		YdrzCzrzMxServiceImpl.xzrzMx(bm, id, ywids);
		
	}
	/**
	 * 异动日志
	 * 直接传入操作内容,用于同时操作多张表的情况，把修改内容拼好传过来，日志表以主表命名
	 * @param o
	 * @param cznr
	 * @param zts
	 * @param ckfw
	 * @param bz
	 */
	public void xzrzNr(Object o,String cznr,String id,String zts,String czr,String czip,String ckfw,String shbh,String bz){
		BYdrzCzrz ydrzCzrz =new BYdrzCzrz();
		String bm = "";
		if (o != null) {
			Class cls = o.getClass();
			if (cls.isAnnotationPresent(Table.class)) {
				Table table = (Table) cls.getAnnotation(Table.class);
				bm = table.name() + "_czrz";
			}
		}
		try {
			if(o==null){
				ydrzCzrz.setCznr("新增");
			}else{
				if(StringUtils.isNotBlank(cznr)){
					ydrzCzrz.setCznr(cznr);
				}else{
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ydrzCzrz.setBm(bm);
		ydrzCzrz.setYwid(id);
		ydrzCzrz.setCzr(czr);
		ydrzCzrz.setPt("B");
		ydrzCzrz.setCzly(zts);
		ydrzCzrz.setCzip(czip);   //操作IP
		ydrzCzrz.setCzjqm("0");   //操作机器名
		ydrzCzrz.setCkfw(ckfw);   //查看范围0表示A\B系统都可查看
		ydrzCzrz.setShbh(shbh);
		ydrzCzrz.setBzbz(bz);	
		
		this.getMyBatisDao().xzrz(ydrzCzrz);
	}
	/**
	 * 分页查询方法
	 */
	public Page queryPage(BYdrzCzrz o, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List list = this.getMyBatisDao().searchCzrz(o);
		int totalCount = this.getMyBatisDao().getTotalCount(o);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	/**
	 * 批量分页查询方法
	 */
	public Page queryPages(BYdrzCzrz o, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List list = this.getMyBatisDao().searchCzrzs(o);
		int totalCount = this.getMyBatisDao().getTotalCounts(o);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
}
