package com.movietime.entitywrappers;

import com.movietime.model.ActorsEntity;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Attila on 2015-04-21.
 */
public class LightActorWrapper extends ResourceSupport {
    ActorsEntity actor;

    /*
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    */
}
