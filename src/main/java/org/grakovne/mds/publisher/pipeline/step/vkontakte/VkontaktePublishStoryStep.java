package org.grakovne.mds.publisher.pipeline.step.vkontakte;

import org.grakovne.mds.publisher.entity.mds.Story;
import org.grakovne.mds.publisher.network.api.vkontakte.VkontakteService;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.AttachedAudio;
import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkontaktePublishStoryStep implements Step {

    private final static Logger LOGGER = LoggerFactory.getLogger(VkontaktePublishStoryStep.class);
    private VkontakteService vkontakteService;

    public VkontaktePublishStoryStep() {
        vkontakteService = new VkontakteService();
    }

    @Override
    public Context execute(Context context) {

        LOGGER.info("Publish new post with story to Vkontakte page Started");

        Story story = (Story) context.get(Story.class);
        AttachedAudio attachedAudio = (AttachedAudio) context.get(AttachedAudio.class);

        vkontakteService.postStoryWall(story, attachedAudio);

        LOGGER.info("Publish new post with story to Vkontakte page Finished");

        return context;
    }
}
