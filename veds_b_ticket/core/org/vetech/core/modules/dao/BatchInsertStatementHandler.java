package org.vetech.core.modules.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.vetech.core.modules.utils.Reflections;

/**
 * 拦截参数是 {@link net.coderbee.mybatis.parameter.BatchInsertParameter} 的实例的语句执行，由于批量插入在参数绑定的时候已经执行了，这里不需要再次执行。
 * 
 * @author <a href="http://coderbee.net">coderbee</a>
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }) })
public class BatchInsertStatementHandler implements Interceptor {

	// @Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();

			StatementHandler delegate = (StatementHandler) Reflections.getFieldValue(routingStatementHandler, "delegate");
			if (delegate instanceof PreparedStatementHandler) {

				PreparedStatementHandler preparedStatementHandler = (PreparedStatementHandler) delegate;
				MappedStatement mappedStatement = (MappedStatement) Reflections.getFieldValue(preparedStatementHandler, "mappedStatement");

				BoundSql boundSql = (BoundSql) Reflections.getFieldValue(preparedStatementHandler, "boundSql");

				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject instanceof BatchInsertParameter) {
					PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];
					int rows = ps.getUpdateCount();
					Executor executor = (Executor) Reflections.getFieldValue(preparedStatementHandler, "executor");

					KeyGenerator keyGenerator = mappedStatement.getKeyGenerator();

					keyGenerator.processAfter(executor, mappedStatement, ps, parameterObject);
					return rows;
				}
			}
		}

		return invocation.proceed();
	}

	// @Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	// @Override
	public void setProperties(Properties properties) {

	}

}
