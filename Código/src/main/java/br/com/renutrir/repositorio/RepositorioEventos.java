package br.com.renutrir.repositorio;

import br.com.renutrir.model.Evento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioEventos {
    private final String arquivoEventos = "src/dados/eventos.dat";
    private List<Evento> eventos;

    public RepositorioEventos() {
        eventos = carregarEventos();
    }

    private List<Evento> carregarEventos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoEventos))) {
            return (List<Evento>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvarEventos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoEventos))) {
            oos.writeObject(eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public Optional<Evento> buscarEventoPorNome(String nome) {
        return eventos.stream().filter(e -> e.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public void removerEvento(String nome) {
        eventos.removeIf(e -> e.getNome().equalsIgnoreCase(nome));
        salvarEventos();
    }

    public void atualizarEvento(Evento eventoAtualizado) {
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (evento.getNome().equalsIgnoreCase(eventoAtualizado.getNome())) {
                eventos.set(i, eventoAtualizado);
                salvarEventos();
                return;
            }
        }
    }

    public List<Evento> listarEventos() {
        return new ArrayList<>(eventos);
    }
}
