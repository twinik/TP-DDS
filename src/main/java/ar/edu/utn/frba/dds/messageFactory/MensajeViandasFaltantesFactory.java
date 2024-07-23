package ar.edu.utn.frba.dds.messageFactory;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;

public class MensajeViandasFaltantesFactory {
  public static String generarMensaje(int viandasFaltantes, Heladera heladera) {
    return "Faltan " + viandasFaltantes + " viandas para que la heladera " +
        heladera.getNombre() + " alcance su capacidad maxima";
  }
}
