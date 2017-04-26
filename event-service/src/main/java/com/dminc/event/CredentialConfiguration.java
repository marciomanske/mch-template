package com.dminc.event;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix="security.oauth2.client")
@Getter
@Setter
@ToString
public class CredentialConfiguration {

    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String grantType;
    private String scope;
    
}
