package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Entity
@Table(name = "producers", schema = "", catalog = "movietime2")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "producerid")
public class ProducersEntity {
    private int producerid;
    private String name;
    //@JsonBackReference
    @JsonIgnore
    private List<MoviesEntity> movies;


    @Id
    @Column(name = "producerid", nullable = false, insertable = true, updatable = true)
    public int getProducerid() {
        return producerid;
    }

    public void setProducerid(int producerid) {
        this.producerid = producerid;
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

        if (producerid != that.producerid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = producerid;
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
