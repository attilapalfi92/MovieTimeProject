package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-16.
 */
@Entity
@Table(name = "movies2actors", schema = "", catalog = "movietime2")
public class Movies2ActorsEntity {
    private int m2aid;
    private int movieid;
    private int actorid;
    private String asCharacter;
    @JsonIgnore
    private ActorsEntity actor;
    @JsonIgnore
    private MoviesEntity movie;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "m2aid", nullable = false, insertable = true, updatable = true)
    public int getM2aid() { return m2aid; }

    public void setM2aid(int m2aid) { this.m2aid = m2aid; }

    @Basic
    @Column(name = "movieid", nullable = false, insertable = false, updatable = false)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "actorid", nullable = false, insertable = false, updatable = false)
    public int getActorid() {
        return actorid;
    }

    public void setActorid(int actorid) {
        this.actorid = actorid;
    }

    @Basic
    @Column(name = "as_character", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getAsCharacter() {
        return asCharacter;
    }

    public void setAsCharacter(String asCharacter) {
        this.asCharacter = asCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movies2ActorsEntity that = (Movies2ActorsEntity) o;

        if (actorid != that.actorid) return false;
        if (movieid != that.movieid) return false;
        if (asCharacter != null ? !asCharacter.equals(that.asCharacter) : that.asCharacter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + actorid;
        result = 31 * result + (asCharacter != null ? asCharacter.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "actorid", referencedColumnName = "actorid", nullable = false)
    public ActorsEntity getActor() {
        return actor;
    }

    public void setActor(ActorsEntity actor) {
        this.actor = actor;
    }

    @ManyToOne
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
