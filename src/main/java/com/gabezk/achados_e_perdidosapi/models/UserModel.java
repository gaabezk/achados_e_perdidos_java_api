package com.gabezk.achados_e_perdidosapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_USUARIO")
public class UserModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_completo")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String phone;

    @Column(name = "data_cadastro")
    private Date registrationDate;

    @Column(name = "data_atualizacao")
    private Date updateDate;

    @OneToMany(mappedBy = "user")
    private Set<PostItemReportModel> itemReports = new HashSet<>();
}
