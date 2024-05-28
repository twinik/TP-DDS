package ar.edu.utn.frba.dds.domain.tarjetas;

/**
 * FrecuenciaUso interface permite definir el comportamiento de la frecuencia de uso de una tarjeta.
 */
public abstract class FrecuenciaUso {

  public abstract boolean frecuenciaValida(Tarjeta tarjeta);

  // TEMPLATE METHOD
  boolean permiteUsar(Tarjeta tarjeta) {
    return tarjeta.isActiva() && frecuenciaValida(tarjeta);
  }

}