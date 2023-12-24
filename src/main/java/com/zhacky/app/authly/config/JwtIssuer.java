package com.zhacky.app.authly.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
  private final JwtProperties properties;

  public String issue(Long userId, String email, List<String> roles) {

    String token =
        JWT.create()
            .withSubject(String.valueOf(userId))
            .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
            .withClaim("e", email)
            .withClaim("a", roles)
            .sign(Algorithm.HMAC256(properties.getSecretKey()));

    return token;
  }
}
