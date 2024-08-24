package br.com.renutrir.renutrir;

import br.com.renutrir.repositorio.RepositorioContas;
import br.com.renutrir.servicos.ControladorCadastro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class HelloController {

    @FXML
    private Button loginBotao;

    @FXML
    private Button cadastroBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    private Button confCad;

    @FXML
    private Button confCadIns;

    @FXML
    private Button doadorBotao;

    @FXML
    private Button instBotao;

    private void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        System.out.println("Clicou para voltar: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, fxmlArquivo, titulo);
    }

    @FXML
    public void botaoVoltar() {
        realizarTrocaDeTela("01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoVoltar1() {
        realizarTrocaDeTela("02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar2() {
        realizarTrocaDeTela("02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar3() {
        realizarTrocaDeTela("01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoLogin() {
        System.out.println("Login");
        Stage stage = (Stage) loginBotao.getScene().getWindow();
        trocarTela(stage, "03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoCadastro() {
        System.out.println("Cadastro");
        Stage stage = (Stage) cadastroBotao.getScene().getWindow();
        trocarTela(stage, "02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoSouInstituicao() {
        System.out.println("Cadastro instituição");
        Stage stage = (Stage) instBotao.getScene().getWindow();
        trocarTela(stage, "02-cadastro-instituicao.fxml", "ReNutrir - Cadastro Instituição");
    }

    @FXML
    public void botaoSouDoador() {
        System.out.println("Cadastro doador");
        Stage stage = (Stage) doadorBotao.getScene().getWindow();
        trocarTela(stage, "02-cadastro-doador.fxml", "ReNutrir - Cadastro Doador");
    }

    @FXML
    public void confirmarCadastro() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro de doador confirmado!");
        System.out.println("Cadastro confirmado.");
        alert.showAndWait();
    }

    @FXML
    public void confirmarCadastroIns() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro de instituição confirmado!");
        System.out.println("Cadastro confirmado.");
        alert.showAndWait();
    }

    private void trocarTela(Stage stage, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle(title);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Variáveis do cadastro doador

    private ControladorCadastro controladorCadastro;
    private RepositorioContas repositorioContas;

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldUserNome;

    @FXML
    private TextField fieldCep;

    @FXML
    private TextField fieldEndereco;

    @FXML
    private TextField fieldBairro;

    @FXML
    private TextField fieldNumero;

    @FXML
    private TextField fieldMunicipio;

    @FXML
    private TextField fieldUf;

    @FXML
    private TextField fieldComp;

    @FXML
    private TextField fieldRef;

    @FXML
    private TextField fieldCpf;

    @FXML
    private TextField fieldTelefone;

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private PasswordField fieldConfSenha;

    //Métodos do cadastro doador

    public void emailField() {

    }

    public void nomeField() {

    }

    public void cepField() {

    }

    public void cpfField() {

    }

    public void nomeUserField() {

    }

    public void senhaField() {

    }

    public void confSenhaField() {

    }

    public void telefoneField() {

    }

    public void enderecoField() {

    }

    public void refField() {

    }

    public void bairroField() {

    }

    public void numField() {

    }

    public void municipioField() {

    }

    public void ufField() {

    }

    public void compField() {

    }

    //Próximos métodos
}
