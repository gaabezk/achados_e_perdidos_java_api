package com.gabezk.achados_e_perdidos_java_api.services;

import com.gabezk.achados_e_perdidos_java_api.responses.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImgurService {

    @Value("${imgur.client-id}")
    private String clientId;

    @Value("${imgur.upload-url}")
    private String imgurUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ImgurService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<ImageUploadResponse> uploadImages(MultipartFile[] files) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Client-ID " + clientId);

        List<ImageUploadResponse> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", file.getResource());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(imgurUrl, requestEntity, String.class);
                String imageUrl = extractImageUrl(response.getBody());
                responses.add(new ImageUploadResponse(file.getOriginalFilename(), imageUrl, "success", "Upload successful"));
            } catch (Exception e) {
                responses.add(new ImageUploadResponse(file.getOriginalFilename(), null, "failure", e.getMessage()));
            }
        }

        return responses;
    }

    private String extractImageUrl(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("data").path("link").asText();
        } catch (Exception e) {
            return null;
        }
    }
}
