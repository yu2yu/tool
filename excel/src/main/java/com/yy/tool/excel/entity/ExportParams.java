package com.yy.tool.excel.entity;

import com.yy.tool.excel.constants.ExcelType;
import lombok.Data;

/**
 * @version 1.0
 * @description: 导出参数配置
 * @author: yy
 * @date: 2020/11/25
 */
@Data
public class ExportParams {

    // sheet标题
    private String sheetName;
    // 总标题
    private String title;

    private ExcelType excelType = ExcelType.HSSF;
}
