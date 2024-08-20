package br.com.renutrir.servicos;

import br.com.renutrir.model.Certificado;

public class ControladorCertificado {

    /**
     * Cria um novo certificado.
     *
     * @param descricao A descrição do certificado.
     * @param quantDoacoes A quantidade de doações associadas ao certificado.
     * @return O novo certificado criado.
     */
    public Certificado criarCertificado(String descricao, int quantDoacoes) {
        validarDescricao(descricao);
        validarQuantDoacoes(quantDoacoes);

        Certificado certificado = new Certificado();
        certificado.setDescricao(descricao);
        certificado.setDataEmissao(java.time.LocalDate.now()); // Data atual como data de emissão
        certificado.setQuantDoacoes(quantDoacoes);

        // Aqui você pode adicionar o código para salvar o certificado no banco de dados ou outro repositório

        return certificado;
    }

    /**
     * Valida a descrição do certificado.
     *
     * @param descricao A descrição a ser validada.
     */
    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do certificado não pode estar vazia.");
        }
    }

    /**
     * Valida a quantidade de doações.
     *
     * @param quantDoacoes A quantidade de doações a ser validada.
     */
    private void validarQuantDoacoes(int quantDoacoes) {
        if (quantDoacoes < 0) {
            throw new IllegalArgumentException("A quantidade de doações não pode ser negativa.");
        }
    }
}
