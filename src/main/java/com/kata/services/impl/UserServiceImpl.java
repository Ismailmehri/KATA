package com.kata.services.impl;

import com.kata.Exceptions.AccountException;
import com.kata.model.Account;
import com.kata.model.User;
import com.kata.repository.UserRepository;
import com.kata.services.AccountService;
import com.kata.services.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link UserService}
 * @author Ismail MEHRI
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AccountService accountService;
    
    @Override
    public User createUser(User user) throws AccountException
    {
        User u = userRepository.findUserByLogin(user.getLogin());
        if (u == null) {
            User newUser = userRepository.save(user);
            accountService.createAccount(createAccoutForUser(newUser));
            return newUser;
        } else {
            throw new AccountException("The login has been exist");
        }
    }

    @Override
    public User getUser(String login)
    {
        return userRepository.findUserByLogin(login);
    }
    
    private Account createAccoutForUser(User user)
    {
        Account account = new Account();
        account.setClient(user);
        account.setBalance(0);
        return account;
    }
}
