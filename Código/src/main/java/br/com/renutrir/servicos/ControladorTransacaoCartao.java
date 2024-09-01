package br.com.renutrir.servicos;

import br.com.renutrir.model.TransacaoCartaoDebito;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ControladorTransacaoCartao {

    public static boolean validarNumeroCartao(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.isEmpty() || !numeroCartao.matches("\\d+")) {
            return false;
        }
        return verificarLuhn(numeroCartao);
    }

    private static boolean verificarLuhn(String numeroCartao) {
        int soma = 0;
        boolean alternar = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            alternar = !alternar;
        }

        return (soma % 10 == 0);
    }

    public static boolean validarDataValidade(String dataValidade) {
        LocalDate hoje = LocalDate.now();
        LocalDate data = LocalDate.parse("01/" + dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (data.isBefore(hoje.withDayOfMonth(1))) {
            return false;
        } else if (data.getMonthValue() < 1 || data.getMonthValue() > 12) {
            return false;
        }

        return true;
    }

    public static boolean validarNomeTitular(String nome) {
        if (nome == null || nome.isEmpty() || nome.length() > 26) {
            return false;
        }
        return Pattern.compile("^[a-zA-Z\\s]+$").matcher(nome).matches();
    }

    public static boolean validarCVV(String cvv) {
        if (cvv == null || cvv.isEmpty() || !cvv.matches("\\d+")) {
            return false;
        }
        return cvv.length() == 3 || cvv.length() == 4;
    }

    public static boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty() || !senha.matches("\\d+")) {
            return false;
        }
        return senha.length() == 4 || senha.length() == 6;
    }

    public static boolean validarTransacaoCartaoDebito(String numeroCartao, String dataValidade, String nomeTitular, String cvv, String senha) {
        return validarNumeroCartao(numeroCartao) &&
               validarDataValidade(dataValidade) &&
               validarNomeTitular(nomeTitular) &&
               validarCVV(cvv) &&
               validarSenha(senha);
    }

    public static boolean validarTransacaoCartaoCredito(String numeroCartao, String dataValidade, String nomeTitular, String cvv) {
        return validarNumeroCartao(numeroCartao) &&
               validarDataValidade(dataValidade) &&
               validarNomeTitular(nomeTitular) &&
               validarCVV(cvv);
    }
}
