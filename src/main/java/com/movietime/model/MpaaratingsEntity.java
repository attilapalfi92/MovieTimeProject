package com.movietime.model;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-16.
 */
@Entity
@Table(name = "mpaaratings", schema = "", catalog = "movietime2")
public class MpaaratingsEntity {
    private int movieid;
    private String reasontext;
    private int mpaaratingsId;
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = true, updatable = true)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "reasontext", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getReasontext() {
        return reasontext;
    }

    public void setReasontext(String reasontext) {
        this.reasontext = reasontext;
    }

    @Id
    @Column(name = "mpaaratingsId", nullable = false, insertable = true, updatable = true)
    public int getMpaaratingsId() {
        return mpaaratingsId;
    }

    public void setMpaaratingsId(int mpaaratingsId) {
        this.mpaaratingsId = mpaaratingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpaaratingsEntity that = (MpaaratingsEntity) o;

        if (movieid != that.movieid) return false;
        if (mpaaratingsId != that.mpaaratingsId) return false;
        if (reasontext != null ? !reasontext.equals(that.reasontext) : that.reasontext != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (reasontext != null ? reasontext.hashCode() : 0);
        result = 31 * result + mpaaratingsId;
        return result;
    }

    @OneToOne(mappedBy = "mpaaRating")
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
