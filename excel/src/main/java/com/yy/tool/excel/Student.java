package com.yy.tool.excel;

import com.yy.tool.excel.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/11/24 15:40
 */
@Data
@AllArgsConstructor
public class Student {

    @ExcelColumn(name = "姓名",index=1,needMerge = true)
    private String name;
    @ExcelColumn(name = "年龄",index=2)
    private Integer age;
    @ExcelColumn(name = "性别",index=3)
    private String sex;

    @ExcelColumn(name = "老师",index=4,isCollection=true)
    private List<Person> personList;

    @ExcelColumn(name = "年级",index=5)
    private String grade;

}
