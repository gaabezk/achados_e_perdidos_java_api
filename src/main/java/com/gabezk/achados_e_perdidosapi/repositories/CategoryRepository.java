package com.gabezk.achados_e_perdidosapi.repositories;

import com.gabezk.achados_e_perdidosapi.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
}
