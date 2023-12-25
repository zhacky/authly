package com.zhacky.app.authly.service;

import com.zhacky.app.authly.entity.Role;
import com.zhacky.app.authly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.zhacky.app.authly.entity.UserRole.ROLE_ADMIN;
import static com.zhacky.app.authly.entity.UserRole.ROLE_USER;

@Profile("local")
@Component
public class SeedRunner implements CommandLineRunner {


  @Autowired
  UserService userService;

  @Override
  public void run(String... args) throws Exception {
    Role admin = new Role(ROLE_ADMIN);
    Role user = new Role(ROLE_USER);
    userService.findAll().stream().map(User::getEmail).forEach(System.out::println);
//    userService.create(User.builder().email("test@test.com")
//        .password("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S")
//        .roles(Set.of(admin)).extraInfo("Some extra info on user1").build());
//
//    userService.create(User.builder().email("next@test.com")
//        .password("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S")
//        .roles(Set.of(user)).extraInfo("Some extra info on " +
//            "user2").build());

  }
}
