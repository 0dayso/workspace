package org.vetech.core.modules.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源 应用数据库的数据库配置统一在datasource_config中维护 可实现动态数据源的添加和删除
 * 
 * @author 章磊
 * 
 */
public abstract class DynamicDataSource extends AbstractRoutingDataSource {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	/**
	 * 已经注册的数据源
	 */
	private Map<Object, Object> _targetDataSources = new HashMap<Object, Object>();
	private DataSource defaultDataSource;

	private String dataSources;

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}

	public void setDefaultDataSource(DataSource defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}

	/**
	 * 初始化时先把要用到的数据源全部加载好
	 */
	public void init() {
		if (StringUtils.isNotBlank(dataSources)) {
			String[] datas = dataSources.split(",");
			for (int j = 0; j < datas.length; j++) {
				selectDataSource(datas[j]);
			}
		}
		_targetDataSources.put("defaultDataSource", defaultDataSource);
		super.setTargetDataSources(_targetDataSources);
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String id = DataSourceHold.get();
		DataSource _dataSource = null;
		if (StringUtils.isBlank(id)) {
			return "defaultDataSource";
		}
		_dataSource = selectDataSource(id);
		if (_dataSource == null) {// 如果已经注册了直接返回
			_dataSource = (DataSource) _targetDataSources.get("defaultDataSource");
			return "defaultDataSource";
		}
		DataSourceHold.clear();
		return id;
	}

	public DataSource selectDataSource(String databaseid) {
		DataSource _dataSource = (DataSource) _targetDataSources.get(databaseid);
		if (_dataSource != null) {
			return _dataSource;
		}
		// 如果没有重新创建数据源
		_dataSource = createDataSource(databaseid);
		if (_dataSource == null) {
			return null;
		}

		_targetDataSources.put(databaseid, _dataSource);
		super.setTargetDataSources(_targetDataSources);
		super.afterPropertiesSet();
		return _dataSource;
	}

	public DataSource createDataSource(String databaseid) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		try {
			conn = defaultDataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM datasource_config WHERE APPNAME = ?");
			ps.setString(1, databaseid);
			rs = ps.executeQuery();
			if (rs.next()) {
				dataSourceConfig.setId(rs.getString("ID"));
				dataSourceConfig.setDriverClassName(rs.getString("DRIVERCLASSNAME"));
				dataSourceConfig.setUrl(rs.getString("URL"));
				dataSourceConfig.setUsername(rs.getString("USERNAME"));
				dataSourceConfig.setPassword(rs.getString("PASSWORD"));
				dataSourceConfig.setMaxActive(rs.getString("MAXACTIVE"));
				dataSourceConfig.setMaxIdle(rs.getString("MAXIDLE"));
				dataSourceConfig.setMinIdle(rs.getString("MINIDLE"));
				dataSourceConfig.setAppname(rs.getString("APPNAME"));
				dataSourceConfig.setIssharding(rs.getString("ISSHARDING"));
			}
		} catch (Exception e) {
			logger.error("动态数据源连接异常datasource_config", e);
		} finally {
			try {
				if (conn != null) {
					conn.commit();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("动态数据源连接异常datasource_config", e1);
			}
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("动态数据源连接异常datasource_config", e);
			}
		}
		if (null != dataSourceConfig && dataSourceConfig.getId() != null) {
			DataSource newdataSource = this._createDataSource(dataSourceConfig);
			return newdataSource;
		}
		return null;
	}

	public abstract DataSource _createDataSource(DataSourceConfig dataSourceConfig);
}
