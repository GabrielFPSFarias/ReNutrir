package br.com.renutrir.model;

import br.com.renutrir.servicos.Cadastro;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Instituicao extends Cadastro {
    private String cnpj;
    private LocalDate dataFundacao;
    
    public Evento criarEvento(String nome, LocalDate data, String local){
        Evento evento = new Evento(nome, data, local);
        return evento;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacaoStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataFundacao = LocalDate.parse(dataFundacaoStr, formatter);
            if (dataFundacao.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data de fundação não pode ser no futuro.");
            }
            this.dataFundacao = dataFundacao;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
        }
    }

    public int calcularFundacao() {
        if (dataFundacao == null) {
            throw new IllegalStateException("Data de fundação não foi definida.");
        }
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataFundacao, dataAtual).getYears();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
