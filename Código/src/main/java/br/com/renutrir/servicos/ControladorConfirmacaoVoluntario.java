package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import br.com.renutrir.servicos.ControladorListaInstituicoes;

public class ControladorConfirmacaoVoluntario {
    ControladorListaInstituicoes controladorListaInstituicoes = new ControladorListaInstituicoes();

@FXML
 private Label instituicaoInformacoesLabel;

@FXML
Button voltarBotao72;
@FXML
private Button confirmarVoluntarioBotao;
@FXML
public void voltarBotao72(){

    controladorListaInstituicoes.realizarTrocaDeTela("/br/com/renutrir/05.5-lista-instituicoes1.fxml", "ReNutrir - Lista de Instituições",voltarBotao72);
    }
@FXML
public void confirmarVoluntarioBotao (){


    }

    /*public void realizarTrocaDeTela(String fxmlArquivo, String titulo) {
        System.out.println("Clicou: " + fxmlArquivo);
        Stage stage = (Stage) voltarBotao72.getScene().getWindow();
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
    }*/
}
