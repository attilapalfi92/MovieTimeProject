package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
public class BiographiesEntity {}
/*
@Entity
@Table(name = "biographies", schema = "", catalog = "movietime2")
public class BiographiesEntity {
    private int bioid;
    private String name;
    private String biotext;
    @JsonIgnore
    private ActorsEntity actor;

    @Id
    @Column(name = "bioid", nullable = false, insertable = true, updatable = true)
    public int getBioid() {
        return bioid;
    }

    public void setBioid(int bioid) {
        this.bioid = bioid;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = false, updatable = false, columnDefinition = "varchar(250)", length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "biotext", nullable = true, insertable = true, updatable = true, columnDefinition = "mediumtext", length = 16777215)
    public String getBiotext() {
        return biotext;
    }

    public void setBiotext(String biotext) {
        this.biotext = biotext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BiographiesEntity that = (BiographiesEntity) o;

        if (bioid != that.bioid) return false;
        if (biotext != null ? !biotext.equals(that.biotext) : that.biotext != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bioid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (biotext != null ? biotext.hashCode() : 0);
        return result;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name", referencedColumnName = "name", nullable = false)
    public ActorsEntity getActor() {
        return actor;
    }

    public void setActor(ActorsEntity actor) {
        this.actor = actor;
    }
}
*/