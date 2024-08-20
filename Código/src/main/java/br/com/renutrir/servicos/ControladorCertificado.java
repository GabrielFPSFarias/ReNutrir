package br.com.renutrir.servicos;

import br.com.renutrir.model.Certificado;

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
        if (quantDoacoes < 0) {
            throw new IllegalArgumentException("A quantidade de doações não pode ser negativa.");
        }
    }
}
