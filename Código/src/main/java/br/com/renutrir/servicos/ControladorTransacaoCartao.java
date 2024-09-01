package br.com.renutrir.servicos;

import br.com.renutrir.model.TransacaoCartaoCredito;
import br.com.renutrir.model.TransacaoCartaoDebito;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ControladorTransacaoCartao {

    public boolean validarNumeroCartao(TransacaoCartaoCredito transacao) {
        String numeroCartao = transacao.getNumeroCartao();
        return numeroCartao != null && !numeroCartao.isEmpty() && numeroCartao.matches("\\d+") && verificarLuhn(numeroCartao);
    }

    private boolean verificarLuhn(String numeroCartao) {
        int soma = 0;
        boolean alternar = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));
            if (alternar) digito = digito * 2 > 9 ? digito * 2 - 9 : digito * 2;
            soma += digito;
            alternar = !alternar;
        }

        return soma % 10 == 0;
    }

    public boolean validarDataValidade(TransacaoCartaoCredito transacao) {
        String dataValidade = transacao.getDataValidade();
        LocalDate data = LocalDate.parse("01/" + dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return !data.isBefore(LocalDate.now().withDayOfMonth(1)) && data.getMonthValue() >= 1 && data.getMonthValue() <= 12;
    }

    public boolean validarNomeTitular(TransacaoCartaoCredito transacao) {
        String nomeTitular = transacao.getNomeTitular();
        return nomeTitular != null && !nomeTitular.isEmpty() && nomeTitular.length() <= 26 && nomeTitular.matches("^[a-zA-Z\\s]+$");
    }

    public boolean validarCVV(TransacaoCartaoCredito transacao) {
        String cvv = transacao.getCvv();
        return cvv != null && cvv.matches("\\d{3,4}");
    }

    public boolean validarSenha(TransacaoCartaoDebito transacao) {
        String senha = transacao.getSenha();
        return senha != null && senha.matches("\\d{4,6}");
    }

    // Método para validar transações de cartão de débito
    public boolean validarTransacaoCartaoDebito(TransacaoCartaoDebito transacao) {
        return validarNumeroCartao(transacao) &&
               validarDataValidade(transacao) &&
               validarNomeTitular(transacao) &&
               validarCVV(transacao) &&
               validarSenha(transacao);
    }

    // Método para validar transações de cartão de crédito
    public boolean validarTransacaoCartaoCredito(TransacaoCartaoCredito transacao) {
        return validarNumeroCartao(transacao) &&
               validarDataValidade(transacao) &&
               validarNomeTitular(transacao) &&
               validarCVV(transacao);
    }
}
