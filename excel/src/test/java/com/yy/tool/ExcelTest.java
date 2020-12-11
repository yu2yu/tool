package com.yy.tool;


import com.yy.tool.excel.ExcelExportUtil;
import com.yy.tool.excel.Person;
import com.yy.tool.excel.Student;
import com.yy.tool.excel.annotation.ExcelColumn;
import com.yy.tool.excel.entity.ExcelEntity;
import com.yy.tool.excel.entity.ExportParams;
import com.yy.tool.excel.util.ExcelExportParseUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/11/24 15:55
 */
public class ExcelTest {


    @Test
    public void testCopy(){

        Field[] fields = Student.class.getDeclaredFields();
        // TODO：支持jdk1.8以下
        ExcelEntity excelEntity = new ExcelEntity();
        for (Field field : fields) {
            //得到注释
            field.setAccessible(true);
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);

            excelEntity.setName(annotation.name());
            excelEntity.setIndex(annotation.index());
            System.out.println(excelEntity.toString());
        }

    }

    public static List<Student> getData(){
        ArrayList<Student> objects = new ArrayList<>();
        //
        for (int i = 0; i < 10; i++) {
            ArrayList<Person> people = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Person person = new Person();
                person.setAge(10);
                person.setName("学生"+j);
                people.add(person);
            }
            Student student = new Student("李"+i,i,"男",people,"5");
            objects.add(student);
        }
        return objects;
    }

    @Test
    public void testBaseEntity() throws Exception {

        ExportParams params = new ExportParams();
        params.setSheetName("测试");

        Collection<Student> dataSet = getData();
        Workbook sheets = ExcelExportUtil.exportExcel(params, Student.class, dataSet);
        FileOutputStream out = new FileOutputStream("E:/test2.xls");
        sheets.write(out);
    }
}
