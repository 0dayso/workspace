package org.vetech.core.modules.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
/**
 * 数据源选择拦截器
 * 专门针对多动态维护数据源实现事务多数据源选择问题
 * 实现原理是拦截transation并根据方法和类上什么的DataSource注解获得数据源
 * @author 章磊
 *
 */
@Aspect
@Order(1)
public class DataSourceAspect {
	@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
	public void methodsToBeProfiled(){}

	@Before("methodsToBeProfiled()")
	public void dataSource(JoinPoint point) throws Throwable {
		Object target = point.getTarget();
		Class targetcls = target.getClass();
		MethodSignature  methodSignature =(MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		if(method.isAnnotationPresent(DataSource.class)){
			DataSource dataSource = (DataSource)method.getAnnotation(DataSource.class);
			DataSourceHold.set(dataSource.name());
		}else if(targetcls.isAnnotationPresent(DataSource.class)){
			DataSource dataSource = (DataSource)targetcls.getAnnotation(DataSource.class);
			DataSourceHold.set(dataSource.name());
		}
		
	}
}
