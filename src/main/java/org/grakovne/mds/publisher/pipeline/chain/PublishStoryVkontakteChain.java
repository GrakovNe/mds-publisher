package org.grakovne.mds.publisher.pipeline.chain;

import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.chain.common.Chain;
import org.grakovne.mds.publisher.pipeline.chain.common.ChainResult;
import org.grakovne.mds.publisher.pipeline.chain.common.ChainResultType;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.grakovne.mds.publisher.pipeline.step.mds.MdsDeleteTempFileStep;
import org.grakovne.mds.publisher.pipeline.step.mds.MdsDownloadMdsStoryStep;
import org.grakovne.mds.publisher.pipeline.step.vkontakte.VkontakteAttachAudioToPlayListStep;
import org.grakovne.mds.publisher.pipeline.step.vkontakte.VkontaktePublishStoryStep;
import org.grakovne.mds.publisher.pipeline.step.vkontakte.VkontakteUploadAudioStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PublishStoryVkontakteChain implements Chain {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublishStoryVkontakteChain.class);

    private final List<Step> steps;
    private final Context context;

    public PublishStoryVkontakteChain() {
        this.steps = new ArrayList<>(5);
        context = new Context();

        steps.add(new MdsDownloadMdsStoryStep());
        steps.add(new VkontakteUploadAudioStep());
        steps.add(new VkontakteAttachAudioToPlayListStep());
        steps.add(new VkontaktePublishStoryStep());
        steps.add(new MdsDeleteTempFileStep());
    }

    @Override
    public ChainResult execute() {
        LOGGER.info("Publish random story to Vkontakte Started");

        try {
            steps.forEach(step -> step.execute(context));
        } catch (Exception ex) {

            LOGGER.warn("Publish random story to Vkontakte Failed");

            return new ChainResult(ChainResultType.FAILED, ex.getMessage());
        }

        LOGGER.info("Publish random story to Vkontakte Finished");

        return new ChainResult(ChainResultType.SUCCESSFUL);
    }
}
