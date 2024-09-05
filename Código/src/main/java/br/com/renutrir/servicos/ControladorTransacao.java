package br.com.renutrir.servicos;

import br.com.renutrir.model.Transacao;
import br.com.renutrir.model.TransacaoPix;
import br.com.renutrir.model.TransacaoCartaoCredito;
import br.com.renutrir.model.TransacaoCartaoDebito;

public class ControladorTransacao {

    public ControladorTransacao() {
    }

    private static final double VALOR_MINIMO = 0.01;
    private static final double VALOR_MAXIMO = 1000000000.00;

    private ControladorTransacaoPix controladorPix = new ControladorTransacaoPix();
    private ControladorTransacaoCartao controladorCartao = new ControladorTransacaoCartao();

    
    public boolean validarTransacao(Transacao transacao) {
        if (!validarValor(transacao.getValorTransacao())) {
            return false;
        }

        if (transacao instanceof TransacaoPix) {
            return controladorPix.validarTransacaoPix((TransacaoPix) transacao);
        } else if (transacao instanceof TransacaoCartaoCredito) {
            return controladorCartao.validarTransacaoCartaoCredito((TransacaoCartaoCredito) transacao);
        } else if (transacao instanceof TransacaoCartaoDebito) {
            return controladorCartao.validarTransacaoCartaoDebito((TransacaoCartaoDebito) transacao);
        }

        return false;
    }

    private boolean validarValor(double valor) {
        return valor >= VALOR_MINIMO && valor <= VALOR_MAXIMO;
    }
}
/*
    //PIX

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
        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

       
        if (idsDeTransacao.contains(idTransacao)) {
            System.out.println("Esse ID de transação já foi utilizado.");
            return false;
        }

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


*/
/*
package br.com.renutrir.servicos;

public class ControladorTransacao {

//PIXXXX
  
      
      private static final double VALOR_MINIMO = 0.01;
      private static final double VALOR_MAXIMO = 1000000000.00;
  
      public boolean validarTransacao(Transacao transacao) {
          return validarIdTransacao(transacao.getIdTransacao()) &&
                 validarValor(transacao.getValor());
      }
  
      public static boolean validarIdTransacao(String idTransacao) {
        
        if (idTransacao == null || idTransacao.length() != 32) {
            return false;
        }
        
        
        if (!idTransacao.matches("[A-Za-z0-9]+")) {
            return false;
        }

      if(identificarIdsDeTransacao(ArrayList idsDeTransacao, String idDeTransacaoInserido)){

        
        return false;

      }
        
        return true;
     }

      private boolean validarValor(double valor) {
          if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
              System.out.println("Valor da transação deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + ".");
              return false;
          }
          return true;
      }
#########################################################################################################################################

 //CARTÃO DE CRÉDITO

      @  VALIDAR N° DO CARTÃO 




CÓDIGO (BOOLEAN)


public static boolean validarNumeroCartao(String numeroCartao) {
        // Verificar se o número do cartão é nulo, vazio ou não contém apenas dígitos
        if (numeroCartao == null || numeroCartao.isEmpty() || !numeroCartao.matches("\\d+")) {
            return false;
        }

        // Verificar se o número do cartão passa na verificação de Luhn
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






   @ NOME TO TITULAR

   CÓDIGO 1 (RETORNA EXCEÇÕES)

   public class ValidadorNome {

    // Regex para validar o nome do titular do cartão, permitindo pontos
    private static final String NOME_REGEX = "^[A-Za-zÀ-ÿ.]+(?: [A-Za-zÀ-ÿ.]+)*$";
    private static final Pattern NOME_PATTERN = Pattern.compile(NOME_REGEX);

    public static void main(String[] args) {
        // Teste com exemplos de nomes
        String[] nomesParaTestar = {
                "João da Silva",                // Válido
                "Maria Clara",                  // Válido
                "John Doe",                     // Válido
                "Jean-Luc Picard",              // Válido
                "Ana-Maria",                    // Inválido (hifens não permitidos)
                "O. Connor",                    // Válido (ponto permitido)
                "Name.With.Points",             // Válido (pontos permitidos)
                "Name With   Multiple Spaces",  // Inválido (múltiplos espaços)
                "Name@With$Special#Characters", // Inválido (caracteres especiais não permitidos)
                "1234"                          // Inválido (números não permitidos)
        };

        for (String nome : nomesParaTestar) {
            if (validarNomeTitular(nome)) {
                System.out.println(nome + " é um nome válido.");
            } else {
                System.err.println(nome + " é inválido.");
            }
        }
    }

    public static boolean validarNomeTitular(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }

        if (nome.length() > 26) {
            return false;
        }

        if (!NOME_PATTERN.matcher(nome).matches()) {
            return false;
        }

        return true;
    }
}

=========================================================================================================================================

  CÓDIGO 2 

  import java.util.regex.Pattern;

public class ValidadorNome {

    // Regex para validar o nome do titular do cartão, permitindo pontos
    private static final String NOME_REGEX = "^[A-Za-zÀ-ÿ.]+(?: [A-Za-zÀ-ÿ.]+)*$";
    private static final Pattern NOME_PATTERN = Pattern.compile(NOME_REGEX);

    public static void main(String[] args) {
        // Teste com exemplos de nomes
        String[] nomesParaTestar = {
                "João da Silva",                // Válido
                "Maria Clara",                  // Válido
                "John Doe",                     // Válido
                "Jean-Luc Picard",              // Válido
                "Ana-Maria",                    // Inválido (hifens não permitidos)
                "O. Connor",                    // Válido (ponto permitido)
                "Name.With.Points",             // Válido (pontos permitidos)
                "Name With   Multiple Spaces",  // Inválido (múltiplos espaços)
                "Name@With$Special#Characters", // Inválido (caracteres especiais não permitidos)
                "1234"                          // Inválido (números não permitidos)
        };

        for (String nome : nomesParaTestar) {
            if (validarNomeTitular(nome)) {
                System.out.println(nome + " é um nome válido.");
            } else {
                System.err.println(nome + " é inválido.");
            }
        }
    }

    public static boolean validarNomeTitular(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }

        if (nome.length() > 26) {
            return false;
        }

        return NOME_PATTERN.matcher(nome).matches();
    }
}



   
@ VALIDAR CVV

CÓDIGO 1

  for (String cvv : cvvsParaTestar) {
            if (validarCVV(cvv)) {
                System.out.println(cvv + " é um CVV válido.");
            } else {
                System.err.println(cvv + " é um CVV inválido.");
            }
        }
    }

    public static boolean validarCVV(String cvv) {
        // Verificar se o CVV é nulo ou vazio
        if (cvv == null || cvv.isEmpty()) {
            return false;
        }

        // Verificar se o CVV contém apenas números
        if (!cvv.matches("\\d+")) {
            return false;
        }

        // Verificar o comprimento do CVV (3 ou 4 dígitos)
        return cvv.length() == 3 || cvv.length() == 4;
    }



#########################################################################################################################################

//PAGAMENTO EM DÉBITO

@ VALIDAR SENHA

public static boolean validarSenha(String senha) {
        
        if (senha == null || senha.isEmpty()) {
            return false;
        }

      
        if (!senha.matches("\\d+")) {
            return false;
        }
        
        return senha.length() == 4 || senha.length() == 6;
    }



*/

