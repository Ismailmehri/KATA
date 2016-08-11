package com.kata.services;

import com.kata.model.History;
import com.kata.model.Operation;
import com.kata.model.User;
import java.util.List;

/**
 * @author Ismail MEHRI
 */
public interface HistoryService
{   
    /**
     * Save the deposit transaction
     *
     * @param user the {@link User}
     * @param balance the current balance
     * @param amount the amount
     */
    public void addDepositHistory(User user, double balance, double amount);
    
    /**
     * Save the Withdraw transaction
     *
     * @param user the {@link User}
     * @param balance the current balance
     * @param amount the amount
     */
    public void addWithdrawHistory(User user, double balance, double amount);
    
    /**
     * Return a {@link List} of {@link User} history
     * @param userId the {@link User} ID
     * @param operationType the Operation type
     * @return a {@link List} of {@link User} history
     */
    public List<History> getAllUserTransactionsHistory(Long userId,
            Operation operationType);
}
