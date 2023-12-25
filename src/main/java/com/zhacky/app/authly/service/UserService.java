package com.zhacky.app.authly.service;

import com.zhacky.app.authly.entity.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final JdbcClient jdbcClient;

  public UserService(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<User> findAll() {
    return jdbcClient.sql("SELECT * FROM users")
        .query(User.class)
        .list();
  }

  public Optional<User> findByEmail(String email) {
    return jdbcClient.sql("SELECT * FROM users WHERE email = :email")
        .param("email", email)
        .query(User.class)
        .optional();
  }

  public void create(User user) {
    int update = jdbcClient.sql("INSERT INTO users(username,email,password,roles,extra_info) values(?, ?,?,?,?)")
        .params(List.of(user.getEmail(), user.getEmail(), user.getPassword(), user.getRoles(), user.getExtraInfo()))
        .update();
  }

//  private static final String EXISTING_EMAIL = "test@test.com";
//  private static final String ANOTHER_EMAIL = "next@test.com";

//  public Optional<UserEntity> findByEmail(String email) {
//    // TODO: Move this to a database
//    if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
//      var user = new UserEntity();
//      user.setId(1L);
//      user.setEmail(EXISTING_EMAIL);
//      user.setPassword("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S"); // test
//      user.setRole("ROLE_ADMIN");
//      user.setExtraInfo("My nice admin");
//      return Optional.of(user);
//    } else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
//      var user = new UserEntity();
//      user.setId(2L);
//      user.setEmail(ANOTHER_EMAIL);
//      user.setPassword("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S"); // test
//      user.setRole("ROLE_USER");
//      user.setExtraInfo("My nice user");
//      return Optional.of(user);
//    } else {
//      return Optional.empty();
//    }
//  }
}
