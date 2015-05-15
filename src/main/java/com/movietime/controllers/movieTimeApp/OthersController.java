package com.movietime.controllers.movieTimeApp;

import com.movietime.businessLogicLayer.OthersBusinessLogic;
import com.movietime.entities.PlotsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Attila on 2015-05-10.
 */
@Controller
public class OthersController {
    @Autowired
    OthersBusinessLogic othersBusinessLogic;

    /**
     * Returns with the plot of the desired movie.
     * @param movieId ID of the movie who's plot is needed.
     * @return Http response containing the plot in json format.
     */
    @RequestMapping(value = "/rest/movieTime/plot/byMovieId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<PlotsEntity> getPlotForMovie(@PathVariable("id") int movieId) {
        return new ResponseEntity<>(othersBusinessLogic.getPlotForMovie(movieId), HttpStatus.OK);
    }

}
