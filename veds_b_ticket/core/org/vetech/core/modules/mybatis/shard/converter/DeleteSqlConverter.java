package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

/**
 * delete转换器
 * 
 * @author 章磊
 * 
 */
public class DeleteSqlConverter extends AbstractSqlConverter {

	@Override
	protected Statement doConvert(Statement statement,  List<Map<String,Object>> jdbcParams) {
		if (!(statement instanceof Delete)) {
			throw new IllegalArgumentException("The argument statement must is instance of Delete.");
		}
		Delete delete = (Delete) statement;
		String name = delete.getTable().getName();
		delete.getTable().setName(this.convertTableName(name, jdbcParams));
		return delete;
	}
	
	public static void main(String []args) throws JSQLParserException{
		Delete delete = (Delete) CCJSqlParserUtil
				.parse("delete from aa where id=(select id from t where t.id=?) and a=to_date(?,?)");
		JdbcParamFinder statementVisitor = new JdbcParamFinder();
		delete.getWhere().accept(statementVisitor);
	}

}
