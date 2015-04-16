package com.movietime.entitywrappers;

import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Attila on 2015-04-16.
 */
public class ActorWrapper {
    ActorsEntity actor;
    Map<MoviesEntity, String> roles;

    public ActorWrapper() {
        roles = new HashMap<>();
    }

    public ActorWrapper(ActorsEntity actor, Map<MoviesEntity, String> roles) {
        this.actor = actor;
        this.roles = roles;
    }

    public ActorsEntity getActor() {
        return actor;
    }

    public void setActor(ActorsEntity actor) {
        this.actor = actor;
    }

    public void addRole(MoviesEntity movie, String role) {
        roles.put(movie, role);
    }

    public Map<MoviesEntity, String> getRoles() {
        return roles;
    }
}
