package com.movietime.businesslogic;

import com.movietime.controllers.ActorRestController;
import com.movietime.controllers.MovieRestController;
import com.movietime.dataservices.*;
import com.movietime.entitywrappers.FullMovieWrapper;
import com.movietime.entitywrappers.MoviePage;
import com.movietime.entitywrappers.SubmittedMovie;
import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Attila on 2015-05-02.
 */
@Service
public class MovieDataProvider {

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private ReleasedateDao releasedateDao;
    @Autowired
    private M2aDao m2aDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private TaglineDao taglineDao;

    /**
     * Finds a movie by it's ID.
     * @param movieId ID of the desired movie.
     * @return The desired movie.
     */
    @Transactional(readOnly = true)
    public FullMovieWrapper getMovieById(int movieId) {
        List<Movies2ActorsEntity> result = movieDao.findMovies2ActorsByMovieId(movieId);
        // getting the actors and setting the roles for them
        List<ActorsEntity> actors = new ArrayList<>(result.size());
        for (Movies2ActorsEntity i : result) {
            ActorsEntity actor = i.getActor();
            actor.setRole(i.getAsCharacter());
            actor.add(linkTo(methodOn(ActorRestController.class).getActorById(actor.getActorId())).withRel("actor"));
            actors.add(i.getActor());
        }

        // setting all the other fields
        FullMovieWrapper fullMovieWrapper = new FullMovieWrapper();
        //MoviesEntity movie = result.get(0).getMovie();
        MoviesEntity movie = movieDao.findMovieById(movieId);
        fullMovieWrapper.setMovie(movie);
        fullMovieWrapper.setActors(actors);
        // kicking the hibernate to fetch the required collections
        movie.getProducers().isEmpty();
        movie.getWriters().isEmpty();
        movie.getDirectors().isEmpty();
        movie.getEditors().isEmpty();
        movie.getKeywords().isEmpty();
        movie.getLanguages().isEmpty();
        movie.getLocations().isEmpty();
        movie.getReleaseDates().isEmpty();
        movie.getGenres().isEmpty();

        //String reasonText = movie.getMpaaRating().getReasonText();

        // setting all the other fields
        fullMovieWrapper.setProducers(movie.getProducers());
        fullMovieWrapper.setWriters(movie.getWriters());
        fullMovieWrapper.setDirectors(movie.getDirectors());
        fullMovieWrapper.setEditors(movie.getEditors());
        fullMovieWrapper.setLanguages(movie.getLanguages());
        fullMovieWrapper.setLocations(movie.getLocations());
        //fullMovieWrapper.setReleaseDates(movie.getReleaseDates());
        fullMovieWrapper.setGenres(movie.getGenres());
        //fullMovieWrapper.setQuote(movie.getQuote());
        fullMovieWrapper.setRating(movie.getRating());
        if (!movie.getRunningTimes().isEmpty())
            fullMovieWrapper.setRunningTime(movie.getRunningTimes().get(0));
        if (!movie.getMpaaRatings().isEmpty())
            fullMovieWrapper.setMpaaRating(movie.getMpaaRatings().get(0));
        fullMovieWrapper.setGenres(movie.getGenres());

        // put a link that points to itself
        fullMovieWrapper.add(linkTo(methodOn(MovieRestController.class).getMovieById(movieId)).withSelfRel());

        return fullMovieWrapper;
    }


    /**
     * Finds a list of movie by the beginning of it's title. Paginated.
     * @param movieTitle Beginning of the title of the desired movie.
     * @param page Which page is needed.
     * @param pageSize Size of each pages.
     * @return A page (list) of movies with the given title beginning.
     */
    @Transactional
    public MoviePage getMoviesByTitle(String movieTitle, int page, int pageSize) {
        List<MoviesEntity> movies = movieDao.findMoviesByTitle(movieTitle, page, pageSize);

        for(MoviesEntity movie : movies) {
            movie.add(linkTo(methodOn(MovieRestController.class).getMovieById(movie.getMovieId())).withRel("movie"));
        }

        MoviePage moviePage = new MoviePage(movies, page, pageSize);
        if (movies.size() >= pageSize)
            moviePage.add(linkTo(methodOn(MovieRestController.class).getMoviesByTitle(movieTitle, page + 1, pageSize)).withRel("nextPage"));
        if(page > 1)
            moviePage.add(linkTo(methodOn(MovieRestController.class).getMoviesByTitle(movieTitle, page - 1, pageSize)).withRel("previousPage"));

        return moviePage;
    }


    /**
     * Finds a list of movie by a part of it's title. Paginated.
     * @param movieTitle Part of the title of the desired movie.
     * @param page Which page is needed.
     * @param pageSize Size of each pages.
     * @return A page (list) of movies with the given title part.
     */
    @Transactional
    public MoviePage getMoviesByPartTitle(String movieTitle, int page, int pageSize) {
        List<MoviesEntity> movies = movieDao.findMoviesByPartTitle(movieTitle, page, pageSize);

        for(MoviesEntity movie : movies) {
            movie.add(linkTo(methodOn(MovieRestController.class).getMovieById(movie.getMovieId())).withRel("movie"));
        }

        MoviePage moviePage = new MoviePage(movies, page, pageSize);
        if (movies.size() >= pageSize)
            moviePage.add(linkTo(methodOn(MovieRestController.class).getMoviesByPartTitle(movieTitle, page + 1, pageSize)).withRel("nextPage"));
        if(page > 1)
            moviePage.add(linkTo(methodOn(MovieRestController.class).getMoviesByPartTitle(movieTitle, page - 1, pageSize)).withRel("previousPage"));

        return moviePage;
    }

    /**
     * Returns with the plot of the movie given by it's ID.
     * @param movieId ID of the movie who's plots is wanted.
     * @return Plot of the movie.
     */
    @Transactional
    public PlotsEntity getPlotForMovie(int movieId) {
        PlotsEntity plot = movieDao.findPlotByMovieId(movieId);

        return plot;
    }


    /**
     * Saves new movie to the database
     * @param movie the new movie to be saved
     * @throws PersistingFailedException When persisting is failed, this exception is thrown.
     */
    @Transactional
    public void saveNewMovie(SubmittedMovie movie) throws PersistingFailedException {
        MoviesEntity moviesEntity = new MoviesEntity();

        // setting the movie title
        moviesEntity.setTitle(movie.getTitle());
        // setting the movie year
        DateFormat df = new SimpleDateFormat("yyyy");
        moviesEntity.setYear(df.format(movie.getRelease().getReleaseDate()));
        // saving the MoviesEntity to the database
        movieDao.saveNewMovie(moviesEntity);

        // saving the ReleaseDate
        ReleasedatesEntity rde = new ReleasedatesEntity();
        rde.setMovie(moviesEntity);
        rde.setReleaseDate(movie.getRelease().getReleaseDate());
        rde.setCountry(movie.getRelease().getCountry());
        rde.setAddition(movie.getRelease().getAddition());
        releasedateDao.saveNewReleaseDate(rde);

        // saving roles
        for (Movies2ActorsEntity m2a : movie.getRoles()) {
            m2a.setMovie(moviesEntity);
            m2aDao.saveNewM2aEntity(m2a);
        }

        // saving genres
        for (String genre : movie.getGenres()) {
            GenresEntity genresEntity = new GenresEntity();
            genresEntity.setMovie(moviesEntity);
            genresEntity.setGenre(genre);
            genreDao.saveNewGenre(genresEntity);
        }

        // saving taglines
        for (String tagline : movie.getTaglines()) {
            TaglinesEntity taglinesEntity = new TaglinesEntity();
            taglinesEntity.setMovie(moviesEntity);
            taglinesEntity.setTaglineText(tagline);
            taglineDao.saveNewTagline(taglinesEntity);
        }

        int i = 0;
        i = i + 1;
    }
}
