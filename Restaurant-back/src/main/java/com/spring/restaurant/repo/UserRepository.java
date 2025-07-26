package com.spring.restaurant.repo;

import com.spring.restaurant.entites.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface UserRepository extends JpaRepository<User, UUID>
{
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier")
	Optional<User> findByUsernameOrEmail(@Param("identifier") String identifier);
}
