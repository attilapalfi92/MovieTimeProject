package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    private int movieid;
    private String reasontext;
    private int mpaaratings_id;
    @JsonIgnore
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = false, updatable = false)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "reasontext", nullable = true, insertable = true, updatable = true, columnDefinition = "text", length = 65535)
    public String getReasontext() {
        return reasontext;
    }

    public void setReasontext(String reasontext) {
        this.reasontext = reasontext;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mpaaratings_id", nullable = false, insertable = true, updatable = true)
    public int getMpaaratingsId() {
        return mpaaratings_id;
    }

    public void setMpaaratingsId(int mpaaratings_id) {
        this.mpaaratings_id = mpaaratings_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpaaratingsEntity that = (MpaaratingsEntity) o;

        if (movieid != that.movieid) return false;
        if (mpaaratings_id != that.mpaaratings_id) return false;
        if (reasontext != null ? !reasontext.equals(that.reasontext) : that.reasontext != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (reasontext != null ? reasontext.hashCode() : 0);
        result = 31 * result + mpaaratings_id;
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
