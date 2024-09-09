package br.com.renutrir.repositorio;

import br.com.renutrir.model.SolicitacaoDoacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSolicitacaoDoacao {

    public static final String SOLICITACAO_DOACAO = "src/dados/solicitacoes_doacoes.dat";

    public void salvarSolicitacao(SolicitacaoDoacao solicitacao) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SOLICITACAO_DOACAO, true))) {
            oos.writeObject(solicitacao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<SolicitacaoDoacao> carregarSolicitacoes() {
        List<SolicitacaoDoacao> solicitacoes = new ArrayList<>();
        File arquivo = new File(SOLICITACAO_DOACAO);

        if (!arquivo.exists()) {
            return solicitacoes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof SolicitacaoDoacao) {
                        solicitacoes.add((SolicitacaoDoacao) obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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