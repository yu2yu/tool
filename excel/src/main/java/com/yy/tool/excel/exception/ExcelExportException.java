package com.yy.tool.excel.exception;

import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description: 自定义excel导出异常
 * @author: yy
 * @date: 2020/11/25
 */
@NoArgsConstructor
public class ExcelExportException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcelExportException(String message) {
        super(message);
    }

    public ExcelExportException(ExcelExportExceptionEnum type) {
        super(type.getMsg());
    }

    public ExcelExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelExportException(Throwable cause) {
        super(cause);
    }

    public ExcelExportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
