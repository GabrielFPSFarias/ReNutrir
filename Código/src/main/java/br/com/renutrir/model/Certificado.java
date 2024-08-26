package br.com.renutrir.model;

import java.time.LocalDate;

public class Certificado {
    private String descricao;
    private LocalDate dataEmissao;
    private int quantDoacoes;
    private static final int DOACOES_NECESSARIAS = 50;

    // Construtor geral
    public Certificado(String descricao, LocalDate dataEmissao, int quantDoacoes) {
        this.descricao = descricao;
        this.dataEmissao = dataEmissao;
        this.quantDoacoes = quantDoacoes;
    }


    // Construtor padrão
    public Certificado() {
        this.quantDoacoes = 0;
    }

    // Getters e Setters
    public int getQuantDoacoes() {
        return quantDoacoes;
    }

    public void setQuantDoacoes(int quantDoacoes) {
        this.quantDoacoes = quantDoacoes;
        verificarEConcederCertificado();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    private void verificarEConcederCertificado() {
        if (this.quantDoacoes >= DOACOES_NECESSARIAS) {
            emitirCertificado();
        }
    }

    //Controlador certificado?
    
    private void emitirCertificado() {
        this.descricao = "Certificado concedido por atingir " + DOACOES_NECESSARIAS + " doações.";
        this.dataEmissao = LocalDate.now();
        System.out.println("Certificado emitido: " + this.descricao + " em " + this.dataEmissao);
    }

    //Controlador certficado?

    //Colocar o nome do doador?

    public void adicionarDoacao() {
        this.quantDoacoes++;
        verificarEConcederCertificado();
    }

    //Controlador certficado?


    public boolean isCertificadoEmitido() {
        return this.dataEmissao != null;
    }
}
