package com.gabezk.achados_e_perdidosapi.Enuns;

public enum PostStatusEnum {

    WAITING_APPROVAL("Waiting Approval", "Postagem aguardando aprovação"),
    APPROVED("Approved", "Postagem aprovada"),
    REFUSED("Refused", "Postagem recusada"),
    RETURNED("Returned", "Postagem retornada");

    private final String displayName;
    private final String description;

    PostStatusEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
