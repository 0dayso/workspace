package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
/**
 * update转换器
 * @author 章磊
 *
 */
public class UpdateSqlConverter extends AbstractSqlConverter {

	public static void main(String []args) throws JSQLParserException{
		Update update = (Update)CCJSqlParserUtil.parse("update student set a=1 where id=1");
	}
	@Override
	protected Statement doConvert(Statement statement, List<Map<String,Object>> jdbcParams) {
		if (!(statement instanceof Update)) {
			throw new IllegalArgumentException("The argument statement must is instance of Update.");
		}
		Update update = (Update) statement;
		String name = update.getTables().get(0).getName();
		update.getTables().get(0).setName(this.convertTableName(name, jdbcParams));
		return update;
	}
}
