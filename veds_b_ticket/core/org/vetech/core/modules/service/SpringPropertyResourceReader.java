package org.vetech.core.modules.service;

import java.lang.reflect.Method;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

/**
 * 程序代码加在spring的配置文件
 * 
 * 需要配置<bean id="springPropertyResourceReader"  class="org.vetech.core.modules.service.SpringPropertyResourceReader" init-method="init"/>
 * 
 * @author zhanglei
 *
 */
@Component
public class SpringPropertyResourceReader {
	@Autowired
	private ApplicationContext applicationContext;
	private static Properties properties = new Properties();

	public void init() {
		try {
			// get the names of BeanFactoryPostProcessor
			String[] postProcessorNames = applicationContext.getBeanNamesForType(BeanFactoryPostProcessor.class, true, true);

			for (String ppName : postProcessorNames) {
				// get the specified BeanFactoryPostProcessor
				BeanFactoryPostProcessor beanProcessor = applicationContext.getBean(ppName, BeanFactoryPostProcessor.class);
				// check whether the beanFactoryPostProcessor is
				// instance of the PropertyResourceConfigurer
				// if it is yes then do the process otherwise continue
				if (beanProcessor instanceof PropertyResourceConfigurer) {
					PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer) beanProcessor;

					// get the method mergeProperties
					// in class PropertiesLoaderSupport
					Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
					// get the props
					mergeProperties.setAccessible(true);
					Properties props = (Properties) mergeProperties.invoke(propertyResourceConfigurer);

					// get the method convertProperties
					// in class PropertyResourceConfigurer
					Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties", Properties.class);
					// convert properties
					convertProperties.setAccessible(true);
					convertProperties.invoke(propertyResourceConfigurer, props);

					properties.putAll(props);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

}
