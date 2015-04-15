package com.movietime.entityfacets;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class ActorFacet {

    protected int actorid;
    protected String name;
    protected String sex;

    public int getActorid() { return actorid; }

    public void setActorid(int actorid) { this.actorid = actorid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorFacet that = (ActorFacet) o;

        if (actorid != that.actorid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actorid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }

    abstract public List<MovieFacetNoConnections> getMovies();

    abstract public void setMovies(List<MovieFacetNoConnections> movies);
}
