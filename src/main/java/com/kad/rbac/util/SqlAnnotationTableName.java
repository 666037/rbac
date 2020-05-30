package com.kad.rbac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义tablename注解指定表名
 */
@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.TYPE)
public @interface SqlAnnotationTableName {

    public String value();
}
