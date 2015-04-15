package com.movietime.entityfacets;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class ProducerFacet {
    protected int producerid;
    protected String name;

    public int getProducerid() {
        return producerid;
    }

    public void setProducerid(int producerid) {
        this.producerid = producerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerFacet that = (ProducerFacet) o;

        if (producerid != that.producerid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = producerid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    abstract public List<MovieFacetNoConnections> getMovies();

    abstract public void setMovies(List<MovieFacetNoConnections> movies);
}
