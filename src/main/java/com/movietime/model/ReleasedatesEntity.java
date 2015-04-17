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
    private int movieid;
    private String country;
    private String imdbdate;
    private Date releasedate;
    private String addition;
    private int releasedateId;
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
    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "imdbdate", nullable = false, insertable = true, updatable = true, length = 50)
    public String getImdbdate() {
        return imdbdate;
    }

    public void setImdbdate(String imdbdate) {
        this.imdbdate = imdbdate;
    }

    @Basic
    @Column(name = "releasedate", nullable = true, insertable = true, updatable = true)
    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
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
    public int getReleasedateId() {
        return releasedateId;
    }

    public void setReleasedateId(int releasedateId) {
        this.releasedateId = releasedateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleasedatesEntity that = (ReleasedatesEntity) o;

        if (movieid != that.movieid) return false;
        if (releasedateId != that.releasedateId) return false;
        if (addition != null ? !addition.equals(that.addition) : that.addition != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (imdbdate != null ? !imdbdate.equals(that.imdbdate) : that.imdbdate != null) return false;
        if (releasedate != null ? !releasedate.equals(that.releasedate) : that.releasedate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (imdbdate != null ? imdbdate.hashCode() : 0);
        result = 31 * result + (releasedate != null ? releasedate.hashCode() : 0);
        result = 31 * result + (addition != null ? addition.hashCode() : 0);
        result = 31 * result + releasedateId;
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
