package com.gabezk.achados_e_perdidosapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uf")
    private String uf;

    @Column(name = "nome")
    private String name;
}
