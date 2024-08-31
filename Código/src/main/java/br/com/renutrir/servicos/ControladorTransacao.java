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

      @  VALIDAR N° DO CARTÃO

      public static void validarNumeroCartao(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.isEmpty() || !numeroCartao.matches("\\d+")) {
            throw new NumeroCartaoInvalidoException("Número de cartão inválido. Deve conter apenas dígitos.");
        }

        if (!verificarLuhn(numeroCartao)) {
            throw new NumeroCartaoInvalidoException("Número de cartão inválido. Falhou na verificação de Luhn.");
        }
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


            //EXCEÇÃO

                Classe para a exceção personalizada
                public class NumeroCartaoInvalidoException extends RuntimeException {
                    public NumeroCartaoInvalidoException(String mensagem) {
                        super(mensagem);
                    }
                }
                
                
                MAIN 
                
                 try {
                            validarNumeroCartao("4532015112830366"); // Número de exemplo válido
                            System.out.println("Número de cartão válido.");
                        } catch (NumeroCartaoInvalidoException e) {
                            System.err.println(e.getMessage());
                        }

                        


 @  VALIDAR DATA DE VALIDADE

 CÓDIGO 1 (SIMPLIFICADO)

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidadorCartao {

      public static boolean validarDataValidade(String dataValidade) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
         LocalDate hoje = LocalDate.now();

            // Tenta parsear a data fornecida
            LocalDate data = LocalDate.parse("01/" + dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Verifica se a data está no passado
            if (data.isBefore(hoje.withDayOfMonth(1))) {
               return false;
            }

            else if (data.getMonthValue() < 1 || data.getMonthValue() > 12) {
               return false;
            }

            return true;
      }

      ===============================================================================================================================

CÓDIGO 2

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

      public static void main(String[] args) {
         // Teste com datas válidas e inválidas

         if(validarDataValidade("01/2023")){

            System.out.println("Data válida");
         }
         else System.out.println("Data inválida");
      }
      
public static void main(String[] args) {
   // Teste com datas válidas e inválidas
   try {
      validarDataValidade("08/2024");  // Data válida
      System.out.println("Data de validade válida.");
   } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
   }

   try {
      validarDataValidade("12/2022");  // Data já expirada
      System.out.println("Data de validade válida.");
   } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
   }

   try {
      validarDataValidade("13/2025");  // Mês inválido
      System.out.println("Data de validade válida.");
   } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
   }

   try {
      validarDataValidade("08/2025");  // Data válida
      System.out.println("Data de validade válida.");
   } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
   }
}

   public static void validarDataValidade(String dataValidade) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
      LocalDate hoje = LocalDate.now();

      try {
         // Adiciona o primeiro dia do mês à data fornecida
         LocalDate data = LocalDate.parse("01/" + dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

         // Verifica se a data está no passado
         LocalDate primeiroDiaDoMes = data.withDayOfMonth(1);
         LocalDate ultimoDiaDoMes = primeiroDiaDoMes.plusMonths(1).minusDays(1);

         // Verifica se o primeiro dia do mês da data fornecida é antes do início do mês atual
         if (primeiroDiaDoMes.isBefore(hoje.withDayOfMonth(1))) {
            throw new IllegalArgumentException("Data de validade já expirada.");
         }

         // Verifica se o último dia do mês é antes do início do mês atual
         if (ultimoDiaDoMes.isBefore(hoje.withDayOfMonth(1))) {
            throw new IllegalArgumentException("Data de validade já expirada.");
         }

      } catch (DateTimeParseException e) {
         throw new IllegalArgumentException("Formato de data inválido. Use MM/AAAA.");
      }
    }
   }



   






 
}     
*/
