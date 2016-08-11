package com.kata.controller;

import com.kata.Exceptions.AccountException;
import com.kata.model.Account;
import com.kata.services.AccountService;

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
 * The account controller
 * @author Ismail MEHRI
 */
@RestController
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    private AccountService accountService;
    
    /**
     * return the user {@link Account}
     * @param id the user id
     * @return the user {@link Account}
     */
    @RequestMapping(value = "/byUserId/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> get(@PathVariable Long id)
    {
        Account userAccount = accountService.getUserAccount(id);
        if (userAccount != null) {
            return ResponseEntity.ok().body(userAccount);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Make a deposit
     * @param id the user id
     * @param amount the amount
     * @return 
     */
    @RequestMapping(value = "/deposit/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Void> deposit(@PathVariable Long id,
            @RequestBody double amount)
    {
        try {
            accountService.deposit(id, amount);
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Make a withdraw
     * @param id the user id
     * @param amount the amount
     * @return 
     */
    @RequestMapping(value = "/withdraw/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Void> withdraw(@PathVariable Long id,
            @RequestBody double amount)
    {
        try {
            accountService.withdraw(id, amount);
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
