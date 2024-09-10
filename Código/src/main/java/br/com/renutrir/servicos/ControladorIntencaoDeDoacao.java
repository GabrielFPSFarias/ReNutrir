package br.com.renutrir.servicos;

import br.com.renutrir.model.*;
import br.com.renutrir.renutrir.ProgressAlert;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import javafx.animation.PauseTransition;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ArrayList;
import java.util.Optional;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.util.Duration;

public class ControladorIntencaoDeDoacao implements Initializable {

    @FXML
    public Button botaoItemDoar;
    public TextField fieldItemDoarIntencao;
    public TextField fieldInserirQtdItem;

    @FXML
    private TableView<SolicitacaoDoacao> tableViewDoacoesSolicitadas = new TableView<>();

    public ControladorIntencaoDeDoacao() {
        repositorioInstituicao = new RepositorioInstituicao();
    }

    private HelloController helloController;

    private ControladorTelas controladorTelas;

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    private Button doarAgoraBotao;

    @FXML
    private Button instituicoesDoacaoBotao;

    @FXML
    private Label instituicaoNomeLabel;

    @FXML
    private ListView<Instituicao> instituicoesListView;

    private RepositorioInstituicao repositorioInstituicao;

    @FXML
    private Label instituicaoLabel;

    @FXML
    private TableColumn<SolicitacaoDoacao, String> colInstituicao;
    @FXML
    private TableColumn<SolicitacaoDoacao, String> colItem;
    @FXML
    private TableColumn<SolicitacaoDoacao, Integer> colQuantidade;
    @FXML
    private TableColumn<SolicitacaoDoacao, Integer> colFaltam;

    public void inicializarDados() {
        configurarTabela();
        carregarEExibirSolicitacoes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (instituicoesListView != null) {
            carregarInstituicoes();
        } else {
            instituicoesListView = new ListView<>();
            if (instituicoesListView == null) {
                System.err.println("instituicoesListView é null");
            }
        }
        if (instituicaoNomeLabel == null){
            instituicaoNomeLabel = new Label();
        }

        if (RepositorioIntencaoDoacao.getInstituicaoSelecionada() == null) {
            instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação.");
        }
        inicializarDados();

        tableViewDoacoesSolicitadas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                atualizarLabelInformacoes(newVal);
            }
        });
    }

    private RepositorioSolicitacaoDoacao repositorioSolicitacaoDoacao = new RepositorioSolicitacaoDoacao();

    private void configurarTabela() {
        if (colInstituicao == null){
            colInstituicao = new TableColumn<>();
        } if (colItem == null){
            colItem = new TableColumn<>();
        } if (colQuantidade == null){
            colQuantidade = new TableColumn<>();
        } if (colFaltam == null){
            colFaltam = new TableColumn<>();
        }

        colInstituicao.setCellValueFactory(new PropertyValueFactory<>("nomeInstituicao"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colFaltam.setCellValueFactory(new PropertyValueFactory<>("faltam"));
    }

    private void carregarEExibirSolicitacoes() {
        List<SolicitacaoDoacao> solicitacoes = repositorioSolicitacaoDoacao.carregarSolicitacoes();
        ObservableList<SolicitacaoDoacao> observableSolicitacoes = FXCollections.observableArrayList(solicitacoes);
        tableViewDoacoesSolicitadas.setItems(observableSolicitacoes);
    }

    private void carregarInstituicoes() {
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();
        ObservableList<Instituicao> instituicoesObservableList = FXCollections.observableArrayList(instituicoes);

        instituicoesListView.setItems(instituicoesObservableList);
        instituicoesListView.setCellFactory(lv -> new ListCell<Instituicao>() {
            @Override
            protected void updateItem(Instituicao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNome());
            }
        });

        instituicoesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RepositorioIntencaoDoacao.setInstituicaoSelecionada(newVal);
                atualizarLabelInstituicao();
            }
        });
    }

    private void atualizarLabelInstituicao() {
        Instituicao instituicao = RepositorioIntencaoDoacao.getInstituicaoSelecionada();
        if (instituicao != null) {
            this.instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação. Doação para a " + instituicao.getNome());
        } else {
            this.instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação. Escolha abaixo a instituição beneficiária.");
        }
    }

    @FXML
    private void atualizarLabelInformacoes(SolicitacaoDoacao solicitacao) {
        Optional<Instituicao> instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorNome(solicitacao.getNomeInstituicao());

        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            labelEnderecoEmailInst.setText(instituicao.getEndereco() + "\nEmail: " + instituicao.getEmail());
        } else {
            labelEnderecoEmailInst.setText("Informações não disponíveis.");
        }
    }


    @FXML
    public void botaoDoarAgora(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.WARNING, "Seleção Necessária", "Por favor, selecione uma instituição para prosseguir.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-confirmar-doacao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) doarAgoraBotao.getScene().getWindow();
            stage.setTitle("ReNutrir - Confirmar Doação");
            stage.setScene(new Scene(root));

            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.instituicaoLabel.setText("Você está doando para a " + instituicaoSelecionada.getNome());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    public void botaoVoltar5() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }

    @FXML
    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/05-1-doar-instituicoes.fxml"));
            Parent root = loader.load();

            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.configurarTabela();
            controlador.carregarEExibirSolicitacoes();
            if(controlador.instituicaoNomeLabel == null) {
                controlador.instituicaoNomeLabel = new Label();
            }
            controlador.instituicaoNomeLabel.setText("Instituição - TP Solicitação");

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ReNutrir - Doações Solicitadas");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela das solicitações das instituições.");
        }
    }


    //Tela 05 - Intenção de doação


    //Tela 05.1 - Lista Instituições que solicitaram doações

    @FXML
    private Button doarInstSelecionadaBotao;

    @FXML
    private Label labelEnderecoEmailInst;

    public void botaoVoltar75(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private void botaoDoarInstSelecionada(ActionEvent actionEvent) {
        SolicitacaoDoacao solicitacaoSelecionada = tableViewDoacoesSolicitadas.getSelectionModel().getSelectedItem();
        if (solicitacaoSelecionada == null) {
            showAlert(Alert.AlertType.WARNING, "Seleção Necessária", "Por favor, selecione uma solicitação para prosseguir.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/05-2-doacao-solicitacao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Solicitação");

            if (fieldInserirQtdItem == null){
                fieldInserirQtdItem = new TextField();
            }

            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoes(solicitacaoSelecionada, solicitacaoSelecionada.getItem(), fieldInserirQtdItem.getText());

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação solicitação.");
        }
    }

    public void setInformacoes(SolicitacaoDoacao instituicaoSelecionada, String itemDoacao, String quantidade) {
        nomeInstSolicitadoraLabel.setText(instituicaoSelecionada.getNomeInstituicao());
        nomeItemSolicitadoLabel.setText(instituicaoSelecionada.getItem());
        fieldInserirQtdItem.setText(quantidade);
    }


    //Tela 05-2

    @FXML
    private Label nomeItemSolicitadoLabel;

    @FXML
    private Label nomeInstSolicitadoraLabel;

    @FXML
    void botaoVoltar76(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/05-1-doar-instituicoes.fxml", "ReNutrir - Doações Solicitadas");
    }

    @FXML
    void doarItemSolicitadoBotao(ActionEvent event) {

    }

    @FXML
    void inserirQtdItemField(ActionEvent event) {

    }


    //Label
    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1

    //Tela 07 Botões

    @FXML
    public TextField fieldInserirNomeItem;

    @FXML
    public Button voltarBotao;

    @FXML
    private Button alimentosDoar;

    @FXML
    private Button moveisDoar;

    @FXML
    private Button bebidasDoar;

    @FXML
    private Button pixDoar;

    @FXML
    private Button roupasDoar;

    @FXML
    private Button produtoLimpezaDoar;

    @FXML
    private Button itemHigienerDoar;

    @FXML
    private Button cartaoDoar;

    //Função Botões

    @FXML
    public void botaoVoltar7() {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void doarPix(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-1-pix.fxml", "ReNutrir - Doar com PIX");
    }

    @FXML
    void doarCartao(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    @FXML
    void doarAlimentos(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-3-alimentos.fxml", "ReNutrir - Doar Alimentos");
    }

    @FXML
    void doarRoupas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-5-roupas.fxml", "ReNutrir - Doar Roupas");
    }

    @FXML
    void doarMoveis(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-7-moveis.fxml", "ReNutrir - Doar Móveis");
    }

    @FXML
    void doarBebidas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-4-bebidas.fxml", "ReNutrir - Doar Bebidas");
    }

    @FXML
    void doarProdutoLimpeza(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-6-produtos-limpeza.fxml", "ReNutrir - Doar Produtos de Limpeza");
    }

    @FXML
    public void doarItemHgiene(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/07-8-higiene-pessoal.fxml", "ReNutrir - Doar Itens de Higiene Pessoal");
    }

    //Tela 07-3

    @FXML
    private TextField fieldInserirNomeAlimento;

    @FXML
    private TextField fieldInserirQtdAlimento;

    @FXML
    void botaoVoltar33(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private Button botaoAlimentosDoar;

    @FXML
    void doarAlimentosBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Alimentos";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    private Instituicao obterInstituicaoPorNome(String nome) {
        return new Instituicao();
    }

    @FXML
    void inserirNomeAlimentoField(ActionEvent event) {

    }

    @FXML
    void inserirQtdAlimentoField(ActionEvent event) {

    }

    //Tele 07-4

    @FXML
    void botaoVoltar34(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private TextField fieldInserirQtdBebida;

    @FXML
    private Button botaoBebidaDoar;

    @FXML
    private TextField fieldInserirNomeBebida;

    @FXML
    void doarBebidaBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Bebidas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirNomeBebidaField(ActionEvent event) {

    }

    @FXML
    void inserirQtdBebidaField(ActionEvent event) {

    }

    //Tela 07-5

    @FXML
    void botaoVoltar35(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private TextField fieldInserirNomeRoupa;

    @FXML
    void inserirNomeRoupaField(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirQtdRoupa;

    @FXML
    void inserirQtdRoupaField(ActionEvent event) {

    }

    @FXML
    private Button botaoRoupaDoar;

    @FXML
    void doarRoupaBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Roupas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    //Tela 07-6

    @FXML
    void botaoVoltar36(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private TextField fieldInserirProdLimpeza;

    @FXML
    void inserirProdLimpezaField(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirQtdLimpeza;

    @FXML
    void inserirQtdLimpezaField(ActionEvent event) {

    }

    @FXML
    private Button botaoProdLimpezaDoar;

    @FXML
    void doarProdLimpezaBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Produtos de limpeza";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    //Tela 07-7

    @FXML
    void botaoVoltar37(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private TextField fieldInserirNomeMovel;

    @FXML
    void inserirNomeMovelField(ActionEvent event) {

    }

    @FXML
    private ComboBox<String> selecionarTipoMovelBox;

    @FXML
    void boxSelecionarTipoMovel(ActionEvent event) {

    }

    @FXML
    private Button movelDoarBotao;

    @FXML
    void botaoDoarMovel(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = String.valueOf(1);
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Móveis";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml"); //tem que ser constante
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    //Tela 07-8

    @FXML
    void botaoVoltar38(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private TextField fieldInserirProdHigiene;

    public void alterarProdutoHigiente(String alterar) {
        this.fieldInserirProdHigiene.setText(alterar);
    }

    @FXML
    void inserirProdHigieneField(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirQtdHigiene;

    @FXML
    void inserirQtdHigieneField(ActionEvent event) {

    }

    @FXML
    private Button botaoProdHigieneDoar;

    @FXML
    void doarProdHigieneBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada.");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Produtos de higiene";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    //Tela 07-9

    @FXML
    private Button salvarIntencaoDoacaoBotao;

    @FXML
    private Label exibirEnderecoInstituicaoLabel;

    @FXML
    private Label exibirIntencaoDoacaoLabel;

    @FXML
    private Label labelEnderecoInstituicaoSel;

    @FXML
    void botaoVoltar39(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }


    //Tela 07-10

    @FXML
    public Label exibirInfoDoacaoLabel;

    @FXML
    private Button salvarComprovanteBotao;

    @FXML
    void botaoSalvarComprovante(ActionEvent event) {
        String infoDoacao = exibirInfoDoacaoLabel.getText();

        if (infoDoacao.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma informação para salvar.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Comprovante de Doação");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(((Stage) salvarComprovanteBotao.getScene().getWindow()));
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(infoDoacao);
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Comprovante salvo com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível salvar o comprovante.");
            }
        }
    }

    private Instituicao instituicaoSelecionada;

    private void atualizarLabelEndereco() {
        if (instituicaoSelecionada != null) {
            labelEnderecoInstituicaoSel.setText(instituicaoSelecionada.getEndereco()+"");
        } else {
            labelEnderecoInstituicaoSel.setText("Endereço: Não disponível");
        }
    }

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, int quantidade, String nomeItem, LocalDateTime dataHora, Instituicao instituicao) {
        this.instituicaoSelecionada = instituicao;
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s\nItem: %s\nQuantidade: %d",
                doadorNome, dataHoraFormatada, tipoDoacao, nomeItem, quantidade));
        atualizarLabelEndereco();

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
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

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

}