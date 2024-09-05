package br.com.renutrir.servicos;

import br.com.renutrir.model.*;
import br.com.renutrir.renutrir.ProgressAlert;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.*;
import br.com.renutrir.main.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import br.com.renutrir.sessao.*;

import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;

public class ControladorVoluntario implements Initializable {

    public ControladorVoluntario() {
    }

    SessaoInstituicao sessaoInstituicao;

    public Button botaoSolicitarNovosVoluntarios;
    private HelloController hc;

    private ControladorTelas controladorTelas;

    @FXML
    public Button sejaVoluntarioBotao;
    public Button transportesDoacoesBotao;
    public Button transportesPendentesBotao;
    public Button transportesConcluidosBotao;

    public void setHelloController(HelloController hc) {
        this.hc = hc;
    }

    @FXML
    public Button voltarBotao;

    @FXML
    private TextField fieldHoraInicialVoluntario, fieldHoraFinalVoluntario;

    @FXML
    private Text exibirVoluntarioLabel;

    @FXML
    private CheckBox boxSegundaVoluntario, boxTercaVoluntario, boxQuartaVoluntario, boxQuintaVoluntario, boxSextaVoluntario, boxSabadoVoluntario, boxDomingoVoluntario;

    @FXML
    private Button queroVoluntarioBotao;

    @FXML
    private ComboBox<String> cboxFuncaoVoluntario;

    @FXML
    private ComboBox<String> cboxInstVinculada;

    /*
    ObservableList<String> listaFuncaoVoluntario = FXCollections.observableArrayList(
            "Transportador de Doações", "Auxiliar de Eventos"
    );

     */

    private void configurarComboBox() {
        if (cboxFuncaoVoluntario == null) {
            System.out.println("cboxFuncaoVoluntario está null");
            return;
        } if (cboxInstVinculada == null) {
            System.out.println("cboxInstVinculada está null");
            return;
        }
        carregarFuncoesVoluntarios();
        carregarInstituicoes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarComboBox();
    }

    private void carregarFuncoesVoluntarios() {
        ObservableList<String> funcoesVoluntarios = FXCollections.observableArrayList();

        RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();
        List<String> funcaoVoluntario = repositorioVoluntario.carregarFuncoesVoluntario();

        funcoesVoluntarios.addAll(funcaoVoluntario);

        if (funcoesVoluntarios.isEmpty()) {
            cboxFuncaoVoluntario.setItems(FXCollections.observableArrayList("Nenhuma função disponível"));
        } else {
            cboxFuncaoVoluntario.setItems(funcoesVoluntarios);
        }
        cboxFuncaoVoluntario.setValue(cboxFuncaoVoluntario.getItems().get(0));
    }

    private void carregarInstituicoes() {
        ObservableList<String> listaInstituicoes = FXCollections.observableArrayList();

        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();

        for (Instituicao instituicao : instituicoes) {
            listaInstituicoes.add(instituicao.getNome());
        }

        if (listaInstituicoes.isEmpty()) {
            cboxInstVinculada.setItems(FXCollections.observableArrayList("Nenhuma instituição disponível"));
        } else {
            cboxInstVinculada.setItems(listaInstituicoes);
        }
        cboxInstVinculada.setValue(cboxInstVinculada.getItems().get(0));
    }


    @FXML
    public void funcaoVoluntarioCbox() {

    }

    @FXML
    public void instVinculadaCbox() {

    }

    @FXML
    public void botaoQueroVoluntario() {

    }

    @FXML
    void segundaVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void domingoVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void tercaVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void quartaVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void quintaVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void sextaVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void sabadoVoluntarioBox(ActionEvent event) {

    }

    @FXML
    void botaoDoarAgora(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Realizar Doação");
    }

    @FXML
    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
    }

    public void botaoVoltar9(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    public void botaoDoacoesSolicitadas(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Confirmar Doação");
    }

    public void botaoSejaVoluntario(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/09-seja-voluntario.fxml", "ReNutrir - Seja Voluntario");
    }

    public void botaoTransportesDoacoes(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/10-transportes-doacoes.fxml", "ReNutrir - Transportes");
    }

    public void botaoTransportesPendentes(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/13-transportes-pendentes.fxml", "ReNutrir - Transportes Pendentes");
    }

    public void botaoTransportesConcluidos(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/14-transportes-concluidos.fxml", "ReNutrir - Transportes Concluidos");
    }

    public void botaoTransportesSolicitados(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/11-transportes-solicitados.fxml", "ReNutrir - Transportes Solicitados");
    }

    public void botaoConfirmarTransportes(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/12-confirmar-transporte.fxml", "ReNutrir - Confirmar Transportes");
    }

    public void botaoVoltar8(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
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

    //Tela 24

    @FXML
    public void botaoVoltar24() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    private TextField inserirFuncaoVoluntario;

    public Button solicitarVoluntariosBotao;

    @FXML
    private Button criarFuncaorVoluntarioBotao;

    public void botaoSolicitarVoluntarios(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/25-solicitar-voluntarios.fxml", "ReNutrir - Solicitar Voluntários");
    }

    @FXML
    void botaoCriarFuncaoVoluntario(ActionEvent event) {
        String funcao = inserirFuncaoVoluntario.getText();

        if (funcao != null && !funcao.trim().isEmpty()) {
            hc.repositorioVoluntario.adicionarFuncao(funcao);
            hc.showAlert(Alert.AlertType.INFORMATION, "Função criada!", "Função de voluntário '" + funcao + "' foi adicionada com sucesso!");
        } else {
            hc.showAlert(Alert.AlertType.ERROR, "Erro", "A função de voluntário não foi criada");
        }
    }

    //Tela 25

    @FXML
    void botaoVoltar58() {

        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    public void SolicitarNovosVoluntariosBotao(ActionEvent actionEvent) {
        SessaoInstituicao.getInstancia().getInstituicaoLogada();
        HelloController.getInstituicaoAtual().solicitarVoluntarios();
    }
}
