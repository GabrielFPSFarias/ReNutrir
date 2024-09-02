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
        novaConta.setEndereco(endereco); //Conectar ao ControladorEndereco

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
   // Tem pelo menos 2 letras e permite apenas letras, espaços e apóstrofos
    
    public static void validarNome(String nome) {
        // Verifica se o nome é null ou vazio
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        // Garante que tenha pelo menos 2 letras e que permite apenas letras, espaços e apóstrofos
        String regex = "^[a-zA-Z]+(?: [a-zA-Z]+)*(?: '[a-zA-Z]+)*$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(nome);

        if (!matcher.matches() || nome.length() < 2) {
            throw new IllegalArgumentException("O nome deve ter pelo menos dois caracteres e pode conter apenas letras, espaços e apóstrofos.");
        }
    }


    // SUGESTÃO PARA VALIDAR O NOME DE USUÁRIO
    // nome de usuário tem pelo menos 2 caracteres alfanuméricos e pontos, underscores e hífens não podem ser consecutivos

    
    public static void validarNomeDeUsuario(String nomeUsuario) {
        // Verifica se o nome de usuário é null ou vazio
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        String regex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(nomeUsuario);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("O nome de usuário deve ter 2 ou mais caracteres alfanuméricos, "
                    + "além de ponto, hífen e sublinhado, exceto de forma consecutiva.");
        }
    }

   
    // SUGESTÃO DE NOVO CÓDIGO PARA VALIDAÇÃO DO EMAIL
    //Só permite caracteres alfanuméricos pontos e hífens.

    public static boolean validarEmail(String email) {

        
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

     
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /* 
    if (senha == null || senha.isEmpty() || senha.length() < 8) {
        throw new IllegalArgumentException("A senha deve ter mais de 8 caracteres.");
    }
    */ 

    // SUGESTÃO DE VALIDAÇÃO PARA SENHA
    //Mínimo de 4 caracteres
    
    public static void validarSenha(String senha) {
        
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser null ou vazia.");
        }

        if (senha.length() < 4) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 4 caracteres.");
        }
    }

    /*
    if (telefone == null || telefone.length() < 8) {
        throw new IllegalArgumentException("Telefone inválido.");
    }
    */
    
    // SUGESTÃO DE CÓDIGO PARA O TELEFONE
    // Espaços, hifens e parênteses são opcionais e permitidos para formatação
       
    public static void validarTelefone(String telefone) {
        // Verifica se o telefone é null ou vazio
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode ser null ou vazio.");
        }
        
        String regex = "^\\+\\d{1,3}[ -]?\\(?\\d{1,4}\\)?[ -]?\\d{1,5}[ -]?\\d{1,5}[ -]?\\d{1,5}$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(telefone);
        
        if (!matcher.matches()) {
            throw new IllegalArgumentException("O número de telefone é inválido. Ele deve estar no formato internacional.");
        }
    }

}

