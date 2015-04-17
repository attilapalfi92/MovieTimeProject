package com.movietime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
@Entity
@Table(name = "runningtimes", schema = "", catalog = "movietime2")
public class RunningtimesEntity {
    private int movieid;
    private String time;
    private String addition;
    private int runningtimeId;
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
    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 50)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "addition", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Id
    @Column(name = "runningtime_id", nullable = false, insertable = true, updatable = true)
    public int getRunningtimeId() {
        return runningtimeId;
    }

    public void setRunningtimeId(int runningtimeId) {
        this.runningtimeId = runningtimeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunningtimesEntity that = (RunningtimesEntity) o;

        if (movieid != that.movieid) return false;
        if (runningtimeId != that.runningtimeId) return false;
        if (addition != null ? !addition.equals(that.addition) : that.addition != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (addition != null ? addition.hashCode() : 0);
        result = 31 * result + runningtimeId;
        return result;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false)
    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }
}
