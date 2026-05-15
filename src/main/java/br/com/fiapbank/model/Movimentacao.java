package br.com.fiapbank.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Movimentacao {

    private final LocalDateTime dataHora;
    private final String tipo;
    private final Dinheiro valor;

    public Movimentacao(LocalDateTime dataHora, String tipo, Dinheiro valor) {
        if (dataHora == null) {
            throw new IllegalArgumentException("Data/hora não pode ser nula");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de movimentação não pode ser nulo/vazio");
        }
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public Dinheiro getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Movimentacao other)) return false;
        return Objects.equals(dataHora, other.dataHora)
                && Objects.equals(tipo, other.tipo)
                && Objects.equals(valor, other.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataHora, tipo, valor);
    }
}

