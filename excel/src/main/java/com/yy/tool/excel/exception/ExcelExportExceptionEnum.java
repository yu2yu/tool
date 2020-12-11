package com.yy.tool.excel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/11/25
 */
@AllArgsConstructor
public enum ExcelExportExceptionEnum {

    PARAMETER_ERROR("Excel导出参数错误"),
    EXPORT_ERROR("Excel导出错误");

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
