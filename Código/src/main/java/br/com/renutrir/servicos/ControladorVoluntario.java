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

import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
public class ControladorVoluntario {

    private HelloController hc;

    private ControladorTelas controladorTelas;

    public void setControladorTelas(ControladorTelas controladorTelas) {
        this.controladorTelas = controladorTelas;
    }

    public void setHelloController(HelloController hc) {
        this.hc = hc;
    }



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

        /*
        cboxFuncaoVoluntario.setItems(listaFuncaoVoluntario);
        cboxFuncaoVoluntario.setValue(listaFuncaoVoluntario.get(0));
         */
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

}
