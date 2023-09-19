package com.kevinciz.demo.adapters.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecretsManagerService {

    private final SecretsManagerClient secretsManagerClient;

    public String getSecret(String secretId) {
        GetSecretValueRequest valueRequest = GetSecretValueRequest.builder().secretId(secretId).build();
        
        try{
            return secretsManagerClient.getSecretValue(valueRequest).secretString();
        }catch (SecretsManagerException e){
            log.error(e.awsErrorDetails().errorMessage());
        }
        
        return null;
    }
}
