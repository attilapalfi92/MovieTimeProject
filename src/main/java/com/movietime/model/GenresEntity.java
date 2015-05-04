package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-16.
 */
@Entity
@Table(name = "genres", schema = "", catalog = "movietime2")
public class GenresEntity {
    private int movieId;
    private String genre;
    private int genre_id;
    @JsonIgnore
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = false, updatable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieid) {
        this.movieId = movieid;
    }

    @Basic
    @Column(name = "genre", nullable = false, insertable = true, updatable = true, length = 50)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id", nullable = false, insertable = true, updatable = true)
    public int getGenreId() {
        return genre_id;
    }

    public void setGenreId(int genreId) {
        this.genre_id = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenresEntity that = (GenresEntity) o;

        if (genre_id != that.genre_id) return false;
        if (movieId != that.movieId) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + genre_id;
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}