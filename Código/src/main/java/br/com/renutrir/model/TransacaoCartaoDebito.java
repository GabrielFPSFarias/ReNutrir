package br.com.renutrir.model;

public class TransacaoCartaoDebito extends TransacaoCartaoCredito{

String senha;

    public TransacaoCartaoDebito(double valorTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, doador, instituicao);
    }

    public String getSenha() {
            return senha;
        }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
