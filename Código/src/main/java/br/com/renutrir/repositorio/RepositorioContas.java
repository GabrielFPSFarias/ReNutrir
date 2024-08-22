package br.com.renutrir.repositorio;

import br.com.renutrir.model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContas {

    // armazenar os usuários - email/login
    private Map<String, Doador> contas;

    public RepositorioContas() {
        this.contas = new HashMap<>();
    }

    public void adicionarUsuario(Doador usuario) {
        if (!contas.containsKey(usuario.getEmail())) {
            contas.put(usuario.getEmail(), usuario);
        } else {
            System.out.println("Usuário já cadastrado.");
        }
    }

    public Optional<Doador> buscarUsuarioPorEmail(String email) {
        return Optional.ofNullable(contas.get(email));
    }

    public void removerUsuario(String email) {
        if (contas.containsKey(email)) {
            contas.remove(email);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // verificar login
    public boolean autenticarUsuario(String email, String senha) {
        Doador usuario = contas.get(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    public void listarUsuarios() {
        contas.values().forEach(usuario -> System.out.println(usuario));
    }
}
