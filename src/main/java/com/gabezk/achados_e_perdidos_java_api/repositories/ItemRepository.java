package com.gabezk.achados_e_perdidos_java_api.repositories;

import com.gabezk.achados_e_perdidos_java_api.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemModel, UUID> {
}
