package br.com.renutrir.model;

public class TransacaoPix extends Transacao {
    
    private String idTransacao;

    // Construtor com todos os parâmetros
    public TransacaoPix(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, doador, instituicao); // Correção: Remover o idTransacao aqui, já que Transacao não tem esse parâmetro
        this.idTransacao = idTransacao;
    }

    // Getter e Setter para idTransacao
    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }
}
