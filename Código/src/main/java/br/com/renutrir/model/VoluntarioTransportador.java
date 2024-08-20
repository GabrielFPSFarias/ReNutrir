package br.com.renutrir.model;

public class VoluntarioTransportador extends Voluntario{
    private static int limiteEntregas;

    public static int getLimiteEntregas() {
        return limiteEntregas;
    }

    public static void setLimiteEntregas(int limiteEntregas) {
        VoluntarioTransportador.limiteEntregas = limiteEntregas;
    }

}
