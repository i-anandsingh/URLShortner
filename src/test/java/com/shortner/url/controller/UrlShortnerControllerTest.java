package com.shortner.url.controller;

import com.shortner.url.service.business.IDecoder;
import com.shortner.url.service.business.IEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlShortnerController.class)
class UrlShortnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IEncoder iEncoder;

    @MockitoBean
    private IDecoder iDecoder;

    @Test
    void encode_returnsEncodedUrl() throws Exception {
        when(iEncoder.encode("https://google.com")).thenReturn("1B");

        mockMvc.perform(post("/encode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"https://google.com\""))
                .andExpect(status().isCreated())
                .andExpect(content().string("1B"));
    }

    @Test
    void decode_returnsOriginalUrl() throws Exception {
        when(iDecoder.decoder("1B")).thenReturn("https://google.com");

        mockMvc.perform(get("/decode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"1B\""))
                .andExpect(status().isOk())
                .andExpect(content().string("https://google.com"));
    }
}