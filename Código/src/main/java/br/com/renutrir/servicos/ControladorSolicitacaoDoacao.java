package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorSolicitacaoDoacao {

    public TextField doacaoProdLimpezaSolicitarField;
    public Button confProdLimpezaSolicitar;
    public Button confProdHigieneSolicitar;
    public TextField doacaoProdHigieneSolicitarField;
    public Button confBebidasSolicitar;
    public TextField doacaoBebidasSolicitarField;
    public Button confMoveisSolicitar;
    public TextField doacaoMoveisSolicitarField;
    public Button confRoupasSolicitar;
    public TextField doacaoRoupasSolicitarField;
    public Button confAlimentosSolicitar;
    public TextField doacaoAlimentosSolicitarField;
    public TextField doacaoValorSolicitarField;
    public Button confValorSolicitar;
    @FXML
    public Button voltarBotao, bebidasSolicitar, itemHigienerSolicitar, produtoLimpezaSolicitar, roupasSolicitar, moveisSolicitar, dinheiroSolicitar, alimentosSolicitar;

    @FXML
    public void solicitarDinheiro(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-1-valores.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    public void solicitarAlimentos(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-2-alimentos.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarRoupas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-3-roupas.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarMoveis(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-4-moveis.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarBebidas(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-5-bebidas.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarProdutoLimpeza(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-6-prod-limpeza.fxml", "ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarItemHgiene(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/22-7-higiene-pessoal.fxml", "ReNutrir - Solicitar Doações");
    }

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

    public void botaoVoltar22(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Menu Instituição");
    }

    @FXML
    void botaoVoltar45() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar46() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar47() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar48() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar49() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar50() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar51() {
        realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    public void fieldSolicitarDoacaoProdHigene(ActionEvent actionEvent) {
    }

    public void solicitarConfProdHigiene(ActionEvent actionEvent) {
    }

    public void solicitarConfProdLimpeza(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoProdLimpeza(ActionEvent actionEvent) {
    }

    public void solicitarConfBebidas(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoBebidas(ActionEvent actionEvent) {
    }

    public void solicitarConfMoveis(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoMoveis(ActionEvent actionEvent) {
    }

    public void solicitarConfRoupas(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoRoupas(ActionEvent actionEvent) {
    }

    public void solicitarConfAlimentos(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoAlimentos(ActionEvent actionEvent) {
    }

    public void solicitarConfValor(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoValor(ActionEvent actionEvent) {
    }
}
