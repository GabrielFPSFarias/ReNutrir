package br.com.renutrir.servicos;

import br.com.renutrir.model.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ControladorCadastro {

    public Conta cadastrarConta(String nome, String nomeUsuario, String email, String senha, String telefone, Endereco endereco) {
        validarDados(nome, nomeUsuario, email, senha, telefone);

        Conta novaConta = new Conta();
        novaConta.setNome(nome);
        novaConta.setNomeUsuario(nomeUsuario);
        novaConta.setEmail(email);
        novaConta.setSenha(senha);
        novaConta.setTelefone(telefone);
        novaConta.setEndereco(endereco); //AINDA NÃO TEM CONTROLADOR

        // Aqui dá pra adicionar o código para salvar a conta no banco de dados ou outro repositório

        return novaConta;
    }

    private void validarDados(String nome, String nomeUsuario, String email, String senha, String telefone) {
        validarNome(nome);
        validarNomeDeUsuario(nomeUsuario);
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        validarSenha(senha);
        validarTelefone(telefone);
    }

    // SUGESTÃO DE CONTROLADOR DE NOME
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

    // CÓDIGO DA EXCEÇÃO NO MAIN
    /*
    for (String name : testNames) {
        try {
            validarNome(name);
            System.out.println(name + " é válido.");
        } catch (IllegalArgumentException e) {
            System.out.println(name + " é inválido: " + e.getMessage());
        }
    }
    */

    // SUGESTÃO PARA VALIDAR O NOME DE USUÁRIO
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

    /* Código da exceção no MAIN
    for (String user : testUsers) {
        try {
            validarNomeDeUsuario(user);
            System.out.println(user + " é válido.");
        } catch (IllegalArgumentException e) {
            System.out.println(user + " é inválido: " + e.getMessage());
        }
    }
    */

    // SUGESTÃO DE NOVO CÓDIGO PARA VALIDAÇÃO DO EMAIL
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

    /* 
    if (senha == null || senha.isEmpty() || senha.length() < 8) {
        throw new IllegalArgumentException("A senha deve ter mais de 8 caracteres.");
    }
    */ 

    // SUGESTÃO DE VALIDAÇÃO PARA SENHA
    public static void validarSenha(String senha) {
        // Verifica se a senha é null ou vazia
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser null ou vazia.");
        }

        // Verifica se a senha tem pelo menos 4 caracteres
        if (senha.length() < 4) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 4 caracteres.");
        }
    }

    // EXCEÇÕES NO MAIN
    /*
    for (String senha : testSenhas) {
        try {
            validarSenha(senha);
            System.out.println("Senha \"" + senha + "\" é válida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Senha \"" + senha + "\" é inválida: " + e.getMessage());
        }
    }
    */
        
    /*
    if (telefone == null || telefone.length() < 8) {
        throw new IllegalArgumentException("Telefone inválido.");
    }
    */
    
    // SUGESTÃO DE CÓDIGO PARA O TELEFONE
    public static void validarTelefone(String telefone) {
        // Verifica se o telefone é null ou vazio
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode ser null ou vazio.");
        }
         
        // Expressão regular para validar o formato internacional de número de telefone
        // A regex permite:
        // - Começar com '+' seguido pelo código do país (1 a 3 dígitos)
        // - Espaços, hifens e parênteses são opcionais e permitidos para formatação
        // - O restante do número deve ser composto de dígitos
        String regex = "^\\+\\d{1,3}[\\s\\-]?\\(?\\d{1,4}\\)?[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,9}$";
        
        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);
        
        // Verifica se o número de telefone corresponde ao padrão
        Matcher matcher = pattern.matcher(telefone);
        
        if (!matcher.matches()) {
            throw new IllegalArgumentException("O número de telefone é inválido. Ele deve estar no formato internacional.");
        }
    }

    /* CÓDIGO DAS EXCEÇÕES
    for (String telefone : testTelefones) {
        try {
            validarTelefone(telefone);
            System.out.println("Telefone \"" + telefone + "\" é válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Telefone \"" + telefone + "\" é inválido: " + e.getMessage());
        }
    }
    */
}
    
