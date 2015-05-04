package com.movietime.entitywrappers;

import com.movietime.model.Movies2ActorsEntity;
import com.movietime.model.ReleaseDatesEntity;

import java.util.List;

/**
 * Created by Attila on 2015-05-04.
 */
public class SubmittedMovie {
    private String title;
    private List<String> genres;
    private List<Movies2ActorsEntity> roles;
    private ReleaseDatesEntity release;
    private List<String> taglines;

    public SubmittedMovie(String title, List<String> genres, List<Movies2ActorsEntity> roles, ReleaseDatesEntity release, List<String> taglines) {
        this.title = title;
        this.genres = genres;
        this.roles = roles;
        this.release = release;
        this.taglines = taglines;
    }

    public SubmittedMovie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Movies2ActorsEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<Movies2ActorsEntity> roles) {
        this.roles = roles;
    }

    public ReleaseDatesEntity getRelease() {
        return release;
    }

    public void setRelease(ReleaseDatesEntity release) {
        this.release = release;
    }

    public List<String> getTaglines() {
        return taglines;
    }

    public void setTaglines(List<String> taglines) {
        this.taglines = taglines;
    }
}
