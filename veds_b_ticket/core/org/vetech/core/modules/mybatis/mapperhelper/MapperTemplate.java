package org.vetech.core.modules.mybatis.mapperhelper;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.session.Configuration;
import org.vetech.core.modules.mybatis.util.MapperUtil;

/**
 * 通用Mapper模板类，扩展通用Mapper时需要继承该类
 *
 * @author 章磊
 */
public abstract class MapperTemplate {
    private Map<String, Method> methodMap = new HashMap<String, Method>();
    private Class<?> mapperClass;
    private MapperHelper mapperHelper;

    public MapperTemplate(Class<?> mapperClass, MapperHelper mapperHelper) {
        this.mapperClass = mapperClass;
        this.mapperHelper = mapperHelper;
    }

    public String dynamicSQL(Object record) {
        return "dynamicSQL";
    }

    /**
     * 添加映射方法
     *
     * @param methodName
     * @param method
     */
    public void addMethodMap(String methodName, Method method) {
        methodMap.put(methodName, method);
    }

    public String getUUID() {
        return mapperHelper.getUUID();
    }

    public String getIDENTITY() {
        return mapperHelper.getIDENTITY();
    }

    public boolean getBEFORE() {
        return mapperHelper.getBEFORE();
    }

   

   

    /**
     * 是否支持该通用方法
     *
     * @param msId
     * @return
     */
    public boolean supportMethod(String msId) {
        Class<?> mapperClass = getMapperClass(msId);
        if (this.mapperClass.isAssignableFrom(mapperClass)) {
            String methodName = getMethodName(msId);
            return methodMap.get(methodName) != null;
        }
        return false;
    }

    /**
     * 设置返回值类型
     *
     * @param ms
     * @param entityClass
     */
    protected void setResultType(MappedStatement ms, Class<?> entityClass) {
        ResultMap resultMap = ms.getResultMaps().get(0);
        MetaObject metaObject = MapperUtil.forObject(resultMap);
        metaObject.setValue("type", entityClass);
    }

    /**
     * 重新设置SqlSource
     *
     * @param ms
     * @param sqlSource
     */
    protected void setSqlSource(MappedStatement ms, SqlSource sqlSource) {
        MetaObject msObject = MapperUtil.forObject(ms);
        msObject.setValue("sqlSource", sqlSource);
    }

    /**
     * 重新设置SqlSource
     *
     * @param ms
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public void setSqlSource(MappedStatement ms) throws Exception {
        if (this.mapperClass == getMapperClass(ms.getId())) {
            if (mapperHelper.isSpring4()) {
                return;
            } else if (mapperHelper.isSpring()) {
                throw new RuntimeException("Spring4.x.x 及以上版本支持泛型注入," +
                        "您当前的Spring版本为" + mapperHelper.getSpringVersion() + ",不能使用泛型注入," +
                        "因此在配置MapperScannerConfigurer时,不要扫描通用Mapper接口类," +
                        "也不要在您Mybatis的xml配置文件中的<mappers>中指定通用Mapper接口类.");
            } else {
                throw new RuntimeException("请不要在您Mybatis的xml配置文件中的<mappers>中指定通用Mapper接口类.");
            }
        }
        Method method = methodMap.get(getMethodName(ms));
        try {
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(this, ms);
            } else if (SqlNode.class.isAssignableFrom(method.getReturnType())) {
                SqlNode sqlNode = (SqlNode) method.invoke(this, ms);
                DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(ms.getConfiguration(), sqlNode);
                setSqlSource(ms, dynamicSqlSource);
            } else {
                throw new RuntimeException("自定义Mapper方法返回类型错误,可选的返回类型为void和SqlNode!");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException() != null ? e.getTargetException() : e);
        }
    }

    /**
     * 获取返回值类型 - 实体类型
     *
     * @param ms
     * @return
     */
    public Class<?> getSelectReturnType(MappedStatement ms) {
        String msId = ms.getId();
        Class<?> mapperClass = getMapperClass(msId);
        Type[] types = mapperClass.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType t = (ParameterizedType) type;
                if (t.getRawType() == this.mapperClass) {
                    Class<?> returnType = (Class<?>) t.getActualTypeArguments()[0];
                    return returnType;
                }
            }
        }
        throw new RuntimeException("无法获取Mapper<T>泛型类型:" + msId);
    }

    /**
     * 根据msId获取接口类
     *
     * @param msId
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getMapperClass(String msId) {
        String mapperClassStr = msId.substring(0, msId.lastIndexOf("."));
        try {
            return Class.forName(mapperClassStr);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("无法获取Mapper接口信息:" + msId);
        }
    }

    /**
     * 获取执行的方法名
     *
     * @param ms
     * @return
     */
    public static String getMethodName(MappedStatement ms) {
        return getMethodName(ms.getId());
    }

    /**
     * 获取执行的方法名
     *
     * @param msId
     * @return
     */
    public static String getMethodName(String msId) {
        return msId.substring(msId.lastIndexOf(".") + 1);
    }

    /**
     * 根据对象生成主键映射
     *
     * @param ms
     * @return
     */
    protected List<ParameterMapping> getPrimaryKeyParameterMappings(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();
        for (EntityHelper.EntityColumn column : entityColumns) {
            ParameterMapping.Builder builder = new ParameterMapping.Builder(ms.getConfiguration(), column.getProperty(), column.getJavaType());
            builder.mode(ParameterMode.IN);
            parameterMappings.add(builder.build());
        }
        return parameterMappings;
    }

    /**
     * 获取序列下个值的表达式
     *
     * @param column
     * @return
     */
    protected String getSeqNextVal(EntityHelper.EntityColumn column) {
        return MessageFormat.format(mapperHelper.getSeqFormat(), column.getSequenceName(), column.getColumn(), column.getProperty());
    }

    /**
     * 获取实体类的表名
     *
     * @param entityClass
     * @return
     */
    protected String tableName(Class<?> entityClass) {
        return mapperHelper.getTableName(entityClass);
    }

    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property!=null"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @param columnNode
     * @return
     */
    protected SqlNode getIfNotNull(EntityHelper.EntityColumn column, SqlNode columnNode) {
        return getIfNotNull(column, columnNode, false);
    }

    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property!=null"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @param columnNode
     * @param empty      是否包含!=''条件
     * @return
     */
    protected SqlNode getIfNotNull(EntityHelper.EntityColumn column, SqlNode columnNode, boolean empty) {
        return new IfSqlNode(columnNode, column.getProperty() + " != null " + (empty ? " and " + column.getProperty() + " != ''" : ""));
    }

    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property==null"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @return
     */
    protected SqlNode getIfIsNull(EntityHelper.EntityColumn column, SqlNode columnNode) {
        return new IfSqlNode(columnNode, column.getProperty() + " == null ");
    }
    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property==null or property==''"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @return
     */
    protected SqlNode getIfIsEmpty(EntityHelper.EntityColumn column, SqlNode columnNode) {
        return new IfSqlNode(columnNode, column.getProperty() + " == null  or "+column.getProperty() +" == ''");
    }
    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property!=null"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @return
     */
    protected SqlNode getIfCacheNotNull(EntityHelper.EntityColumn column, SqlNode columnNode) {
        return new IfSqlNode(columnNode, column.getProperty() + "_cache != null ");
    }

    /**
     * 返回if条件的sqlNode
     * <p>一般类型：<code>&lt;if test="property_cache!=null"&gt;columnNode&lt;/if&gt;</code></p>
     *
     * @param column
     * @return
     */
    protected SqlNode getIfCacheIsNull(EntityHelper.EntityColumn column, SqlNode columnNode) {
        return new IfSqlNode(columnNode, column.getProperty() + "_cache == null ");
    }

    /**
     * 获取 <code>[AND] column = #{property}</code>
     *
     * @param column
     * @param first
     * @return
     */
    protected SqlNode getColumnEqualsProperty(EntityHelper.EntityColumn column, boolean first) {
        return new StaticTextSqlNode((first ? "" : " AND ") + column.getColumn() + " = #{" + column.getProperty() + "} ");
    }

    /**
     * 获取所有列的where节点中的if判断列
     *
     * @param entityClass
     * @return
     */
    protected SqlNode getAllIfColumnNode(Class<?> entityClass) {
        //获取全部列
        Set<EntityHelper.EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        List<SqlNode> ifNodes = new ArrayList<SqlNode>();
        boolean first = true;
        //对所有列循环，生成<if test="property!=null">column = #{property}</if>
        for (EntityHelper.EntityColumn column : columnList) {
            ifNodes.add(getIfNotNull(column, getColumnEqualsProperty(column, first), true));
            first = false;
        }
        return new MixedSqlNode(ifNodes);
    }

    /**
     * 根据对象生成所有列的映射
     *
     * @param ms
     * @return
     */
    protected List<ParameterMapping> getColumnParameterMappings(MappedStatement ms) {
    	return getColumnParameterMappings(ms,false);
    }
    /**
     * 根据对象生成所有列的映射
     * 可以返回不需要ID的参数映射，主要解决update set 中有ID的情况
     * @param ms
     * @return
     */
    protected List<ParameterMapping> getColumnParameterMappings(MappedStatement ms,boolean noId) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getColumns(entityClass);
        List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();
        for (EntityHelper.EntityColumn column : entityColumns) {
        	if(noId){
        		if(column.isId()){
        			continue;
        		}
        	}
            ParameterMapping.Builder builder = new ParameterMapping.Builder(ms.getConfiguration(), column.getProperty(), column.getJavaType());
            builder.mode(ParameterMode.IN);
            parameterMappings.add(builder.build());
        }
        return parameterMappings;
    }

    /**
     * 新建SelectKey节点 - 只对mysql的自动增长有效，Oracle序列直接写到列中
     *
     * @param ms
     * @param column
     */
    protected void newSelectKeyMappedStatement(MappedStatement ms, EntityHelper.EntityColumn column) {
        String keyId = ms.getId() + SelectKeyGenerator.SELECT_KEY_SUFFIX;
        if (ms.getConfiguration().hasKeyGenerator(keyId)) {
            return;
        }
        Class<?> entityClass = getSelectReturnType(ms);
        //defaults
        Configuration configuration = ms.getConfiguration();
        KeyGenerator keyGenerator = null;
        Boolean executeBefore = getBEFORE();
        String IDENTITY = (column.getGenerator() == null || column.getGenerator().equals("")) ? getIDENTITY() : column.getGenerator();
        if (IDENTITY.equalsIgnoreCase("JDBC")) {
            keyGenerator = new Jdbc3KeyGenerator();
        } else {
            SqlSource sqlSource = new RawSqlSource(configuration, IDENTITY, entityClass);

            MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, keyId, sqlSource, SqlCommandType.SELECT);
            statementBuilder.resource(ms.getResource());
            statementBuilder.fetchSize(null);
            statementBuilder.statementType(StatementType.STATEMENT);
            statementBuilder.keyGenerator(new NoKeyGenerator());
            statementBuilder.keyProperty(column.getProperty());
            statementBuilder.keyColumn(null);
            statementBuilder.databaseId(null);
            statementBuilder.lang(configuration.getDefaultScriptingLanuageInstance());
            statementBuilder.resultOrdered(false);
            statementBuilder.resulSets(null);
            statementBuilder.timeout(configuration.getDefaultStatementTimeout());

            List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();
            ParameterMap.Builder inlineParameterMapBuilder = new ParameterMap.Builder(
                    configuration,
                    statementBuilder.id() + "-Inline",
                    entityClass,
                    parameterMappings);
            statementBuilder.parameterMap(inlineParameterMapBuilder.build());

            List<ResultMap> resultMaps = new ArrayList<ResultMap>();
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
                    configuration,
                    statementBuilder.id() + "-Inline",
                    column.getJavaType(),
                    new ArrayList<ResultMapping>(),
                    null);
            resultMaps.add(inlineResultMapBuilder.build());
            statementBuilder.resultMaps(resultMaps);
            statementBuilder.resultSetType(null);

            statementBuilder.flushCacheRequired(false);
            statementBuilder.useCache(false);
            statementBuilder.cache(null);

            MappedStatement statement = statementBuilder.build();
            configuration.addMappedStatement(statement);

            MappedStatement keyStatement = configuration.getMappedStatement(keyId, false);
            keyGenerator = new SelectKeyGenerator(keyStatement, executeBefore);
            configuration.addKeyGenerator(keyId, keyGenerator);
        }
        //keyGenerator
        try {
            MetaObject msObject = MapperUtil.forObject(ms);
            msObject.setValue("keyGenerator", keyGenerator);
            msObject.setValue("keyProperties", new String[]{column.getProperty()});
        } catch (Exception e) {
            //ignore
        }
    }
}