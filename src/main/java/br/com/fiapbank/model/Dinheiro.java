package br.com.fiapbank.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Dinheiro {

    private final BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor monetário não pode ser nulo");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor monetário não pode ser negativo");
        }
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Dinheiro somar(Dinheiro outro) {
        Objects.requireNonNull(outro, "Dinheiro não pode ser nulo");
        return new Dinheiro(this.valor.add(outro.valor));
    }

    public Dinheiro subtrair(Dinheiro outro) {
        Objects.requireNonNull(outro, "Dinheiro não pode ser nulo");
        BigDecimal resultado = this.valor.subtract(outro.valor);
        if (resultado.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para subtração");
        }
        return new Dinheiro(resultado);
    }

    public int comparar(Dinheiro outro) {
        Objects.requireNonNull(outro, "Dinheiro não pode ser nulo");
        return this.valor.compareTo(outro.valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dinheiro other)) return false;
        return Objects.equals(valor, other.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor.toPlainString();
    }
}

