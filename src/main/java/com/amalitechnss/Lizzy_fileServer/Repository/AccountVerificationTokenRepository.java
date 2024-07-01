package com.amalitechnss.Lizzy_fileServer.Repository;

import com.amalitechnss.Lizzy_fileServer.Entity.AccountVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountVerificationTokenRepository extends JpaRepository<AccountVerificationToken,Long> {
    AccountVerificationToken findByToken(String  token);
}
