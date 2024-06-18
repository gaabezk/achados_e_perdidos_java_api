package com.gabezk.achados_e_perdidos_java_api.repositories;

import com.gabezk.achados_e_perdidos_java_api.models.ItemReportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemReportRepository extends JpaRepository<ItemReportModel, UUID> {
}
