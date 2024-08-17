package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Instituicao extends Conta {
    private String cnpj;
    private LocalDate dataFundacao;
    
    public Evento criarEvento(String nome, LocalDate data, String local){
        Evento evento = new Evento(nome, data, local);
        return evento;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
            if (dataFundacao.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data de fundação não pode ser no futuro.");
            }
            this.dataFundacao = dataFundacao;
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
