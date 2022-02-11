package com.loto.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务作业主调度程序
 */
public class QuartzBuilder {
    /**
     * main函数中开启定时任务
     */
    public static void main(String[] args) throws SchedulerException {
        // 1、创建任务调度器（好比公交调度站）
        Scheduler scheduler = QuartzBuilder.createScheduler();

        // 2、创建一个任务（好比某一个公交车的出行）
        JobDetail job = QuartzBuilder.createJob();

        // 3、创建任务的时间触发器（好比这个公交车的出行时间表）
        Trigger trigger = QuartzBuilder.createTrigger();

        // 4、使用任务调度器根据时间触发器执行我们的任务
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

    // 1、创建任务调度器（好比公交调度站）
    public static Scheduler createScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        return scheduler;
    }

    // 2、创建一个任务（好比某一个公交车的出行）
    public static JobDetail createJob() {
        // 自定义任务类
        JobBuilder jobBuilder = JobBuilder.newJob(TimerJob.class);
        jobBuilder.withIdentity("jobName", "myJob");
        JobDetail jobDetail = jobBuilder.build();
        return jobDetail;
    }

    /**
     * 3、创建作业任务时间触发器（类似于公交车出车时间表）
     */
    public static Trigger createTrigger() {
        // 创建时间触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "myTrigger")
                .startNow()
                // 每隔2s
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();

        return cronTrigger;
    }
}
