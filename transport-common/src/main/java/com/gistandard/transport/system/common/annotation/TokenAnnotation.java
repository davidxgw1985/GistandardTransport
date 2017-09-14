package com.gistandard.transport.system.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yujie
 * @ClassName Token
 * @Description
 * @Version 1.0
 * @Date 2015-07-30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAnnotation {

    boolean save() default false;

    boolean remove() default false;
}