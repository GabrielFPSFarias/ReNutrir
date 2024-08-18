package br.com.renutrir.model;

import java.time.LocalDate;

public class Doacao {
    private String tipo;
    private Doador doador;
    private Instituicao instituicao;
    private LocalDate data;

    private int qtd;

    public Doacao(String tipo, Doador doador, int qtd, Instituicao instituicao){
    setTipo(tipo);
    setDoador(doador);
    setQtd(qtd);
    setInstituicao(instituicao);
    }
    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
