package com.portfolio.api.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.portfolio.api.models.User;
import com.portfolio.api.services.UserDetailsServiceImplementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtCookiesManager {
  private static final Logger logger = LoggerFactory.getLogger(JwtCookiesManager.class);

  private final String cookiePath = "/api/auth";
  private final int cookieMaxAge = 24 * 60 * 60;

  @Value("app.auth.jwtsecret")
  private String jwtSecret;

  @Value("app.auth.jwtExpiration")
  private int jwtExpiration;

  @Value("app.auth.jwtCookieName")
  private String jwtCookieName;

  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookieName);
    if (cookie == null)
      return null;
    return cookie.getValue();
  }

  public ResponseCookie createJwtCookie(User user) {
    return ResponseCookie.from(jwtCookieName, createTokenFromUsername(user.getUsername()))
        .path(cookiePath)
        .maxAge(cookieMaxAge)
        .httpOnly(true)
        .build();
  }

  public String createTokenFromUsername(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

}
