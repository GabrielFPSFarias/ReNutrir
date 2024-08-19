package br.com.renutrir.repositorio;

import br.com.renutrir.model.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDoacoes {

    private List<Doacao> doacoes;

    public RepositorioDoacoes() {
        this.doacoes = new ArrayList<>();
    }

    public void adicionarDoacao(Doacao doacao) {
        doacoes.add(doacao);
    }

    public void removerDoacao(Doacao doacao) {
        doacoes.remove(doacao);
    }

    //Listar todas as doações feitas
    public List<Doacao> listarDoacoes() {
        return new ArrayList<>(doacoes);
    }
}
