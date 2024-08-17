package br.com.renutrir.model;

import java.time.LocalDate;

public class IntencaoDoacao extends Doador {
    private String tipoItem; // Alimento, Roupas, Móveis, etc.
    private int quantidade;
    private String descricao;
    private LocalDate dataIntencao;
    private String status; // Pendente, Aceita, Concluída

    // Construtor
    public IntencaoDoacao(String tipoItem, int quantidade, String descricao) {
        this.tipoItem = tipoItem;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.dataIntencao = LocalDate.now();
        this.status = "Pendente";
    }

    // Getters e Setters
    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
