package com.gabezk.achados_e_perdidosapi.repositories;

import com.gabezk.achados_e_perdidosapi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
