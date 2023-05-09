package com.portfolio.api.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portfolio.api.services.UserDetailsServiceImplementation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationJwtTokenFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationJwtTokenFilter.class);

  @Autowired
  private JwtCookiesManager jwtCookiesManager;

  @Autowired
  private UserDetailsServiceImplementation userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = jwtCookiesManager.getJwtFromCookies(request);
      if (jwt != null && jwtCookiesManager.validateJwtToken(jwt)) {
        String username = jwtCookiesManager.getUsernameFromJwtToken(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
            null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    } catch (Exception exception) {
      logger.error("User cannot be authenticated.", exception);
    }

    filterChain.doFilter(request, response);
  }

}
