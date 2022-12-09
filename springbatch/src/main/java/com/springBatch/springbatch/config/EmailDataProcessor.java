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
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
public class EmailDataProcessor {


	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	@Qualifier("emailJob")
	public Job emailJob() {
		return jobs.get("emailJob").listener(new JobResultListerner()).start(emailDataProcessingStep())
				.build();
	}

	@Bean
	public Step emailDataProcessingStep() {
		return steps.get("emailDataProcessingStep").tasklet(emailTasklet(null, null)).build();
	}

	@Bean
	@StepScope
	public Tasklet emailTasklet(@Value("#{jobParameters['fileName']}") String fileName,
			@Value("#{jobParameters['fileType']}") String fileType) {
		return (stepContribution, chukContext) -> {
			System.out.println("email Processed...." + fileName + " type:" + fileType);
			return RepeatStatus.FINISHED;
		};
	}
}
