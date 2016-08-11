package com.kata.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * {@link History} model class
 * @author Ismail Mehri
 */

@Entity 
@Table(name="T_HISTORY") 
public class History implements Serializable
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private User client;
    
    @Column(name = "OPERATION_TYPE")
    @Enumerated(EnumType.STRING)
    private Operation operationType;
    
    @Column(name = "OPERATION_DATE")
    private Date operationDate;
    
    @Column(name = "AMOUNT")
    private double amount;
    
    @Column(name = "BALANCE")
    private double balance;

    public History()
    {
        //default contructor
    }
    
    public History(User client, Operation operationType, double amount,
            double balance)
    {
        this.client = client;
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
        this.operationDate = new Date();
    }

    /**
     * Return the history id
     * @return the history id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * set the history id
     * @param id 
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * return the client
     * @return the client
     */
    public User getClient()
    {
        return client;
    }

    /**
     * Set the client
     * @param client the client
     */
    public void setClient(User client)
    {
        this.client = client;
    }

    /**
     * get the operation type
     * @return 
     */
    public Operation getOperationType()
    {
        return operationType;
    }

    /**
     * Set the operation type
     * @param operationType 
     */
    public void setOperationType(Operation operationType)
    {
        this.operationType = operationType;
    }

    /**
     * return the operation date
     * @return 
     */
    public Date getOperationDate()
    {
        return operationDate;
    }

    /**
     * set the operation date
     * @param operationDate 
     */
    public void setOperationDate(Date operationDate)
    {
        this.operationDate = operationDate;
    }

    /**
     * return the amount
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * set the amount
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * return the balance
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * set the balance
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
