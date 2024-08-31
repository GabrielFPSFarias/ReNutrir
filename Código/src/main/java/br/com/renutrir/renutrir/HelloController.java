package br.com.renutrir.renutrir;

import br.com.renutrir.model.Endereco;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.SolicitacaoDoacao;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.model.Doador;
import br.com.renutrir.servicos.*;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
    public Button queroVoluntarioBotao;
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

    private void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
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
        realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
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

    private RepositorioContas repositorioContas = new RepositorioContas();
    private Doador doador = new Doador();

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

        if (!verificarCamposDoadorValidos(email, nomeUsuario, cpf)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Os campos não podem estar vazios.");
        } else if (verificarDoadorExistente(email, cpf, caminhoArquivo)) {
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

            if (repositorioContas.buscarDoadorPorNomeUsuario(nomeUsuario).isPresent() ||
                    repositorioContas.buscarDoadorPorNomeUsuario(nomeUsuario).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma conta cadastrada com esse e-mail.");
                return;
            }

            if (repositorioContas.buscarDoadorPorEmail(email).isPresent() ||
                    repositorioContas.buscarDoadorPorEmail(email).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma conta cadastrada com esse nome de usuário.");
                return;
            }

            if (repositorioContas.buscarDoadorPorCpf(cpf).isPresent() ||
                    repositorioContas.buscarInstituicaoPorCnpj(cpf).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma conta cadastrada com esse CPF/CNPJ.");
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

            if (repositorioContas.adicionarUsuario(doador)) {
                salvarDadosEmArquivo(doador);
                showAlert(Alert.AlertType.INFORMATION, "Cadastro", "Cadastro de doador confirmado!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Não foi possível realizar o cadastro.");
            }

        }

    }

    private void salvarDadosEmArquivo(Doador doador) {
        String caminhoArquivo = "/src/dados/arquivo.txt";
        ControladorArquivo controladorArquivo = new ControladorArquivo(caminhoArquivo);

        try {
            controladorArquivo.escrever("Nome: " + (doador.getNome() != null ? doador.getNome() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Nome de Usuário: " + (doador.getNomeUsuario() != null ? doador.getNomeUsuario() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Email: " + (doador.getEmail() != null ? doador.getEmail() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Telefone: " + (doador.getTelefone() != null ? doador.getTelefone() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("CPF: " + (doador.getCpf() != null ? doador.getCpf() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Senha: " + (doador.getSenha() != null ? doador.getSenha() : "Não informado"));
            controladorArquivo.novaLinha();

            if (doador.getEndereco() != null) {
                Endereco endereco = doador.getEndereco();
                controladorArquivo.escrever("Endereço: " +
                        (endereco.getEndereco() != null ? endereco.getEndereco() : "Não informado") + ", " +
                        (endereco.getBairro() != null ? endereco.getBairro() : "Não informado") + ", " +
                        (endereco.getNumero() != null ? endereco.getNumero() : "Não informado") + ", " +
                        (endereco.getCidade() != null ? endereco.getCidade() : "Não informado") + ", " +
                        (endereco.getUf() != null ? endereco.getUf() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.escrever("Complemento: " + (endereco.getComplemento() != null ? endereco.getComplemento() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.escrever("Referência: " + (endereco.getReferencia() != null ? endereco.getReferencia() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.novaLinha();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarDadosEmArquivoIns(Instituicao instituicao) {
        String caminhoArquivo = "/src/dados/arquivo1.txt";
        ControladorArquivo controladorArquivo = new ControladorArquivo(caminhoArquivo);

        try {
            controladorArquivo.escrever("Nome: " + (instituicao.getNome() != null ? instituicao.getNome() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Nome de Usuário: " + (instituicao.getNomeUsuario() != null ? instituicao.getNomeUsuario() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Email: " + (instituicao.getEmail() != null ? instituicao.getEmail() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Telefone: " + (instituicao.getTelefone() != null ? instituicao.getTelefone() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("CNPJ: " + (instituicao.getCnpj() != null ? instituicao.getCnpj() : "Não informado"));
            controladorArquivo.novaLinha();
            controladorArquivo.escrever("Senha: " + (instituicao.getSenha() != null ? instituicao.getSenha() : "Não informado"));
            controladorArquivo.novaLinha();

            if (instituicao.getEndereco() != null) {
                Endereco endereco = instituicao.getEndereco();
                controladorArquivo.escrever("Endereço: " +
                        (endereco.getEndereco() != null ? endereco.getEndereco() : "Não informado") + ", " +
                        (endereco.getBairro() != null ? endereco.getBairro() : "Não informado") + ", " +
                        (endereco.getNumero() != null ? endereco.getNumero() : "Não informado") + ", " +
                        (endereco.getCidade() != null ? endereco.getCidade() : "Não informado") + ", " +
                        (endereco.getUf() != null ? endereco.getUf() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.escrever("Complemento: " + (endereco.getComplemento() != null ? endereco.getComplemento() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.escrever("Referência: " + (endereco.getReferencia() != null ? endereco.getReferencia() : "Não informado"));
                controladorArquivo.novaLinha();
                controladorArquivo.novaLinha();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        if (!verificarCamposInstituicaoValidos(email, nomeUsuario, cnpj)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Os campos não podem estar vazios.");
        } else if (verificarInstituicaoExistente(email, cnpj, caminhoArquivo)) {
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

            if (repositorioContas.buscarInstituicaoPorEmail(email).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse e-mail.");
                return;
            }

            if (repositorioContas.buscarInstituicaoPorNomeUsuario(nomeUsuario).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse nome de usuário.");
                return;
            }

            if (repositorioContas.buscarInstituicaoPorCnpj(cnpj).isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe uma instituição cadastrada com esse CNPJ.");
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

            if (repositorioContas.adicionarInstituicao(instituicao)) {
                salvarDadosEmArquivoIns(instituicao);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Cadastro da instituição confirmado!");
                alert.showAndWait();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Não foi possível realizar o cadastro.");
            }

        }

    }

    private void trocarTela(Stage stage, String fxmlFile, String title) {
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


    private String caminhoArquivo = "/src/dados/arquivo.txt";

    public boolean autenticarUsuario(String login, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            Doador doadorAtual = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    doadorAtual = new Doador();
                    doadorAtual.setNome(linha.substring(6));
                } else if (linha.startsWith("Nome de Usuário: ")) {
                    if (doadorAtual != null) {
                        doadorAtual.setNomeUsuario(linha.substring(17));
                    }
                } else if (linha.startsWith("Email: ")) {
                    if (doadorAtual != null) {
                        doadorAtual.setEmail(linha.substring(7));
                    }
                } else if (linha.startsWith("CPF: ")) {
                    if (doadorAtual != null) {
                        doadorAtual.setCpf(linha.substring(5));
                    }
                } else if (linha.startsWith("Senha: ")) {
                    if (doadorAtual != null) {
                        doadorAtual.setSenha(linha.substring(7));
                    }
                }

                if (linha.isEmpty() && doadorAtual != null) {
                    if ((doadorAtual.getEmail().equals(login) || doadorAtual.getNomeUsuario().equals(login)) &&
                            doadorAtual.getSenha().equals(senha)) {
                        return true;
                    }
                    doadorAtual = null; //Reinicia para a próxima conta
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; //Se não existir o usuário correspondente
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

    @FXML
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        if (checarInstituicao.isSelected()) {
            //Logar como Instituição
            Instituicao instituicao = buscarInstituicaoNoArquivo(emailOuUsuario, senha, "/src/dados/arquivo1.txt");
            if (instituicao != null) {
                SessaoInstituicao.getInstancia().setInstituicaoLogada(instituicao); //Salva a instituição que fez login
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Instituição!");
                realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para instituição.");
            }
        } else {
            //Logar como Doador
            Doador doador = buscarDoadorNoArquivo(emailOuUsuario, senha, "/src/dados/arquivo.txt");
            if (doador != null) {
                SessaoDoador.getInstancia().setDoadorLogado(doador); //Salva o doador que fez login
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Doador!");
                realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para doador.");
            }
        }
    }

    private boolean verificarInstituicaoExistente(String emailOuUsuario, String cpf, String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Email: " + emailOuUsuario) ||
                        linha.contains("Nome de Usuário: " + emailOuUsuario) ||
                        (cpf != null && linha.contains("CPF: " + cpf))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Instituicao buscarInstituicaoNoArquivo(String emailOuUsuario, String senha, String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            Instituicao instituicao = null;
            boolean instituicaoEncontrada = false;

            String enderecoStr = null;
            String bairro = null;
            String numero = null;
            String cidade = null;
            String uf = null;
            String complemento = null;
            String referencia = null;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    if (instituicao == null) {
                        instituicao = new Instituicao();
                    }
                    instituicao.setNome(linha.substring(6).trim());
                } else if (linha.startsWith("Nome de Usuário: ") && instituicao != null) {
                    String nomeUsuario = linha.substring(17).trim();
                    if (nomeUsuario.equals(emailOuUsuario)) {
                        instituicao.setNomeUsuario(nomeUsuario);
                        instituicaoEncontrada = true;
                    }
                } else if (linha.startsWith("Email: ") && instituicao != null) {
                    String email = linha.substring(7).trim();
                    if (email.equals(emailOuUsuario)) {
                        instituicao.setEmail(email);
                        instituicaoEncontrada = true;
                    }
                } else if (linha.startsWith("Telefone: ") && instituicao != null) {
                    instituicao.setTelefone(linha.substring(10).trim());
                } else if (linha.startsWith("CNPJ: ") && instituicao != null) {
                    instituicao.setCnpj(linha.substring(6).trim());
                } else if (linha.startsWith("Endereço: ") && instituicao != null) {
                    enderecoStr = linha.substring(10).trim();
                } else if (linha.startsWith("Bairro: ") && instituicao != null) {
                    bairro = linha.substring(8).trim();
                } else if (linha.startsWith("Número: ") && instituicao != null) {
                    numero = linha.substring(8).trim();
                } else if (linha.startsWith("Cidade: ") && instituicao != null) {
                    cidade = linha.substring(8).trim();
                } else if (linha.startsWith("UF: ") && instituicao != null) {
                    uf = linha.substring(4).trim();
                } else if (linha.startsWith("Complemento: ") && instituicao != null) {
                    complemento = linha.substring(13).trim();
                } else if (linha.startsWith("Referência: ") && instituicao != null) {
                    referencia = linha.substring(12).trim();
                } else if (linha.startsWith("Senha: ") && instituicaoEncontrada && instituicao != null) {
                    String senhaLida = linha.substring(7).trim();
                    if (senhaLida.equals(senha)) {
                        Endereco endereco = new Endereco(enderecoStr, bairro, numero, cidade, uf, complemento, referencia);
                        instituicao.setEndereco(endereco);
                        instituicao.setSenha(senhaLida); // Definir a senha após a verificação
                        return instituicao;
                    } else {
                        instituicao = null; // Se a senha não estiver correta, a instituição é inválida
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar a instituição
    }

    
    private boolean verificarDoadorExistente(String emailOuUsuario, String cpf, String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Email: " + emailOuUsuario) ||
                        linha.contains("Nome de Usuário: " + emailOuUsuario) ||
                        (cpf != null && linha.contains("CPF: " + cpf))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Doador buscarDoadorNoArquivo(String emailOuUsuario, String senha, String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            Doador doador = null;
            boolean doadorEncontrado = false;

            String enderecoStr = null;
            String bairro = null;
            String numero = null;
            String cidade = null;
            String uf = null;
            String complemento = null;
            String referencia = null;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome: ")) {
                    if (doador == null) {
                        doador = new Doador();
                    }
                    doador.setNome(linha.substring(6).trim());
                } else if (linha.startsWith("Nome de Usuário: ") && doador != null) {
                    String nomeUsuario = linha.substring(17).trim();
                    if (nomeUsuario.equals(emailOuUsuario)) {
                        doador.setNomeUsuario(nomeUsuario);
                        doadorEncontrado = true;
                    }
                } else if (linha.startsWith("Email: ") && doador != null) {
                    String email = linha.substring(7).trim();
                    if (email.equals(emailOuUsuario)) {
                        doador.setEmail(email);
                        doadorEncontrado = true;
                    }
                } else if (linha.startsWith("Telefone: ") && doador != null) {
                    doador.setTelefone(linha.substring(10).trim());
                } else if (linha.startsWith("CPF: ") && doador != null) {
                    doador.setCpf(linha.substring(5).trim());
                } else if (linha.startsWith("Endereço: ") && doador != null) {
                    enderecoStr = linha.substring(10).trim();
                } else if (linha.startsWith("Bairro: ") && doador != null) {
                    bairro = linha.substring(8).trim();
                } else if (linha.startsWith("Número: ") && doador != null) {
                    numero = linha.substring(8).trim();
                } else if (linha.startsWith("Cidade: ") && doador != null) {
                    cidade = linha.substring(8).trim();
                } else if (linha.startsWith("UF: ") && doador != null) {
                    uf = linha.substring(4).trim();
                } else if (linha.startsWith("Complemento: ") && doador != null) {
                    complemento = linha.substring(13).trim();
                } else if (linha.startsWith("Referência: ") && doador != null) {
                    referencia = linha.substring(12).trim();
                } else if (linha.startsWith("Senha: ") && doadorEncontrado && doador != null) {
                    String senhaLida = linha.substring(7).trim();
                    if (senhaLida.equals(senha)) {
                        Endereco endereco = new Endereco(enderecoStr, bairro, numero, cidade, uf, complemento, referencia);
                        doador.setEndereco(endereco);
                        doador.setSenha(senhaLida); // Definir a senha após a verificação
                        return doador;
                    } else {
                        doador = null; //Se a senha não estiver correta, o doador é inválido
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //Retorna null se não encontrar o doador
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

    public void botaoCriarEvento(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/20-criar-eventos.fxml", "ReNutrir - Criar Eventos");
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
    void solicitarDinheiro(ActionEvent event) {

    }

    @FXML
    void solicitarAlimentos(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Alimentos");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Alimentos");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    @FXML
    void solicitarRoupas(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Roupas");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Roupas");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    @FXML
    void solicitarMoveis(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Móveis");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Móveis");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    @FXML
    void solicitarBebidas(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Bebidas");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Bebidas");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    @FXML
    void solicitarProdutoLimpeza(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Produtos de Limpeza");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Produtos de Limpeza");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    @FXML
    void solicitarItemHgiene(ActionEvent event) {
        Instituicao instituicao = obterInstituicaoAtual();
        int meta = solicitarMeta("Itens de Higiene");
        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(instituicao, meta, "Itens de Higiene");
        solicitacao.salvarSolicitacaoEmArquivo();
    }

    private Instituicao obterInstituicaoAtual() {
        String identificador = getInstituicaoAtual().getNomeUsuario();
        RepositorioContas repositorio = new RepositorioContas();
        return repositorio.buscarInstituicaoPorEmailOuUsuario(identificador);
    }

    private int solicitarMeta(String tipoItem) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Definir Meta de Doações");
        dialog.setHeaderText("Solicitação de " + tipoItem);
        dialog.setContentText("Por favor, insira a meta de doações:");

        Optional<String> result = dialog.showAndWait();
        return result.map(Integer::parseInt).orElse(0);
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

    }

    @FXML
    void inserirValorPixField(ActionEvent event) {

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


    //Tela 17 Perfil doador

    @FXML
    private Label exibirInfoDoadorLabel;

    public void exibirPerfilDoador() throws IOException {
        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();

        if (doadorLogado != null) {
            String perfilDoador = String.format(
                    "Nome: %s\n" +
                            "Nome de Usuário: %s\n" +
                            "Telefone: %s\n" +
                            "CPF: %s\n",
                    doadorLogado.getNome() != null ? doadorLogado.getNome() : "",
                    doadorLogado.getNomeUsuario() != null ? doadorLogado.getNomeUsuario() : "",
                    doadorLogado.getTelefone() != null ? doadorLogado.getTelefone() : "",
                    doadorLogado.getCpf() != null ? doadorLogado.getCpf() : ""
            );

            System.out.println("Label: " + exibirInfoDoadorLabel);
            System.out.println("Perfil: " + perfilDoador);

            exibirInfoDoadorLabel.setText(perfilDoador);
        } else {
            exibirInfoDoadorLabel.setText("Nenhum doador logado.");
        }
    }


    //Tela 22.1 Solicitar PIX

    @FXML
    private Button confPixSolicitar;

    @FXML
    void botaoVoltar28(ActionEvent event) {

    }

    @FXML
    void solicitarConfPix(ActionEvent event) {

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

    //Próximos métodos
}
