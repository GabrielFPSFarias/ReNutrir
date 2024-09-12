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

    public static Instituicao getInstituicaoAtual() {
        return SessaoInstituicao.getInstancia().getInstituicaoLogada();
    }

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
    public void botaoVoltar6() {
        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
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
    public void botaoVoltar21() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar23() {
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
    }

    public void botaoDoacoesSolicitadas(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Confirmar Doação");
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

    //Tela 22

    private ControladorSolicitacaoDoacao controladorSolicitacaoDoacao = new ControladorSolicitacaoDoacao();

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
                            "%s\n\n" +
                            "Razão social: %s\n",
                    instituicaoLogada.getNome() != null ? instituicaoLogada.getNome() : "",
                    instituicaoLogada.getNomeUsuario() != null ? instituicaoLogada.getNomeUsuario() : "",
                    instituicaoLogada.getTelefone() != null ? instituicaoLogada.getTelefone() : "",
                    instituicaoLogada.getCnpj() != null ? instituicaoLogada.getCnpj() : "",
                    instituicaoLogada.getEndereco() != null ? instituicaoLogada.getEndereco() : "",
                    instituicaoLogada.getRazaoSocial() != null ? instituicaoLogada.getRazaoSocial() : ""
            );

            exibirInfoInstLabel.setText(perfilInst);
            System.out.println("Perfil: " + perfilInst);
        } else {
            exibirInfoInstLabel.setText("Nenhuma instituição logada.");
        }
    }

    //Tela 24 Voluntários (inst)

    public RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();

    //Limpar a sessão

    public void limparSessao() {
        SessaoDoador.getInstancia().limparSessao();
        doadorLogado = null;
    }

}