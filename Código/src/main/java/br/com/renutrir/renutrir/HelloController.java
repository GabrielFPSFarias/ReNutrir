package br.com.renutrir.renutrir;

import br.com.renutrir.model.Endereco;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.model.Doador;
import br.com.renutrir.servicos.*;
import br.com.renutrir.main.*;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
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
        String nome = fieldNome.getText();
        String nomeUsuario = fieldUserNome.getText();
        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();
        String confSenha = fieldConfSenha.getText();
        String telefone = fieldTelefone.getText();
        String cpf = fieldCpf.getText();
        String endereco = fieldEndereco.getText();
        String bairro = fieldBairro.getText();
        String numero = fieldNumero.getText();
        String municipio = fieldCidade.getText();
        String uf = fieldUf.getText();
        String comp = fieldComp.getText();
        String ref = fieldRef.getText();

        if (email == null || email.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail não pode estar vazio.");
            return;
        }

        if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail deve conter um '@'.");
            return;
        }

        if (senha == null || senha.length() < 4) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A senha deve ter pelo menos 4 caracteres.");
            return;
        }

        if (!senha.equals(confSenha)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "As senhas não correspondem.");
            return;
        }

        Doador doador = new Doador();
        try {
            doador.setNome(nome);
            doador.setNomeUsuario(nomeUsuario);
            doador.setEmail(email);
            doador.setSenha(senha);
            doador.setTelefone(telefone);
            doador.setCpf(cpf);
            doador.setEndereco(new Endereco(endereco, bairro, numero, municipio, uf, comp, ref));

            salvarDadosEmArquivo(doador);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro de doador confirmado!");
            System.out.println("Cadastro confirmado.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao Confirmar Cadastro", e.getMessage());
        }
    }

    private void salvarDadosEmArquivo(Doador doador) {
        String caminhoArquivo = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo.txt";
        ControladorArquivo controladorArquivo = new ControladorArquivo(caminhoArquivo);

        try {
            controladorArquivo.escrever("Nome: " + (doador.getNome() != null ? doador.getNome() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Nome de Usuário: " + (doador.getNomeUsuario() != null ? doador.getNomeUsuario() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Email: " + (doador.getEmail() != null ? doador.getEmail() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Telefone: " + (doador.getTelefone() != null ? doador.getTelefone() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("CPF: " + (doador.getCpf() != null ? doador.getCpf() : "Não informado"));
            controladorArquivo.novaLinha();

            if (doador.getEndereco() != null) {
                Endereco endereco = doador.getEndereco();
                controladorArquivo.escrever("Endereço: " +
                        (endereco.getEndereco() != null ? endereco.getEndereco() : "Não informado") + ", " +
                        (endereco.getBairro() != null ? endereco.getBairro() : "Não informado") + ", " +
                        (endereco.getNumero() != null ? endereco.getNumero() : "Não informado") + ", " +
                        (endereco.getCidade() != null ? endereco.getCidade() : "Não informado") + ", " +
                        (endereco.getUf() != null ? endereco.getUf() : "Não informado") + ", " +
                        (endereco.getComplemento() != null ? endereco.getComplemento() : "Não informado") + ", " +
                        (endereco.getReferencia() != null ? endereco.getReferencia() : "Não informado"));
            } else {
                controladorArquivo.escrever("Endereço: Não informado");
            }
            controladorArquivo.novaLinha();
            controladorArquivo.novaLinha();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao Salvar Dados", "Não foi possível salvar os dados no arquivo.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
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
    private TextField fieldCidade;

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

    public void cidadeField() {

    }

    public void ufField() {

    }

    public void compField() {

    }

    //Próximos métodos
}
