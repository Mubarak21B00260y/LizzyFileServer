package com.amalitechnss.Lizzy_fileServer.Authentication;

import com.amalitechnss.Lizzy_fileServer.Entity.Role;
import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;
import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.InvalidAccountVerificationException;

import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.UserAlreadyExistsException;
import com.amalitechnss.Lizzy_fileServer.Repository.AccountVerificationTokenRepository;
import com.amalitechnss.Lizzy_fileServer.Repository.RoleRepository;
import com.amalitechnss.Lizzy_fileServer.Repository.UserRepository;
import com.amalitechnss.Lizzy_fileServer.Requests.RegisterRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.ResetPasswordRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.SetNewPasswordRequest;
import com.amalitechnss.Lizzy_fileServer.Service.EmailService;
import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;
import com.amalitechnss.Lizzy_fileServer.User.AccountVerificationToken;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ApplicationEventPublisher eventPublisher;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AccountVerificationTokenRepository accountVerificationTokenRepository;
    private final RoleRepository roleRepository;
    private final AccountTokenService accountTokenService;
    private final EmailService emailService;
    


    public AuthenticationResponse Login(@Validated AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        var auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = (User) auth.getPrincipal();

        var claims = new HashMap<String, Object>();
        claims.put("full name", user.getFullName());
        String JwtToken = jwtService.generateToken(claims, user);
        return new AuthenticationResponse(JwtToken);

    }

    public void RegisterUser(RegisterRequest request) {
        Optional<Role> roleList = roleRepository.findByName("USER");
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder().encode(request.getPassword()));
        user.setRoles(roleList.stream().toList());
        user.setEnabled(false);
        user.setAccountLocked(false);

          var  optionalUser  = userRepository.findByEmail(request.getEmail());
          if (optionalUser!=null){
              throw  new RuntimeException(" User with this email  already exists , please go back to the  login page to login");

          }

        userRepository.save(user);

        eventPublisher.publishEvent(new RegistrationCompleteEvent(user, request.getEmail()));
    }

    public void ConfirmRegistration(String token) {

        AccountVerificationToken savedToken = accountVerificationTokenRepository.findByToken(token);
        if (savedToken == null) {
            throw new RuntimeException(" invalid token");
        }

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {

            eventPublisher.publishEvent(new RegistrationCompleteEvent(savedToken.getUser(), savedToken.getUser().getEmail()));
            throw new RuntimeException(" token has expired, a new token has been sent to the same email");
        }

        Optional<User> optionalUser = userRepository.findById(savedToken.getUser().getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            savedToken.setValidatedAt(LocalDateTime.now());
            accountVerificationTokenRepository.save(savedToken);
            userRepository.save(user);
        }
    }

    public void ResetPassword(ResetPasswordRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder().matches(request.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException(" incorrect old password");
        }
        user.setPassword(passwordEncoder().encode(request.getNewPassword()));
        userRepository.save(user);

    }

    public void ForgotPassword(String email) throws MessagingException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(" user not found");
        }
        var token = accountTokenService.CreateAndSaveVerificationToken(user);
        emailService.SendAccountRecoveryEmail(user.getEmail(), user.getFullName(), EmailTemplate.Reset_Password, token, "Password Reset");
    }

    //    this is to take the token directly from the front end , validate it and send the response to the front end ,
    // so the front end can also perform a validation before redirecting user to enter a new password page.
    public void ConfirmAccountRecovery(String token) throws MessagingException {

        AccountVerificationToken savedToken = accountVerificationTokenRepository.findByToken(token);

        if (savedToken == null) {
            throw new InvalidAccountVerificationException("invalid token");
        }
        if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            //  I am only using  the token for accountVerificationToken  since  I don't want to create another   ForgotPasswordResetToken ,and basically rewrite the same implementation again.
            var resendToken = accountTokenService.CreateAndSaveVerificationToken(savedToken.getUser());
            emailService.SendAccountRecoveryEmail(savedToken.getUser().getEmail(), savedToken.getUser().getFullName(), EmailTemplate.Reset_Password, resendToken, "Password Reset");
            throw new InvalidAccountVerificationException(" token expired, a new token has been sent to the same email");
        }
    }

    // finally I update the password, I still take the token from the front end ,  but only to fetch the user from it
    public void SetPassword(SetNewPasswordRequest request) {
        AccountVerificationToken savedToken = accountVerificationTokenRepository.findByToken(request.getToken());
        if (savedToken == null) {
            throw new RuntimeException("USER NOT FOUND");
        }
        User user = savedToken.getUser();
        user.setPassword(passwordEncoder().encode(request.getNewPassword()));
        userRepository.save(user);


    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


