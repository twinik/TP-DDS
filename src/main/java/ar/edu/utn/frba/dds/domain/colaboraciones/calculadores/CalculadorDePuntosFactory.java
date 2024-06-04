package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;

public class CalculadorDePuntosFactory {
  public static CalculadorDePuntos create(FormaColaboracion formaColaboracion) {
    return switch (formaColaboracion) {
      case DONACION_DINERO -> new CalculadorPuntosDonacionDinero();
      case DONACION_VIANDA -> new CalculadorPuntosDonacionVianda();
      case REGISTRO_PERSONA -> new CalculadorPuntosAltaPersona();
      case REDISTRIBUCION_VIANDA -> new CalculadorPuntosRedistribucionVianda();
      case OFERTA_PRODUCTO -> new CalculadorPuntosOfertaProducto();
      case COLOCACION_HELADERA -> new CalculadorPuntosColocacionHeladera();
      default -> null;
    };
  }
}
