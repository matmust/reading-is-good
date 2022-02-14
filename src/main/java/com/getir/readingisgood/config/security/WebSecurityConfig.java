package com.getir.readingisgood.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(prefix = "security", value = "enabled", havingValue = "true")
@Import( {SecurityProperties.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs",
        "/h2-console/**",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/webjars/**","/csrf");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .csrf().disable()
        .addFilterAfter(new JWTAuthorizationFilter(securityProperties), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/token").permitAll()
        .anyRequest().authenticated();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    if (null != securityProperties.getCorsConfiguration()) {
      source.registerCorsConfiguration("/**", securityProperties.getCorsConfiguration());
    }
    return source;
  }

}
