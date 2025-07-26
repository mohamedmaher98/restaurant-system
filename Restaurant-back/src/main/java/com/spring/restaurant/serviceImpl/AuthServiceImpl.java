package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.config.jwt.JWTUtils;
import com.spring.restaurant.dto.auth.*;
import com.spring.restaurant.entites.User;
import com.spring.restaurant.repo.UserRepository;
import com.spring.restaurant.serveices.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtils jwtUtils;

	@Override
	public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest)
	{
		AuthResponse authResponse = new AuthResponse();
		if (!isValidEmail(registerRequest.getEmail()))
		{
			authResponse.setMessage("Invalid Email format");
			authResponse.setSuccess(false);
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		if (userRepository.findByUsername(registerRequest.getUsername()).isPresent())
		{
			authResponse.setMessage("Username is already in use");
			authResponse.setSuccess(false);
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}

		if (userRepository.findByEmail(registerRequest.getEmail()).isPresent())
		{
			authResponse.setMessage("Email is already in use");
			authResponse.setSuccess(false);
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		User user = copyUserData(registerRequest);
		userRepository.save(user);

		authResponse.setMessage("User registered successfully");
		authResponse.setSuccess(true);
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}

	private User copyUserData(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setPhone(registerRequest.getPhone());
		user.setName(registerRequest.getName());
		return user;
	}

	private boolean isValidEmail(String email)
	{
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email.matches(regex);
	}

	@Override
	public ResponseEntity<AuthResponse> login(LoginRequest loginRequest)
	{
		authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());
		User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		Map<String, Object> jwtMap = jwtUtils.generateToken(userDetails.getUsername());
		Object token = jwtMap.get("access_token");

		return ResponseEntity.ok(new AuthResponse(token.toString(), user.getUsername(), user.getEmail(), "Login successful", true));
	}
}
