package com.gistandard.transport.base.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * 标识数据源的MyBatis的DAO,为 {@link org.mybatis.spring.mapper.MapperScannerConfigurer}扫描指定注解类。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repository
public @interface MyBatisRepository {
    String value() default "";
}