package com.portfolio.api.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.portfolio.api.models.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtCookiesManager {
  private static final Logger logger = LoggerFactory.getLogger(JwtCookiesManager.class);

  @Value("${app.auth.cookie.path}")
  private String cookiePath;
  @Value("${app.auth.cookie.maxAgeMilliseconds}")
  private int cookieMaxAge;

  @Value("${app.auth.jwt.secret}")
  private String jwtSecret;

  @Value("${app.auth.jwt.expirationMilliseconds}")
  private int jwtExpiration;

  @Value("${app.auth.cookie.name}")
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

  public ResponseCookie createEmptyJwtCookie() {
    return ResponseCookie.from(jwtCookieName, null)
        .path(cookiePath)
        .build();
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateJwtToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(jwtSecret)
          .parseClaimsJws(token);
      // if can be parsed propertly is correct
      return true;
    } catch (SignatureException exception) {
      logger.error("JWT: Invalid signature.", exception.getMessage());
    } catch (MalformedJwtException exception) {
      logger.error("JWT: Invalid token.", exception.getMessage());
    } catch (ExpiredJwtException exception) {
      logger.error("JWT: Expired token.", exception.getMessage());
    } catch (UnsupportedJwtException exception) {
      logger.error("JWT: Unsupported token.", exception.getMessage());
    } catch (IllegalArgumentException exception) {
      logger.error("JWT: Token is empty.", exception.getMessage());
    }
    return false;
  }
}
