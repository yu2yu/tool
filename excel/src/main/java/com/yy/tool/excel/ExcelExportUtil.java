package com.yy.tool.excel;

import com.yy.tool.excel.constants.ExcelConstants;
import com.yy.tool.excel.constants.ExcelType;
import com.yy.tool.excel.entity.ExportParams;
import com.yy.tool.excel.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;
import java.util.Objects;

/**
 * @description: excel 导出工具类
 * @author: yy
 * @date: 2020/11/25
 */
public class ExcelExportUtil {

    /**
     *  实体类导出
     *
     * @param params
     *          导出参数配置
     * @param pojoClass
     *          导出需要解析的实体
     * @param dataSet
     *          导出数据
     */
    public static Workbook exportExcel(ExportParams params,Class<?> pojoClass, Collection<?> dataSet){
        Objects.requireNonNull(pojoClass);
        Workbook workbook;
        if (ExcelType.HSSF.equals(params.getExcelType())) {
            workbook = new HSSFWorkbook();
        } else if (dataSet.size() < ExcelConstants.EXCEL_EXPORT_DATA_SIZE) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new SXSSFWorkbook();
        }
        workbook = new ExcelService().createExcel(workbook, params, pojoClass, dataSet);
        return workbook;
    }
}
