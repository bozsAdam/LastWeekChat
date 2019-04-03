package com.lastweekchat.gifservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GifService {

    public List<String> getGifList(String input) {
        RestTemplate restTemplate = new RestTemplate();
        String gifUrl
                = "https://api.tenor.com/v1/search?q=" + input + "&key=LCN58D706L2J&limit=8&anon_id=3a76e56901d740da9e59ffb22b988242";
        ResponseEntity<String> response
                = restTemplate.getForEntity(gifUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        JsonNode results = root.path("results");
        List<String> result = new ArrayList<>();
        List<JsonNode> mediaNodes = new ArrayList<>();
        if (results.isArray()) {
            results.forEach(jsonNode -> {
                mediaNodes.add(jsonNode.path("media"));
            });
        } else result.add("Nothing found");
        if (!mediaNodes.isEmpty()) mediaNodes.forEach(jsonNode -> {
            jsonNode.forEach(jsonNode1 -> result.add(jsonNode1.path("tinygif").path("url").textValue()));
                });
        return result;
    }
}
