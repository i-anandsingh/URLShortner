package com.shortner.url.service.client;

import com.shortner.url.Entity.UrlEntity;
import com.shortner.url.Repository.UrlShortnerRepository;
import com.shortner.url.service.business.IDecoder;
import com.shortner.url.utility.Base62;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecoderImpl implements IDecoder {

    private final UrlShortnerRepository urlShortnerRepository;

    private DecoderImpl(UrlShortnerRepository urlShortnerRepository){
        this.urlShortnerRepository = urlShortnerRepository;
    }

    @Override
    public String decoder(String encodedValue) {
        long seqId = Base62.decode(encodedValue);
        Optional<UrlEntity> urlEntity = urlShortnerRepository.findById(seqId);
        return (urlEntity.isEmpty() ? "No such url exists" : urlEntity.get().getOriginalUrl());
    }
}
