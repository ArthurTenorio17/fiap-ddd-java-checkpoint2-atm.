package br.com.fiapbank.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Conta extends BaseEntity {

    private Dinheiro saldo;
    private final List<Movimentacao> historico;

    protected Conta(Dinheiro saldoInicial) {
        super();
        this.saldo = (saldoInicial == null) ? new Dinheiro(java.math.BigDecimal.ZERO) : saldoInicial;
        this.historico = new ArrayList<>();
    }

    public Dinheiro consultarSaldo() {
        return saldo;
    }

    public List<Movimentacao> consultarHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void depositar(Dinheiro valor) {
        validarValorPositivo(valor);
        saldo = saldo.somar(valor);
        registrarMovimentacao("DEPOSITO", valor);
    }

    public final void realizarSaque(Dinheiro valor) {
        validarValorPositivo(valor);
        validarSaldo(valor);

        saldo = saldo.subtrair(valor);
        registrarMovimentacao("SAQUE", valor);

        aplicarRegraDeTaxa();
    }

    protected abstract void aplicarRegraDeTaxa();

    protected void registrarTaxa(Dinheiro taxa) {
        if (taxa == null) {
            throw new IllegalArgumentException("Taxa não pode ser nula");
        }
        if (taxa.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return;
        }
        if (saldo.comparar(taxa) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para cobrança de taxa");
        }
        saldo = saldo.subtrair(taxa);
        registrarMovimentacao("TAXA", taxa);
    }

    protected void registrarRendimento(Dinheiro rendimento) {
        if (rendimento == null) {
            throw new IllegalArgumentException("Rendimento não pode ser nulo");
        }
        if (rendimento.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return;
        }
        saldo = saldo.somar(rendimento);
        registrarMovimentacao("RENDIMENTO", rendimento);
    }

    private void registrarMovimentacao(String tipo, Dinheiro valor) {
        Objects.requireNonNull(tipo, "Tipo deve existir");
        Objects.requireNonNull(valor, "Valor deve existir");
        historico.add(new Movimentacao(LocalDateTime.now(), tipo, valor));
    }

    private void validarValorPositivo(Dinheiro valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        if (valor.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    private void validarSaldo(Dinheiro valor) {
        if (saldo.comparar(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}

