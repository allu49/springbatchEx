package com.springBatch.springbatch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobResultListerner implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Starting.....");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("End...........");
	}

}
