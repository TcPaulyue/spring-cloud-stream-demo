package io.pivotal.loansource;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(LoanController.class).withIdentity("QuartzTask").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("QuartzTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
    }

}