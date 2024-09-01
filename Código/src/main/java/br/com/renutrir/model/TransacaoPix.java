package br.com.renutrir.model;

public class TransacaoPix extends Transacao{

    private double valorTransacao;
    private String idTransacao;

     public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    // Getter e Setter para idTransacao
    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

}
