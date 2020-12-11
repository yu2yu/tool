package com.yy.tool.excel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0
 * @description: 导出字段注解
 * @author: yy
 * @date: 2020/11/25
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 导出列表头名
     */
    String name() default "";

    /**
     * 列
     */
    int index();

    /**
     * 导出格式
     */
    String format() default "yyyy-MM-dd HH:mm:ss";

    /**
     * 针对下拉框和数据库中的枚举值 值得替换 导出是{"男_1","女_0"} 导入反过来,所以只用写一个
     */
    String[] replace() default {};


    /**
     * 导出类型 1 是文本 2 是图片,3是函数,4是数字 默认是文本
     */
    int type() default 1;

    /**
     * 是否是集合
     */
    boolean isCollection() default false;


    /**
     * 是否合并
     */
    boolean needMerge() default false;


}
