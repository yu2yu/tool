package com.yy.tool.excel.exception;

/**
 * @version 1.0
 * @description: 断言处理类，用于抛出各种API异常
 * @author: yy
 * @date: 2020/11/25
 */
public class Asserts {

    public static void fail(String message) {
        throw new ExcelExportException(message);
    }
}

