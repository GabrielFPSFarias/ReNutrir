package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioVoluntario;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import br.com.renutrir.renutrir.HelloController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.util.Callback;

public class ControladorEscolhaInstituicaoFuncaoVoluntario implements Initializable {

    private RepositorioInstituicao repositorioInstituicoes;

    public ControladorEscolhaInstituicaoFuncaoVoluntario(){

        this.repositorioInstituicoes = new RepositorioInstituicao();

    }

    HelloController hc = new HelloController();

    private Instituicao instituicaoSelecionada;
    private String funcaoVoluntarioSelecionada;

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
    private TextField buscaTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarListView();

        configurarListViewFuncoes();

        instituicaoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                instituicaoSelecionada = newValue;
                String detalhesInst = getDetalhes(newValue.getNomeUsuario());
                instituicaoDetalhesLabel.setText(detalhesInst);
            } else {
                System.out.println("Nenhuma instituição selecionada");
            }
        });


        funcaoVoluntarioListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                funcaoVoluntarioSelecionada = newValue;
            } else {
                System.out.println("Nenhuma função de voluntário selecionada");
            }
        });

        buscaTextField.textProperty().addListener((observable, oldValue, newValue) -> filtrarInstituicoes(newValue));
    }

    public void salvarSelecao(){

        instituicaoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                instituicaoSelecionada = newValue;

            } else {
                System.out.println("Nenhuma instituição selecionada");
            }
        });


        funcaoVoluntarioListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                funcaoVoluntarioSelecionada = newValue;
            } else {
                funcaoVoluntarioSelecionada = "Transportador voluntário";
            }
        });
    }

    @FXML
    private ListView<String> funcaoVoluntarioListView;

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

        salvarSelecao();
    }
    @FXML
    private void carregarFuncoesVoluntarios() {
        ObservableList<String> funcoesVoluntarios = FXCollections.observableArrayList();

        RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();
        List<String> funcaoVoluntario = repositorioVoluntario.carregarFuncoesVoluntario();

        funcoesVoluntarios.addAll(funcaoVoluntario);

        if (funcoesVoluntarios.isEmpty()) {
            funcaoVoluntarioListView.setItems(FXCollections.observableArrayList("Nenhuma função disponível"));
        } else {
            funcaoVoluntarioListView.setItems(funcoesVoluntarios);
        }
    }

    private void configurarListViewFuncoes() {
        if (funcaoVoluntarioListView == null) {
            System.out.println("listFuncaoVoluntario está null");
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

        salvarSelecao();

        if (instituicaoSelecionada != null && funcaoVoluntarioSelecionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/renutrir/28-confirmacao-voluntario.fxml"));
                Parent root = loader.load();

                ControladorConfirmacaoVoluntario controladorConfirmacao = loader.getController();

                controladorConfirmacao.setConfirmacaoDados(instituicaoSelecionada, funcaoVoluntarioSelecionada);

                Stage stage = (Stage) proximoBotao1.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma instituição e uma função de voluntário.");
            alert.showAndWait();
        }


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
            detalhes.append("Horário de funcionamento: ").append(instituicao.getHorarioInicial()).append(" às ").append(instituicao.getHorarioFinal()).append("\n\n");


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
