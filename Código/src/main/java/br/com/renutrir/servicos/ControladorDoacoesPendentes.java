package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorDoacoesPendentes implements Initializable {

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

    @FXML
    public Button voltarBotao;

    //Tela 26
    @FXML
    void botaoVoltar59() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    @FXML
    private ComboBox <IntencaoDoacao> CBoxDPendentes;

public void preencherCBoxDPendentes() {
    if(SessaoInstituicao.getInstancia().getInstituicaoLogada() != null){
        Instituicao instituicao = SessaoInstituicao.getInstancia().getInstituicaoLogada();
    System.out.println("Checando intenções para " + instituicao.getNome());
    CBoxDPendentes = new ComboBox<IntencaoDoacao>();
    RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
    if(repositorioIntencaoDoacao.instRecebe(instituicao)){
        CBoxDPendentes.getItems().clear();
    List<IntencaoDoacao> intencoes = repositorioIntencaoDoacao.getIntencoes();
        for (IntencaoDoacao intencaoDoacao : intencoes) {
            System.out.println(intencaoDoacao);
            if (intencaoDoacao.getInstituicao().getNomeUsuario().equals(instituicao.getNomeUsuario())) {
                System.out.println("É para a instituição");
                CBoxDPendentes.getItems().add(intencaoDoacao);
                System.out.println(CBoxDPendentes.getItems());
            }
            }
        }
    }
    else{
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao();
        intencaoDoacao.setDoador(null);
        CBoxDPendentes.setItems(FXCollections.observableArrayList(intencaoDoacao));
    }
    }

    public Button exibirIDoacaoBotao;
    public void botaoExibirIDoacao(ActionEvent actionEvent) {
    }
    //Tela 26-1

    @FXML
    void botaoVoltar57() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    public Button doacaoRecebidaBotaoF;

    public Label nomeDoadorLabelF;

    public Label intencaoDoacaoLabelF;

    public Label instDestinadaLabelF;

    public Label dataHoraIntencaoLabelF;

    public void botaoDoacaoRecebidaF(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencherCBoxDPendentes();
    }
}