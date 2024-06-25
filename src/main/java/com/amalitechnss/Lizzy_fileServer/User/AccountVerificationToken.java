package com.amalitechnss.Lizzy_fileServer.User;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private  String token;
    @ManyToOne
    @JoinColumn(name="USER_VERIFICATION_ID", nullable = false)
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

}
