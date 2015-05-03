package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Entity
@Table(name = "actors", schema = "", catalog = "movietime2")
public class ActorsEntity extends ResourceSupport {
    private int actorid;
    private String name;
    private String sex;
    @JsonIgnore
    private List<Movies2ActorsEntity> roles;
    private String role;
    /*
    @JsonIgnore
    private BiographiesEntity biography;
    */

    @Id
    @Column(name = "actorid", nullable = false, insertable = true, updatable = true)
    public int getActorid() {
        return actorid;
    }

    public void setActorid(int actorid) {
        this.actorid = actorid;
    }

    @Transient
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, columnDefinition = "varchar(250)", length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2,
            columnDefinition = "enum('M','F')")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorsEntity that = (ActorsEntity) o;

        if (actorid != that.actorid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actorid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    public List<Movies2ActorsEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<Movies2ActorsEntity> roles) {
        this.roles = roles;
    }

    //@OneToOne(mappedBy = "actor", fetch = FetchType.LAZY)
    /*
    public BiographiesEntity getBiography() {
        return biography;
    }

    public void setBiography(BiographiesEntity biography) {
        this.biography = biography;
    }
    */
}
