package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "locations", schema = "", catalog = "movietime2")
public class LocationsEntity {
    private int movieid;
    private String location;
    private String addition;
    private int locationId;
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
    @Column(name = "location", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
    @Column(name = "location_id", nullable = false, insertable = true, updatable = true)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsEntity that = (LocationsEntity) o;

        if (locationId != that.locationId) return false;
        if (movieid != that.movieid) return false;
        if (addition != null ? !addition.equals(that.addition) : that.addition != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (addition != null ? addition.hashCode() : 0);
        result = 31 * result + locationId;
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
