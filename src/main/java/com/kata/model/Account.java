package com.kata.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * {@link Account} model class
 * @author Ismail Mehri
 */

@Entity 
@Table(name="T_ACCOUNT") 
public class Account implements Serializable
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private User client;
    
    @Column(name = "BALANCE")
    private double balance;

    /**
     * return the account id
     * @return the account id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Set the account id
     * @param id 
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Return the client
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
     * return the balance
     * @return  the balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Set the balance
     * @param balance the balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
