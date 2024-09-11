package br.com.renutrir.servicos;

import br.com.renutrir.model.Doacao;
import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.ProgressAlert;
import br.com.renutrir.repositorio.RepositorioDoacoes;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ControladorDoacaoConcluida {

     @FXML
     private Text enderecoInstituicaoLabel;

    //Tela 07-1

    @FXML
    private Button confPixDoar;

    @FXML
    private TextField fieldInserirValorPix;

    private Instituicao obterInstituicaoPorNome(String nome) {
        return new Instituicao();
    }

    @FXML
    void botaoVoltar29(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    private void doarConfPix(ActionEvent event) {
        String valorDoacao = fieldInserirValorPix.getText();
        if (valorDoacao.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor para a doação.");
            return;
        }

        try {
            double valor = Double.parseDouble(valorDoacao);
            if (valor <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erro", "O valor da doação deve ser maior que R$ 0.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor numérico válido.");
            return;
        }

        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado. Certifique-se de que você está logado.");
            return;
        }

        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress();

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                double progress = i / 100.0;
                Platform.runLater(() -> progressAlert.updateProgress(progress));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (progress >= 1.0) {
                    Platform.runLater(() -> {
                        progressAlert.hideProgress();
                        realizarTrocaDeTelaComValor("/br/com/renutrir/07-1-1-pix-detalhes.fxml", "ReNutrir - Doar com PIX", valorDoacao);
                    });
                    break;
                }
            }
        }).start();
    }

    public void configurarTelaConfirmarPix(String valorDoacao) {
        valorDoacaoPixLabel.setText("Valor: R$ " + valorDoacao);
        fieldPixCopiaCola.setText(gerarCodigoPixAleatorio());
        fieldPixCopiaCola.setEditable(false);
    }

    public void configurarTela(String nomeDoador, String tipoDoacao, LocalDateTime dataHora, String valorDoacao) {
        exibirInfoDoacaoLabel.setText("Doador: " + nomeDoador + "\nTipo de Doação: " + tipoDoacao +
                "\nData: " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                "\nValor: R$ " + valorDoacao);
    }

    @FXML
    private String gerarCodigoPixAleatorio() {
        String[] codigos = {
                "00020126870014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirSA6006Recife62070503***63049D25",
                "00020126360014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrir6006Olinda62070503***63049D25",
                "00020126860014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirPE6007Recife62070503***63049D25",
                "00020126450014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirSA6006Olinda62070503***63049D25",
                "00020126270014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirPE6006Recife62070503***63049D25"
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
    private Label valorDoacaoPixLabel;

    @FXML
    private TextField fieldPixCopiaCola;

    @FXML
    private TextField fieldIdTransacaoPix;

    @FXML
    private void doarFinalPix(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado.");
            return;
        }

        String doadorNome = doador.getNome();
        String tipoDoacao = "PIX";
        LocalDateTime dataHora = LocalDateTime.now();
        String valorDoacao = valorDoacaoPixLabel.getText().replace("Valor: R$ ", "");

        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress();

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                double progress = i / 100.0;
                Platform.runLater(() -> progressAlert.updateProgress(progress));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (progress >= 1.0) {
                    Platform.runLater(() -> {
                        progressAlert.hideProgress();
                        realizarTrocaDeTelaComInfoPix("/br/com/renutrir/07-10-doacao-concluida.fxml",
                                "ReNutrir - Doação Concluída", doadorNome, tipoDoacao, dataHora, valorDoacao);
                    });
                    break;
                }
            }
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

    private String valorDoacao;

    @FXML
    void doarCartaoDebito(ActionEvent event) {
        valorDoacao = fieldInserirValorCartao.getText();

        if (valorDoacao == null || valorDoacao.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor para a doação.");
        } else {
            valorDoacao = valorDoacao.replace(",", ".");
            try {
                double valor = Double.parseDouble(valorDoacao);
                if (valor <= 0) {
                    showAlert(Alert.AlertType.ERROR, "Erro", "O valor da doação deve ser maior que R$ 0.");
                } else {
                    realizarTrocaDeTelaCartao("/br/com/renutrir/07-2-2-c-debito.fxml", "ReNutrir - Doar com Débito");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor numérico válido.");
            }
        }
    }

    @FXML
    void doarCartaoCredito(ActionEvent event) {
        valorDoacao = fieldInserirValorCartao.getText();

        if (valorDoacao == null || valorDoacao.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor para a doação.");
        } else {
            valorDoacao = valorDoacao.replace(",", ".");
            try {
                double valor = Double.parseDouble(valorDoacao);
                if (valor <= 0) {
                    showAlert(Alert.AlertType.ERROR, "Erro", "O valor da doação deve ser maior que R$ 0.");
                } else {
                    realizarTrocaDeTelaCartao("/br/com/renutrir/07-2-1-c-credito.fxml", "ReNutrir - Doar com Crédito");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira um valor numérico válido.");
            }
        }
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
    private Label valorDoacaoCreExibir;

    @FXML
    private TextField fieldInserirNomeTitularCre;

    @FXML
    private TextField fieldInserirNumCredito;

    @FXML
    private TextField fieldInserirSenhaCre;

    public void receberDadosCartaoCredito(String titular, String numeroCartao, String senha, String valorDoacaoCred) {
        exibirInfoDoacaoCartao.setText("Titular: " + titular + "\nNúmero do Cartão: " + numeroCartao);
    }

    @FXML
    void doarCredito(ActionEvent event) {
        String titular = fieldInserirNomeTitularCre.getText();
        String numeroCartao = fieldInserirNumCredito.getText();
        String senha = fieldInserirSenhaCre.getText();
        String valorDoacao = valorDoacaoCreExibir.getText();

        if (titular.isEmpty() || numeroCartao.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        if (numeroCartao.length() != 16) {
            showAlert(Alert.AlertType.ERROR,"Erro", "O número do cartão deve ter 16 dígitos.");
            return;
        }

        if (senha.length() < 4 || senha.length() > 8) {
            showAlert(Alert.AlertType.ERROR,"Erro", "A senha deve ter entre 4 e 8 dígitos.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-3-transacao-cartao.fxml"));
        try {
            Parent root = loader.load();
            ControladorDoacaoConcluida controller = loader.getController();
            controller.receberDadosCartao(titular, numeroCartao, senha, valorDoacao);

            Stage stage = (Stage) creditoDoar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void inserirNomeTitularCreField(ActionEvent event) {

    }

    @FXML
    void exibirValorDoacaoCre(ActionEvent event) {

    }

    @FXML
    void inserirNumCreditoField(ActionEvent event) {

    }

    @FXML
    void inserirSenhaCreField(ActionEvent event) {

    }

    public void initializeCredito(String valorDoacao) {
        valorDoacaoCreExibir.setText("Valor: R$ " + valorDoacao);
    }

    public void initializeDebito(String valorDoacao) {
        valorDoacaoExibirDeb.setText("Valor: R$ " + valorDoacao);
    }

    //Tela 07-2-2

    @FXML
    private Label valorDoacaoExibirDeb;

    @FXML
    private TextField fieldInserirSenhaDeb;

    @FXML
    private TextField fieldInserirNumDebito;

    @FXML
    private Button debitoDoar;

    @FXML
    private TextField fieldInserirTitularDeb;


    @FXML
    void botaoVoltar32(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    public void receberDadosCartao(String titular, String numeroCartao, String senha, String valorDoacao) {
        exibirInfoDoacaoCartao.setText("Titular: " + titular + "\nNúmero do Cartão: " + numeroCartao);
        valorDoacaoCartaoLabel.setText(valorDoacao);
    }

    @FXML
    void doarDebito(ActionEvent event) {
        String titular = fieldInserirTitularDeb.getText();
        String numeroCartao = fieldInserirNumDebito.getText();
        String senha = fieldInserirSenhaDeb.getText();
        String valorDoacao = valorDoacaoExibirDeb.getText();

        if (titular.isEmpty() || numeroCartao.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        if (numeroCartao.length() != 16) {
            showAlert(Alert.AlertType.ERROR,"Erro", "O número do cartão deve ter 16 dígitos.");
            return;
        }

        if (senha.length() < 4 || senha.length() > 8) {
            showAlert(Alert.AlertType.ERROR,"Erro", "A senha deve ter entre 4 e 8 dígitos.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-3-transacao-cartao.fxml"));
        try {
            Parent root = loader.load();
            ControladorDoacaoConcluida controller = loader.getController();
            controller.receberDadosCartao(titular, numeroCartao, senha, valorDoacao);

            Stage stage = (Stage) debitoDoar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void inserirTitularDebField(ActionEvent event) {

    }

    @FXML
    void exibirValorDoacao(ActionEvent event) {

    }

    @FXML
    void inserirNumDebitoField(ActionEvent event) {

    }

    @FXML
    void inserirSenhaDebField(ActionEvent event) {

    }

    //Tela 07-2-3

    @FXML
    public Button finalCartaoDoar;

    @FXML
    public void doarFinalCartao(ActionEvent actionEvent) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado.");
            return;
        }

        String doadorNome = doador.getNome();
        String tipoDoacao = "Cartão";
        LocalDateTime dataHora = LocalDateTime.now();

        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress();

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                double progress = i / 100.0;
                Platform.runLater(() -> progressAlert.updateProgress(progress));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (progress >= 1.0) {
                    Platform.runLater(() -> {
                        progressAlert.hideProgress();

                        realizarTrocaDeTelaComInfo("/br/com/renutrir/07-10-doacao-concluida.fxml", "ReNutrir - Doação Concluída", doadorNome, tipoDoacao, dataHora);
                    });
                    break;
                }
            }
        }).start();
    }



    @FXML
    public Text exibirInstituicaoDestinadaCartao;
    public Text exibirInfoDoacaoCartao;
    public Text valorDoacaoCartaoLabel;
    public Button voltarBotao;

    //07.10

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
        String itemSelecionado = "Cartão";
        return itemSelecionado;
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
                    realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
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

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, LocalDateTime dataHora) {
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s",
                doadorNome, dataHoraFormatada, tipoDoacao
        ));
    }

    @FXML
    void botaoVoltar39(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
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

    public void realizarTrocaDeTelaCartao(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            if (caminhoFXML.contains("07-2-1-c-credito.fxml")) {
                ControladorDoacaoConcluida controlador = loader.getController();
                controlador.initializeCredito(valorDoacao);
            }

            if (caminhoFXML.contains("07-2-2-c-debito.fxml")) {
                ControladorDoacaoConcluida controlador = loader.getController();
                controlador.initializeDebito(valorDoacao);
            }

            Stage stage = (Stage) fieldInserirValorCartao.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarTrocaDeTelaComInfo(String caminhoFXML, String titulo, String doadorNome, String tipoDoacao, LocalDateTime dataHora) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            ControladorDoacaoConcluida controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, dataHora);

            Stage stage = (Stage) finalCartaoDoar.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível trocar a tela.");
        }
    }

    public void realizarTrocaDeTelaComValor(String caminhoFXML, String titulo, String valorDoacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            if (caminhoFXML.contains("07-1-1-pix-detalhes.fxml")) {
                ControladorDoacaoConcluida controlador = loader.getController();
                controlador.configurarTelaConfirmarPix(valorDoacao);
            }

            Stage stage = (Stage) voltarBotao.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao carregar a tela.");
        }
    }


    public void realizarTrocaDeTelaComInfoPix(String caminhoFXML, String titulo, String nomeDoador, String tipoDoacao, LocalDateTime dataHora, String valorDoacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            if (caminhoFXML.contains("07-10-doacao-concluida.fxml")) {
                ControladorDoacaoConcluida controlador = loader.getController();
                controlador.configurarTela(nomeDoador, tipoDoacao, dataHora, valorDoacao);
            }

            Stage stage = (Stage) voltarBotao.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}