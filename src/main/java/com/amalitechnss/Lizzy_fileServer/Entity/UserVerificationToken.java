package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import  java.util.Date;

@NoArgsConstructor
@Entity
@Data
public class UserVerificationToken {
    // minutes it takes for  the tokens to expire set to  10 minutes;
    private  final  int  expirationTime=10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private  String token;
    private  Date expiration;


    @OneToOne()
    @JoinColumn( name="UserVerificationId ", nullable = false, foreignKey = @ForeignKey( name = "F_K_USER_VERIFY_TOKEN"))
    private  User user;


    // two constructors : 1 for when I need to work with the user and token fields, and the other for when I need to work with just the token
    public UserVerificationToken(String token,User user) {
        super();
        this.user=user;
        this.token = token;
        this.expiration= CalculateExpiryTime(expirationTime);
    }

    public UserVerificationToken(String token) {
super();
        this.token = token;
        this.expiration= CalculateExpiryTime(expirationTime);
    }



    private  Date  CalculateExpiryTime (int expirationTime) {

        LocalDateTime PresentTime = LocalDateTime.now();
        LocalDateTime Expire= PresentTime.plusMinutes(expirationTime);
        // converting datetime to  date;
         Date ExpiryTime= Date.from(Expire.atZone(ZoneId.systemDefault()).toInstant());

      return  ExpiryTime;

    }


}
