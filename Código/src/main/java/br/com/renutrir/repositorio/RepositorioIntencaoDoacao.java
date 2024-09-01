package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.IntencaoDoacao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIntencaoDoacao {

    private List<IntencaoDoacao> intencoes;

    public RepositorioIntencaoDoacao() {
        this.intencoes = new ArrayList<>();
    }

    // Adiciona uma nova intenção de doação ao repositório
    public void adicionarIntencao(IntencaoDoacao intencao) {
        intencoes.add(intencao);
    }

    // Remove uma intenção de doação do repositório pelo ID
    public boolean removerIntencao(IntencaoDoacao intencao) {
        return intencoes.remove(intencao);
    }

    // Atualiza uma intenção de doação existente
    public void atualizarIntencao(IntencaoDoacao intencao) {
        for (int i = 0; i < intencoes.size(); i++) {
            if (intencoes.get(i).equals(intencao)) {
                intencoes.set(i, intencao);
                break;
            }
        }
    }

    // Busca uma intenção de doação pelo ID
    public IntencaoDoacao buscarIntencao(Doador doador, LocalDateTime data) {
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getDoador().equals(doador)) {
                if (intencao.getData().equals(data)) {
                    return intencao;
                }
            }
        }
        return null;
    }

    // Retorna todas as intenções de doação
    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes); // Retorna uma cópia da lista
    }
}