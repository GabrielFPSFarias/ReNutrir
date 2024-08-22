package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doacao;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDoacoes {

    private List<Doacao> doacoes;

    public RepositorioDoacoes() {
        this.doacoes = new ArrayList<>();
    }

    // Adiciona uma nova doação ao repositório
    public void adicionarDoacao(Doacao doacao) {
        doacoes.add(doacao);
    }

    // Remove uma doação do repositório pelo ID
    public boolean removerDoacao(Doacao doacao) {
        return doacoes.remove(doacao);
    }

    // Atualiza uma doação existente
    public void atualizarDoacao(Doacao doacao) {
        for (int i = 0; i < doacoes.size(); i++) {
            if (doacoes.get(i).getId().equals(doacao.getId())) {
                doacoes.set(i, doacao);
                break;
            }
        }
    }

    // Busca uma doação pelo ID
    public Doacao buscarDoacaoPorId(String id) {
        for (Doacao doacao : doacoes) {
            if (doacao.getId().equals(id)) {
                return doacao;
            }
        }
        return null;
    }

    // retorna todas as doações
    public List<Doacao> listarDoacoes() {
        return new ArrayList<>(doacoes); // Retorna uma cópia da lista
    }
}
