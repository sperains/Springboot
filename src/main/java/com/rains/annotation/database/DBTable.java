package com.rains.annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Rains on 2016/8/23.
 */
@Target({ElementType.TYPE , ElementType.CONSTRUCTOR} )                           //作用于class
@Retention(RetentionPolicy.RUNTIME)          //注解在运行期也可使用
public @interface DBTable {

    public String name() default "";
}
