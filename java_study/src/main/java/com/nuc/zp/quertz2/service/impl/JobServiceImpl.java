package com.nuc.zp.quertz2.service.impl;

import com.nuc.zp.quertz2.dao.JobMapper;
import com.nuc.zp.quertz2.domain.MyJob;
import com.nuc.zp.quertz2.service.JobService;
import com.nuc.zp.quertz2.util.ScheduleUtils;
import com.nuc.zp.quertz2.util.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service("JobService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper baseMapper;

    @Autowired
    private Scheduler scheduler;


    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<MyJob> scheduleMyJobList = this.baseMapper.queryList();
        // 如果不存在，则创建
        scheduleMyJobList.forEach(scheduleJob -> {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        });
    }

    @Override
    public MyJob findJob(Integer jobId) {
        return this.baseMapper.findById(jobId).get();
    }

    @Override
    public List<MyJob> findJobs() {
        return this.baseMapper.queryList();
    }


    @Override
    @Transactional
    public void createJob(MyJob myJob) {
        this.baseMapper.save(myJob);
        ScheduleUtils.createScheduleJob(scheduler, myJob);
    }

    @Override
    @Transactional
    public void updateJob(MyJob myJob) {

        this.baseMapper.save(myJob);
        ScheduleUtils.updateScheduleJob(scheduler, myJob);
    }

    @Override
    @Transactional
    public void deleteJobs(String[] jobIds) {
        List<String> list = Arrays.asList(jobIds);
        list.forEach(jobId -> ScheduleUtils.deleteScheduleJob(scheduler, Integer.valueOf(jobId)));

    }

    @Override
    @Transactional
    public void updateBatch(String jobIds, String status) {
        String[] list = jobIds.split(StringPool.COMMA);
        for (String id : list) {
            MyJob myJob = findJob(Integer.valueOf(id));
            myJob.setStatus(status);
            this.baseMapper.save(myJob);
        }
    }

    @Override
    @Transactional
    public void run(String jobIds) {
        String[] list = jobIds.split(StringPool.COMMA);
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.run(scheduler, this.findJob(Integer.valueOf(jobId))));
    }

    @Override
    @Transactional
    public void pause(String jobIds) {
        String[] list = jobIds.split(StringPool.COMMA);
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.pauseJob(scheduler, Integer.valueOf(jobId)));
        this.updateBatch(jobIds, MyJob.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional
    public void resume(String jobIds) {
        String[] list = jobIds.split(StringPool.COMMA);
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.resumeJob(scheduler, Integer.valueOf(jobId)));
        this.updateBatch(jobIds, MyJob.ScheduleStatus.NORMAL.getValue());
    }
}
