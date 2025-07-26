package com.spring.restaurant.serveices;

import com.spring.restaurant.entites.User;
import com.spring.restaurant.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
	{
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with: " + usernameOrEmail));
		return new CustomUserDetails(user);
	}
}
