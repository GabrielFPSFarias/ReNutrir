package br.com.renutrir.servicos;

import br.com.renutrir.model.TransacaoPix;
import java.util.ArrayList;

public class ControladorTransacaoPix {

    public ControladorTransacaoPix() {
    }

    public boolean validarTransacaoPix(TransacaoPix transacaoPix) {
        return validarIdTransacao(transacaoPix);
    }

    private boolean validarIdTransacao(TransacaoPix transacaoPix) {
        String idTransacao = transacaoPix.getIdTransacao();
        ArrayList<String> idsDeTransacao = transacaoPix.getDoador().getIdsDeTransacao();

        if (idTransacao == null || idTransacao.length() != 32) {
            return false;
        }

        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

        if (identificarIdsDeTransacao(idsDeTransacao, idTransacao)) {
            System.out.println("Esse ID de transação já foi utilizado pelo usuário.");
            return false;
        }

        return true;
    }

    private boolean identificarIdsDeTransacao(ArrayList<String> idsDeTransacao, String idDeTransacaoInserido) {
        return idsDeTransacao.contains(idDeTransacaoInserido);
    }
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



