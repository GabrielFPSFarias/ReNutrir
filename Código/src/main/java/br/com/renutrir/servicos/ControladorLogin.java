package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.repositorio.RepositorioDoador;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorLogin implements Initializable {

    public CheckBox checarInstituicao;
    public Button loginEntrarBotao;
    public PasswordField loginSenhaField;
    public TextField loginEmailField;
    private HelloController hc;

    public ControladorLogin(HelloController hc) {
        this.hc = hc;
    }

    public ControladorLogin() {
    }

    public Doador buscarDoadorNoRepositorio(String emailOuUsuario, String senha) {
        RepositorioDoador repositorioDoador = new RepositorioDoador();

        Optional<Doador> doadorOpt = repositorioDoador.buscarDoadorPorEmail(emailOuUsuario);
        if (!doadorOpt.isPresent()) {
            doadorOpt = repositorioDoador.buscarDoadorPorNomeUsuario(emailOuUsuario);
        }

        if (doadorOpt.isPresent()) {
            Doador doador = doadorOpt.get();
            if (doador.getSenha().equals(senha)) {
                return doador;
            }
        }
        return null;
    }

    public Instituicao buscarInstituicaoNoRepositorio(String emailOuUsuario, String senha) {
        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();

        Optional<Instituicao> instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorEmail(emailOuUsuario);
        if (!instituicaoOpt.isPresent()) {
            instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorNomeUsuario(emailOuUsuario);
        }

        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            if (instituicao.getSenha().equals(senha)) {
                return instituicao;
            }
        }
        return null;
    }

    @FXML
    public Button voltarBotao;

    public void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, fxmlArquivo, titulo);

        if (fxmlArquivo.equals("/br/com/renutrir/03-login.fxml")) {
            SessaoDoador.getInstancia().limparSessao();
            SessaoInstituicao.getInstancia().limparSessao();
        } else {
            Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
            if (doadorLogado != null) {
                System.out.println("Doador logado: " + doadorLogado.getNome());
            }

            Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();
            if (instituicaoLogada != null) {
                System.out.println("Instituição logada: " + instituicaoLogada.getNome());
            }
        }
    }

    @FXML
    public void botaoVoltar3() {
        realizarTrocaDeTela("/br/com/renutrir/02-tela-anterior.fxml", "Tela Anterior");
    }

    public void processarLogin(String emailOuUsuario, String senha, boolean isInstituicao) {
        if (isInstituicao) {
            Instituicao instituicao = buscarInstituicaoNoRepositorio(emailOuUsuario, senha);
            if (instituicao != null) {
                SessaoInstituicao.getInstancia().setInstituicaoLogada(instituicao);
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Instituição!");
                realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para instituição.");
            }
        } else {
            Doador doador = buscarDoadorNoRepositorio(emailOuUsuario, senha);
            if (doador != null) {
                SessaoDoador.getInstancia().setDoadorLogado(doador);
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Doador!");
                realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para doador.");
            }
        }
    }
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void trocarTela(Stage stage, String fxmlFile, String title) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void fieldLoginEmail(ActionEvent actionEvent) {
        
    }

    public void fieldLoginSenha(ActionEvent actionEvent) {
    }

    public void botaoLoginEntrar(ActionEvent actionEvent) {
    }

    public void instituicaoChecar(ActionEvent actionEvent) {
    }
}
