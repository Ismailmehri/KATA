package com.kata.services;

import com.kata.Exceptions.AccountException;
import com.kata.model.Account;

/**
 * 
 * @author Ismail MEHRI
 */
public interface AccountService
{
    /**
     * Create a {@link User} {@link Account} 
     *
     * @param account the {@link User} {@link Account} 
     * @return the created {@link Account} 
     * @throws AccountException on errors
     */
    public Account createAccount(Account account) throws AccountException;
    
    /**
     * Returns the {@link User} account
     *
     * @param login the {@link User} login
     * @return {@link User}
     */
    public Account getUserAccount(String login);
    
        /**
     * Returns the {@link User} account
     *
     * @param id the {@link User} ID
     * @return {@link User}
     */
    public Account getUserAccount(Long id);
    
    /**
     * Make a deposit
     *
     * @param userId the {@link User} ID
     * @param amount the amount, it should be > 0
     * @throws AccountException  on error
     */
    public void deposit(Long userId, double amount) throws AccountException;
    
    /**
     * Make a withdraw
     * @param userId the {@link User} ID
     * @param amount the amount, it should be > 0
     * @throws AccountException 
     */
    public void withdraw(Long userId, double amount) throws AccountException;
}
