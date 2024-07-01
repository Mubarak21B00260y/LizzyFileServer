package com.amalitechnss.Lizzy_fileServer.Authentication;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Repository.AccountVerificationTokenRepository;
import com.amalitechnss.Lizzy_fileServer.Entity.AccountVerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
public class AccountTokenService {
private  final AccountVerificationTokenRepository accountVerificationTokenRepository;
    public String CreateAndSaveVerificationToken(User user) {
        var verificationToken= generateVerificationToken();
        AccountVerificationToken accountVerificationToken= new  AccountVerificationToken();
        accountVerificationToken.setToken(verificationToken);
        accountVerificationToken.setCreatedAt(LocalDateTime.now());
        accountVerificationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        accountVerificationToken.setUser(user);
        accountVerificationTokenRepository.save(accountVerificationToken);

        return verificationToken;
    }



    private String generateVerificationToken() {
        String pattern= "0123456789";
        StringBuilder TokenBuilder=new StringBuilder();
        SecureRandom secureRandom= new SecureRandom();
        for (int i = 0; i< 6; i++) {
            int RandomNum= secureRandom.nextInt(pattern.length());
            TokenBuilder.append(RandomNum);
        }
        return TokenBuilder.toString();
    }
}
