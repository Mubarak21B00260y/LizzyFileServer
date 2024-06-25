package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
   private final  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User  user = userRepository.findByEmail(email);
          if(user==null) {
              throw  new RuntimeException("user not found");
          }
          return user;
    }
}
