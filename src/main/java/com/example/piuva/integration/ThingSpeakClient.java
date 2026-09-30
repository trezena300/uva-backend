package com.example.piuva.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ThingSpeakClient {

    private final RestTemplate restTemplate;

    @Value("${thingspeak.channel-id}")
    private String channelId;

    @Value("${thingspeak.read-api-key}")
    private String readApiKey;

    public List<ThingSpeakFeedResponse.Feed> buscarUltimasLeituras(int quantidade) {
        String url = String.format(
                "https://api.thingspeak.com/channels/%s/feeds.json?api_key=%s&results=%d",
                channelId, readApiKey, quantidade
        );

        ThingSpeakFeedResponse response = restTemplate.getForObject(url, ThingSpeakFeedResponse.class);

        if (response == null || response.getFeeds() == null) {
            return Collections.emptyList();
        }
        return response.getFeeds();
    }
}
