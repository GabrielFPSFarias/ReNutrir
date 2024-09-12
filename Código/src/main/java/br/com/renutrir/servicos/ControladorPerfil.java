package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.repositorio.RepositorioDoador;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class ControladorPerfil {

    public ControladorPerfil() {
    }

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

    private String caminhoArquivoDoador = "/src/dados/arquivo.txt";

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

    private String caminhoArquivoInstituicao = "/src/dados/arquivo1.txt";

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


    //-----------------------------------------------------
    //Excluir conta

    @FXML
    private Button botaoExcluirConta;

    @FXML
    private PasswordField fieldSenhaExcluir;

    @FXML
    private Label nomeUserExcluirLabel;

    @FXML
    private Button voltarBotao;

    @FXML
    void botaoVoltar79(ActionEvent event) {
        if (SessaoDoador.getInstancia().getDoadorLogado() != null) {
            realizarTrocaDeTela ("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
        } else if (SessaoInstituicao.getInstancia().getInstituicaoLogada() != null) {
            realizarTrocaDeTela ("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Menu Instituição");
        }

    }

    @FXML
    void excluirContaBotao(ActionEvent event) {
        String senhaDigitada = fieldSenhaExcluir.getText().trim();

        if (SessaoDoador.getInstancia().getDoadorLogado() != null) {
            Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
            if (doador.getSenha().equals(senhaDigitada)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação de Exclusão");
                alert.setHeaderText(null);
                alert.setContentText("A exclusão da conta é irreversível. Todos os dados associados serão permanentemente removidos. Deseja continuar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {

                    RepositorioDoador repositorioDoador = new RepositorioDoador();
                    repositorioDoador.removerDoador(doador.getCpf());

                    SessaoDoador.getInstancia().limparSessao();
                    showAlert(Alert.AlertType.INFORMATION, "Exclusão de Conta", "Sua conta foi excluída com sucesso.");
                    realizarTrocaDeTela ("/br/com/renutrir/01-tela-inicial.fxml", "ReNutrir");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Senha incorreta.");
            }
        } else if (SessaoInstituicao.getInstancia().getInstituicaoLogada() != null) {
            Instituicao instituicao = SessaoInstituicao.getInstancia().getInstituicaoLogada();
            if (instituicao.getSenha().equals(senhaDigitada)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação de Exclusão");
                alert.setHeaderText(null);
                alert.setContentText("A exclusão da conta é irreversível. Todos os dados associados serão permanentemente removidos. Deseja continuar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();
                    repositorioInstituicao.removerInstituicao(instituicao.getCnpj());

                    SessaoInstituicao.getInstancia().limparSessao();
                    showAlert(Alert.AlertType.INFORMATION, "Exclusão de Conta", "Sua conta foi excluída com sucesso.");
                    realizarTrocaDeTela ("/br/com/renutrir/01-tela-inicial.fxml", "ReNutrir");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Senha incorreta.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhum usuário está logado.");
        }
    }


    @FXML
    void senhaExcluirField(ActionEvent event) {
        String senhaDigitada = fieldSenhaExcluir.getText().trim();

        if (SessaoDoador.getInstancia().getDoadorLogado() != null) {
            Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
            if (doador.getSenha().equals(senhaDigitada)) {
                fieldSenhaExcluir.setStyle("-fx-border-color: green;");
            } else {
                fieldSenhaExcluir.setStyle("-fx-border-color: red;");
            }
        } else if (SessaoInstituicao.getInstancia().getInstituicaoLogada() != null) {
            Instituicao instituicao = SessaoInstituicao.getInstancia().getInstituicaoLogada();
            if (instituicao.getSenha().equals(senhaDigitada)) {
                fieldSenhaExcluir.setStyle("-fx-border-color: green;");
            } else {
                fieldSenhaExcluir.setStyle("-fx-border-color: red;");
            }
        } else {
            fieldSenhaExcluir.setStyle("-fx-border-color: gray;");
        }
    }

    @FXML
    void initialize() {
        fieldSenhaExcluir.textProperty().addListener((observable, oldValue, newValue) -> senhaExcluirField(null));

        if (SessaoDoador.getInstancia().getDoadorLogado() != null) {
            nomeUserExcluirLabel.setText(SessaoDoador.getInstancia().getDoadorLogado().getNomeUsuario());
        } else if (SessaoInstituicao.getInstancia().getInstituicaoLogada() != null) {
            nomeUserExcluirLabel.setText(SessaoInstituicao.getInstancia().getInstituicaoLogada().getNomeUsuario());
        }
    }

    public void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        System.out.println("Clicou: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, fxmlArquivo, titulo);

        if (fxmlArquivo.equals("/br/com/renutrir/03-login.fxml")){
            SessaoDoador.getInstancia().limparSessao();
            SessaoInstituicao.getInstancia().limparSessao();
        } else {
            Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
            if (doadorLogado != null) {
                System.out.println("Doador logado: " + doadorLogado.getNome()); //testar
            }

            Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();
            if (instituicaoLogada != null) {
                System.out.println("Instituição logada: " + instituicaoLogada.getNome()); //testar tbm
            }
        }
    }

    public void trocarTela(Stage stage, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (fxmlFile.equals("/br/com/renutrir/03-login.fxml")) {
                SessaoDoador.getInstancia().limparSessao();
                //se for a tela de login, limpa a sessão.
            }

            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle(title);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
