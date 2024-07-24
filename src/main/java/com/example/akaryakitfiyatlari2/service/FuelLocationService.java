package com.example.akaryakitfiyatlari2.service;

import com.example.akaryakitfiyatlari2.model.CityResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class FuelLocationService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.host}")
    private String apiHost;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public FuelLocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CityResponse getCityLocations() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", apiHost);
        headers.set("X-RapidAPI-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Send the request with headers
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        // Convert the raw response to CityResponse
        return parseCityResponse(response.getBody());
    }

    private CityResponse parseCityResponse(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode dataNode = rootNode.path("data");

            CityResponse cityResponse = new CityResponse();
            cityResponse.setCities(objectMapper.convertValue(dataNode.path("cities"), List.class));
            cityResponse.setDistricts(objectMapper.convertValue(dataNode.path("districts"), List.class));

            return cityResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return new CityResponse(); // Return empty response in case of error
        }
    }
}
