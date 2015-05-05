package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "releasedates", schema = "", catalog = "movietime2")
public class ReleasedatesEntity {
    private int movieId;
    private String country;
    private String imdbDate;
    private Date releaseDate;
    private String addition;
    private int releaseDateId;
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
    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "imdbdate", nullable = false, insertable = true, updatable = true, length = 50)
    public String getImdbDate() {
        return imdbDate;
    }

    public void setImdbDate(String imdbdate) {
        this.imdbDate = imdbdate;
    }

    @Basic
    @Column(name = "releasedate", nullable = true, insertable = true, updatable = true)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releasedate) {
        this.releaseDate = releasedate;
    }

    @Basic
    @Column(name = "addition", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Id
    @Column(name = "releasedate_id", nullable = false, insertable = true, updatable = true)
    public int getReleaseDateId() {
        return releaseDateId;
    }

    public void setReleaseDateId(int releasedateId) {
        this.releaseDateId = releasedateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleasedatesEntity that = (ReleasedatesEntity) o;

        if (movieId != that.movieId) return false;
        if (releaseDateId != that.releaseDateId) return false;
        if (addition != null ? !addition.equals(that.addition) : that.addition != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (imdbDate != null ? !imdbDate.equals(that.imdbDate) : that.imdbDate != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (imdbDate != null ? imdbDate.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (addition != null ? addition.hashCode() : 0);
        result = 31 * result + releaseDateId;
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
