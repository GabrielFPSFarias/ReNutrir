package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Endereco;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.repositorio.RepositorioDoador;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorCadastro extends HelloController {

    private HelloController helloController;

    public ControladorCadastro() {
    }

    // Variáveis do Cadastro Doador
    @FXML private TextField fieldNome;
    @FXML private TextField fieldUserNome;
    @FXML private TextField fieldCep;
    @FXML private TextField fieldEndereco;
    @FXML private TextField fieldBairro;
    @FXML private TextField fieldNumero;
    @FXML private TextField fieldCidade;
    @FXML private TextField fieldUf;
    @FXML private TextField fieldComp;
    @FXML private TextField fieldRef;
    @FXML private TextField fieldCpf;
    @FXML private TextField fieldTelefone;
    @FXML private TextField fieldEmail;
    @FXML private PasswordField fieldSenha;
    @FXML private PasswordField fieldConfSenha;

    // Variáveis do Cadastro Instituição
    @FXML private TextField fieldEmailIns;
    @FXML private TextField fieldCnpjIns;
    @FXML private TextField fieldNomeIns;
    @FXML private TextField fieldInsCep;
    @FXML private TextField fieldUserNomeIns;
    @FXML private PasswordField fieldSenhaIns;
    @FXML private PasswordField fieldConfSenhaIns;
    @FXML private TextField fieldTelefoneIns;
    @FXML private TextField fieldEnderecoIns;
    @FXML private TextField fieldRefIns;
    @FXML private TextField fieldBairroIns;
    @FXML private TextField fieldCidadeIns;
    @FXML private TextField fieldNumeroIns;
    @FXML private TextField fieldCompIns;
    @FXML private TextField fieldUfIns;
    @FXML private TextField chavePixInsText;
    @FXML private TextField razaoSocialInsText;
    @FXML private TextField horarioInicialInsText;
    @FXML private TextField horarioFinalInsText;
    @FXML private TextArea descricaoInstituicaoTextArea;

    // Botões
    @FXML private Button confCad;
    @FXML private Button confCadIns;
    @FXML private Button voltarBotao;

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

    // Métodos de Cadastro Doador
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

            if (nomeUsuario == null || nomeUsuario.length() < 6) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O nome de usuário deve ter pelo menos 6 ou mais caracteres.");
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
        String chavePix = chavePixInsText.getText();
        String razaoSocial = razaoSocialInsText.getText();
        String horarioInicial = horarioInicialInsText.getText();
        String horarioFinal = horarioFinalInsText.getText();
        String descricaoInstituicao = descricaoInstituicaoTextArea.getText();

        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();

        if (!verificarCamposInstituicaoValidos(email, nomeUsuario, cnpj) ||
                nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty() ||
                bairro.isEmpty() || numero.isEmpty() || municipio.isEmpty() ||
                uf.isEmpty() || chavePix.isEmpty() || razaoSocial.isEmpty() ||
                horarioInicial.isEmpty() || horarioFinal.isEmpty() || descricaoInstituicao.isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "Todos os campos são obrigatórios.");
            return;
        }

        if (repositorioInstituicao.buscarInstituicaoPorCnpj(cnpj).isPresent()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Cadastro", "CNPJ já existente.");
            return;
        }

        if (!email.contains("@")) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O e-mail deve conter um '@'.");
            return;
        }

        if (nomeUsuario.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O nome de usuário deve ter pelo menos 6 ou mais caracteres.");
            return;
        }

        if (senha.length() < 4) {
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
            return;
        }

        instituicao.setEndereco(new Endereco(endereco, bairro, numero, municipio, uf, comp, ref));

        instituicao.setChavePix(chavePix);
        instituicao.setRazaoSocial(razaoSocial);
        instituicao.setHorarioInicial(horarioInicial);
        instituicao.setHorarioFinal(horarioFinal);
        instituicao.setDescricao(descricaoInstituicao);

        repositorioInstituicao.adicionarInstituicao(instituicao);

        showAlert(Alert.AlertType.INFORMATION, "Cadastro", "Cadastro da instituição confirmado!");
    }


    public void showAlert(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void botaoVoltar1() {
        System.out.println("Pré-cadastro");
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }

    @FXML
    public void botaoVoltar2() {
        System.out.println("Pré-cadastro");
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/02-pre-cadastro.fxml", "ReNutrir - Cadastro");
    }
}