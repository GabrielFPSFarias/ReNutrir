package br.com.renutrir.repositorio;

import br.com.renutrir.model.IntencaoDoacao;

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
