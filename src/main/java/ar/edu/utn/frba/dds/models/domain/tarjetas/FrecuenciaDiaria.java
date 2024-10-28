package ar.edu.utn.frba.dds.models.domain.tarjetas;

import lombok.Getter;
import lombok.Setter;

/**
 * FrecuenciaDiaria class permite representar la frecuencia de uso diaria.
 */
@Getter
@Setter
public class FrecuenciaDiaria implements FrecuenciaUso {
  private static Integer MAXIMOS_USOS_BASE = 4;

  public FrecuenciaDiaria() {
  }

  public static void setMaximosUsosBase(Integer maximosUsosBase) {
    MAXIMOS_USOS_BASE = maximosUsosBase;
  }

  public boolean permiteUsar(Tarjeta tarjeta) {
    Integer usosTotalesPermitidos = MAXIMOS_USOS_BASE + tarjeta.getDuenio().cantidadMenores() * 2;
    return tarjeta.getCantidadUsosDia() <= usosTotalesPermitidos;
  }

}