package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContas {

    //Salva os usuários por email, nome usuário e CPF
    private Map<String, Doador> contasPorEmail;
    private Map<String, Doador> contasPorNomeUsuario;
    private Map<String, Doador> contasPorCpf;

    //Salva as instituições por CNPJ, nome usuário instituição e email
    private Map<String, Instituicao> contasPorCnpj;
    private Map<String, Instituicao> contasPorNomeInstituicao;
    private Map<String, Instituicao> contasEmailInstituicao;

    public RepositorioContas() {
        this.contasPorEmail = new HashMap<>();
        this.contasPorNomeUsuario = new HashMap<>();
        this.contasPorCpf = new HashMap<>();
        this.contasPorCnpj = new HashMap<>();
        this.contasPorNomeInstituicao = new HashMap<>();
        this.contasEmailInstituicao = new HashMap<>();
    }

    public boolean adicionarUsuario(Doador usuario) {
        if (contasPorEmail.containsKey(usuario.getEmail())) {
            System.out.println("Erro: Já existe um usuário com esse email.");
            return false;
        }
        if (contasPorNomeUsuario.containsKey(usuario.getNomeUsuario())) {
            System.out.println("Erro: Já existe um usuário com esse nome de usuário.");
            return false;
        }
        if (contasPorCpf.containsKey(usuario.getCpf())) {
            System.out.println("Erro: Já existe um usuário com esse CPF.");
            return false;
        }

        // Adicionar o usuário aos mapas
        contasPorEmail.put(usuario.getEmail(), usuario);
        contasPorNomeUsuario.put(usuario.getNomeUsuario(), usuario);
        contasPorCpf.put(usuario.getCpf(), usuario);

        return true;
    }

    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (contasEmailInstituicao.containsKey(instituicao.getEmail())) {
            System.out.println("Erro: Já existe uma instituição com esse email.");
            return false;
        }
        if (contasPorNomeInstituicao.containsKey(instituicao.getNomeUsuario())) {
            System.out.println("Erro: Já existe uma instituição com esse nome de usuário.");
            return false;
        }
        if (contasPorCnpj.containsKey(instituicao.getCnpj())) {
            System.out.println("Erro: Já existe uma instituição com esse CNPJ.");
            return false;
        }

        // Adicionar a instituição aos mapas
        contasEmailInstituicao.put(instituicao.getEmail(), instituicao);
        contasPorNomeInstituicao.put(instituicao.getNomeUsuario(), instituicao);
        contasPorCnpj.put(instituicao.getCnpj(), instituicao);

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

    public Optional<Instituicao> buscarInstituicaoEmail(String email) {
        return Optional.ofNullable(contasEmailInstituicao.get(email));
    }

    public Optional<Instituicao> buscarUsuarioPorNomeInstituicao(String nomeUsuario) {
        return Optional.ofNullable(contasPorNomeInstituicao.get(nomeUsuario));
    }

    public Optional<Instituicao> buscarUsuarioPorCnpj(String cnpj) {
        return Optional.ofNullable(contasPorCnpj.get(cnpj));
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

    public boolean autenticarUsuario(String email, String senha) {
        Doador usuario = contasPorEmail.get(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public boolean autenticarUsuarioPorNomeUsuario(String nomeUsuario, String senha) {
        Doador usuario = contasPorNomeUsuario.get(nomeUsuario);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public void listarUsuarios() {
        contasPorEmail.values().forEach(usuario -> System.out.println(usuario));
    }
}
