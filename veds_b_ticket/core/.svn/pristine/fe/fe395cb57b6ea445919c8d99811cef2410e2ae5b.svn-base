package org.vetech.core.modules.web;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper.EntityColumn;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class MBaseControl<T extends AbstractPageEntity,S extends MBaseService> extends AbstractBaseControl{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected S baseService;
	private static final String PAGE_SIZE = "10";
	protected Class<T> entityClass;
	public MBaseControl(){
		if (this.entityClass == null) {
			this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
	}
	public abstract void updateInitEntity(T t);
	public abstract void insertInitEntity(T t) ;
	public void pageAfter(Page page){
		
	}
	
	public void selectInitEntity(Map<String,Object> param){
		
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("entity") T t,BindingResult bindingResult, ModelMap model){
		try{
			this.valid(bindingResult);
			String edit = (String)model.get("EDIT");
			if("1".equals(edit)){
				updateInitEntity(t);
				this.baseService.update(t);
			}else{
				insertInitEntity(t);
				this.baseService.insert(t);
			}
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	private T initEntity(ModelMap model) throws Exception{
		T t = (T)model.get("initEntity");
		if(t==null){
			t = (T)entityClass.newInstance();
			model.addAttribute("initEntity", t);
		}
		return t;
	}
	@RequestMapping(value = "delete_{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, ModelMap model){
		try{
			T t = initEntity(model);
			Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
			Iterator<EntityColumn> it = entityColumns.iterator();
			while (it.hasNext()) {
				EntityColumn entityColumn = it.next();
				if(entityColumn.isId() && !entityColumn.isBusinessid()){
					BeanUtils.setProperty(t, entityColumn.getProperty(), id);
					break;
				}
			}
			this.baseService.deleteById(t);
			return "/common/turning";
		}catch (Exception e) {
			logger.error("删除错误", e);
			return this.addError("删除错误"+e.getMessage(),e, "list",model);
		}
	}
	
	@RequestMapping(value = "list",method = RequestMethod.POST)
	public @ResponseBody Page list(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType,String sortName, Model model,
			HttpServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_",true);
		selectInitEntity(searchParams);
		Page page = this.baseService.queryPage(searchParams, pageNum, pageSize);
		pageAfter(page);
		return page;
	}
	
	@RequestMapping(value = "edit_{id}")
	public String edit(@PathVariable("id") String id, ModelMap model) {
		try{
			T t = initEntity(model);
			Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
			Iterator<EntityColumn> it = entityColumns.iterator();
			while (it.hasNext()) {
				EntityColumn entityColumn = it.next();
				if(entityColumn.isId() && !entityColumn.isBusinessid()){
					BeanUtils.setProperty(t, entityColumn.getProperty(), id);
					break;
				}
			}
			model.addAttribute("entity", this.baseService.getEntityById(t));
			return viewpath("edit");
		}catch (Exception e) {
			logger.error("编辑错误", e);
			return this.addError("编辑错误"+e.getMessage(), e, "edit", model);
		}
	}
	/**
	 * 每个方法执行前都会执行这个方法
	 * 这个方法在编辑时保存的时候非常有用，原理是从数据库取出了数据，在于页面的数据进行比较，填充
	 * @param id
	 * @param model
	 */
	@ModelAttribute
	public void editForm(@RequestParam(value = "id", defaultValue = "-1") String id, ModelMap model) {
		try{
			if (!id.equals("-1")) {
				T t = initEntity(model);
				Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
				Iterator<EntityColumn> it = entityColumns.iterator();
				while (it.hasNext()) {
					EntityColumn entityColumn = it.next();
					if(entityColumn.isId() && !entityColumn.isBusinessid()){
						BeanUtils.setProperty(t, entityColumn.getProperty(), id);
						break;
					}
				}
				Object o = this.baseService.getEntityById(t);
				if(o!=null){
					model.addAttribute("entity",o);
					model.addAttribute("EDIT","1");
				}
			}
		}catch (Exception e) {
			logger.error("编辑错误", e);
		}
	}
	
	private Set<EntityColumn> getBusinessId(){
		Set<EntityColumn> entityColumns = EntityHelper.getPKColumns(this.entityClass);
		Iterator<EntityColumn> it = entityColumns.iterator();
		Set<EntityColumn> sets = new HashSet<EntityHelper.EntityColumn>();
		while (it.hasNext()) {
			EntityColumn entityColumn = it.next();
			if(entityColumn.isBusinessid()){
				sets.add(entityColumn);
			}
		}
		return sets;
	}
	protected void setBusinessId(ModelMap model,String name,Object value) throws Exception{
		T t = initEntity(model);
		Set<EntityColumn> entityColumns = getBusinessId();
		Iterator<EntityColumn> it = entityColumns.iterator();
		while (it.hasNext()) {
			EntityColumn entityColumn = it.next();
			if(entityColumn.getProperty().equals(name)){
				BeanUtils.setProperty(t, entityColumn.getProperty(), value);
			}
		}
	}
}
