package com.kata.repository;

import com.kata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author imehri
 */
public interface UserRepository extends JpaRepository<User, Long> 
{
    @Query("select u from User u where u.login = :login")
    public User findUserByLogin(@Param("login") String login);
}
