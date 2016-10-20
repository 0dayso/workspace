package org.vetech.core.modules.mybatis.shard;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.datasource.DataSource;
import org.vetech.core.modules.datasource.DataSourceHold;
import org.vetech.core.modules.mybatis.util.MapperUtil;

@Intercepts({
    @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
    @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
            RowBounds.class, ResultHandler.class }) })
public class ShardDBPlugin implements Interceptor{
	private final Logger logger = LoggerFactory.getLogger(ShardDBPlugin.class);
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
        if (!(args[0] instanceof MappedStatement)) {
            return invocation.proceed();
        }
        try{
	        MappedStatement mappedStatement = (MappedStatement)args[0];
	        Class class1 = MapperUtil.getMapperClass(mappedStatement.getId());
	        if(class1.isAnnotationPresent(DataSource.class)){
	        	DataSource dataSource = (DataSource)class1.getAnnotation(DataSource.class);
	        	DataSourceHold.set(dataSource.name());
	//	        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
	//	        String sql = boundSql.getSql();
	//	        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
	//	        Statement statement = null;
	//			try {
	//				statement = CCJSqlParserUtil.parse(sql);
	//				SqlStatementVisitorAdapter sqlStatementVisitorAdapter = new SqlStatementVisitorAdapter(args[1], parameterMappings);
	//				statement.accept(sqlStatementVisitorAdapter);
	//			} catch (Exception e) {
	//				logger.error(e.getMessage(), e);
	//				throw new ShardException(e);
	//			}
	        }
        }catch(Exception e){
        	logger.error("动态数据源ShardDBPlugin",e);
        }
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		 return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
