package com.shortner.url.service.client;

import com.shortner.url.Entity.UrlEntity;
import com.shortner.url.Repository.UrlShortnerRepository;
import com.shortner.url.service.business.IDecoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DecoderImpl implements IDecoder {

    private final UrlShortnerRepository urlShortnerRepository;

    private DecoderImpl(UrlShortnerRepository urlShortnerRepository){
        this.urlShortnerRepository = urlShortnerRepository;
    }

    @Override
    public String decoder(Long encodedUrl) {
        Optional<UrlEntity> urlEntity = urlShortnerRepository.findByEncodedUrl(encodedUrl);
        if(urlEntity.isEmpty()){
            return "No such encoded url";
        }
        return  urlEntity.get().getOriginalUrl();
    }
}
