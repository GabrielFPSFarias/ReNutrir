package br.com.renutrir.repositorio;

import br.com.renutrir.model.SolicitacaoDoacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSolicitacaoDoacao {

    public static final String ArquivoSolicitacao = "src/dados/solicitacoes_doacoes.dat";

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


    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            //pra nn sobrescrever..
        }
    }
}

/*
package br.com.renutrir.repositorio;

import br.com.renutrir.model.SolicitacaoDoacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSolicitacaoDoacao {

    private static final String SolicitacaoDoacao = "src/dados/solicitacoes_doacoes.dat";

    public void salvarSolicitacao(SolicitacaoDoacao solicitacao) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SolicitacaoDoacao, true))) {
            oos.writeObject(solicitacao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SolicitacaoDoacao> carregarSolicitacoes() {
        List<SolicitacaoDoacao> solicitacoes = new ArrayList<>();
        File arquivo = new File(SolicitacaoDoacao);

        if (!arquivo.exists()) {
            return solicitacoes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof SolicitacaoDoacao) {
                    solicitacoes.add((SolicitacaoDoacao) obj);
                }
            }
        } catch (EOFException e) {
            //
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return solicitacoes;
    }
}
*/