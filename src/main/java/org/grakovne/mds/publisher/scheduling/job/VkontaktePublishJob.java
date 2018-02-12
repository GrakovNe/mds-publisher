package org.grakovne.mds.publisher.scheduling.job;

import org.grakovne.mds.publisher.pipeline.chain.PublishStoryVkontakteChain;
import org.grakovne.mds.publisher.pipeline.chain.common.ChainResult;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class VkontaktePublishJob implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(VkontaktePublishJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        LOGGER.info("Vkontakte publish job Started");

        PublishStoryVkontakteChain publishStoryVkontakteChain = new PublishStoryVkontakteChain();
        ChainResult result = publishStoryVkontakteChain.execute();

        LOGGER.info("Vkontakte publish job Finished with result:\n {}", result);
    }

    public void config(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder
            .newJob(VkontaktePublishJob.class)
            .build();

        Trigger trigger = TriggerBuilder
            .newTrigger()
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInHours(2)
                .repeatForever())
            .build();

        scheduler.scheduleJob(job, trigger);
    }
}
