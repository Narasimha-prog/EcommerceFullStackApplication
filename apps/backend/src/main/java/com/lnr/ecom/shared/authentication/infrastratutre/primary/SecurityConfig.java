package com.lnr.ecom.shared.authentication.infrastratutre.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain configure(HttpSecurity  http) throws Exception{

  return  http.authorizeHttpRequests(authroze-> authroze.requestMatchers(HttpMethod.GET,"api/categories","api/products-shop/**").permitAll()
      .requestMatchers("/api/**").authenticated()
      .anyRequest().permitAll())
      .csrf(AbstractHttpConfigurer::disable)
      .oauth2ResourceServer(oath2->oath2.jwt(jwt->jwt.jwtAuthenticationConverter(new KindeJwtAutoticationConverter())))
      .build();

  }
}
