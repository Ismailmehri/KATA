package com.kata.repository;

import com.kata.model.History;
import com.kata.model.Operation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author imehri
 */
public interface HistoryRepository extends JpaRepository<History, Long> 
{
    @Query("select h from History h where h.client.id = :clientId "
            + "and (:opType is null or h.operationType = :opType)")
    public List<History> findUserTranscationsHistory(
            @Param("clientId")Long clientId, @Param("opType") Operation opType);
}
