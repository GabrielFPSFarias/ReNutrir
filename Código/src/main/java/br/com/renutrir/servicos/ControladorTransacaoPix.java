package br.com.renutrir.servicos;

import java.util.ArrayList;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Transacao;

public class ControladorTransacaoPix {

    // Limite mínimo e máximo para valor de transação, exemplo fictício.
    private static final double VALOR_MINIMO = 0.01;
    private static final double VALOR_MAXIMO = 1000000000.00;

    public boolean validarTransacaoPix(Transacao transacao) {
        return validarIdTransacao(transacao) &&
                validarValor(transacao.getValorTransacao());
    }

    private boolean validarIdTransacao(Transacao transacao) {
        String idTransacao = transacao.getIdTransacao();
        Doador doador = transacao.getDoador();

        // Verifica se o ID é nulo ou não possui 32 caracteres
        if (idTransacao == null || idTransacao.length() != 32) {
            return false;
        }

        // Verifica se o ID contém apenas caracteres alfanuméricos
        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

        // Verifica se o ID já foi utilizado pelo doador
        if (identificarIdsDeTransacao(doador.getIdsDeTransacao(), idTransacao)) {
            System.out.println("Esse ID de transação já foi utilizado pelo usuário.");
            return false;
        }

        // Se passou todas as verificações, o ID é válido
        return true;
    }

    private boolean identificarIdsDeTransacao(ArrayList<String> idsDeTransacao, String idDeTransacaoInserido) {
        // Verifica se o ID está na lista de IDs do Doador
        return idsDeTransacao.contains(idDeTransacaoInserido);
    }

    private boolean validarValor(double valor) {
        if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
            System.out.println("Valor da transação deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + ".");
            return false;
        }
        return true;
    }

//Abaixo está o código anterior

/*
package br.com.renutrir.servicos;

import br.com.renutrir.model.Transacao;

public class TransacaoPix {
// Limite mínimo e máximo para valor de transação, exemplo fictício.
      private static final double VALOR_MINIMO = 0.01;
      private static final double VALOR_MAXIMO = 1000000000.00;

      public boolean validarTransacao(Transacao transacao) {
          return validarIdTransacao(transacao.getIdTransacao()) &&
                 validarValor(transacao.getValor());
      }

      public static boolean validarIdTransacao(String idTransacao) {
        // Verifica se o ID é nulo ou não possui 32 caracteres
        if (idTransacao == null || idTransacao.length() != 32) {
            return false;
        }

        // Verifica se o ID contém apenas caracteres alfanuméricos
        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

      if(identificarIdsDeTransacao(ArrayList idsDeTransacao, String idDeTransacaoInserido)){

        //Escrever que esse ID de transação já foi utilizado pelo usuário

        return false;

      }

        // Se passou todas as verificações, o ID é válido
        return true;
     }

      private boolean validarValor(double valor) {
          if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
              System.out.println("Valor da transação deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + ".");
              return false;
          }
          return true;
      }
*/
}


