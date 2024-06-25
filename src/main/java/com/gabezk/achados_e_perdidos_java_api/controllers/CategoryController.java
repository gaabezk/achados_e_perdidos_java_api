package com.gabezk.achados_e_perdidos_java_api.controllers;

import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorException;
import com.gabezk.achados_e_perdidos_java_api.models.CategoryModel;
import com.gabezk.achados_e_perdidos_java_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> postItem(
            @RequestParam("name") String postTitle,
            @RequestParam("description") String postDescription
    ) throws CustomErrorException {
        try {
            return ResponseEntity.ok(categoryService.postCategory(postTitle, postDescription));
        } catch (Exception ex) {
            throw new CustomErrorException(ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getItens() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
