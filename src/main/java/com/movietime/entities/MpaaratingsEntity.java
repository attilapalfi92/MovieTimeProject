package com.movietime.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-16.
 */

//public class MpaaratingsEntity {}

@Entity
@Table(name = "mpaaratings", schema = "", catalog = "movietime2")
public class MpaaratingsEntity {
    @JsonIgnore
    private int movieId;
    private String reasonText;
    private int mpaaRatings_id;
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
    @Column(name = "reasontext", nullable = true, insertable = true, updatable = true, columnDefinition = "text", length = 65535)
    public String getReasonText() {
        return reasonText;
    }

    public void setReasonText(String reasontext) {
        this.reasonText = reasontext;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mpaaratings_id", nullable = false, insertable = true, updatable = true)
    public int getMpaaratingsId() {
        return mpaaRatings_id;
    }

    public void setMpaaratingsId(int mpaaratings_id) {
        this.mpaaRatings_id = mpaaratings_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpaaratingsEntity that = (MpaaratingsEntity) o;

        if (movieId != that.movieId) return false;
        if (mpaaRatings_id != that.mpaaRatings_id) return false;
        if (reasonText != null ? !reasonText.equals(that.reasonText) : that.reasonText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (reasonText != null ? reasonText.hashCode() : 0);
        result = 31 * result + mpaaRatings_id;
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
