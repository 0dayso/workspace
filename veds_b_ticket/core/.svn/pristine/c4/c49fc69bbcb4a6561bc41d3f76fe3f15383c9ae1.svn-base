package org.vetech.core.modules.datasource;


import javax.sql.DataSource;

import org.apache.commons.lang3.math.NumberUtils;
/**
 * tomcat动态连接池实现
 * @author 章磊
 *
 */
public class TomcatPoolDynamicDataSource extends DynamicDataSource {

	@Override
	public DataSource _createDataSource(DataSourceConfig dataSourceConfig) {
		org.apache.tomcat.jdbc.pool.DataSource dataSource =new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
		dataSource.setUrl(dataSourceConfig.getUrl());
		dataSource.setUsername(dataSourceConfig.getUsername());
		dataSource.setPassword(dataSourceConfig.getPassword());
		dataSource.setMaxActive(NumberUtils.toInt(dataSourceConfig.getMaxActive()));
		dataSource.setMaxIdle(NumberUtils.toInt(dataSourceConfig.getMaxIdle()));
		dataSource.setMinIdle(NumberUtils.toInt(dataSourceConfig.getMinIdle()));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setValidationQuery("select count(*) from dual");
		return dataSource;
	}

}
