package org.vetech.core.modules.web.ajax;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
/**
 * 扩展spring的controller,页面请求json转对象的类
 * 当页面json属性少于类的属性时，原来的会报错，这个可以解决这个问题
 * this.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
 * @author 章磊
 *
 */
public class MappingJackson2HttpMessageConverterEx extends MappingJackson2HttpMessageConverter {
	public MappingJackson2HttpMessageConverterEx(){
		super();
		this.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	}
}
