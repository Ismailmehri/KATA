package com.kata.services;

import com.kata.Exceptions.AccountException;
import com.kata.TransactionalIntegrationTest;
import com.kata.model.Account;
import com.kata.model.Profile;
import com.kata.model.User;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * UserService Tests
 * @author Ismail Mehri
 */
@Sql(value = "classpath:com/kata/userServiceTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:com/kata/emptyDatabase.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServicesTest extends TransactionalIntegrationTest
{
    @Autowired
    UserService userService;
    
    @Autowired
    AccountService accountService;
    
    @Test
    public void should_create_an_account_when_user_has_been_created()
            throws AccountException
    {
        //Given
        Long userId = 2L;
        Account userAccount = accountService.getUserAccount(userId);
        Assert.assertNull(userAccount);
        
        //When
        User user = new User();
        user.setId(userId);
        user.setLogin("login");
        userService.createUser(user);
        
        //Then
        userAccount = accountService.getUserAccount(userId);
        Assert.assertNotNull(userAccount);
        Assert.assertEquals(userId, userAccount.getClient().getId());
    }
    
    @Test(expected = AccountException.class)
    public void should_not_create_user_when_user_has_been_exist()
            throws AccountException
    {
        //Given
        String login = "login1";
        User expectedUser = userService.getUser(login);
        Assert.assertNotNull(expectedUser);
        Assert.assertEquals(login, expectedUser.getLogin());
        
        //When
        expectedUser.setId(5L);
        userService.createUser(expectedUser);
    }
    
    @Test
    public void should_return_user_by_login()
    {
        //Given
        String login = "login1";
        String expectedFirstName = "userFirstName";
        String expectedLastName = "userLastNAme";
        String expectedPassword = "0000";
        Profile expectedProfile = Profile.ADMIN;
        
        //When
        User user = userService.getUser(login);
        
        //Then
        Assert.assertEquals(user.getLogin(), login);
        Assert.assertEquals(user.getFirstName(), expectedFirstName);
        Assert.assertEquals(user.getLastName(), expectedLastName);
        Assert.assertEquals(user.getPassword(), expectedPassword);
        Assert.assertEquals(user.getProfile(), expectedProfile);
    }
}
