package org.grakovne.mds.publisher.scheduling;

import org.grakovne.mds.publisher.scheduling.job.VkontaktePublishJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;

public class ApplicationScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationScheduler.class);
    private final Scheduler scheduler;

    public ApplicationScheduler() {
        try {
            this.scheduler = getDefaultScheduler();
            configureJobs();
        } catch (SchedulerException ex) {
            LOGGER.error("Cant' schedule jobs");
            throw new RuntimeException();
        }
    }

    public void reconfigureJobs() {
        try {
            scheduler.clear();
            configureJobs();
        } catch (SchedulerException e) {
            LOGGER.error("Cant' reconfigure scheduler");
            throw new RuntimeException();
        }
    }

    public void start() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error("Cant' start scheduler");
            throw new RuntimeException();
        }
    }

    public void stop() {
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            LOGGER.error("Cant' start scheduler");
            throw new RuntimeException();
        }
    }

    private void configureJobs() throws SchedulerException {
        new VkontaktePublishJob().config(scheduler);
    }

}
