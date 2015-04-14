package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Entity
@Table(name = "movies", schema = "", catalog = "movietime2")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieid")
public class MoviesEntity {
    private int movieid;
    private String title;
    private String year;
    private String imdbid;
    @JsonManagedReference
    private List<ActorsEntity> actors;
    @JsonManagedReference
    private List<WritersEntity> writers;
    @JsonManagedReference
    private List<ProducersEntity> producers;

    @Id
    @Column(name = "movieid", nullable = false, insertable = true, updatable = true)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 400)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "year", nullable = true, insertable = true, updatable = true, length = 100)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "imdbid", nullable = true, insertable = true, updatable = true, length = 10)
    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesEntity that = (MoviesEntity) o;

        if (movieid != that.movieid) return false;
        if (imdbid != null ? !imdbid.equals(that.imdbid) : that.imdbid != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (imdbid != null ? imdbid.hashCode() : 0);
        return result;
    }

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies2actors", catalog = "movietime2", schema = "", joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false), inverseJoinColumns = @JoinColumn(name = "actorid", referencedColumnName = "actorid", nullable = false))
    public List<ActorsEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorsEntity> actors) {
        this.actors = actors;
    }

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies2writers", catalog = "movietime2", schema = "", joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false), inverseJoinColumns = @JoinColumn(name = "writerid", referencedColumnName = "writerid", nullable = false))
    public List<WritersEntity> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersEntity> writers) {
        this.writers = writers;
    }

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies2producers", catalog = "movietime2", schema = "", joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false), inverseJoinColumns = @JoinColumn(name = "producerid", referencedColumnName = "producerid", nullable = false))
    public List<ProducersEntity> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersEntity> producers) {
        this.producers = producers;
    }
}
