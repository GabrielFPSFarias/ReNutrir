package br.com.renutrir.renutrir;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label textoTeste;

    @FXML
    public void botaoLogin() {
        textoTeste.setText("Clicou no botão login");
        System.out.println("Login");
    }

    @FXML
    public void botaoCadastro() {
        textoTeste.setText("Clicou no botão cadastro");
        System.out.println("Cadastro");
    }
}
