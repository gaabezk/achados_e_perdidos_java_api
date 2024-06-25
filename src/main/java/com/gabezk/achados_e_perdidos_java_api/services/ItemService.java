package com.gabezk.achados_e_perdidos_java_api.services;

import com.gabezk.achados_e_perdidos_java_api.enums.ItemTypeEnum;
import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorException;
import com.gabezk.achados_e_perdidos_java_api.models.CategoryModel;
import com.gabezk.achados_e_perdidos_java_api.models.ImageModel;
import com.gabezk.achados_e_perdidos_java_api.models.ItemModel;
import com.gabezk.achados_e_perdidos_java_api.repositories.ImageRepository;
import com.gabezk.achados_e_perdidos_java_api.repositories.ItemRepository;
import com.gabezk.achados_e_perdidos_java_api.requests.ItemRequest;
import com.gabezk.achados_e_perdidos_java_api.responses.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class ItemService {

    @Value("${imgur.client-id}")
    private String imgurClientId;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImgurService imgurService;

    @Transactional(rollbackFor = CustomErrorException.class)
    public ItemModel postItem(ItemRequest request) {
        var itemReport = new ItemModel();
        itemReport.setTitle(request.getTitle());
        itemReport.setDescription(request.getDescription());
        itemReport.setDate(OffsetDateTime.now(ZoneOffset.UTC));
        itemReport.setItemType(request.itemType);
        itemReport.setLocation(request.location);
        itemReport.getCity().setId(request.cityId);
        Set<CategoryModel> categorySet = new HashSet<>();
        for (UUID categoryId : request.getCategoriesId()) {
            categorySet.add(new CategoryModel(categoryId));
        }
        itemReport.setCategories(categorySet);

        itemReport = itemRepository.save(itemReport);

        var imagesList = new ArrayList<ImageModel>();

        var imageResponses = imgurService.uploadImages(request.getFiles());

        for (ImageUploadResponse imageResponse : imageResponses) {
            ImageModel newImage = new ImageModel();
            newImage.setUrl(imageResponse.getUrl());
            newImage.setItem(itemReport);
            imagesList.add(imageRepository.save(newImage));
        }
        itemReport.setImages(new HashSet<>(imagesList));

        return itemRepository.save(itemReport);
    }

    public List<ItemModel> getItens() {
        return itemRepository.findAll();
    }
}
