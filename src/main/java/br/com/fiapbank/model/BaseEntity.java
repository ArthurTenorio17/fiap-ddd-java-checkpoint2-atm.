package br.com.fiapbank.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class BaseEntity {

    private final UUID id;
    private final LocalDate dataCriacao;

    protected BaseEntity() {
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}

