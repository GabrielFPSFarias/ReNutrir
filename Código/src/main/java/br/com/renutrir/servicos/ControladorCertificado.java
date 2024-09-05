package br.com.renutrir.servicos;

import br.com.renutrir.model.Certificado;
import br.com.renutrir.model.Doador;
import br.com.renutrir.repositorio.RepositorioDoacoes;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class ControladorCertificado {

    public ControladorCertificado() {
    }

    public static final int DOACOES_NECESSARIAS = 50;

    public Certificado criarCertificado(String descricao, int quantDoacoes) {
        validarDescricao(descricao);
        validarQuantDoacoes(quantDoacoes);

        Certificado certificado = new Certificado();
        certificado.setDescricao(descricao);
        certificado.setDataEmissao(java.time.LocalDate.now());
        certificado.setQuantDoacoes(quantDoacoes);

        return certificado;
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do certificado não pode estar vazia.");
        }
    }

    private void validarQuantDoacoes(int quantDoacoes) {
        if (quantDoacoes < DOACOES_NECESSARIAS) {
            throw new IllegalArgumentException("O doador não pode receber o certificado, ainda tem menos de " + DOACOES_NECESSARIAS + " doações efetuadas.");
        }
    }

    public void verificarProgressoParaCertificado(Doador doador) {
        Platform.runLater(() -> {
            RepositorioDoacoes repositorio = new RepositorioDoacoes();
            int doacoesRealizadas = repositorio.listarDoacoes().size();
            int doacoesRestantes = DOACOES_NECESSARIAS - doacoesRealizadas;

            if (doacoesRestantes <= 0) {
                emitirCertificado(doador);
                showAlert(Alert.AlertType.INFORMATION, "Certificado Disponível", "Seu certificado já está disponível.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Progresso do Certificado",
                        "Faltam " + doacoesRestantes + " doações para você ganhar o certificado.");
            }
        });
    }

    private void emitirCertificado(Doador doador) {
        String nomeArquivo = "src/dados/" + doador.getNomeUsuario() + "_certificado.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("Certificado de Reconhecimento");
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.newLine();
            writer.write("Este certificado é concedido a:");
            writer.newLine();
            writer.write(doador.getNome());
            writer.newLine();
            writer.write("CPF: " + doador.getCpf());
            writer.newLine();
            writer.write("Por sua contribuição generosa de " + DOACOES_NECESSARIAS + " doações.");
            writer.newLine();
            writer.newLine();
            writer.write("Data: " + LocalDate.now());
            writer.newLine();
            writer.newLine();
            writer.write("Agradecemos profundamente pelo seu apoio.");
            writer.newLine();
            writer.write("ReNutrir");
            writer.newLine();
            writer.write("=============================");
        } catch (IOException e) {
            Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao salvar o certificado."));
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
