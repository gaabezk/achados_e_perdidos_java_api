package com.gabezk.achados_e_perdidos_java_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_IMAGEM")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImageModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private ItemModel item;
}
