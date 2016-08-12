package com.kata.services.impl;

import com.kata.Exceptions.AccountException;
import com.kata.model.Account;
import com.kata.repository.AccountRepository;
import com.kata.services.AccountService;
import com.kata.services.HistoryService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link AccountService}
 * @author Ismail MEHRI
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService
{

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    HistoryService historyService;
    
    @Override
    public Account createAccount(Account account) throws AccountException
    {
        Account userAccount = accountRepository
                .findClient(account.getClient().getId());
        if (userAccount != null) {
            throw new AccountException("The Client has already an account");
        }
        return accountRepository.save(account);
    }

    @Override
    public void deposit(Long userId, double amount) throws AccountException
    {
        Account userAccount = accountRepository.findClient(userId);
        if (userAccount == null) {
            throw new AccountException("The user has not an account");
        }
        if (amount > 0) {
            double balance = userAccount.getBalance();
            userAccount.setBalance(balance + amount);
            accountRepository.save(userAccount);
            historyService.addDepositHistory(userAccount.getClient(),
                    userAccount.getBalance(), amount);
        } else {
            throw new AccountException("The amount should be > 0");
        }
    }

    @Override
    public void withdraw(Long userId, double amount)
            throws AccountException
    {
        Account userAccount = accountRepository.findClient(userId);
        if (userAccount == null) {
            throw new AccountException("The user has not an account");
        }
        if (amount > 0) {
            double balance = userAccount.getBalance();
            userAccount.setBalance(balance - amount);
            accountRepository.save(userAccount);
            historyService.addWithdrawHistory(userAccount.getClient(),
                userAccount.getBalance(), amount);
        } else {
            throw new AccountException("The amount should be > 0");
        }
    }

    @Override
    public Account getUserAccount(String login)
    {
        return accountRepository.findClientAccountByLogin(login);
    }

    @Override
    public Account getUserAccount(Long id) {
        return accountRepository.findClient(id);
    }
}
