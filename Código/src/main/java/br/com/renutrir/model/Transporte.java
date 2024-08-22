package br.com.renutrir.model;

import java.time.LocalDateTime;
import java.time.Duration;

public class Transporte {

  private String tipoDeTransporte;
  private LocalDateTime horarioDeColeta;
  private LocalDateTime horarioDeEntrega;

  //Herdar tipoDeDoacao da intenção de doação
  
  public Transporte() {
  }
  
  public Transporte(String tipoDeTransporte, LocalDateTime horarioDeColeta, LocalDateTime horarioDeEntrega) {
    this.tipoDeTransporte = tipoDeTransporte;
    setHorarioDeColeta(horarioDeColeta);
    setHorarioDeEntrega(horarioDeEntrega);
  }

  public String getTipoDeTransporte() {
    return tipoDeTransporte;
  }

  public void setTipoDeTransporte(String tipoDeTransporte) {
    this.tipoDeTransporte = tipoDeTransporte;
  }

  public LocalDateTime getHorarioDeColeta() {
    return horarioDeColeta;
  }

  public void setHorarioDeColeta(LocalDateTime horarioDeColeta) {
    if (horarioDeColeta.isAfter(LocalDateTime.now())) {
      this.horarioDeColeta = horarioDeColeta;
    } else {
      throw new IllegalArgumentException("O horário de coleta não pode ser no passado.");
    }
  }

  public LocalDateTime getHorarioDeEntrega() {
    return horarioDeEntrega;
  }

  public void setHorarioDeEntrega(LocalDateTime horarioDeEntrega) {
    if (horarioDeEntrega.isAfter(horarioDeColeta)) {
      this.horarioDeEntrega = horarioDeEntrega;
    } else {
      throw new IllegalArgumentException("O horário de entrega deve ser após o horário de coleta.");
    }
  }

  // Calcular duração do transporte
  public Duration calcularDuracao() {
    if (horarioDeColeta != null && horarioDeEntrega != null) {
      return Duration.between(horarioDeColeta, horarioDeEntrega);
    } else {
      throw new IllegalStateException("Horário de coleta e/ou entrega não foram definidos.");
    }
  }

  // Verificar se o transporte está em andamento
  public boolean estaEmAndamento() {
    LocalDateTime agora = LocalDateTime.now();
    return horarioDeColeta != null && horarioDeEntrega != null &&
            agora.isAfter(horarioDeColeta) && agora.isBefore(horarioDeEntrega);
  }

  @Override
  public String toString() {
    return "Transporte{" +
            "tipoDeTransporte='" + tipoDeTransporte + '\'' +
            ", horarioDeColeta=" + horarioDeColeta +
            ", horarioDeEntrega=" + horarioDeEntrega +
            '}';
  }
}
