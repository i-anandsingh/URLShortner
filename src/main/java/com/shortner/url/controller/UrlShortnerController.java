package com.shortner.url.controller;

import com.shortner.url.service.business.IDecoder;
import com.shortner.url.service.business.IEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class UrlShortnerController {

    private final IEncoder iEncoder;
    private final IDecoder iDecoder;

    private UrlShortnerController (
            IEncoder iEncoder,
            IDecoder iDecoder
    ){
        this.iEncoder = iEncoder;
        this.iDecoder = iDecoder;
    }

    @PostMapping("/encode")
    private ResponseEntity<String> encoder(
            @RequestBody String originalUrl
    ) {
        String encodedUrl = iEncoder.encode(originalUrl);
        return new ResponseEntity<>(encodedUrl, HttpStatus.CREATED);
    }

    @GetMapping("/decode")
    private ResponseEntity<String> decoder(
            @RequestBody String encodedUrl
    ) {
        String originalUrl = iDecoder.decoder(encodedUrl);
        return new ResponseEntity<>(originalUrl, HttpStatus.OK);
    }
}
