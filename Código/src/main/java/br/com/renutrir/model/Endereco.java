package br.com.renutrir.model;

public class Endereco {
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco() {
    }

    // Construtor com todos parâmetros
    public Endereco(String numero, String complemento, String bairro, String cidade, String uf) {
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("Número não pode ser vazio.");
        }
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.isEmpty()) {
            throw new IllegalArgumentException("Bairro não pode ser vazio.");
        }
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia.");
        }
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        if (uf == null || uf.length() != 2) {
            throw new IllegalArgumentException("UF deve ter exatamente 2 caracteres.");
        }
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
