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

    // Remove uma doação do repositório
    public boolean removerDoacao(Doacao doacao) {
        return doacoes.remove(doacao);
    }

    // Atualiza uma doação existente
    public void atualizarDoacao(Doacao doacao) {
        for (int i = 0; i < doacoes.size(); i++) {
            if (doacoes.get(i).equals(doacao)) {
                doacoes.set(i, doacao);
                break;
            }
        }
    }

    // Busca uma doação
    public Doacao buscarDoacao(Doacao d) {
        for (Doacao doacao : doacoes) {
            if (doacao.equals(d)) {
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
