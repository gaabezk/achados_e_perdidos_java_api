package com.gabezk.achados_e_perdidos_java_api.services;

import com.gabezk.achados_e_perdidos_java_api.models.CategoryModel;
import com.gabezk.achados_e_perdidos_java_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel postCategory(String name, String description) {
        return categoryRepository.save(new CategoryModel(name, description));
    }

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

}
