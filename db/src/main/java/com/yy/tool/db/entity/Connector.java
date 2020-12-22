package com.yy.tool.db.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/22
 */
@Data
public class Connector {

    private String type;
    private String name;
    private String username;
    private String password;
    private String url;
    private String driverClassName;

    List<String> table;

}
