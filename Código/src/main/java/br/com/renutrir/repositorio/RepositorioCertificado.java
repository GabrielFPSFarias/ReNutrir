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
