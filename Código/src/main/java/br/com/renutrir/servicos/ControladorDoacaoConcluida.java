package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.ProgressAlert;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class ControladorDoacaoConcluida {


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
    public void configurarTelaConfirmarPix(String valorDoacao) {
        valorDoacaoPixLabel.setText("Valor: R$ " + valorDoacao);
        fieldPixCopiaCola.setText(gerarCodigoPixAleatorio());
    }

    @FXML
    private void doarConfPix(ActionEvent event) {
        String valorDoacao = fieldInserirValorPix.getText();
        if (valorDoacao.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor para a doação.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-1-1-pix-detalhes.fxml"));
            Parent novaTela = loader.load();

            ControladorDoacaoConcluida novoControlador = loader.getController();
            novoControlador.configurarTelaConfirmarPix(valorDoacao);

            Stage stage = (Stage) confPixDoar.getScene().getWindow();
            stage.setScene(new Scene(novaTela));
            stage.setTitle("ReNutrir - Doar com Pix");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela de confirmação do PIX.");
        }
    }

    @FXML
    private String gerarCodigoPixAleatorio() {
        String[] codigos = {
                "00020126870014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirSA6006Recife62070503***63049D25",
                "00020126360014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrir6006Olinda62070503***63049D25"
        };
        int indice = new Random().nextInt(codigos.length);
        return codigos[indice];
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
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
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
        //valorDoacaoExibirDeb.setText("Valor: R$ " + valor);
    }

    @FXML
    void doarCartaoCredito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-1-c-credito.fxml", "ReNutrir - Doar com Crédito");
        //valorDoacaoCreExibir.setText("Valor: R$ " + valor);
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

    @FXML
    public Text exibirInstituicaoDestinadaCartao;
    public Text exibirInfoDoacaoCartao;
    public Text valorDoacaoCartaoLabel;
    public Button voltarBotao;

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
