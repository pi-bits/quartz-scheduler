package com.serviceinfotech.schedulers.quartzschedulers.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class SimpleJobListener implements JobListener{


    private static Logger logger = LoggerFactory
            .getLogger(SimpleJobListener.class);
    private String name;

    public SimpleJobListener(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
        logger.info("jobToBeExecuted");
        logger.info("Job : " + jobName + " is going to start...");

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        logger.info("jobToBeVetoed");

    }


    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        logger.info("Next firing time : {} ", context.getNextFireTime());
    }
}
