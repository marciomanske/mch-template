package com.dminc.show;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="security.oauth2.client")
@Getter
@Setter
public class CredentialConfiguration {

    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String grantType;
    private String scope;
    
}
