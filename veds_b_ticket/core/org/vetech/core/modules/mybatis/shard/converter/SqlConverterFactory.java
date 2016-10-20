package org.vetech.core.modules.mybatis.shard.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import org.apache.ibatis.mapping.ParameterMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mybatis.shard.exception.ShardException;
import org.vetech.core.modules.mybatis.shard.visitor.SqlStatementVisitorAdapter;

/**
 * SQL解析工程
 * 
 * @author 章磊
 * 
 */
public class SqlConverterFactory {
	private final Logger logger = LoggerFactory.getLogger(SqlConverterFactory.class);
	private static SqlConverterFactory factory;
	static {
		factory = new SqlConverterFactory();
	}

	public static SqlConverterFactory getInstance() {
		return factory;
	}

	private Map<String, SqlConverter> converterMap;

	private SqlConverterFactory() {
		converterMap = new HashMap<String, SqlConverter>();
		register();
	}

	private void register() {
		converterMap.put(Select.class.getName(), new SelectSqlConverter());
		converterMap.put(Insert.class.getName(), new InsertSqlConverter());
		converterMap.put(Update.class.getName(), new UpdateSqlConverter());
		converterMap.put(Delete.class.getName(), new DeleteSqlConverter());
	}

	/**
	 * 修改sql语句
	 * 
	 * @param sql
	 * @param params
	 * @param mapperId
	 * @return 修改后的sql
	 * @throws ShardException
	 *             解析sql失败会抛出ShardException
	 */
	public String convert(String sql, Object params,List<ParameterMapping> parameterMappings) throws ShardException {
		Statement statement = null;
		try {
			statement = CCJSqlParserUtil.parse(sql);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ShardException(e);
		}
		SqlStatementVisitorAdapter sqlStatementVisitorAdapter = new SqlStatementVisitorAdapter(params, parameterMappings);
		statement.accept(sqlStatementVisitorAdapter);
		SqlConverter converter = this.converterMap.get(statement.getClass().getName());

		if (converter != null) {
			return converter.convert(statement, sqlStatementVisitorAdapter.getJdbcParams());
		}
		return sql;
	}
	
}
