public class ControladorEndereco {

    private Endereco endereco;

    public ControladorEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean validarEndereco() {
        return validarUf(endereco.getUf()) && validarNumero(endereco.getNumero());
    }

    private boolean validarUf(String uf) {
        if (uf == null || uf.length() != 2 || !uf.matches("[A-Za-z]{2}")) {
            System.out.println("UF inválida: " + uf);
            return false;
        }
        return true;
    }

    private boolean validarNumero(String numero) {
        if (numero == null || !numero.matches("\\d+")) {
            System.out.println("Número inválido: " + numero);
            return false;
        }
        return true;
    }

    public String getEndereco() {
        return endereco.getEndereco();
    }

    public String getBairro() {
        return endereco.getBairro();
    }

    public String getNumero() {
        return endereco.getNumero();
    }

    public String getCidade() {
        return endereco.getCidade();
    }

    public String getUf() {
        return endereco.getUf();
    }

    public String getComplemento() {
        return endereco.getComplemento();
    }

    public String getReferencia() {
        return endereco.getReferencia();
    }
