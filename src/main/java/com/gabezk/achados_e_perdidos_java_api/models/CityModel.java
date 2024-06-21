package com.gabezk.achados_e_perdidos_java_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uf")
    private String uf;

    @Column(name = "nome")
    private String name;

    public CityModel(Integer id) {
        this.id = id;
    }

    public CityModel() {

    }
}
