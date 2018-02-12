package org.grakovne.mds.publisher.network.api.vkontakte;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.grakovne.mds.publisher.configuraton.Configuration;
import org.grakovne.mds.publisher.entity.mds.Author;
import org.grakovne.mds.publisher.entity.mds.Story;
import org.grakovne.mds.publisher.network.api.ApiException;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.AttachedAudio;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.UploadAudioResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.grakovne.mds.publisher.network.api.vkontakte.Constants.RATING;
import static org.grakovne.mds.publisher.network.api.vkontakte.Constants.YEAR;

public class VkontakteService {
    private VkontakteRepository repository;
    private Configuration configuration;

    public VkontakteService() {
        this.repository = VkontakteRepository.ApiFactory.create();
        this.configuration = new Configuration();
    }

    public static String prepareAuthorsList(List<Author> authors) {
        StringBuilder builder = new StringBuilder();

        if (null == authors) {
            return "";
        }

        authors.forEach(author -> builder.append(", ").append(author.getName()));
        return builder.toString().replaceFirst(", ", "");
    }

    public UploadAudioResult uploadAudio(File file) {
        String url = configuration.getUploadUrl();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        try {
            return repository.uploadAudio(url, body).execute().body();
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public AttachedAudio attachFileToPlayList(UploadAudioResult result) {
        try {
            String rawVkResponse = repository.attachAudio(
                "done_add",
                result.getAid().toString(),
                "1",
                result.getAudio(),
                result.getGid().toString(),
                result.getHash(),
                result.getMid().toString(),
                result.getServer().toString(),
                "1"
            ).execute().body().string();

            return parseVkAttachResponse(result, rawVkResponse);
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }

    public void postStoryWall(Story story, AttachedAudio audio) {
        try {
            repository.postAudio(
                "1",
                "post",
                configuration.getPublicId(),
                "own",
                configuration.getWallPostHash(),
                "audio",
                formatAudioId(audio),
                formatStoryData(story)
            ).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatStoryData(Story story) {

        StringBuilder result = new StringBuilder();

        if (!story.getAuthors().isEmpty()) {
            result
                .append(prepareAuthorsList(story.getAuthors()))
                .append(" - ");
        }

        if (null != story.getTitle()) {
            result
                .append(story.getTitle());
        }

        result.append("\n");

        if (null != story.getYear()) {
            result
                .append("\n")
                .append(String.format("%s%d", YEAR, story.getYear()));
        }

        if (null != story.getRating()) {
            result
                .append("\n")
                .append(String.format("%s%s", RATING, story.getRating().getValue().floatValue()));
        }

        if (null != story.getAnnotation()) {
            result.append("\n\n").append(story.getAnnotation());
        }

        return result.toString().trim();
    }

    private String formatAudioId(AttachedAudio attachedAudio) {
        return configuration.getPublicId() + "_" + attachedAudio.getAudioId();
    }

    private AttachedAudio parseVkAttachResponse(UploadAudioResult uploadAudioResult, String response) {
        String[] responseData = response.substring(response.indexOf("[") + 1).split(",");
        try {
            return new AttachedAudio(uploadAudioResult.getMid(), Long.parseLong(responseData[0]));
        } catch (Exception e) {
            throw new ApiException("Can't attach file to playlist");
        }
    }


}
