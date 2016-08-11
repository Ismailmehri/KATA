package com.kata.services.impl;

import com.kata.model.History;
import com.kata.model.Operation;
import com.kata.model.User;
import com.kata.repository.HistoryRepository;
import com.kata.services.HistoryService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link HistoryService}
 * @author Ismail MEHRI
 */
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService
{

    @Autowired
    HistoryRepository historyRepository;
    
    @Override
    public void addDepositHistory(User user, double balance, double amount)
    {
        History depositHistory = new History(user, Operation.DEPOSIT, amount,
                balance);
        historyRepository.save(depositHistory);
    }

    @Override
    public void addWithdrawHistory(User user, double balance, double amount)
    {
        History depositHistory = new History(user, Operation.WITHDRAWAL, amount,
                balance);
        historyRepository.save(depositHistory);
    }

    @Override
    public List<History> getAllUserTransactionsHistory(Long userId,
            Operation operationType)
    {
        List<History> findAll = historyRepository.findAll();
        return historyRepository.findUserTranscationsHistory(userId,
                operationType);
    }
    
}
