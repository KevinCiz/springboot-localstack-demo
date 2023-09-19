package com.kevinciz.demo.adapters.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinciz.demo.properties.ConfigProperties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HealthController {

    @Autowired
    ConfigProperties configProperties;

    @GetMapping("/healthz")
    public ResponseEntity<Object> getHealthz(){
        Map<String, String> data = new HashMap<>();
        data.put("env", configProperties.getEnvironment());
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<Object> getPing(){
        Map<String, String> ping = new HashMap<>();
        ping.put("response", "pong");
        return new ResponseEntity<>(ping,HttpStatus.OK);
    }
}
