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

    @FXML
    public void botaoVoltar() {
        realizarTrocaDeTela("01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoVoltar1() {
        realizarTrocaDeTela("02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar2() {
        realizarTrocaDeTela("02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar3() {
        realizarTrocaDeTela("01-tela-inicial.fxml", "ReNutrir");
    }

    @FXML
    public void botaoVoltar4() {
        realizarTrocaDeTela("03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoVoltar5() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar6() {
        realizarTrocaDeTela("05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    @FXML
    public void botaoVoltar7() {
        realizarTrocaDeTela("06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
    }

    @FXML
    public void botaoVoltar8() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar9() {
        realizarTrocaDeTela("08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar10() {
        realizarTrocaDeTela("08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar11() {
        realizarTrocaDeTela("10-transportes-doacoes.fxml", "ReNutrir - Transportes");
    }

    @FXML
    public void botaoVoltar12() {
        realizarTrocaDeTela("11-transportes-solicitados.fxml", "ReNutrir - Transportes Solicitados");
    }

    @FXML
    public void botaoVoltar13() {
        realizarTrocaDeTela("08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar14() {
        realizarTrocaDeTela("08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    @FXML
    public void botaoVoltar15() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar16() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar17() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar18() {
        realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Doador");
    }

    @FXML
    public void botaoVoltar19() {
        realizarTrocaDeTela("03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoVoltar20() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar21() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar22() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar23() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar24() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoVoltar25() {
        realizarTrocaDeTela("24-voluntarios.fxml", "ReNutrir - Voluntários");
    }

    @FXML
    public void botaoVoltar26() {
        realizarTrocaDeTela("19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }

    @FXML
    public void botaoLogin() {
        System.out.println("Login");
        Stage stage = (Stage) loginBotao.getScene().getWindow();
        trocarTela(stage, "03-login.fxml", "ReNutrir - Login");
    }

    @FXML
    public void botaoCadastro() {
        System.out.println("Cadastro");
        Stage stage = (Stage) cadastroBotao.getScene().getWindow();
        trocarTela(stage, "02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoSouInstituicao() {
        System.out.println("Cadastro instituição");
        Stage stage = (Stage) instBotao.getScene().getWindow();
        trocarTela(stage, "02-cadastro-instituicao.fxml", "ReNutrir - Cadastro Instituição");
    }

    @FXML
    public void botaoSouDoador() {
        System.out.println("Cadastro doador");
        Stage stage = (Stage) doadorBotao.getScene().getWindow();
        trocarTela(stage, "02-cadastro-doador.fxml", "ReNutrir - Cadastro Doador");
    }

    private RepositorioContas repositorioContas = new RepositorioContas();
    private Doador doador = new Doador();

    @FXML
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

        if (repositorioContas.buscarUsuarioPorEmail(email).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse e-mail.");
            return;
        }

        if (repositorioContas.buscarUsuarioPorNomeUsuario(nomeUsuario).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse nome de usuário.");
            return;
        }

        if (repositorioContas.buscarUsuarioPorCpf(cpf).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse CNPJ.");
            return;
        }

        try {
            doador.setCpf(cpf);
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O CNPJ digitado é inválido ou não existe.");
            System.out.println("CNPJ inválido.");
            return;
        }

        Doador doador = new Doador();
        doador.setNome(nome);
        doador.setNomeUsuario(nomeUsuario);
        doador.setEmail(email);
        doador.setSenha(senha);
        doador.setTelefone(telefone);
        doador.setCpf(cpf);
        doador.setEndereco(new Endereco(endereco, bairro, numero, municipio, uf, comp, ref));

        if (repositorioContas.adicionarUsuario(doador)) {
            salvarDadosEmArquivo(doador);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro de doador confirmado!");
            alert.showAndWait();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Não foi possível realizar o cadastro.");
        }
    }

    private void salvarDadosEmArquivo(Doador doador) {
        String caminhoArquivo = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo.txt";
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
        String caminhoArquivo = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo1.txt";
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

        if (repositorioContas.buscarUsuarioPorEmail(email).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse e-mail.");
            return;
        }

        if (repositorioContas.buscarUsuarioPorNomeUsuario(nomeUsuario).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Já existe um usuário cadastrado com esse nome de usuário.");
            return;
        }

        if (repositorioContas.buscarUsuarioPorCpf(cnpj).isPresent()) {
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

    private String caminhoArquivo = "C:/Users/Daniel Dionísio/IdeaProjects/D/ReNutrir/src/dados/arquivo.txt";

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

    public void botaoLoginEntrar() {
        String login = loginEmailField.getText();
        String senha = loginSenhaField.getText();

        boolean autenticado = autenticarUsuario(login, senha);

        if (autenticado) {
            //Login bem-sucedido
            System.out.println("Login bem-sucedido!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login bem-sucedido!");
            alert.showAndWait();
            realizarTrocaDeTela("04-menu-doador.fxml", "ReNutrir - Menu Doador");
        } else {
            // Falha no login
            System.out.println("Falha no login: email ou senha incorretos.");
            showAlert(Alert.AlertType.ERROR, "Erro de Login", "Email/nome de usuário ou senha inválidos.");
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

    public void botaoItencaoDoacao(ActionEvent actionEvent) {
        realizarTrocaDeTela("05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }

    public void botaoVoluntario(ActionEvent actionEvent) {
        realizarTrocaDeTela("08-menu-voluntario.fxml", "ReNutrir - Voluntario");
    }

    public void botaoCertificados(ActionEvent actionEvent) {
        realizarTrocaDeTela("15-pontuacoes-certificados.fxml", "ReNutrir - Certificados");
    }

    public void botaoEventos(ActionEvent actionEvent) {
        realizarTrocaDeTela("16-proximos-eventos.fxml", "ReNutrir - Eventos");
    }

    public void botaoPerfil(ActionEvent actionEvent) {
        realizarTrocaDeTela("17-perfil-doador.fxml", "ReNutrir - Perfil");
    }

    public void botaoDoacoesPendentesDoador(ActionEvent actionEvent) {
        realizarTrocaDeTela("18-doacoes-pendentes-doador.fxml", "ReNutrir - Doações Pendentes");
    }


    //Próximos métodos
}
