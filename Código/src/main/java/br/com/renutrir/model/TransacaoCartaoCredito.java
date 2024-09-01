package br.com.renutrir.model;

public class TransacaoCartaoCredito extends Transacao {

  String numeroCartao;
  String dataValidade;
  String nomeTitular;
  String cvv;

    public TransacaoCartaoCredito(double valorTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, doador, instituicao);
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    // Getter e Setter para dataValidade
    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    // Getter e Setter para nomeTitular
    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    // Getter e Setter para cvv
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

