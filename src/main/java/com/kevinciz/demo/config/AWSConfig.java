package com.kevinciz.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kevinciz.demo.properties.AwsProperties;


import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class AWSConfig {

    private final AwsProperties awsProperties;

    private AwsCredentialsProvider getCredentialsProvider() {
        if (System.getenv("AWS_WEB_IDENTITY_TOKEN_FILE") != null) {
            return WebIdentityTokenFileCredentialsProvider.builder().build();
        }
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(
                awsProperties.getAccessKey(),
                awsProperties.getSecretKey()));
    }
        
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                    .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                    .credentialsProvider(getCredentialsProvider())
                    .endpointOverride(URI.create(awsProperties.getServiceEndpoint()))
                    .region(Region.of(awsProperties.getRegion()))
                    .forcePathStyle(true)
                    .build(); 
    }

    @Bean
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
                .region(Region.of(awsProperties.getRegion()))
                .endpointOverride(URI.create(awsProperties.getServiceEndpoint()))
                .credentialsProvider(getCredentialsProvider())
                .build();
    }
}
