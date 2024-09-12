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
        import br.com.renutrir.repositorio.RepositorioInstituicao;
        import br.com.renutrir.repositorio.RepositorioVoluntario;
        import br.com.renutrir.sessao.SessaoDoador;
        import br.com.renutrir.sessao.SessaoInstituicao;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import br.com.renutrir.renutrir.HelloController;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.net.URL;
        import java.util.List;
        import java.util.Optional;
        import java.util.ResourceBundle;
        import javafx.util.Callback;

public class ControladorListaInstituicoes implements Initializable {

    private RepositorioInstituicao repositorioInstituicoes;

    public ControladorListaInstituicoes(){

        this.repositorioInstituicoes = new RepositorioInstituicao();

    }


    HelloController hc = new HelloController();

/*
    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    private Button instituicoesDoacaoBotao;

    @FXML
    private Label instituicaoNomeLabel;

    @FXML
    private ListView<Instituicao> instituicoesListView;

    private RepositorioInstituicao repositorioInstituicao;

    @FXML
    private Label instituicaoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (instituicoesListView != null) {
            carregarInstituicoes();
        } else {
            System.err.println("instituicoesListView é null!");
        }

        if (RepositorioIntencaoDoacao.getInstituicaoSelecionada() == null) {
            instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação.");
        }
    }


    private void carregarInstituicoes() {
        if (instituicoesListView != null) {
            List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();
            ObservableList<Instituicao> instituicoesObservableList = FXCollections.observableArrayList(instituicoes);

            instituicoesListView.setItems(instituicoesObservableList);
            instituicoesListView.setCellFactory(lv -> new ListCell<Instituicao>() {
                @Override
                protected void updateItem(Instituicao item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getNome());
                }
            });

            instituicoesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    RepositorioIntencaoDoacao.setInstituicaoSelecionada(newVal);
                    atualizarLabelInstituicao();
                }
            });
        } else {
            System.err.println("instituicoesListView é null!");
        }
    }

    private void atualizarLabelInstituicao() {
        Instituicao instituicao = RepositorioIntencaoDoacao.getInstituicaoSelecionada();
        if (instituicao != null) {
            this.instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação. Doação para a " + instituicao.getNome());
        } else {
            this.instituicaoNomeLabel.setText("Seja bem-vindo ao ReNutrir. Realize aqui a sua intenção de doação. Escolha abaixo a instituição beneficiária.");
        }
    }

    @FXML
    public void botaoDoarAgora(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-confirmar-doacao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) doarAgoraBotao.getScene().getWindow();
            stage.setTitle("ReNutrir - Confirmar Doação");
            stage.setScene(new Scene(root));

            ControladorIntencaoDeDoacao controlador = loader.getController();
            Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

            if (instituicaoSelecionada != null) {
                controlador.instituicaoLabel.setText("Você está doando para a: " + instituicaoSelecionada.getNome());
            } else {
                controlador.instituicaoLabel.setText("Nenhuma instituição selecionada.");
            }
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }

    @FXML
    public void botaoVoltar5() {
        realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Menu Doador");
    }

    @FXML
    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {

    }

    @FXML
    public void clearListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().clearSelection();
    }

    @FXML
    public void selectAllListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().selectAll();
    }

    @FXML
    public void selectFirstListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void selectLastListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().selectLast();
    }

    @FXML
    public void selectNextListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().selectNext();
    }

    @FXML
    public void selectPrevListView(ActionEvent event) {
        instituicoesListView.getSelectionModel().selectPrevious();
    }

    public void setControladorTelas(ControladorTelas controladorTelas) {
        this.controladorTelas = controladorTelas;
    }

    //Tela 05 - Intenção de doação


    //Label
    @FXML
    private Label valorDoacaoExibirDeb; //label da tela 07-2-2

    @FXML
    private Label valorDoacaoCreExibir; //label da tela 07-2-1
*/

    @FXML
    private ListView<Instituicao> instituicaoListView;

    private ObservableList<Instituicao> obsInstituicoes;

    @FXML
    private Button proximoBotao1;

    @FXML
    private Button voltarBotao71;

    @FXML
    private Label instituicaoDetalhesLabel;

    @FXML
    private ComboBox<String> cboxFuncaoVoluntario;

    @FXML
    private TextField buscaTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Preencher o ListView com Instituicoes
        //instituicoesListView.setItems(FXCollections.observableArrayList(nomesInstituicoes));
        //instituicoesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       // instituicoesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->

        carregarListView();

        if (instituicaoListView != null) {
            carregarListView();
        } else {
            System.err.println("instituicoesListView é null!");
        }

        configurarComboBox();
    }

    public void carregarListView(){


        if (instituicaoListView != null){

        obsInstituicoes = FXCollections.observableArrayList(repositorioInstituicoes.listarInstituicoes());

        instituicaoListView.setItems(obsInstituicoes);

        instituicaoListView.setCellFactory(new Callback<ListView<Instituicao>, ListCell<Instituicao>>() {
            @Override
            public ListCell<Instituicao> call(ListView<Instituicao> param) {
                return new ListCell<Instituicao>() {
                    @Override
                    protected void updateItem(Instituicao instituicao, boolean empty) {
                        super.updateItem(instituicao, empty);
                        if (instituicao != null) {
                            setText(instituicao.getNome());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        instituicaoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String detalhesInst = getDetalhes(newValue.getNomeUsuario());
                instituicaoDetalhesLabel.setText(detalhesInst);
            }
        });
         } else System.err.println("ListView é nulo");
    }

    private void carregarFuncoesVoluntarios() {
        ObservableList<String> funcoesVoluntarios = FXCollections.observableArrayList();

        RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();
        List<String> funcaoVoluntario = repositorioVoluntario.carregarFuncoesVoluntario();

        funcoesVoluntarios.addAll(funcaoVoluntario);

        if (funcoesVoluntarios.isEmpty()) {
            cboxFuncaoVoluntario.setItems(FXCollections.observableArrayList("Nenhuma função disponível"));
        } else {
            cboxFuncaoVoluntario.setItems(funcoesVoluntarios);
        }
        cboxFuncaoVoluntario.setValue(cboxFuncaoVoluntario.getItems().get(0));
    }

    private void configurarComboBox() {
        if (cboxFuncaoVoluntario == null) {
            System.out.println("cboxFuncaoVoluntario está null");
            return;
        }
        carregarFuncoesVoluntarios();
    }



    public void realizarTrocaDeTela(String fxmlArquivo, String titulo, Button voltarBotao) {
        System.out.println("Clicou: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
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
    public void proximoBotao1(){

        realizarTrocaDeTela("/br/com/renutrir/27-confirmacao-voluntario.fxml", "ReNutrir - Confirmação do voluntário", proximoBotao1);

    }

    @FXML
    public void voltarBotao71(){

        realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Menu do voluntário", voltarBotao71);
    }

    public String getDetalhes (String nomeUsuario) {

        Optional<Instituicao> instituicaoOptional = repositorioInstituicoes.buscarInstituicaoPorNomeUsuario(nomeUsuario);

        if (instituicaoOptional.isPresent()) {

            Instituicao instituicao = instituicaoOptional.get();

            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Nome: ").append(instituicao.getNome()).append("\n\n");
            detalhes.append(instituicao.getEndereco()).append("\n\n");
            detalhes.append("Telefone: ").append(instituicao.getTelefone()).append("\n\n");
            detalhes.append("Email: ").append(instituicao.getEmail()).append("\n\n");
            detalhes.append("Horário de funcionamento: ").append(instituicao.getHorarioFuncionamento());

            return detalhes.toString();
        } else {
            return "Detalhes da instituição não encontrados.";
        }
    }

    @FXML
    private void buscarInstituicoesPorEndereco(){

        buscaTextField.textProperty().addListener((observable, oldValue, newValue) -> filtrarInstituicoes(newValue));

    }

    private void filtrarInstituicoes(String busca) {
        if (busca == null || busca.isEmpty()) {
            // Se o campo de busca estiver vazio, mostra a lista completa
            instituicaoListView.setItems(FXCollections.observableArrayList(repositorioInstituicoes.listarInstituicoes()));
        } else {
            // Filtra a lista de instituições pelo nome
            ObservableList<Instituicao> listaFiltrada = FXCollections.observableArrayList();
            for (Instituicao instituicao : repositorioInstituicoes.listarInstituicoes()) {
                if (instituicao.getEndereco().getEnderecoCompleto().toLowerCase().contains(busca.toLowerCase())) {
                    listaFiltrada.add(instituicao);
                }
            }
            instituicaoListView.setItems(listaFiltrada);
        }
    }
}
