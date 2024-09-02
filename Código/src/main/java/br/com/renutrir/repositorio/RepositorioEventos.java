package br.com.renutrir.repositorio;

import br.com.renutrir.model.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEventos {
    private List<Evento> eventos;

    public RepositorioEventos() {
        this.eventos = new ArrayList<>();
    }

    // Adiciona um novo evento ao repositório
    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    // Remove um evento do repositório
    public boolean removerEvento(Evento evento) {
        return eventos.remove(evento);
    }

    // Remove eventos passados
    public void atualizarEventos() {
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getData().isBefore(LocalDate.now())){
                eventos.remove(eventos.get(i));
            }
        }
    }

    // Busca um evento
    public Evento buscarEvento(Evento e) {
        for (Evento evento : eventos) {
            if (evento.equals(e)) {
                return evento;
            }
        }
        return null;
    }

    // retorna todos os Eventos
    public List<Evento> listarEventos() {
        return new ArrayList<>(eventos); // Retorna uma cópia da lista
    }
}
