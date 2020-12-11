package com.yy.tool.excel.service;

import com.yy.tool.excel.constants.ExcelType;
import com.yy.tool.excel.entity.ExcelEntity;
import com.yy.tool.excel.entity.ExportParams;
import com.yy.tool.excel.exception.Asserts;
import com.yy.tool.excel.exception.ExcelExportException;
import com.yy.tool.excel.exception.ExcelExportExceptionEnum;
import com.yy.tool.excel.util.CommonUtil;
import com.yy.tool.excel.util.ExcelExportParseUtil;
import com.yy.tool.excel.util.ExcelWrite;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @version 1.0
 * @description: 导出实现类
 * @author: yy
 * @date: 2020/11/25
 */
@Slf4j
public class ExcelService {


    public Workbook createExcel(Workbook workbook, ExportParams params, Class<?> pojoClass, Collection<?> dataset) {

        if (log.isDebugEnabled()) {
            log.debug("Excel export start ,class is {}", pojoClass);
            log.debug("Excel version is {}", params.getExcelType().equals(ExcelType.HSSF) ? "03" : "07");
        }

        // 判断参数
        Objects.requireNonNull(workbook);
        Objects.requireNonNull(params);
        Objects.requireNonNull(pojoClass);

        // 建立sheet
        Sheet sheet = workbook.createSheet(params.getSheetName());
        // 解析注解得到注解实体类
        List<ExcelEntity> entities = ExcelExportParseUtil.parseClass(pojoClass);
        Collections.sort(entities);
        //
        int rowHead = createHead(workbook, sheet, params, entities);
        createData(sheet,rowHead,entities,dataset);
        return workbook;
    }


    /**
     * 创建头部标题
     *
     * @param workbook
     * @param sheet
     * @param params
     * @param entities
     */
    public int createHead(Workbook workbook, Sheet sheet, ExportParams params, List<ExcelEntity> entities) {
        if (log.isDebugEnabled()) {
            log.debug("Excel create head start ,title is {}", entities);
        }
        int rows = 0;
        int filedWidth = CommonUtil.getCols(entities);
        // 如果总标题不为空，需要新建一行
        if (params.getTitle() != null) {
            rows += createHeaderRow(params, sheet, workbook,filedWidth);
        }
        rows += createTitleRow(params, sheet, workbook, rows,0, entities);
        sheet.createFreezePane(0, rows, 0, rows);
        return rows;
    }

    /**
     * 创建表头 目前系统由于不需要多个嵌套，因此功能仅支持一层
     * @param params
     * @param sheet
     * @param workbook
     * @param index
     * @param col
     * @param entities
     * @return
     */
    public int createTitleRow(ExportParams params, Sheet sheet, Workbook workbook, int index,int col,List<ExcelEntity> entities) {
        int totalRows = CommonUtil.getRows(entities);
        Row row = sheet.createRow(index);
        for (int i = 0,size = entities.size(); i < size; i++) {
            ExcelEntity entity = entities.get(i);
            List<ExcelEntity> entitySubList = entity.getList();
            // TODO：需要合并，递归行数
            ExcelWrite.createStringCell(row ,col, entity.getName(), null, null);
            if (entitySubList == null) {
                if (totalRows==2)
                    sheet.addMergedRegion(new CellRangeAddress(index, (index + totalRows-1), col, col));
                col++;
            }
            else{
                int subSize = entitySubList.size();
                sheet.addMergedRegion(new CellRangeAddress(index, index, col, col+subSize-1));
                createTitleRow(params,sheet,workbook,index+1,col,entitySubList);
                col += subSize;
            }
        }
        return totalRows;
    }

    /**
     * 创建总标题
     * @param params
     * @param sheet
     * @param workbook
     * @param filedWidth
     * @return
     */
    public int createHeaderRow(ExportParams params, Sheet sheet, Workbook workbook,int filedWidth) {

        Row row = sheet.createRow(0);
        ExcelWrite.createStringCell(row,0,params.getTitle(),null,null);
        try {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, filedWidth));
        }catch (IllegalArgumentException e){
            log.error("合并单元格错误日志",e.getMessage());
        }
        return 1;
    }

    /**
     * 需要反射出来所有的具体数据
     * @param sheet
     * @param index
     * @param entities
     * @param dataset
     * @return
     */
    public void createData(Sheet sheet,int index,List<ExcelEntity> entities,Collection<?> dataset){
        // 写入数据
        Iterator<?> iterator = dataset.iterator();
        while (iterator.hasNext()) {
            Object data = iterator.next();
            index += writeDataRow(sheet,index,0,entities,data);
        }
    }

    private int writeDataRow(Sheet sheet,int index,int column,List<ExcelEntity> entities,Object data){
        // 写入数据
        Row row=sheet.getRow(index) == null?sheet.createRow(index):sheet.getRow(index);
        int maxRow = 1;
        try {
            for (ExcelEntity entity : entities) {
                if(entity.getList() != null){
                    Collection<?> subListValue = CommonUtil.getListValueByReflect(entity, data);
                    List<ExcelEntity> subList = entity.getList();
                    for (Object o : subListValue) {
                        writeDataRow(sheet,index++,column,subList,o);
                    }
                    column += subList.size();
                    maxRow += subListValue.size() - 1;
                }else {
                    // 是否需要纵向合并单元格
                    Object result = entity.getField().get(data);
                    ExcelWrite.createStringCell(row,column++,result.toString(),null,null);
                }
            }
            // 判断是否需要合并
            if(maxRow > 1)
                writeMergeDataCol(sheet, index-maxRow, maxRow, entities);
        } catch (Exception e) {
            log.error("解析数据，导出数据出错",e);
        }
        return maxRow;
    }

    private void writeMergeDataCol(Sheet sheet,int index,int maxRow,List<ExcelEntity> entities){
        // TODO： 需要合并的单元格
        int cellNum = 0;
        for (ExcelEntity entity : entities) {
            if (entity.getList()!=null) {
                cellNum += entity.getList().size();
            }else if(entity.isNeedMerge()){
                // 需要合并到maxRow
                sheet.addMergedRegion(new CellRangeAddress(index, index + maxRow -1, cellNum, cellNum));
                cellNum++;
            }
        }
    }

}
