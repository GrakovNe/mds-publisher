package org.grakovne.mds.publisher;

import org.grakovne.mds.publisher.pipeline.chain.PublishStoryVkontakteChain;
import org.grakovne.mds.publisher.pipeline.chain.common.ChainResult;
import org.grakovne.mds.publisher.scheduling.ApplicationScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("App Started");

        ApplicationScheduler applicationScheduler = new ApplicationScheduler();
        applicationScheduler.start();
    }
}
