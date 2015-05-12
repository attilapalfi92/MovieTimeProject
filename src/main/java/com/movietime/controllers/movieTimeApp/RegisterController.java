package com.movietime.controllers.movieTimeApp;

import com.movietime.model.UsersEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Attila on 2015-05-12.
 */
@Controller
public class RegisterController {

    @RequestMapping(value = "/rest/movieTime/register", method = RequestMethod.POST)
    HttpEntity<UsersEntity> register(@RequestBody UsersEntity user) {


        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
