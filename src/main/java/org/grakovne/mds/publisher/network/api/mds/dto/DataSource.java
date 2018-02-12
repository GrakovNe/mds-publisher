package org.grakovne.mds.publisher.network.api.mds.dto;

import org.grakovne.mds.publisher.entity.mds.Story;

import java.io.File;

public class DataSource {
    private Story story;
    private File audio;

    public DataSource(Story story, File audio) {
        this.story = story;
        this.audio = audio;
    }

    public Story getStory() {
        return story;
    }

    public File getAudio() {
        return audio;
    }

    @Override
    public String toString() {
        return "DataSource{" +
            "story=" + story +
            ", audio=" + audio +
            '}';
    }
}
