package org.grakovne.mds.publisher.pipeline.step.vkontakte;

import org.grakovne.mds.publisher.network.api.vkontakte.VkontakteService;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.UploadAudioResult;
import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class VkontakteUploadAudioStep implements Step {

    private final static Logger LOGGER = LoggerFactory.getLogger(VkontakteUploadAudioStep.class);
    private VkontakteService vkontakteService;

    public VkontakteUploadAudioStep() {
        vkontakteService = new VkontakteService();
    }

    @Override
    public Context execute(Context context) {

        LOGGER.info("Upload audio file to Vkontakte page Started");

        File audioFile = (File) context.get(File.class);
        UploadAudioResult uploadAudioResult = vkontakteService.uploadAudio(audioFile);

        context.put(UploadAudioResult.class, uploadAudioResult);

        LOGGER.info("Upload audio file to Vkontakte page Finished");

        return context;
    }
}
