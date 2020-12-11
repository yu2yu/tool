package com.yy.tool.excel.util;

import com.yy.tool.excel.ExcelExportUtil;
import com.yy.tool.excel.annotation.ExcelColumn;
import com.yy.tool.excel.entity.ExcelEntity;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/11/25
 */
public class ExcelExportParseUtil {

    /**
     * 解析需要导出的类成注解类
     * @param clazz
     * @return
     */
    public static List<ExcelEntity> parseClass(Class<?> clazz){
        return parseClass(clazz, ExcelColumn.class);
    }

    public static List<ExcelEntity> parseClass(Class<?> clazz,Class<ExcelColumn> annotationClazz){
        List<ExcelEntity> headPojos = parseClassAnnotation(clazz, annotationClazz);
        // 判断是否继承父类
        while(clazz.getSuperclass() != null){
            clazz = clazz.getSuperclass();
            headPojos.addAll(parseClassAnnotation(clazz,annotationClazz));
        }
        return headPojos;
    }


    public static <T> List<ExcelEntity> parseClassAnnotation(Class<T> clazz,
                                                             Class<ExcelColumn> annotationClazz){
        List<ExcelEntity> headPojos = new ArrayList<>();
        //获取属性
        Field[] fields = clazz.getDeclaredFields();
        // TODO：支持jdk1.8以下
        ExcelEntity excelEntity = null;
        for (Field field : fields) {
            //得到注释
            excelEntity = new ExcelEntity();
            field.setAccessible(true);
            ExcelColumn annotation = field.getAnnotation(annotationClazz);
            // 属性赋值
            excelEntity.setField(field);
            excelEntity.setName(annotation.name());
            excelEntity.setIndex(annotation.index());
            excelEntity.setNeedMerge(annotation.needMerge());
            if(annotation.isCollection()){
                ParameterizedType pt = (ParameterizedType) field.getGenericType();
                Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
                List<ExcelEntity> excelSubEntities = parseClass(clz);
                excelEntity.setList(excelSubEntities);
            }
            headPojos.add(excelEntity);
        }
        // 垃圾回收
        excelEntity = null;
        return headPojos;
    }



}
