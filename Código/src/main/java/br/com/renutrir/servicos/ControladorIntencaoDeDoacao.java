package br.com.renutrir.servicos;

import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;

import java.util.List;

public class ControladorIntencaoDeDoacao {

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
        if (intencao.getDescricao() == null || intencao.getDescricao().isEmpty()) {
            System.out.println("Descrição inválida.");
            return false;
        }
        if (intencao.getQuantidade() <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }
        // Outras validações necessárias podem ser adicionadas aqui

        return true;
    }
}
