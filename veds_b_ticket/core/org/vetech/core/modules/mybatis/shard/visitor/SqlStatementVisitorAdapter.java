package org.vetech.core.modules.mybatis.shard.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.vetech.core.modules.mybatis.entity.AbstractMyBatisEntity;
import org.vetech.core.modules.mybatis.shard.converter.JdbcParamFinder;
import org.vetech.core.modules.mybatis.util.MapperUtil;

public class SqlStatementVisitorAdapter extends StatementVisitorAdapter{
	private List<Map<String,Object>> jdbcParams = new ArrayList<Map<String,Object>>();
	private Object params;
	private List<ParameterMapping> parameterMappings;
	public SqlStatementVisitorAdapter(Object params,List<ParameterMapping> parameterMappings){
		this.params = params;
		this.parameterMappings = parameterMappings;
	}

	public List<Map<String, Object>> getJdbcParams() {
		return jdbcParams;
	}

	@Override
	public void visit(Select select) {
		JdbcParamFinder conditionFinder = new JdbcParamFinder();
		select.getSelectBody().accept(conditionFinder);
		List<Map<String,Object>> conditionlist = conditionFinder.getJdbcParamlist();
		/**
		 * parameterMappings与jdbcParamList顺序一致
		 * 将params解析出来对应到jdbcParamList中
		 */
		 
		if(!(params instanceof AbstractMyBatisEntity) && !(params instanceof Map)){
			//参数不是map和实体对象，那么params是单类型例如String,例如根据ID查询用到
			for(int i=0;i<conditionlist.size();i++){
				Map map = conditionlist.get(i);
				String key = (String)map.keySet().iterator().next();
				Map<String, Object> tmp = new HashMap<String, Object>();
				tmp.put(key.toUpperCase(), params);
				jdbcParams.add(tmp);
			}
		}else{
			for(int i=0;i<conditionlist.size();i++){
				ParameterMapping parameterMapping = parameterMappings.get(i);
				String property = parameterMapping.getProperty();
				MetaObject metaObjectParam = MapperUtil.forObject(params);
				Object value= metaObjectParam.getValue(property);
				Map map = conditionlist.get(i);
				String key = (String)map.keySet().iterator().next();
				Map<String, Object> tmp = new HashMap<String, Object>();
				tmp.put(key.toUpperCase(), value);
				jdbcParams.add(tmp);
			}
		}
	}

	@Override
	public void visit(Delete delete) {
		JdbcParamFinder statementVisitor = new JdbcParamFinder();
		//只处理where语句后面的带有问号参数
		delete.getWhere().accept(statementVisitor);
		List<Map<String, Object>> jdbcParamList = statementVisitor.getJdbcParamlist();
		/**
		 * parameterMappings与jdbcParamList顺序一致
		 * 将params解析出来对应到jdbcParamList中
		 */
		for(int i=0;i<jdbcParamList.size();i++){
			ParameterMapping parameterMapping = parameterMappings.get(i);
			String property = parameterMapping.getProperty();
			MetaObject metaObjectParam = MapperUtil.forObject(params);
			Object value= metaObjectParam.getValue(property);
			Map map = jdbcParamList.get(i);
			String key = (String)map.keySet().iterator().next();
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put(key.toUpperCase(), value);
			jdbcParams.add(tmp);
		}
	}

	@Override
	public void visit(Update update) {
		JdbcParamFinder statementVisitor = new JdbcParamFinder();
		if (update.getWhere() != null) {
			update.getWhere().accept(statementVisitor);
		}
		List<Map<String, Object>> jdbcParamList = statementVisitor.getJdbcParamlist();
		/**
		 * parameterMappings与jdbcParamList顺序一致
		 * 将params解析出来对应到jdbcParamList中
		 */
		for(int i=0;i<jdbcParamList.size();i++){
			//update只处理where后面的字段条件，因此parameterMappings包含所有JDBC参数，因此要从where开始获取parameterMappings参数内容
			ParameterMapping parameterMapping = parameterMappings.get(parameterMappings.size()-jdbcParamList.size()+i);
			String property = parameterMapping.getProperty();
			MetaObject metaObjectParam = MapperUtil.forObject(params);
			Object value= metaObjectParam.getValue(property);
			Map map = jdbcParamList.get(i);
			String key = (String)map.keySet().iterator().next();
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put(key.toUpperCase(), value);
			jdbcParams.add(tmp);
		}
	}

	@Override
	public void visit(Insert insert) {
		/**
		 * insert获取参数，是将参数名称作为字段名称
		 */
		for(ParameterMapping parameterMapping : parameterMappings){
			Map<String, Object> map = new HashMap<String, Object>();
			String property = parameterMapping.getProperty();
			property = property.replaceAll("_bind","" );
			MetaObject metaObject = MapperUtil.forObject(params);
			Object value = metaObject.getValue(property);
			map.put(property.toUpperCase(), value);
			jdbcParams.add(map);
		}
	}
	
}
