package com.zhacky.app.authly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @Profile("!docker-compose")
@SpringBootApplication
public class AuthlyApplication {

  public static void main(String[] args) {

    SpringApplication.run(AuthlyApplication.class, args);
  }
}
