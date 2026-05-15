package br.com.fiapbank.model;

import java.util.Objects;

public final class ContaAcesso {

    private final String senha;

    private static final java.util.regex.Pattern SENHA_PATTERN = java.util.regex.Pattern.compile(
            "^(?=.{6,}$)(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).*$"
    );

    public ContaAcesso(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }

        if (!SENHA_PATTERN.matcher(senha).matches()) {
            throw new IllegalArgumentException(
                    "Senha inválida. Requisitos: min 6 caracteres, ao menos 1 letra maiúscula, 1 número e 1 símbolo."
            );
        }

        this.senha = senha;
    }

    public Boolean autenticar(String senhaInformada) {
        if (senhaInformada == null) {
            return false;
        }
        return Objects.equals(this.senha, senhaInformada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ContaAcesso other)) return false;
        return Objects.equals(senha, other.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }
}

