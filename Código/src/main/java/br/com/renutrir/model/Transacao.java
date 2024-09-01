package br.com.renutrir.model;

public class Transacao {

    private Doador doador;
    private Instituicao instituicao;
    private valorTransacao;

    //Construtor com todos os parâmetros
    public Transacao(double valorTransacao, Doador doador, Instituicao instituicao) {
        this.valorTransacao = valorTransacao;
        this.doador = doador;
        this.instituicao = instituicao;
    }

    // Getters e Setters
    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
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
