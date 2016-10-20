package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.Statement;
/**
 * SQL转换器
 * @author 章磊
 *
 */
public interface SqlConverter {
	String convert(Statement statement, List<Map<String,Object>> jdbcParams);
}
