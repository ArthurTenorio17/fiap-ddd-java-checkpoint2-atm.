package br.com.fiapbank.model;

import java.math.BigDecimal;


public class ContaCorrente extends Conta {

    private final Dinheiro taxaSaque;
    private final Cliente cliente;

    public ContaCorrente(Cliente cliente, Dinheiro saldoInicial, Dinheiro taxaSaque) {
        super(saldoInicial);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;

        if (taxaSaque == null) {
            throw new IllegalArgumentException("Taxa de saque não pode ser nula");
        }
        if (taxaSaque.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de saque não pode ser negativa");
        }
        this.taxaSaque = taxaSaque;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        // Em Conta Corrente, existe taxa após o saque
        registrarTaxa(taxaSaque);
    }
}


