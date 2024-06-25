package com.gabezk.achados_e_perdidos_java_api.controllers;

import com.gabezk.achados_e_perdidos_java_api.enums.ItemTypeEnum;
import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorException;
import com.gabezk.achados_e_perdidos_java_api.models.ItemModel;
import com.gabezk.achados_e_perdidos_java_api.requests.ItemRequest;
import com.gabezk.achados_e_perdidos_java_api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/item-reports")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/cadastrar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> postItem(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("foundDate") String foundDateString,
            @RequestParam("itemType") ItemTypeEnum itemType,
            @RequestParam("cityId") int cityId,
            @RequestParam("categoriesId") List<UUID> categoriesId,
            @RequestParam("files") MultipartFile[] files
    ) throws ParseException {
        var itemReportPostRequest = ItemRequest.builder()
                .title(title)
                .description(description)
                .location(location)
                .itemType(itemType)
                .cityId(cityId)
                .categoriesId(categoriesId)
                .files(files)
                .foundDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(foundDateString))
                .build();

        var itemReportPost = itemService.postItem(itemReportPostRequest);
        return ResponseEntity.ok(itemReportPost);
    }

    @GetMapping()
    public ResponseEntity<List<ItemModel>> getItens() {
        return ResponseEntity.ok(itemService.getItens());
    }
}
