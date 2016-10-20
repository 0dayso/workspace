package org.vetech.core.modules.service;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vetech.core.modules.mybatis.mapper.Mapper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper.EntityColumn;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.VeDate;
/**
 * Mybaits基础service
 * @author 章磊
 *
 * @param <M> Mybatis的dao
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class MBaseService<T extends AbstractPageEntity, M extends Mapper<T>> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Class<T> entityClass;
	public MBaseService(){
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
	public int deleteById(T t){
		return this.myBatisDao.deleteByPrimaryKey(t);
	}
	public int delete(T t){
		return this.myBatisDao.delete(t);
	}
	public int insert(T t) throws Exception{
		Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
		Iterator<EntityColumn> it = entityColumns.iterator();
		EntityColumn entityColumn = null;
		while (it.hasNext()) {
			entityColumn = it.next();
			if(entityColumn.isId() && !entityColumn.isBusinessid()){
				break;
			}
		}
		//当前台传入了ID,如果有值,直接insert
		String id = BeanUtils.getProperty(t, entityColumn.getProperty());
		if(StringUtils.isBlank(id)){
			if(entityColumn.isUuid()){
				BeanUtils.setProperty(t, entityColumn.getProperty(), UUID.randomUUID().toString().replaceAll("-", ""));
			}else if("NO".equals(StringUtils.upperCase(entityColumn.getGenerator()))){
				BeanUtils.setProperty(t, entityColumn.getProperty(), VeDate.getNo(6));
			}
		}
		
		return this.myBatisDao.insert(t);
	}
	public int update(T t) throws Exception{
		return this.myBatisDao.updateByPrimaryKey(t);
	}
	public int updateSelective(T t)throws Exception{
		return this.myBatisDao.updateByPrimaryKeySelective(t);
	}
	public List<T> queryList(T t)throws Exception{
		return this.myBatisDao.select(t);
	}
	/**
	 * 根据id获得一个实体类
	 * @param id
	 * @return
	 */
	public T getEntityById(T t){
		return myBatisDao.selectByPrimaryKey(t);
	}
	public Page queryPage(Map param,int pageNum,int pageSize){
		Page page =new Page(pageNum,pageSize);
		PageHelper.start(page);
		List list = this.selectDynamicSQL(param);
		int totalCount = myBatisDao.countDynamicSQL(entityClass, param);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	public List selectDynamicSQL(Map param){
		List list = myBatisDao.selectDynamicSQL(entityClass, param);
		return EntityHelper.maplist2BeanList(list, entityClass);
	}
}
