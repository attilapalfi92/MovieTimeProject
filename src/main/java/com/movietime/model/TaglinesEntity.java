package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "taglines", schema = "", catalog = "movietime2")
public class TaglinesEntity {
    private int movieId;
    private String taglineText;
    private int taglineId;
    @JsonIgnore
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = false, updatable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieid) {
        this.movieId = movieid;
    }

    @Basic
    @Column(name = "taglinetext", nullable = true, insertable = true, updatable = true, columnDefinition = "text", length = 65535)
    public String getTaglineText() {
        return taglineText;
    }

    public void setTaglineText(String taglinetext) {
        this.taglineText = taglinetext;
    }

    @Id
    @Column(name = "tagline_id", nullable = false, insertable = true, updatable = true)
    public int getTaglineId() {
        return taglineId;
    }

    public void setTaglineId(int taglineId) {
        this.taglineId = taglineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaglinesEntity that = (TaglinesEntity) o;

        if (movieId != that.movieId) return false;
        if (taglineId != that.taglineId) return false;
        if (taglineText != null ? !taglineText.equals(that.taglineText) : that.taglineText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (taglineText != null ? taglineText.hashCode() : 0);
        result = 31 * result + taglineId;
        return result;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
