package br.com.renutrir.repositorio;

import br.com.renutrir.model.Endereco;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.SolicitacaoDoacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioSolicitacaoDoacao {

    public static final String ArquivoSolicitacao = "src/dados/solicitacoes_doacoes.dat";

    private final RepositorioInstituicao repositorioInstituicao;

    public RepositorioSolicitacaoDoacao(RepositorioInstituicao repositorioInstituicao) {
        this.repositorioInstituicao = repositorioInstituicao;
    }

    public RepositorioSolicitacaoDoacao() {
        this.repositorioInstituicao = null;
    }

    public void salvarSolicitacao(SolicitacaoDoacao solicitacao) {
        List<SolicitacaoDoacao> solicitacoes = carregarSolicitacoes();
        solicitacoes.add(solicitacao);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ArquivoSolicitacao))) {
            oos.writeObject(solicitacoes);
            System.out.println("Solicitação salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a solicitação: " + e.getMessage());
        }
    }

    public List<SolicitacaoDoacao> carregarSolicitacoes() {
        List<SolicitacaoDoacao> solicitacoes = new ArrayList<>();
        File arquivo = new File(ArquivoSolicitacao);

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
                System.out.println("Arquivo das solicitações não encontrado, criando um novo.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo solicitacoes_doacoes.dat: " + e.getMessage());
            }
            return solicitacoes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            solicitacoes = (List<SolicitacaoDoacao>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar as solicitações: " + e.getMessage());
        }

        return solicitacoes;
    }

    public void atualizarSolicitacao(SolicitacaoDoacao solicitacaoAtualizada) {
        List<SolicitacaoDoacao> solicitacoes = carregarSolicitacoes();
        boolean atualizado = false;

        for (int i = 0; i < solicitacoes.size(); i++) {
            SolicitacaoDoacao solicitacao = solicitacoes.get(i);
            if (solicitacao.getTipoItem().equals(solicitacaoAtualizada.getTipoItem()) &&
                    solicitacao.getItem().equals(solicitacaoAtualizada.getItem()) &&
                    solicitacao.getNomeInstituicao().equals(solicitacaoAtualizada.getNomeInstituicao()) &&
                    solicitacao.getNomeUsuario().equals(solicitacaoAtualizada.getNomeUsuario())) {

                solicitacoes.set(i, solicitacaoAtualizada);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ArquivoSolicitacao))) {
                oos.writeObject(solicitacoes);
                System.out.println("Solicitação atualizada com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao atualizar a solicitação: " + e.getMessage());
            }
        } else {
            System.err.println("Solicitação não encontrada para atualização.");
        }
    }

    public void salvarSolicitacoes(List<SolicitacaoDoacao> solicitacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ArquivoSolicitacao))) {
            oos.writeObject(solicitacoes);
            System.out.println("Solicitações salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar as solicitações: " + e.getMessage());
        }
    }

    public void removerSolicitacoes(List<SolicitacaoDoacao> solicitacoesParaRemover) {
        List<SolicitacaoDoacao> todasSolicitacoes = carregarSolicitacoes();
        todasSolicitacoes.removeAll(solicitacoesParaRemover);
        salvarSolicitacoes(todasSolicitacoes);
    }

    public void removerSolicitacao(SolicitacaoDoacao solicitacaoParaRemover) {
        List<SolicitacaoDoacao> todasSolicitacoes = carregarSolicitacoes();

        boolean removido = todasSolicitacoes.removeIf(solicitacao ->
                solicitacao.getTipoItem().equals(solicitacaoParaRemover.getTipoItem()) &&
                        solicitacao.getItem().equals(solicitacaoParaRemover.getItem()) &&
                        solicitacao.getNomeInstituicao().equals(solicitacaoParaRemover.getNomeInstituicao()) &&
                        solicitacao.getNomeUsuario().equals(solicitacaoParaRemover.getNomeUsuario())
        );

        if (removido) {
            salvarSolicitacoes(todasSolicitacoes);
            System.out.println("Solicitação removida com sucesso.");
        } else {
            System.err.println("Erro: Solicitação não encontrada para remoção.");
        }
    }

    public Endereco getEnderecoInstituicao(String tipoItem, String item, String nomeInstituicao, String nomeUsuario) {
        List<SolicitacaoDoacao> solicitacoes = carregarSolicitacoes();

        for (SolicitacaoDoacao solicitacao : solicitacoes) {
            if (solicitacao.getTipoItem().equals(tipoItem) &&
                    solicitacao.getItem().equals(item) &&
                    solicitacao.getNomeInstituicao().equals(nomeInstituicao) &&
                    solicitacao.getNomeUsuario().equals(nomeUsuario)) {

                Optional<Instituicao> instituicaoOpt = repositorioInstituicao.buscarInstituicaoPorNome(nomeInstituicao);
                if (instituicaoOpt.isPresent()) {
                    Instituicao instituicao = instituicaoOpt.get();
                    return instituicao.getEndereco();
                } else {
                    System.err.println("Instituição não encontrada.");
                    return null;
                }
            }
        }

        System.err.println("Solicitação não encontrada para o endereço.");
        return null;
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // Não sobrescrever o cabeçalho do stream
        }
    }
}
