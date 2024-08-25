package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContas {

    //Salva os usuários por email, nome usuário e CPF
    private Map<String, Doador> contasPorEmail;
    private Map<String, Doador> contasPorNomeUsuario;
    private Map<String, Doador> contasPorCpf;

    public RepositorioContas() {
        this.contasPorEmail = new HashMap<>();
        this.contasPorNomeUsuario = new HashMap<>();
        this.contasPorCpf = new HashMap<>();
    }

    public boolean adicionarUsuario(Doador usuario) {
        //Verificar se já existe um usuário com o mesmo e-mail
        if (contasPorEmail.containsKey(usuario.getEmail())) {
            System.out.println("Erro: Já existe um usuário com esse e-mail.");
            return false;
        }

        //Verificar se já existe um usuário com o mesmo nome de usuário
        if (contasPorNomeUsuario.containsKey(usuario.getNomeUsuario())) {
            System.out.println("Erro: Já existe um usuário com esse nome de usuário.");
            return false;
        }

        //Verificar se já existe um usuário com o mesmo CPF
        if (contasPorCpf.containsKey(usuario.getCpf())) {
            System.out.println("Erro: Já existe um usuário com esse CPF.");
            return false;
        }
        contasPorEmail.put(usuario.getEmail(), usuario);
        contasPorNomeUsuario.put(usuario.getNomeUsuario(), usuario);
        contasPorCpf.put(usuario.getCpf(), usuario);

        return true;
    }

    public Optional<Doador> buscarUsuarioPorEmail(String email) {
        return Optional.ofNullable(contasPorEmail.get(email));
    }

    public Optional<Doador> buscarUsuarioPorNomeUsuario(String nomeUsuario) {
        return Optional.ofNullable(contasPorNomeUsuario.get(nomeUsuario));
    }

    public Optional<Doador> buscarUsuarioPorCpf(String cpf) {
        return Optional.ofNullable(contasPorCpf.get(cpf));
    }

    public void removerUsuario(String email) {
        Doador usuario = contasPorEmail.remove(email);
        if (usuario != null) {
            contasPorNomeUsuario.remove(usuario.getNomeUsuario());
            contasPorCpf.remove(usuario.getCpf());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    //Verifica login
    public boolean autenticarUsuario(String email, String senha) {
        Doador usuario = contasPorEmail.get(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public void listarUsuarios() {
        contasPorEmail.values().forEach(usuario -> System.out.println(usuario));
    }
}
