package br.com.renutrir.model;

public class Conta {
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.length() < 6){
            throw new IllegalArgumentException("Coloque um nome de usuário com 6 ou mais caracteres.");
        }
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 4){
            throw new IllegalArgumentException("A senha deve ter mais de 4 caracteres");
        }
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.length() < 8){
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }
}
