package com.amalitechnss.Lizzy_fileServer.Authentication;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@RequiredArgsConstructor
@Service

public class JwtService {
    @Value("${jwt.secret}")
    private   String SecretKey;

//    public String generateToken(UserDetails userDetails) {
//
//  return  generateToken(new HashMap<>(), userDetails);
//    }

    public   String generateToken(Map<String,Object>claims ,UserDetails userDetails){

        return  buildToken(claims,userDetails);
    }

    private String buildToken(Map<String, Object> ExtraClaims, UserDetails userDetails) {
        List<String> authorities= userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Date issuedAt= new Date(System.currentTimeMillis());
        Date expires= new Date(issuedAt.getTime()+15*60*1000);
        return   Jwts.builder().claims(ExtraClaims).subject(userDetails.getUsername()).issuedAt(issuedAt).expiration(expires).claim("authorities",authorities) .signWith(generateKey()).compact();
    }

    private javax.crypto.SecretKey generateKey() {

        byte[] KeyInBite= Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(KeyInBite);
    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private <T> T  extractClaim(String token, Function<Claims,T> claimResolver) {
  Claims claims  = extractAllClaims(token);
      return  claimResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {

         return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }


    public boolean validToken(String token, UserDetails userDetails) {
        final String username= extractUsername(token);

        return  username.equals(userDetails.getUsername())&& !IsExpired(token);

    }

    private boolean IsExpired(String token) {
        return extractExpiration(token).before( new  Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
