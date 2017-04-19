package com.dminc.auth.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.dminc.auth.domain.User;
import com.dminc.auth.repository.UserRepository;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void createUserTest() {

        User user = User.builder()
        .username("name")
        .password("password")
        .build();

        
        userService.createUser(user);
        verify(repository, times(1)).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createDuplicatedUserTest() {

        User user = User.builder()
                .username("name")
                .password("password")
                .build();

        when(repository.findOne(user.getUsername())).thenReturn(User.builder().build());
        userService.createUser(user);
    }
    
}
