package com.shortner.url.service.client;

import com.shortner.url.Entity.UrlEntity;
import com.shortner.url.Repository.UrlShortnerRepository;
import com.shortner.url.service.business.IEncoder;
import com.shortner.url.utility.Base62;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncoderImpl implements IEncoder {

    private final UrlShortnerRepository urlShortnerRepository;

    private EncoderImpl(UrlShortnerRepository urlShortnerRepository) {
        this.urlShortnerRepository = urlShortnerRepository;
    }

    @Override
    public String encode(String originalUrl) {
        Optional<UrlEntity> existing = urlShortnerRepository.findByOriginalUrl(originalUrl);
        if (existing.isPresent()) {
            return existing.get().getEncodedUrl();
        }

        UrlEntity entity = new UrlEntity();
        entity.setOriginalUrl(originalUrl);
        entity.setEncodedUrl(""); // placeholder, encoded_url is NOT NULL
        UrlEntity saved = urlShortnerRepository.save(entity);

        String encodedUrl = encoding(saved.getSeqId());
        saved.setEncodedUrl(encodedUrl);
        urlShortnerRepository.save(saved);

        return encodedUrl;
    }

    private String encoding(Long seqId) {
        return Base62.encode(seqId);
    }
}
