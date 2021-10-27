package com.nuc.zp.quertz2.service;


import com.nuc.zp.quertz2.domain.MyJob;

import java.util.List;

public interface JobService {

    MyJob findJob(Integer jobId);

    List<MyJob> findJobs();

    void createJob(MyJob myJob);

    void updateJob(MyJob myJob);

    void deleteJobs(String[] jobIds);

    void updateBatch(String jobIds, String status);

    void run(String jobIds);

    void pause(String jobIds);

    void resume(String jobIds);

}
