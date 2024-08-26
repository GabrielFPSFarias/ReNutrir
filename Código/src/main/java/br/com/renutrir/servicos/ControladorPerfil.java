package br.com.renutrir.servicos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import br.com.renutrir.*;

public class ControladorPerfil {

    //Perfil Doador

    @FXML
    private Label labelNome;

    @FXML
    private Label labelNomeUsuario;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelCpf;

    private String caminhoArquivoDoador = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo.txt";

    @FXML
    private void carregarPerfilDoador() {
        carregarDadosDoador();
    }

    private void carregarDadosDoador() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoDoador))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    labelNome.setText("Nome: " + linha.substring(6));
                } else if (linha.startsWith("Nome de Usuário: ")) {
                    labelNomeUsuario.setText("Nome de Usuário: " + linha.substring(17));
                } else if (linha.startsWith("Email: ")) {
                    labelEmail.setText("Email: " + linha.substring(7));
                } else if (linha.startsWith("Telefone: ")) {
                    labelTelefone.setText("Telefone: " + linha.substring(10));
                } else if (linha.startsWith("CPF: ")) {
                    labelCpf.setText("CPF: " + linha.substring(5));
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar os dados do doador.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    //Perfil Instituição

    @FXML
    private Label labelNomeInstituicao;

    @FXML
    private Label labelCNPJ;

    @FXML
    private Label labelEndereco;

    @FXML
    private Label labelTelefoneInstituicao;

    @FXML
    private Label labelEmailInstituicao;

    private String caminhoArquivoInstituicao = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo1.txt";

    @FXML
    private void carregarPerfilInstituicao() {
        carregarDadosInstituicao();
    }

    private void carregarDadosInstituicao() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoInstituicao))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    labelNomeInstituicao.setText("Nome da Instituição: " + linha.substring(6));
                } else if (linha.startsWith("CNPJ: ")) {
                    labelCNPJ.setText("CNPJ: " + linha.substring(6));
                } else if (linha.startsWith("Endereço: ")) {
                    labelEndereco.setText("Endereço: " + linha.substring(10));
                } else if (linha.startsWith("Telefone: ")) {
                    labelTelefoneInstituicao.setText("Telefone: " + linha.substring(10));
                } else if (linha.startsWith("Email: ")) {
                    labelEmailInstituicao.setText("Email: " + linha.substring(7));
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar os dados da instituição.");
            e.printStackTrace();
        }
    }

}
