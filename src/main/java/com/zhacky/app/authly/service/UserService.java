package com.zhacky.app.authly.service;

import com.zhacky.app.authly.entity.UserEntity;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final String EXISTING_EMAIL = "test@test.com";
  private static final String ANOTHER_EMAIL = "next@test.com";

  public Optional<UserEntity> findByEmail(String email) {
    // TODO: Move this to a database
    if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setId(1L);
      user.setEmail(EXISTING_EMAIL);
      user.setPassword("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S"); // test
      user.setRole("ROLE_ADMIN");
      user.setExtraInfo("My nice admin");
      return Optional.of(user);
    } else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setId(2L);
      user.setEmail(ANOTHER_EMAIL);
      user.setPassword("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S"); // test
      user.setRole("ROLE_USER");
      user.setExtraInfo("My nice user");
      return Optional.of(user);
    } else {
      return Optional.empty();
    }
  }
}
