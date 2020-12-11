package com.yy.tool.excel.entity;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @version 1.0
 * @description: Excel 导入导出基础对象类
 * @author: yy
 * @date: 2020/11/25
 */

@Data
public class ExcelBaseEntity {

    /**
     * 对应name
     */
    String name;

    /**
     * 对应 field
     */
    Field field;


}
