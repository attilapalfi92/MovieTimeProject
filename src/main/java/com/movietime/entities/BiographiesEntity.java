package com.movietime.entities;

import javax.persistence.*;

/**
 * Created by Attila on 2015-04-17.
 */
//public class BiographiesEntity {}

@Entity
@Table(name = "biographies", schema = "", catalog = "movietime2")
public class BiographiesEntity {
    private int bioId;
    private String name;
    private String bioText;
    //@JsonIgnore
    //private ActorsEntity actor;

    @Id
    @Column(name = "bioid", nullable = false, insertable = true, updatable = true)
    public int getBioId() {
        return bioId;
    }

    public void setBioId(int bioid) {
        this.bioId = bioid;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = false, updatable = false, columnDefinition = "varchar(250)", length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "biotext", nullable = true, insertable = true, updatable = true, columnDefinition = "mediumtext", length = 16777215)
    public String getBioText() {
        return bioText;
    }

    public void setBioText(String biotext) {
        this.bioText = biotext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BiographiesEntity that = (BiographiesEntity) o;

        if (bioId != that.bioId) return false;
        if (bioText != null ? !bioText.equals(that.bioText) : that.bioText != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bioId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bioText != null ? bioText.hashCode() : 0);
        return result;
    }

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "name", referencedColumnName = "name", nullable = false)
    /*
    public ActorsEntity getActor() {
        return actor;
    }

    public void setActor(ActorsEntity actor) {
        this.actor = actor;
    }
    */
}
