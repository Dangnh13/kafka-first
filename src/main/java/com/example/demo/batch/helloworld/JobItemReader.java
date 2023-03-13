package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobItemReader {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(name = "job10")
    public Job job10(@Qualifier("step10") Step step10) {
        return jobBuilderFactory.get("job10")
//                .incrementer(new RunIdIncrementer())
                .start(step10)
                .build();
    }

    @Bean(name = "step10")
    public Step step10() {
        return stepBuilderFactory.get("step10")
                .<Employee, Employee>chunk(5)
                .reader(reader())
                .writer(writer())

                // Config giới hạn lỗi để tiếp tục xử lý.
                // Nếu số lỗi gặp phải <= giới hạn thì tiếp tục xử lý, bằng ko sẽ ném ra exception = stop job
                // đồng thời trạng thái job trả về là failed
                // Lần thực thi lại sẽ bắt đầu từ chunk bị gặp lỗi
                .faultTolerant()
                .skipLimit(2)
                .skip(FlatFileParseException.class)
                .build();
    }

    @Bean
    public FlatFileItemReader<Employee> reader()
    {
        //Create reader instance
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();

        //Set input file location
        reader.setResource(new FileSystemResource("/Users/dangnh/Downloads/demo/input/inputData.csv"));

        //Set number of lines to skips. Use it if file has header rows.
        reader.setLinesToSkip(1);

        //Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(new DefaultLineMapper() {
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "id", "firstName", "lastName" });
                    }
                });
                //Set values in Employee class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
                    {
                        setTargetType(Employee.class);
                    }
                });
            }
        });
        return reader;
    }
    @Bean
    public ConsoleItemWriter<Employee> writer()
    {
        return new ConsoleItemWriter<Employee>();
    }
}

