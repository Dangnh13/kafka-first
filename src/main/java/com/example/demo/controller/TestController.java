package com.example.demo.controller;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping(value = "")
    public String hello() {
        System.out.println("INSIDE hello method()");
        return "testService.myService()";
    }

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    JobOperator jobOperator;

    @Autowired
    @Qualifier("job1")
    private Job job;

    // The function below accepts a GET request to invoke the Batch Process and returns a String as response with the message "Batch Process started!!".
    @GetMapping(path = "/batch/start") // Start batch process path
    public ResponseEntity<String> startBatch() {
        JobParameters Parameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
//                .addLong()
                .toJobParameters()
                ;
        try {
            jobLauncher.run(job, Parameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                 | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>("Batch Process started!!", HttpStatus.OK);
    }
}
