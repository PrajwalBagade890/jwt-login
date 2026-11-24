package com.example.jwt;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
public class AuthController {
 private final AuthenticationManager manager; private final JwtService jwt;
 public AuthController(AuthenticationManager manager,JwtService jwt){this.manager=manager;this.jwt=jwt;}
 public record LoginRequest(String username,String password){}
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest request){
  if(request.username()==null || request.username().isBlank() || request.password()==null || request.password().isBlank()) return ResponseEntity.badRequest().body(Map.of("message","Username and password are required"));
  try {var auth=manager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));return ResponseEntity.ok(Map.of("token",jwt.generateToken(auth.getName()),"tokenType","Bearer","expiresIn",3600));}
  catch(AuthenticationException e){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid username or password"));}
 }
}
