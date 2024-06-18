package com.gabezk.achados_e_perdidos_java_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORIA")
@Data
public class CategoryModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_cadastro")
    private Date registrationDate;

    @Column(name = "data_atualizacao")
    private Date updateDate;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ItemReportPostModel> items = new HashSet<>();
}
