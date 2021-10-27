package com.nuc.zp.quertz2.controller;

import com.nuc.zp.quertz2.domain.MyJob;
import com.nuc.zp.quertz2.service.JobService;
import com.nuc.zp.quertz2.util.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("job")
public class JobController {

    private String message;

    @Autowired
    private JobService jobService;

    @GetMapping
    public List jobList() {
        return this.jobService.findJobs();
    }

    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    public void addJob(@RequestBody MyJob myJob) {
        log.debug("{}", myJob);
        this.jobService.createJob(myJob);

    }

    @DeleteMapping("/{jobIds}")
    public void deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
            String[] ids = jobIds.split(StringPool.COMMA);
            this.jobService.deleteJobs(ids);
    }

    @PutMapping
    public void updateJob(@RequestBody MyJob myJob)   {
        log.debug("{}", myJob);

        this.jobService.updateJob(myJob);
    }

    @GetMapping("run/{jobId}")
    public void runJob(@NotBlank(message = "{required}") @PathVariable String jobId)   {
        log.debug("{}",jobId);
        this.jobService.run(jobId);
    }

    @GetMapping("pause/{jobId}")
    public void pauseJob(@NotBlank(message = "{required}") @PathVariable String jobId)   {
        this.jobService.pause(jobId);
    }

    @GetMapping("resume/{jobId}")
    public void resumeJob(@NotBlank(message = "{required}") @PathVariable String jobId)   {
        this.jobService.resume(jobId);
    }


}
