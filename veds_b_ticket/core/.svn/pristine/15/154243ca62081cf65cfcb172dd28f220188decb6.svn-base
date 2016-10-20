package org.vetech.core.modules.service;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.vetech.core.modules.mybatis.mapper.Mapper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper.EntityColumn;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.dhtmlx.DhtmlxList;
import org.vetech.core.modules.web.dhtmlx.DhtmlxPage;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DhtmlxBaseService<T extends AbstractPageEntity, M extends Mapper<T>> {
	protected Class<T> entityClass;
	public DhtmlxBaseService(){
		if (this.entityClass == null) {
			this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
	}
	@Autowired
	private M myBatisDao;
	public M getMyBatisDao() {
		return myBatisDao;
	}

	public void setMyBatisDao(M myBatisDao) {
		this.myBatisDao = myBatisDao;
	}
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteById(T t){
		
		this.myBatisDao.deleteByPrimaryKey(t);
	}
	@Transactional
	public void save(List<T> list) throws Exception{
//		for (int i = 0; i < list.size(); i++) {
//			T t = list.get(i);
//			if("add".equals(t.getDhtmlxType())){
//				insert(t);
//			}else if("update".equals(t.getDhtmlxType())){
//				updateSelective(t);
//			}else if("delete".equals(t.getDhtmlxType())){
//				deleteById(t);
//			}
//		}
	}
	public void insert(T t) throws Exception{
		Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
		Iterator<EntityColumn> it = entityColumns.iterator();
		EntityColumn entityColumn = null;
		while (it.hasNext()) {
			entityColumn = it.next();
			if(entityColumn.isId() && !entityColumn.isBusinessid()){
				break;
			}
		}
		if(entityColumn.isUuid()){
			
			BeanUtils.setProperty(t, entityColumn.getProperty(), UUID.randomUUID().toString().replaceAll("-", ""));
		}else if("NO".equals(StringUtils.upperCase(entityColumn.getGenerator()))){
			BeanUtils.setProperty(t, entityColumn.getProperty(), VeDate.getNo(6));
		}
		this.myBatisDao.insert(t);
	}
	public void update(T t) throws Exception{
		this.myBatisDao.updateByPrimaryKey(t);
	}
	public void updateSelective(T t)throws Exception{
		this.myBatisDao.updateByPrimaryKeySelective(t);
	}
	/**
	 * 根据id获得一个实体类
	 * @param id
	 * @return
	 */
	public T getEntityById(T t){
		return myBatisDao.selectByPrimaryKey(t);
	}
	public DhtmlxPage queryPage(Map param,int pageNum,int pageSize){
		Page page =new Page(pageNum,pageSize);
		PageHelper.start(page);
		List list = this.selectDynamicSQL(param);
		int totalCount = myBatisDao.countDynamicSQL(entityClass, param);
		DhtmlxPage<T> dhtmlxPage = new DhtmlxPage<T>();
		dhtmlxPage.setTotal_count(totalCount);
		dhtmlxPage.setData(list);
		dhtmlxPage.setPos(pageNum);
		return dhtmlxPage;
	}
	public List<?> selectDynamicSQL(Map param){
		List list = myBatisDao.selectDynamicSQL(entityClass, param);
		return EntityHelper.maplist2BeanList(list, entityClass);
	}
	public DhtmlxList selectDynamicSQLDhtmlx(Map param){
		List list = myBatisDao.selectDynamicSQL(entityClass, param);
		DhtmlxList dhtmlxList = new DhtmlxList();
		dhtmlxList.setData(EntityHelper.maplist2BeanList(list, entityClass));
		return dhtmlxList;
	}
}
