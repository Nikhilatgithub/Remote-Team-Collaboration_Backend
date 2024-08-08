package com.teamCollaboration.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	// will be used for token verification
	// dependency -  JWT utils
	@Autowired
	private JwtTokenProvider utils;

	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {
		// check authorization header from incoming request
		String authHeader = request.getHeader("Authorization");
		String userName=null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// => request header contains JWT , so extract it.
			String jwt = authHeader.substring(7);
			try {
				userName=utils.getUsernameFromToken(jwt);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			UserDetails userDetails = detailsServiceImpl.loadUserByUsername(userName);
			if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
				= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println("saved auth token in sec ctx");
			}
			//Authentication authentication = utils.populateAuthenticationTokenFromJWT(jwt);
			/*
			 * 	save this Authentication object , 
			 * containing - email , user id n granted authorities , 
			 * under spring security context ,  so that subsequent filters will NOT
			 * retry the authentication again (isAuthenticated is already set to true)		
			 */
			
		}
		filterChain.doFilter(request, response);// to continue with remaining chain of spring sec filters

	}

}
