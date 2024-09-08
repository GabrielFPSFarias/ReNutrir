package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIntencaoDoacao {

    private final String arquivoIntencao = "src/dados/intencoes-de-doacao.dat";
    private List<IntencaoDoacao> intencoes;

    public RepositorioIntencaoDoacao() {
        intencoes = carregarIntencoes();
    }

    private List<IntencaoDoacao> carregarIntencoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoIntencao))) {
            return (List<IntencaoDoacao>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarIntencoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoIntencao))) {
            oos.writeObject(intencoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarIntencao(IntencaoDoacao intencao) {
        intencoes.add(intencao);
        salvarIntencoes();
    }

    public boolean removerIntencao(IntencaoDoacao intencao) {
        boolean removido = intencoes.remove(intencao);
        if (removido) {
            salvarIntencoes();
        }
        return removido;
    }

    public void atualizarIntencao(IntencaoDoacao intencao) {
        for (int i = 0; i < intencoes.size(); i++) {
            if (intencoes.get(i).equals(intencao)) {
                intencoes.set(i, intencao);
                salvarIntencoes();
                break;
            }
        }
    }

    public IntencaoDoacao buscarIntencao(Doador doador, LocalDateTime data) {
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getDoador().equals(doador) && intencao.getData().equals(data)) {
                return intencao;
            }
        }
        return null;
    }

    public ArrayList<IntencaoDoacao> intencoesPorInst(Instituicao instituicao) {
        ArrayList<IntencaoDoacao> intencoesInst = new ArrayList<>();
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getInstituicao().equals(instituicao)) {
                intencoesInst.add(intencao);
            }
        }
        return intencoesInst;
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes);
    }

    public boolean instRecebe(Instituicao instituicao) {
        for(IntencaoDoacao intencao : this.intencoes){
            if(intencao.getInstituicao().equals(instituicao)){
                return true;
            }
        }
        return false;
    }

    private static Instituicao instituicaoSelecionada;

    public static Instituicao getInstituicaoSelecionada() {
        return instituicaoSelecionada;
    }

    public static void setInstituicaoSelecionada(Instituicao instituicao) {
        instituicaoSelecionada = instituicao;
    }
}