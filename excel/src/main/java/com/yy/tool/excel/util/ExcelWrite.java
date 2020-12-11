package com.yy.tool.excel.util;

import com.yy.tool.excel.constants.ExcelType;
import com.yy.tool.excel.entity.ExcelEntity;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/9
 */
public class ExcelWrite {

    /**
     * 创建文本类型的Cell
     *
     * @param row
     * @param index
     * @param text
     * @param style
     * @param entity
     */
    public static void createStringCell(Row row, int index, String text, CellStyle style, ExcelEntity entity) {
        Cell cell = row.createCell(index);
        cell.setCellValue(text);
        if (style != null) {
            cell.setCellStyle(style);
        }
    }


    /**
     * 创建文本类型的Cell
     *
     * @param row
     * @param column
     * @param column
     * @param style
     * @param entity
     */
    public static int createListString(Row row, int column, Object text, CellStyle style, List<ExcelEntity> entity) {

        Cell cell = row.createCell(column);
        cell.setCellValue(text.toString());
        if (style != null) {
            cell.setCellStyle(style);
        }
        return 0;
    }
}
