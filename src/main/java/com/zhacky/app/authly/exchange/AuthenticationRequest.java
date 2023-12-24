package com.zhacky.app.authly.exchange;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationRequest {
  private String email;
  private String password;
}
