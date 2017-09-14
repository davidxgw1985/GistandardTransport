package com.gistandard.transport.tools.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 布尔类型的分支判断
 * 
 * @author ShengHao
 * 
 *         2016-5-31 下午1:37:06
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BooleanObjectFieldNotNull {

}
