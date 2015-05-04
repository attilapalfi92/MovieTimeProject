package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-05-01.
 */
@Entity
@Table(name = "plots", schema = "", catalog = "movietime2")
public class PlotsEntity {
    private int movieId;
    private String plotText;
    private int plot_id;
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
    @Column(name = "plottext", nullable = true, insertable = true, updatable = true, columnDefinition = "text", length = 65535)
    public String getPlotText() {
        return plotText;
    }

    public void setPlotText(String plottext) {
        this.plotText = plottext;
    }

    @Id
    @Column(name = "plot_id", nullable = false, insertable = true, updatable = true)
    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlotsEntity that = (PlotsEntity) o;

        if (movieId != that.movieId) return false;
        if (plot_id != that.plot_id) return false;
        if (plotText != null ? !plotText.equals(that.plotText) : that.plotText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (plotText != null ? plotText.hashCode() : 0);
        result = 31 * result + plot_id;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
