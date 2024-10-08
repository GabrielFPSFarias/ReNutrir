package br.com.renutrir.model;

import java.io.Serializable;

public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    private String endereco;
    private String bairro;
    private String numero;
    private String cidade;
    private String uf;
    private String complemento;
    private String referencia;

    public Endereco(String endereco, String bairro, String numero, String cidade, String uf, String complemento, String referencia) {
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.referencia = referencia;
    }

    // Getters e Setters

    public String getEndereco() {
        return endereco;
    }
    public String getBairro() {
        return bairro;
    }
    public String getNumero() {
        return numero;
    }
    public String getCidade() {
        return cidade;
    }
    public String getUf() {
        return uf;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getReferencia() {
        return referencia;
    }

    @Override
    public String toString() {
        return "Endereço: " + endereco + ", " +
                "Bairro: " + bairro + ", " +
                "Número: " + numero + ", " +
                "Cidade: " + cidade + ", " +
                "UF: " + uf + ", " +
                "Complemento: " + complemento + ", " +
                "Referência: " + referencia;
    }

    public String getEnderecoCompleto() {
        return String.format("%s, %s - %s, %s - %s, %s", endereco, numero, bairro, cidade, uf, referencia);
    }
}

/*
package br.com.renutrir.model;

public class Endereco {
    private String endereco;
    private String bairro;
    private String numero;
    private String cidade;
    private String uf;
    private String complemento;
    private String referencia;

    /*

        public class Endereco {
    private String numeroCasa;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    */
/*
    public Endereco(String endereco, String bairro, String numero, String cidade, String uf, String complemento, String referencia) {
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.referencia = referencia;
    }

    // Getters e Setters

    public String getEndereco() {
        return endereco;
    }
    public String getBairro() {
        return bairro;
    }
    public String getNumero() {
        return numero;
    }
    public String getCidade() {
        return cidade;
    }
    public String getUf() {
        return uf;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getReferencia() {
        return referencia;
    }
}
*/