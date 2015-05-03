package com.movietime.entitywrappers;

import com.movietime.model.ActorsEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Attila on 2015-05-03.
 */
public class ActorPage extends ResourceSupport {
    private List<ActorsEntity> actors;
    private int page;
    private int pageSize;

    public ActorPage(List<ActorsEntity> actors, int page, int pageSize) {
        this.actors = actors;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ActorPage() {
    }

    public List<ActorsEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorsEntity> actors) {
        this.actors = actors;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
