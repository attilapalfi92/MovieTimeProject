package com.movietime.entitywrappers;

import com.movietime.model.MoviesEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-22.
 */
public class MoviePage extends ResourceSupport {
    private List<MoviesEntity> movies;
    private int pageNum;
    private int pageSize;

    public MoviePage(List<MoviesEntity> movies, int pageNum, int pageSize) {
        this.movies = movies;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public MoviePage() {
        movies = new ArrayList<>();
        pageNum = 0;
        pageSize = 0;
    }

    public List<MoviesEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesEntity> movies) {
        this.movies = movies;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
