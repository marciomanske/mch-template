package com.dminc.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dminc.auth.domain.User;
import com.dminc.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository        userRepository;

    @Override
    public User createUser(User user)  {

        User existingUser = userRepository.findByUsername(user.getUsername());
        Assert.isNull(existingUser, "user already exists: " + user.getUsername());
        

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        userRepository.save(user);
        log.info("User {} created successfully", user.getUsername());
        return user;
    }

}
