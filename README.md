# FIAP Bank ATM - Checkpoint 2

## 📌 Sobre o Projeto

Este projeto representa a continuação e evolução do Checkpoint 1 do FIAP Bank ATM. Na primeira versão, o sistema foi desenvolvido de forma procedural, concentrando toda a lógica dentro do método `main`. Neste Checkpoint 2, o sistema foi totalmente refatorado para uma arquitetura orientada a objetos baseada em Domain-Driven Design (DDD), separação em camadas e boas práticas de desenvolvimento Java.

Este projeto foi desenvolvido para o Checkpoint 2 da disciplina de Java/Orientação a Objetos da FIAP.

O sistema simula um Caixa Eletrônico (ATM) do FIAP Bank utilizando conceitos avançados de:

* Orientação a Objetos
* Domain-Driven Design (DDD)
* Encapsulamento
* Herança
* Polimorfismo
* Classes Abstratas
* Template Method
* Value Objects
* Arquitetura em Camadas

O objetivo principal foi refatorar um sistema procedural para uma arquitetura profissional baseada em objetos.

---

# 🏗️ Arquitetura do Projeto

O sistema foi dividido em camadas conforme solicitado no checkpoint:

```text
src/main/java/br/com/fiapbank
│
├── application
├── infrastructure
├── model
└── presentation
```

## 📂 application

Responsável pela inicialização e orquestração do sistema.

Contém:

* Main.java

---

## 📂 infrastructure

Responsável pelo armazenamento em memória e simulação de persistência.

Contém:

* ContaRepository.java

---

## 📂 model

Camada principal do domínio da aplicação.

Contém:

* BaseEntity
* Cliente
* Conta
* ContaCorrente
* ContaPoupanca
* ContaAcesso
* Dinheiro
* Movimentacao

---

## 📂 presentation

Responsável pela interface via terminal.

Contém:

* Menu.java

---

# 🚀 Funcionalidades

O sistema possui:

✅ Autenticação por senha

✅ Consulta de saldo

✅ Depósito

✅ Saque

✅ Histórico de movimentações

✅ Taxa de saque para Conta Corrente

✅ Rendimento para Conta Poupança

✅ Registro automático de movimentações

---

# 💰 Histórico de Movimentações

Cada operação realizada gera automaticamente um registro contendo:

* Data/Hora
* Tipo da operação
* Valor

Exemplos:

```text
DEPOSITO
SAQUE
TAXA
RENDIMENTO
```

---

# 🧠 Conceitos Aplicados

## Encapsulamento

Todos os atributos foram encapsulados utilizando `private`.

---

## Herança

A classe `Conta` foi implementada como classe abstrata.

As classes:

* ContaCorrente
* ContaPoupanca

herdam seus comportamentos.

---

## Polimorfismo

As contas são manipuladas através da abstração:

```java
Conta conta = new ContaCorrente(...)
```

---

## Template Method

O saque foi implementado utilizando o padrão Template Method.

Fluxo:

```text
validar saldo
↓
debitar valor
↓
registrar movimentação
↓
aplicar regra específica
```

---

## Value Objects

Foram implementados os seguintes Value Objects:

* Dinheiro
* ContaAcesso
* Movimentacao

---

# 🔐 Validação de Senha

A senha exige:

* mínimo de 6 caracteres
* letra maiúscula
* número
* caractere especial

---

# ⚙️ Tecnologias Utilizadas

* Java 17+
* Maven
* VSCode

---

# ▶️ Como Executar

## Compilar o projeto

```bash
mvn compile
```

---

## Executar o sistema

```bash
mvn exec:java -Dexec.mainClass="br.com.fiapbank.application.Main"
```

---

# 👨‍💻 Autor

Arthur Tenorio

Estudante de Engenharia de Software - FIAP
