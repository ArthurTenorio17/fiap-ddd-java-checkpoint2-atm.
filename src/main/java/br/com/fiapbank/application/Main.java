package br.com.fiapbank.application;

import br.com.fiapbank.infrastructure.ContaRepository;
import br.com.fiapbank.model.ContaAcesso;
import br.com.fiapbank.model.ContaCorrente;
import br.com.fiapbank.model.ContaPoupanca;
import br.com.fiapbank.model.Dinheiro;
import br.com.fiapbank.model.Cliente;
import br.com.fiapbank.model.Conta;

import br.com.fiapbank.presentation.Menu;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Infraestrutura em memória
        ContaRepository repository = new ContaRepository();

        // Dados de exemplo
        Cliente clienteCorrente = new Cliente(
                "Arthur Tenorio",
                new ContaAcesso("Arthur17@")
        );

        Cliente clientePoupanca = new Cliente(
                "Isadora Tenorio",
                new ContaAcesso("Isadora22@")
        );

        // Conta Corrente: taxa fixa após saque
        Conta contaCorrente = new ContaCorrente(
                clienteCorrente,
                new Dinheiro(new BigDecimal("1000.00")),
                new Dinheiro(new BigDecimal("1.00"))
        );

        // Conta Poupança: rendimento = saldoAtual * percentualRendimento
        Conta contaPoupanca = new ContaPoupanca(
                clientePoupanca,
                new Dinheiro(new BigDecimal("500.00")),
                new BigDecimal("0.01")
        );

        repository.salvar(clienteCorrente.getContaAcesso(), contaCorrente);
        repository.salvar(clientePoupanca.getContaAcesso(), contaPoupanca);



        // Aplicação -> Presentation
        new Menu(repository).executar();
    }
}

