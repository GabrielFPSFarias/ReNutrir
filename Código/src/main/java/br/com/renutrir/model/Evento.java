package br.com.renutrir.model;

import java.time.LocalDate;
import java.util.List;

public class Evento {
    private String nome;
    private LocalDate data;
    private String local;
    private List<Doacao> doacoes;
    private int quantidadeVoluntarios;
    private int doacoesEfetuadas;
    private int pessoasAtendidas;

    public Evento(String nome, LocalDate data, String local){
        this.setNome(nome);
        this.setData(data);
        this.setLocal(local);
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
    
    public void setQuantidadeVoluntarios() {
    	this.quantidadeVoluntarios = quantidadeVoluntarios;
    }
    
    public int getQuantidadeVoluntarios() {
    	return quantidadeVoluntarios;
    }
    
    public void setDoacoesEfetuadas(){
    	this.doacoesEfetuadas = doacoesEfetuadas;
    }
    
    public int getDoacoesEfetuadas(){
    	return doacoesEfetuadas;
    }
    
    public void setPessoasAtendidas(){
    	this.pessoasAtendidas = pessoasAtendidas;
    }
    
    public int getPessoasAtendidas(){
    	return pessoasAtendidas;
    }
}
