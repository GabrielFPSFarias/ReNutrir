package br.com.renutrir.model;

import java.time.LocalDate;

public class Doacao {
    private String id;
    private String descricao;
    private int quantidade;
    private String doadorId;
    private String instituicaoId;
    private Doador doador;
    private Instituicao instituicao;
    private LocalDate data;

    public Doacao(String id, String descricao, int quantidade, String doadorId, String instituicaoId, Doador doador, Instituicao instituicao, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.doadorId = doadorId;
        this.instituicaoId = instituicaoId;
        this.doador = doador;
        this.instituicao = instituicao;
        this.data = data;
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(String doadorId) {
        this.doadorId = doadorId;
    }

    public String getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(String instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
