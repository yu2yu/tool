package com.yy.tool;

import com.yy.tool.excel.annotation.ExcelColumn;
import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/9
 */
@Data
public class Person {

    @ExcelColumn(name = "姓名",index=1)
    private String name;
    @ExcelColumn(name = "年龄",index=2)
    private Integer age;
}
