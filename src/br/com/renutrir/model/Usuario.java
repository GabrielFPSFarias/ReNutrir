package br.com.renutrir.model;

import br.com.renutrir.servicos.Cadastro;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Usuario extends Cadastro {
    private LocalDate dataNascimento;

    public LocalDate getDataNascimento() {
        return dataNascimento;
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

    public int calcularIdade() {
        if (dataNascimento == null) {
            throw new IllegalStateException("Data de nascimento não foi definida.");
        }
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }
}
