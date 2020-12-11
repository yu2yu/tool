package com.yy.tool.excel.constants;

/**
 * @version 1.0
 * @description: excel类型
 *
 * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls；局限就是导出的行数至多为65535行
 * XSSFWorkbook:是操作Excel2007后的版本，扩展名是.xlsx；最多可以导出104万行，不过这样就伴随着一个问题---OOM内存溢出
 * SXSSFWorkbook:是操作Excel2007后的版本，扩展名是.xlsx；用硬盘空间换内存
 *
 * @author: yy
 * @date: 2020/11/25
 */
public enum ExcelType {

    HSSF,
    XSSF,
    SXSS;
}
