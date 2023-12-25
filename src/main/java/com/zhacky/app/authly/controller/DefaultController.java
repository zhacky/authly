package com.zhacky.app.authly.controller;

import com.zhacky.app.authly.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DefaultController {

  @GetMapping("/")
  public String home() {
    return "Hello";
  }

  @GetMapping("/secured")
  public String secured(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    return "If you see this, then you are logged in!"
        + userPrincipal.getEmail()
        + "\nID: "
        + userPrincipal.getUserId();
  }

  @GetMapping("/admin")
  public String admin(@AuthenticationPrincipal UserPrincipal principal) {
    return "You must be an admin! " + principal.getUsername() + "\nID: " + principal.getUserId();
  }
}
