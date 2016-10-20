package org.vetech.core.modules.mybatis.mapper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.vetech.core.modules.mybatis.mapperhelper.EntityHelper;

/**
 * 动态SQL
 * @author 章磊
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DynamicSQLProvider {
	private boolean isBlank(SearchFilter searchFilter){
		Object value = searchFilter.value;
		if(value==null){
    		return true;
    	}else if(value instanceof String){
    		if(StringUtils.isBlank((String)value)){
    			return true;
    		}
    	}
		return false;
	}
	private void whereSql(SQL sql,SearchFilter searchFilter){
        switch (searchFilter.operator) {
		case EQ:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " = #{inParam." + searchFilter.searchName + "}");
			break;
		case NOT:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " <> #{inParam." + searchFilter.searchName + "}");
			break;
		case NOTNULL:
			sql.WHERE(searchFilter.fieldName+" is not null");
			break;
		case ISNULL:
			sql.WHERE(searchFilter.fieldName+" is null");
			break;
		case LIKE:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " like '%'||#{inParam." + searchFilter.searchName + "}||'%'");
			break;
		case GT:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " > #{inParam." + searchFilter.searchName + "}");
			break;
		case LT:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " < #{inParam." + searchFilter.searchName + "}");
			break;
		case GTE:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " >= #{inParam." + searchFilter.searchName + "}");
			break;
		case LTE:
			if(isBlank(searchFilter)){
				return;
			}
			sql.WHERE(searchFilter.fieldName + " <= #{inParam." + searchFilter.searchName + "}");
			break;
		}
	}
	/**
	 * inParam必须有cls和inParam
	 * cls是实体类型
	 * inParam是参数，参数的key例如search_EQ_字段
	 * @param param
	 * @return
	 */
	public String selectDynamicSQL( final Map param){
		return new SQL() {{
			Class cls = (Class)param.get("cls");
			Map  inParam = (Map)param.get("inParam");
			Map<String, SearchFilter> filters = SearchFilter.parse(inParam);
			param.put("inParam", SearchFilter.type(inParam));
            EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(cls);
            SELECT(EntityHelper.getSelectColumns(cls));
            FROM(entityTable.getName());
            if (inParam != null) {
            	for(SearchFilter searchFilter : filters.values()){
                	 whereSql(this,searchFilter);
            	}
            	String orderBy = (String)inParam.get("orderBy");
            	if (StringUtils.isNotBlank(orderBy)) {
            		ORDER_BY(orderBy);
            	}
            }
        }}.toString();
	}
	public String countDynamicSQL(final Map param){
		return new SQL() {{
			Class cls = (Class)param.get("cls");
			Map  inParam = (Map)param.get("inParam");
			Map<String, SearchFilter> filters = SearchFilter.parse(inParam);
			param.put("inParam", SearchFilter.type(inParam));
            EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(cls);
            SELECT("count(1)");
            FROM(entityTable.getName()+" entity");
            if (inParam != null) {
            	for(SearchFilter searchFilter : filters.values()){
                	 whereSql(this,searchFilter);
            	}
            }
        }}.toString();
	}
}
