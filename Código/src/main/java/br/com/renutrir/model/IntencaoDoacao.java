package br.com.renutrir.model;

import java.time.LocalDate;

public class IntencaoDoacao {
    private String tipoItem; // Valor, alimento, Roupas, Móveis, etc.
    private String id;
    private String descricao;
    private int quantidade;
    private LocalDate dataIntencao;
    private String status; // Pendente, Aceita, Concluída
    private String doadorId;

    public IntencaoDoacao(String tipoItem, String id, String descricao, int quantidade, LocalDate dataIntencao, String status, String doadorId) {
        this.tipoItem = tipoItem;
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataIntencao = LocalDate.now();
        this.status = "Pendente";
        this.doadorId = doadorId;
    }

    // Getters e Setters
    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataIntencao() {
        return dataIntencao;
    }

    public void setDataIntencao(LocalDate dataIntencao) {
        this.dataIntencao = dataIntencao;
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

    public String getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(String doadorId) {
        this.doadorId = doadorId;
    }
}
