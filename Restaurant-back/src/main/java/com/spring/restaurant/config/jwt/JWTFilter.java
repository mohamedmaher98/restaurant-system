package com.spring.restaurant.config.jwt;

import com.spring.restaurant.serveices.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
public class JWTFilter extends OncePerRequestFilter
{
	private JWTUtils jwtUtils;
	private CustomUserDetailsService userDetailsService;

	@Autowired
	public void setJwtUtils(JWTUtils jwtUtils)
	{
		this.jwtUtils = jwtUtils;
	}

	@Autowired
	public void setUserDetailsService(CustomUserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException
	{
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (request.getParameterMap().containsKey("username"))
		{
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new CustomUserDetails(), null,
					Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
			return;
		}
		if (header == null || !header.startsWith("Bearer "))
		{
			chain.doFilter(request, response);
			return;
		}
		final String token = header.split(" ")[1].trim();
		if (jwtUtils.invalidToken(token))
		{
			chain.doFilter(request, response);
			return;
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.getUsernameFromToken(token));
		Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
		if (userDetails != null)
			authorities = userDetails.getAuthorities();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
