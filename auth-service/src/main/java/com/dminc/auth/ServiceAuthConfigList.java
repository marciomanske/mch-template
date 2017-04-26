package com.dminc.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="serviceauthconfig")
@Setter
@Getter
public class ServiceAuthConfigList {

    private List<ServiceAuthConfig> services = new ArrayList<>();
    
}
