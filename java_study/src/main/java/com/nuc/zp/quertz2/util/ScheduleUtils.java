package com.nuc.zp.quertz2.util;

import com.nuc.zp.quertz2.domain.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 定时任务工具类
 */
@Slf4j
public class ScheduleUtils {

    protected ScheduleUtils() {

    }

    private static final String JOB_NAME_PREFIX = "TASK_";

    /**
     * 获取触发器key
     */
    private static TriggerKey getTriggerKey(Integer jobId) {
        return TriggerKey.triggerKey(JOB_NAME_PREFIX + jobId);
    }

    /**
     * 获取jobKey
     */
    private static JobKey getJobKey(Integer jobId) {
        return JobKey.jobKey(JOB_NAME_PREFIX + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Integer jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("获取Cron表达式失败", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, MyJob scheduleMyJob) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleMyJob.getJobId()))
                    .build();

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleMyJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleMyJob.getJobId())).startAt(scheduleMyJob.getStartTime()).endAt(scheduleMyJob.getEndTime())
                    .withSchedule(scheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(MyJob.JOB_PARAM_KEY, scheduleMyJob);

            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if (scheduleMyJob.getStatus().equals(MyJob.ScheduleStatus.PAUSE.getValue())) {
                pauseJob(scheduler, scheduleMyJob.getJobId());
            }
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, MyJob scheduleMyJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleMyJob.getJobId());

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleMyJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleMyJob.getJobId());

            if (trigger == null) {
                throw new SchedulerException("获取Cron表达式失败");
            } else {
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).startAt(scheduleMyJob.getStartTime()).withSchedule(scheduleBuilder).endAt(scheduleMyJob.getEndTime()).build();
                // 参数
                trigger.getJobDataMap().put(MyJob.JOB_PARAM_KEY, scheduleMyJob);
            }

            scheduler.rescheduleJob(triggerKey, trigger);

            // 暂停任务
            if (scheduleMyJob.getStatus().equals(MyJob.ScheduleStatus.PAUSE.getValue())) {
                pauseJob(scheduler, scheduleMyJob.getJobId());
            }

        } catch (SchedulerException e) {
            log.error("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, MyJob scheduleMyJob) {
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(MyJob.JOB_PARAM_KEY, scheduleMyJob);

            scheduler.triggerJob(getJobKey(scheduleMyJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
            log.error("执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("恢复定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败", e);
        }
    }
}
