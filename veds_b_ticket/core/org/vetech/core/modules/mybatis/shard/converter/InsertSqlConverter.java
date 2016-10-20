package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 * 转换insert语句
 * 
 * @author 章磊
 * 
 */
public class InsertSqlConverter extends AbstractSqlConverter {

	@Override
	protected Statement doConvert(Statement statement,  List<Map<String,Object>> jdbcParams) {
		if (!(statement instanceof Insert)) {
			throw new IllegalArgumentException("The argument statement must is instance of Insert.");
		}
		Insert insert = (Insert) statement;
		insert.getTable().setName(this.convertTableName(insert.getTable().getName(), jdbcParams));
		return insert;
	}

}
