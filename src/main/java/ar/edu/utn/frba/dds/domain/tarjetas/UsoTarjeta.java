package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UsoTarjeta class permite representar el uso de una tarjeta.
 */
@Getter
@AllArgsConstructor
public class UsoTarjeta {
  private LocalDateTime fechaUso;
  private Heladera heladera;
}