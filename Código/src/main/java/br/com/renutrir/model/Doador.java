package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Doador extends Conta {
    private int nivel;
    private LocalDate dataNascimento;
    private Certificado certificado;
    private String cpf;
    private distanciaDeEntrega;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!CpfValidacao(cpf)) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        this.cpf = cpf;
    }

    public void gerarDescricao() {
        this.certificado.setDescricao("Certificado emitido para " + this.getNome() +
                " em reconhecimento às suas " + this.certificado.getQuantDoacoes() + " doações.");
    }

    private boolean CpfValidacao(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        char[] cpfArray = cpf.toCharArray();
        int[] cpfInt = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfInt[i] = cpfArray[i] - '0';
        }

        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += cpfInt[i] * (10 - i);
            soma2 += cpfInt[i] * (11 - i);
        }

        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

        soma2 += digito1 * 2;
        int resto2 = soma2 % 11;
        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

        return (digito1 == cpfInt[9] && digito2 == cpfInt[10]);
    }
}
