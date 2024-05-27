package ar.edu.utn.frba.dds.domain.tarjetas;

import lombok.Getter;
import lombok.Setter;

/**
 * FrecuenciaDiaria class permite representar la frecuencia de uso diaria.
 */
@Getter
@Setter
public class FrecuenciaDiaria implements FrecuenciaUso {
  public FrecuenciaDiaria() {
  }

  private static Integer MAXIMOS_USOS_BASE;

  public static void setMaximosUsosBase(Integer maximosUsosBase) {
    MAXIMOS_USOS_BASE = maximosUsosBase;
  }

  public boolean permiteUsar(Tarjeta tarjeta) {
    Integer usosTotalesPermitidos = MAXIMOS_USOS_BASE + tarjeta.getDuenio().cantidadMenores() * 2;
    return tarjeta.getCantidadUsosDia() < usosTotalesPermitidos;
  }

}