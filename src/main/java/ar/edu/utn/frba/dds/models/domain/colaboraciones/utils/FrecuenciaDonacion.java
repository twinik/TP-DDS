package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

/**
 * FrecuenciaDonacion enum representa las frecuencias de donacion.
 */
public enum FrecuenciaDonacion {
  DIARIA,
  SEMANAL,
  MENSUAL,
  ANUAL;

  public static String mapearTexto(FrecuenciaDonacion f) {
    String primerCaracter = f.name().substring(0, 1);
    return primerCaracter + f.name().substring(1).toLowerCase();
  }

  public static FrecuenciaDonacion fromOrdinal(Integer ordinal) {
    return values()[ordinal];
  }
}