package ar.edu.utn.frba.dds.domain.tarjetas;

import lombok.Getter;
import lombok.Setter;

/**
 * FrecuenciaDiaria class permite representar la frecuencia de uso diaria.
 */
@Getter
@Setter
public class FrecuenciaDiaria extends FrecuenciaUso {
  private static Integer MAXIMOS_USOS_BASE;

  public FrecuenciaDiaria() {
  }

  public static void setMaximosUsosBase(Integer maximosUsosBase) {
    MAXIMOS_USOS_BASE = maximosUsosBase;
  }

  public boolean frecuenciaValida(Tarjeta tarjeta) {
    Integer usosTotalesPermitidos = MAXIMOS_USOS_BASE + tarjeta.getDuenio().cantidadMenores() * 2;
    return tarjeta.isActiva() && tarjeta.getCantidadUsosDia() < usosTotalesPermitidos;
  }

}