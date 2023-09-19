package com.kevinciz.demo.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinciz.demo.adapters.service.S3Service;
import com.kevinciz.demo.properties.S3Properties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    @Autowired
    S3Properties s3Properties;

    private final S3Service s3Service;

    @GetMapping("/s3")
    public ResponseEntity<Object> getObject() {
        return new ResponseEntity<>(s3Service.getObjectContent(s3Properties.getBucketName(), s3Properties.getFileName()), HttpStatus.OK);
    }
}
