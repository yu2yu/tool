package com.yy.tool.db;

import com.yy.tool.db.service.ConnectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DBApplicationTest {


    @Autowired
    ConnectorService connectorService;

    @Test
    public void testConnector(){

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("type","mysql");
        hashMap.put("name","testMysql");
        hashMap.put("username","root");
        hashMap.put("password","root");
        hashMap.put("url","jdbc:mysql://127.0.0.1:3306/m_pop_pms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        hashMap.put("driverClassName","com.mysql.jdbc.Driver");

        connectorService.add(hashMap);

    }
}
