package com.shortner.url.controller;

import com.shortner.url.service.business.IEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UrlShortnerController {

    private final IEncoder iEncoder;

    private UrlShortnerController (IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    @PostMapping("/encode")
    private ResponseEntity<String> encodeUrl(
            @RequestBody String var
    ) {
        String enocdedUrl = iEncoder.encode(var);
        return new ResponseEntity<>(enocdedUrl, HttpStatus.CREATED);
    }
}
