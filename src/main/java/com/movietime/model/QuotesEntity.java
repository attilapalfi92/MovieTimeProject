package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "quotes", schema = "", catalog = "movietime2")
public class QuotesEntity {
    private int movieid;
    private String quotetext;
    private int quoteId;
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
    @Column(name = "quotetext", nullable = true, insertable = true, updatable = true, columnDefinition = "mediumtext", length = 16777215)
    public String getQuotetext() {
        return quotetext;
    }

    public void setQuotetext(String quotetext) {
        this.quotetext = quotetext;
    }

    @Id
    @Column(name = "quote_id", nullable = false, insertable = true, updatable = true)
    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuotesEntity that = (QuotesEntity) o;

        if (movieid != that.movieid) return false;
        if (quoteId != that.quoteId) return false;
        if (quotetext != null ? !quotetext.equals(that.quotetext) : that.quotetext != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (quotetext != null ? quotetext.hashCode() : 0);
        result = 31 * result + quoteId;
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
