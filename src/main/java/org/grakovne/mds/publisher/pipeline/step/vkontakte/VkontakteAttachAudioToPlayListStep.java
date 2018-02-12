package org.grakovne.mds.publisher.pipeline.step.vkontakte;

import org.grakovne.mds.publisher.network.api.vkontakte.VkontakteService;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.AttachedAudio;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.UploadAudioResult;
import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VkontakteAttachAudioToPlayListStep implements Step {

    private final static Logger LOGGER = LoggerFactory.getLogger(VkontakteAttachAudioToPlayListStep.class);
    private VkontakteService vkontakteService;

    public VkontakteAttachAudioToPlayListStep() {
        vkontakteService = new VkontakteService();
    }

    @Override
    public Context execute(Context context) {

        LOGGER.info("Attach uploaded file to playlist Started");

        UploadAudioResult uploadAudioResult = (UploadAudioResult) context.get(UploadAudioResult.class);
        AttachedAudio attachedAudio = vkontakteService.attachFileToPlayList(uploadAudioResult);

        context.put(AttachedAudio.class, attachedAudio);

        LOGGER.info("Attach uploaded file to playlist Finished");

        return context;
    }
}
