package br.com.renutrir.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Doacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeDoador;
    private String item;
    private int quantidade;
    private String dataHora;
    private String status;

    public Doacao(String nomeDoador, String item, int quantidade, String dataHora, String status) {
        this.nomeDoador = nomeDoador;
        this.item = item;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.status = status;
    }

    // Getters e Setters
    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doacao doacao = (Doacao) o;
        return quantidade == doacao.quantidade &&
                Objects.equals(nomeDoador, doacao.nomeDoador) &&
                Objects.equals(item, doacao.item) &&
                Objects.equals(dataHora, doacao.dataHora) &&
                Objects.equals(status, doacao.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeDoador, item, quantidade, dataHora, status);
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