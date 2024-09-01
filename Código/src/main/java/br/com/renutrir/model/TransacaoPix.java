package br.com.renutrir.model;

import br.com.renutrir.model.Transacao;

<<<<<<< HEAD
//
public class TransacaoPix extends Transacao {
=======
    private String idTransacao;


    // Getter e Setter para idTransacao
    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }
>>>>>>> 56aab743836e93d57183773e95d61076cd1447f1

    public TransacaoPix(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, idTransacao, doador, instituicao);
    }
}