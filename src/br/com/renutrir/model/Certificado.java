package br.com.renutrir.model;

import java.time.LocalDate;

public class Certificado {
    private String descricao;
    private LocalDate dataEmissao;

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
