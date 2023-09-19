package com.kevinciz.demo.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinciz.demo.adapters.service.SecretsManagerService;
import com.kevinciz.demo.properties.SecretsManagerProperties;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class SecretsManagerController {
    @Autowired
    SecretsManagerProperties SecretsManagerProperties;

    private final SecretsManagerService secretsManagerService;

    @GetMapping("/secretsmanager")
    public ResponseEntity<Object> getSecret(){
        return new ResponseEntity<Object>(secretsManagerService.getSecret(SecretsManagerProperties.getSecretId()), HttpStatus.OK);
    }
}
