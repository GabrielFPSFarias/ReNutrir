package br.com.renutrir.model;

public class Voluntario extends Doador{
    private String disponibilidade; // Ex: dias e horários disponíveis
    private String funcaoVoluntariado; // o cargo que o voluntário vai cumprir

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
}
