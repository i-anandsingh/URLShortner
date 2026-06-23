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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EncodeImplTest {

    @Mock
    private UrlShortnerRepository urlShortnerRepository;

    @InjectMocks
    private EncoderImpl encoderImpl;

    @Test
    void encode_NewIfUrlIsNew_ElseReturnFromDb(){
        String url = "www.google.com";

        UrlEntity existing = new UrlEntity();
        existing.setOriginalUrl(url);
        existing.setEncodedUrl("1B");

        when(urlShortnerRepository.findByOriginalUrl(url))
                .thenReturn(Optional.of(existing));

        String result = encoderImpl.encode(url);
        assertEquals("1B", result);
        verify(urlShortnerRepository, never()).save(any());
    }

    @Test
    void encode_createsNewCode_whenUrlIsNew() {
        String url = "https://google.com";

        when(urlShortnerRepository.findByOriginalUrl(url))
                .thenReturn(Optional.empty());

        UrlEntity savedWithId = new UrlEntity();
        savedWithId.setSeqId(99L);
        savedWithId.setOriginalUrl(url);

        when(urlShortnerRepository.save(any(UrlEntity.class)))
                .thenReturn(savedWithId);

        String result = encoderImpl.encode(url);

        assertEquals(Base62.encode(99L), result);
        verify(urlShortnerRepository, times(2)).save(any(UrlEntity.class));
    }
}
