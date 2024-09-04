package br.com.renutrir.servicos;

import  br.com.renutrir.renutrir.HelloController;

public class ControladorLogin{

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginSenhaField;

    @FXML
    private Button loginEntrarBotao;

    HelloController hc = new HelloController;

    hc.realizarTrocaDeTela(String fxmlArquivo, String titulo);
    hc.trocarTela(Stage stage, String fxmlFile, String title);

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

    @FXML
    public void botaoLogin() {
        System.out.println("Login");
        Stage stage = (Stage) loginBotao.getScene().getWindow();
        trocarTela(stage, "/br/com/renutrir/03-login.fxml", "ReNutrir - Login");
    }

    public void validarLogin(){

    }

}
