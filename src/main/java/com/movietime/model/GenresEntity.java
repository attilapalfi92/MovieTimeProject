package com.movietime.model;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-16.
 */
@Entity
@Table(name = "genres", schema = "", catalog = "movietime2")
public class GenresEntity {
    private int movieid;
    private String genre;
    private int genreId;
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = true, updatable = true)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "genre", nullable = false, insertable = true, updatable = true, length = 50)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Id
    @Column(name = "genreId", nullable = false, insertable = true, updatable = true)
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenresEntity that = (GenresEntity) o;

        if (genreId != that.genreId) return false;
        if (movieid != that.movieid) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + genreId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
