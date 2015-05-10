package com.movietime.controllers.movieTimeApp;

import com.movietime.businesslogic.MovieDataProvider;
import com.movietime.model.PlotsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Attila on 2015-05-10.
 */
@Controller
public class OthersController {
    @Autowired
    MovieDataProvider movieDataProvider;

    /**
     * Returns with the plot of the desired movie.
     * @param movieId ID of the movie who's plot is needed.
     * @return Http response containing the plot in json format.
     */
    @RequestMapping(value = "/rest/movieTime/plot/byMovieId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<PlotsEntity> getPlotForMovie(@PathVariable("id") int movieId) {
        return new ResponseEntity<PlotsEntity>(movieDataProvider.getPlotForMovie(movieId), HttpStatus.OK);
    }
}
