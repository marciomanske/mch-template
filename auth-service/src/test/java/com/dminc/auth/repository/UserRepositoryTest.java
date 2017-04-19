package com.dminc.auth.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dminc.auth.AuthApplication;
import com.dminc.auth.domain.User;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@Slf4j
public class UserRepositoryTest {

    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void createAndFindUserTest() {
        
        User user = User.builder()
                .username("dmi-user")
                .password("dmi-password")
                .email("dmiuser@dminc.com")
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        userRepository.save(user);

        User existing = userRepository.findByUsername("dmi-user");
        assertEquals(existing.getUsername(), user.getUsername());
        assertEquals(existing.getPassword(), user.getPassword());
        assertEquals(existing.getEmail(), user.getEmail());
        
        log.info("User: {}" , existing);
        
    }
    
}
