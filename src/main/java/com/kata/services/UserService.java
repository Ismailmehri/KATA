package com.kata.services;

import com.kata.Exceptions.AccountException;
import com.kata.model.User;

/**
 * @author Ismail MEHRI
 */
public interface UserService
{
    /**
     * Create the {@link User} and the user {@link Account} 
     * @param user the {@link User}
     * @return {@link User}
     * @throws AccountException if {@link User} login has been exist.
     */
    public User createUser(User user) throws AccountException;
    
    /**
     * Return {@link User} by login
     * @param login the {@link User} login
     * @return {@link User}
     */
    public User getUser(String login);
}
