package com.movietime.entityfacets;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class WriterFacet {

    protected int writerid;
    protected String name;

    public int getWriterid() {
        return writerid;
    }

    public void setWriterid(int writerid) {
        this.writerid = writerid;
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

        WriterFacet that = (WriterFacet) o;

        if (writerid != that.writerid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = writerid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    abstract public List<MovieFacetNoConnections> getMovies();

    abstract public void setMovies(List<MovieFacetNoConnections> movies);
}
