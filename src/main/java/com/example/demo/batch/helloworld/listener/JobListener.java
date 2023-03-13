package com.example.demo.batch.helloworld.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(">>>>>>> BEFORE JOB LISTENER :" + jobExecution.getJobInstance().getJobName());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(">>>>>>> AFTER JOB LISTENER:" + jobExecution.getJobInstance().getJobName());
    }
}
