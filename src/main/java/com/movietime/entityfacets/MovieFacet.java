package com.movietime.entityfacets;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class MovieFacet {

    protected int movieid;
    protected String title;
    protected String year;
    protected String imdbid;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieFacet that = (MovieFacet) o;

        if (movieid != that.movieid) return false;
        if (imdbid != null ? !imdbid.equals(that.imdbid) : that.imdbid != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (imdbid != null ? imdbid.hashCode() : 0);
        return result;
    }

    abstract public List<ActorFacetNoConnections> getActors();

    abstract public void setActors(List<ActorFacetNoConnections> actors);

    abstract public List<WriterFacetNoConnections> getWriters();

    abstract public void setWriters(List<WriterFacetNoConnections> writers);

    abstract public List<ProducerFacetNoConnections> getProducers();

    abstract public void setProducers(List<ProducerFacetNoConnections> producers);
}
