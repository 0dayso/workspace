package org.vetech.core.modules.mybatis.mapper;



import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 通用Mapper接口,其他接口继承该接口即可
 *
 * <p>这是一个例子，自己扩展时可以参考</p>
 *
 * <p>项目地址 : <a href="https://github.com/abel533/Mapper" target="_blank">https://github.com/abel533/Mapper</a></p>
 *
 * @param <T> 不能为空
 * @author 章磊
 */
public interface Mapper<T> extends DynamicSQLMapper{
	
    @SelectProvider(type = MapperProvider.class, method = "dynamicSQL")
    List<T> select(T record);

    @SelectProvider(type = MapperProvider.class, method = "dynamicSQL")
    int selectCount(T record);

    @SelectProvider(type = MapperProvider.class, method = "dynamicSQL")
    T selectByPrimaryKey(Object key);

    @InsertProvider(type = MapperProvider.class, method = "dynamicSQL")
    int insert(T record);

    @InsertProvider(type = MapperProvider.class, method = "dynamicSQL")
    int insertSelective(T record);

    @DeleteProvider(type = MapperProvider.class, method = "dynamicSQL")
    int delete(T record);

    @DeleteProvider(type = MapperProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKey(Object key);

    @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL")
    int updateByPrimaryKey(T record);

    @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeySelective(T record);
 
}
