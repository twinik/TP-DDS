package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UsoTarjeta class permite representar el uso de una tarjeta.
 */
@Getter
public class UsoTarjeta {
  private Long id;
  private LocalDateTime fechaUso;
  private Heladera heladera;

  public UsoTarjeta(Tarjeta tarjeta, LocalDateTime fechaUso, Heladera heladera) {
    this.fechaUso = fechaUso;
    this.heladera = heladera;
    tarjeta.agregarUsos();
  }

}