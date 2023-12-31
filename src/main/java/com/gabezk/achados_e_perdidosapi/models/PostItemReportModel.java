package com.gabezk.achados_e_perdidosapi.models;

import com.gabezk.achados_e_perdidosapi.Enuns.PostStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_POSTAGEM")
@Data
public class PostItemReportModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemReportModel itemReport;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private ItemReportModel item;

    @ManyToMany
    @JoinTable(
            name = "TB_POSTAGEM_CATEGORIA",
            joinColumns = @JoinColumn(name = "postagem_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoryModel> categories = new HashSet<>();

    @Column(name = "data_cadastro")
    private Date registrationDate;

    @Column(name = "data_atualizacao")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserModel user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatusEnum status;
}
