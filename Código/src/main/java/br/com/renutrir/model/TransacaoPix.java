package br.com.renutrir.model;

import br.com.renutrir.model.Transacao;

//
public class TransacaoPix extends Transacao {

    public TransacaoPix(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, idTransacao, doador, instituicao);
    }
}