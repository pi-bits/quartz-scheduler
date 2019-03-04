package com.serviceinfotech.schedulers.quartzschedulers.job;

import com.serviceinfotech.schedulers.quartzschedulers.service.SimpleJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob implements Job {

    @Autowired
    private SimpleJobService simpleJobService;


    public SimpleJob() {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        simpleJobService.printAndEmail();

    }
}
