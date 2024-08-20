package br.com.renutrir.model;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Transporte {

  private String tipoDeTransporte;
  
  //Herdar tipoDeDoacao da intenção de doação
  
  private LocalDateTime horarioDeColeta;
  private LocalDateTime horarioDeEntrega;
}
