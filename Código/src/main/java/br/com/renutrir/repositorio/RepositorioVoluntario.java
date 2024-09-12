package br.com.renutrir.repositorio;

import br.com.renutrir.model.Voluntario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioVoluntario {
    private static final String CAMINHO_ARQUIVO = "src/dados/funcoesVoluntario.dat";
    private List<String> funcoesVoluntario;

    public RepositorioVoluntario() {
        funcoesVoluntario = carregarFuncoesVoluntario();
        voluntarios = carregarVoluntarios();
    }

    public List<String> carregarFuncoesVoluntario() {
        File file = new File(CAMINHO_ARQUIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvarFuncoesVoluntario() {
        File diretorio = new File("src/dados");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(new File(CAMINHO_ARQUIVO));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(funcoesVoluntario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarFuncao(String funcao) {
        funcoesVoluntario.add(funcao);
        salvarFuncoesVoluntario();
    }

    public List<String> listarFuncoesVoluntario() {
        return new ArrayList<>(funcoesVoluntario);
    }

    public void removerFuncao(String funcao) {
        funcoesVoluntario.remove(funcao);
        salvarFuncoesVoluntario();
    }

    private final String arquivoVoluntarios = "src/dados/voluntariosRegistrados.dat";
    private List<Voluntario> voluntarios;



    private List<Voluntario> carregarVoluntarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoVoluntarios))) {
            return (List<Voluntario>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private void salvarVoluntarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoVoluntarios))) {
            oos.writeObject(voluntarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void adicionarVoluntario(Voluntario voluntario) {
        voluntarios.add(voluntario);
        salvarVoluntarios();
    }


    public Optional<Voluntario> buscarVoluntarioNomeUsuario(String nomeUsuario) {
        return voluntarios.stream()
                .filter(v -> v.getNomeUsuario() != null && v.getCpf().equals(nomeUsuario))
                .findFirst();
    }

    public void atualizarVoluntario(Voluntario voluntarioAtualizado) {
        Optional<Voluntario> voluntarioExistente = buscarVoluntarioNomeUsuario(voluntarioAtualizado.getNomeUsuario());
        if (voluntarioExistente.isPresent()) {
            voluntarios.remove(voluntarioExistente.get());
            voluntarios.add(voluntarioAtualizado);
            salvarVoluntarios();
        }
    }

    public void removerVoluntario(String nomeUsuario) {
        voluntarios.removeIf(v -> v.getNomeUsuario() != null && v.getNomeUsuario().equals(nomeUsuario));
        salvarVoluntarios();
    }

    public List<Voluntario> listarVoluntarios() {
        return new ArrayList<>(voluntarios);
    }

}
