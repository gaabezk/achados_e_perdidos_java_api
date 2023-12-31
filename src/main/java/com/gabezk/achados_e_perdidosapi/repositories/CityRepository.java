package com.gabezk.achados_e_perdidosapi.repositories;

import com.gabezk.achados_e_perdidosapi.models.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityModel, Integer> {
}
