package com.shortner.url.service.client;

import com.shortner.url.service.business.IEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderImpl implements IEncoder{


    @Override
    public String encode(String url) {
        return "";
    }
}
