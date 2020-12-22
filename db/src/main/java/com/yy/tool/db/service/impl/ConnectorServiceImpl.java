package com.yy.tool.db.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.tool.db.entity.Connector;
import com.yy.tool.db.manage.DBUtil;
import com.yy.tool.db.service.ConnectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/22
 */
@Slf4j
@Service
public class ConnectorServiceImpl implements ConnectorService {

    @Override
    public Connector add(Map<String, String> params) {

        log.info("params:{}", params);
        String name = params.get("name");
        String connectorType = params.get("type");
        Assert.hasText(name, "connector name is empty.");
        Assert.hasText(connectorType, "connector connectorType is empty.");

        ObjectMapper mapper = new ObjectMapper();
        Connector connector = null;
        try {
            connector = mapper.readValue(mapper.writeValueAsString(params), Connector.class);
        } catch (IOException e) {
            log.error("转换为对象出错");
        }
        // 首先查询该配置的数据库参数是否有效
        setTable(connector);
        return connector;
    }

    private void setTable(Connector connector) {
        boolean alive = DBUtil.alive(connector);
        if (!alive) {
            log.warn("无效的数据库配置，请检查",connector);
        }
        Assert.isTrue(alive, "无法连接.");
        // 如果可以连接则获取全部表信息
        List<String> table = DBUtil.getTable(connector);
        connector.setTable(table);
    }
}
