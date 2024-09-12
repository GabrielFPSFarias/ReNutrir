package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.model.Voluntario;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import br.com.renutrir.sessao.SessaoDoador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

import br.com.renutrir.repositorio.RepositorioVoluntario;
import br.com.renutrir.sessao.SessaoDoador;

public class ControladorConfirmacaoVoluntario implements Initializable {

    private ControladorEscolhaInstituicaoFuncaoVoluntario controladorEscolha;
    private RepositorioInstituicao repositorioInstituicoes;



    @FXML
    private Label instituicaoInformacoesLabel;

    public Doador getDoadorLogado() {
        return doadorLogado;
    }

    private Doador doadorLogado = SessaoDoador.getInstancia().getDoadorLogado();


    @FXML
    Button voltarBotao72;
    @FXML
    private Button confirmarVoluntarioBotao;

    @FXML
    public void voltarBotao72() {

        controladorEscolha.realizarTrocaDeTela("/br/com/renutrir/27-escolha-instituicao-funcao-voluntario.fxml", "ReNutrir - Escolha de instituição e função do voluntário", voltarBotao72);
    }


    public void setConfirmacaoDados(Instituicao instituicao, String funcaoVoluntario) {


        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Nome: ").append(instituicao.getNome()).append("\n\n");
        detalhes.append(instituicao.getEndereco()).append("\n\n");
        detalhes.append("Telefone: ").append(instituicao.getTelefone()).append("\n\n");
        detalhes.append("Email: ").append(instituicao.getEmail()).append("\n\n");
        detalhes.append("Horário de funcionamento: ").append(instituicao.getHorarioInicial()).append(" às ").append(instituicao.getHorarioFinal()).append("\n\n");
        detalhes.append("Função: ").append(funcaoVoluntario).append("\n\n");

        instituicaoInformacoesLabel.setText(detalhes.toString());

        this.instituicaoSelecionada = instituicao;
        this.funcaoVoluntarioSelecionada = funcaoVoluntario;

    }

    private Instituicao instituicaoSelecionada;
    private String funcaoVoluntarioSelecionada;

    @FXML
    public void confirmarVoluntarioBotao() {
        if (instituicaoSelecionada != null && funcaoVoluntarioSelecionada != null) {

            if (repositorioInstituicoes.buscarInstituicaoPorNomeUsuario(instituicaoSelecionada.getNomeUsuario()).isPresent()) {

                instituicaoSelecionada.getRepositorioVoluntario().adicionarVoluntario(new Voluntario(getDoadorLogado().getNome(), getDoadorLogado().getEmail(), getDoadorLogado().getTelefone(), funcaoVoluntarioSelecionada));
                ControladorVoluntario controladorVoluntario = new ControladorVoluntario();

                controladorVoluntario.getInstituicaoVinculadaLabel().setText("Instituição vinculada: " + instituicaoSelecionada.getNome());
                controladorVoluntario.getFuncaoVoluntarioLabel().setText("Função do voluntário: " + funcaoVoluntarioSelecionada);


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.repositorioInstituicoes = new RepositorioInstituicao();
        this.controladorEscolha = new ControladorEscolhaInstituicaoFuncaoVoluntario();

    }
}
