package br.com.renutrir.repositorio;

import br.com.renutrir.model.Instituicao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioInstituicao {
    private final String arquivoInstituicao = "src/dados/instituicoes.dat";
    private List<Instituicao> instituicoes;

    public RepositorioInstituicao() {
        instituicoes = carregarInstituicoes();
    }

    private List<Instituicao> carregarInstituicoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoInstituicao))) {
            return (List<Instituicao>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); //nn existe
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarInstituicoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoInstituicao))) {
            oos.writeObject(instituicoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarInstituicao(Instituicao instituicao) {
        instituicoes.add(instituicao);
        salvarInstituicoes();
    }

    public Optional<Instituicao> buscarInstituicaoPorCnpj(String cnpj) {
        return instituicoes.stream().filter(i -> i.getCnpj().equals(cnpj)).findFirst();
    }

    public void atualizarInstituicao(Instituicao instituicaoAtualizada) {
        Optional<Instituicao> instituicaoExistente = buscarInstituicaoPorCnpj(instituicaoAtualizada.getCnpj());
        if (instituicaoExistente.isPresent()) {
            instituicoes.remove(instituicaoExistente.get());
            instituicoes.add(instituicaoAtualizada);
            salvarInstituicoes();
        }
    }

    public void removerInstituicao(String cnpj) {
        instituicoes.removeIf(i -> i.getCnpj().equals(cnpj));
        salvarInstituicoes();
    }

    public List<Instituicao> listarInstituicoes() {
        return new ArrayList<>(instituicoes);
    }
}
