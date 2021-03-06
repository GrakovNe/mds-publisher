package org.grakovne.mds.publisher.entity.mds;

public class Cover implements MdsEntity {
    private Integer id;

    private Story story;

    private String base64EncodedCover;

    public Cover() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getBase64EncodedCover() {
        return base64EncodedCover;
    }

    public void setBase64EncodedCover(String base64EncodedCover) {
        this.base64EncodedCover = base64EncodedCover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cover cover = (Cover) o;

        if (story != null ? !story.equals(cover.story) : cover.story != null) {
            return false;
        }
        return base64EncodedCover != null
            ? base64EncodedCover.equals(cover.base64EncodedCover) : cover.base64EncodedCover == null;
    }

    @Override
    public int hashCode() {
        int result = story != null ? story.hashCode() : 0;
        result = 31 * result + (base64EncodedCover != null ? base64EncodedCover.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cover{" +
            "id=" + id +
            ", base64EncodedCover='" + base64EncodedCover + '\'' +
            '}';
    }
}
