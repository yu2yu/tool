### EXCEL导出工具
项目是通用的Excel导出框架
> 是由于公司项目早期堆积着大量重复的excel导入导出数据，并且脱离java对象的设计原则，导致可维护性以及可操作性大大降低

### 用法

#### 导出

##### 简单对象
```java
public class Student {

    @ExcelColumn(name = "姓名",index=1,needMerge = true)
    private String name;
    @ExcelColumn(name = "年龄",index=2)
    private Integer age;
    @ExcelColumn(name = "性别",index=3)
    private String sex;
}

ExportParams params = new ExportParams();
params.setSheetName("sheet标题");
Collection<Student> dataSet = getData();
Workbook sheets = ExcelExportUtil.exportExcel(params, Student.class, dataSet);
FileOutputStream out = new FileOutputStream("文件位置");
sheets.write(out);
```

目前只支持导出两层复杂表头