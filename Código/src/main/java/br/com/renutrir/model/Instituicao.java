package br.com.renutrir.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public class Instituicao extends Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj;
    private LocalDate dataFundacao;
    private double distanciaMaximaDeColeta;
    private LocalDateTime horarioFuncionamento;
    private LocalDateTime horarioColeta;
    private boolean precisaVoluntario;
    private String endereco;
    private String descricao;
    private String chavePix;
    private String razaoSocial;
    private String horarioInicial;
    private String horarioFinal;

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Evento criarEvento(String nome, LocalDate data, String local, LocalTime horario, String tipo, String descricao) {
        return new Evento(nome, data, local, horario, tipo, descricao, this);
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
        if (!cnpjValidacao(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.cnpj = cnpj;
    }

    public boolean isPresent(List<Instituicao> instituicoes) {
        return instituicoes.stream()
                .anyMatch(instituicao -> this.cnpj.equals(instituicao.getCnpj()) || this.getEmail().equals(instituicao.getEmail()));
    }

    private boolean cnpjValidacao(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }

        char[] cnpjArray = cnpj.toCharArray();
        int[] cnpjInt = new int[14];
        for (int i = 0; i < 14; i++) {
            cnpjInt[i] = cnpjArray[i] - '0';
        }

        int[] multiplicador1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += cnpjInt[i] * multiplicador1[i];
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        int[] multiplicador2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += cnpjInt[i] * multiplicador2[i];
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return (digito1 == cnpjInt[12] && digito2 == cnpjInt[13]);
    }

    public LocalDateTime getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(LocalDateTime horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    @Override
    public void setNomeUsuario(String nomeUsuario) {
        super.setNomeUsuario(nomeUsuario);
    }

    public void solicitarVoluntarios() {
        this.precisaVoluntario = true;
    }

    public void setDescricao(String descricaoInstituicao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
