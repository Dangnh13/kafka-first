package com.example.demo.batch.helloworld;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class HelloWorldJobParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> inside hello world job parameter validator");
        String startAt = jobParameters.getString("startAt");
        System.out.println(">>>>>>>>>>>>> Parameter value startAt :" + startAt);
        /*if(startAt > 0) {
            throw new JobParametersInvalidException(">>>>>>> Exception because startAt > 0");
        }*/
    }
}
