package br.com.renutrir.model;

public class VoluntarioTransportador extends Voluntario {
    private static int limiteEntregas;

    public VoluntarioTransportador(String tipoVoluntario) {
        super(tipoVoluntario);
    }

    public VoluntarioTransportador(String nome, String email, String telefone, String disponibilidade, String funcaoVoluntariado, Instituicao instituicaoVinculada) {
        super(nome, email, telefone, disponibilidade, funcaoVoluntariado, instituicaoVinculada);
    }

    public static int getLimiteEntregas() {
        return limiteEntregas;
    }

    public static void setLimiteEntregas(int limiteEntregas) {
        VoluntarioTransportador.limiteEntregas = limiteEntregas;
    }

}
