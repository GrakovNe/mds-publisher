package org.grakovne.mds.publisher.pipeline.step.mds;

import org.grakovne.mds.publisher.entity.mds.Story;
import org.grakovne.mds.publisher.network.api.mds.MdsService;
import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MdsDownloadMdsStoryStep implements Step {

    private final static Logger LOGGER = LoggerFactory.getLogger(MdsDownloadMdsStoryStep.class);
    private MdsService mdsService;

    public MdsDownloadMdsStoryStep() {
        mdsService = new MdsService();
    }

    @Override
    public Context execute(Context context) {

        LOGGER.info("Download random story from MDS Started");

        Story story = mdsService.getRandomStory();
        File file = mdsService.getStoryAudio(story);

        context.put(Story.class, story);
        context.put(File.class, file);

        LOGGER.info("Download random story from MDS Finished");

        return context;
    }
}
