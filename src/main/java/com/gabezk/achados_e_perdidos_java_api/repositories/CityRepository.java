package com.gabezk.achados_e_perdidos_java_api.repositories;

import com.gabezk.achados_e_perdidos_java_api.models.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityModel, Integer> {
}
