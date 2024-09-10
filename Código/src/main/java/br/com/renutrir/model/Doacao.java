package br.com.renutrir.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Doacao extends IntencaoDoacao {
    private static final long serialVersionUID = 1L;

    public Doacao(Doador doador, Instituicao instituicao, int quantidade, String tipoItem, String item) {
        super(doador, instituicao, quantidade, tipoItem, item);
        this.setStatus("Concluída");
        this.setData(LocalDateTime.now());
    }

    public Doacao(String nomeUsuario, String itemSelecionado, int quantidade, String dataHora, String status) {
        this.setStatus("Concluída");
        this.setData(LocalDateTime.now());
    }
}

/*
package br.com.renutrir.model;

import br.com.renutrir.sessao.SessaoDoador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Doacao extends IntencaoDoacao implements Serializable {

    private static final long serialVersionUID = 1L;

    public Doacao(IntencaoDoacao doacao) {
        super();
        this.setData(LocalDateTime.now());
        this.setDoador(doacao.getDoador());
        this.setInstituicao(doacao.getInstituicao());
        this.setQuantidade(doacao.getQuantidade());
        this.setStatus("Concluída");
        this.setTipoItem(doacao.getTipoItem());
        this.setTipoItem(doacao.getItem());
    }

    private static String valorDoacao;

    public Doacao(String nomeUsuario, String itemSelecionado, int i, String dataHora, String status) {
    }

    public static void setValorDoacao(String valor) {
        valorDoacao = valor;
    }

    public static String getValorDoacao() {
        return valorDoacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntencaoDoacao that = (Doacao) o;
        return Objects.equals(getData(), that.getData()) && Objects.equals(getDoador(), that.getDoador()) && Objects.equals(getInstituicao(), that.getInstituicao());
    }

    public String getNomeDoador() {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        if (doadorLogado != null) {
            return doadorLogado.getNomeUsuario();
        } else {
            return "Desconhecido";
        }
    }

}
*/