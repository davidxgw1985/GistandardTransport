package com.gistandard.transport.base.operatelog.annotation;

/**
 * @author yujie
 * @ClassName SysServiceLog
 * @Description
 * @Version 1.0
 * @Date 2015-07-28
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysServiceLog {
    String description()  default "";
}
