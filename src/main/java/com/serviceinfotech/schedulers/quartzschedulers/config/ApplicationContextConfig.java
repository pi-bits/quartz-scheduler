package com.serviceinfotech.schedulers.quartzschedulers.config;


import com.serviceinfotech.schedulers.quartzschedulers.job.SimpleJob;
import com.serviceinfotech.schedulers.quartzschedulers.listeners.SimpleJobListener;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class ApplicationContextConfig {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SimpleJobListener jobsListener;



    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(SimpleJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        return jobDetailFactory;
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        int frequencyInSec = 10;
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(frequencyInSec)
                .repeatForever())
                .build();
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        logger.debug("Configuring Job factory");
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactory.setGlobalJobListeners(jobsListener);
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setStartupDelay(1);
        logger.debug("Starting Scheduler threads");
        schedulerFactory.start();
        return schedulerFactory;
    }


}
