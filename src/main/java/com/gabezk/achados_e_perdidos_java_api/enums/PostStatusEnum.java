package com.gabezk.achados_e_perdidos_java_api.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {

    WAITING_APPROVAL("Waiting Approval", "Aguardando aprovação"),
    APPROVED("Approved", "Aprovado"),
    REFUSED("Refused", "Recusado"),
    RETURNED("Returned", "Retornado");

    private final String displayName;
    private final String description;

    PostStatusEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

}
