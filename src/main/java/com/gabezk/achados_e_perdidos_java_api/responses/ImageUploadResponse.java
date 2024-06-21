package com.gabezk.achados_e_perdidos_java_api.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadResponse {
    private String fileName;
    private String url;
    private String status;
    private String message;

    public ImageUploadResponse(String fileName, String url, String status, String message) {
        this.fileName = fileName;
        this.url = url;
        this.status = status;
        this.message = message;
    }
}