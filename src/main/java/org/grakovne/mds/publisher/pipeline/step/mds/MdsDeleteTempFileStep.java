package org.grakovne.mds.publisher.pipeline.step.mds;

import org.grakovne.mds.publisher.pipeline.Context;
import org.grakovne.mds.publisher.pipeline.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MdsDeleteTempFileStep implements Step {

    private final static Logger LOGGER = LoggerFactory.getLogger(MdsDeleteTempFileStep.class);

    @Override
    public Context execute(Context context) {

        LOGGER.info("Delete temporary file Started");

        File file = (File) context.get(File.class);
        if (file.delete()) {
            LOGGER.info("Delete temporary file Finished");
        } else {
            LOGGER.info("Delete temporary file Failed");
        }

        return context;
    }
}
