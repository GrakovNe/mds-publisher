package org.grakovne.mds.publisher.network.api.mds;

import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.grakovne.mds.publisher.entity.mds.Story;
import org.grakovne.mds.publisher.network.api.ApiException;

import java.io.File;

import static org.grakovne.mds.publisher.network.api.ApiNames.MDS_API_BASE_URL;

public class MdsService {

    private static final String STORY_TEMP_FILE_PREFIX = "mds_story_temporary_file_";
    private static final String STORY_TEMP_FILE_EXTENSION = ".mp3";
    private MdsRepository mdsRepository;

    public MdsService() {
        mdsRepository = MdsRepository.ApiFactory.create();
    }

    public Story getRandomStory() {
        try {
            return mdsRepository.getRandomStory().execute().body().getBody();
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public File getStoryAudio(Story story) {
        try {
            File resultFile = File.createTempFile(STORY_TEMP_FILE_PREFIX, story.getId() + STORY_TEMP_FILE_EXTENSION);
            ResponseBody body = mdsRepository.getStoryAudio(findStoryUrl(story)).execute().body();
            FileUtils.writeByteArrayToFile(resultFile, body.bytes());

            return resultFile;
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public void deleteStoryAudio(File file) {
        file.delete();
    }

    private String findStoryUrl(Story story) {
        return MDS_API_BASE_URL + "story/" + story.getId() + "/audio/";
    }
}
