package com.dminc.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="serviceauthconfig")
public class ServiceAuthConfig {

    public List<String> services = new ArrayList<>();

    public List<String> getServices() {
        return services;
    }
    
}
