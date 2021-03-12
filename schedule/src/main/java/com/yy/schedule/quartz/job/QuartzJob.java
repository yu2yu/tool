package com.yy.schedule.quartz.job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import java.util.Date;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/11
 */
@Data
@Slf4j
public class QuartzJob implements Job {

    private String message;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // context为job执行的上下文环境
        log.info(message);

    }
}
