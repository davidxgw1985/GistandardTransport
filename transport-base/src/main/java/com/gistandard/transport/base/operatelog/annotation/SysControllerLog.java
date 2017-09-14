package com.gistandard.transport.base.operatelog.annotation;

import java.lang.annotation.*;

/**
 * @author yujie
 * @ClassName SysControllerLog
 * @Description
 * @Version 1.0
 * @Date 2015-07-28
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysControllerLog {
    String description()  default "";
}
