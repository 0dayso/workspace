package org.vetech.core.modules.mybatis.shard.strategy;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.shard.strategy.impl.NoShardStrategy;

public class ShardStrategyFactory {
	private static Map<String,ShardStrategy> shardStrategys;

	private static NoShardStrategy noShardStrategy = new NoShardStrategy();
	public static void setShardStrategys(Map<String, ShardStrategy> shardStrategys) {
		ShardStrategyFactory.shardStrategys = shardStrategys;
	}
	
	public static ShardStrategy getShardStrategy(String tableName){
		if(shardStrategys==null || StringUtils.isBlank(tableName)){
			return noShardStrategy;
		}
		ShardStrategy shardStrategy = shardStrategys.get(tableName);
		if(shardStrategy==null){
			return noShardStrategy;
		}else{
			return shardStrategy;
		}
	}
	
}
