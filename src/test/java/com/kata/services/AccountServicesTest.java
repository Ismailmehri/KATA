package com.kata.services;

import com.kata.Exceptions.AccountException;
import com.kata.TransactionalIntegrationTest;
import com.kata.model.Account;
import com.kata.model.History;
import com.kata.model.Operation;
import com.kata.model.User;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * AccountServices Tests
 * @author Ismail MEHRI
 */
@Sql(value = "classpath:com/kata/accountServiceTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:com/kata/emptyDatabase.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AccountServicesTest extends TransactionalIntegrationTest
{
    @Autowired
    AccountService accountService;
    
    @Autowired
    HistoryService historyService;
        
    @Test
    public void should_create_an_account() throws AccountException
    {
        //Given
        Long clientId = 1L;
        User u = new User();
        u.setId(clientId);
        
        Account a = new Account();
        a.setId(1L);
        a.setClient(u);
        a.setBalance(1200.5);
        
        //When
        Account userAccount = accountService.createAccount(a);
        
        //Then
        Assert.assertNotNull(userAccount);
        Assert.assertEquals(clientId, userAccount.getClient().getId());
    }
    
    @Test(expected = AccountException.class)
    public void should_not_create_an_account_when_client_has_one()
            throws AccountException
    {
        //Given
        User u = new User();
        u.setId(1L);
        
        Account a = new Account();
        a.setId(1L);
        a.setClient(u);
        a.setBalance(1200.5);
        
        //When
        // create the first account
        accountService.createAccount(a);
        //create the second account
        a.setId(2L);
        accountService.createAccount(a);
    }
    
    @Test
    public void should_make_a_deposit() throws AccountException
    {
        //Given
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        double expected = 250.5;
        Assert.assertEquals(expected, userAccount.getBalance(), 0);
        
        //When
        double amount = 20.2;
        accountService.deposit(2L, amount);
        
        //then
        userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        expected = expected + amount;
        Assert.assertEquals(expected, userAccount.getBalance(), 0);
    }
    
    @Test(expected = AccountException.class)
    public void should_not_make_a_deposit_when_user_has_not_an_account()
            throws AccountException 
    {
        //Given
        Account userAccount = accountService.getUserAccount(1L);
        Assert.assertNull(userAccount);
        
        //When
        accountService.deposit(1L, 25.5);
    }
    
    @Test(expected = AccountException.class)
    public void should_not_make_a_deposit_when_amount_less_than_zero()
            throws AccountException 
    {
        //Given
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        
        //When
        accountService.deposit(1L, -25.5);
    }
    
    @Test
    public void should_make_a_withdrawal() throws AccountException
    {
        //Given
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        double expected = 250.5;
        Assert.assertEquals(expected, userAccount.getBalance(), 0);
        
        //When
        double amount = 20.2;
        accountService.withdraw(2L, amount);
        
        //then
        userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        expected = expected - amount;
        Assert.assertEquals(expected, userAccount.getBalance(), 0);
    }
    
    @Test(expected = AccountException.class)
    public void should_not_make_a_withdrawal_when_user_has_not_an_account()
            throws AccountException 
    {
        //Given
        Account userAccount = accountService.getUserAccount(1L);
        Assert.assertNull(userAccount);
        
        //When
        accountService.withdraw(1L, 25.5);
    }
    
    @Test(expected = AccountException.class)
    public void should_not_make_a_withdrawal_when_amount_less_than_zero()
            throws AccountException 
    {
        //Given
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        
        //When
        accountService.withdraw(1L, -25.5);
    }
    
    @Test
    public void should_save_tansaction_when_user_make_a_deposit()
            throws AccountException
    {
        //Given
        List<History> allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(2L, null);
        Assert.assertEquals(0, allUserTransactionsHistory.size());
        
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        double expectedAmount = 20.2;
        double expectedBalance = userAccount.getBalance() + expectedAmount;
        
        //When
        accountService.deposit(2L, expectedAmount);
        
        //then
        allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(2L, null);
        Assert.assertEquals(1, allUserTransactionsHistory.size());
        History userTransactionHistory = allUserTransactionsHistory.get(0);
        
        Assert.assertEquals(userTransactionHistory.getAmount(),
                expectedAmount, 0);
        
        Assert.assertEquals(userTransactionHistory.getBalance(),
        expectedBalance, 0);
        
        Assert.assertEquals(userTransactionHistory.getOperationType(),
        Operation.DEPOSIT);
        
        Assert.assertEquals(2L, userTransactionHistory.getClient().getId(), 0);
    }
    
    @Test
    public void should_save_tansaction_when_user_make_a_withdrawal()
            throws AccountException
    {
        //Given
        List<History> allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(2L, null);
        Assert.assertEquals(0, allUserTransactionsHistory.size());
        
        Account userAccount = accountService.getUserAccount(2L);
        Assert.assertNotNull(userAccount);
        double expectedAmount = 20.2;
        double expectedBalance = userAccount.getBalance() - expectedAmount;
        
        //When
        accountService.withdraw(2L, expectedAmount);
        
        //then
        allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(2L, null);
        Assert.assertEquals(1, allUserTransactionsHistory.size());
        History userTransactionHistory = allUserTransactionsHistory.get(0);
        
        Assert.assertEquals(userTransactionHistory.getAmount(),
                expectedAmount, 0);
        
        Assert.assertEquals(userTransactionHistory.getBalance(),
        expectedBalance, 0);
        
        Assert.assertEquals(userTransactionHistory.getOperationType(),
        Operation.WITHDRAWAL);
        
        Assert.assertEquals(2L, userTransactionHistory.getClient().getId(), 0);
    }
}
