package com.example.onboarding.poc2.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.onboarding.poc2.job.JobInsertMovieAuto;

@Configuration
public class Quartz {
	@Bean
	public JobDetail jobAutoInsertDetails() {
		return JobBuilder.newJob(JobInsertMovieAuto.class).withIdentity("JobAutoInsert", "auto-insert-movie")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger jobAutoInsertTrigger(JobDetail jobAutoInsertDetails) {
		return TriggerBuilder.newTrigger().forJob(jobAutoInsertDetails)
				.withIdentity("auto-trigger", "auto-insert-movie")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * ? * * *")).build();
	}
}
