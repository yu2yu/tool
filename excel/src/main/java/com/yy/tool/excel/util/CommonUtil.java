package com.yy.tool.excel.util;

import com.yy.tool.excel.entity.ExcelEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/11/25
 */
public class CommonUtil {

    /**
     * 获取类的属性
     * @param clazz
     * @return
     */
    public static List<Field> getClassFields(Class<?> clazz){
        List<Field> fieldList = new ArrayList<>();
        do{
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }while(clazz != Object.class && clazz != null);
        return fieldList;
    }


    /**
     * 获取导出报表的字段总列
     * @param excelEntities
     * @return
     */
    public static int getCols(List<ExcelEntity> excelEntities){
        // TODO：我们需要计算出总共需要占多少列？ list里面套list 递归
        int length = 0;
        for (ExcelEntity excelEntity : excelEntities) {
            //
            if(excelEntity.getList() != null)
                length += getCols(excelEntity.getList());
            else
                length++;
        }
        return length;
    }

    /**
     * 获取导出报表表头的总行数
     * @param excelEntities
     * @return
     */
    public static int getRows(List<ExcelEntity> excelEntities){
        int length = 1;
        for (ExcelEntity excelEntity : excelEntities) {
            if(excelEntity.getList() != null)
                length += getRows(excelEntity.getList());
        }
        return length;
    }

    /**
     * 反射获取值
     * @param excelEntity
     * @param value
     * @return
     */
    public static Object getValueByType(ExcelEntity excelEntity,Object value) throws IllegalAccessException {
        return excelEntity.getField().get(value);
    }

    /**
     * 获取集合的值
     * @param entity
     * @param obj
     * @return
     * @throws Exception
     */
    public static Collection<?> getListValueByReflect(ExcelEntity entity, Object obj) throws Exception {
        Object value = (Collection<?>) entity.getField().get(obj);
        return (Collection<?>) value;
    }
}
