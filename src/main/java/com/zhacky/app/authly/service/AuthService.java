package com.zhacky.app.authly.service;

import com.zhacky.app.authly.exchange.AuthenticationResponse;
import com.zhacky.app.authly.security.JwtIssuer;
import com.zhacky.app.authly.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtIssuer jwtIssuer;
  private final DaoAuthenticationProvider provider;

  public AuthenticationResponse attemptLogin(String email, String password) {

    var authentication =
        provider.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    var principal = (UserPrincipal) authentication.getPrincipal();

    var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
    AuthenticationResponse authResponse = AuthenticationResponse.builder().jwt(token).build();

    return authResponse;
  }
}
