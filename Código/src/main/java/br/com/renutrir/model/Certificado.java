package br.com.renutrir.model;

import java.time.LocalDate;

public class Certificado {
    private String descricao;
    private LocalDate dataEmissao;
    private int quantDoacoes;

    public Certificado(String descricao, LocalDate dataEmissao, int quantDoacoes) {
        this.descricao = descricao;
        this.dataEmissao = dataEmissao;
        this.quantDoacoes = quantDoacoes;
    }

    public Certificado() {
    }

    public int getQuantDoacoes() {
        return quantDoacoes;
    }

    public void setQuantDoacoes(int quantDoacoes) {
        this.quantDoacoes = quantDoacoes;
    }

    public String getDescricao() {
        return descricao;
    }

     public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
