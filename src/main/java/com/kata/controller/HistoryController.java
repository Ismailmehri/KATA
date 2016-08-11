package com.kata.controller;

import com.kata.model.History;
import com.kata.services.HistoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * the History Controller
 * @author Ismail MEHRI
 */
@RestController
@RequestMapping("/history")
public class HistoryController
{
    @Autowired
    HistoryService historyService;
    
    /**
     * Return a list of {@link History}
     * @param id the user id
     * @return a list of {@link History}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<History>> getAllUserHistory(
            @PathVariable Long id)
    {
        return ResponseEntity.ok()
                .body(historyService.getAllUserTransactionsHistory(id, null));
    }
}
