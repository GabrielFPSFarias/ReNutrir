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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static br.com.renutrir.servicos.ControladorCertificado.DOACOES_NECESSARIAS;

public class HelloController {

    private Doador doadorLogado;

    @FXML
    public TextField fieldEmailIns;

    @FXML
    public TextField fieldCnpjIns;

    @FXML
    public TextField fieldNomeIns;

    @FXML
    public TextField fieldInsCep;

    @FXML
    public TextField fieldUserNomeIns;

    @FXML
    public PasswordField fieldSenhaIns;

    @FXML
    public PasswordField fieldConfSenhaIns;

    @FXML
    public TextField fieldTelefoneIns;

    @FXML
    public TextField fieldEnderecoIns;

    @FXML
    public TextField fieldRefIns;

    @FXML
    public TextField fieldBairroIns;

    @FXML
    public TextField fieldCidadeIns;

    @FXML
    public TextField fieldNumeroIns;

    @FXML
    public TextField fieldCompIns;

    @FXML
    public TextField fieldUfIns;
    public CheckBox checarInstituicao;
    public Button instituicoesDoacaoBotao;
    public Button doacoesSolicitadasBotao;
    public Button sejaVoluntarioBotao;
    public Button transportesDoacoesBotao;
    public Button transportesPendentesBotao;
    public Button transportesConcluidosBotao;
    public Button transportesSolicitadosBotao;
    public Button confirmarTransportesBotao;
    public Button criarEventoBotao;
    public Button historicoDoacoesBotao;
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
    public void botaoVoltar22() {
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
        CadastroController cadastrarDoador = new CadastroController();
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
        CadastroController cadastrarInstituicao = new CadastroController();
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

    //Variáveis do cadastro doador

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldUserNome;

    @FXML
    private TextField fieldCep;

    @FXML
    private TextField fieldEndereco;

    @FXML
    private TextField fieldBairro;

    @FXML
    private TextField fieldNumero;

    @FXML
    private TextField fieldCidade;

    @FXML
    private TextField fieldUf;

    @FXML
    private TextField fieldComp;

    @FXML
    private TextField fieldRef;

    @FXML
    private TextField fieldCpf;

    @FXML
    private TextField fieldTelefone;

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private PasswordField fieldConfSenha;

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

    //Tela de login

    public void fieldLoginEmail() {

    }

    public void fieldLoginSenha() {

    }

    @FXML
    private SessaoDoador sessaoDoador;
    @FXML
    private SessaoInstituicao sessaoInstituicao;

    /*
    private Doador buscarDoadorNoRepositorio(String emailOuUsuario, String senha) {
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

    private Instituicao buscarInstituicaoNoRepositorio(String emailOuUsuario, String senha) {
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

    @FXML
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        if (checarInstituicao.isSelected()) {
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
     */

    @FXML
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        ControladorLogin controladorLogin = new ControladorLogin(this);

        if (checarInstituicao.isSelected()) {
            controladorLogin.processarLogin(emailOuUsuario, senha, true);
        } else {
            controladorLogin.processarLogin(emailOuUsuario, senha, false);
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

    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
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

    //Tela 05 - Intenção Doação

    @FXML
    private Button doarAgoraBotao;

    @FXML
    void botaoDoarAgora(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Realizar Doação");
    }


    //Tela 07 - Intenção Doação (Doador)

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
        realizarTrocaDeTela("/br/com/renutrir/07-6-produtos-limpeza.fxml", "ReNutrir - Doar");
    }

    @FXML
    void doarItemHgiene(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-8-higiene-pessoal.fxml", "ReNutrir - Doar");
    }

    //Tela 07.1 Doar com PIX

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
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela de confirmação do PIX.");
        }
    }

    @FXML
    void inserirValorPixField(ActionEvent event) {

    }

    //Tela 07.1.1 Confirmar PIX

    @FXML
    private TextField fieldPixCopiaCola;

    @FXML
    private Text valorDoacaoPixLabel;

    @FXML
    private TextField fieldIdTransacaoPix;

    @FXML
    private Button finalPixDoar;

    @FXML
    private Button copiarPixPagar;

    @FXML
    void botaoVoltar53(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-1-pix.fxml","ReNutrir - Doar com Pix");
    }

    public void configurarTelaConfirmarPix(String valorDoacao) {
        valorDoacaoPixLabel.setText("Valor: R$ " + valorDoacao);
        fieldPixCopiaCola.setText(gerarCodigoPixAleatorio());
    }

    @FXML
    void doarFinalPix(ActionEvent event) {
        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress(ProgressBar.INDETERMINATE_PROGRESS);

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
    void pagarPixCopiar() {
        String texto = fieldPixCopiaCola.getText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(texto);
        clipboard.setContent(content);
    }

    private String gerarCodigoPixAleatorio() {
        String[] codigos = {
                "00020126870014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirSA6006Recife62070503***63049D25",
                "00020126360014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrir6006Olinda62070503***63049D25"
        };
        int indice = new Random().nextInt(codigos.length);
        return codigos[indice];
    }

    @FXML
    void pixCopiaColaField(ActionEvent event) {

    }

    @FXML
    void idTransacaoPixField(ActionEvent event) {

    }



    //Tela 07.2 Doar com cartão

    @FXML
    private Button cartaoDebitoDoar;

    @FXML
    private Button cartaoCreditoDoar;

    @FXML
    private TextField fieldInserirValorCartao;

    @FXML
    void botaoVoltar30(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void inserirValorCartaoField(ActionEvent event) {

    }

    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1

    @FXML
    void doarCartaoDebito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-2-debito.fxml", "ReNutrir - Doar com Débito");
        valorDoacaoExibirDeb.setText("Valor: R$ " + valor);
    }

    @FXML
    void doarCartaoCredito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-1-credito.fxml", "ReNutrir - Doar com Crédito");
        valorDoacaoCreExibir.setText("Valor: R$ " + valor);
    }


    //Tela 07.2.1 Doar com crédito

    @FXML
    private TextField fieldInserirNomeTitularCre;

    @FXML
    private TextField fieldInserirNumCredito;

    @FXML
    private TextField fieldInserirSenhaCre;

    @FXML
    private Button creditoDoar;

    @FXML
    void botaoVoltar31(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    @FXML
    void doarCredito(ActionEvent event) {

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


    //Tela 07.2.2 Doar com débito

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

    @FXML
    void doarDebito(ActionEvent event) {

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

    //Tela 07.3 Doar com alimentos

    @FXML
    private TextField fieldInserirNomeAlimento;

    @FXML
    private TextField fieldInserirQtdAlimento;

    @FXML
    private Button botaoAlimentosDoar;

    @FXML
    void botaoVoltar33(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    public Label exibirInfoDoacaoLabel;

    @FXML
    private Button salvarComprovanteBotao;

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
            HelloController controlador = loader.getController();
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


    //Tela 07.4 Doar bebidas

    @FXML
    private TextField fieldInserirQtdBebida;

    @FXML
    private Button botaoBebidaDoar;

    @FXML
    private TextField fieldInserirNomeBebida;

    @FXML
    void botaoVoltar34(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void doarBebidaBotao(ActionEvent event) {
        String nomeBebida = fieldInserirNomeBebida.getText();
        String qtdBebida = fieldInserirQtdBebida.getText();

        if (nomeBebida.isEmpty() || qtdBebida.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdBebida);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome(): "Desconhecido";
        String tipoDoacao = "Bebidas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            HelloController controlador = loader.getController();
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

    //Tela 07.5 Doar roupas

    @FXML
    private TextField fieldInserirNomeRoupa;

    @FXML
    private Button botaoRoupaDoar;

    @FXML
    private TextField fieldInserirQtdRoupa;

    @FXML
    void botaoVoltar35(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeRoupa, dataHora);

            Stage stage = (Stage) botaoRoupaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirNomeRoupaField(ActionEvent event) {

    }

    @FXML
    void inserirQtdRoupaField(ActionEvent event) {

    }


    //Tela 07.6 Doar produtos de limpeza

    @FXML
    private Button botaoProdLimpezaDoar;

    @FXML
    private TextField fieldInserirQtdLimpeza;

    @FXML
    private TextField fieldInserirProdLimpeza;


    @FXML
    void botaoVoltar36(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeProdutoLimpeza, dataHora);

            Stage stage = (Stage) botaoProdLimpezaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirProdLimpezaField(ActionEvent event) {

    }

    @FXML
    void inserirQtdLimpezaField(ActionEvent event) {

    }


    //Tela 07.7 Doar móveis

    @FXML
    private TextField fieldInserirNomeMovel;

    @FXML
    private Button movelDoarBotao;

    @FXML
    private ComboBox<String> selecionarTipoMovelBox;

    @FXML
    void botaoVoltar37(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, 1, nomeMovel, dataHora);

            Stage stage = (Stage) movelDoarBotao.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirNomeMovelField(ActionEvent event) {

    }

    @FXML
    void boxSelecionarTipoMovel(ActionEvent event) {

    }

    //Tela 07.8 Doar itens de higiene pessoal

    @FXML
    private TextField fieldInserirProdHigiene;

    @FXML
    private TextField fieldInserirQtdHigiene;

    @FXML
    private Button botaoProdHigieneDoar;

    @FXML
    void botaoVoltar38(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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

    @FXML
    void inserirProdHigieneField(ActionEvent event) {

    }

    @FXML
    void inserirQtdHigieneField(ActionEvent event) {

    }

    //Tela 07.10 Doação concluída

    @FXML
    void botaoVoltar39(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }

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

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, int quantidade, String item, LocalDateTime dataHora) {
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s\nItem: %s\nQuantidade: %d",
                doadorNome, dataHoraFormatada, tipoDoacao, item, quantidade));

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
    }

    @FXML
    private Button botaoRegistrarDoacao;

    private HelloApplication application = new HelloApplication();

    @FXML
    private void registrarDoacao(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();

        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado. Certifique-se de que você está logado.");
            return;
        }

        String itemSelecionado = obterItemSelecionado();

        if (itemSelecionado == null || itemSelecionado.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Item selecionado inválido.");
            return;
        }

        application.showAlertComProgresso();

        new Thread(() -> {
            try {
                Thread.sleep(500);

                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                String status = "Concluída";

                Doacao doacao = new Doacao(doador.getNomeUsuario(), itemSelecionado, 1, dataHora, status);

                RepositorioDoacoes repositorioDoacoes = new RepositorioDoacoes();
                repositorioDoacoes.adicionarDoacao(doacao);

                salvarDoacoesEmArquivo(repositorioDoacoes);

                int doacoesConcluidas = contarDoacoes(doador);
                int doacoesRestantes = DOACOES_NECESSARIAS - doacoesConcluidas;
                if (doacoesRestantes > 0) {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Faltam " + doacoesRestantes + " doações para alcançar a meta do certificado."));
                } else {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Seu certificado já está disponível no menu doador."));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(application::hideProgress);
            }
        }).start();
    }

    private void salvarDoacoesEmArquivo(RepositorioDoacoes repositorioDoacoes) {
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

    private String obterItemSelecionado() {
        return "item";
    }

    public int contarDoacoes(Doador doador) {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado == null) {
            System.out.println("Nenhum doador está logado.");
            return 0;
        }

        String nomeUsuario = doadorLogado.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_doacoes.dat";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            return 0;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            @SuppressWarnings("unchecked")
            List<Doacao> doacoes = (List<Doacao>) ois.readObject();

            int count = 0;
            for (Doacao doacao : doacoes) {
                if (doacao.getNomeDoador().equals(nomeUsuario)) {
                    count++;
                }
            }

            System.out.println("Número de doações: " + count);
            return count;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    //Tela 09 Seja Voluntário

    @FXML
    private TextField fieldHoraInicialVoluntario;

    @FXML
    private CheckBox boxQuintaVoluntario;

    @FXML
    private CheckBox boxQuartaVoluntario;

    @FXML
    private CheckBox boxSextaVoluntario;

    @FXML
    private TextField fieldHoraFinalVoluntario;

    @FXML
    private CheckBox boxSegundaVoluntario;

    @FXML
    private Text exibirVoluntarioLabel;

    @FXML
    private CheckBox boxTercaVoluntario;

    @FXML
    private CheckBox boxSabadoVoluntario;

    @FXML
    private CheckBox boxDomingoVoluntario;

    @FXML
    private Button queroVoluntarioBotao;

    private String caminhoArquivoInstituicoes = "/src/dados/arquivo1.txt";

    @FXML
    public void botaoQueroVoluntario() {

    }

    @FXML
    private ComboBox<String> cboxFuncaoVoluntario;

    @FXML
    private ComboBox<String> cboxInstVinculada;

    private List<String> listFuncoesVoluntario = new ArrayList<>();

    @FXML
    public void funcaoVoluntarioCbox() {
        // listFuncoesVoluntario.clear();
        listFuncoesVoluntario.add("Transportador de doações");
        listFuncoesVoluntario.add("Ajudante dos Eventos");

        ObservableList<String> observableListFuncoes = FXCollections.observableArrayList(listFuncoesVoluntario);
        cboxFuncaoVoluntario.setItems(observableListFuncoes);
    }

    @FXML
    public void instVinculadaCbox() {
        cboxInstVinculada.getItems().clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivoInstituicoes))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    String nomeInstituicao = linha.substring(6).trim();
                    cboxInstVinculada.getItems().add(nomeInstituicao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private TextArea listaEventosArea;

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



    //Tela 22 - Solicitar Doações (Instituição)

    @FXML
    private Button bebidasSolicitar;

    @FXML
    private Button itemHigienerSolicitar;

    @FXML
    private Button produtoLimpezaSolicitar;

    @FXML
    private Button roupasSolicitar;

    @FXML
    private Button moveisSolicitar;

    @FXML
    private Button dinheiroSolicitar;

    @FXML
    private Button alimentosSolicitar;

    private static Instituicao instituicaoAtual;


    public static Instituicao getInstituicaoAtual() {
        return instituicaoAtual;
    }

    public static void setInstituicaoAtual(Instituicao instituicao) {
        instituicaoAtual = instituicao;
    }

    @FXML
    public void botaoVoltar40() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Menu Instituição");
    }

    @FXML
    void solicitarDinheiro(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-1-valores.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarAlimentos(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-2-alimentos.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarRoupas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-3-roupas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarMoveis(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-4-moveis.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarBebidas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-5-bebidas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarProdutoLimpeza(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-6-prod-limpeza.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarItemHgiene(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-7-higiene-pessoal.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.1 Solicitar Dinheiro

    @FXML
    private Button confValorSolicitar;

    @FXML
    private TextField doacaoValorSolicitarField;

    @FXML
    void solicitarConfValor(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoValor(ActionEvent event) {

    }

    @FXML
    void botaoVoltar45() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.2 Solicitar Alimentos

    @FXML
    private Button confAlimentosSolicitar;

    @FXML
    private TextField doacaoAlimentosSolicitarField;


    @FXML
    void solicitarConfAlimentos(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoAlimentos(ActionEvent event) {

    }

    @FXML
    void botaoVoltar46() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.3 Solicitar Roupas

    @FXML
    private TextField doacaoRoupasSolicitarField;

    @FXML
    private Button confRoupasSolicitar;

    @FXML
    void solicitarConfRoupas(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoRoupas(ActionEvent event) {

    }

    @FXML
    void botaoVoltar47() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.4 Solicitar Móveis

    @FXML
    private Button confMoveisSolicitar;

    @FXML
    private TextField doacaoMoveisSolicitarField;

    @FXML
    void solicitarConfMoveis(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoMoveis(ActionEvent event) {

    }

    @FXML
    void botaoVoltar48() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.5 Solicitar Bebidas

    @FXML
    private TextField doacaoBebidasSolicitarField;

    @FXML
    private Button confBebidasSolicitar;

    @FXML
    void solicitarConfBebidas(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoBebidas(ActionEvent event) {

    }

    @FXML
    void botaoVoltar49() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.6 Solicitar Produtos de limpeza

    @FXML
    private TextField doacaoProdLimpezaSolicitarField;

    @FXML
    private Button confProdLimpezaSolicitar;


    @FXML
    void solicitarConfProdLimpeza(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoProdLimpeza(ActionEvent event) {

    }

    @FXML
    void botaoVoltar50() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.7 Solicitar Produtos de higiene pessoal

    @FXML
    private Button confProdHigieneSolicitar;

    @FXML
    private TextField doacaoProdHigieneSolicitarField;

    @FXML
    void solicitarConfProdHigiene(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoProdHigene(ActionEvent event) {

    }

    @FXML
    void botaoVoltar51() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
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

    //Próximos métodos
}
/*

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
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static br.com.renutrir.servicos.ControladorCertificado.DOACOES_NECESSARIAS;

public class HelloController {

    private Doador doadorLogado;

    @FXML
    public TextField fieldEmailIns;

    @FXML
    public TextField fieldCnpjIns;

    @FXML
    public TextField fieldNomeIns;

    @FXML
    public TextField fieldInsCep;

    @FXML
    public TextField fieldUserNomeIns;

    @FXML
    public PasswordField fieldSenhaIns;

    @FXML
    public PasswordField fieldConfSenhaIns;

    @FXML
    public TextField fieldTelefoneIns;

    @FXML
    public TextField fieldEnderecoIns;

    @FXML
    public TextField fieldRefIns;

    @FXML
    public TextField fieldBairroIns;

    @FXML
    public TextField fieldCidadeIns;

    @FXML
    public TextField fieldNumeroIns;

    @FXML
    public TextField fieldCompIns;

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
    public Button solicitarDoacoesBotao;
    public Button atuaisVoluntariosBotao;
    public Button perfilInstituicaoBotao;
    public Button doacoesPendentesInstituicaoBotao;
    public Button solicitarVoluntariosBotao;

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginSenhaField;

    @FXML
    private Button loginEntrarBotao;

    @FXML
    private Button loginBotao;

    @FXML
    private Button cadastroBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    private Button confCad;

    @FXML
    private Button confCadIns;

    @FXML
    private Button doadorBotao;

    @FXML
    private Button instBotao;

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
    public void botaoVoltar22() {
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

    private boolean verificarCamposInstituicaoValidos(String email, String nomeUsuario, String cnpj) {
        return email != null && !email.trim().isEmpty() &&
                nomeUsuario != null && !nomeUsuario.trim().isEmpty() &&
                cnpj != null && !cnpj.trim().isEmpty();
    }

    private boolean verificarCamposDoadorValidos(String email, String nomeUsuario, String cpf) {
        return email != null && !email.trim().isEmpty() &&
                nomeUsuario != null && !nomeUsuario.trim().isEmpty() &&
                cpf != null && !cpf.trim().isEmpty();
    }

    public void confirmarCadastro() {
        String nome = fieldNome.getText();
        String nomeUsuario = fieldUserNome.getText();
        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();
        String confSenha = fieldConfSenha.getText();
        String telefone = fieldTelefone.getText();
        String cpf = fieldCpf.getText();
        String endereco = fieldEndereco.getText();
        String bairro = fieldBairro.getText();
        String numero = fieldNumero.getText();
        String municipio = fieldCidade.getText();
        String uf = fieldUf.getText();
        String comp = fieldComp.getText();
        String ref = fieldRef.getText();

        RepositorioDoador repositorioContas = new RepositorioDoador();

        if (!verificarCamposDoadorValidos(email, nomeUsuario, cpf)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Os campos não podem estar vazios.");
        } else if (repositorioContas.buscarDoadorPorCpf(cpf).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "E-mail, nome de usuário ou CPF já existente.");
        } else {

            if (email == null || email.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail não pode estar vazio.");
                return;
            }

            if (!email.contains("@")) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail deve conter um '@'.");
                return;
            }

            if (senha == null || senha.length() < 4) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A senha deve ter pelo menos 4 caracteres.");
                return;
            }

            if (!senha.equals(confSenha)) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "As senhas não correspondem.");
                return;
            }

            if (repositorioContas.buscarDoadorPorNomeUsuario(nomeUsuario).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma conta cadastrada com esse nome de usuário.");
                return;
            }

            if (repositorioContas.buscarDoadorPorEmail(email).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma conta cadastrada com esse e-mail.");
                return;
            }

            Doador doador = new Doador();
            doador.setNome(nome);
            doador.setNomeUsuario(nomeUsuario);
            doador.setEmail(email);
            doador.setSenha(senha);
            doador.setTelefone(telefone);

            try {
                doador.setCpf(cpf);
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O CPF digitado é inválido.");
                return;
            }

            doador.setEndereco(new Endereco(endereco, bairro, numero, municipio, uf, comp, ref));

            repositorioContas.adicionarDoador(doador);
            showAlert(Alert.AlertType.INFORMATION, "Cadastro", "Cadastro de doador confirmado!");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void confirmarCadastroIns() {
        String nome = fieldNomeIns.getText();
        String nomeUsuario = fieldUserNomeIns.getText();
        String email = fieldEmailIns.getText();
        String senha = fieldSenhaIns.getText();
        String confSenha = fieldConfSenhaIns.getText();
        String telefone = fieldTelefoneIns.getText();
        String cnpj = fieldCnpjIns.getText();
        String endereco = fieldEnderecoIns.getText();
        String bairro = fieldBairroIns.getText();
        String numero = fieldNumeroIns.getText();
        String municipio = fieldCidadeIns.getText();
        String uf = fieldUfIns.getText();
        String comp = fieldCompIns.getText();
        String ref = fieldRefIns.getText();

        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();

        if (!verificarCamposInstituicaoValidos(email, nomeUsuario, cnpj)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Os campos não podem estar vazios.");
        } else if (repositorioInstituicao.buscarInstituicaoPorCnpj(cnpj).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "E-mail, nome de usuário ou CNPJ já existente.");
        } else {

            if (email == null || email.trim().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail não pode estar vazio.");
                return;
            }

            if (!email.contains("@")) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail deve conter um '@'.");
                return;
            }

            if (senha == null || senha.length() < 4) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A senha deve ter pelo menos 4 caracteres.");
                return;
            }

            if (!senha.equals(confSenha)) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "As senhas não correspondem.");
                return;
            }

            if (repositorioInstituicao.buscarInstituicaoPorEmail(email).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse e-mail.");
                return;
            }

            if (repositorioInstituicao.buscarInstituicaoPorNomeUsuario(nomeUsuario).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse nome de usuário.");
                return;
            }

            Instituicao instituicao = new Instituicao();
            instituicao.setNome(nome);
            instituicao.setNomeUsuario(nomeUsuario);
            instituicao.setEmail(email);
            instituicao.setSenha(senha);
            instituicao.setTelefone(telefone);

            try {
                instituicao.setCnpj(cnpj);
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O CNPJ digitado é inválido ou não existe.");
                System.out.println("CNPJ inválido.");
                return;
            }

            instituicao.setEndereco(new Endereco(endereco, bairro, numero, municipio, uf, comp, ref));

            repositorioInstituicao.adicionarInstituicao(instituicao);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro da instituição confirmado!");
            alert.showAndWait();
        }
    }

    void trocarTela(Stage stage, String fxmlFile, String title) {
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

    //Variáveis do cadastro doador

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldUserNome;

    @FXML
    private TextField fieldCep;

    @FXML
    private TextField fieldEndereco;

    @FXML
    private TextField fieldBairro;

    @FXML
    private TextField fieldNumero;

    @FXML
    private TextField fieldCidade;

    @FXML
    private TextField fieldUf;

    @FXML
    private TextField fieldComp;

    @FXML
    private TextField fieldRef;

    @FXML
    private TextField fieldCpf;

    @FXML
    private TextField fieldTelefone;

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private PasswordField fieldConfSenha;

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

    //Tela de login

    public void fieldLoginEmail() {

    }

    public void fieldLoginSenha() {

    }

    @FXML
    private SessaoDoador sessaoDoador;
    @FXML
    private SessaoInstituicao sessaoInstituicao;

    private Doador buscarDoadorNoRepositorio(String emailOuUsuario, String senha) {
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

    private Instituicao buscarInstituicaoNoRepositorio(String emailOuUsuario, String senha) {
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

    @FXML
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        if (checarInstituicao.isSelected()) {
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

    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
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

    //Tela 05 - Intenção Doação

    @FXML
    private Button doarAgoraBotao;

    @FXML
    private ComboBox<Instituicao> selecionarInstituicaoCbox;

    @FXML
    private Button instituicoesDoacaoBotao;

    private RepositorioInstituicao repositorioInstituicao;

    public void configurarTela() {
        repositorioInstituicao = new RepositorioInstituicao();
        popularComboBoxInstituicoes();
    }

    private void popularComboBoxInstituicoes() {
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();
        selecionarInstituicaoCbox.getItems().clear();
        selecionarInstituicaoCbox.getItems().addAll(instituicoes);

        selecionarInstituicaoCbox.setCellFactory(param -> new ListCell<Instituicao>() {
            @Override
            protected void updateItem(Instituicao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Instituição" : item.getNome());
            }
        });

        selecionarInstituicaoCbox.setButtonCell(new ListCell<Instituicao>() {
            @Override
            protected void updateItem(Instituicao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Instituição" : item.getNome());
            }
        });
    }


    @FXML
    void botaoDoarAgora(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Realizar Doação");

    }

    @FXML
    void cboxSelecionarInstituicao(ActionEvent event) {
        Instituicao instituicaoSelecionada = selecionarInstituicaoCbox.getValue();
        if (instituicaoSelecionada != null) {
            //colocar pra doar pra a inst selecionada
        }
    }


    //Tela 07 - Intenção Doação (Doador)

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
        realizarTrocaDeTela("/br/com/renutrir/07-6-produtos-limpeza.fxml", "ReNutrir - Doar");
    }

    @FXML
    void doarItemHgiene(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-8-higiene-pessoal.fxml", "ReNutrir - Doar");
    }

    //Tela 07.1 Doar com PIX

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
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a tela de confirmação do PIX.");
        }
    }

    @FXML
    void inserirValorPixField(ActionEvent event) {

    }

    //Tela 07.1.1 Confirmar PIX

    @FXML
    private TextField fieldPixCopiaCola;

    @FXML
    private Text valorDoacaoPixLabel;

    @FXML
    private TextField fieldIdTransacaoPix;

    @FXML
    private Button finalPixDoar;

    @FXML
    private Button copiarPixPagar;

    @FXML
    void botaoVoltar53(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-1-pix.fxml","ReNutrir - Doar com Pix");
    }

    public void configurarTelaConfirmarPix(String valorDoacao) {
        valorDoacaoPixLabel.setText("Valor: R$ " + valorDoacao);
        fieldPixCopiaCola.setText(gerarCodigoPixAleatorio());
    }

    @FXML
    void doarFinalPix(ActionEvent event) {
        ProgressAlert progressAlert = new ProgressAlert();
        progressAlert.start(new Stage());
        progressAlert.showProgress(ProgressBar.INDETERMINATE_PROGRESS);

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
    void pagarPixCopiar() {
        String texto = fieldPixCopiaCola.getText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(texto);
        clipboard.setContent(content);
    }

    private String gerarCodigoPixAleatorio() {
        String[] codigos = {
                "00020126870014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrirSA6006Recife62070503***63049D25",
                "00020126360014BR.GOV.BCB.PIX0127renutrir@instituicao.com.br0234DoacaoparaInstituicoesReNutrir5204000053039865802BR5911ReNutrir6006Olinda62070503***63049D25"
        };
        int indice = new Random().nextInt(codigos.length);
        return codigos[indice];
    }

    @FXML
    void pixCopiaColaField(ActionEvent event) {

    }

    @FXML
    void idTransacaoPixField(ActionEvent event) {

    }



    //Tela 07.2 Doar com cartão

    @FXML
    private Button cartaoDebitoDoar;

    @FXML
    private Button cartaoCreditoDoar;

    @FXML
    private TextField fieldInserirValorCartao;

    @FXML
    void botaoVoltar30(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void inserirValorCartaoField(ActionEvent event) {

    }

    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1

    @FXML
    void doarCartaoDebito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-2-debito.fxml", "ReNutrir - Doar com Débito");
        valorDoacaoExibirDeb.setText("Valor: R$ " + valor);
    }

    @FXML
    void doarCartaoCredito(ActionEvent event) {
        String valor = fieldInserirValorCartao.getText();
        realizarTrocaDeTela("/br/com/renutrir/07-2-1-credito.fxml", "ReNutrir - Doar com Crédito");
        valorDoacaoCreExibir.setText("Valor: R$ " + valor);
    }


    //Tela 07.2.1 Doar com crédito

    @FXML
    private TextField fieldInserirNomeTitularCre;

    @FXML
    private TextField fieldInserirNumCredito;

    @FXML
    private TextField fieldInserirSenhaCre;

    @FXML
    private Button creditoDoar;

    @FXML
    void botaoVoltar31(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-2-cartao.fxml", "ReNutrir - Doar com Cartão");
    }

    @FXML
    void doarCredito(ActionEvent event) {

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


    //Tela 07.2.2 Doar com débito

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

    @FXML
    void doarDebito(ActionEvent event) {

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

    //Tela 07.3 Doar com alimentos

    @FXML
    private TextField fieldInserirNomeAlimento;

    @FXML
    private TextField fieldInserirQtdAlimento;

    @FXML
    private Button botaoAlimentosDoar;

    @FXML
    void botaoVoltar33(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    public Label exibirInfoDoacaoLabel;

    @FXML
    private Button salvarComprovanteBotao;

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
            HelloController controlador = loader.getController();
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


    //Tela 07.4 Doar bebidas

    @FXML
    private TextField fieldInserirQtdBebida;

    @FXML
    private Button botaoBebidaDoar;

    @FXML
    private TextField fieldInserirNomeBebida;

    @FXML
    void botaoVoltar34(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    void doarBebidaBotao(ActionEvent event) {
        String nomeBebida = fieldInserirNomeBebida.getText();
        String qtdBebida = fieldInserirQtdBebida.getText();

        if (nomeBebida.isEmpty() || qtdBebida.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdBebida);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de validação", "Por favor, preencha todos os campos");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome(): "Desconhecido";
        String tipoDoacao = "Bebidas";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Parent root = loader.load();
            HelloController controlador = loader.getController();
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

    //Tela 07.5 Doar roupas

    @FXML
    private TextField fieldInserirNomeRoupa;

    @FXML
    private Button botaoRoupaDoar;

    @FXML
    private TextField fieldInserirQtdRoupa;

    @FXML
    void botaoVoltar35(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeRoupa, dataHora);

            Stage stage = (Stage) botaoRoupaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirNomeRoupaField(ActionEvent event) {

    }

    @FXML
    void inserirQtdRoupaField(ActionEvent event) {

    }


    //Tela 07.6 Doar produtos de limpeza

    @FXML
    private Button botaoProdLimpezaDoar;

    @FXML
    private TextField fieldInserirQtdLimpeza;

    @FXML
    private TextField fieldInserirProdLimpeza;


    @FXML
    void botaoVoltar36(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeProdutoLimpeza, dataHora);

            Stage stage = (Stage) botaoProdLimpezaDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirProdLimpezaField(ActionEvent event) {

    }

    @FXML
    void inserirQtdLimpezaField(ActionEvent event) {

    }


    //Tela 07.7 Doar móveis

    @FXML
    private TextField fieldInserirNomeMovel;

    @FXML
    private Button movelDoarBotao;

    @FXML
    private ComboBox<String> selecionarTipoMovelBox;

    @FXML
    void botaoVoltar37(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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
            HelloController controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, 1, nomeMovel, dataHora);

            Stage stage = (Stage) movelDoarBotao.getScene().getWindow();
            stage.setTitle("ReNutrir - Doação Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    void inserirNomeMovelField(ActionEvent event) {

    }

    @FXML
    void boxSelecionarTipoMovel(ActionEvent event) {

    }

    //Tela 07.8 Doar itens de higiene pessoal

    @FXML
    private TextField fieldInserirProdHigiene;

    @FXML
    private TextField fieldInserirQtdHigiene;

    @FXML
    private Button botaoProdHigieneDoar;

    @FXML
    void botaoVoltar38(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/07-confirmar-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

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

    @FXML
    void inserirProdHigieneField(ActionEvent event) {

    }

    @FXML
    void inserirQtdHigieneField(ActionEvent event) {

    }

    //Tela 07.10 Doação concluída

    @FXML
    void botaoVoltar39(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }

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

    public void setInformacoesDoacao(String doadorNome, String tipoDoacao, int quantidade, String item, LocalDateTime dataHora) {
        String dataHoraFormatada = dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        exibirInfoDoacaoLabel.setText(String.format(
                "Doador: %s\nData e hora: %s\nTipo da doação: %s\nItem: %s\nQuantidade: %d",
                doadorNome, dataHoraFormatada, tipoDoacao, item, quantidade));

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
    }

    @FXML
    private Button botaoRegistrarDoacao;

    private HelloApplication application = new HelloApplication();

    @FXML
    private void registrarDoacao(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();

        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado. Certifique-se de que você está logado.");
            return;
        }

        String itemSelecionado = obterItemSelecionado();

        if (itemSelecionado == null || itemSelecionado.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Item selecionado inválido.");
            return;
        }

        application.showAlertComProgresso();

        new Thread(() -> {
            try {
                Thread.sleep(500);

                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                String status = "Concluída";

                Doacao doacao = new Doacao(doador.getNomeUsuario(), itemSelecionado, 1, dataHora, status);

                RepositorioDoacoes repositorioDoacoes = new RepositorioDoacoes();
                repositorioDoacoes.adicionarDoacao(doacao);

                salvarDoacoesEmArquivo(repositorioDoacoes);

                int doacoesConcluidas = contarDoacoes(doador);
                int doacoesRestantes = DOACOES_NECESSARIAS - doacoesConcluidas;
                if (doacoesRestantes > 0) {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Faltam " + doacoesRestantes + " doações para alcançar a meta do certificado."));
                } else {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Seu certificado já está disponível no menu doador."));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(application::hideProgress);
            }
        }).start();
    }

    private void salvarDoacoesEmArquivo(RepositorioDoacoes repositorioDoacoes) {
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

    /*
    @FXML
    private void registrarDoacao(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();

        if (doador == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Doador não encontrado. Certifique-se de que você está logado.");
            return;
        }

        String itemSelecionado = obterItemSelecionado();

        if (itemSelecionado == null || itemSelecionado.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Item selecionado inválido.");
            return;
        }

        application.showAlertComProgresso();

        new Thread(() -> {
            try {
                Thread.sleep(500);

                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                String status = "Concluída";
                String doacaoInfo = "Doador: " + doador.getNomeUsuario() + ", Item: " + itemSelecionado + ", Quantidade: " + 1
                        + ", Data: " + dataHora + ", Status: " + status;

                File diretorio = new File("src/dados/");
                if (!diretorio.exists()) {
                    diretorio.mkdirs();
                }

                String nomeArquivo = "src/dados/" + doador.getNomeUsuario() + "_doacoes.txt";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
                    writer.write(doacaoInfo);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível registrar a doação."));
                    return;
                }

                int doacoesConcluidas = contarDoacoes(doador);
                int doacoesRestantes = DOACOES_NECESSARIAS - doacoesConcluidas;
                if (doacoesRestantes > 0) {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Faltam " + doacoesRestantes + " doações para alcançar a meta do certificado."));
                } else {
                    Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "Doação Concluída", "Sua doação foi registrada com sucesso! " +
                            "Seu certificado já está disponível no menu doador."));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(application::hideProgress);
            }
        }).start();
    }


    private String obterItemSelecionado() {
        return "item";
    }

    public int contarDoacoes(Doador doador) {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado == null) {
            System.out.println("Nenhum doador está logado.");
            return 0;
        }

        String nomeUsuario = doadorLogado.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_doacoes.dat";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            return 0;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            @SuppressWarnings("unchecked")
            List<Doacao> doacoes = (List<Doacao>) ois.readObject();

            int count = 0;
            for (Doacao doacao : doacoes) {
                if (doacao.getNomeDoador().equals(nomeUsuario)) {
                    count++;
                }
            }

            System.out.println("Número de doações: " + count);
            return count;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
    public int contarDoacoes(Doador doador) {
        String nomeUsuario = doador.getNomeUsuario();
        String caminhoArquivo = "src/dados/" + nomeUsuario + "_doacoes.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            int count = (int) reader.lines().count();
            System.out.println("Número de doações: " + count);
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }



     //Tela 09 Seja Voluntário

    @FXML
    private TextField fieldHoraInicialVoluntario;

    @FXML
    private CheckBox boxQuintaVoluntario;

    @FXML
    private CheckBox boxQuartaVoluntario;

    @FXML
    private CheckBox boxSextaVoluntario;

    @FXML
    private TextField fieldHoraFinalVoluntario;

    @FXML
    private CheckBox boxSegundaVoluntario;

    @FXML
    private Text exibirVoluntarioLabel;

    @FXML
    private CheckBox boxTercaVoluntario;

    @FXML
    private CheckBox boxSabadoVoluntario;

    @FXML
    private CheckBox boxDomingoVoluntario;

    @FXML
    private Button queroVoluntarioBotao;

    private String caminhoArquivoInstituicoes = "/src/dados/arquivo1.txt";

    @FXML
    public void botaoQueroVoluntario() {

    }
//-----------------------------------------------------------------------------------------------------------------------
 @FXML
private ComboBox<String> cboxFuncaoVoluntario;

@FXML
private ComboBox<String> cboxInstVinculada;

private List<String> listFuncoesVoluntario = new ArrayList<>();

@FXML
public void funcaoVoluntarioCbox() {
    listFuncoesVoluntario.clear(); // Limpa a lista para evitar duplicação
    listFuncoesVoluntario.add("Transportador de doações");
    listFuncoesVoluntario.add("Ajudante dos Eventos");

    ObservableList<String> observableListFuncoes = FXCollections.observableArrayList(listFuncoesVoluntario);
    cboxFuncaoVoluntario.setItems(observableListFuncoes);
}

@FXML
public void instVinculadaCbox() {
    cboxInstVinculada.getItems().clear();
    try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivoInstituicoes))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (linha.startsWith("Nome: ")) {
                String nomeInstituicao = linha.substring(6).trim();
                cboxInstVinculada.getItems().add(nomeInstituicao);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    /*
    @FXML
    public void instVinculadaCbox() {
        cboxInstVinculada.getItems().clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivoInstituicoes))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    String nomeInstituicao = linha.substring(6).trim();
                    cboxInstVinculada.getItems().add(nomeInstituicao);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void funcaoVoluntarioCbox(ActionEvent event) {
        cboxFuncaoVoluntario.getItems().clear(); // Limpa as opções atuais, se houver
        cboxFuncaoVoluntario.getItems().addAll("Transportador de Doações", "Ajudante dos Eventos");
    }
    */
//---------------------------------------------------------------------------------
    /*
    @FXML
    private ComboBox<Voluntario> cboxFuncaoVoluntario;

    @FXML
    private ComboBox<Voluntario> cboxInstVinculada;

    private List<Voluntario> listVoluntario = new ArrayList<>();

    private ObservableList<Voluntario> observableListVoluntario;

    Voluntario instancia = new Voluntario();*/
/*
    @FXML
    public void funcaoVoluntarioCbox () {
        instancia.getFuncaoVoluntariado("Transportador de doações");
        listVoluntario.add(instancia);

        observableListVoluntario = FXCollections.observableArrayList(listVoluntario);
        cboxFuncaoVoluntario.setItems(observableListVoluntario);
    }
*/
   /* @FXML
    public void instVinculadaCbox () {

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


    /*
    @FXML
    void botaoGerarCertificado(ActionEvent event) {
        Doador doador = SessaoDoador.getInstancia().getDoadorLogado();
        Certificado certificado = doador.getCertificado();

        if (certificado == null || !certificado.isCertificadoEmitido()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Certificado não disponível");
            alert.setHeaderText(null);
            alert.setContentText("O doador ainda não completou 50 doações.");
            alert.showAndWait();
        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Certificado gerado");
                alert.setHeaderText(null);
                alert.setContentText("O certificado foi gerado com sucesso.");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao gerar certificado");
                alert.setHeaderText(null);
                alert.setContentText("Ocorreu um erro ao gerar o certificado.");
                alert.showAndWait();
            }
        }
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
                            "Email: %s\n",
                    doadorLogado.getNome() != null ? doadorLogado.getNome() : "",
                    doadorLogado.getNomeUsuario() != null ? doadorLogado.getNomeUsuario() : "",
                    doadorLogado.getTelefone() != null ? doadorLogado.getTelefone() : "",
                    cpfFormatado,
                    doadorLogado.getEmail() != null ? doadorLogado.getEmail() : ""
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
    private TextArea listaEventosArea;

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



    //Tela 22 - Solicitar Doações (Instituição)

    @FXML
    private Button bebidasSolicitar;

    @FXML
    private Button itemHigienerSolicitar;

    @FXML
    private Button produtoLimpezaSolicitar;

    @FXML
    private Button roupasSolicitar;

    @FXML
    private Button moveisSolicitar;

    @FXML
    private Button dinheiroSolicitar;

    @FXML
    private Button alimentosSolicitar;

    private static Instituicao instituicaoAtual;


    public static Instituicao getInstituicaoAtual() {
        return instituicaoAtual;
    }

    public static void setInstituicaoAtual(Instituicao instituicao) {
        instituicaoAtual = instituicao;
    }

    @FXML
    public void botaoVoltar40() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Menu Instituição");
    }

    @FXML
    void solicitarDinheiro(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-1-valores.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarAlimentos(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-2-alimentos.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarRoupas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-3-roupas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarMoveis(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-4-moveis.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarBebidas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-5-bebidas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarProdutoLimpeza(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-6-prod-limpeza.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarItemHgiene(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-7-higiene-pessoal.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.1 Solicitar Dinheiro

    @FXML
    private Button confValorSolicitar;

    @FXML
    private TextField doacaoValorSolicitarField;

    @FXML
    void solicitarConfValor(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoValor(ActionEvent event) {

    }

    @FXML
    void botaoVoltar45() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.2 Solicitar Alimentos

    @FXML
    private Button confAlimentosSolicitar;

    @FXML
    private TextField doacaoAlimentosSolicitarField;


    @FXML
    void solicitarConfAlimentos(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoAlimentos(ActionEvent event) {

    }

    @FXML
    void botaoVoltar46() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.3 Solicitar Roupas

    @FXML
    private TextField doacaoRoupasSolicitarField;

    @FXML
    private Button confRoupasSolicitar;

    @FXML
    void solicitarConfRoupas(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoRoupas(ActionEvent event) {

    }

    @FXML
    void botaoVoltar47() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.4 Solicitar Móveis

    @FXML
    private Button confMoveisSolicitar;

    @FXML
    private TextField doacaoMoveisSolicitarField;

    @FXML
    void solicitarConfMoveis(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoMoveis(ActionEvent event) {

    }

    @FXML
    void botaoVoltar48() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.5 Solicitar Bebidas

    @FXML
    private TextField doacaoBebidasSolicitarField;

    @FXML
    private Button confBebidasSolicitar;

    @FXML
    void solicitarConfBebidas(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoBebidas(ActionEvent event) {

    }

    @FXML
    void botaoVoltar49() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.6 Solicitar Produtos de limpeza

    @FXML
    private TextField doacaoProdLimpezaSolicitarField;

    @FXML
    private Button confProdLimpezaSolicitar;


    @FXML
    void solicitarConfProdLimpeza(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoProdLimpeza(ActionEvent event) {

    }

    @FXML
    void botaoVoltar50() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }


    //Tela 22.7 Solicitar Produtos de higiene pessoal

    @FXML
    private Button confProdHigieneSolicitar;

    @FXML
    private TextField doacaoProdHigieneSolicitarField;

    @FXML
    void solicitarConfProdHigiene(ActionEvent event) {

    }

    @FXML
    void fieldSolicitarDoacaoProdHigene(ActionEvent event) {

    }

    @FXML
    void botaoVoltar51() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
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
                            "CNPJ: %s\n",
                    instituicaoLogada.getNome() != null ? instituicaoLogada.getNome() : "",
                    instituicaoLogada.getNomeUsuario() != null ? instituicaoLogada.getNomeUsuario() : "",
                    instituicaoLogada.getTelefone() != null ? instituicaoLogada.getTelefone() : "",
                    instituicaoLogada.getCnpj() != null ? instituicaoLogada.getCnpj() : ""
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


    //Tela 25 Solicitar Voluntários

    public void SolicitarNovosVoluntariosBotao(ActionEvent actionEvent) {
        instituicaoAtual.solicitarVoluntarios();
    }



    //Próximos métodos
}
*/