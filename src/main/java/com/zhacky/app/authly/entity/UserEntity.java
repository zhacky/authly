package com.zhacky.app.authly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
  private Long id;
  private String email;
  @JsonIgnore private String password;
  private String role;
  private String extraInfo;
}
