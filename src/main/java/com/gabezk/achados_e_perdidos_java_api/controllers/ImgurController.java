package com.gabezk.achados_e_perdidos_java_api.controllers;

import com.gabezk.achados_e_perdidos_java_api.responses.ImageUploadResponse;
import com.gabezk.achados_e_perdidos_java_api.services.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImgurController {

    @Value("${imgur.client-id}")
    private String clientId;

    @Autowired
    private ImgurService imgurService;

    @PostMapping("/upload")
    public ResponseEntity<List<ImageUploadResponse>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<ImageUploadResponse> responses = imgurService.uploadImages(files);
        boolean hasFailures = responses.stream().anyMatch(response -> "failure".equals(response.getStatus()));

        if (hasFailures) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(responses);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
    }
}
