package br.com.fiapbank.model;

import java.util.Objects;

public class Cliente extends BaseEntity {

    private final String nomeCompleto;
    private final ContaAcesso contaAcesso;

    public Cliente(String nomeCompleto, ContaAcesso contaAcesso) {

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new IllegalArgumentException("Nome completo é obrigatório");
        }

        this.nomeCompleto = nomeCompleto;
        this.contaAcesso = contaAcesso;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public ContaAcesso getContaAcesso() {
        return contaAcesso;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Cliente other)) return false;

        return Objects.equals(nomeCompleto, other.nomeCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto);
    }
}