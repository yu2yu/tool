package com.yy.tool.db.service;

import com.yy.tool.db.entity.Connector;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/22
 */
public interface ConnectorService {

    /**
     * 添加数据库连接
     * @param params
     * @return
     */
    Connector add(Map<String,String> params);

}
