package com.gabezk.achados_e_perdidos_java_api.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class ItemRequest {

    public String postTitle;
    public String postDescription;
    public String itemTitle;
    public String itemDescription;
    public String itemLocation;
    public Date foundDate;
    public MultipartFile[] files;

    public ItemRequest(String postTitle, String postDescription, String itemTitle, String itemDescription, String itemLocation, Date foundDate, MultipartFile[] files) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemLocation = itemLocation;
        this.foundDate = foundDate;
        this.files = files;
    }
}
