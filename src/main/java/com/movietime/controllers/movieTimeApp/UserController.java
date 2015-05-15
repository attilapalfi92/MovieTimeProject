package com.movietime.controllers.movieTimeApp;

import com.movietime.businessLogicLayer.UserBusinessLogic;
import com.movietime.exceptions.EmailUsedException;
import com.movietime.exceptions.PersistingFailedException;
import com.movietime.exceptions.UsernameUsedException;
import com.movietime.entities.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Attila on 2015-05-12.
 */
@Controller
public class UserController {
    @Autowired
    UserBusinessLogic userBusinessLogic;

    @RequestMapping(value = "/rest/movieTimeRegister", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<String> register(@RequestBody UsersEntity user) {

        try {
            userBusinessLogic.saveNewUser(user);
            return new ResponseEntity<>(user.getUserName(), HttpStatus.CREATED);

        } catch (UsernameUsedException u) {
            return new ResponseEntity<>("Username is used.", HttpStatus.BAD_REQUEST);

        } catch (EmailUsedException e) {
            return new ResponseEntity<>("Email is used.", HttpStatus.BAD_REQUEST);

        } catch (PersistingFailedException p) {
            return new ResponseEntity<>("Fill every data.", HttpStatus.BAD_REQUEST);
        }
    }

}
