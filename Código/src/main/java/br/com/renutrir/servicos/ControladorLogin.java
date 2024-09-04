package br.com.renutrir.servicos;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.repositorio.RepositorioDoador;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import br.com.renutrir.sessao.SessaoDoador;
import br.com.renutrir.sessao.SessaoInstituicao;
import javafx.scene.control.Alert;

import java.util.Optional;

public class ControladorLogin {

    private HelloController hc;

    public ControladorLogin(HelloController hc) {
        this.hc = hc;
    }

    public Doador buscarDoadorNoRepositorio(String emailOuUsuario, String senha) {
        RepositorioDoador repositorioDoador = new RepositorioDoador();

        Optional<Doador> doadorOpt = repositorioDoador.buscarDoadorPorEmail(emailOuUsuario);
        if (!doadorOpt.isPresent()) {
            doadorOpt = repositorioDoador.buscarDoadorPorNomeUsuario(emailOuUsuario);
        }

        if (doadorOpt.isPresent()) {
            Doador doador = doadorOpt.get();
            if (doador.getSenha().equals(senha)) {
                return doador;
            }
        }
        return null;
    }

    public Instituicao buscarInstituicaoNoRepositorio(String emailOuUsuario, String senha) {
        RepositorioInstituicao repositorioInstituicao = new RepositorioInstituicao();

        Optional<Instituicao> instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorEmail(emailOuUsuario);
        if (!instituicaoOpt.isPresent()) {
            instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorNomeUsuario(emailOuUsuario);
        }

        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            if (instituicao.getSenha().equals(senha)) {
                return instituicao;
            }
        }
        return null;
    }

    public void processarLogin(String emailOuUsuario, String senha, boolean isInstituicao) {
        if (isInstituicao) {
            Instituicao instituicao = buscarInstituicaoNoRepositorio(emailOuUsuario, senha);
            if (instituicao != null) {
                SessaoInstituicao.getInstancia().setInstituicaoLogada(instituicao); // Salva a instituição que fez login
                hc.showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Instituição!");
                hc.realizarTrocaDeTela("/br/com/renutrir/19-menu-instituicao.fxml", "ReNutrir - Instituição");
            } else {
                hc.showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para instituição.");
            }
        } else {
            Doador doador = buscarDoadorNoRepositorio(emailOuUsuario, senha);
            if (doador != null) {
                SessaoDoador.getInstancia().setDoadorLogado(doador); // Salva o doador que fez login
                hc.showAlert(Alert.AlertType.INFORMATION, "Login Bem-Sucedido", "Bem-vindo, Doador!");
                hc.realizarTrocaDeTela("/br/com/renutrir/04-menu-doador.fxml", "ReNutrir - Doador");
            } else {
                hc.showAlert(Alert.AlertType.ERROR, "Erro de Login", "E-mail, nome de usuário ou senha inválidos para doador.");
            }
        }
    }
}
