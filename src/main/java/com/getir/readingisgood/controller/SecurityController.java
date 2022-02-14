package com.getir.readingisgood.controller;

import com.getir.readingisgood.config.security.SecurityProperties;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/token")
public class SecurityController {

  @Autowired
  private SecurityProperties securityProperties;

  @PostMapping
  public String create(@RequestParam String username,
                         @RequestParam String password) {
    if (username.equals(securityProperties.getUsername()) && password.equals(securityProperties.getPassword())) {
      return getJWTToken(username);
    } else {
      throw new UsernameNotFoundException("Username or password is not valid");
    }
  }

  private String getJWTToken(String username) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER");
    String token = Jwts.builder().setId("id").setSubject(username)
        .claim("authorities", grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getExpirationTimeMillis()))
        .signWith(SignatureAlgorithm.HS512, securityProperties.getSecretKey().getBytes()).compact();
    return "Bearer token: " + token;
  }
}
