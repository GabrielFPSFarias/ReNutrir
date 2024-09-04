package br.com.renutrir.servicos;

import br.com.renutrir.model.Instituicao;
import br.com.renutrir.renutrir.HelloController;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ControladorIntencaoDeDoacao {

    private HelloController hc;

    @FXML
    private ComboBox<String> escolherInstituicaoDoarCbox;

    private RepositorioInstituicao repositorioInstituicao;

    @FXML
    public void initialize() {
        repositorioInstituicao = new RepositorioInstituicao();
        cboxEscolherInstituicaoDoar();
    }

    @FXML
    void cboxEscolherInstituicaoDoar() {
        List<Instituicao> instituicoes = repositorioInstituicao.listarInstituicoes();
        for (Instituicao instituicao : instituicoes) {
            escolherInstituicaoDoarCbox.getItems().add(instituicao.getNome());
        }
    }

    @FXML
    public void botaoInstituicoesDoacao(ActionEvent actionEvent) {
        hc.realizarTrocaDeTela("/br/com/renutrir/06-doacoes-solicitadas.fxml", "ReNutrir - Doações Solicitadas");
    }

    /*
    private RepositorioIntencaoDoacao repositorioIntencaoDoacao;

    public ControladorIntencaoDeDoacao() {
        this.repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
    }

    public void criarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.adicionarIntencao(intencao);
            System.out.println("Intenção de doação criada com sucesso!");
        } else {
            System.out.println("Falha ao criar intenção de doação: dados inválidos.");
        }
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return repositorioIntencaoDoacao.listarIntencoes();
    }

    public void atualizarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.atualizarIntencao(intencao);
            System.out.println("Intenção de doação atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar intenção de doação: dados inválidos.");
        }
    }

    public void removerIntencaoDeDoacao(IntencaoDoacao intencao) {
        repositorioIntencaoDoacao.removerIntencao(intencao);
        System.out.println("Intenção de doação removida com sucesso!");
    }

    private boolean validarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (intencao.getItem() == null || intencao.getItem().isEmpty()) {
            System.out.println("Item inválido.");
            return false;
        }
        if (intencao.getQuantidade() <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }
        // espaço para mais validações..

        return true;
    }
    */

}
