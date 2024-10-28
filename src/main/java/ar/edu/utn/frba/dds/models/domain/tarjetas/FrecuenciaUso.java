package ar.edu.utn.frba.dds.models.domain.tarjetas;

/**
 * FrecuenciaUso interface permite definir el comportamiento de la frecuencia de uso de una tarjeta.
 */
public interface FrecuenciaUso {
  boolean permiteUsar(Tarjeta tarjeta);
}