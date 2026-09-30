package com.example.piuva.integration;

import lombok.Data;

import java.util.List;

@Data
public class ThingSpeakFeedResponse {

    private Channel channel;
    private List<Feed> feeds;

    @Data
    public static class Channel {
        private Long id;
        private String name;
    }

    @Data
    public static class Feed {
        private String created_at;
        private Long entry_id;
        private String field1;
        private String field2;
    }
}
