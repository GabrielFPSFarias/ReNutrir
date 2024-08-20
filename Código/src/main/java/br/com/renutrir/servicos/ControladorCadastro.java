package br.com.renutrir.servicos;

import br.com.renutrir.model.*;
import br.com.renutrir.model.Conta;
import br.com.renutrir.model.Endereco;

public class ControladorCadastro {

    public Conta cadastrarConta(String nome, String nomeUsuario, String email, String senha, String telefone, Endereco endereco) {
        validarDados(nome, nomeUsuario, email, senha, telefone);

        Conta novaConta = new Conta();
        novaConta.setNome(nome);
        novaConta.setNomeUsuario(nomeUsuario);
        novaConta.setEmail(email);
        novaConta.setSenha(senha);
        novaConta.setTelefone(telefone);
        novaConta.setEndereco(endereco);

        // Aqui você pode adicionar o código para salvar a conta no banco de dados ou outro repositório

        return novaConta;
    }

    private void validarDados(String nome, String nomeUsuario, String email, String senha, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        if (nomeUsuario == null || nomeUsuario.length() < 6) {
            throw new IllegalArgumentException("O nome de usuário deve ter 6 ou mais caracteres.");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("A senha deve ter mais de 4 caracteres.");
        }

        if (telefone == null || telefone.length() < 8) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
    }
}

