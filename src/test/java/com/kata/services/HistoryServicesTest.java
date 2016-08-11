package com.kata.services;

import com.kata.TransactionalIntegrationTest;
import com.kata.model.History;
import com.kata.model.Operation;
import com.kata.model.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * HistoryServices Tests
 * @author Ismail MEHRI
 */
@Sql(value = "classpath:com/kata/historyServiceTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:com/kata/emptyDatabase.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class HistoryServicesTest extends TransactionalIntegrationTest
{
    @Autowired
    HistoryService historyService;
    
    @Test
    public void should_save_transaction() throws InterruptedException
    {
        //Given
        User user= new User();
        user.setId(1L);
        List<History> allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(user.getId(), null);
        Assert.assertEquals(0, allUserTransactionsHistory.size());
        
        //When
        historyService.addDepositHistory(user, 250.5, 20.2);
        TimeUnit.SECONDS.sleep(5);
        historyService.addWithdrawHistory(user, 250.5, 20.2);
        
        //Then
        allUserTransactionsHistory = historyService
                .getAllUserTransactionsHistory(user.getId(), null);
        Assert.assertEquals(2, allUserTransactionsHistory.size());
        
        List<History> allUserDepositTransactions = historyService
                .getAllUserTransactionsHistory(user.getId(),
                        Operation.DEPOSIT);
        Assert.assertEquals(1, allUserDepositTransactions.size());
        
        List<History>  allUserWiyhdralTransactions = historyService
                .getAllUserTransactionsHistory(user.getId(),
                        Operation.WITHDRAWAL);
        Assert.assertEquals(1, allUserWiyhdralTransactions.size());
        
        Assert.assertTrue(allUserDepositTransactions.get(0).getOperationDate()
                .before(allUserWiyhdralTransactions.get(0).getOperationDate()));
    }
}
