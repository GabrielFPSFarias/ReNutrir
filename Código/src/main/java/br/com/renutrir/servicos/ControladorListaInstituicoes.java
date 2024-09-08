package br.com.renutrir.servicos;
/*package br.com.renutrir.servicos;

import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.DetalhesInstituicao;
import br.com.renutrir.repositorio.RepositorioContas;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorListaInstituicoes implements Initializable {

        private RepositorioContas repositorioContas;

        public ControladorListaInstituicoes() {
            this.repositorioContas = new RepositorioContas();
        }

        public VBox carregarTelaInstituicoes() {

            ListView<String> listView = new ListView<>();
            listView.getItems().addAll(repositorioContas.getListaDeNomesInstituicoes());

            listView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue) -> {
                if (newValue != null) {

                    DetalhesInstituicao.showDetalhes(newValue);
                }
            });

            VBox layout = new VBox(listView);
            return layout;
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            carregarTelaInstituicoes();
    }
}
*/

        import br.com.renutrir.model.Doador;
        import br.com.renutrir.model.Instituicao;
        import br.com.renutrir.renutrir.DetalhesInstituicao;
        import br.com.renutrir.repositorio.RepositorioContas;
        import br.com.renutrir.sessao.SessaoDoador;
        import br.com.renutrir.sessao.SessaoInstituicao;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.layout.VBox;
        import br.com.renutrir.renutrir.HelloController;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

        import javafx.scene.control.ListCell;
        import javafx.util.Callback;

public class ControladorListaInstituicoes implements Initializable {

    HelloController hc = new HelloController();

    private RepositorioContas repositorioContas;

    private  DetalhesInstituicao detalhesInstituicao;

    @FXML
    private ListView<Instituicao> instituicaoListView;

    @FXML
    private Button voltarBotao71;

    @FXML
    private Label instituicaoDetalhesLabel;


    public ControladorListaInstituicoes() {
        this.repositorioContas = new RepositorioContas();
        this.detalhesInstituicao = new DetalhesInstituicao();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Preencher o ListView com Instituicoes
        instituicaoListView.getItems().addAll(repositorioContas.getListaNomeInstituicoes());

        // Definir uma CellFactory para exibir apenas o nome da instituição
        instituicaoListView.setCellFactory(new Callback<ListView<Instituicao>, ListCell<Instituicao>>() {
            @Override
            public ListCell<Instituicao> call(ListView<Instituicao> param) {
                return new ListCell<Instituicao>() {
                    @Override
                    protected void updateItem(Instituicao instituicao, boolean empty) {
                        super.updateItem(instituicao, empty);
                        if (instituicao != null) {
                            setText(instituicao.getNome()); // Exibir apenas o nome
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        instituicaoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String detalhesInst = detalhesInstituicao.getDetalhes(newValue.getNomeUsuario());
                instituicaoDetalhesLabel.setText(detalhesInst);
            }
        });
    }

    public void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        System.out.println("Clicou: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao71.getScene().getWindow();
        hc.trocarTela(stage, fxmlArquivo, titulo);

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
    public void voltarBotao71(){

        realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }
}
