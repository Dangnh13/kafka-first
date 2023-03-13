package com.example.demo.batch.helloworld;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@Configuration
/*

@PropertySource("application.properties")*/
public class BatchConfiguration{

   /* @Bean
    public BatchConfigurer batchConfigurer(DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            protected JobRepository createJobRepository() throws Exception {
                    MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
                    return factory.getObject();
            }
        };
    }
*/
/*
    @Bean
    @Primary
    public BatchConfigurer batchConfigurer(@Qualifier("repo-db") DataSource dataSource) {
        return new DefaultBatchConfigurer(jobRepoDataSource());
    }

    @Bean(name = "repo-db")
    @Primary
    public DataSource jobRepoDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/bootbatch?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("123456")
                .build();
    }*/
}