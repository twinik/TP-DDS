package ar.edu.utn.frba.dds.models.messageFactory;

import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;

public class MensajeViandasFaltantesFactory {
    public static String generarMensaje(int viandasFaltantes, Heladera heladera) {
        return "Falta(n) " + viandasFaltantes + " vianda(s) para que la heladera: " +
                heladera.getNombre() + " alcance su capacidad maxima";
    }
}
