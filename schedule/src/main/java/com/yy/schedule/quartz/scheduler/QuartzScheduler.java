package com.yy.schedule.quartz.scheduler;

import com.yy.schedule.quartz.job.QuartzJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/11
 */
public class QuartzScheduler {

    public static void main(String[] args) throws SchedulerException {

        // 通过jobDetail来绑定job类
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job1","group1")
                .usingJobData("message","hello quartz").build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow()
                .withIdentity("trigger1","group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();
        // trigger 执行规则

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().startNow()
                .withIdentity("trigger1","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();

        // scheduler 调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();

    }

}
