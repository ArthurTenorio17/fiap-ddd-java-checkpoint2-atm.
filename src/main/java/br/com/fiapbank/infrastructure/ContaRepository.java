package br.com.fiapbank.infrastructure;

import br.com.fiapbank.model.Conta;
import br.com.fiapbank.model.ContaAcesso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ContaRepository {

    private final Map<ContaAcesso, Conta> contas = new HashMap<>();

    public void salvar(ContaAcesso acesso, Conta conta) {
        Objects.requireNonNull(acesso, "Acesso não pode ser nulo");
        Objects.requireNonNull(conta, "Conta não pode ser nula");
        contas.put(acesso, conta);
    }

    public Optional<Conta> buscarPorAcesso(ContaAcesso acesso) {
        Objects.requireNonNull(acesso, "Acesso não pode ser nulo");
        return Optional.ofNullable(contas.get(acesso));
    }
}

