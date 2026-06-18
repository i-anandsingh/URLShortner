package com.shortner.url.service.business;

import org.springframework.stereotype.Service;

@Service
public interface IDecoder {
    public String decoder(Long encodedUrl);
}
