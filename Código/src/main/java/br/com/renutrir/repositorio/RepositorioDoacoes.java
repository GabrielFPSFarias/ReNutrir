package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doacao;
import br.com.renutrir.model.Doador;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDoacoes {

    private Doador doadorLogado;
    private String nomeUsuario;
    private String arquivoDoacoes;
    private List<Doacao> doacoes;

    /*
    public RepositorioDoacoes() {
        this.doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        if (doadorLogado != null) {
            this.nomeUsuario = doadorLogado.getNomeUsuario();
            this.arquivoDoacoes = "src/dados/" + nomeUsuario + "_doacoes.dat";
            this.doacoes = carregarDoacoes();
        } else {
            System.err.println("Erro: Doador não está logado. Não é possível inicializar o repositório de doações.");
            this.doacoes = new ArrayList<>();
            this.arquivoDoacoes = null;
        }
    }
     */

    public RepositorioDoacoes() {
        this.doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        if (doadorLogado != null) {
            this.nomeUsuario = doadorLogado.getNomeUsuario();
        } else {
            this.nomeUsuario = "default";
        }
        this.arquivoDoacoes = "src/dados/" + nomeUsuario + "_doacoes.dat";
        this.doacoes = carregarDoacoes();
    }

    private List<Doacao> carregarDoacoes() {
        if (arquivoDoacoes == null) {
            return new ArrayList<>();
        }

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
        if (arquivoDoacoes == null) {
            System.err.println("Erro: Não é possível adicionar doação. Caminho do arquivo não está definido.");
            return;
        }

        doacoes.add(doacao);
        salvarDoacoes();
    }

    private void salvarDoacoes() {
        if (arquivoDoacoes == null) {
            System.err.println("Erro: Não é possível salvar doações. Caminho do arquivo não está definido.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDoacoes))) {
            oos.writeObject(doacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarDoacoesEmArquivo(RepositorioDoacoes repositorioDoacoes) {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado == null) {
            return;
        }

        String nomeUsuario = doadorLogado.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_doacoes.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(repositorioDoacoes.listarDoacoes());
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
        return new ArrayList<>(doacoes);
    }
}

