package br.com.renutrir.repositorio;

import br.com.renutrir.model.Certificado;
import br.com.renutrir.model.Doador;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RepositorioCertificado {

    private Map<String, Certificado> certificados;

    public RepositorioCertificado() {
        this.certificados = new HashMap<>();
        carregarCertificados();
    }

    public void adicionarCertificado(Doador doador, Certificado certificado) {
        certificados.put(doador.getNomeUsuario(), certificado);
        salvarCertificados();
    }

    public Certificado obterCertificado(String nomeUsuario) {
        return certificados.get(nomeUsuario);
    }

    public boolean verificarCertificado(String nomeUsuario) {
        return certificados.containsKey(nomeUsuario);
    }

    public void removerCertificado(String nomeUsuario) {
        certificados.remove(nomeUsuario);
        salvarCertificados();
    }

    private void carregarCertificados() {
        File arquivo = new File("src/dados/certificados.txt");
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nomeUsuario = partes[0];
                    String descricao = partes[1];
                    Certificado certificado = new Certificado();
                    certificado.setDescricao(descricao);
                    certificados.put(nomeUsuario, certificado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarCertificados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dados/certificados.txt"))) {
            for (Map.Entry<String, Certificado> entry : certificados.entrySet()) {
                String nomeUsuario = entry.getKey();
                Certificado certificado = entry.getValue();
                writer.write(nomeUsuario + ";" + certificado.getDescricao());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
