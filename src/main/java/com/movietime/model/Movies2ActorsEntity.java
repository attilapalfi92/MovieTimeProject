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
    private int movieId;
    private int actorId;
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
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieid) {
        this.movieId = movieid;
    }

    @Basic
    @Column(name = "actorid", nullable = false, insertable = false, updatable = false)
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorid) {
        this.actorId = actorid;
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

        if (actorId != that.actorId) return false;
        if (movieId != that.movieId) return false;
        if (asCharacter != null ? !asCharacter.equals(that.asCharacter) : that.asCharacter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + actorId;
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
