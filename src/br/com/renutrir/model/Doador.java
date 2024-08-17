package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.renutrir.servicos.Cadastro;

public class Doador extends Conta {
    private int nivel;
    private LocalDate dataNascimento;
    private Certificado certificado;

    // Construtor, getters e setters

    public Doacao doar(String tipo, int qtd, Instituicao instituicao) {
        Doacao doacao = new Doacao(tipo, this, qtd, instituicao);
        return doacao;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public void setCertificado(Certificado certificado){
        this.certificado = certificado;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
            if (dataNascimento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
            }
            this.dataNascimento = dataNascimento;
    }

    public int getNivel(){
        return this.nivel;
    }

    public Certificado getCertificado() {
        return this.certificado;
    }
        public int calcularIdade() {
            if (dataNascimento == null) {
                throw new IllegalStateException("Data de nascimento não foi definida.");
            }
            LocalDate dataAtual = LocalDate.now();
            return Period.between(dataNascimento, dataAtual).getYears();
        }
}
