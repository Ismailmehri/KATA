package com.kata.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * {@link User} model class
 * 
 * @author Ismail MEHRI
 */
@Entity 
@Table(name="T_USER") 
public class User implements Serializable
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "PROFILE")
    @Enumerated(EnumType.STRING)
    private Profile profile;

    /**
     * return the user id
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * set the user id
     * @param id the id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Return the first name
     * @return the user first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * set the user first name
     * @param firstName the user first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Return the last name
     * @return the user last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Set the user last name
     * @param lastName the user last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * return the user login
     * @return the user login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * set the user login
     * @param login 
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * return the user password
     * @return the use password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * set the user password
     * @param password the password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * return the profile
     * @return the profile
     */
    public Profile getProfile()
    {
        return profile;
    }

    /**
     * set the profile
     * @param profile  the profile
     */
    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }
}
