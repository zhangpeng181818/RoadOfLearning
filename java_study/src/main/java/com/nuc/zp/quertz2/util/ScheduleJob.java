package com.nuc.zp.quertz2.util;

import com.nuc.zp.quertz2.domain.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {

        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        MyJob scheduleMyJob = (MyJob) context.getMergedJobDataMap().get(MyJob.JOB_PARAM_KEY);

        // 获取spring bean

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            log.info("任务准备执行，任务ID：{}", scheduleMyJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleMyJob.getBeanName(), scheduleMyJob.getMethodName(),
                    scheduleMyJob.getParams());
            Future<?> future = service.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            // 任务状态 0：成功 1：失败

            log.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleMyJob.getJobId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleMyJob.getJobId(), e);
            // 任务状态 0：成功 1：失败
        } finally {
        }
    }
}
