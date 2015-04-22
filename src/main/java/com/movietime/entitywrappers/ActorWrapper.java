package com.movietime.entitywrappers;

import com.movietime.model.ActorsEntity;
import com.movietime.model.BiographiesEntity;
import com.movietime.model.MoviesEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Attila on 2015-04-16.
 */
public class ActorWrapper extends ResourceSupport {
    ActorsEntity actor;
    Map<LightMovieWrapper, String> roles;
    BiographiesEntity biography;

    public ActorWrapper() {
        roles = new HashMap<>();
    }

    public BiographiesEntity getBiography() {
        return biography;
    }

    public void setBiography(BiographiesEntity biography) {
        this.biography = biography;
    }

    public ActorsEntity getActor() {
        return actor;
    }

    public void setActor(ActorsEntity actor) {
        this.actor = actor;
    }

    public void addRole(LightMovieWrapper movie, String role) {
        roles.put(movie, role);
    }

    public Map<LightMovieWrapper, String> getRoles() {
        return roles;
    }
}
