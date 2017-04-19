package com.dminc.auth.controller;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dminc.auth.AuthApplication;
import com.dminc.auth.domain.User;
import com.dminc.auth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;

import lombok.SneakyThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    @SneakyThrows
    public void createNewUserTest() {
        
        final User user = User.builder()
                        .username("test")
                        .password("secret")
                        .build();
        
        String jsonUser = mapper.writeValueAsString(user);
        
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(jsonUser))
        .andExpect(status().isOk());
        
    }
    
    @Test
    @SneakyThrows
    public void createAnInvalidUserTest() {
        mockMvc.perform(post("/users"))
        .andExpect(status().isBadRequest());
        
    }

    @Test
    @SneakyThrows
    public void getCurrentUserTest() {
        mockMvc.perform(get("/users/current").principal(new UserPrincipal("test")))
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(status().isOk());
    }
}
