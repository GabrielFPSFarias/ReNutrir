package br.com.renutrir.repositorio;

import br.com.renutrir.model.Certificado;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCertificado {

    private List<Certificado> certificados;

    public RepositorioCertificado() {
        this.certificados = new ArrayList<>();
    }

    public void adicionarCertificado(Certificado certificado) {
        certificados.add(certificado);
    }

    public List<Certificado> listarCertificados() {
        return new ArrayList<>(certificados);
    }
}
/*
import java.util.HashMap;
import java.util.Map;

public class RepositorioDeCertificados {
    private Map<String, Certificado> certificados;

    public RepositorioDeCertificados() {
        this.certificados = new HashMap<>();
    }

    public void adicionarCertificado(Certificado certificado) {
        certificados.put(certificado.getDoador().getNome(), certificado);
    }

    public Certificado obterCertificado(String nomeDoador) {
        return certificados.get(nomeDoador);
    }

    public void removerCertificado(String nomeDoador) {
        certificados.remove(nomeDoador);
    }

    public void listarCertificados() {
        for (Certificado certificado : certificados.values()) {
            System.out.println(certificado);
        }
    }

    public boolean verificarCertificado(String nomeDoador) {
        return certificados.containsKey(nomeDoador);
    }
}*/
