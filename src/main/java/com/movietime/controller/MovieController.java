package com.movietime.controller;

import com.movietime.dataservices.DataServices;
import com.movietime.model.MoviesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Attila on 2015-04-06.
 */
@Controller
public class MovieController {

    @Autowired
    private DataServices dataServices;

    @Transactional
    @RequestMapping("/hello")
    public ModelAndView welcomePage() {

        List<MoviesEntity> movieResults = dataServices.findMoviesByTitle("Eternal sunshine of");
        System.out.println(movieResults.get(0));

        return new ModelAndView("hello");
    }
}
