package br.com.fiapbank.model;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {

    private final BigDecimal percentualRendimento;
    private final Cliente cliente;

    public ContaPoupanca(Cliente cliente, Dinheiro saldoInicial, BigDecimal percentualRendimento) {
        super(saldoInicial);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;

        if (percentualRendimento == null) {
            throw new IllegalArgumentException("Percentual de rendimento não pode ser nulo");
        }
        if (percentualRendimento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Percentual de rendimento não pode ser negativo");
        }
        this.percentualRendimento = percentualRendimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        // Em Conta Poupança, após o saque aplica rendimento (juros)
        // rendimento = saldo_atual * percentual
        BigDecimal saldoAtual = consultarSaldo().getValor();
        BigDecimal rendimentoValor = saldoAtual.multiply(percentualRendimento);
        registrarRendimento(new Dinheiro(rendimentoValor));
    }
}


