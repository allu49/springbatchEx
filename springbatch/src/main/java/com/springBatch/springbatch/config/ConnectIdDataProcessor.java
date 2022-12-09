package com.springBatch.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectIdDataProcessor {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	@Qualifier("coneectIdJob")
	public Job coneectIdJob() {
		return jobs.get("coneectIdJob").listener(new JobResultListerner()).start(connectIdDataProcessingStep()).build();
	}

	@Bean
	public Step connectIdDataProcessingStep() {
		return steps.get("connectIdDataProcessing").tasklet(connectIdTasklet(null, null)).build();
	}

	@Bean
	@StepScope
	public Tasklet connectIdTasklet(@Value("#{jobParameters['fileName']}") String fileName,
			@Value("#{jobParameters['fileType']}") String fileType) {
		return (stepContribution, chukContext) -> {
			System.out.println("connectId Processed...." + fileName + " type:" + fileType);
			return RepeatStatus.FINISHED;
		};
	}

}
