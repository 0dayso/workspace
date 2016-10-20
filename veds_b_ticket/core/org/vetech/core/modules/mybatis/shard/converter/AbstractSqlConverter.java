package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mybatis.shard.strategy.ShardStrategy;
import org.vetech.core.modules.mybatis.shard.strategy.ShardStrategyFactory;

/**
 * 将SQL解析并转换成具体的表名实现分表分库
 * 
 * @author 章磊
 * 
 */
public abstract class AbstractSqlConverter implements SqlConverter {
	private final Logger logger = LoggerFactory.getLogger(AbstractSqlConverter.class);
	public String convert(Statement statement, List<Map<String,Object>> jdbcParams) {
		try{
			Statement statement2 = doConvert(statement, jdbcParams);
			return statement2.toString();
		}catch(Exception e){
			logger.error("SQL转换错误",e);
		}
		return statement.toString();
	}

	protected String convertTableName(String tableName,List<Map<String, Object>> jdbcParamlist) {
		ShardStrategy shardStrategy = ShardStrategyFactory.getShardStrategy(tableName);
		return shardStrategy.getTargetTableName(tableName,jdbcParamlist);
	}

	protected abstract Statement doConvert(Statement statement, List<Map<String,Object>> jdbcParams);
}
