package br.com.renutrir.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Doacao extends IntencaoDoacao {

    public Doacao(IntencaoDoacao doacao) {
        super();
        this.setData(LocalDateTime.now());
        this.setDoador(doacao.getDoador());
        this.setInstituicao(doacao.getInstituicao());
        this.setQuantidade(doacao.getQuantidade());
        this.setStatus("Concluída");
        this.setTipoItem(doacao.getTipoItem());
        this.setTipo(doacao.getTipo());
    }

    // Getters e Setters

    public int getQuantidade() {
        return getQuantidade();
    }

    public void setQuantidade(int quantidade) {
        this.setQuantidade(quantidade);
    }

    public LocalDateTime getData() {
        return getData();
    }

    public void setData(LocalDateTime data) {
        this.setData(data);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntencaoDoacao that = (Doacao) o;
        return Objects.equals(getData(), that.getData()) && Objects.equals(getDoador(), that.getDoador()) && Objects.equals(getInstituicao(), that.getInstituicao());
    }
}
