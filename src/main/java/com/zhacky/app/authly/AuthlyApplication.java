package com.zhacky.app.authly;

import com.zhacky.app.authly.entity.User;
import com.zhacky.app.authly.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// @Profile("!docker-compose")
@SpringBootApplication
public class AuthlyApplication {

  public static void main(String[] args) {

    SpringApplication.run(AuthlyApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(UserService userService) {
    return args -> {
      userService.findAll().stream().map(User::getEmail).forEach(System.out::println);
      userService.create(User.builder().email("test@test.com")
          .password("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S")
          .role("ROLE_ADMIN").extraInfo("Some extra info on user1").build());
      userService.create(User.builder().email("next@test.com")
          .password("$2a$12$HVtN3noZRyZX4zV7Wq3wa.IOzXwI/wPlVadgt5uEBAyjkdy308q.S")
          .role("ROLE_ADMIN").extraInfo("Some extra info on user1").build());

    };

  }
}
