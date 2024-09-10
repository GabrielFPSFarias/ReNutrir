package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Evento;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.repositorio.RepositorioEventos;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class ControladorEventos {

    @FXML
    private TextField nomeEventoField;

    @FXML
    private TextField dataEventoField;

    @FXML
    private TextField horarioEventoField;

    @FXML
    private TextField endEventoField;

    @FXML
    private TextField tipoEventoField;

    @FXML
    private TextArea descricaoEventoField;

    @FXML
    private Button voltarBotao;

    private RepositorioEventos repositorio;

    public ControladorEventos() {
        repositorio = new RepositorioEventos();
    }

    @FXML
    void botaoEditarEvento(ActionEvent event) {
        if (nomeEventoField == null) {
            nomeEventoField = new TextField();
        }

        String nomeEventoSelecionado = nomeEventoField.getText();
        Optional<Evento> eventoParaEditarOpt = repositorio.buscarEventoPorNome(nomeEventoSelecionado);

        if (eventoParaEditarOpt.isPresent()) {
            Evento eventoParaEditar = eventoParaEditarOpt.get();
            atualizarEvento(eventoParaEditar);
            repositorio.atualizarEvento(eventoParaEditar);
            showAlert(Alert.AlertType.INFORMATION, "Evento Editado", "O evento '" + nomeEventoSelecionado + "' foi editado com sucesso!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Evento não encontrado.");
        }
    }

    public void removerEventosPassados() {
        List<Evento> eventosAtuais = repositorio.listarEventos();
        LocalDateTime agora = LocalDateTime.now();

        eventosAtuais.removeIf(evento -> evento.getData().atTime(evento.getHorario()).isBefore(agora));
        repositorio.salvarEventos();
    }

    private Evento criarEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataEventoField.getText() + " " + horarioEventoField.getText(), formatter);
        return new Evento(
                nomeEventoField.getText(),
                dataHora.toLocalDate(),
                endEventoField.getText(),
                dataHora.toLocalTime(),
                tipoEventoField.getText(),
                descricaoEventoField.getText()
        );
    }

    private void atualizarEvento(Evento evento) {
        evento.setNome(nomeEventoField.getText());
        evento.setData(LocalDate.parse(dataEventoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        evento.setHorario(LocalTime.parse(horarioEventoField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        evento.setLocal(endEventoField.getText());
        evento.setTipo(tipoEventoField.getText());
        evento.setDescricao(descricaoEventoField.getText());
    }

    private void limparCamposEvento() {
        nomeEventoField.clear();
        dataEventoField.clear();
        horarioEventoField.clear();
        endEventoField.clear();
        tipoEventoField.clear();
        descricaoEventoField.clear();
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        System.out.println("Clicou: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, fxmlArquivo, titulo);

        if (fxmlArquivo.equals("/br/com/renutrir/03-login.fxml")) {
            SessaoDoador.getInstancia().limparSessao();
            SessaoInstituicao.getInstancia().limparSessao();
        } else {
            logarSessaoUsuario();
        }
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

    private void logarSessaoUsuario() {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        if (doadorLogado != null) {
            System.out.println("Doador logado: " + doadorLogado.getNome());
        }

        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();
        if (instituicaoLogada != null) {
            System.out.println("Instituição logada: " + instituicaoLogada.getNome());
        }
    }

    @FXML
    public void salvarCriarEventoBotao(ActionEvent actionEvent) {
        try {
            Evento evento = criarEvento();
            repositorio.adicionarEvento(evento);
            limparCamposEvento();
            showAlert(Alert.AlertType.INFORMATION, "Evento Criado", "O evento '" + evento.getNome() + "' foi criado com sucesso!");
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Formato", "Por favor, insira a data e hora no formato 'dd/MM/yyyy HH:mm'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void botaoListaEventosCriadosBotao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/20-2-lista-eventos.fxml", "ReNutrir - Lista de Eventos");

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> atualizarListaEventos());
        pause.play();
    }


    private void atualizarListaEventos() {
        RepositorioEventos repositorioEventos = new RepositorioEventos();
        List<Evento> eventos = repositorioEventos.listarEventos();

        ObservableList<Evento> eventosList = FXCollections.observableArrayList();
        LocalDateTime agora = LocalDateTime.now();

        for (Evento evento : eventos) {
            if (evento.getData().atTime(evento.getHorario()).isAfter(agora)) {
                eventosList.add(evento);
            }
        }

        tableViewEventos.setItems(eventosList);

    }


    @FXML
    private Button criarNovoEventoBotao;

    @FXML
    public void botaoCriarNovoEvento() {
        realizarTrocaDeTela("/br/com/renutrir/20-1-detalhes-eventos.fxml", "ReNutrir - Criar Novo Evento");
    }

    //Tela 20.1 - Detalhes Eventos

    @FXML
    private Button botaoSalvarCriarEvento;

    @FXML
    void botaoVoltar20(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }


    @FXML
    void fieldNomeEvento(ActionEvent event) {

    }

    @FXML
    void fieldEndEvento(ActionEvent event) {

    }

    @FXML
    void fieldHorarioEvento(ActionEvent event) {

    }

    @FXML
    void fieldDataEvento(ActionEvent event) {

    }

    @FXML
    void fieldTipoEvento(ActionEvent event) {

    }

    @FXML
    void fieldDescricaoEvento(ActionEvent event) {

    }

    //Tela 20.2 Lista de eventos criados

    @FXML
    private Label labelEventosLista;

    @FXML
    private Label listaEventosArea;

    @FXML
    void botaoVoltar43(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    //Tela 20.3 Editar eventos criados pela instituição

    @FXML
    void botaoVoltar44(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }


    //Tela para o voluntário

    @FXML
    private TableView<Evento> tableViewEventos;

    @FXML
    private TableColumn<Evento, String> tableEventosInstituicao;

    @FXML
    private TableColumn<Evento, String> tableEventosInformacoes;

    public void initialize() {
        tableEventosInstituicao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableEventosInformacoes.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome() + " - " + cellData.getValue().getData()
                        + " " + cellData.getValue().getHorario() + " - " + cellData.getValue().getLocal()
                        + " - " + cellData.getValue().getDescricao()));
    }

    @FXML
    void botaoVoltar16(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }
}
