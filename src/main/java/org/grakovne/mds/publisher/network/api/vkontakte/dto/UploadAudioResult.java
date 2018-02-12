package org.grakovne.mds.publisher.network.api.vkontakte.dto;

public class UploadAudioResult {
    private Long mid;
    private Long gid;
    private Long aid;
    private Integer server;
    private String audio;
    private String hash;

    public UploadAudioResult() {
    }

    public Long getMid() {
        return mid;
    }

    public Long getGid() {
        return gid;
    }

    public Long getAid() {
        return aid;
    }

    public Integer getServer() {
        return server;
    }

    public String getAudio() {
        return audio;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "UploadAudioResult{" +
            "mid=" + mid +
            ", gid=" + gid +
            ", aid=" + aid +
            ", server=" + server +
            ", audio='" + audio + '\'' +
            ", hash='" + hash + '\'' +
            '}';
    }
}
