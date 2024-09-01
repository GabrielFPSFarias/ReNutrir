package br.com.renutrir.servicos;

import br.com.renutrir.model.Certificado;
import br.com.renutrir.model.Doador;
import javafx.scene.control.Alert;

public class ControladorCertificado {

    public Certificado criarCertificado(String descricao, int quantDoacoes) {
        validarDescricao(descricao);
        validarQuantDoacoes(quantDoacoes);

        Certificado certificado = new Certificado();
        certificado.setDescricao(descricao);
        certificado.setDataEmissao(java.time.LocalDate.now()); // Data atual como data de emissão
        certificado.setQuantDoacoes(quantDoacoes);

        return certificado;
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do certificado não pode estar vazia.");
        }
    }

    private void validarQuantDoacoes(int quantDoacoes) {
        if (quantDoacoes < 50) {
            throw new IllegalArgumentException("O doador não pode receber o certificado, ainda tem menos de 50 doações efetuadas.");
        }
    }

    public void verificarProgressoParaCertificado(Doador doador) {
        int doacoesRealizadas = doador.getCertificado().getQuantDoacoes();
        int doacoesRestantes = Certificado.DOACOES_NECESSARIAS - doacoesRealizadas;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Progresso para Certificado");
        alert.setHeaderText(null);
        alert.setContentText("Faltam " + doacoesRestantes + " doações para você conseguir o certificado.");
        alert.showAndWait();
    }
}
