package com.gabezk.achados_e_perdidos_java_api.controllers;

import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorException;
import com.gabezk.achados_e_perdidos_java_api.models.ItemModel;
import com.gabezk.achados_e_perdidos_java_api.requests.ItemRequest;
import com.gabezk.achados_e_perdidos_java_api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/item-reports")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> postItem(
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postDescription") String postDescription,
            @RequestParam("itemTitle") String itemTitle,
            @RequestParam("itemDescription") String itemDescription,
            @RequestParam("itemLocation") String itemLocation,
            @RequestParam("foundDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date foundDate,
            @RequestParam("files") MultipartFile[] files
    ) throws CustomErrorException {
        var itemReportPostRequest = new ItemRequest(postTitle, postDescription, itemTitle, itemDescription, itemLocation, foundDate, files);
        var itemReportPost = itemService.postItem(itemReportPostRequest);
        return ResponseEntity.ok(itemReportPost);
    }

    @GetMapping()
    public ResponseEntity<List<ItemModel>> getItens() {
        return ResponseEntity.ok(itemService.getItens());
    }
}
