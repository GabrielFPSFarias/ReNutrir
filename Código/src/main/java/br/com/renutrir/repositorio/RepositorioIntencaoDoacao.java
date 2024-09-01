package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.IntencaoDoacao;
import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    // Busca uma intenção de doação pelo ID
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

    // Retorna todas as intenções de doação
    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes); //Retorna lista das intençoes
    }

    public RepositorioIntencaoDoacao() {
        this.intencoes = new ArrayList<>();
    }

    public void adicionarIntencao(IntencaoDoacao intencao) {
        intencoes.add(intencao);
        salvarIntencaoNoArquivo(intencao);
    }

    private void salvarIntencaoNoArquivo(IntencaoDoacao intencao) {
        String nomeUsuario = intencao.getDoador().getNomeUsuario();

        if (nomeUsuario == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nome de usuário não encontrado.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dados/intencoes.txt", true))) {
            writer.write("Nome de Usuário: " + nomeUsuario + "\n");
            writer.write("Item: " + intencao.getItem() + "\n");
            writer.write("Quantidade: " + intencao.getQuantidade() + "\n");
            writer.write("Data: " + intencao.getData() + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}