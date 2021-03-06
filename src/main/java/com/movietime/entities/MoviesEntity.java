package com.movietime.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Entity
@Table(name = "movies", schema = "", catalog = "movietime2")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieid")
public class MoviesEntity extends ResourceSupport {
    private int movieId;
    private String title;
    private String year;
    private String imdbId;
    private TaglinesEntity tagline;
    //@JsonIgnore
    //private List<ActorsEntity> actors;
    @JsonIgnore
    private List<WritersEntity> writers;
    @JsonIgnore
    private List<ProducersEntity> producers;
    @JsonIgnore
    private List<Movies2ActorsEntity> characters;
    @JsonIgnore
    private List<MpaaratingsEntity> mpaaRatings;
    @JsonIgnore
    private List<GenresEntity> genres;
    @JsonIgnore
    private List<DirectorsEntity> directors;
    @JsonIgnore
    private List<EditorsEntity> editors;
    @JsonIgnore
    private List<KeywordsEntity> keywords;
    @JsonIgnore
    private List<LanguageEntity> languages;
    @JsonIgnore
    private List<LocationsEntity> locations;
    @JsonIgnore
    private QuotesEntity quote;
    @JsonIgnore
    private RatingsEntity rating;
    @JsonIgnore
    private List<ReleasedatesEntity> releaseDates;
    @JsonIgnore
    private List<RunningtimesEntity> runningTimes;
    @JsonIgnore
    private PlotsEntity plot;

    /*
    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "movies2actors", catalog = "movietime2", schema = "", joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false), inverseJoinColumns = @JoinColumn(name = "actorid", referencedColumnName = "actorid", nullable = false))
    @Transient
    public List<ActorsEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorsEntity> actors) {
        this.actors = actors;
    }
    */

    @Id
    @Column(name = "movieid", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieid) {
        this.movieId = movieid;
    }

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 400)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "year", nullable = true, insertable = true, updatable = true, length = 100)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "imdbid", nullable = true, insertable = true, updatable = true, length = 10)
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbid) {
        this.imdbId = imdbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesEntity that = (MoviesEntity) o;

        if (movieId != that.movieId) return false;
        if (imdbId != null ? !imdbId.equals(that.imdbId) : that.imdbId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (imdbId != null ? imdbId.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies2writers", catalog = "movietime2", schema = "",
            joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "writerid", referencedColumnName = "writerid", nullable = false))
    public List<WritersEntity> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersEntity> writers) {
        this.writers = writers;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies2producers", catalog = "movietime2", schema = "",
            joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "producerid", referencedColumnName = "producerid", nullable = false))
    public List<ProducersEntity> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersEntity> producers) {
        this.producers = producers;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<Movies2ActorsEntity> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Movies2ActorsEntity> characters) {
        this.characters = characters;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    /*@JoinTable(name = "mpaaratings", catalog = "movietime2", schema = "",
        joinColumns = @JoinColumn(name = "movieid", referencedColumnName = "movieid", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "mpaaratings_id", referencedColumnName = "mpaaratings_id", nullable = false))*/
    public List<MpaaratingsEntity> getMpaaRatings() {
        return mpaaRatings;
    }

    public void setMpaaRatings(List<MpaaratingsEntity> mpaaRatings) {
        this.mpaaRatings = mpaaRatings;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresEntity> genres) {
        this.genres = genres;
    }

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    public List<DirectorsEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsEntity> directors) {
        this.directors = directors;
    }

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    public List<EditorsEntity> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorsEntity> editors) {
        this.editors = editors;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<KeywordsEntity> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordsEntity> keywords) {
        this.keywords = keywords;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<LocationsEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsEntity> locations) {
        this.locations = locations;
    }

    @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY)
    public QuotesEntity getQuote() {
        return quote;
    }

    public void setQuote(QuotesEntity quote) {
        this.quote = quote;
    }

    @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY)
    public RatingsEntity getRating() {
        return rating;
    }

    public void setRating(RatingsEntity rating) {
        this.rating = rating;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<ReleasedatesEntity> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleasedatesEntity> releaseDates) {
        this.releaseDates = releaseDates;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    public List<RunningtimesEntity> getRunningTimes() {
        return runningTimes;
    }

    public void setRunningTimes(List<RunningtimesEntity> runningTimes) {
        this.runningTimes = runningTimes;
    }

    @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY)
    public TaglinesEntity getTagline() {
        return tagline;
    }

    public void setTagline(TaglinesEntity tagline) {
        this.tagline = tagline;
    }

    @OneToOne(mappedBy = "movie")
    public PlotsEntity getPlot() {
        return plot;
    }

    public void setPlot(PlotsEntity plot) {
        this.plot = plot;
    }
}
