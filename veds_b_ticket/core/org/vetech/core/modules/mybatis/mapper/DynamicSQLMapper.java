package org.vetech.core.modules.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
@SuppressWarnings({"rawtypes" })
public interface DynamicSQLMapper {
	   
    @SelectProvider(type = DynamicSQLProvider.class, method = "selectDynamicSQL")
    List<Map> selectDynamicSQL(@Param("cls")Class cls, @Param("inParam")Map inParam);
    
    @SelectProvider(type = DynamicSQLProvider.class, method = "countDynamicSQL")
    int countDynamicSQL(@Param("cls")Class cls, @Param("inParam")Map inParam);
}
