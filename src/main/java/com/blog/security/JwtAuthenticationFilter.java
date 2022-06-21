package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	JwtTokenHelper helper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestToken = request.getHeader("Authorization");

		String token = null;
		String userName = null;
		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				userName = this.helper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				e.getStackTrace();
			} catch (ExpiredJwtException e) {
				e.getStackTrace();
			} catch (MalformedJwtException e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("login not access");
		}

		
		
		
		/* 2 */
		
		if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails details=this.detailsService.loadUserByUsername(userName);
			if(this.helper.validateToken(token, details))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken( details, null,details.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("invalid token");
			}
		} else {
			System.out.println("username is null");
		}
		
		filterChain.doFilter(request, response);
	}

}
