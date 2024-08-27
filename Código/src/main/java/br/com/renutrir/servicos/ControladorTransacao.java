package br.com.renutrir.servicos;

import br.com.renutrir.model.Transacao;

import java.util.List;

public class ControladorTransacao {

    // Limite mínimo e máximo para valor de transação, exemplo fictício.
    private static final double VALOR_MINIMO = 0.01;
    private static final double VALOR_MAXIMO = 1000000.00;

    public boolean validarTransacao(Transacao transacao, List<String> idsDeTransacao) {
        return validarIdTransacao(transacao.getIdTransacao(), idsDeTransacao) &&
                validarValor(transacao.getValor());
    }

    public static boolean validarIdTransacao(String idTransacao, List<String> idsDeTransacao) {
        //Verifica se o ID é nulo ou não possui 32 caracteres
        if (idTransacao == null || idTransacao.length() != 32) {
            return false;
        }

        //Verifica se o ID contém apenas caracteres alfanuméricos
        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

        //Verifica se o ID já foi utilizado
        if (idsDeTransacao.contains(idTransacao)) {
            System.out.println("Esse ID de transação já foi utilizado.");
            return false;
        }

        //Se passou todas as verificações, o ID é válido
        return true;
    }

    private boolean validarValor(double valor) {
        if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
            System.out.println("Valor da transação deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + ".");
            return false;
        }
        return true;
    }
}

/*
package br.com.renutrir.servicos;

public class ControladorTransacao {

//PIXXXX
  
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

    ---------------------------------------------------------------------------------------------------------------

      private boolean validarValor(double valor) {
          if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
              System.out.println("Valor da transação deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + ".");
              return false;
          }
          return true;
      }
      ==============================================================================================================

      //CARTÃO DE CRÉDITO

      public static boolean validarAutenticacaoPagamento(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()){
        
            return false;  
          }
        // Verifica se o código da autenticação tem no mínimo 10 e no máximo 32 caracteres
        if (codigo.length() < 10 || codigo.length() > 32) {
            return false;
        }
        // Verifica se o código contém apenas letras e números
        for (int i = 0; i < codigo.length(); i++) {
            char c = codigo.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        // Se passar por todas as verificações, o código é válido
        return true;
    }
 
}
  
      
*/
