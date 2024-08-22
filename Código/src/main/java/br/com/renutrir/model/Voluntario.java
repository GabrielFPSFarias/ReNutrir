package br.com.renutrir.model;

import java.util.Objects;

public class Voluntario extends Doador {

    private String disponibilidade; // Ex: dias e horários disponíveis
    private String funcaoVoluntariado; // o cargo que o voluntário vai cumprir
    private String instituicaoVinculada; // refere-se à instituição a que o voluntário está prestando serviço

    public Voluntario() {
    }
    
    public Voluntario(String nome, String email, String telefone, String disponibilidade,
                      String funcaoVoluntariado, String instituicaoVinculada) {
        super(nome, email, telefone);
        this.disponibilidade = disponibilidade;
        this.funcaoVoluntariado = funcaoVoluntariado;
        this.instituicaoVinculada = instituicaoVinculada;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getFuncaoVoluntariado() {
        return funcaoVoluntariado;
    }

    public void setFuncaoVoluntariado(String funcaoVoluntariado) {
        this.funcaoVoluntariado = funcaoVoluntariado;
    }

    public String getInstituicaoVinculada() {
        return instituicaoVinculada;
    }

    public void setInstituicaoVinculada(String instituicaoVinculada) {
        this.instituicaoVinculada = instituicaoVinculada;
    }

    // Verificar a disponibilidade
    public boolean estaDisponivel(String dia) {
        return disponibilidade != null && disponibilidade.contains(dia);
    }

    // Verificar se o voluntário está vinculado
    public boolean estaVinculadoA(String instituicao) {
        return instituicaoVinculada != null && instituicaoVinculada.equalsIgnoreCase(instituicao);
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", disponibilidade='" + disponibilidade + '\'' +
                ", funcaoVoluntariado='" + funcaoVoluntariado + '\'' +
                ", instituicaoVinculada='" + instituicaoVinculada + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        if (!super.equals(o)) return false;
        Voluntario that = (Voluntario) o;
        return Objects.equals(disponibilidade, that.disponibilidade) &&
                Objects.equals(funcaoVoluntariado, that.funcaoVoluntariado) &&
                Objects.equals(instituicaoVinculada, that.instituicaoVinculada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disponibilidade, funcaoVoluntariado, instituicaoVinculada);
    }
}

