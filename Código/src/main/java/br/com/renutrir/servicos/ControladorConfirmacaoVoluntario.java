package br.com.renutrir.servicos;

import br.com.renutrir.model.Instituicao;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import br.com.renutrir.repositorio.RepositorioInstituicao;

import java.util.Optional;

public class ControladorConfirmacaoVoluntario {

    private  ControladorEscolhaInstituicaoFuncaoVoluntario controladorEscolhaInstituicaoFuncaoVoluntario;
    private RepositorioInstituicao repositorioInstituicoes;

    public ControladorConfirmacaoVoluntario() {

       this.controladorEscolhaInstituicaoFuncaoVoluntario = new ControladorEscolhaInstituicaoFuncaoVoluntario();
       this.repositorioInstituicoes = new RepositorioInstituicao();
    }
@FXML
 private Label instituicaoInformacoesLabel;

@FXML
Button voltarBotao72;
@FXML
private Button confirmarVoluntarioBotao;
@FXML
public void voltarBotao72(){

    controladorEscolhaInstituicaoFuncaoVoluntario.realizarTrocaDeTela("/br/com/renutrir/27-escolha-instituicao-funcao-voluntario.fxml", "ReNutrir - Escolha de instituição e função do voluntário",voltarBotao72);
    }

    public String getConfirmacaoDados (String nomeUsuario) {

        Optional<Instituicao> instituicaoOptional = repositorioInstituicoes.buscarInstituicaoPorNomeUsuario(nomeUsuario);

        if (instituicaoOptional.isPresent()) {

            Instituicao instituicao = instituicaoOptional.get();

            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Nome: ").append(instituicao.getNome()).append("\n\n");
            detalhes.append(instituicao.getEndereco()).append("\n\n");
            detalhes.append("Telefone: ").append(instituicao.getTelefone()).append("\n\n");
            detalhes.append("Email: ").append(instituicao.getEmail()).append("\n\n");
            detalhes.append("Horário de funcionamento: ").append(instituicao.getHorarioInicial()).append(" às ").append(instituicao.getHorarioFinal());
            detalhes.append("Descrição: ").append(instituicao.getDescricao());

            return detalhes.toString();
        } else {
            return "Detalhes da instituição não encontrados.";
        }
    }
@FXML
public void confirmarVoluntarioBotao (){


    }
}
