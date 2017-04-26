package com.dminc.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceAuthConfig {

    private String serviceName;
    private String secret;
    private String[] authorizedGrantTypes;
    private String[] scopes;
    
}
