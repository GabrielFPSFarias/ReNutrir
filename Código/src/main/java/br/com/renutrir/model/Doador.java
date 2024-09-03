package br.com.renutrir.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public class Doador extends Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    private int nivel;
    private LocalDate dataNascimento;
    private Certificado certificado;
    private String cpf;
    private float distanciaMaximaDeEntrega;
    private ArrayList<String> idsDeTransacao;

    public Doador() {
        idsDeTransacao = new ArrayList<>();
    }

    public Doador(String nome, String email, String telefone) {
        super();
        this.setNome(nome);
        idsDeTransacao = new ArrayList<>();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }
        this.dataNascimento = dataNascimento;
    }

    public float getDistanciaMaximaDeEntrega() {
        return distanciaMaximaDeEntrega;
    }

    public void setDistanciaMaximaDeEntrega(float distanciaMaximaDeEntrega) {
        this.distanciaMaximaDeEntrega = distanciaMaximaDeEntrega;
    }

    public ArrayList<String> getIdsDeTransacao() {
        return idsDeTransacao;
    }

    public void setIdsDeTransacao(ArrayList<String> idsDeTransacao) {
        this.idsDeTransacao = idsDeTransacao;
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

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int calcularIdade() {
        if (dataNascimento == null) {
            throw new IllegalStateException("Data de nascimento não foi definida.");
        }
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }

    public void gerarDescricao() {
        if (certificado != null) {
            this.certificado.setDescricao("Certificado emitido para " + this.getNome() +
                    " em reconhecimento às suas " + this.certificado.getQuantDoacoes() + " doações.");
        }
    }

    public IntencaoDoacao doar(Instituicao instituicao, int quantidade, String tipoItem, String item) {
        return new IntencaoDoacao(this, instituicao, quantidade, tipoItem, item);
    }

    private boolean CpfValidacao(String cpf) {
        if ((cpf == null) || !cpf.matches("\\d{11}")) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doador doador = (Doador) o;
        return Objects.equals(cpf, doador.cpf);
    }
}


/*
package br.com.renutrir.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public class Doador extends Conta {
    private int nivel;
    private LocalDate dataNascimento;
    private Certificado certificado;
    private String cpf;
    private float distanciaMaximaDeEntrega;

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public float getDistanciaMaximaDeEntrega() {
        return distanciaMaximaDeEntrega;
    }

    public void setDistanciaMaximaDeEntrega(float distanciaMaximaDeEntrega) {
        this.distanciaMaximaDeEntrega = distanciaMaximaDeEntrega;
    }

    public ArrayList<String> getIdsDeTransacao() {
        return idsDeTransacao;
    }

    public void setIdsDeTransacao(ArrayList<String> idsDeTransacao) {
        this.idsDeTransacao = idsDeTransacao;
    }

    private ArrayList <String> idsDeTransacao;

    public Doador() {
    }

    public Doador(String nome, String email, String telefone) {
        super();
        this.setNome(nome);
        idsDeTransacao = new ArrayList<>();
    }

    // Construtor, getters e setters
    public String getNome() {
        return super.getNome();
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

     public void setCertificado(Certificado certificado) {
         this.certificado = certificado;
     }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }
        this.dataNascimento = dataNascimento;
    }

    public int getNivel() {
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
        if ((cpf == null) || !cpf.matches("\\d{11}")) {
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

    public IntencaoDoacao doar(Instituicao instituicao, int quantidade, String tipoItem, String item) {
return new IntencaoDoacao(this, instituicao, quantidade, tipoItem, item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doador doador = (Doador) o;
        return Objects.equals(cpf, doador.cpf);
    }

}
*/