package br.com.renutrir.servicos;

import br.com.renutrir.model.Doacao;
import br.com.renutrir.repositorio.RepositorioDoacoes;

import javax.swing.*;
import java.util.List;

public class ControladorTransporte {

  private RepositorioDoacoes repositorioDoacoes;

  public ControladorTransporte() {
  }

  public ControladorTransporte(Action action) {
    this.repositorioDoacoes = new RepositorioDoacoes();
  }

  public void agendarTransporte(Doacao doacao) {
    if (validarDoacao(doacao)) {
      repositorioDoacoes.adicionarDoacao(doacao);
      System.out.println("Transporte agendado com sucesso!");
    } else {
      System.out.println("Falha ao agendar transporte: dados inválidos.");
    }
  }

  public List<Doacao> listarDoacoes() {
    return repositorioDoacoes.listarDoacoes();
  }

  public void atualizarTransporte(Doacao doacao) {
    if (validarDoacao(doacao)) {
      repositorioDoacoes.atualizarDoacao(doacao);
      System.out.println("Transporte atualizado com sucesso!");
    } else {
      System.out.println("Falha ao atualizar transporte: dados inválidos.");
    }
  }

  public void cancelarTransporte(Doacao doacao) {
    repositorioDoacoes.removerDoacao(doacao);
    System.out.println("Transporte cancelado com sucesso!");
  }

  private boolean validarDoacao(Doacao doacao) {
    if (doacao.getItem() == null || doacao.getItem().isEmpty()) {
      System.out.println("Item inválido.");
      return false;
    }
    if (doacao.getQuantidade() <= 0) {
      System.out.println("Quantidade inválida.");
      return false;
    }
    // mais validações...

    return true;
  }
}
