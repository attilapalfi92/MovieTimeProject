package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "language", schema = "", catalog = "movietime2")
public class LanguageEntity {
    private int movieid;
    private String language;
    private String addition;
    private int languageId;
    @JsonIgnore
    private MoviesEntity movie;

    @Basic
    @Column(name = "movieid", nullable = false, insertable = false, updatable = false)
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    @Basic
    @Column(name = "language", nullable = false, insertable = true, updatable = true, length = 100)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "addition", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Id
    @Column(name = "language_id", nullable = false, insertable = true, updatable = true)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageEntity that = (LanguageEntity) o;

        if (languageId != that.languageId) return false;
        if (movieid != that.movieid) return false;
        if (addition != null ? !addition.equals(that.addition) : that.addition != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (addition != null ? addition.hashCode() : 0);
        result = 31 * result + languageId;
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
