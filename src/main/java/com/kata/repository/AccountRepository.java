/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kata.repository;

import com.kata.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ismail MEHRI
 */
public interface AccountRepository extends JpaRepository<Account, Long> 
{
    @Query("select a from Account a where a.client.id = :clientId")
    public Account findClient(@Param("clientId")Long clienId);

}
