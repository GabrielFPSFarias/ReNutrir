package br.com.renutrir.model;

public class Transacao {

    private double valorTransacao;
    private String idTransacao;
    private Doador doador;
    private Instituicao instituicao;

    //Construtor com todos os parâmetros
    public Transacao(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        this.valorTransacao = valorTransacao;
        this.idTransacao = idTransacao;
        this.doador = doador;
        this.instituicao = instituicao;
    }

    public double getValor() {
        return this.valorTransacao;
    }

    // Getters e Setters
    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
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
}
