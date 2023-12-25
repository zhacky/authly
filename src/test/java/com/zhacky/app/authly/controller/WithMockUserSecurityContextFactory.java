package com.zhacky.app.authly.controller;

import com.zhacky.app.authly.security.UserPrincipal;
import com.zhacky.app.authly.security.UserPrincipalAuthenticationToken;
import java.util.Arrays;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockUser> {

  @Override
  public SecurityContext createSecurityContext(WithMockUser annotation) {
    var authorities =
        Arrays.stream(annotation.authorities()).map(SimpleGrantedAuthority::new).toList();
    var principal =
        UserPrincipal.builder()
            .userId(annotation.userId())
            .email("fake@email.com")
            .authorities(authorities)
            .build();

    var context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(new UserPrincipalAuthenticationToken(principal));
    return context;
  }
}
