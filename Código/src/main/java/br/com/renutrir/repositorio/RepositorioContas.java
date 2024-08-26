package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContas {

    // Salva os doadores por email, nome usuário e CPF
    private Map<String, Doador> doadoresPorEmail;
    private Map<String, Doador> doadoresPorNomeUsuario;
    private Map<String, Doador> doadoresPorCpf;
    private Map<String, Doador> contasPorEmail;
    private Map<String, Doador> contasPorNomeUsuario;
    private Map<String, Doador> contasPorCpf;

    // Salva as instituições por CNPJ, nome usuário instituição e email
    private Map<String, Instituicao> instituicoesPorCnpj;
    private Map<String, Instituicao> instituicoesPorNomeUsuario;
    private Map<String, Instituicao> instituicoesPorEmail;
    private Map<String, Instituicao> contasPorCnpj;
    private Map<String, Instituicao> contasPorNomeInstituicao;
    private Map<String, Instituicao> contasEmailInstituicao;

    public RepositorioContas() {
        this.doadoresPorEmail = new HashMap<>();
        this.doadoresPorNomeUsuario = new HashMap<>();
        this.doadoresPorCpf = new HashMap<>();
        this.instituicoesPorCnpj = new HashMap<>();
        this.instituicoesPorNomeUsuario = new HashMap<>();
        this.instituicoesPorEmail = new HashMap<>();
        this.contasPorEmail = new HashMap<>();
        this.contasPorNomeUsuario = new HashMap<>();
        this.contasPorCpf = new HashMap<>();
        this.contasPorCnpj = new HashMap<>();
        this.contasPorNomeInstituicao = new HashMap<>();
        this.contasEmailInstituicao = new HashMap<>();
    }

    public boolean adicionarDoador(Doador doador) {
        if (doadoresPorEmail.containsKey(doador.getEmail())) {
            System.out.println("Erro: Já existe um doador com esse email.");
            return false;
        }
        if (doadoresPorNomeUsuario.containsKey(doador.getNomeUsuario())) {
            System.out.println("Erro: Já existe um doador com esse nome de usuário.");
            return false;
        }
        if (doadoresPorCpf.containsKey(doador.getCpf())) {
            System.out.println("Erro: Já existe um doador com esse CPF.");
            return false;
        }

        // Adicionar o doador aos mapas
        doadoresPorEmail.put(doador.getEmail(), doador);
        doadoresPorNomeUsuario.put(doador.getNomeUsuario(), doador);
        doadoresPorCpf.put(doador.getCpf(), doador);

        return true;
    }

    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (instituicoesPorEmail.containsKey(instituicao.getEmail())) {
            System.out.println("Erro: Já existe uma instituição com esse email.");
            return false;
        }
        if (instituicoesPorNomeUsuario.containsKey(instituicao.getNomeUsuario())) {
            System.out.println("Erro: Já existe uma instituição com esse nome de usuário.");
            return false;
        }
        if (instituicoesPorCnpj.containsKey(instituicao.getCnpj())) {
            System.out.println("Erro: Já existe uma instituição com esse CNPJ.");
            return false;
        }

        // Adicionar a instituição aos mapas
        instituicoesPorEmail.put(instituicao.getEmail(), instituicao);
        instituicoesPorNomeUsuario.put(instituicao.getNomeUsuario(), instituicao);
        instituicoesPorCnpj.put(instituicao.getCnpj(), instituicao);

        return true;
    }

    public Optional<Doador> buscarDoadorPorEmail(String email) {
        return Optional.ofNullable(doadoresPorEmail.get(email));
    }

    public Optional<Doador> buscarDoadorPorNomeUsuario(String nomeUsuario) {
        return Optional.ofNullable(doadoresPorNomeUsuario.get(nomeUsuario));
    }

    public Optional<Doador> buscarDoadorPorCpf(String cpf) {
        return Optional.ofNullable(doadoresPorCpf.get(cpf));
    }

    public Optional<Instituicao> buscarInstituicaoPorEmail(String email) {
        return Optional.ofNullable(instituicoesPorEmail.get(email));
    }

    public Optional<Instituicao> buscarInstituicaoPorNomeUsuario(String nomeUsuario) {
        return Optional.ofNullable(instituicoesPorNomeUsuario.get(nomeUsuario));
    }

    public Optional<Instituicao> buscarInstituicaoPorCnpj(String cnpj) {
        return Optional.ofNullable(instituicoesPorCnpj.get(cnpj));
    }

    public void removerDoador(String email) {
        Doador doador = doadoresPorEmail.remove(email);
        if (doador != null) {
            doadoresPorNomeUsuario.remove(doador.getNomeUsuario());
            doadoresPorCpf.remove(doador.getCpf());
        } else {
            System.out.println("Doador não encontrado.");
        }
    }

    public void removerInstituicao(String email) {
        Instituicao instituicao = instituicoesPorEmail.remove(email);
        if (instituicao != null) {
            instituicoesPorNomeUsuario.remove(instituicao.getNomeUsuario());
            instituicoesPorCnpj.remove(instituicao.getCnpj());
        } else {
            System.out.println("Instituição não encontrada.");
        }
    }

    public boolean autenticarDoador(String email, String senha) {
        Doador doador = doadoresPorEmail.get(email);
        return doador != null && doador.getSenha().equals(senha);
    }

    //Exceção?????????
    //Login????

    public boolean autenticarInstituicao(String email, String senha) {
        Instituicao instituicao = instituicoesPorEmail.get(email);
        return instituicao != null && instituicao.getSenha().equals(senha);
    }

    //Exceção?

    public void listarDoadores() {
        doadoresPorEmail.values().forEach(doador -> System.out.println(doador));
    }

    public void listarInstituicoes() {
        instituicoesPorEmail.values().forEach(instituicao -> System.out.println(instituicao));
    }

    public Instituicao buscarInstituicaoPorEmailOuUsuario(String emailOuUsuario) {
        Instituicao instituicao = contasEmailInstituicao.get(emailOuUsuario);
        if (instituicao == null) {
            instituicao = contasPorNomeInstituicao.get(emailOuUsuario);
        }
        return instituicao;
    }

    public Doador buscarDoadorPorEmailOuUsuario(String emailOuUsuario) {
        Doador doador = contasPorEmail.get(emailOuUsuario);
        if (doador == null) {
            doador = contasPorNomeUsuario.get(emailOuUsuario);
        }
        return doador;
    }

/* ************ private Map<String, Doador> doadores = new HashMap<>();
    private Map<String, Instituicao> instituicoes = new HashMap<>();

    // Adicionar doador
    public boolean adicionarUsuario(Doador doador) {
        return true;
    }*/

}
