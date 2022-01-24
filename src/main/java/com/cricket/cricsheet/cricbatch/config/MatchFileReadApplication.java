package com.cricket.cricsheet.cricbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = {"com.cricket.cricsheet"})
@EnableBatchProcessing
public class MatchFileReadApplication implements CommandLineRunner{

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job matchFileReaderjob;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MatchFileReadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long st= System.currentTimeMillis();
        jobLauncher.run(matchFileReaderjob, new JobParameters());
        System.out.println("time taken:"+(System.currentTimeMillis()- st)/1000);
		
	}

}
