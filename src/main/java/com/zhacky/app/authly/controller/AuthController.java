package com.zhacky.app.authly.controller;

import com.zhacky.app.authly.exchange.AuthenticationRequest;
import com.zhacky.app.authly.exchange.AuthenticationResponse;
import com.zhacky.app.authly.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/auth/login")
  public AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest) {

    return authService.attemptLogin(authRequest.getEmail(), authRequest.getPassword());
  }
}
