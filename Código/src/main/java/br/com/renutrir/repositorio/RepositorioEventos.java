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

    private void salvarEventos() {
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

    public List<Evento> listarEventos() {
        return new ArrayList<>(eventos);
    }
}


/*
package br.com.renutrir.repositorio;

import br.com.renutrir.model.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEventos {
    private static List<Evento> eventos;

    public RepositorioEventos() {
        this.eventos = new ArrayList<>();
    }

    // Adiciona um novo evento ao repositório
    public static void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    // Remove um evento do repositório
    public static boolean removerEvento(Evento evento) {
        return eventos.remove(evento);
    }

    // Remove eventos passados
    public static void atualizarEventos() {
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getData().isBefore(LocalDate.now())){
                eventos.remove(eventos.get(i));
            }
        }
    }

    // Busca um evento
    public static Evento buscarEvento(Evento e) {
        for (Evento evento : eventos) {
            if (evento.equals(e)) {
                return evento;
            }
        }
        return null;
    }

    // retorna todos os Eventos
    public static List<Evento> listarEventos() {
        return new ArrayList<>(eventos); // Retorna uma cópia da lista
    }
}
*/