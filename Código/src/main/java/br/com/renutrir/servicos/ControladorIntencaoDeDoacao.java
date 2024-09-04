package br.com.renutrir.servicos;

import br.com.renutrir.model.*;
import br.com.renutrir.renutrir.ProgressAlert;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.*;
import br.com.renutrir.main.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.awt.*;
import java.util.List;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;

public class ControladorIntencaoDeDoacao {

    private HelloController helloController;
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
    private ControladorTelas controladorTelas;

    public void setControladorTelas(ControladorTelas controladorTelas) {
        this.controladorTelas = controladorTelas;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    //Label
    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1

    //Tela 07 Botões

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

    //Tela 07-1

    @FXML
    private Button confPixDoar;

    @FXML
    private TextField fieldInserirValorPix;

    @FXML
    void botaoVoltar29(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void doarConfPix(ActionEvent event) {
        String valorDoacao = fieldInserirValorPix.getText();
        if (valorDoacao.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor para a doação.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-1-1-pix-detalhes.fxml"));
            Parent novaTela = loader.load();

            HelloController novoControlador = loader.getController();
            novoControlador.configurarTelaConfirmarPix(valorDoacao);

            Stage stage = (Stage) confPixDoar.getScene().getWindow();
            stage.setScene(new Scene(novaTela));
            stage.setTitle("ReNutrir - Doar com Pix");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            helloController.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela de confirmação do PIX.");
        }
    }

    @FXML
    void inserirValorPixField(ActionEvent event) {

    }

    //Tela 07-1-1

    @FXML
    void botaoVoltar53(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-1-pix.fxml","ReNutrir - Doar com Pix");
    }

    @FXML
    private Button finalPixDoar;

    @FXML
    private Button copiarPixPagar;

    @FXML
    private TextField fieldPixCopiaCola;

    @FXML
    private Text valorDoacaoPixLabel;

    @FXML
    private TextField fieldIdTransacaoPix;

    @FXML
    void doarFinalPix(ActionEvent event) {
        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress();

        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simula uma tarefa de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> progressAlert.hideProgress());
        }).start();
    }

    @FXML
    void pixCopiaColaField(ActionEvent event) {

    }

    @FXML
    void idTransacaoPixField(ActionEvent event) {

    } @FXML
    void pagarPixCopiar() {
        String texto = fieldPixCopiaCola.getText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(texto);
        clipboard.setContent(content);
    }

    //Tela 07-2

    @FXML
    void botaoVoltar30(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private Button cartaoDebitoDoar;

    @FXML
    private Button cartaoCreditoDoar;

    @FXML
    private TextField fieldInserirValorCartao;

    @FXML
    void doarCartaoDebito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-2-c-debito.fxml", "ReNutrir - Doar com Débito");
        valorDoacaoExibirDeb.setText("Valor: R$ " + valor);
    }

    @FXML
    void doarCartaoCredito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-1-c-credito.fxml", "ReNutrir - Doar com Crédito");
        valorDoacaoCreExibir.setText("Valor: R$ " + valor);
    }

    @FXML
    void inserirValorCartaoField(ActionEvent event) {

    }

    //Tela 07-2-1

    @FXML
    void botaoVoltar31(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    @FXML
    private Button creditoDoar;

    @FXML
    void doarCredito(ActionEvent event) {

    }
    @FXML
    private TextField fieldInserirNomeTitularCre;

    @FXML
    void inserirNomeTitularCreField(ActionEvent event) {

    }

    @FXML
    void exibirValorDoacaoCre(ActionEvent event) {

    }
    @FXML
    private TextField fieldInserirNumCredito;

    @FXML
    void inserirNumCreditoField(ActionEvent event) {

    }
    @FXML
    private TextField fieldInserirSenhaCre;

    @FXML
    void inserirSenhaCreField(ActionEvent event) {

    }

    //Tela 07-2-2

    @FXML
    void botaoVoltar32(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    @FXML
    private Button debitoDoar;

    @FXML
    void doarDebito(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirTitularDeb;

    @FXML
    void inserirTitularDebField(ActionEvent event) {

    }

    @FXML
    void exibirValorDoacao(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirNumDebito;

    @FXML
    void inserirNumDebitoField(ActionEvent event) {

    }

    @FXML
    private TextField fieldInserirSenhaDeb;

    @FXML
    void inserirSenhaDebField(ActionEvent event) {

    }

    //Tela 07-2-3

    @FXML
    public Button finalCartaoDoar;

    public void doarFinalCartao(ActionEvent actionEvent) {
    }

    public Text exibirInstituicaoDestinadaCartao;

    public Text exibirInfoDoacaoCartao;

    public Text valorDoacaoCartaoLabel;

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
        String nomeAlimento = fieldInserirNomeAlimento.getText();
        String qtdAlimento = fieldInserirQtdAlimento.getText();

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
        String nomeBebida = fieldInserirNomeBebida.getText();
        String qtdBebida = fieldInserirQtdBebida.getText();

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
        String nomeRoupa = fieldInserirNomeRoupa.getText();
        String qtdRoupa = fieldInserirQtdRoupa.getText();

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
        String nomeProdutoLimpeza = fieldInserirProdLimpeza.getText();
        String qtdProdutoLimpeza = fieldInserirQtdLimpeza.getText();

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
        String nomeMovel = fieldInserirNomeMovel.getText();
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
        String nomeProdutoHigiene = fieldInserirProdHigiene.getText();
        String qtdProdutoHigiene = fieldInserirQtdHigiene.getText();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeProdutoHigiene, dataHora);

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

        // Mostra a barra de progresso
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

                Platform.runLater(() -> {
                    if (helloController != null) {
                        helloController.salvarDoacoesEmArquivo(repositorioDoacoes);
                        helloController.verificarProgressoParaCertificado(doador);
                    }

                    // Após 1 segundo de progresso, mostrar que a doação foi feita com sucesso
                    progressAlert.hideProgress();
                    showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi realizada com sucesso!");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, int quantidade, String item, LocalDateTime dataHora) {
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s\nItem: %s\nQuantidade: %d",
                doadorNome, dataHoraFormatada, tipoDoacao, item, quantidade));

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