package com.springBatch.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication implements CommandLineRunner {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("emailJob")
	Job emailDataProcessor;

	@Autowired
	@Qualifier("coneectIdJob")
	Job connectIdDataProcessor;

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = "connectId";

		JobParametersBuilder builder = new JobParametersBuilder();
		JobParameters jobParameter = builder.addString("jobType", "OCA").addString("fileName", "textfile")
				.addString("fileType", fileName).toJobParameters();

		switch (fileName) {
		case "email":
			jobLauncher.run(emailDataProcessor, jobParameter);
			break;
		case "connectId":
			jobLauncher.run(connectIdDataProcessor, jobParameter);
			break;
		}

	}

}
