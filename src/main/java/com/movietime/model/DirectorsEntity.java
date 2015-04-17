package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "directors", schema = "", catalog = "movietime2")
public class DirectorsEntity {
    private int directorid;
    private String name;
    @JsonIgnore
    private List<MoviesEntity> movies;

    @Id
    @Column(name = "directorid", nullable = false, insertable = true, updatable = true)
    public int getDirectorid() {
        return directorid;
    }

    public void setDirectorid(int directorid) {
        this.directorid = directorid;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectorsEntity that = (DirectorsEntity) o;

        if (directorid != that.directorid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = directorid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies2directors", catalog = "movietime2", schema = "", joinColumns = @JoinColumn(name = "directorid", referencedColumnName = "directorid", nullable = false), inverseJoinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false))
    public List<MoviesEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesEntity> movies) {
        this.movies = movies;
    }
}
