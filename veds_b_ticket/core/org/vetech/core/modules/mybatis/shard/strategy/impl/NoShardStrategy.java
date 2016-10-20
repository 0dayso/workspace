package org.vetech.core.modules.mybatis.shard.strategy.impl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.shard.strategy.ShardStrategy;

public class NoShardStrategy implements ShardStrategy {
	@Override
	public String getTargetTableName(String baseTableName,  List<Map<String, Object>> conditionlist) {
		return baseTableName;
	}


}
