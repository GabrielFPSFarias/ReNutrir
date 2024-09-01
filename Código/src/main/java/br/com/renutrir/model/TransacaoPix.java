package br.com.renutrir.model;

public class TransacaoPix extends Transacao {
    private String idTransacao;

    public TransacaoPix(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, doador, instituicao);
        this.idTransacao = idTransacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }
}


/*
package br.com.renutrir.model;

public class TransacaoPix extends Transacao {
    public TransacaoPix(double valorTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, doador, instituicao);
    }

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
<<<<<<< HEAD

    public TransacaoPix(double valorTransacao, String idTransacao, Doador doador, Instituicao instituicao) {
        super(valorTransacao, idTransacao, doador, instituicao);
    }


=======
        >>>>>>> 3124b7805b2ff7cdc5ba84bd029ffebd65ace228

}
*/