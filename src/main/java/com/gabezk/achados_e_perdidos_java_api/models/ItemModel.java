package com.gabezk.achados_e_perdidos_java_api.models;

import com.gabezk.achados_e_perdidos_java_api.enums.ItemTypeEnum;
import com.gabezk.achados_e_perdidos_java_api.enums.PostStatusEnum;
import jakarta.persistence.*;
        import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Entity
@Table(name = "TB_ITEM")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descricao")
    private String description;

    @Column(name = "localizacao")
    private String location;

    @Column(name = "data")
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CityModel city = new CityModel(1);

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private ItemTypeEnum itemType = ItemTypeEnum.LOST;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatusEnum status = PostStatusEnum.WAITING_APPROVAL;

    @ManyToMany
    @JoinTable(
            name = "tb_item_categoria",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoryModel> categories;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageModel> images;

    @Column(name = "data_cadastro")
    private OffsetDateTime registrationDate = OffsetDateTime.now(ZoneOffset.UTC);

    @Column(name = "data_atualizacao")
    private OffsetDateTime updateDate = OffsetDateTime.now(ZoneOffset.UTC);
}
