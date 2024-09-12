package br.com.renutrir.model;

import java.util.ArrayList;
import java.util.Objects;

public class Voluntario extends Doador {

    private String funcaoVoluntariado; // o cargo que o voluntário vai cumprir
    private Instituicao instituicaoVinculada; // refere-se à instituição a que o voluntário está prestando serviço

    public Voluntario(String nome, String email, String telefone,String funcaoVoluntariado) {
        super(nome, email, telefone);

        this.funcaoVoluntariado = funcaoVoluntariado;
        this.instituicaoVinculada = instituicaoVinculada;
    }

    public Voluntario(String tipoVoluntario) {
    }

    public String getFuncaoVoluntariado(String funcao) {
        return funcaoVoluntariado;
    }

    public void setFuncaoVoluntariado(String funcaoVoluntariado) {
        this.funcaoVoluntariado = funcaoVoluntariado;
    }

    public Instituicao getInstituicaoVinculada() {
        return instituicaoVinculada;
    }

    public void setInstituicaoVinculada(Instituicao instituicaoVinculada) {
        this.instituicaoVinculada = instituicaoVinculada;
    }

    // Verificar a disponibilidade

    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", funcaoVoluntariado='" + funcaoVoluntariado + '\'' +
                ", instituicaoVinculada='" + instituicaoVinculada + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(funcaoVoluntariado, that.funcaoVoluntariado) &&
                Objects.equals(instituicaoVinculada, that.instituicaoVinculada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), funcaoVoluntariado, instituicaoVinculada);
    }

    public void voluntariar(){

    }

    //Listar os tipos de voluntários

    private ArrayList<Voluntario> volunt = new ArrayList<>();

    public Voluntario() {
        volunt.add(new Voluntario("Transportador de Doações"));
        volunt.add(new Voluntario("Auxiliar de Eventos"));
    }

    public ArrayList<Voluntario> todos() {
        return volunt;
    }
}
