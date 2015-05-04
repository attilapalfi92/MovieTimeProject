package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Entity
@Table(name = "producers", schema = "", catalog = "movietime2")
public class ProducersEntity {
    private int producerId;
    private String name;
    @JsonIgnore
    private List<MoviesEntity> movies;


    @Id
    @Column(name = "producerid", nullable = false, insertable = true, updatable = true)
    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerid) {
        this.producerId = producerid;
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

        ProducersEntity that = (ProducersEntity) o;

        if (producerId != that.producerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = producerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    //@JsonIgnore
    @ManyToMany(mappedBy = "producers", fetch = FetchType.LAZY)
    public List<MoviesEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesEntity> movies) {
        this.movies = movies;
    }
}
