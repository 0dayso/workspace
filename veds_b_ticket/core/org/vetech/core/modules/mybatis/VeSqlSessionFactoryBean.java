package org.vetech.core.modules.mybatis;

import java.io.IOException;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.scan.ScanUtil;
/**
 * 重载buildSqlSessionFactory,
 * 将加载mapper.xml时遇到的异常及时打到控制台
 * 
 * @author  win7
 * @version  [版本号, 2016-8-4]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeSqlSessionFactoryBean extends SqlSessionFactoryBean{
	Logger logger = LoggerFactory.getLogger(VeSqlSessionFactoryBean.class);
	private Class<?> typeAliasesSuperType;
	private String typeAliasesPackage;
	
	
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	@Override
	public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
		// TODO Auto-generated method stub
		this.typeAliasesSuperType = typeAliasesSuperType;
	}

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		long ks = System.currentTimeMillis();
		try {
			/**
			 * mybatis不是精确扫描得到entity
			 * 下面是自己实现的一个精确扫描
			 * typeAliasesSuperType和typeAliasesPackage覆盖了父类属性
			 */
			Set<Class> set = ScanUtil.scan(this.typeAliasesPackage, this.typeAliasesSuperType);
			logger.info("mybatis扫描entity耗时:"+(System.currentTimeMillis()-ks));
			super.setTypeAliases(set.toArray(new Class[]{}));
			// TODO Auto-generated method stub
			return super.buildSqlSessionFactory();
		} catch (Throwable e) {
			logger.error("mybatis加载错误，很严重",e);//将加载mapper.xml时遇到的异常及时打到控制台,否则会到服务器启动超时才打出错误
			throw new IOException(e);
		}finally{
			logger.info("mybatis启动耗时:"+(System.currentTimeMillis()-ks));
		}
		
	}
	
}
