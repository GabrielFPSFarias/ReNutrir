package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.SolicitacaoDoacao;
import br.com.renutrir.repositorio.RepositorioSolicitacaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorSolicitacaoDoacao {

    @FXML
    public TextField doacaoAlimentosItemField;
    public TextField doacaoRoupasItemField;
    public TextField doacaoMoveisItemField;
    public TextField doacaoBebidasItemField;
    public TextField doacaoProdLimpezaItemField;
    public TextField doacaoProdHigieneItemField;

    public ControladorSolicitacaoDoacao() {
    }

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

    @FXML
    public void solicitarConfAlimentos(ActionEvent actionEvent) {
        salvarSolicitacao("Alimentos", doacaoAlimentosItemField, doacaoAlimentosSolicitarField);
    }

    @FXML
    public void solicitarConfRoupas(ActionEvent actionEvent) {
        salvarSolicitacao("Roupas", doacaoRoupasItemField, doacaoRoupasSolicitarField);
    }

    @FXML
    public void solicitarConfMoveis(ActionEvent actionEvent) {
        salvarSolicitacao("Moveis", doacaoMoveisItemField, doacaoMoveisSolicitarField);
    }

    @FXML
    public void solicitarConfBebidas(ActionEvent actionEvent) {
        salvarSolicitacao("Bebidas", doacaoBebidasItemField, doacaoBebidasSolicitarField);
    }

    @FXML
    public void solicitarConfProdLimpeza(ActionEvent actionEvent) {
        salvarSolicitacao("Produto de Limpeza", doacaoProdLimpezaItemField, doacaoProdLimpezaSolicitarField);
    }

    @FXML
    public void solicitarConfProdHigiene(ActionEvent actionEvent) {
        salvarSolicitacao("Produto de Higiene", doacaoProdHigieneItemField, doacaoProdHigieneSolicitarField);
    }

    private void salvarSolicitacao(String tipoItem, TextField itemField, TextField quantidadeField) {
        String item = itemField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        Instituicao instituicao = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        if (instituicao != null) {
            SolicitacaoDoacao solicitacao = new SolicitacaoDoacao(tipoItem, item, quantidade, instituicao.getNome(), instituicao.getNomeUsuario());
            RepositorioSolicitacaoDoacao repositorio = new RepositorioSolicitacaoDoacao();
            repositorio.salvarSolicitacao(solicitacao);
            System.out.println("Solicitação salva: " + solicitacao);
            showAlert(Alert.AlertType.INFORMATION, "Concluído!", "Solicitação de doação criada para os doadores");
            realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    public void fieldSolicitarDoacaoProdLimpeza(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoBebidas(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoMoveis(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoRoupas(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoAlimentos(ActionEvent actionEvent) {
    }

    public void solicitarConfValor(ActionEvent actionEvent) {
    }

    public void fieldSolicitarDoacaoValor(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoAlimentos(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoRoupas(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoMoveis(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoBebidas(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoProdLimpeza(ActionEvent actionEvent) {
    }

    public void fieldItemDoacaoProdHigene(ActionEvent actionEvent) {
    }
}
