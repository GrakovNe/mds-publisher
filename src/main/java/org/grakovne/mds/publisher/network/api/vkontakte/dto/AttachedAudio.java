package org.grakovne.mds.publisher.network.api.vkontakte.dto;

public class AttachedAudio {
    private Long mid;
    private Long audioId;

    public AttachedAudio(Long mid, Long audioId) {
        this.mid = mid;
        this.audioId = audioId;
    }

    public Long getMid() {
        return mid;
    }

    public Long getAudioId() {
        return audioId;
    }

    @Override
    public String toString() {
        return "AttachedAudio{" +
            "mid=" + mid +
            ", audioId=" + audioId +
            '}';
    }
}
