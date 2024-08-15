package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Doador extends Cadastro{
    private int nivel;
    private LocalDate dataNascimento;
    private Certificado certificado;

    // Construtor, getters e setters

    public void doar(Doacao doacao) {
        // Implementação do método de doação
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public void setCertificado(Certificado certificado){
        this.certificado = certificado;
    }

    public void setDataNascimento(String dataNascimentoStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
            if (dataNascimento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
            }
            this.dataNascimento = dataNascimento;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dia/mês/ano.");
        }
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
