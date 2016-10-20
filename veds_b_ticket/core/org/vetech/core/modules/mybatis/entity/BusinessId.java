package org.vetech.core.modules.mybatis.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 必须带的条件
 * @author 章磊
 *
 */
public @interface BusinessId {

}
