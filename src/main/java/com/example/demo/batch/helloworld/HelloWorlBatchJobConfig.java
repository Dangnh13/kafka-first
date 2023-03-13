package com.example.demo.batch.helloworld;

import com.example.demo.batch.helloworld.listener.JobListener;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
//@EnableAutoConfiguration
//@SpringBootApplication
public class HelloWorlBatchJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public TestService testService;

    @Bean(value = "job1", name = "job1")
    public Job helloWorldJob() {
        return jobBuilderFactory.get("job1")
                // Không cho phép restart lại job tại nơi bị thất bại, sẽ bắn lỗi JobRestartException
                // Mặc định springbatch job sẽ thực hiện lại từ nơi bị thất bại trước nếu cùng parameter
                // Điều này để đảm bảo rằng job luôn có instance mới mỗi khi chạy
                .preventRestart()
                // Tăng cường cho parameter như tạo thêm các biến mới hoặc modify giá trị biến cũ của lần chạy trước (TH restart job)
                // nó sẽ chạy trước JobParameter
                .incrementer(new SampleIncrementer())
                // validate parameters
                .validator(new HelloWorldJobParameterValidator())
                //  Intercepting Job Execution, thực thi trước và sau job, kể cả có lỗi cũng chạy vào afterJob(JobExecution jobExecution)
                .listener(new JobListener())
                .start(step1())
                .next(step3())
                .next(step4())
                .build();
    }

    @Bean(value = "job2", name = "job2")
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .start(step2())
                .build();
    }

    @Bean(value = "job3", name = "job3")
    public Job job3() {
        return jobBuilderFactory.get("job3")
                .start(step3())
                .build();
    }

    @Bean(name = "job4")
    public Job job4() {
        return jobBuilderFactory.get("job4")
                .start(step4())
                .build();
    }

    @Bean(value = "step1")
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    testService.helloService();
                    System.out.println(">>>>>>>>>  JOB STEP1 Hello World Step");
                    return RepeatStatus.FINISHED;
                })
                // Maximum start limit exceeded for step, cấu hình này làm cho step1
                // luôn luôn được chạy dù step1 ở trạng thái COMPLETED
                .allowStartIfComplete(true)
                .build();
    }

    @Bean(value = "step2")
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(">>>>>>>> RUN INSIDE STEP 2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean(value = "step3")
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(">>>>>>>> RUN INSIDE STEP 3");
                    System.out.println(3/0);
                    return RepeatStatus.FINISHED;
                })
                // Maximum start limit exceeded for step
                // Max số lần chạy lại trong 1 job instance
                .startLimit(2)
                .build();
    }

    @Bean(value = "step4")
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(">>>>>>>> RUN INSIDE STEP 4");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
