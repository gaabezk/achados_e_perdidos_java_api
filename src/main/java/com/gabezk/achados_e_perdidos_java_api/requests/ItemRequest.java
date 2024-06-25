package com.gabezk.achados_e_perdidos_java_api.requests;

import com.gabezk.achados_e_perdidos_java_api.enums.ItemTypeEnum;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {

    public String title;
    public String description;
    public String location;
    public Date foundDate;
    public ItemTypeEnum itemType;
    public MultipartFile[] files;
    public int cityId;
    public List<UUID> categoriesId;
}
