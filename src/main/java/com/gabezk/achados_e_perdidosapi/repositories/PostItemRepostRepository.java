package com.gabezk.achados_e_perdidosapi.repositories;

import com.gabezk.achados_e_perdidosapi.models.PostItemReportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostItemRepostRepository extends JpaRepository<PostItemReportModel, UUID> {
}
