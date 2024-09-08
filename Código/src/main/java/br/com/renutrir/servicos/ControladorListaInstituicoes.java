
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
package br.com.renutrir.servicos;

        import br.com.renutrir.renutrir.DetalhesInstituicao;
        import br.com.renutrir.repositorio.RepositorioContas;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;
        import javafx.scene.layout.VBox;
        import br.com.renutrir.renutrir.HelloController;

        import java.net.URL;
        import java.util.ResourceBundle;

public class ControladorListaInstituicoes implements Initializable {

    HelloController hc = new HelloController();

    private RepositorioContas repositorioContas;

    @FXML
    private ListView<String> instituicaoListView;

    @FXML
    private Button voltarBotao71;

    public ControladorListaInstituicoes() {
        this.repositorioContas = new RepositorioContas();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instituicaoListView.getItems().addAll(repositorioContas.getListaDeNomesInstituicoes());

        instituicaoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                DetalhesInstituicao.showDetalhes(newValue);
            }
        });
    }

    @FXML
    public void botaoVoltar71(){
        hc.realizarTrocaDeTela("/br/com/renutrir/05-intencao-doacao.fxml", "ReNutrir - Intenção de Doação");
    }
}
