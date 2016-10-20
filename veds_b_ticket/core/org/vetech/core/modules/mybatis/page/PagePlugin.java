package org.vetech.core.modules.mybatis.page;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mybatis.util.MapperUtil;
/**
 * 分页插件
 * @author 章磊
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {
	private final Logger logger = LoggerFactory.getLogger(PagePlugin.class);
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		if (invocation.getTarget() instanceof StatementHandler) {
			Page page = PageHelper.get();
			if (page == null) {
				return invocation.proceed();
			}
			PageHelper.end();
			try{
				StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
				MetaObject metaStatementHandler = MapperUtil.findTargetObject(statementHandler);
				BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
				Object paramObject = boundSql.getParameterObject();
				int start = (page.getPageNum() - 1) * page.getPageSize() + 1;
				int count = page.getPageSize() + (page.getPageNum() - 1) * page.getPageSize();
				if (paramObject instanceof AbstractPageEntity) {
					((AbstractPageEntity) paramObject).setStart(start);
					((AbstractPageEntity) paramObject).setCount(count);
				} else if (paramObject instanceof Map) {
					Map param = (Map) paramObject;
					param.put("start", start);
					param.put("count", count);
				} else {
					return invocation.proceed();
				}
				String sql = boundSql.getSql();
				String changedsql = changeSql(sql);
				Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
				ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, "count", new IntegerTypeHandler());
				List paramMappings = boundSql.getParameterMappings();
				if (paramMappings.isEmpty()) {// 当没有传入参数的时候，这个list是不可修改类型，那么add参数的时候会报错，因此要重新构造一个ArrayList
					paramMappings = new ArrayList<ParameterMapping>();
					metaStatementHandler.setValue("delegate.boundSql.parameterMappings", paramMappings);
				}
				if(!findStartAndCount(paramMappings)){	//存在就不要在加start和count参数
					boundSql.getParameterMappings().add(builder.build());
					ParameterMapping.Builder rnbuilder = new ParameterMapping.Builder(configuration, "start", new IntegerTypeHandler());
					boundSql.getParameterMappings().add(rnbuilder.build());
				}
				metaStatementHandler.setValue("delegate.boundSql.sql", changedsql.toString());
	
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
		return invocation.proceed();
	}
	/**
	 * 找参数中是否存在start和count参数
	 * @param paramMappings
	 * @return
	 */
	private boolean findStartAndCount(List<ParameterMapping> paramMappings){
		if(paramMappings!=null && !paramMappings.isEmpty()){
			boolean isStart = false;
			boolean isCount = false;
			for(ParameterMapping parameterMapping : paramMappings){
				if("start".equals(parameterMapping.getProperty())){
					isStart = true;
				}
				if("count".equals(parameterMapping.getProperty())){
					isCount = true;
				}
			}
			if(isStart && isCount){
				return true;
			}
		}
		return false;
	}
	/**
	 * 将内部sql的字段全部移动到外部查询，避免有子查询导致性能问题
	 * @param sql
	 * @return
	 * @throws JSQLParserException
	 */
	private String changeSql(String sql) throws JSQLParserException {
		Select select = (Select) CCJSqlParserUtil.parse(sql);
		PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
		List<SelectItem> list = plainSelect.getSelectItems();
		StringBuffer fieldsBuf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			fieldsBuf.append(list.get(i)).append(",");
		}
		String fields = fieldsBuf.substring(0, fieldsBuf.length() - 1);
		List<SelectItem> newList = new ArrayList<SelectItem>();
		newList.add(new SelectExpressionItem(new Column("*")));
		plainSelect.setSelectItems(newList);
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT  ").append(fields);
		sbSql.append("  FROM (SELECT ROWNUM RN, NOPAGESQL.*   ");
		sbSql.append("          FROM (").append(plainSelect.toString()).append(") NOPAGESQL ");
		sbSql.append("         WHERE ROWNUM <= ? ) entity");
		sbSql.append(" WHERE RN >= ?");
		return sbSql.toString();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// mapperHelper.setProperties(properties);
	}
}