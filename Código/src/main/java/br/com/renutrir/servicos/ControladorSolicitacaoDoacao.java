package br.com.renutrir.servicos;

import br.com.renutrir.renutrir.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorSolicitacaoDoacao {
    HelloController helloController = new HelloController();

    //Botão voltar
    @FXML
    public Button voltarBotao;

    @FXML
    public void botaoVoltar22() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
    }
    @FXML
    void botaoVoltar45() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar46() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar47() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar48() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar49() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar50() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void botaoVoltar51() {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-solicitar-doacoes.fxml","ReNutrir - Solicitar Doações");
    }

    //Botões Solicitações
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

    //Métodos Solicitações
    @FXML
    void solicitarDinheiro(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-1-valores.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarAlimentos(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-2-alimentos.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarRoupas(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-3-roupas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarMoveis(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-4-moveis.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarBebidas(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-5-bebidas.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarProdutoLimpeza(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-6-prod-limpeza.fxml","ReNutrir - Solicitar Doações");
    }

    @FXML
    void solicitarItemHgiene(ActionEvent event) {
        helloController.realizarTrocaDeTela("/br/com/renutrir/22-7-higiene-pessoal.fxml","ReNutrir - Solicitar Doações");
    }
    //Tela 22-1 Solicitar valores

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

    //Tela 22-2 Solicitar Alimentos

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
    //Tela 22-3 Solicitar Roupas

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
    //Tela 22-4 Solicitar Móveis

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


    //Tela 22-6 Solicitar Produtos de limpeza

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

    //Tela 22-7 Solicitar Produtos de higiene pessoal

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
}