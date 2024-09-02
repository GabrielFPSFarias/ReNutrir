package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.IntencaoDoacao;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIntencaoDoacao {

    private List<IntencaoDoacao> intencoes;

    // Remove uma intenção de doação do repositório pelo ID
    public boolean removerIntencao(IntencaoDoacao intencao) {
        return intencoes.remove(intencao);
    }

    // Atualiza uma intenção de doação existente
    public void atualizarIntencao(IntencaoDoacao intencao) {
        for (int i = 0; i < intencoes.size(); i++) {
            if (intencoes.get(i).equals(intencao)) {
                intencoes.set(i, intencao);
                break;
            }
        }
    }

    public IntencaoDoacao buscarIntencao(Doador doador, LocalDateTime data) {
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getDoador().equals(doador)) {
                if (intencao.getData().equals(data)) {
                    return intencao;
                }
            }
        }
        return null;
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes); //Retorna lista das intençoes
    }

    public RepositorioIntencaoDoacao() {
        this.intencoes = new ArrayList<>();
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void adicionarIntencao(IntencaoDoacao intencao) {
        try {
            salvarIntencaoNoArquivo(intencao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarIntencaoNoArquivo(IntencaoDoacao intencao) throws IOException {
        String nomeUsuario = intencao.getDoador().getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_intencoes.txt"; //usa nomeUsuario

        File arquivo = new File(caminhoArquivo);

        File diretorio = arquivo.getParentFile();
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        try (FileWriter writer = new FileWriter(arquivo, true)) {
            writer.write(intencao.toString() + System.lineSeparator());
        }
    }

    public int contarDoacoes(Doador doador) {
        String nomeUsuario = doador.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_intencoes.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            return (int) reader.lines().count(); // Conta o número de linhas no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}