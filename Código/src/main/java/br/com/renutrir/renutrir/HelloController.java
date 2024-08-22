package br.com.renutrir.renutrir;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML
    private Label textoTeste;

    @FXML
    public void botaoLogin() {
        textoTeste.setText("Clicou no botão login");
        System.out.println("Login");
        terceiraTelaLogin();
    }

    @FXML
    public void botaoCadastro() {
        textoTeste.setText("Clicou no botão cadastro");
        System.out.println("Cadastro");
        segundaTelaCadastro();
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void botaoSouInstituicao() {
        cadastroInstituicao();
    }

    @FXML
    public void botaoSouDoador() {
        cadastroDoador();
    }

    private void terceiraTelaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("03-login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle("ReNutrir - Login");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void segundaTelaCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("02-pre-cadastro.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle("ReNutrir - Cadastro");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cadastroInstituicao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("02-cadastro-instituicao.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle("ReNutrir - Cadastro");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cadastroDoador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("02-cadastro-doador.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle("ReNutrir - Cadastro");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
