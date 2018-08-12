package com.example.webdevsummer22018serverjavamahale816.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavamahale816.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findUserByUserName(@Param("username") String username);
	
	@Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
	public Iterable<User> findUserByCredentials(@Param("username") String u, @Param("password") String p);
}