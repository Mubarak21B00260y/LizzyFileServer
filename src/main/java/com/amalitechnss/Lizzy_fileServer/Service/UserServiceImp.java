package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Entity.UserVerificationToken;
import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;
import com.amalitechnss.Lizzy_fileServer.Model.UserDTO;
import com.amalitechnss.Lizzy_fileServer.Repository.UserRepository;
import com.amalitechnss.Lizzy_fileServer.Repository.UserVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImp implements UserService{
private  UserRepository userRepository;
 private  UserDTO userDTO;
 private PasswordEncoder passwordEncoder;
private ApplicationEventPublisher applicationEventPublisher;
private UserVerificationTokenRepository userVerificationTokenRepository;

    public UserServiceImp(UserDTO userDTO, UserRepository userRepository ,PasswordEncoder passwordEncoder ,ApplicationEventPublisher applicationEventPublisher,UserVerificationTokenRepository userVerificationTokenRepository) {
        this.userDTO = userDTO;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.applicationEventPublisher=applicationEventPublisher;
        this.userVerificationTokenRepository=userVerificationTokenRepository;
    }


    public User RegisterUser (UserDTO userDTO) throws IOException {

        User  user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

       applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user,""));

        return user;
    }



    public void  SaveVerificationTokensForUser( String token, User user) {
        UserVerificationToken  userVerificationToken= new UserVerificationToken(token, user);

userVerificationTokenRepository.save(userVerificationToken);

    }

}
