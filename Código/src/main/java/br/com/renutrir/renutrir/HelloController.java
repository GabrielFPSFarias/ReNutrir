package br.com.renutrir.renutrir;

import br.com.renutrir.model.Endereco;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.model.Doador;
import br.com.renutrir.servicos.*;
import br.com.renutrir.main.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Optional;

public class HelloController {

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
        System.out.println("Clicou para voltar: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, fxmlArquivo, titulo);
    }
//Botões Voltar
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

    private ControladorCadastro controladorCadastro;

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
    public void botaoLoginEntrar() {
        String emailOuUsuario = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        if (checarInstituicao.isSelected()) {
            //Autenticar como Instituição
            Instituicao instituicao = buscarInstituicaoNoArquivo(emailOuUsuario, senha, "/src/dados/arquivo1.txt");
            if (instituicao != null) {
                showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Instituição!");
                realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para instituição.");
            }
        } else {
            //Autenticar como Doador
            Doador doador = buscarDoadorNoArquivo(emailOuUsuario, senha, "/src/dados/arquivo.txt");
            if (doador != null) {
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
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Email: " + emailOuUsuario) || linha.contains("Nome de Usuário: " + emailOuUsuario)) {
                    instituicao = new Instituicao();
                    if (emailOuUsuario.contains("@")) {
                        instituicao.setEmail(emailOuUsuario);
                    } else {
                        instituicao.setNomeUsuario(emailOuUsuario);
                    }
                    instituicao.setSenha(senha);
                } else if (linha.contains("Senha: " + senha) && instituicao != null) {
                    return instituicao;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Email: " + emailOuUsuario) || linha.contains("Nome de Usuário: " + emailOuUsuario)) {
                    doador = new Doador();
                    if (emailOuUsuario.contains("@")) {
                        doador.setEmail(emailOuUsuario);
                    } else {
                        doador.setNomeUsuario(emailOuUsuario);
                    }
                    doador.setSenha(senha);
                } else if (linha.contains("Senha: " + senha) && doador != null) {
                    return doador;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        realizarTrocaDeTela("/br/com/renutrir/17-perfil-doador.fxml", "ReNutrir - Perfil");
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
        realizarTrocaDeTela("/br/com/renutrir/23-perfil-instituicao.fxml", "ReNutrir - Perfil da Instituição");
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

    // Interface e FXML

    @FXML
    private Button alimentosDoar;

    @FXML
    private Button mobiliasDoar;

    @FXML
    private Button outrosDoar;

    @FXML
    private Button pixDoar;

    @FXML
    private Button roupasDoar;

    @FXML
    private Button cartaoDoar;

    @FXML
    public void doarPix(ActionEvent event) {

    }

    @FXML
    public void doarCartao(ActionEvent event) {

    }

    @FXML
    public void doarAlimentos(ActionEvent event) {

    }

    @FXML
    public void doarRoupas(ActionEvent event) {

    }

    @FXML
    public void doarMobilias(ActionEvent event) {

    }

    @FXML
    public void doarOutros(ActionEvent event) {

    }


    //Próximos métodos
}
