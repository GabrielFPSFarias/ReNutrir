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

public class ControladorIntencaoDeDoacao implements Initializable {

    public ControladorIntencaoDeDoacao() {
    }

    private HelloController helloController;

    private ControladorTelas controladorTelas;

    public void setControladorTelas(ControladorTelas controladorTelas) {
        this.controladorTelas = controladorTelas;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (escolherInstituicaoDoarCbox == null) {
            System.out.println("escolherInstituicaoDoarCbox está null");
            return;
        }
        carregarInstituicoes();
    }

    //Tela 05 - Intenção de doação

    @FXML
    public Button instituicoesDoacaoBotao, doarAgoraBotao;
    public ComboBox escolherInstituicaoDoarCbox;

    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
    }

    public void botaoDoarAgora(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Realizar Doação");
    }

    public void cboxEscolherInstituicaoDoar(ActionEvent actionEvent) {
    }

    public void botaoVoltar5() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }


    private void carregarInstituicoes() {
        ObservableList<String> listaInstituicoes = FXCollections.observableArrayList();

        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();

        for (Instituicao instituicao : instituicoes) {
            listaInstituicoes.add(instituicao.getNome());
        }

        if (listaInstituicoes.isEmpty()) {
            escolherInstituicaoDoarCbox.setItems(FXCollections.observableArrayList("Nenhuma instituição disponível"));
        } else {
            escolherInstituicaoDoarCbox.setItems(listaInstituicoes);
        }
        escolherInstituicaoDoarCbox.setValue(escolherInstituicaoDoarCbox.getItems().get(0));
    }

    //Label
    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1

    //Tela 07 Botões

    @FXML
    public TextField fieldInserirNomeItem;
    public TextField fieldInserirQtdItem;

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
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Doações Solicitadas");
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
        String nomeAlimento;
        nomeAlimento = fieldInserirNomeItem.getText();
        String qtdAlimento;
        qtdAlimento = fieldInserirQtdItem.getText();

        if (nomeAlimento.isEmpty() || qtdAlimento.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdAlimento);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Alimentos";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeAlimento, dataHora);

            Stage stage = (Stage) botaoAlimentosDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
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
    void doarBebidaBotao(ActionEvent event) {
        String nomeBebida = fieldInserirNomeItem.getText();
        String qtdBebida = fieldInserirQtdItem.getText();

        if (nomeBebida.isEmpty() || qtdBebida.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdBebida);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Bebidas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeBebida, dataHora);

            Stage stage = (Stage) botaoBebidaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
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
    void doarRoupaBotao(ActionEvent event) {
        String nomeRoupa = fieldInserirNomeItem.getText();
        String qtdRoupa = fieldInserirQtdItem.getText();

        if (nomeRoupa.isEmpty() || qtdRoupa.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdRoupa);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome(): "Desconhecido";
        String tipoDoacao = "Roupas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeRoupa, dataHora);

            Stage stage = (Stage) botaoRoupaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
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
    void doarProdLimpezaBotao(ActionEvent event) {
        String nomeProdutoLimpeza = fieldInserirNomeItem.getText();
        String qtdProdutoLimpeza = fieldInserirQtdItem.getText();

        if (nomeProdutoLimpeza.isEmpty() || qtdProdutoLimpeza.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdProdutoLimpeza);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Quantidade inválida. Por favor, insira um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Produtos de Limpeza";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeProdutoLimpeza, dataHora);

            Stage stage = (Stage) botaoProdLimpezaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
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
    void botaoDoarMovel(ActionEvent event) {
        selecionarTipoMovelBox.getItems().addAll("Novo", "Usado");
        String nomeMovel = fieldInserirNomeItem.getText();
        String tipoMovel = selecionarTipoMovelBox.getValue() != null ? selecionarTipoMovelBox.getValue() : "";

        if (nomeMovel.isEmpty() || tipoMovel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Móveis";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, 1, nomeMovel, dataHora);

            Stage stage = (Stage) movelDoarBotao.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
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
    void doarProdHigieneBotao(ActionEvent event) {
        String nomeProdutoHigiene = fieldInserirNomeItem.getText();
        String qtdProdutoHigiene = fieldInserirQtdItem.getText();

        if (nomeProdutoHigiene.isEmpty() || qtdProdutoHigiene.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdProdutoHigiene);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Quantidade inválida. Por favor, insira um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Itens de Higiene Pessoal";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml"));
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setIntencaoDoacao(doadorNome, tipoDoacao, quantidade, dataHora);

            Stage stage = (Stage) botaoProdHigieneDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
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
    void botaoVoltar39(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }

    @FXML
    void botaoSalvarIntencaoDoacao(ActionEvent event) {
        String nomeInstituicao = exibirEnderecoInstituicaoLabel.getText();
        String nomeItem = fieldInserirNomeItem.getText();
        String quantidadeStr = fieldInserirQtdItem.getText();
        LocalDateTime dataHora = LocalDateTime.now();
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        int quantidade;

        if (nomeInstituicao.isEmpty() || nomeItem.isEmpty() || quantidadeStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos corretamente.");
            return;
        }

        try {
            quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erro de validação", "A quantidade deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Quantidade inválida. Por favor, insira um número válido.");
            return;
        }

        IntencaoDoacao novaIntencao = new IntencaoDoacao(nomeInstituicao, nomeItem, quantidade, dataHora);
        RepositorioIntencaoDoacao repositorio = new RepositorioIntencaoDoacao();
        repositorio.adicionarIntencao(novaIntencao);

        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "A intenção de doação foi salva com sucesso! Realize a sua doação dentro dos próximos 7 dias.");
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

    @FXML
    private Button botaoRegistrarDoacao;

    private String obterItemSelecionado() {
        if (fieldInserirNomeAlimento != null && !fieldInserirNomeAlimento.getText().isEmpty()) {
            return fieldInserirNomeAlimento.getText();
        } else if (fieldInserirNomeBebida != null && !fieldInserirNomeBebida.getText().isEmpty()) {
            return fieldInserirNomeBebida.getText();
        } else if (fieldInserirNomeRoupa != null && !fieldInserirNomeRoupa.getText().isEmpty()) {
            return fieldInserirNomeRoupa.getText();
        } else if (fieldInserirProdLimpeza != null && !fieldInserirProdLimpeza.getText().isEmpty()) {
            return fieldInserirProdLimpeza.getText();
        } else if (fieldInserirNomeMovel != null && !fieldInserirNomeMovel.getText().isEmpty()) {
            return fieldInserirNomeMovel.getText();
        } else if (fieldInserirProdHigiene != null && !fieldInserirProdHigiene.getText().isEmpty()) {
            return fieldInserirProdHigiene.getText();
        } else {
            return null;
        }
    }

    @FXML
    private void registrarDoacao(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();

        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado. Certifique-se de que você está logado.");
            return;
        }

        String itemSelecionado = obterItemSelecionado();

        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress();

        new Thread(() -> {
            try {
                Thread.sleep(500);

                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                String status = "Concluída";

                Doacao doacao = new Doacao(doador.getNomeUsuario(), itemSelecionado, 1, dataHora, status);

                RepositorioDoacoes repositorioDoacoes = new RepositorioDoacoes();
                repositorioDoacoes.adicionarDoacao(doacao);
                salvarDoacoesEmArquivo(repositorioDoacoes);

                Platform.runLater(() -> {
                    verificarProgressoParaCertificado(doador);

                    progressAlert.hideProgress();
                    showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi realizada com sucesso!");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void salvarDoacoesEmArquivo(RepositorioDoacoes repositorioDoacoes) {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado == null) {
            Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Erro", "Nenhum doador está logado."));
            return;
        }

        String nomeUsuario = doadorLogado.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_doacoes.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(repositorioDoacoes.listarDoacoes());
        } catch (IOException e) {
            e.printStackTrace();
            Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível salvar as doações."));
        }
    }

    public void verificarProgressoParaCertificado(Doador doador) {
        ControladorCertificado controladorCertificado = new ControladorCertificado();
        controladorCertificado.verificarProgressoParaCertificado(doador);
    }

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, int quantidade, String item, LocalDateTime dataHora) {
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s\nItem: %s\nQuantidade: %d",
                doadorNome, dataHoraFormatada, tipoDoacao, item, quantidade));

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
    }

    public void setIntencaoDoacao(String nomeDoador, String tipoDoacao, int quantidade, LocalDateTime dataHora) {
        if (exibirIntencaoDoacaoLabel != null) {
            String texto = String.format("Doador: %s\nData e hora: %s\nTipo da doação: %s\nQuantidade: %d",
                    nomeDoador, dataHora, tipoDoacao, quantidade);
            exibirIntencaoDoacaoLabel.setText(texto);
        } else {
            System.out.println("exibirIntencaoDoacaoLabel é null");
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

/*
    private HelloController hc;
    private RepositorioIntencaoDoacao repositorioIntencaoDoacao;

    @FXML
    private Button doarAgoraBotao;

    @FXML
    void botaoDoarAgora(ActionEvent event) {
        hc.realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Realizar Doação");
    }

    @FXML
    private ComboBox<String> escolherInstituicaoDoarCbox;

    private RepositorioInstituicao repositorioInstituicao;

    @FXML
    public void initialize() {
        repositorioInstituicao = new RepositorioInstituicao();
        carregarInstituicoesNaComboBox();
    }

    private void carregarInstituicoesNaComboBox() {
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();
        if (instituicoes.isEmpty()) {
            System.out.println("Nenhuma instituição encontrada no arquivo.");
        }
        for (Instituicao instituicao : instituicoes) {
            System.out.println("Adicionando instituição: " + instituicao.getNome());
            escolherInstituicaoDoarCbox.getItems().add(instituicao.getNome());
        }
    }

    @FXML
    void cboxEscolherInstituicaoDoar(ActionEvent event) {

    }
    @FXML
    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        hc.realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
    }

    public ControladorIntencaoDeDoacao(HelloController hc) {
        this.repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        this.hc = hc;
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return repositorioIntencaoDoacao.listarIntencoes();
    }

    public void criarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.adicionarIntencao(intencao);
            System.out.println("Intenção de doação criada com sucesso!");
        } else {
            System.out.println("Falha ao criar intenção de doação: dados inválidos.");
        }
    }


    public void atualizarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.atualizarIntencao(intencao);
            System.out.println("Intenção de doação atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar intenção de doação: dados inválidos.");
        }
    }

    public void removerIntencaoDeDoacao(IntencaoDoacao intencao) {
        repositorioIntencaoDoacao.removerIntencao(intencao);
        System.out.println("Intenção de doação removida com sucesso!");
    }

    private boolean validarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (intencao.getItem() == null || intencao.getItem().isEmpty()) {
            System.out.println("Item inválido.");
            return false;
        }
        if (intencao.getQuantidade() <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }
        // espaço para mais validações..

        return true;
    }
*/