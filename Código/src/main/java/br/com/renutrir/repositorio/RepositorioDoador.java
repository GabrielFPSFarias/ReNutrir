package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDoador {
    private final String arquivoDoador = "src/dados/doadores.dat";
    private List<Doador> doadores;

    public RepositorioDoador() {
        doadores = carregarDoadores();
    }

    @SuppressWarnings("unchecked")
    private List<Doador> carregarDoadores() {
        File file = new File(arquivoDoador);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoDoador))) {
            return (List<Doador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarDoadores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDoador))) {
            oos.writeObject(doadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarDoador(Doador doador) {
        doadores.add(doador);
        salvarDoadores();
    }

    public Optional<Doador> buscarDoadorPorCpf(String cpf) {
        return doadores.stream()
                .filter(d -> d.getNomeUsuario() != null && d.getNomeUsuario().equalsIgnoreCase(cpf))
                .findFirst();
    }

    public Optional<Doador> buscarDoadorPorNomeUsuario(String nomeUsuario) {
        return doadores.stream()
                .filter(d -> d.getNomeUsuario() != null && d.getNomeUsuario().equalsIgnoreCase(nomeUsuario))
                .findFirst();
    }


    public Optional<Doador> buscarDoadorPorEmail(String email) {
        return doadores.stream()
                .filter(d -> d.getEmail() != null && d.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void atualizarDoador(Doador doadorAtualizado) {
        Optional<Doador> doadorExistente = buscarDoadorPorCpf(doadorAtualizado.getCpf());
        if (doadorExistente.isPresent()) {
            doadores.remove(doadorExistente.get());
            doadores.add(doadorAtualizado);
            salvarDoadores();
        }
    }

    public void removerDoador(String cpf) {
        doadores.removeIf(d -> d.getCpf().equals(cpf));
        salvarDoadores();
    }

    public List<Doador> listarDoadores() {
        return new ArrayList<>(doadores);
    }
}
