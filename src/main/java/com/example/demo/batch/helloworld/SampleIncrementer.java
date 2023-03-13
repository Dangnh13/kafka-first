package com.example.demo.batch.helloworld;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class SampleIncrementer implements JobParametersIncrementer {

    public JobParameters getNext(JobParameters parameters) {
        System.out.println(">>>>>>>>>>>>>> SAMPLE INCREMENT");
        if (parameters==null || parameters.isEmpty()) {
            return new JobParametersBuilder().addLong("haidang", 1L).toJobParameters();
        }
        long id = parameters.getLong("haidang",1L) + 1;
        return new JobParametersBuilder().addLong("haidang", id).toJobParameters();
    }
}
