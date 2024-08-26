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

        // Aqui dá pra adicionar o código para salvar a conta no banco de dados ou outro repositório

        return novaConta;
    }

    private void validarDados(String nome, String nomeUsuario, String email, String senha, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

/* SUGESTÃO DE CONTROLADOR DE NOME

public static void validarNome(String nome) {
        // Verifica se o nome é null ou vazio
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        // Regex para garantir que o nome tenha pelo menos 2 caracteres alfabéticos
        // e que permita apenas letras, espaços e apóstrofos
        String regex = "^[a-zA-Z]+(?: [a-zA-Z]+)*(?: '[a-zA-Z]+)*$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);

        // Verifica se o nome corresponde ao padrão
        Matcher matcher = pattern.matcher(nome);

        // Verifica se o nome é válido
        if (!matcher.matches() || nome.length() < 2) {
            throw new IllegalArgumentException("O nome deve ter pelo menos dois caracteres e pode conter apenas letras, espaços e apóstrofos.");
        }
    }

CÓDIGO DA EXCEÇÃO NO MAIN

for (String name : testNames) {
            try {
                validarNome(name);
                System.out.println(name + " é válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(name + " é inválido: " + e.getMessage());
            }
        }
        */

        if (nomeUsuario == null || nomeUsuario.length() < 6) {
            throw new IllegalArgumentException("O nome de usuário deve ter 6 ou mais caracteres.");
        }

        /* SUGESTÂO PARA VALIDAR O NOME DE USUÁRIO

import java.util.regex.Pattern;
import java.util.regex.Matcher;

     public static void validarNomeDeUsuario(String nomeUsuario) {
        // Verifica se o nome de usuário é null ou vazio
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        // Regex para garantir que o nome de usuário tenha pelo menos 2 caracteres alfanuméricos
        // e que pontos, underscores e hífens não sejam consecutivos
        String regex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);

        // Verifica se o nome do usuário corresponde ao padrão
        Matcher matcher = pattern.matcher(nomeUsuario);

        // Verifica se o nome de usuário é válido
        if (!matcher.matches()) {
            throw new IllegalArgumentException("O nome de usuário deve ter 2 ou mais caracteres alfanuméricos, "
                    + "além de ponto, hífen e sublinhado, exceto de forma consecutiva.");
        }
    }

    Código da exceção no MAIN

    for (String user : testUsers) {
            try {
                validarNomeDeUsuario(user);
                System.out.println(user + " é válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(user + " é inválido: " + e.getMessage());
            }
        }

        */
        

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

         /*SUGESTÂO DE NOVO CÓDIGO PARA VALIDAÇÂO DO EMAIL;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

    public static boolean validarEmail(String email) {
        // Regex que segue as regras descritas
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);
        
        // Verifica se o e-mail corresponde ao padrão
        Matcher matcher = pattern.matcher(email);

        // Retorna verdadeiro se o e-mail for válido
        return matcher.matches();
    }
        */
        

        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("A senha deve ter mais de 4 caracteres.");
        }

        if (telefone == null || telefone.length() < 8) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
    }
}
