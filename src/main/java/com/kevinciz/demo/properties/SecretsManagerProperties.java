package com.kevinciz.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.secretsmanager")
public class SecretsManagerProperties {
    private String secretId;
    
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretId() {
        return secretId;
    }
}
