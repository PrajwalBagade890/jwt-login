package com.example.jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
 private final SecretKey key;
 public JwtService(@Value("${jwt.secret:}") String secret){key=secret.isBlank()?Jwts.SIG.HS256.key().build():Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));}
 public String generateToken(String username){long now=System.currentTimeMillis();return Jwts.builder().subject(username).issuedAt(new Date(now)).expiration(new Date(now+3600000)).signWith(key,Jwts.SIG.HS256).compact();}
}
