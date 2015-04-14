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
@Table(name = "writers", schema = "", catalog = "movietime2")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "writerid")
public class WritersEntity {
    private int writerid;
    private String name;
    @JsonBackReference
    private List<MoviesEntity> movies;

    @Id
    @Column(name = "writerid", nullable = false, insertable = true, updatable = true)
    public int getWriterid() {
        return writerid;
    }

    public void setWriterid(int writerid) {
        this.writerid = writerid;
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

        WritersEntity that = (WritersEntity) o;

        if (writerid != that.writerid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = writerid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    //@JsonIgnore
    @ManyToMany(mappedBy = "writers", fetch = FetchType.LAZY)
    public List<MoviesEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesEntity> movies) {
        this.movies = movies;
    }
}
