package com.iuh.rencar_project.utils.filter;

import java.io.IOException;

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
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iuh.rencar_project.security.JwtUtils;
import com.iuh.rencar_project.security.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 4:12:19 PM
 * @version 0.1
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String requestTokenHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;

			// Get username from token
			if (StringUtils.hasText(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				username = jwtUtils.getUsernameFromToken(jwtToken);
			}

			// Get username not empty and token not empty
			if (StringUtils.hasText(jwtToken) && StringUtils.hasText(username)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				// Token valid then set authentication
				if (jwtUtils.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, "", userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// The user is authenticated then it pass spring security
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} else {
				System.out.println("No authentication detected");
			}
			// handle exception
		} catch (SignatureException signatureException) {
			request.setAttribute("signatureException", signatureException);
		} catch (MalformedJwtException malformedJwtException) {
			request.setAttribute("malformedJwtException", malformedJwtException);
		} catch (ExpiredJwtException expiredJwtException) {
			request.setAttribute("expiredJwtException", expiredJwtException);
		} catch (UnsupportedJwtException unsupportedJwtException) {
			request.setAttribute("unsupportedJwtException", unsupportedJwtException);
		} catch (IllegalArgumentException e) {
			request.setAttribute("illegalArgumentException", e);
		}
		filterChain.doFilter(request, response);
	}

}
