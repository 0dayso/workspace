package org.vetech.core.modules.mybatis.shard.converter;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.WithinGroupExpression;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
/**
 * select转换器
 * 目前支持union,子查询，leftJoin,表关联
 * @author 章磊
 *
 */
public class SelectSqlConverter extends AbstractSqlConverter {
	@Override
	protected Statement doConvert(Statement statement, List<Map<String,Object>> jdbcParams) {
		
		TableNameModifier tableNameModifier = new TableNameModifier(jdbcParams);
		((Select) statement).getSelectBody().accept(tableNameModifier);
		return statement;
	}

	public static void main(String[] args) throws JSQLParserException {
		SelectSqlConverter converter = new SelectSqlConverter();
		Select select = (Select) CCJSqlParserUtil
				.parse("select (select deptname from dept where to(id)=?) deptname,name,b.* from student a,member b where to_a(a.id)>(select id from tt where  a=to_date(?,?,a(1))) union all select distinct id,name,b.* from student a,member b where a.name=?");
		select.getSelectBody().accept(converter.getTableNameModifier());
		JdbcParamFinder conditionFinder = new JdbcParamFinder();
		select.getSelectBody().accept(conditionFinder);
	}
	public  TableNameModifier getTableNameModifier(){
//		return new TableNameModifier("1", "1");
		return null;
	}
	public class TableNameModifier implements SelectVisitor, FromItemVisitor, ExpressionVisitor, ItemsListVisitor , SelectItemVisitor{
	    private List<Map<String, Object>> jdbcParamlist;
	    public TableNameModifier(List<Map<String, Object>> jdbcParamlist) {
			this.jdbcParamlist = jdbcParamlist;
		}

		@Override
	    public void visit(WithItem withItem) {
	        withItem.getSelectBody().accept(this);
	    }

	    @Override
	    public void visit(PlainSelect plainSelect) {
	        if (plainSelect.getSelectItems() != null) {
	            for (SelectItem item : plainSelect.getSelectItems()) {
	                item.accept(this);
	            }
	        }

	        plainSelect.getFromItem().accept(this);

	        if (plainSelect.getJoins() != null) {
	            for (Join join : plainSelect.getJoins()) {
	                join.getRightItem().accept(this);
	            }
	        }
	        if (plainSelect.getWhere() != null) {
	            plainSelect.getWhere().accept(this);
	        }
	        if (plainSelect.getOracleHierarchical() != null) {
	            plainSelect.getOracleHierarchical().accept(this);
	        }
	    }

	    @Override
	    public void visit(Table tableName) {
	        String tableWholeName = tableName.getName();
	        tableWholeName = convertTableName(tableWholeName, jdbcParamlist);
	        tableName.setName(tableWholeName);
	    }

	    @Override
	    public void visit(SubSelect subSelect) {
	        subSelect.getSelectBody().accept(this);
	    }

	    @Override
	    public void visit(Addition addition) {
	        visitBinaryExpression(addition);
	    }

	    @Override
	    public void visit(AndExpression andExpression) {
	        visitBinaryExpression(andExpression);
	    }

	    @Override
	    public void visit(Between between) {
	        between.getLeftExpression().accept(this);
	        between.getBetweenExpressionStart().accept(this);
	        between.getBetweenExpressionEnd().accept(this);
	    }

	    @Override
	    public void visit(Column tableColumn) {
	    }

	    @Override
	    public void visit(Division division) {
	        visitBinaryExpression(division);
	    }

	    @Override
	    public void visit(DoubleValue doubleValue) {
	    }

	    @Override
	    public void visit(EqualsTo equalsTo) {
	        visitBinaryExpression(equalsTo);
	    }

	    @Override
	    public void visit(Function function) {
	    }

	    @Override
	    public void visit(GreaterThan greaterThan) {
	        visitBinaryExpression(greaterThan);
	    }

	    @Override
	    public void visit(GreaterThanEquals greaterThanEquals) {
	        visitBinaryExpression(greaterThanEquals);
	    }

	    @Override
	    public void visit(InExpression inExpression) {
	        inExpression.getLeftExpression().accept(this);
	        inExpression.getRightItemsList().accept(this);
	    }

	    @Override
	    public void visit(SignedExpression signedExpression) {
	        signedExpression.getExpression().accept(this);
	    }

	    @Override
	    public void visit(IsNullExpression isNullExpression) {
	    }

	    @Override
	    public void visit(JdbcParameter jdbcParameter) {
	    }

	    @Override
	    public void visit(LikeExpression likeExpression) {
	        visitBinaryExpression(likeExpression);
	    }

	    @Override
	    public void visit(ExistsExpression existsExpression) {
	        existsExpression.getRightExpression().accept(this);
	    }

	    @Override
	    public void visit(LongValue longValue) {
	    }

	    @Override
	    public void visit(MinorThan minorThan) {
	        visitBinaryExpression(minorThan);
	    }

	    @Override
	    public void visit(MinorThanEquals minorThanEquals) {
	        visitBinaryExpression(minorThanEquals);
	    }

	    @Override
	    public void visit(Multiplication multiplication) {
	        visitBinaryExpression(multiplication);
	    }

	    @Override
	    public void visit(NotEqualsTo notEqualsTo) {
	        visitBinaryExpression(notEqualsTo);
	    }

	    @Override
	    public void visit(NullValue nullValue) {
	    }

	    @Override
	    public void visit(OrExpression orExpression) {
	        visitBinaryExpression(orExpression);
	    }

	    @Override
	    public void visit(Parenthesis parenthesis) {
	        parenthesis.getExpression().accept(this);
	    }

	    @Override
	    public void visit(StringValue stringValue) {
	    }

	    @Override
	    public void visit(Subtraction subtraction) {
	        visitBinaryExpression(subtraction);
	    }

	    public void visitBinaryExpression(BinaryExpression binaryExpression) {
	        binaryExpression.getLeftExpression().accept(this);
	        binaryExpression.getRightExpression().accept(this);
	    }

	    @Override
	    public void visit(ExpressionList expressionList) {
	        for (Expression expression : expressionList.getExpressions()) {
	            expression.accept(this);
	        }

	    }

	    @Override
	    public void visit(DateValue dateValue) {
	    }

	    @Override
	    public void visit(TimestampValue timestampValue) {
	    }

	    @Override
	    public void visit(TimeValue timeValue) {
	    }

	    /*
	     * (non-Javadoc)
	     *
	     * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.CaseExpression)
	     */
	    @Override
	    public void visit(CaseExpression caseExpression) {
	    }

	    /*
	     * (non-Javadoc)
	     *
	     * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.WhenClause)
	     */
	    @Override
	    public void visit(WhenClause whenClause) {
	    }

	    @Override
	    public void visit(AllComparisonExpression allComparisonExpression) {
	        allComparisonExpression.getSubSelect().getSelectBody().accept(this);
	    }

	    @Override
	    public void visit(AnyComparisonExpression anyComparisonExpression) {
	        anyComparisonExpression.getSubSelect().getSelectBody().accept(this);
	    }

	    @Override
	    public void visit(SubJoin subjoin) {
	        subjoin.getLeft().accept(this);
	        subjoin.getJoin().getRightItem().accept(this);
	    }

	    @Override
	    public void visit(Concat concat) {
	        visitBinaryExpression(concat);
	    }

	    @Override
	    public void visit(Matches matches) {
	        visitBinaryExpression(matches);
	    }

	    @Override
	    public void visit(BitwiseAnd bitwiseAnd) {
	        visitBinaryExpression(bitwiseAnd);
	    }

	    @Override
	    public void visit(BitwiseOr bitwiseOr) {
	        visitBinaryExpression(bitwiseOr);
	    }

	    @Override
	    public void visit(BitwiseXor bitwiseXor) {
	        visitBinaryExpression(bitwiseXor);
	    }

	    @Override
	    public void visit(CastExpression cast) {
	        cast.getLeftExpression().accept(this);
	    }

	    @Override
	    public void visit(Modulo modulo) {
	        visitBinaryExpression(modulo);
	    }

	    @Override
	    public void visit(AnalyticExpression analytic) {
	    }

	    @Override
	    public void visit(SetOperationList list) {
	        for (PlainSelect plainSelect : list.getPlainSelects()) {
	            visit(plainSelect);
	        }
	    }

	    @Override
	    public void visit(ExtractExpression eexpr) {
	    }

	    @Override
	    public void visit(LateralSubSelect lateralSubSelect) {
	        lateralSubSelect.getSubSelect().getSelectBody().accept(this);
	    }

	    @Override
	    public void visit(MultiExpressionList multiExprList) {
	        for (ExpressionList exprList : multiExprList.getExprList()) {
	            exprList.accept(this);
	        }
	    }

	    @Override
	    public void visit(ValuesList valuesList) {
	    }


	    @Override
	    public void visit(IntervalExpression iexpr) {
	    }

	    @Override
	    public void visit(JdbcNamedParameter jdbcNamedParameter) {
	    }

	    @Override
	    public void visit(OracleHierarchicalExpression oexpr) {
	        if (oexpr.getStartExpression() != null) {
	            oexpr.getStartExpression().accept(this);
	        }

	        if (oexpr.getConnectExpression() != null) {
	            oexpr.getConnectExpression().accept(this);
	        }
	    }

	    @Override
	    public void visit(RegExpMatchOperator rexpr) {
	        visitBinaryExpression(rexpr);
	    }

	    @Override
	    public void visit(RegExpMySQLOperator rexpr) {
	        visitBinaryExpression(rexpr);
	    }

	    @Override
	    public void visit(JsonExpression jsonExpr) {
	    }

	    @Override
	    public void visit(AllColumns allColumns) {
	    }

	    @Override
	    public void visit(AllTableColumns allTableColumns) {
	    }

	    @Override
	    public void visit(SelectExpressionItem item) {
	        item.getExpression().accept(this);
	    }

	    @Override
	    public void visit(WithinGroupExpression wgexpr) {
	    }

	    @Override
	    public void visit(UserVariable var) {
	    }


	}
}

/**
 * 根据select的访问器不停的查找table名称,参考源代码TablesNamesFinder
 * @author 章磊
 *
 */

