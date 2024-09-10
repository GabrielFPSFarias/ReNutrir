package br.com.renutrir.renutrir;

import br.com.renutrir.model.*;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.servicos.*;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Cell;
import javafx.scene.control.SelectionMode;


public class HelloController {

  /*  lv.getItems().addAll(FXCollections.observableArrayList(contas.getListaDeNomesInstituicoes()));

     listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            // Abrir a nova janela com os detalhes da instituição
            DetalhesInstituicao.showDetalhes(newValue);
        }
    };*/

    @FXML
    public void botaoListaDoacaoEfetuada(){

        realizarTrocaDeTela("/br/com/renutrir/05.5-lista-instituicoes1.fxml", "ReNutrir - Lista das Instituições");
    }

    public ControladorDoacoesPendentes controladorDoacoesPendentes = new ControladorDoacoesPendentes();
    public Button instituicoesDoacaoBotao;
    public ComboBox escolherInstituicaoDoarCbox;
    private Doador doadorLogado;

    @FXML
    public TextField fieldEmailIns, fieldCnpjIns, fieldNomeIns, fieldInsCep, fieldUserNomeIns, fieldTelefoneIns;

    @FXML
    public TextField fieldEnderecoIns, fieldRefIns, fieldBairroIns, fieldCidadeIns, fieldNumeroIns, fieldCompIns;

    @FXML
    public PasswordField fieldSenhaIns, fieldConfSenhaIns;

    @FXML
    public TextField fieldUfIns;
    public CheckBox checarInstituicao;
    public Button doacoesSolicitadasBotao;
    public Button sejaVoluntarioBotao;
    public Button transportesDoacoesBotao;
    public Button transportesPendentesBotao;
    public Button transportesConcluidosBotao;
    public Button transportesSolicitadosBotao;
    public Button confirmarTransportesBotao;
    public Button criarEventoBotao;
    public Button historicoDoacoesBotao;

    @FXML
    public Button doacaoEfetuada;
    public Button solicitarDoacoesBotao;
    public Button atuaisVoluntariosBotao;
    public Button perfilInstituicaoBotao;
    public Button doacoesPendentesInstituicaoBotao;
    public Button solicitarVoluntariosBotao;

    @FXML
    public TextField loginEmailField;

    @FXML
    public PasswordField loginSenhaField;

    @FXML
    public Button loginEntrarBotao;

    @FXML
    public Button loginBotao;

    @FXML
    public Button cadastroBotao;

    @FXML
    public Button voltarBotao;

    @FXML
    public Button confCad;

    @FXML
    public Button confCadIns;

    @FXML
    public Button doadorBotao;

    @FXML
    public Button instBotao;

    @FXML
    public Button intencaoDoacaoBotao;
    public Button voluntarioBotao;
    public Button certificadosBotao;
    public Button eventosBotao;
    public Button perfilBotao;
    public Button doacoesPendentesDoadorBotao;
    private String nomeEventoParaEditar;

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
    public void botaoVoltar() {
        realizarTrocaDeTela("/br/com/renutrir/01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoVoltar1() {
        realizarTrocaDeTela("/br/com/renutrir/02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar2() {
        realizarTrocaDeTela("/br/com/renutrir/02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar3() {
        realizarTrocaDeTela("/br/com/renutrir/01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoVoltar4() {
        realizarTrocaDeTela("/br/com/renutrir/03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoVoltar5() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar6() {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    public void botaoVoltar7() {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Doações Solicitadas");
    }

    @FXML
    public void botaoVoltar8() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar9() {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar10() {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar11() {
        realizarTrocaDeTela("/br/com/renutrir/10-transportes-doacoes.fxml", "ReNutrir - Transportes");
    }

    @FXML
    public void botaoVoltar12() {
        realizarTrocaDeTela("/br/com/renutrir/11-transportes-solicitados.fxml", "ReNutrir - Transportes Solicitados");
    }

    @FXML
    public void botaoVoltar13() {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar14() {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar15() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar16() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar17() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar18() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar19() {
        realizarTrocaDeTela("/br/com/renutrir/03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoVoltar20() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar21() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar23() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar24() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar25() {
        realizarTrocaDeTela("/br/com/renutrir/24-voluntarios.fxml", "ReNutrir - Voluntários");
    }

    @FXML
    public void botaoVoltar26() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoLogin() {
        System.out.println("Login");
        Stage stage = (Stage) loginBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoCadastro() {
        System.out.println("Cadastro");
        Stage stage = (Stage) cadastroBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoSouInstituicao() {
        System.out.println("Cadastro instituição");
        Stage stage = (Stage) instBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/02-cadastro-instituicao.fxml", "ReNutrir - Cadastro Instituição");
    }

    @FXML
    public void botaoSouDoador() {
        System.out.println("Cadastro doador");
        Stage stage = (Stage) doadorBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/02-cadastro-doador.fxml", "ReNutrir - Cadastro Doador");
    }

    public void confirmarCadastro() {
        ControladorCadastro cadastrarDoador = new ControladorCadastro();
        cadastrarDoador.confirmarCadastro();
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void confirmarCadastroIns() {
        ControladorCadastro cadastrarInstituicao = new ControladorCadastro();
        cadastrarInstituicao.confirmarCadastroIns();
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

    //Métodos do cadastro doador

    public void emailField() {

    }

    public void nomeField() {

    }

    public void cepField() {

    }

    public void cpfField() {

    }

    public void nomeUserField() {

    }

    public void senhaField() {

    }

    public void confSenhaField() {

    }

    public void telefoneField() {

    }

    public void enderecoField() {

    }

    public void refField() {

    }

    public void bairroField() {

    }

    public void numField() {

    }

    public void cidadeField() {

    }

    public void ufField() {

    }

    public void compField() {

    }

    //Tela de login (TEMPORÁRIO)

    @FXML
    private SessaoDoador sessaoDoador;
    @FXML
    private SessaoInstituicao sessaoInstituicao;

    @FXML
    public void fieldLoginSenha(ActionEvent actionEvent) {
    }
    @FXML
    public void fieldLoginEmail(ActionEvent actionEvent) {
    }

    @FXML
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        if (checarInstituicao.isSelected()) {
            processarLogin(emailOuUsuario, senha, true);
        } else {
            processarLogin(emailOuUsuario, senha, false);
        }
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

    public void processarLogin(String emailOuUsuario, String senha, boolean isInstituicao) {
        if (isInstituicao) {
            Instituicao instituicao = buscarInstituicaoNoRepositorio(emailOuUsuario, senha);
            if (instituicao != null) {
                SessaoInstituicao.getInstancia().setInstituicaoLogada(instituicao); // Salva a instituição que fez login
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Instituição!");
                realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para instituição.");
            }
        } else {
            Doador doador = buscarDoadorNoRepositorio(emailOuUsuario, senha);
            if (doador != null) {
                SessaoDoador.getInstancia().setDoadorLogado(doador); // Salva o doador que fez login
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Doador!");
                realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para doador.");
            }
        }
    }

    //Métodos de cadastro instituição

    public void ufInsField(ActionEvent actionEvent) {
    }

    public void compInsField(ActionEvent actionEvent) {
    }

    public void cidadeInsField(ActionEvent actionEvent) {
    }

    public void numInsField(ActionEvent actionEvent) {
    }

    public void bairroInsField(ActionEvent actionEvent) {
    }

    public void refInsField(ActionEvent actionEvent) {
    }

    public void enderecoInsField(ActionEvent actionEvent) {
    }

    public void telefoneInsField(ActionEvent actionEvent) {
    }

    public void confSenhaInsField(ActionEvent actionEvent) {
    }

    public void senhaInsField(ActionEvent actionEvent) {
    }

    public void nomeUserInsField(ActionEvent actionEvent) {
    }

    public void cepInsField(ActionEvent actionEvent) {
    }

    public void nomeInsField(ActionEvent actionEvent) {
    }

    public void cnpjInsField(ActionEvent actionEvent) {
    }

    public void emailInsField(ActionEvent actionEvent) {
    }

//Botões mudança de tela

    public void botaoItencaoDoacao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    public void botaoVoluntario(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    public void botaoCertificados(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/15-pontuacoes-certificados.fxml", "ReNutrir - Certificados");
    }

    public void botaoEventos(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/16-proximos-eventos.fxml", "ReNutrir - Eventos");
    }

    public void botaoPerfil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/17-perfil-doador.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ReNutrir - Perfil");
            stage.show();
            HelloController controller = loader.getController();
            controller.exibirPerfilDoador();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void botaoDoacoesPendentesDoador(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/18-doacoes-pendentes-doador.fxml", "ReNutrir - Doações Pendentes");
    }

    public void instituicaoChecar(ActionEvent actionEvent) {
        // Implementação necessária
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

    public void botaoHistoricoDoacoes(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/21-historico-doacoes.fxml", "ReNutrir - Histórico de Doações");
    }

    public void botaoSolicitarDoacoes(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml", "ReNutrir - Solicitar Doações");
    }

    public void botaoPerfilInstituicao(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/23-perfil-instituicao.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ReNutrir - Perfil");
            stage.show();
            HelloController controller = loader.getController();
            controller.exibirPerfilInstituicao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void botaoAtuaisVoluntarios(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/24-voluntarios.fxml", "ReNutrir - Voluntários");
    }

    public void botaoDoacoesPendentesInstituicao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/26-doacoes-pendentes-inst.fxml", "ReNutrir - Doações Pendentes");
    }

    public void botaoSolicitarVoluntarios(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/25-solicitar-voluntarios.fxml", "ReNutrir - Solicitar Voluntários");
    }


    //Tela 15 Certificados e pontuação

    @FXML
    private Button gerarCertificadoBotao;

    @FXML
    private Label labelInfoDoadorCertificado;

    @FXML
    void botaoVoltar15(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml","ReNutrir - Menu Doador");
    }

    @FXML
    void botaoGerarCertificado(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
        ControladorCertificado controladorCertificado = new ControladorCertificado();

        controladorCertificado.verificarProgressoParaCertificado(doador);
    }


    //Tela 17 Perfil doador

    @FXML
    private Label exibirInfoDoadorLabel;

    public static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.replaceAll(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4"
        );
    }

    public void exibirPerfilDoador() throws IOException {
        System.out.println("Iniciando exibição do perfil do doador.");
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado != null) {
            String cpfFormatado = formatarCPF(doadorLogado.getCpf());

            String perfilDoador = String.format(
                    "Nome: %s\n" +
                            "Nome de Usuário: %s\n" +
                            "Telefone: %s\n" +
                            "CPF: %s\n" +
                            "Email: %s\n" +
                            "%s\n",
                    doadorLogado.getNome() != null ? doadorLogado.getNome() : "",
                    doadorLogado.getNomeUsuario() != null ? doadorLogado.getNomeUsuario() : "",
                    doadorLogado.getTelefone() != null ? doadorLogado.getTelefone() : "",
                    cpfFormatado,
                    doadorLogado.getEmail() != null ? doadorLogado.getEmail() : "",
                    doadorLogado.getEndereco() != null ? doadorLogado.getEndereco() : ""
            );

            System.out.println("Perfil do doador: " + perfilDoador);
            if (exibirInfoDoadorLabel != null) {
                exibirInfoDoadorLabel.setText(perfilDoador);
            } else {
                System.out.println("Não inicializou.");
            }
        } else {
            if (exibirInfoDoadorLabel != null) {
                exibirInfoDoadorLabel.setText("Nenhum doador logado.");
            } else {
                System.out.println("A label exibirInfoDoadorLabel não está inicializada.");
            }
        }
    }


    //Tela 19 - Menu Instituição

    @FXML
    public void botaoCriarEvento() {
        realizarTrocaDeTela("/br/com/renutrir/20-criar-eventos.fxml", "ReNutrir - Criar Evento");
    }


    //Tela 20 - Criar eventos

    @FXML
    private Button criarEventosBotao;

    @FXML
    private Button editarEventoBotao;

    @FXML
    private Button listaEventosCriadosBotao;

    @FXML
    public void botaoListaEventosCriadosBotao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/20-2-lista-eventos.fxml","ReNutrir - Lista de Eventos");

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> atualizarListaEventos());
        pause.play();

        List<Evento> eventos = carregarEventosDoArquivo();
        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        StringBuilder eventosTexto = new StringBuilder(); //cada evento está relacionado com a inst que o criou
        for (Evento evento : eventos) {
            if (evento.getNome().equals(instituicaoLogada.getNome())) {
                eventosTexto.append(evento.toString()).append("\n");
            }
        }
        listaEventosArea.setText(eventosTexto.toString());
    }

    @FXML
    void botaoEditarEvento(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/20-3-editar-eventos.fxml","ReNutrir - Lista de Eventos");
        String nomeEventoSelecionado = nomeEventoField.getText();

        List<Evento> eventos = carregarEventosDoArquivo();
        Evento eventoParaEditar = eventos.stream()
                .filter(e -> e.getNome().equals(nomeEventoSelecionado))
                .findFirst()
                .orElse(null);

        if (eventoParaEditar != null) {
            eventoParaEditar.setNome(nomeEventoField.getText());
            eventoParaEditar.setData(LocalDate.parse(dataEventoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            eventoParaEditar.setHorario(LocalTime.parse(horarioEventoField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            eventoParaEditar.setLocal(endEventoField.getText());
            eventoParaEditar.setTipo(tipoEventoField.getText());
            eventoParaEditar.setDescricao(descricaoEventoField.getText());

            salvarEventoNoArquivo((Evento) eventos); //salva as mudanças

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evento Editado");
            alert.setHeaderText(null);
            alert.setContentText("O evento '" + nomeEventoParaEditar + "' foi editado com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Evento não encontrado.");
            alert.showAndWait();
        }
    }

    @FXML
    private Button criarNovoEventoBotao;

    @FXML
    public void botaoCriarNovoEvento() {
        realizarTrocaDeTela("/br/com/renutrir/20-1-detalhes-eventos.fxml", "ReNutrir - Criar Novo Evento");
    }

    //Tela 20.1 - Detalhes Eventos

    @FXML
    private TextField horarioEventoField;

    @FXML
    private Button botaoSalvarCriarEvento;

    @FXML
    private TextField endEventoField;

    @FXML
    private TextField tipoEventoField;

    @FXML
    private TextField nomeEventoField;

    @FXML
    private TextField dataEventoField;

    @FXML
    private TextArea descricaoEventoField;


    @FXML
    void botaoVoltar20(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    void salvarCriarEventoBotao(ActionEvent event) {
        try {
            String nome = nomeEventoField.getText();
            String dataStr = dataEventoField.getText();
            String horarioStr = horarioEventoField.getText();
            String local = endEventoField.getText();
            String tipo = tipoEventoField.getText();
            String descricao = descricaoEventoField.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataStr + " " + horarioStr, formatter);

            Evento evento = new Evento(nome, dataHora.toLocalDate(), local, dataHora.toLocalTime(), tipo, descricao);
            salvarEventoNoArquivo(evento);
            limparCamposEvento();

            //evento criado com sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evento Criado");
            alert.setHeaderText(null);
            alert.setContentText("O evento '" + nome + "' foi criado com sucesso!");
            alert.showAndWait();

        } catch (DateTimeParseException e) {
            //mostrar erro de formatação na data e hr
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Formato");
            alert.setHeaderText("Formato de Data/Hora Inválido");
            alert.setContentText("Por favor, insira a data e hora no formato 'dd/MM/yyyy HH:mm'.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCamposEvento() {
        nomeEventoField.clear();
        dataEventoField.clear();
        horarioEventoField.clear();
        endEventoField.clear();
        tipoEventoField.clear();
        descricaoEventoField.clear();
    }

    private void salvarEventoNoArquivo(Evento evento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eventos.txt", true))) {
            writer.write(evento.getNome() + ";" + evento.getData() + ";" + evento.getHorario() + ";" + evento.getLocal() + ";" + evento.getTipo() + ";" + evento.getDescricao());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Evento> carregarEventosDoArquivo() {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String nome = partes[0];
                LocalDate data = LocalDate.parse(partes[1]);
                String horario = partes[2];
                String local = partes[3];
                String tipo = partes[4];
                String descricao = partes[5];

                Evento evento = new Evento(nome, data, local, LocalTime.parse(horario), tipo, descricao);
                eventos.add(evento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    private void removerEventosPassados() {
        List<Evento> eventosAtuais = carregarEventosDoArquivo();
        LocalDateTime agora = LocalDateTime.now();

        eventosAtuais.removeIf(evento -> evento.getData().atTime(evento.getHorario()).isBefore(agora));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eventos.txt"))) {
            for (Evento evento : eventosAtuais) {
                writer.write(evento.getNome() + ";" + evento.getData() + ";" + evento.getHorario() + ";" + evento.getLocal() + ";" + evento.getTipo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    void botaoVoltar43(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    private Label listaEventosArea;

    private void atualizarListaEventos() {
        if (listaEventosArea == null) {
            System.err.println("Erro: listaEventosArea não foi inicializada.");
            return;
        }

        List<Evento> eventos = carregarEventosDoArquivo();
        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        StringBuilder eventosTexto = new StringBuilder();
        for (Evento evento : eventos) {
            if (evento.getNome().equals(instituicaoLogada.getNome())) {
                eventosTexto.append(evento.toString()).append("\n");
            }
        }

        listaEventosArea.setText(eventosTexto.toString());
    }


    //Tela 20.3 Editar eventos criados pela instituição

    @FXML
    void botaoVoltar44(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    private static Instituicao instituicaoAtual;



    public static Instituicao getInstituicaoAtual() {
        return instituicaoAtual;
    }

    public static void setInstituicaoAtual(Instituicao instituicao) {
        instituicaoAtual = instituicao;

    }

    //Tela 22

    private ControladorSolicitacaoDoacao controladorSolicitacaoDoacao = new ControladorSolicitacaoDoacao();

    @FXML
    public void botaoVoltar40() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Menu Instituição");
    }

    @FXML
    void solicitarDinheiro(ActionEvent event) {
        controladorSolicitacaoDoacao.solicitarDinheiro(event);
    }

    @FXML
    void solicitarAlimentos(ActionEvent event) {
        controladorSolicitacaoDoacao.solicitarAlimentos(event);
    }

    //Tela 23 Perfil Instituição

    @FXML
    private Label exibirInfoInstLabel;

    public void exibirPerfilInstituicao() throws IOException {
        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        if (instituicaoLogada != null) {
            String perfilInst = String.format(
                    "Nome: %s\n" +
                            "Nome de Usuário: %s\n" +
                            "Telefone: %s\n" +
                            "CNPJ: %s\n" +
                            "%s\n",
                    instituicaoLogada.getNome() != null ? instituicaoLogada.getNome() : "",
                    instituicaoLogada.getNomeUsuario() != null ? instituicaoLogada.getNomeUsuario() : "",
                    instituicaoLogada.getTelefone() != null ? instituicaoLogada.getTelefone() : "",
                    instituicaoLogada.getCnpj() != null ? instituicaoLogada.getCnpj() : "",
                    instituicaoLogada.getEndereco() != null ? instituicaoLogada.getEndereco() : ""
            );

            exibirInfoInstLabel.setText(perfilInst);
            System.out.println("Perfil: " + perfilInst);
        } else {
            exibirInfoInstLabel.setText("Nenhuma instituição logada.");
        }
    }


    //Limpar a sessão

    public void limparSessao() {
        SessaoDoador.getInstancia().limparSessao();
        doadorLogado = null;
    }

    //Tela 24 Voluntários (inst)


    @FXML
    private Button criarFuncaorVoluntarioBotao;

    @FXML
    private TextField inserirFuncaoVoluntario;

    public RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();

    @FXML
    void botaoCriarFuncaoVoluntario(ActionEvent event) {
        String funcao = inserirFuncaoVoluntario.getText();

        if (funcao != null && !funcao.trim().isEmpty()) {
            repositorioVoluntario.adicionarFuncao(funcao);
            showAlert(Alert.AlertType.INFORMATION, "Função criada!", "Função de voluntário '" + funcao + "' foi adicionada com sucesso!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "A função de voluntário não foi criada");
        }
    }


    //Tela 25 Solicitar Voluntários

    public void SolicitarNovosVoluntariosBotao(ActionEvent actionEvent) {
        SessaoInstituicao.getInstancia().getInstituicaoLogada();
        instituicaoAtual.solicitarVoluntarios();
    }

    @FXML
    void botaoVoltar58() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }


    //Tela 26 Doações Pendentes

    @FXML
    void botaoVoltar57() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    //Tela 26.1 Validar intenção

    @FXML
    void botaoVoltar59() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    public void cboxEscolherInstituicaoDoar(ActionEvent actionEvent) {
    }


    //Próximos métodos
}