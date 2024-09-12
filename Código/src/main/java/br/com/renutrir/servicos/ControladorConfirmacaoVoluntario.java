package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import br.com.renutrir.repositorio.RepositorioVoluntario;

public class ControladorConfirmacaoVoluntario {

    private ControladorEscolhaInstituicaoFuncaoVoluntario controladorEscolha;
    private RepositorioInstituicao repositorioInstituicoes;

    public ControladorConfirmacaoVoluntario() {

        this.controladorEscolha = new ControladorEscolhaInstituicaoFuncaoVoluntario();
        this.repositorioInstituicoes = new RepositorioInstituicao();
    }

    @FXML
    private Label instituicaoInformacoesLabel;

    @FXML
    Button voltarBotao72;
    @FXML
    private Button confirmarVoluntarioBotao;

    @FXML
    public void voltarBotao72() {

        controladorEscolha.realizarTrocaDeTela("/br/com/renutrir/27-escolha-instituicao-funcao-voluntario.fxml", "ReNutrir - Escolha de instituição e função do voluntário", voltarBotao72);
    }

    /*@FXML
    void doarAlimentosBotao(ActionEvent actionEvent) {
        Instituicao instituicaoSelecionada = RepositorioIntencaoDoacao.getInstituicaoSelecionada();

        if (instituicaoSelecionada == null) {
            hc.showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma instituição selecionada");
            return;
        }

        System.out.println("Instituição selecionada: " + instituicaoSelecionada.getNome());

        String nomeItem = fieldItemDoarIntencao.getText();
        String qtdItem = fieldInserirQtdItem.getText();
        IntencaoDoacao intencaoDoacao = new IntencaoDoacao(SessaoDoador.getInstancia().getDoadorLogado(), instituicaoSelecionada, Integer.parseInt(qtdItem), "Alimentos", nomeItem);
        RepositorioIntencaoDoacao repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
        repositorioIntencaoDoacao.adicionarIntencao(intencaoDoacao);

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdItem);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "A quantidade deve ser um número válido.");
            return;
        }

        Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();
        String doadorNome = doadorLogado != null ? doadorLogado.getNome() : "Desconhecido";
        String tipoDoacao = "Alimentos";
        LocalDateTime dataHora = LocalDateTime.now();

        try {
            URL fxmlUrl = getClass().getResource("/br/com/renutrir/07-9-intencao-concluida.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML não encontrado.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            ControladorIntencaoDeDoacao controlador = loader.getController();
            controlador.setInformacoesDoacao(doadorNome, tipoDoacao, quantidade, nomeItem, dataHora, instituicaoSelecionada);
            showAlert(Alert.AlertType.INFORMATION, "Intenção Realizada", "Sua intenção de doação foi realizada com sucesso!");

            Stage stage = (Stage) botaoItemDoar.getScene().getWindow();
            stage.setTitle("ReNutrir - Intenção Concluída");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a tela de doação concluída.");
        }
    }*/

    // Método para receber a instituição e função e exibi-las


    public void setConfirmacaoDados(Instituicao instituicao, String funcaoVoluntario) {


        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Nome: ").append(instituicao.getNome()).append("\n\n");
        detalhes.append(instituicao.getEndereco()).append("\n\n");
        detalhes.append("Telefone: ").append(instituicao.getTelefone()).append("\n\n");
        detalhes.append("Email: ").append(instituicao.getEmail()).append("\n\n");
        detalhes.append("Horário de funcionamento: ").append(instituicao.getHorarioInicial()).append(" às ").append(instituicao.getHorarioFinal()).append("\n\n");
        ;
        detalhes.append("Função: ").append(funcaoVoluntario).append("\n\n");
        ;

        instituicaoInformacoesLabel.setText(detalhes.toString());

        this.instituicaoSelecionada = instituicao;
        this.funcaoVoluntarioSelecionada = funcaoVoluntario;

    }

    private Instituicao instituicaoSelecionada;
    private String funcaoVoluntarioSelecionada;

    @FXML
    public void confirmarVoluntarioBotao() {
        if (instituicaoSelecionada != null && funcaoVoluntarioSelecionada != null) {
            // Supondo que você tenha um repositório para salvar os voluntários ou algo similar
            RepositorioVoluntario repositorioVoluntario = new RepositorioVoluntario();

            // Aqui pode ser criado um objeto de voluntário ou função do voluntário
            repositorioVoluntario.adicionarFuncao(funcaoVoluntarioSelecionada);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Sua participação como voluntário foi confirmada!");
            alert.showAndWait();

            // Redirecionar para outra tela após a confirmação
            controladorEscolha.realizarTrocaDeTela("/br/com/renutrir/08-menu-voluntario.fxml", "ReNutrir - Voluntariado Concluído", confirmarVoluntarioBotao);
        } else {
            // Caso nenhuma instituição ou função tenha sido selecionada, exibir alerta
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma instituição e uma função de voluntário.");
            alert.showAndWait();
        }
    }
}
