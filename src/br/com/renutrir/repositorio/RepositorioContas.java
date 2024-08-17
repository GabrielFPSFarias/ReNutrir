package br.com.renutrir.repositorio;

import br.com.renutrir.model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContas {

    // Mapa para armazenar os usuários, onde a chave é o email/login do doador
    private Map<String, Doador> contas;

    public RepositorioContas() {
        this.contas = new HashMap<>();
    }

    // Método para adicionar um novo usuário ao repositório
    public void adicionarUsuario(Doador usuario) {
        if (!contas.containsKey(usuario.getEmail())) {
            contas.put(usuario.getEmail(), usuario);
        } else {
            System.out.println("Usuário já cadastrado.");
        }
    }

    // Método para buscar um usuário pelo email/login
    public Optional<Doador> buscarUsuarioPorEmail(String email) {
        return Optional.ofNullable(contas.get(email));
    }

    // Método para remover um usuário do repositório
    public void removerUsuario(String email) {
        if (contas.containsKey(email)) {
            contas.remove(email);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Método para verificar as credenciais de login
    public boolean autenticarUsuario(String email, String senha) {
        Doador usuario = contas.get(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    // Método para listar todos os usuários cadastrados
    public void listarUsuarios() {
        contas.values().forEach(usuario -> System.out.println(usuario));
    }
}
