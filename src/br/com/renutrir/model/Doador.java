package br.com.renutrir.model;

public class Doador extends Usuario {
    private int nivel;
    private Certificado certificado;

    // Construtor, getters e setters

    public void doar(Doacao doacao) {
        // Implementação do método de doação
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public void setCertificado(Certificado certificado){
        this.certificado = certificado;
    }

    public int getNivel(){
        return this.nivel;
    }

    public Certificado getCertificado(){
        return this.certificado;
    }
}
