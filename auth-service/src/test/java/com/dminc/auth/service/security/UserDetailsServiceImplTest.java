package com.dminc.auth.service.security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dminc.auth.domain.User;
import com.dminc.auth.repository.UserRepository;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl service;
    
    @Mock
    private UserRepository repository;
    
    @Before
    public void setup() {
        initMocks(this);
    }
    
    @Test
    public void findByUsername() {
        
        final User user = User.builder().build();
        when(repository.findByUsername(any())).thenReturn(user);
        UserDetails userDetails = service.loadUserByUsername("name");
        assertEquals(user, userDetails);
    }
    
    @Test(expected = UsernameNotFoundException.class)
    public void shouldFailToLoadByUsernameWhenUserNotExists() {
        service.loadUserByUsername("name");
    }
}
