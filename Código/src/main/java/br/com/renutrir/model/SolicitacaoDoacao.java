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