package com.amalitechnss.Lizzy_fileServer.SecurityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                    auth.requestMatchers( "/api/auth/**").permitAll();


                    auth.requestMatchers( "/error").permitAll();

                    auth.requestMatchers("api/get/all").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                    auth.requestMatchers("/api/upload/single").hasAuthority("ROLE_ADMIN");

                    auth.requestMatchers("api/share/file/{filename}").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers("api/account/resetPassword").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
                    auth.requestMatchers("api/account/forgotPassword").permitAll();
                    auth.requestMatchers("api/account/ConfirmAccountRecovery").permitAll();
                    auth.requestMatchers("api/account/setPassword").permitAll();
                    auth.requestMatchers("api/download/file").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");

                    auth.requestMatchers("api/delete/file/{Id}").hasAnyAuthority("ROLE_ADMIN");
                    auth.requestMatchers("api/edit/file/{Id}").hasAuthority("ROLE_ADMIN");
                    auth.requestMatchers("api/edit/file/{Id}").permitAll();
                    auth.requestMatchers("api/search/file").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
                    auth.anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
