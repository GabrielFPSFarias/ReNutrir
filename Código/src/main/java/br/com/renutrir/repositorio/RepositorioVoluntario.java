package br.com.renutrir.repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVoluntario {
    private static final String CAMINHO_ARQUIVO = "src/dados/funcoesVoluntario.dat";
    private List<String> funcoesVoluntario;

    public RepositorioVoluntario() {
        funcoesVoluntario = carregarFuncoesVoluntario();
    }

    private List<String> carregarFuncoesVoluntario() {
        File file = new File(CAMINHO_ARQUIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvarFuncoesVoluntario() {
        File diretorio = new File("src/dados");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(new File(CAMINHO_ARQUIVO));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(funcoesVoluntario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarFuncao(String funcao) {
        funcoesVoluntario.add(funcao);
        salvarFuncoesVoluntario();
    }

    public List<String> listarFuncoesVoluntario() {
        return new ArrayList<>(funcoesVoluntario);
    }

    public void removerFuncao(String funcao) {
        funcoesVoluntario.remove(funcao);
        salvarFuncoesVoluntario();
    }
}
