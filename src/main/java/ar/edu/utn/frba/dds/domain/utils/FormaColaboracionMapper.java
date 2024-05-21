package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;

public class FormaColaboracionMapper {
  public static FormaColaboracion obtenerFormaColaboracion(String formaColaboracion) {
    return switch (formaColaboracion) {
      case "DINERO" -> FormaColaboracion.DONACION_DINERO;
      case "DONACION_VIANDAS" -> FormaColaboracion.DONACION_VIANDA;
      case "REDISTRIBUCION_VIANDAS" -> FormaColaboracion.REDISTRIBUCION_VIANDA;
      case "ENTREGA_TARJETAS" -> FormaColaboracion.REGISTRO_PERSONA;
      case "OFERTA_PRODUCTO" -> FormaColaboracion.OFERTA_PRODUCTO;
      case "COLOCACION_HELADERA" -> FormaColaboracion.COLOCACION_HELADERA;
      default -> throw new RuntimeException("Forma de Colaboracion Invalida");
    };
  }
}
