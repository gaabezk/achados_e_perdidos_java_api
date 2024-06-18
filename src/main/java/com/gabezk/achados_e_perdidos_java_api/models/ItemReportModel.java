package com.gabezk.achados_e_perdidos_java_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabezk.achados_e_perdidos_java_api.enums.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ITEM")
@Data
public class ItemReportModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CityModel city;

    @OneToOne
    @JoinColumn(name = "postagem_id")
    @JsonIgnore
    private ItemReportPostModel post;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private ItemTypeEnum itemType;

    @Column(name = "localizacao")
    private String location;

    @Column(name = "data")
    private Date date;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageModel> images;
}
