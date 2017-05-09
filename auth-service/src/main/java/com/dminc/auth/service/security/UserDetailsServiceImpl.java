package com.dminc.auth.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dminc.auth.domain.User;
import com.dminc.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("Query by username {}", username);
        User user = userRespository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

}
