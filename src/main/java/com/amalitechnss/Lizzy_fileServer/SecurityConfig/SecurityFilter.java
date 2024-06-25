package com.amalitechnss.Lizzy_fileServer.SecurityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                   // auth.requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll();
                   // auth.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
                    auth.requestMatchers( "/error").permitAll();
//                    auth.requestMatchers( "api/get/all").hasAnyAuthority("USER");
                    auth.requestMatchers("api/get/all").authenticated();
                    auth.requestMatchers("/api/upload/single").authenticated();
                    auth.requestMatchers("api/share/file/{filename}").authenticated();
                    auth.requestMatchers("api/account/resetPassword").authenticated();
                    auth.requestMatchers("api/account/forgotPassword").permitAll();
                    auth.requestMatchers("api/account/ConfirmAccountRecovery").permitAll();
                    auth.requestMatchers("api/account/setPassword").permitAll();
                    //todo: add the additional routes;
                    auth.anyRequest().permitAll();
                });
        return httpSecurity.build();
    }
}
