package com.getir.readingisgood.config.security;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Data
@Component
@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {
  private String username;
  private String password;
  private int expirationTimeMillis;
  private String secretKey;

  private boolean enabled;
  private Cors cors;
  private String issuerUri;

  public CorsConfiguration getCorsConfiguration() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOriginPatterns(cors.getAllowedOrigins());
    corsConfiguration.setAllowedMethods(cors.getAllowedMethods());
    corsConfiguration.setAllowedHeaders(cors.getAllowedHeaders());
    corsConfiguration.setExposedHeaders(cors.getExposedHeaders());
    corsConfiguration.setAllowCredentials(cors.getAllowCredentials());
    corsConfiguration.setMaxAge(cors.getMaxAge());

    return corsConfiguration;
  }

  @Data
  public static class Cors {

    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;
  }
}
