package br.com.renutrir.servicos;

import br.com.renutrir.model.IntencaoDoacao;
import br.com.renutrir.repositorio.RepositorioIntencaoDoacao;
import javafx.fxml.FXML;
import br.com.renutrir.model.Endereco;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.repositorio.*;
import br.com.renutrir.model.Doador;
import br.com.renutrir.servicos.*;
import br.com.renutrir.main.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Optional;
import java.awt.*;
import java.util.List;

public class ControladorIntencaoDeDoacao {

    private RepositorioIntencaoDoacao repositorioIntencaoDoacao;

    public ControladorIntencaoDeDoacao() {
        this.repositorioIntencaoDoacao = new RepositorioIntencaoDoacao();
    }

    public void criarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.adicionarIntencao(intencao);
            System.out.println("Intenção de doação criada com sucesso!");
        } else {
            System.out.println("Falha ao criar intenção de doação: dados inválidos.");
        }
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return repositorioIntencaoDoacao.listarIntencoes();
    }

    public void atualizarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (validarIntencaoDeDoacao(intencao)) {
            repositorioIntencaoDoacao.atualizarIntencao(intencao);
            System.out.println("Intenção de doação atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar intenção de doação: dados inválidos.");
        }
    }

    public void removerIntencaoDeDoacao(IntencaoDoacao intencao) {
        repositorioIntencaoDoacao.removerIntencao(intencao);
        System.out.println("Intenção de doação removida com sucesso!");
    }

    private boolean validarIntencaoDeDoacao(IntencaoDoacao intencao) {
        if (intencao.getDescricao() == null || intencao.getDescricao().isEmpty()) {
            System.out.println("Descrição inválida.");
            return false;
        }
        if (intencao.getQuantidade() <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }
        // espaço para mais validações..

        return true;
    }


}
