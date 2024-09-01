package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Evento {
    private String nome;
    private LocalDate data;
    private String local;
    private List<Doacao> doacoes;
    private int quantidadeVoluntarios;
    private int doacoesEfetuadas;
    private int pessoasAtendidas;
    private String tipo;
    private LocalTime horario;
    private String descricao;

    public Evento(String nome, LocalDate data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public Evento(String nome, LocalDate data, String local, LocalTime horario, String tipo, String descricao) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.horario = horario;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    public int getQuantidadeVoluntarios() {
        return quantidadeVoluntarios;
    }

    public void setQuantidadeVoluntarios(int quantidadeVoluntarios) {
        this.quantidadeVoluntarios = quantidadeVoluntarios;
    }

    public int getDoacoesEfetuadas() {
        return doacoesEfetuadas;
    }

    public void setDoacoesEfetuadas(int doacoesEfetuadas) {
        this.doacoesEfetuadas = doacoesEfetuadas;
    }

    public int getPessoasAtendidas() {
        return pessoasAtendidas;
    }

    public void setPessoasAtendidas(int pessoasAtendidas) {
        this.pessoasAtendidas = pessoasAtendidas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
