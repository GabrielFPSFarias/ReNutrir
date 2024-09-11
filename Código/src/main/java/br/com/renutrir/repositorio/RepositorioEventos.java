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

    public List<Evento> carregarEventos() {
        List<Evento> eventos = new ArrayList<>();
        File arquivo = new File(arquivoEventos);

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
                System.out.println("Arquivo de eventos não encontrado, criando um novo.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de eventos: " + e.getMessage());
            }
            return eventos;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            eventos = (List<Evento>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo de eventos não encontrado - " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os eventos: " + e.getMessage());
        }

        return eventos;
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
