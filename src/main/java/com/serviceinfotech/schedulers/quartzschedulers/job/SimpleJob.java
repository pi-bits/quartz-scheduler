package com.serviceinfotech.schedulers.quartzschedulers.job;

import com.serviceinfotech.schedulers.quartzschedulers.service.SimpleJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob implements Job {

    //    @Autowired
    private SimpleJobService simpleJobService;

    public static final String NAME = "SIMPLE_JOB";

    public SimpleJob() {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            ApplicationContext applicationContext = (ApplicationContext)
                    jobExecutionContext.getScheduler().getContext().get("applicationContext");
            simpleJobService = (SimpleJobService) applicationContext.getBean("simpleJobService");
            simpleJobService.printAndEmail();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
