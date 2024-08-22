package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase: RegistroTemperatura: guarda la hora y la temperatura registrada en ese instante
 */
@Getter
@AllArgsConstructor
public class RegistroTemperatura {
  private Long id;
  LocalDateTime fechaHora;
  float temperaturaRegistrada;

  public RegistroTemperatura(LocalDateTime fechaHora, float temperaturaRegistrada) {
    this.fechaHora = fechaHora;
    this.temperaturaRegistrada = temperaturaRegistrada;
  }
}
