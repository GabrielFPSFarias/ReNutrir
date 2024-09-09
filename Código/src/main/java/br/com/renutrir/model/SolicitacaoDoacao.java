package br.com.renutrir.model;

import java.io.Serializable;

public class SolicitacaoDoacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipoItem;
    private String item;
    private int quantidade;
    private String nomeInstituicao;
    private String nomeUsuario;
    private int faltam;

    public SolicitacaoDoacao(String tipoItem, String item, int quantidade, String nomeInstituicao, String nomeUsuario) {
        this.tipoItem = tipoItem;
        this.item = item;
        this.quantidade = quantidade;
        this.nomeInstituicao = nomeInstituicao;
        this.nomeUsuario = nomeUsuario;
        this.faltam = quantidade;
    }

    // Getters e Setters

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getFaltam() {
        return faltam;
    }

    public void setFaltam(int faltam) {
        this.faltam = faltam;
    }

    @Override
    public String toString() {
        return "SolicitacaoDoacao{" +
                "tipoItem='" + tipoItem + '\'' +
                ", item='" + item + '\'' +
                ", quantidade=" + quantidade +
                ", nomeInstituicao='" + nomeInstituicao + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", faltam=" + faltam +
                '}';
    }
}


/*
package br.com.renutrir.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SolicitacaoDoacao  {
    Instituicao instituicao;
    int meta;
    String tipoItem;

    public SolicitacaoDoacao(Instituicao instituicao, int meta, String tipoItem) {
        this.instituicao = instituicao;
        this.meta = meta;
        this.tipoItem = tipoItem;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public void salvarSolicitacaoEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("solicitacoes_doacao.txt", true))) {
            writer.write("Instituicao: " + instituicao.getNome() + ", Meta: " + meta + ", Tipo de Item: " + tipoItem);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar solicitação: " + e.getMessage());
        }
    }


}
*/