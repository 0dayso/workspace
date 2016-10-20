package org.vetech.core.modules.datasource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源 应用数据库的数据库配置统一在datasource_config中维护 可实现动态数据源的添加和删除
 * 
 * @author 章磊
 * 
 */
public class DynamicDataSource2 extends AbstractRoutingDataSource {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSource2.class);

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		logger.error("设置多数据源" + targetDataSources.keySet());
		super.setTargetDataSources(targetDataSources);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceName = DataSourceHold.get();
		logger.debug("取得的数据源是：" + dataSourceName);
		return dataSourceName;
	}
}
