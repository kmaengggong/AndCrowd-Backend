package com.fiveis.andcrowd.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix="spring.security.oauth2.client.registration.naver")
public class NaverOauthConfig {
    String clientId;
    String redirectUri;
}