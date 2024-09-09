package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorDoacoesPendentes implements Initializable {

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
    public Button voltarBotao;

    //Tela 26
    @FXML
    void botaoVoltar59() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    @FXML
    private ComboBox <IntencaoDoacao> CBoxDPendentes;

    @FXML
    public void preencherCBoxDPendentes() {
        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        if (instituicaoLogada != null) {
            System.out.println("Checando intenções para " + instituicaoLogada.getNome());

            RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
            List<IntencaoDoacao> intencoes = repositorioIntencaoDoacao.getIntencoes();

            List<IntencaoDoacao> intencoesFiltradas = new ArrayList<>();
            for (IntencaoDoacao intencaoDoacao : intencoes) {
                if (intencaoDoacao.getInstituicao().getNomeUsuario().equals(instituicaoLogada.getNomeUsuario())) {
                    intencoesFiltradas.add(intencaoDoacao);
                }
            }

            if(CBoxDPendentes == null){
                CBoxDPendentes = new ComboBox<>();
            }

            if (!intencoesFiltradas.isEmpty()) {
                ObservableList<IntencaoDoacao> observableList = FXCollections.observableArrayList(intencoesFiltradas);
                CBoxDPendentes.setItems(observableList);
            } else {
                IntencaoDoacao intencaoDoacao = new IntencaoDoacao();
                intencaoDoacao.setDoador(null);
                CBoxDPendentes.setItems(FXCollections.observableArrayList(intencaoDoacao));
                System.out.println("Nenhuma intenção de doação pendente para a instituição " + instituicaoLogada.getNome());
            }
        } else {
            System.out.println("Nenhuma instituição logada.");
        }
    }

    @FXML
    public Button exibirIDoacaoBotao;

    private IntencaoDoacao intencaoSelecionada;
    @FXML
    public void botaoExibirIDoacao(ActionEvent actionEvent) {
        intencaoSelecionada = CBoxDPendentes.getSelectionModel().getSelectedItem();
        System.out.println(intencaoSelecionada);

        if (intencaoSelecionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/26-1-validar-intencao.fxml"));
                Parent root = loader.load();

                ControladorDoacoesPendentes controlador = loader.getController();
                //controlador.setIntencaoDoacao(intencaoSelecionada);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Validar Intenção de Doação");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setInformacoesItencaoDoacao();
        } else {
            System.out.println("Nenhuma intenção de doação selecionada.");
        }
    }

    //Tela 26-1

    @FXML
    void botaoVoltar57() {
        realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml","ReNutrir - Menu Instituição");
    }

    @FXML
    public Button doacaoRecebidaBotaoF;
    public Label nomeDoadorLabelF = new Label();
    public Label dataHoraIntencaoLabelF = new Label();
    public Label itemDoadoLabelF = new Label();
    public Label tipoDoacaoLabelF = new Label();

    public void setInformacoesItencaoDoacao() {
        IntencaoDoacao intencaoDoacao = intencaoSelecionada;
        System.out.println(intencaoDoacao);
        String UM;
        switch (intencaoDoacao.getTipoItem()) {
            case "Alimentos": UM = "Kg"; break;
            case "Bebidas": UM = "L"; break;
            default: UM = "x"; break;
        }
        String dataHoraFormatada = this.intencaoSelecionada.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        itemDoadoLabelF.setText(intencaoDoacao.getQuantidade() + UM + " " + intencaoDoacao.getItem());
        tipoDoacaoLabelF.setText("Tipo: " + intencaoDoacao.getTipoItem());
        nomeDoadorLabelF.setText("Doador: " + intencaoDoacao.getDoador().getNome());
        dataHoraIntencaoLabelF.setText(dataHoraFormatada);
    }

    @FXML
    public void botaoDoacaoRecebidaF(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencherCBoxDPendentes();
    }
}