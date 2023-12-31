package com.gabezk.achados_e_perdidosapi.repositories;

import com.gabezk.achados_e_perdidosapi.models.ItemReportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemReportRepository extends JpaRepository<ItemReportModel, UUID> {
}
