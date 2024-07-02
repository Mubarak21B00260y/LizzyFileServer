package com.amalitechnss.Lizzy_fileServer.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@RequiredArgsConstructor
public class SecurityBeansInjector {

    private final AuthenticationConfiguration authenticationConfiguration;
   private final UserDetailsService userDetailsService;


@Bean
public AuthenticationManager authenticationManager () throws Exception {

    return authenticationConfiguration.getAuthenticationManager();
}
@Bean
public AuthenticationProvider authenticationProvider (){
 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
 provider.setUserDetailsService(userDetailsService);
 provider.setPasswordEncoder(passwordEncoder());
 return  provider;


}
    @Bean
    public PasswordEncoder passwordEncoder ()  {
        return  new BCryptPasswordEncoder();
    }


    @Bean
      public CorsFilter corsFilter (){

    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource= new UrlBasedCorsConfigurationSource();
    final CorsConfiguration configuration= new CorsConfiguration();
     configuration.setAllowCredentials(true);
     configuration.setAllowedOrigins(Collections.singletonList("https://lizzy-file-server-2ki1.vercel.app"));
     configuration.setAllowedHeaders(Arrays.asList(HttpHeaders.ORIGIN, HttpHeaders.AUTHORIZATION,HttpHeaders.ACCEPT, HttpHeaders.CONTENT_TYPE));
     configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);

        return  new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
