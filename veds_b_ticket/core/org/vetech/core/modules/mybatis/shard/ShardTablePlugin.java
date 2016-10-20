package org.vetech.core.modules.mybatis.shard;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mybatis.shard.converter.SqlConverterFactory;
import org.vetech.core.modules.mybatis.shard.exception.ShardException;
import org.vetech.core.modules.mybatis.shard.strategy.ShardStrategy;
import org.vetech.core.modules.mybatis.shard.strategy.ShardStrategyFactory;
import org.vetech.core.modules.mybatis.util.MapperUtil;
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class })})
public class ShardTablePlugin implements Interceptor {
	private final Logger logger = LoggerFactory.getLogger(ShardTablePlugin.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try{
			if (invocation.getTarget() instanceof StatementHandler) {
				StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
				MetaObject metaStatementHandler = MapperUtil.findTargetObject(statementHandler);
				MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
				SqlConverterFactory sqlConverterFactory = SqlConverterFactory.getInstance();
				BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
				Object paramObject = boundSql.getParameterObject();
				String sql = boundSql.getSql();
				List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				String newSql = sqlConverterFactory.convert(sql, paramObject,parameterMappings);
				metaStatementHandler.setValue("delegate.boundSql.sql",newSql);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new ShardException(e);
		}
		return invocation.proceed();
	}
	public void setShardStrategys(Map<String, ShardStrategy> shardStrategys){
		ShardStrategyFactory.setShardStrategys(shardStrategys);
	}
	@Override
	public Object plugin(Object target) {
		 return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
