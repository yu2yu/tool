package com.yy.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/3
 */

@SpringBootApplication
@EnableConfigurationProperties
public class ScheduleJobExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleJobExecutorApplication.class, args);
    }

}
