package com.shortner.url.service.client;

import com.shortner.url.Entity.UrlEntity;
import com.shortner.url.Repository.UrlShortnerRepository;
import com.shortner.url.utility.Base62;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DecoderImplTest {

    @Mock
    private UrlShortnerRepository urlShortnerRepository;

    @InjectMocks
    private DecoderImpl decoderImpl;

    @Test
    void decoder_returnsOriginalUrl_whenCodeExists() {
        String encodedValue = "1B";
        long seqId = Base62.decode(encodedValue);

        UrlEntity entity = new UrlEntity();
        entity.setSeqId(seqId);
        entity.setOriginalUrl("https://google.com");

        when(urlShortnerRepository.findById(seqId))
            .thenReturn(Optional.of(entity));

        String result = decoderImpl.decoder(encodedValue);

        assertEquals("https://google.com", result);
    }

    @Test
    void decoder_returnsNotFoundMessage_whenCodeDoesNotExist() {
        String encodedValue = "zz";
        long seqId = Base62.decode(encodedValue);

        when(urlShortnerRepository.findById(seqId))
            .thenReturn(Optional.empty());

        String result = decoderImpl.decoder(encodedValue);

        assertEquals("No such url exists", result);
    }
}