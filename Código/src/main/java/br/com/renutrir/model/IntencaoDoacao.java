package br.com.renutrir.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class IntencaoDoacao {
    private String tipoItem; // Valor, alimento, Roupas, Móveis, etc.
    private String item;
    private int quantidade;
    private LocalDateTime data;
    private String status; // Pendente, Aceita, Concluída
    private Doador doador;
    private Instituicao instituicao;

    public IntencaoDoacao() {
    }

    public IntencaoDoacao(Doador doador, String item, int quantidade) {
        this(doador, null, quantidade, null, item);
    }

    public IntencaoDoacao(Doador doador, Instituicao instituicao, int quantidade, String tipoItem, String item) {
        this.data = LocalDateTime.now();
        this.doador = doador;
        this.instituicao = instituicao;
        this.quantidade = quantidade;
        this.tipoItem = tipoItem;
        this.item = item;
        this.status = "Pendente";
    }

    // Getters e Setters
    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntencaoDoacao that = (IntencaoDoacao) o;
        return Objects.equals(data, that.data) && Objects.equals(doador, that.doador) && Objects.equals(instituicao, that.instituicao);
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return String.format("Doador: %s, Item: %s, Quantidade: %d, Data: %s, Status: %s",
                doador.getNome(), item, quantidade, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), status);
    }


}
