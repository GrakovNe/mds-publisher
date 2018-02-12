package org.grakovne.mds.publisher.entity.mds;

public class Rating implements MdsEntity {
    private Integer id;

    private Story story;

    private Double value;

    private Integer voters;

    public Rating() {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getVoters() {
        return voters;
    }

    public void setVoters(Integer voters) {
        this.voters = voters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rating rating = (Rating) o;

        if (story != null ? !story.equals(rating.story) : rating.story != null) {
            return false;
        }
        if (value != null ? !value.equals(rating.value) : rating.value != null) {
            return false;
        }
        return voters != null ? voters.equals(rating.voters) : rating.voters == null;
    }

    @Override
    public int hashCode() {
        int result = story != null ? story.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (voters != null ? voters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
            "id=" + id +
            ", value=" + value +
            ", voters=" + voters +
            '}';
    }
}
