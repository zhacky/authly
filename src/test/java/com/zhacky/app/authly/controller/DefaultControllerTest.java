package com.zhacky.app.authly.controller;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DefaultControllerTest {

  @Autowired private MockMvc api;

  @Test
  void anyone_canView_publicEndpoint() throws Exception {
    api.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("Hello")));
  }

  @Test
  void notLoggedIn_shouldNotSee_SecuredEndpoint() throws Exception {
    api.perform(get("/secured")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void loggedIn_shouldSee_SecuredEndPoint() throws Exception {
    api.perform(get("/secured"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("\nID: 1")));
  }

  @Test
  void notLoggedIn_shouldNotSee_AdminEndpoint() throws Exception {
    api.perform(get("/admin")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void loggedInSimpleUser_shouldNotSee_AdminEndPoint() throws Exception {
    api.perform(get("/admin")).andExpect(status().isForbidden());
  }

  @Test
  @WithAdminUser
  void loggedInAdmin_shouldSee_AdminEndPoint() throws Exception {
    api.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsStringIgnoringCase("\nID: 1")));
  }
}
