package com.kata.controller;

import com.kata.Exceptions.AccountException;
import com.kata.model.User;
import com.kata.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user controller
 * @author Ismail MEHRI
 */

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;
    
    /**
     * Create user account
     * @param user the user
     * @return
     * @throws AccountException  on error
     */
    @RequestMapping(method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save( @RequestBody User user)
            throws AccountException
    {
        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return ResponseEntity.ok().body(createdUser);
        }
    }
    
    /**
     * Return the user
     * @param login the user login
     * @return the user
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@PathVariable String login)
    {
        User user = userService.getUser(login);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
