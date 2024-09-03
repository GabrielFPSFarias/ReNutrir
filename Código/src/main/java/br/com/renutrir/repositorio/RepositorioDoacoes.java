package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doacao;
import br.com.renutrir.model.Doador;
import br.com.renutrir.sessao.SessaoDoador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDoacoes {

    Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
    String nomeUsuario = doadorLogado.getNomeUsuario();

    private List<Doacao> doacoes;
    private final String arquivoDoacoes = "src/dados/"+ nomeUsuario +"doacoes.dat";

    public RepositorioDoacoes() {
        this.doacoes = carregarDoacoes();
    }

    private List<Doacao> carregarDoacoes() {
        File file = new File(arquivoDoacoes);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoDoacoes))) {
            return (List<Doacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void adicionarDoacao(Doacao doacao) {
        doacoes.add(doacao);
        salvarDoacoes();
    }

    private void salvarDoacoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDoacoes))) {
            oos.writeObject(doacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean removerDoacao(Doacao doacao) {
        boolean removed = doacoes.remove(doacao);
        if (removed) {
            salvarDoacoes();
        }
        return removed;
    }

    public void atualizarDoacao(Doacao doacao) {
        for (int i = 0; i < doacoes.size(); i++) {
            if (doacoes.get(i).equals(doacao)) {
                doacoes.set(i, doacao);
                salvarDoacoes();
                break;
            }
        }
    }

    public Doacao buscarDoacao(Doacao d) {
        for (Doacao doacao : doacoes) {
            if (doacao.equals(d)) {
                return doacao;
            }
        }
        return null;
    }

    public List<Doacao> listarDoacoes() {
        return new ArrayList<>(doacoes); // Retorna uma cópia da lista
    }
}

/*
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
*/