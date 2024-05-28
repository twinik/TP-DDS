package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Clase: RegistroTemperatura: guarda la hora y la temperatura registrada en ese instante
 */
@Getter
@AllArgsConstructor
public class RegistroTemperatura {
  LocalDateTime fechaHora;
  float temperaturaRegistrada;
}
