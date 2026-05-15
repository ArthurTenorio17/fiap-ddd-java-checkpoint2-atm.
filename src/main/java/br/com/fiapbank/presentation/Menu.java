package br.com.fiapbank.presentation;

import br.com.fiapbank.infrastructure.ContaRepository;
import br.com.fiapbank.model.Conta;
import br.com.fiapbank.model.ContaAcesso;
import br.com.fiapbank.model.Dinheiro;
import br.com.fiapbank.model.Movimentacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final ContaRepository repository;

    public Menu(ContaRepository repository) {
        this.repository = repository;
    }

    public void executar() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("========== BEM-VINDO! ==========");
            System.out.println("===== FIAP BANK ATM - BETA =====");

            Conta contaLogada;
            while (true) {
                System.out.println();
                System.out.println("[1] Cadastrar senha");
                System.out.println("[2] Entrar");
                System.out.print("Escolha uma opção: ");

                int opcaoInicial = lerInteiro(scanner);
                try {
                    switch (opcaoInicial) {
                        case 1 -> {
                            cadastrarSenha(scanner);
                            System.out.println("Cadastro realizado! Faça login.");
                            contaLogada = autenticar(scanner);
                        }
                        case 2 -> contaLogada = autenticar(scanner);
                        default -> {
                            System.out.println("Opção inválida!");
                            continue;
                        }
                    }
                    break;
                } catch (RuntimeException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            while (true) {
                mostrarMenu();
                int opcao = lerInteiro(scanner);
                try {
                    switch (opcao) {
                        case 1 -> consultarSaldo(contaLogada);
                        case 2 -> fazerDeposito(contaLogada, scanner);
                        case 3 -> fazerSaque(contaLogada, scanner);
                        case 4 -> consultarHistorico(contaLogada);
                        case 5 -> {
                            System.out.println("Encerrando...");
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
    }

    private void cadastrarSenha(Scanner scanner) {
        System.out.print("Digite a nova senha de acesso: ");
        String senha = scanner.nextLine();
        ContaAcesso acesso = new ContaAcesso(senha);

        br.com.fiapbank.model.Cliente cliente = new br.com.fiapbank.model.Cliente(
                "Cliente Padrão",
                acesso
        );


        // Cria uma conta padrão associada ao cliente, com saldo inicial 0.
        // Usa Conta Corrente com taxa 5.00 para que a movimentação TAXA apareça no histórico.
        Conta contaPadrao = new br.com.fiapbank.model.ContaCorrente(
                cliente,
                new Dinheiro(BigDecimal.ZERO),
                new Dinheiro(new BigDecimal("5.00"))
        );

        repository.salvar(cliente.getContaAcesso(), contaPadrao);

    }

    private Conta autenticar(Scanner scanner) {
        while (true) {
            System.out.print("Digite a senha de acesso: ");
            String senha = scanner.nextLine();

            ContaAcesso acesso = new ContaAcesso(senha);
            return repository.buscarPorAcesso(acesso)
                    .orElseThrow(() -> new IllegalArgumentException("Senha inválida"));

        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("[1] Consultar Saldo");
        System.out.println("[2] Fazer Depósito");
        System.out.println("[3] Fazer Saque");
        System.out.println("[4] Histórico de Movimentações");
        System.out.println("[5] Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerInteiro(Scanner scanner) {
        String raw = scanner.nextLine();
        return Integer.parseInt(raw.trim());
    }

    private void consultarSaldo(Conta conta) {
        System.out.println("Saldo atual: " + conta.consultarSaldo().toString());
    }

    private void fazerDeposito(Conta conta, Scanner scanner) {
        System.out.print("Digite o valor do depósito: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine().trim());
        conta.depositar(new Dinheiro(valor));
        System.out.println("Depósito realizado com sucesso!");
    }

    private void fazerSaque(Conta conta, Scanner scanner) {
        System.out.print("Digite o valor do saque: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine().trim());
        conta.realizarSaque(new Dinheiro(valor));
        System.out.println("Saque realizado com sucesso!");
    }

    private void consultarHistorico(Conta conta) {
        System.out.println("--- Histórico de Movimentações ---");
        List<Movimentacao> historico = conta.consultarHistorico();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if (historico.isEmpty()) {
            System.out.println("Sem movimentações.");
            return;
        }

        for (Movimentacao m : historico) {
            System.out.println(
                    m.getDataHora().format(formatter)
                            + " | " + m.getTipo()
                            + " | " + m.getValor().toString()
            );
        }
    }
}

